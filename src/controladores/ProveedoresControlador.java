/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import abm.ABMProveedor;
import busquedas.busqueda;
import interfaz.AbmProveedorGui;
import interfaz.AplicacionGui;
import interfaz.ArticulosCompradosGui;
import interfaz.ArticulosDeProveedores;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import modelos.Cliente;
import modelos.Producto;
import modelos.Proveedor;
import org.javalite.activejdbc.Base;

/**
 *
 * @author agustin
 */
public class ProveedoresControlador implements ActionListener {
  
    private AplicacionGui apliGui;
    private busqueda buscar;
    private AbmProveedorGui provGui;
    private ABMProveedor abmProv;
    private JTextField buscarId;
    private JTextField buscarNombre;
    private JTextField buscarCuil;
    private List<Proveedor> provList;
    private DefaultTableModel modelProv;
    private JTable tablaProv;
    private boolean nuevoPulsado;
    private boolean modificarPulsado;
    
    public ProveedoresControlador(AplicacionGui app){
        abrirBase();
        apliGui = app;
        provList = new LinkedList<Proveedor>();
        buscar = new busqueda();
        provGui = apliGui.getAbmProveedorGui();
        provGui.setActionListener(this);
        nuevoPulsado = false;
        modificarPulsado = false;
        buscarCuil = provGui.getBusquedaCuil();
        buscarNombre = provGui.getBusquedaNombre();
        buscarId = provGui.getBusquedaId();
        modelProv = provGui.getTablaProveedores();
        tablaProv = provGui.getTablaProv();
        abmProv = new ABMProveedor();
        provList = buscar.filtroProveedor("","","");
        provGui.habilitarCampos(false,false);
        provGui.limpiarCampos();
        buscarCuil.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyReleased(java.awt.event.KeyEvent evt) {
                busquedaCuilKeyReleased(evt);
            }
        });
        buscarNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyReleased(java.awt.event.KeyEvent evt) {
                busquedaNombreKeyReleased(evt);
            }
        });
        buscarId.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyReleased(java.awt.event.KeyEvent evt) {
                busquedaIdKeyReleased(evt);
            }
        });
        tablaProv.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tablaProvMouseReleased(evt);
            }
        });
        actualizarLista();
        Base.close();
    }
    
    
    private void abrirBase(){
        if (!Base.hasConnection()){
            Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/sexshop","root", "root");
        }
    }
    
    private void actualizarLista(){
        modelProv.setRowCount(0);
        Iterator<Proveedor> it = provList.iterator();
        while(it.hasNext()){
            Proveedor p = it.next();
            String row[] = new String[3];
            row[0] = p.getId().toString();
            row[1] = p.getString("cuil");
            row[2] = p.getString("nombre");
            modelProv.addRow(row);
        }
    }
    
    private void agregarFila(Proveedor c){
        String row[] = new String[3];
        row[0] = c.getId().toString();
        row[1] = c.getString("cuil");
        row[2] = c.getString("nombre");
        modelProv.addRow(row);  
    }
    
    private boolean cargarDatosProv(Proveedor p, boolean id){
        boolean result = true;
        p.set("apellido",TratamientoString.eliminarTildes(provGui.getApellido().getText()).toUpperCase());
        p.set("nombre",TratamientoString.eliminarTildes(provGui.getNombre().getText()).toUpperCase());
        p.set("celular",TratamientoString.eliminarTildes(provGui.getCelular().getText()).toUpperCase());
        p.set("telefono",TratamientoString.eliminarTildes(provGui.getTelefono().getText()).toUpperCase());
        p.set("mail",TratamientoString.eliminarTildes(provGui.getEmail().getText()).toUpperCase());
        p.set("nombre_banco",TratamientoString.eliminarTildes(provGui.getBanco().getText()).toUpperCase());
        p.set("cuil",TratamientoString.eliminarTildes(provGui.getCuil().getText()).toUpperCase());
        p.set("sucursal",TratamientoString.eliminarTildes(provGui.getSucursal().getText()).toUpperCase());
        p.set("tipo_cuenta",TratamientoString.eliminarTildes(provGui.getTipoCuenta().getText()).toUpperCase());
        p.set("dni",TratamientoString.eliminarTildes(provGui.getDni().getText()).toUpperCase());
        if(!provGui.getCompraMinima().getText().equals("")){
            try{p.set("compra_minima",Integer.parseInt(provGui.getCompraMinima().getText()));}
            catch(NumberFormatException num){
                result = false;
                JOptionPane.showMessageDialog(provGui,"La compra minima debe ser un numero entero");
            }
        }
        else{p.set("compra_minima",null);} 
        if(!provGui.getNumCuenta().getText().equals("")){
            try{p.set("cuenta",Integer.parseInt(provGui.getNumCuenta().getText()));}
            catch(NumberFormatException num){
                result = false;
                JOptionPane.showMessageDialog(provGui,"El numero de cuenta debe ser un numero entero");
            }
        }
        else{p.set("cuenta",null);} 
        return result;
       // if(id) p.setId(TratamientoString.eliminarTildes(provGui.get).getText()).toUpperCase());
    }
    
    public void busquedaCuilKeyReleased(java.awt.event.KeyEvent evt){
        abrirBase();
        provList = buscar.filtroProveedor(buscarCuil.getText(),buscarNombre.getText(),buscarId.getText());
        actualizarLista();
        Base.close();
    }
    
    public void busquedaNombreKeyReleased(java.awt.event.KeyEvent evt){
        abrirBase();
        provList = buscar.filtroProveedor(buscarCuil.getText(),buscarNombre.getText(),buscarId.getText());
        actualizarLista();;
        Base.close();
    }
    
    public void busquedaIdKeyReleased(java.awt.event.KeyEvent evt){
        abrirBase();
        provList = buscar.filtroProveedor(buscarCuil.getText(),buscarNombre.getText(),buscarId.getText());
        actualizarLista();
        Base.close();
    }
    
    public void tablaProvMouseReleased(java.awt.event.MouseEvent evt){
        abrirBase();
        provGui.habilitarCampos(false,false);
        nuevoPulsado = false;
        modificarPulsado = false;
        int r = tablaProv.getSelectedRow();
        Proveedor p = Proveedor.findById(tablaProv.getValueAt(r, 0));
        provGui.CargarCampos(p);
        Base.close();
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        abrirBase();
        JButton b = (JButton)e.getSource();
        if(b.equals(provGui.getArticulos())){
            ArticulosDeProveedores art = new ArticulosDeProveedores(apliGui ,true);
            List<Producto> articulos = buscar.provee(provGui.getCuil().getText());
            Iterator it = articulos.iterator();
            String row[] = new String[4];
            while(it.hasNext()){
                Producto p = (Producto)it.next();
                row[0] = p.get("id").toString();
                row[1] = p.get("nombre").toString();
                row[2] = p.get("marca").toString();
                row[3] = p.get("tipo").toString();
                art.getTablaArticulosProveedor().addRow(row);
            }
            art.setVisible(true);
        }
        if(b.equals(provGui.getSiguiente())){ //permite avanzar al siguiente proveedor de la lista
            int r = tablaProv.getSelectedRow();
            if(modelProv.getRowCount()-1>r){
                tablaProv.changeSelection(r+1,0, false, false);
                r++;
                Proveedor p = Proveedor.findById(tablaProv.getValueAt(r, 0));
                provGui.CargarCampos(p);
            }
        }
        if(b.equals(provGui.getAnterior())){////permite retroceder al proveedor anterior de la lista
            int r = tablaProv.getSelectedRow();
            if(r>0){
                tablaProv.changeSelection(tablaProv.getSelectedRow()-1,0, false, false);
                r--;
                Proveedor p = Proveedor.findById(tablaProv.getValueAt(r, 0));
                provGui.CargarCampos(p);
            }
        }
        if(b.equals(provGui.getNuevo())){ //permite crear un nuevo proveedor
            System.out.println("nuevo pulsado");
            provGui.limpiarCampos();
            provGui.habilitarCampos(true,true);
            nuevoPulsado = true;
            modificarPulsado = false;
        }
        if(b.equals(provGui.getGuardar()) && nuevoPulsado){//intenta guardar el nuevo prov creado
            System.out.println("pulsado guardar crear");
            Proveedor c = new Proveedor();
            if(!cargarDatosProv(c,false)){
                Base.close();
                return;
            }
            if(c.getString("nombre").equals("") || c.getString("cuil").equals("")){
                JOptionPane.showMessageDialog(provGui,"Un Proveedor debe tener nombre y cuil");
                Base.close();
                return;
            }
            if(abmProv.alta(c)){
                
                JOptionPane.showMessageDialog(provGui,"Proveedor registrado exitosamente");
                c = abmProv.getProveedor(c);
                agregarFila(c);
            }    
            else
                JOptionPane.showMessageDialog(provGui,"Proveedor ya existente");  
            provGui.limpiarCampos();
            nuevoPulsado = false;
            provGui.habilitarCampos(false,false);

        }
        if(b.equals(provGui.getModificar())){  //permite modificar un proveedor existente
            System.out.println("modificar pulsado");
            provGui.habilitarCampos(true,false);  
            modificarPulsado = true;
            nuevoPulsado = false;
        }
        if(b.equals(provGui.getGuardar()) && modificarPulsado){  //intenta guardar la modificacion de un prov
            System.out.println("pulsado guardar modificar");
            Proveedor prov = new Proveedor();
            cargarDatosProv(prov,true);
            if(abmProv.modificar(prov)){
                JOptionPane.showMessageDialog(provGui,"Modificacion realizada con exito");
                provList = buscar.filtroProveedor("","","");
                actualizarLista();
            }   
            else{
                JOptionPane.showMessageDialog(provGui,"No hay ningun proveedor seleccionado");
            }
            provGui.limpiarCampos();
            modificarPulsado = false;
            provGui.habilitarCampos(false,false);
        }
        if(b.equals(provGui.getBorrar())){  //intenta borrar un proveedor seleccionado
            int confirmarBorrar = JOptionPane.showConfirmDialog(provGui,"¿borrar proveedor?","Confirmar Borrado",JOptionPane.YES_NO_OPTION);
            if (JOptionPane.OK_OPTION == confirmarBorrar){
                 System.out.println("confirmado");
                 Proveedor prov = new Proveedor();
                 cargarDatosProv(prov,true);
                 prov = Proveedor.findFirst("cuil = ?", prov.get("cuil"));
                 if(abmProv.baja(prov)){
                     JOptionPane.showMessageDialog(provGui,"Proveedor borrado exitosamente");
                     provList = buscar.filtroProveedor("","","");
                     actualizarLista();
                     provGui.limpiarCampos();
                 }
                 else
                     JOptionPane.showMessageDialog(provGui,"No hay ningun proveedor seleccionado");
            }     
        }
        Base.close();
        
    }
}
