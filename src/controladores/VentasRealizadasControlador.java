/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import abm.ABMVenta;
import busquedas.busqueda;
import com.toedter.calendar.JDateChooser;
import interfaz.AplicacionGui;
import interfaz.VentasRealizadas;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Calendar;
import java.sql.Date;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import modelos.Cliente;
import modelos.Producto;
import modelos.ProductosVentas;
import modelos.Venta;
import org.javalite.activejdbc.Base;

/**
 *
 * @author agustin
 */
public class VentasRealizadasControlador implements ActionListener {
    
    
    private JTextField filtNomb;
    private JTextField filtApe;
    private JTextField filtId;
    private AplicacionGui apliGui;
    private VentasRealizadas ventasGui;
    private List<Cliente> cl;
    private List<Venta> vl;
    private busqueda buscar;
    private JDateChooser calenDesde;
    private JDateChooser calenHasta;
    private JTable tablaFacturas;
    private DefaultTableModel facturasDefault;
    private DefaultTableModel factDefault;
    private JTable tablaFact;
    private String dateDesde;
    private String dateHasta;
    private List<ProductosVentas> prodVentas;
    private ABMVenta abmventa;
    
    
    public VentasRealizadasControlador(AplicacionGui app){
        abrirBase();
        apliGui = app;
        ventasGui = apliGui.getVentasRealizadas();
        ventasGui.setActionListener(this);
        filtNomb = ventasGui.getFiltroNombre();
        filtApe = ventasGui.getFiltroApellido();
        filtId = ventasGui.getFiltroId();
        calenDesde = ventasGui.getCalendarioDesde();
        calenHasta = ventasGui.getCalendarioHasta();
        cl = new LinkedList<Cliente>();
        vl = new LinkedList<Venta>();
        tablaFacturas = ventasGui.getTablaFacturas();
        facturasDefault = ventasGui.getTablaFacturasDefault();
        dateDesde = "0-0-0";
        dateHasta = "9999-0-0";
        buscar = new busqueda();
        cl = buscar.filtroCliente("","","");
        factDefault = ventasGui.getTablaFacturaDefault();
        tablaFact = ventasGui.getTablaFactura();
        prodVentas =  new LinkedList<ProductosVentas>();
        abmventa = new ABMVenta();
        
        Base.close();
        tablaFacturas.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tablaFacturasMouseReleased(evt);
            }
        });
        
        
        filtNomb.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyReleased(java.awt.event.KeyEvent evt) {
                filtroNombreKeyReleased(evt);
            }
        });
        filtApe.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyReleased(java.awt.event.KeyEvent evt) {
                filtroApellidoKeyReleased(evt);
            }
        });
        filtId.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyReleased(java.awt.event.KeyEvent evt) {
                filtroIdKeyReleased(evt);
            }
        });
        calenDesde.getJCalendar().addPropertyChangeListener("calendar", new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent e) {
                calenDesdePropertyChange(e);
            }
        });
        calenHasta.getJCalendar().addPropertyChangeListener("calendar", new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent e) {
                calenHastaPropertyChange(e);
            }
        });
        
        actualizarListaFacturas();
        
    }
    
    private void abrirBase(){
        if (!Base.hasConnection()){
            Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/sexshop","root", "root");
        }
    }
    
    private void actualizarListaFacturas(){
         facturasDefault.setRowCount(0);
         abrirBase();
         for (Cliente c : cl){
            vl = buscar.filtroVenta(c.getId().toString(),dateDesde,dateHasta);

            for (Venta v : vl) {
                String row[] = new String[3];
                row[0] = v.getId().toString();
                row[1] = c.get("nombre") + " " +c.get("apellido");
                row[2] = v.get("fecha").toString();
                facturasDefault.addRow(row);
            }
        }
        Base.close();
         
    }
    
    private void actualizarFactura(){
        factDefault.setRowCount(0);
        for(ProductosVentas pv: prodVentas){
            Object row[] = new Object[4];
            row[0] = Integer.parseInt(pv.get("cantidad").toString());
            Producto p = Producto.findFirst("numero_producto = ?",pv.get("producto_id"));
            row[1] = p.get("nombre") +", "+ p.get("marca");
            Float a = Float.parseFloat(pv.get("precio_final").toString());
            row[2] = a;
            row[3] = Integer.parseInt(pv.get("cantidad").toString()) * a; 
            factDefault.addRow(row);
        }
        setTotal();
    }
    
    private void limpiarFactura(){
        factDefault.setRowCount(0);
        ventasGui.getClienteFactura().setText("");
        ventasGui.getCalendarioFactura().setDate(Date.valueOf("0000-1-1"));
    }
    
    public void calenDesdePropertyChange(PropertyChangeEvent e){
        final Calendar c = (Calendar) e.getNewValue();
        dateDesde = c.get(Calendar.YEAR)+"-" +c.get(Calendar.MONTH)+"-"+c.get(Calendar.DATE);  
        actualizarListaFacturas();
    }
    
    public void calenHastaPropertyChange(PropertyChangeEvent e){
        final Calendar c = (Calendar) e.getNewValue();
        dateHasta = c.get(Calendar.YEAR)+"-" +c.get(Calendar.MONTH)+"-"+c.get(Calendar.DATE);
        actualizarListaFacturas();
    }
    
    public void filtroNombreKeyReleased(java.awt.event.KeyEvent evt){   
        abrirBase();
        cl = buscar.filtroCliente(filtNomb.getText(),filtApe.getText(),filtId.getText());
        Base.close();
        actualizarListaFacturas();
    }
    
    public void filtroApellidoKeyReleased(java.awt.event.KeyEvent evt){
        cl = buscar.filtroCliente(filtNomb.getText(),filtApe.getText(),filtId.getText());
        actualizarListaFacturas();
    }
    
    public void filtroIdKeyReleased(java.awt.event.KeyEvent evt){
        abrirBase();
        cl = buscar.filtroCliente(filtNomb.getText(),filtApe.getText(),filtId.getText());
        Base.close();
        actualizarListaFacturas();
    }
 
    public void tablaFacturasMouseReleased(java.awt.event.MouseEvent evt){
        abrirBase();
        int r = tablaFacturas.getSelectedRow();
        Cliente c = buscar.buscarCliente(tablaFacturas.getValueAt(r, 0));
        ventasGui.getClienteFactura().setText(tablaFacturas.getValueAt(r, 1).toString());
        ventasGui.getCalendarioFactura().setDate(Date.valueOf(tablaFacturas.getValueAt(r, 2).toString()));
        if(r>-1){
            prodVentas = buscar.filtroVendidos(tablaFacturas.getValueAt(r, 0).toString(),"");

        }
        actualizarFactura();
        Base.close();
        
    }
    
    private void setTotal(){
        Float total = Float.parseFloat("0.0");
        
        for(int i=0;i<tablaFact.getRowCount();i++){
            total += Float.parseFloat(factDefault.getValueAt(i, 3).toString());
        }
        ventasGui.getTotalFactura().setText(total.toString());
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        abrirBase();
        JButton b = (JButton)e.getSource();
        if(b.equals(ventasGui.getModificar())){
            
        }
        if(b.equals(ventasGui.getEliminar())){
            int r = tablaFacturas.getSelectedRow();
            if(r<0){
                JOptionPane.showMessageDialog(ventasGui,"No hay ninguna factura seleccionada");
                return;
            }
            int confirmarBorrar = JOptionPane.showConfirmDialog(ventasGui,"¿borrar factura?","Confirmar Borrado",JOptionPane.YES_NO_OPTION);
            if (JOptionPane.OK_OPTION == confirmarBorrar){
                Venta v = new Venta();
                v.setId(facturasDefault.getValueAt(r, 0));
                abmventa.baja(v);
                actualizarListaFacturas();  
                limpiarFactura();
                JOptionPane.showMessageDialog(ventasGui,"Factura borrada exitosamente");
            }  
        }
        if(b.equals(ventasGui.getDevolucion())){
            int r = tablaFacturas.getSelectedRow();
            if(r<0){
                JOptionPane.showMessageDialog(ventasGui,"No hay ninguna factura seleccionada");
                return;
            }
            int confirmarBorrar = JOptionPane.showConfirmDialog(ventasGui,"¿cancelar factura?","Confirmar Borrado",JOptionPane.YES_NO_OPTION);
            if (JOptionPane.OK_OPTION == confirmarBorrar){
                Venta v = new Venta();
                v.setId(facturasDefault.getValueAt(r, 0));
                if(abmventa.bajaConDevolucion(v)){
                    JOptionPane.showMessageDialog(ventasGui,"Factura cancelada exitosamente");
                    limpiarFactura();
                    actualizarListaFacturas();
                }
                else{
                    JOptionPane.showMessageDialog(ventasGui,"Error al cancelar factura");
                }
            }           
        }
        if(b.equals(ventasGui.getImprimir())){
            
        }
        if(Base.hasConnection())
            Base.close();
    }
    
    
}
