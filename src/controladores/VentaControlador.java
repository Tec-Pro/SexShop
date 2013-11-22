/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import abm.ABMProducto;
import abm.ABMVenta;
import busquedas.busqueda;
import interfaz.AbmProductoGui;
import interfaz.ModificarPrecioPesosGui;
import interfaz.VentaGui;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import modelos.Cliente;
import modelos.Producto;

import modelos.Venta;
import net.sf.jasperreports.engine.util.Pair;
import org.javalite.activejdbc.Base;

/**
 *
 * @author alan
 */
public class VentaControlador implements ActionListener{
    
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
     private JTable tablac;
     private JTable tablap;
     private DefaultTableModel tablaClientes;
     private DefaultTableModel tablaProd;
     private JTable tablafac;
     private DefaultTableModel tablaFactura;
     
     public VentaControlador(VentaGui ventaGui){
        
       //Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/sexshop", "root", "root");
        prodlista = new LinkedList<Producto>();
        clientelista = new LinkedList<Cliente>();
        busqueda = new busqueda();
        abmVenta = new ABMVenta();
        this.ventaGui=ventaGui;
        actionListener(this);
        
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
        tablafac.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablafacMouseClicked(evt);
            }
        });
        
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
        
        tablap = ventaGui.getTablaArticulos();
        tablap.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
            //    tablaProductosMouseClicked(evt);
            }
        });
        
        tablaClientes = ventaGui.getTablaClientesDefault();  
        tablaProd = ventaGui.getTablaArticulosDefault();
        
        clientelista =  busqueda.filtroCliente("","","");
        prodlista =  busqueda.filtroProducto("", "", "");
        actualizarListaCliente();
        actualizarListaProd();
           
    }
     
     public void tablafacMouseClicked(java.awt.event.MouseEvent evt){
         if(tablafac.isEditing()){
             
         }
     }
     
      public void busquedaClienteKeyReleased(java.awt.event.KeyEvent evt){
        
       
        clientelista =  busqueda.filtroCliente(textnom.getText(),textap.getText(), textcodcli.getText());
        actualizarListaCliente();
            
    }

      public void busquedaProductoKeyReleased(java.awt.event.KeyEvent evt){
        
       
        prodlista =  busqueda.filtroProducto(textcodprod.getText(),textnom.getText(), textmarca.getText());
        actualizarListaProd();
      }
      
       private void actualizarListaCliente(){
       if(!Base.hasConnection()){
        Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/sexshop", "root", "root");
        }
        tablaClientes.setRowCount(0);
        Iterator<Cliente> it = clientelista.iterator();
        while(it.hasNext()){
            Cliente a = it.next();
            String row[] = new String[3];
            row[0] = a.getId().toString();
            row[1] = a.getString("nombre");
            row[2] = a.getString("apellido");
            tablaClientes.addRow(row);
        }
        Base.close();
    }
       
       private void actualizarListaProd(){
       if(!Base.hasConnection()){
        Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/sexshop", "root", "root");
        }
        tablaProd.setRowCount(0);
        Iterator<Producto> it = prodlista.iterator();
        while(it.hasNext()){
            Producto a = it.next();
            String row[] = new String[3];
            row[0] = a.getId().toString();
            row[1] = a.getString("nombre");
            row[2] = a.getString("marca");
            tablaProd.addRow(row);
        }
        Base.close();
    }
       
 
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == ventaGui.getClienteALaFactura()){
            int row = tablac.getSelectedRow();
            String id = (String) tablaClientes.getValueAt(row, 0);
            String nom = (String) tablaClientes.getValueAt(row, 1);
            String ap = (String) tablaClientes.getValueAt(row, 2);
            ventaGui.getClienteFactura().setText(id+" "+nom+" "+ap);
        }
        if(e.getSource() == ventaGui.getArticulosALaFactura()){
            int row = tablap.getSelectedRow();
            String nom = (String) tablaProd.getValueAt(row, 1);
            String marc = (String) tablaProd.getValueAt(row, 2);
             if(!Base.hasConnection()){
                  Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/sexshop", "root", "root");
              }
            Producto p = Producto.findById(tablaProd.getValueAt(row, 0));
            Object rows[] = new Object[5];
             rows[0] = p.getInteger("id");
             rows[1] = 1;
             rows[2] = nom+", "+marc;
             rows[3] = p.getFloat("precio_venta");
             rows[4] = p.getFloat("precio_venta");
             Base.close();
            ventaGui.getTablaFacturaDefault().addRow(rows);
        }
        if(e.getSource() == ventaGui.getBorrarArticulosSeleccionados()){
            
            int countrows = ventaGui.getTablaFactura().getSelectedRowCount();
            int rows[] = new int[countrows];
            rows = ventaGui.getTablaFactura().getSelectedRows();
            int i=0;
            while(i<rows.length){
                ventaGui.getTablaFacturaDefault().removeRow(rows[i]);
                i++;
            }
            
        }
        if(e.getSource() == ventaGui.getRealizarVenta()){
            int rows = ventaGui.getTablaFactura().getRowCount();
            int i=0;
            LinkedList<Pair> prods = new LinkedList();
            Pair par;
            while(i<=rows){
                Object id = ventaGui.getTablaFacturaDefault().getValueAt(i, 0);
                if(!Base.hasConnection()){
                     Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/sexshop", "root", "root");
                }
                Producto p = Producto.findById(id);
                Base.close();
                Object cant = ventaGui.getTablaFacturaDefault().getValueAt(i, 1);
                par = new Pair(p,cant);
                prods.addFirst(par);
                i++;
            }
            Venta v = new Venta();
            v.set("fecha", ventaGui.getCalendarioFactura().getDate());
            v.setProductos(prods);
            if(!Base.hasConnection()){
                     Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/sexshop", "root", "root");
                }
         //   Cliente
           // v.set("cliente_id", )
        }
    }
    

    private void actionListener(ActionListener c) {
        ventaGui.getClienteALaFactura().addActionListener(c);
        ventaGui.getArticulosALaFactura().addActionListener(c);
        ventaGui.getBorrarArticulosSeleccionados().addActionListener(c);
    }
    
    
    
}
