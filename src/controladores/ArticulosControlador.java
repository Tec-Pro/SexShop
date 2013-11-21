/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import abm.*;
import busquedas.busqueda;
import interfaz.*;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import modelos.Cliente;
import modelos.Producto;
import modelos.Proveedor;
import org.javalite.activejdbc.Base;
import org.javalite.activejdbc.LazyList;
import org.javalite.activejdbc.Model;

/**
 *
 * @author alan
 */
public class ArticulosControlador implements ActionListener{
    AbmProductoGui prodGui;
    ABMProducto abmProd;
    AplicacionGui apgui;
    ModificarPrecioPesosGui mpp;
    private boolean nuevoPulsado;
    private boolean modificarPulsado;
    private int confirmarBorrar;
    private JTextField bn;
    private JTextField bc;
    private JTextField bm;
    private DefaultTableModel tablaProductos;
    private JTable tabla;
    List<Producto> pl;
    private busqueda pb;
    
    public ArticulosControlador(AbmProductoGui apg){
        
       //Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/sexshop", "root", "root");
        pl = new LinkedList<Producto>();
        pb = new busqueda();
        abmProd = new ABMProducto();
        this.prodGui=apg;
        nuevoPulsado = false;
        modificarPulsado = false;
        actionListener(this);
        mpp = new ModificarPrecioPesosGui(apgui, true);
        bn = prodGui.getBusquedaNombre();
        bn.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyReleased(java.awt.event.KeyEvent evt) {
                busquedaNombreKeyReleased(evt);
            }
        });
        bc = prodGui.getBusquedaCodigo();
        bc.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyReleased(java.awt.event.KeyEvent evt) {
                busquedaCodigoKeyReleased(evt);
            }
        });
        tabla = prodGui.getTabla();
        tabla.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaMouseClicked(evt);
            }
        });
        
        bm = prodGui.getBusquedaMarca();
        bm.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyReleased(java.awt.event.KeyEvent evt) {
                busquedaMarcaKeyReleased(evt);
            }
        });
        
        tablaProductos = prodGui.getTablaArticulos();  
        pl = pb.filtroProducto("","","");
        actualizarLista();
           
    }
    
    


    
    public void busquedaNombreKeyReleased(java.awt.event.KeyEvent evt){
        
       
        pl = pb.filtroProducto(bc.getText(),bn.getText(), bm.getText());
        actualizarLista();
            
    }
    public void busquedaMarcaKeyReleased(java.awt.event.KeyEvent evt){
        
        pl = pb.filtroProducto(bc.getText(),bn.getText(),bm.getText());
        actualizarLista();
             
    }
     public void busquedaCodigoKeyReleased(java.awt.event.KeyEvent evt){
         
        pl = pb.filtroProducto(bc.getText(),bn.getText(), bm.getText());
        actualizarLista();
              
    }
    
     private void actualizarLista(){
       if(!Base.hasConnection()){
        Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/sexshop", "root", "root");
        }
        tablaProductos.setRowCount(0);
        Iterator<Producto> it = pl.iterator();
        while(it.hasNext()){
            Producto a = it.next();
            String row[] = new String[3];
            row[0] = a.getId().toString();
            row[1] = a.getString("nombre");
            row[2] = a.getString("marca");
            tablaProductos.addRow(row);
        }
        Base.close();
    }
     
     private void agregarFila(Producto c){
        String row[] = new String[3];
        row[0] = c.getId().toString();
        row[1] = c.getString("nombre");
        row[2] = c.getString("marca");
        tablaProductos.addRow(row);  
    }
     
     public void tablaMouseClicked(java.awt.event.MouseEvent evt){
         prodGui.getProveedores().removeAllItems();
        if(!Base.hasConnection()){
        Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/sexshop", "root", "root");
        }
        prodGui.habilitarCampos(false);
        nuevoPulsado = false;
        modificarPulsado = false;
        System.out.println("tabla pulsada");
        int r = tabla.getSelectedRow();
        Producto p = Producto.findById(tabla.getValueAt(r, 0));
        prodGui.CargarCampos(p);
        Proveedor prov = Proveedor.findById(p.getInteger("proveedor_id"));
        String nom = prov.getString("nombre");
        String cuil = prov.getString("cuil");
        String pr = nom+"-"+cuil;
        prodGui.getProveedores().addItem(pr);
        Base.close();
        prodGui.repaint();
    }
     private void cargarDatosProd(Producto c, boolean id){
        c.set("nombre",TratamientoString.eliminarTildes(prodGui.getNombre().getText()).toUpperCase());
        c.set("marca",TratamientoString.eliminarTildes(prodGui.getMarca().getText()).toUpperCase());
        c.set("tipo",TratamientoString.eliminarTildes(prodGui.getTipo().getText()).toUpperCase());
        c.set("precio_compra",TratamientoString.eliminarTildes(prodGui.getPrecioCompra().getText()).toUpperCase());
        c.set("precio_venta",TratamientoString.eliminarTildes(prodGui.getPrecioVenta().getText()).toUpperCase());
        c.set("stock", TratamientoString.eliminarTildes(prodGui.getStock().getText()).toUpperCase());
        c.set("numero_producto", prodGui.getIdArticulo().getText());
        //if(id) c.setId(TratamientoString.eliminarTildes(prodGui.getIdArticulo().getText()).toUpperCase());
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == prodGui.getModificar()){
            modificarPulsado=true;
            nuevoPulsado=false;
            prodGui.habilitarCampos(true);
            prodGui.getProveedores().removeAllItems();
            if(!Base.hasConnection()){
                 Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/sexshop", "root", "root");
             }
            LazyList<Proveedor> l =  Proveedor.findAll();
           // Base.close();
            Iterator<Proveedor> it = l.iterator();
             while(it.hasNext()){
                 Proveedor prov = it.next();
                 String nom = prov.getString("nombre");
                 String cuil = prov.getString("cuil");
                 String prove = nom+"-"+cuil;
                prodGui.getProveedores().addItem(prove);
             }
             Base.close();
             
            //Agregar al combo todos los proveedores!
        }
        if(e.getSource() == prodGui.getNuevo()){
            nuevoPulsado=true;
            prodGui.limpiarCampos();
            prodGui.habilitarCampos(true);
            prodGui.getProveedores().removeAllItems();
            if(!Base.hasConnection()){
                 Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/sexshop", "root", "root");
             }
            LazyList<Proveedor> l =  Proveedor.findAll();
           // Base.close();
            Iterator<Proveedor> it = l.iterator();
             while(it.hasNext()){
                 Proveedor prov = it.next();
                 String nom = prov.getString("nombre");
                 String cuil = prov.getString("cuil");
                 String prove = nom+"-"+cuil;
                prodGui.getProveedores().addItem(prove);
             }
             Base.close();
            //Agregar al combo todos los proveedores
        }
        if(e.getSource() == prodGui.getGuardar() && modificarPulsado){
            String id = prodGui.getIdArticulo().getText();
            if(!Base.hasConnection()){
                    Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/sexshop", "root", "root");
               }
            Producto p = Producto.first("numero_producto = ?", id);
            Base.close();
            p.set("precio_venta", prodGui.getPrecioVenta().getText());
            p.set("precio_compra", prodGui.getPrecioCompra().getText());
            p.set("stock", prodGui.getStock().getText());
        //    p.set("numero_producto", prodGui.getN);
            p.set("nombre", prodGui.getNombre().getText());
            p.set("tipo", prodGui.getTipo().getText());
            p.set("marca", prodGui.getMarca().getText());
            String prov = (String) prodGui.getProveedores().getSelectedItem();
            String[] proveedor = prov.split("-");
            p.setCuilProveedor(proveedor[1]);
            if(abmProd.modificar(p)){
                JOptionPane.showMessageDialog(apgui, "Producto modificado exitosamente.");
                pl = pb.filtroProducto("","","");
                actualizarLista();
                prodGui.repaint();
            }   
            else{
                JOptionPane.showMessageDialog(prodGui,"No hay ningun producto seleccionado");
            }
            modificarPulsado=false;
            prodGui.habilitarCampos(false);
            
        }
        if(e.getSource() == prodGui.getGuardar() && nuevoPulsado){
            System.out.println("pulsado guardar crear");
            Producto p = new Producto();
            cargarDatosProd(p,false);
          //  p.set("numero_producto", Integer.valueOf(prodGui.getIdArticulo().getText()));
            //p.setCuilProveedor("33333");
            String prov = (String) prodGui.getProveedores().getSelectedItem();
            String[] proveedor = prov.split("-");
            p.setCuilProveedor(proveedor[1]);
            System.out.println (proveedor[0]);
            System.out.println (proveedor[1]);
            if(p.getString("nombre").equals("") || p.getString("marca").equals("")){
                JOptionPane.showMessageDialog(prodGui,"Un producto debe tener nombre y marca");
            }
            
            if(abmProd.alta(p)){
                
                JOptionPane.showMessageDialog(prodGui,"Producto registrado exitosamente");
                p = abmProd.getProducto(p);
                agregarFila(p);
            }    
            else{
                JOptionPane.showMessageDialog(prodGui,"producto ya existente");  
            }
            nuevoPulsado = false;
            prodGui.habilitarCampos(false);
           
          
        }
        
        
        if(e.getSource() == prodGui.getBorrar()){
            confirmarBorrar = JOptionPane.showConfirmDialog(prodGui,"¿borrar producto?","Confirmar Borrado",JOptionPane.YES_NO_OPTION);
            if (JOptionPane.OK_OPTION == confirmarBorrar){
                 System.out.println("confirmado");
                 if(!Base.hasConnection()){
                    Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/sexshop", "root", "root");
               }
                 Producto p = Producto.first("numero_producto = ?" ,prodGui.getIdArticulo().getText());
                 //cargarDatosProd(p,true);
                 //int row = tabla.getSelectedRow();
                 Base.close();
                // p.set("numero_producto",prodGui.getIdArticulo().getText() );
                 if(abmProd.baja(p)){
                     JOptionPane.showMessageDialog(prodGui,"Producto borrado exitosamente");
                     pl = pb.filtroProducto("","","");
                     actualizarLista();
                     prodGui.limpiarCampos();
                 }
                 else
                     JOptionPane.showMessageDialog(prodGui,"No hay ningun producto seleccionado");
            }
        }
        if(e.getSource() == prodGui.getAnterior()){
            prodGui.getProveedores().removeAllItems();
            int r = tabla.getSelectedRow();
            if(r>0){
                tabla.changeSelection(tabla.getSelectedRow()-1,0, false, false);
                r--;
                if(!Base.hasConnection()){
                    Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/sexshop", "root", "root");
               }
                Producto c = Producto.findById(tabla.getValueAt(r, 0));
                Proveedor p = Proveedor.first("id = ?", c.getString("proveedor_id"));
                Base.close();
                
                prodGui.CargarCampos(c);
                String nom = p.getString("nombre");
                String cuil = p.getString("cuil");
                String pr = nom+"-"+cuil;
                prodGui.getProveedores().addItem(pr);
                prodGui.repaint();
                
            }
        }
        
        if(e.getSource()== prodGui.getSiguiente()){ //permite avanzar al siguiente cliente de la lista
            prodGui.getProveedores().removeAllItems();
            int r = tabla.getSelectedRow();
            if(tablaProductos.getRowCount()-1>r){
                tabla.changeSelection(r+1,0, false, false);
                r++;
                if(!Base.hasConnection()){
                    Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/sexshop", "root", "root");
               }
                Producto c = Producto.findById(tabla.getValueAt(r, 0));
                Proveedor p = Proveedor.first("id = ?", c.getString("proveedor_id"));
                Base.close();
                prodGui.CargarCampos(c);
                String nom = p.getString("nombre");
                String cuil = p.getString("cuil");
                String pr = nom+"-"+cuil;
                prodGui.getProveedores().addItem(pr);
                prodGui.repaint();
            }
        }
        
        if(e.getSource() == prodGui.getModificarPrecioPesos()){
            mpp = new ModificarPrecioPesosGui(apgui, true);
            mpp.setVisible(true);
            
        }
        /*if(e.getSource() == mpp.getAceptar()){
            int srow = prodGui.getTabla().getSelectedRow();
            Object id = prodGui.getTablaArticulos().getValueAt(srow, 0);
            Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/sexshop", "root", "root");
            Producto p = Producto.first("id = ?", id);
            p.set("precio_venta", mpp.getPesos().getText());
            p.saveIt();
            Base.close();
            mpp.dispose();
            JOptionPane.showMessageDialog(apgui, "Precio modificado exitosamente.");
            
        }
        if(e.getSource() == mpp.getCancelar()){
            mpp.dispose();
        }
        */
        if(e.getSource()== prodGui.getModificarPrecioPorcentaje()){
            
        }
    }

    private void actionListener(ActionListener c) {
        prodGui.getGuardar().addActionListener(c);
        prodGui.getAnterior().addActionListener(c);
        prodGui.getSiguiente().addActionListener(c);
        prodGui.getBorrar().addActionListener(c);
        prodGui.getNuevo().addActionListener(c);
        prodGui.getModificar().addActionListener(c);
        prodGui.getModificarPrecioPesos().addActionListener(c);
        prodGui.getModificarPrecioPorcentaje().addActionListener(c);
//        mpp.getAceptar().addActionListener(c);
  //      mpp.getCancelar().addActionListener(c);
    }
    
}