/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import abm.ABMVenta;
import busquedas.busqueda;
import interfaz.VentaGui;
import interfaz.VentasRealizadas;
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
import modelos.Producto;
import modelos.Venta;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.util.Pair;
import org.javalite.activejdbc.Base;

/**
 *
 * @author alan
 */
public class VentaControlador implements ActionListener, CellEditorListener {

    private JTextField textap;
    private JTextField textcodcli;
    private JTextField textnom;
    private JTextField textmarca;
    private JTextField textcodprod;
    private List prodlista;
    private List clientelista;
    private busqueda busqueda;
    private ABMVenta abmVenta;
    private VentaGui ventaGui;
    private JTable tablap;
    private DefaultTableModel tablaClientes;
    private DefaultTableModel tablaProd;
    private JTable tablafac;
    private ControladorJReport reporteFactura;
    private Integer idFacturaAModificar;
    private VentasRealizadasControlador ventasControlador;
    
    public VentaControlador(VentaGui ventaGui, VentasRealizadasControlador ventasControlador) throws JRException, ClassNotFoundException, SQLException {

        //Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/sexshop", "root", "root");
        prodlista = new LinkedList<Producto>();
        clientelista = new LinkedList<Cliente>();
        busqueda = new busqueda();
        abmVenta = new ABMVenta();
        this.ventaGui = ventaGui;
        this.ventasControlador= ventasControlador;
        ventaGui.setActionListener(this);
        tablap = ventaGui.getTablaArticulos();

        textap = ventaGui.getBusquedaApellido();
        textap.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyReleased(java.awt.event.KeyEvent evt) {
                busquedaClienteKeyReleased(evt);
            }
        });
        textcodcli = ventaGui.getBusquedaCodigoCliente();
        textcodcli.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyReleased(java.awt.event.KeyEvent evt) {
                busquedaClienteKeyReleased(evt);
            }
        });
        tablafac = ventaGui.getTablaFactura();

        textnom = ventaGui.getBusquedaNombre();
        textnom.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyReleased(java.awt.event.KeyEvent evt) {
                busquedaProductoKeyReleased(evt);
            }
        });

        textcodprod = ventaGui.getBusquedaCodigoArticulo();
        textcodprod.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyReleased(java.awt.event.KeyEvent evt) {
                busquedaProductoKeyReleased(evt);
            }
        });
        textmarca = ventaGui.getBusquedaMarca();
        textmarca.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                busquedaProductoKeyReleased(evt);
            }
        });
        tablaClientes = ventaGui.getTablaClientesDefault();
        tablaProd = ventaGui.getTablaArticulosDefault();
        clientelista = busqueda.filtroCliente("", "", "");
        prodlista = busqueda.filtroProducto("", "", "");
        actualizarListaCliente();
        actualizarListaProd();
        reporteFactura = new ControladorJReport(("factura.jasper"));
    }

    public void busquedaClienteKeyReleased(java.awt.event.KeyEvent evt) {
        clientelista = busqueda.filtroCliente(textnom.getText(), textap.getText(), textcodcli.getText());
        actualizarListaCliente();
    }

    public void busquedaProductoKeyReleased(java.awt.event.KeyEvent evt) {
        prodlista = busqueda.filtroProducto(textcodprod.getText(), textnom.getText(), textmarca.getText());
        actualizarListaProd();
    }

    private void actualizarListaCliente() {
        abrirBase();
        tablaClientes.setRowCount(0);
        Iterator<Cliente> it = clientelista.iterator();
        while (it.hasNext()) {
            Cliente a = it.next();
            String row[] = new String[3];
            row[0] = a.getId().toString();
            row[1] = a.getString("nombre");
            row[2] = a.getString("apellido");
            tablaClientes.addRow(row);
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

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == ventaGui.getClienteALaFactura()) {//Boton cliente a la factura
            int row = ventaGui.getTablaClientes().getSelectedRow();
            if (row > -1) {
                String id = (String) tablaClientes.getValueAt(row, 0);
                String nom = (String) tablaClientes.getValueAt(row, 1);
                String ap = (String) tablaClientes.getValueAt(row, 2);
                ventaGui.getClienteFactura().setText(id + " " + nom + " " + ap);
            }
        }

        if (e.getSource() == ventaGui.getArticulosALaFactura()) {//Boton articulos a la factura
            int[] rows = ventaGui.getTablaArticulos().getSelectedRows();
            if (rows.length > 0) {
                for (int i = 0; i < rows.length; i++) {
                    abrirBase();
                    if (!existeProdFacc(Integer.valueOf((String) tablap.getValueAt(rows[i], 0)))) {
                        Producto p = Producto.findFirst("numero_producto = ?", (tablap.getValueAt(rows[i], 0)));
                        Object cols[] = new Object[5];
                        cols[0] = p.get("numero_producto");
                        cols[1] = 1;
                        cols[2] = p.get("nombre") + " " + p.get("marca");
                        cols[3] = BigDecimal.valueOf(p.getFloat("precio_venta")).setScale(2, RoundingMode.CEILING);
                        cols[4] = BigDecimal.valueOf(p.getFloat("precio_venta")).setScale(2, RoundingMode.CEILING);
                        Base.close();
                        ventaGui.getTablaFacturaDefault().addRow(cols);
                        setCellEditor();
                        actualizarPrecio();
                    }
                }
            }
        }

        if (e.getSource() == ventaGui.getBorrarArticulosSeleccionados()) {//boton borrar articulos seleccionados
            int[] rows = ventaGui.getTablaFactura().getSelectedRows();
            if (rows.length > 0) {
                Integer[] idABorrar = new Integer[rows.length];
                for (int i = 0; i < rows.length; i++) {
                    idABorrar[i] = (Integer) tablafac.getValueAt(rows[i], 0);
                }
                int i = 0;
                int cantABorrar = 0;
                while (cantABorrar < rows.length) {
                    while (i < ventaGui.getTablaFactura().getRowCount()) {
                        if ((Integer) ventaGui.getTablaFactura().getValueAt(i, 0) == idABorrar[cantABorrar]) {
                            ventaGui.getTablaFacturaDefault().removeRow(i);
                            cantABorrar++;
                        }
                        i++;
                    }
                    i = 0;
                }
                actualizarPrecio();
            }
        }

        if (e.getSource() == ventaGui.getRealizarVenta()) {//Boton realizar venta
            if (ventaGui.getClienteFactura().getText().equals("") || ventaGui.getCalenFacturaText().getText().equals("")) {
                JOptionPane.showMessageDialog(ventaGui, "Fecha o cliente vacios", "Error!", JOptionPane.ERROR_MESSAGE);
            } else {
                Venta v = new Venta();
                LinkedList<Pair> parDeProductos = new LinkedList();
                String laFecha = ventaGui.getCalenFacturaText().getText(); //saco la fecha
                String cliente = ventaGui.getClienteFactura().getText();
                Integer idCliente = Integer.valueOf(cliente.split(" ")[0]); //saco el id cliente
                for (int i = 0; i < ventaGui.getTablaFactura().getRowCount(); i++) {
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
                v.set("cliente_id", idCliente);
                v.setProductos(parDeProductos);
                abrirBase();
                if (abmVenta.alta(v)) {
                    if (JOptionPane.showConfirmDialog(ventaGui, "¿Desea abrir el dialogo de impresión?", "¡Venta exitosa!", JOptionPane.YES_NO_OPTION) == 0) {
                        try {
                            reporteFactura.mostrarFactura(abmVenta.getUltimoIdVenta());
                        } catch (ClassNotFoundException ex) {
                            Logger.getLogger(VentaControlador.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (SQLException ex) {
                            Logger.getLogger(VentaControlador.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (JRException ex) {
                            Logger.getLogger(VentaControlador.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    ventaGui.limpiarVentana();
                } else {
                    JOptionPane.showMessageDialog(ventaGui, "Ocurrió un error inesperado, venta no realizada");
                }
            }
            ventasControlador.actualizarListaFacturas();
            Base.close();
        }

        if (e.getSource() == ventaGui.getFacturaNueva()) {
            ventaGui.limpiarVentana();
            ventaGui.getModificar().setEnabled(false);
            ventaGui.getRealizarVenta().setEnabled(true);
        }

        if (e.getSource() == ventaGui.getModificar()) {
            if (ventaGui.getClienteFactura().getText().equals("") || ventaGui.getCalenFacturaText().getText().equals("")) {
                JOptionPane.showMessageDialog(ventaGui, "Fecha o cliente vacios", "Error!", JOptionPane.ERROR_MESSAGE);
            } else {
                Venta v = new Venta();
                LinkedList<Pair> parDeProductos = new LinkedList();
                String laFecha = ventaGui.getCalenFacturaText().getText(); //saco la fecha
                String cliente = ventaGui.getClienteFactura().getText();
                Integer idCliente = Integer.valueOf(cliente.split(" ")[0]); //saco el id cliente
                for (int i = 0; i < ventaGui.getTablaFactura().getRowCount(); i++) {
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
                v.set("cliente_id", idCliente);
                v.setProductos(parDeProductos);
                System.out.print(idFacturaAModificar);
                v.set("id", idFacturaAModificar);
                abrirBase();
                if (abmVenta.modificar(v)) {
                    Base.close();
                    if (JOptionPane.showConfirmDialog(ventaGui, "¿Desea abrir el dialogo de impresión?", "¡Venta modificada!", JOptionPane.YES_NO_OPTION) == 0) {
                        try {
                            reporteFactura.mostrarFactura(idFacturaAModificar);
                        } catch (ClassNotFoundException ex) {
                            Logger.getLogger(VentaControlador.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (SQLException ex) {
                            Logger.getLogger(VentaControlador.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (JRException ex) {
                            Logger.getLogger(VentaControlador.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }

                    ventaGui.limpiarVentana();
                    ventaGui.getModificar().setEnabled(false);
                    ventaGui.getRealizarVenta().setEnabled(true);

                } else {
                    JOptionPane.showMessageDialog(ventaGui, "Ocurrió un error inesperado, venta no realizada");
                }
                
            }
        }

    }

    public void setCellEditor() {
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

    public Integer getIdFacturaAModificar() {
        return idFacturaAModificar;
    }

    @Override
    public void editingStopped(ChangeEvent ce) {
        actualizarPrecio();

    }

    private void actualizarPrecio() {
        BigDecimal importe;
        BigDecimal total = new BigDecimal(0);
        for (int i = 0; i < tablafac.getRowCount(); i++) {
            importe = BigDecimal.valueOf((Integer) tablafac.getValueAt(i, 1)).multiply((BigDecimal) ventaGui.getTablaFactura().getValueAt(i, 3));
            tablafac.setValueAt(importe, i, 4);
        }
        for (int i = 0; i < tablafac.getRowCount(); i++) {
            total = total.add((BigDecimal) tablafac.getValueAt(i, 4));
        }
        ventaGui.getTotalFactura().setText(total.toString());
    }

    @Override
    public void editingCanceled(ChangeEvent ce) {
    }

    private void abrirBase() {
        if (!Base.hasConnection()) {
            Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/sexshop", "root", "root");
        }
    }

    public void setIdFacturaAModificar(Integer idFacturaAModificar) {
        this.idFacturaAModificar = idFacturaAModificar;
    }
    
    
}
