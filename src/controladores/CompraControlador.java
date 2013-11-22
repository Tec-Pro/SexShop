/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import abm.ABMCompra;
import busquedas.busqueda;
import interfaz.AplicacionGui;
import interfaz.CompraGui;
import interfaz.ComprasRealizadas;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.table.DefaultTableModel;
import modelos.Cliente;
import modelos.Compra;
import modelos.Producto;
import modelos.Proveedor;
import modelos.Venta;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.util.Pair;
import org.javalite.activejdbc.Base;
import org.javalite.activejdbc.LazyList;

/**
 *
 * @author alan
 */
public class CompraControlador implements ActionListener,CellEditorListener{
    private JTextField textcuil;
    private JTextField textnom;
   // private JTextField textnom;
   // private JTextField textmarca;
   // private JTextField textcodprod;
    private List prodlista;
    private List provlista;
    private busqueda busqueda;
    private ABMCompra abmCompra;
    private CompraGui compraGui;
    private JTable tablaprov;
    private AplicacionGui apgui;
    private DefaultTableModel tablaProveedores;
    private DefaultTableModel tablaProd;
    private JTable tablafac;
    private ControladorJReport reporteFactura;
    private ComprasRealizadas compraRealizadaGui;

    public CompraControlador(CompraGui compraGui, ComprasRealizadas compra) throws JRException, ClassNotFoundException, SQLException{

        //Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/sexshop", "root", "root");
        prodlista = new LinkedList<Producto>();
        provlista = new LinkedList<Proveedor>();
        busqueda = new busqueda();
        abmCompra = new ABMCompra();
        this.compraGui = compraGui;
        this.compraGui.setActionListener(this);
        this.compraRealizadaGui = compra;
        textcuil = compraGui.getBusquedaCuil();
        textcuil.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyReleased(java.awt.event.KeyEvent evt) {
                busquedaProveedorKeyReleased(evt);
            }
        });
        textnom = compraGui.getNombreProveedor();
        textnom.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyReleased(java.awt.event.KeyEvent evt) {
                busquedaProveedorKeyReleased(evt);
            }
        });
        
        tablaprov = compraGui.getTablaProveedores();
        tablaprov.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaProvMouseClicked(evt);
            }
        });
        
       
        
        tablaProveedores = compraGui.getTablaProveedoresDefault();
        tablaProd = compraGui.getTablaArticulosDefault();
        provlista = busqueda.filtroProveedor("", "", "");
        actualizarListaProveedor();
        
    }
    
     public void tablafacMouseClicked(java.awt.event.MouseEvent evt) {
        if (tablafac.isEditing()) {
        }
    }
   
     public void busquedaProveedorKeyReleased(java.awt.event.KeyEvent evt) {
        provlista = busqueda.filtroProveedor(textcuil.getText(), textnom.getText(), "");
        actualizarListaProveedor();
    }

     public void tablaProvMouseClicked(java.awt.event.MouseEvent evt){
         tablaProd.setRowCount(0);
         int row = tablaprov.getSelectedRow();
         abrirBase();
         prodlista = Producto.where("proveedor_id = ?", tablaProveedores.getValueAt(row, 0));
         actualizarListaProd();
     }
     
     private void actualizarListaProveedor() {
        abrirBase();
        tablaProveedores.setRowCount(0);
        Iterator<Proveedor> it = provlista.iterator();
        while (it.hasNext()) {
            Proveedor a = it.next();
            String row[] = new String[3];
            row[0] = a.getId().toString();
            row[1] = a.getString("nombre");
            row[2] = a.getString("apellido");
            tablaProveedores.addRow(row);
        }
        Base.close();
    }
     
     private void actualizarListaProd() {
        abrirBase();
        tablaProd.setRowCount(0);
        Iterator<Producto> it = prodlista.iterator();
        while (it.hasNext()) {
            Producto a = it.next();
            String row[] = new String[3];
            row[0] = a.getString("numero_producto");
            row[1] = a.getString("nombre");
            row[2] = a.getString("marca");
            tablaProd.addRow(row);
        }
        Base.close();
    }
    
     public void actionPerformed(ActionEvent e) {
        if (e.getSource() == compraGui.getClienteALaFactura()) {//Boton cliente a la factura
            int row = compraGui.getTablaProveedores().getSelectedRow();
            if (row > -1) {
                String id = (String) tablaprov.getValueAt(row, 0);
                String nom = (String) tablaprov.getValueAt(row, 1);
                String ap = (String) tablaprov.getValueAt(row, 2);
                compraGui.getProveedorCompra().setText(id + " " + nom + " " + ap);
            }
        }
        if (e.getSource() == compraGui.getArticulosALaCompra()) {//Boton articulos a la factura
            abrirBase();
            int[] rows = compraGui.getTablaArticulos().getSelectedRows();
            if (rows.length > 0) {
                for (int i = 0; i < rows.length; i++) {
                   // abrirBase();
                    if (!existeProdFacc(Integer.valueOf((String) tablaProd.getValueAt(rows[i], 0)))) {
                        Producto p = Producto.findFirst("numero_producto = ?", (tablaProd.getValueAt(rows[i], 0)));
                        Object cols[] = new Object[5];
                        cols[0] = p.get("numero_producto");
                        cols[1] = 1;
                        cols[2] = p.get("nombre") + " " + p.get("marca");
                        cols[3] = BigDecimal.valueOf(p.getFloat("precio_compra")).setScale(2, RoundingMode.CEILING);
                        cols[4] = BigDecimal.valueOf(p.getFloat("precio_compra")).setScale(2, RoundingMode.CEILING);;
                        Base.close();
                        compraGui.getTablaCompraDefault().addRow(cols);
                        setCellEditor();
                        actualizarPrecio();
                    }else{
                        System.out.println("que hace guacho");
                    }
                }
            }
        }
        
         if (e.getSource() == compraGui.getBorrarArticulosSeleccionados()) {//boton borrar articulos seleccionados
            int[] rows = compraGui.getTablaCompra().getSelectedRows();
            if (rows.length > 0) {
                Integer[] idABorrar = new Integer[rows.length];
                for (int i = 0; i < rows.length; i++) {
                    idABorrar[i] = (Integer) tablafac.getValueAt(rows[i], 0);
                }
                int i = 0;
                int cantABorrar = 0;
                while (cantABorrar < rows.length) {
                    while (i < compraGui.getTablaCompra().getRowCount()) {
                        if ((Integer) compraGui.getTablaCompra().getValueAt(i, 0) == idABorrar[cantABorrar]) {
                            compraGui.getTablaCompraDefault().removeRow(i);
                            cantABorrar++;
                        }
                        i++;
                    }
                    i = 0;
                }
                actualizarPrecio();
            }
        }
         
         if (e.getSource() == compraGui.getRealizarCompra()) {//Boton realizar venta
            if (compraGui.getProveedorCompra().getText().equals("") || compraGui.getCalendarioFacturaText().getText().equals("")) {
                JOptionPane.showMessageDialog(compraGui, "Fecha o proveedor vacios", "Error!", JOptionPane.ERROR_MESSAGE);
            } else {
                Compra v = new Compra();
                LinkedList<Pair> parDeProductos = new LinkedList();
                String laFecha = compraGui.getCalendarioFacturaText().getText(); //saco la fecha
                String cliente = compraGui.getProveedorCompra().getText();
                Integer idCliente = Integer.valueOf(cliente.split(" ")[0]); //saco el id cliente
                for (int i = 0; i < compraGui.getTablaCompra().getRowCount(); i++) {
                    abrirBase();
                    Producto producto = Producto.findFirst("numero_producto = ?", tablafac.getValueAt(i, 0));
                    Base.close();
                    Integer cantidad = (Integer) tablafac.getValueAt(i, 1); //saco la cantidad
                    BigDecimal precioFinal = (BigDecimal) tablafac.getValueAt(i, 3);
                    Pair parCantYPrecioFinal = new Pair(cantidad, precioFinal.doubleValue());
                    Pair par = new Pair(producto, parCantYPrecioFinal); //creo el par
                    parDeProductos.add(par); //meto el par a la lista
                }
                v.set("fecha", laFecha);
                v.set("proveedor_id", idCliente);
                v.setProductos(parDeProductos);
                if (abmCompra.alta(v)) {
                    JOptionPane.showMessageDialog(apgui, "Compra realizada con exito.");
    
                    compraGui.limpiarVentana();

                } else {
                    JOptionPane.showMessageDialog(apgui, "Ocurrió un error inesperado, compra no realizada");
                }
            }
         }
         
          if (e.getSource() == compraGui.getCompraNueva()) {
                compraGui.limpiarVentana();
            }
            if(e.getSource()==compraRealizadaGui.getModificar()){
                compraGui.getProveedorCompra().setText(compraRealizadaGui.getProveedorCompra().getText());
                
            }
        
        
        
        
     }
     
     private void setCellEditor() {
        for (int i = 0; i < tablafac.getRowCount(); i++) {
            tablafac.getCellEditor(i, 1).addCellEditorListener(this);
            tablafac.getCellEditor(i, 3).addCellEditorListener(this);
        }
    }

    private boolean existeProdFacc(int idProd) {
        boolean ret = false;
        for (int i = 0; i < tablafac.getRowCount() && !ret; i++) {
            ret = (Integer) tablafac.getValueAt(i, 0) == idProd;
        }
        return ret;
    }
    
    private void actualizarPrecio() {
        BigDecimal importe;
        BigDecimal total = new BigDecimal(0);
        for (int i = 0; i < tablafac.getRowCount(); i++) {
            importe = BigDecimal.valueOf((Integer) tablafac.getValueAt(i, 1)).multiply((BigDecimal) compraGui.getTablaCompra().getValueAt(i, 3));
            tablafac.setValueAt(importe, i, 4);
        }
        for (int i = 0; i < tablafac.getRowCount(); i++) {
            total = total.add((BigDecimal) tablafac.getValueAt(i, 4));
        }
        compraGui.getTotalCompra().setText(total.toString());
    }
     
     private void abrirBase() {
        if (!Base.hasConnection()) {
            Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/sexshop", "root", "root");
        }
    }

    @Override
    public void editingStopped(ChangeEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void editingCanceled(ChangeEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
