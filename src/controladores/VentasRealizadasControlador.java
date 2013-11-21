/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

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
    private List<ProductosVentas> prodVendidos;
    private JTextField calenDesdeText;
    private JTextField calenHastaText;
    
    public VentasRealizadasControlador(AplicacionGui app){
        
        apliGui = app;
        ventasGui = apliGui.getVentasRealizadas();
        filtNomb = ventasGui.getFiltroNombre();
        filtApe = ventasGui.getFiltroApellido();
        filtId = ventasGui.getFiltroId();
        calenDesde = ventasGui.getCalendarioDesde();
        calenHasta = ventasGui.getCalendarioHasta();
        calenDesdeText= ventasGui.getCalenDesdeText();
        calenHastaText= ventasGui.getCalenHastaText();
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
        prodVendidos =  new LinkedList<ProductosVentas>();
        
        tablaFacturas.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tablaFacturasMouseReleased(evt);
            }
        });
        calenDesdeText.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyReleased(java.awt.event.KeyEvent evt) {
                if(calenDesdeText.getText().equals("")) dateDesde="0-0-0";
                else  dateDesde =calenDesdeText.getText();   
                actualizarListaFacturas();
            }
        });
        
        calenHastaText.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyReleased(java.awt.event.KeyEvent evt) {
                if(calenHastaText.getText().equals("")) dateHasta="9999-0-0";
                else  dateHasta =calenHastaText.getText();  
                actualizarListaFacturas();
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
    
    private void actualizarListaFacturas(){
         facturasDefault.setRowCount(0);
         buscar.abrirBase();
         for (Cliente c : cl){
            //Base.close();
            vl = buscar.filtroVenta(c.getId().toString(),dateDesde,dateHasta);
            
           //buscar.abrirBase();
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
        buscar.abrirBase();
        factDefault.setRowCount(0);
        for(ProductosVentas pv: prodVendidos){
            
            Object row[] = new Object[4];
            row[0] = Integer.parseInt(pv.get("cantidad").toString());
            Producto p = Producto.findById(pv.get("idproducto"));
            row[1] = p.get("nombre") +", "+ p.get("marca");
            Float a = Float.parseFloat(pv.get("precio_final").toString());
            row[2] = a;
            row[3] = Integer.parseInt(pv.get("cantidad").toString()) * a; 
            factDefault.addRow(row);
        }
        Base.close();
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
        cl = buscar.filtroCliente(filtNomb.getText(),filtApe.getText(),filtId.getText());
        actualizarListaFacturas();
    }
    
    public void filtroApellidoKeyReleased(java.awt.event.KeyEvent evt){
        cl = buscar.filtroCliente(filtNomb.getText(),filtApe.getText(),filtId.getText());
        actualizarListaFacturas();
    }
    
    public void filtroIdKeyReleased(java.awt.event.KeyEvent evt){
        cl = buscar.filtroCliente(filtApe.getText(),filtApe.getText(),filtId.getText());
        actualizarListaFacturas();
    }
 
    public void tablaFacturasMouseReleased(java.awt.event.MouseEvent evt){
        int r = tablaFacturas.getSelectedRow();
        Cliente c = buscar.buscarCliente(tablaFacturas.getValueAt(r, 0));
        ventasGui.getClienteFactura().setText(tablaFacturas.getValueAt(r, 1).toString());
        ventasGui.getCalendarioFactura().setDate(Date.valueOf(tablaFacturas.getValueAt(r, 2).toString()));
        if(c!=null){
            prodVendidos = buscar.filtroVendidos(c.getId().toString(),"");
        }
        actualizarFactura();
       
        
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        JButton b = (JButton)e.getSource();
        if(b.equals(ventasGui.getModificar())){
            
        }
       
    }
    
    
}
