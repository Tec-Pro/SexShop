/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import busquedas.busqueda;
import abm.ABMCliente;
import interfaz.AbmClienteGui;
import interfaz.AplicacionGui;
import interfaz.ArticulosCompradosGui;
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
import modelos.ClientesProductos;
import modelos.Producto;
import org.javalite.activejdbc.Base;
/**
 *
 * @author agustin
 */
public class ClienteControlador implements ActionListener {
    
    private AplicacionGui apliGui;
    private busqueda cb;
    private AbmClienteGui clienteGui;
    private ABMCliente abmCliente;
    private boolean nuevoPulsado;
    private boolean modificarPulsado;
    private int confirmarBorrar;
    private JTextField ba;
    private JTextField bc;
    private DefaultTableModel tablaClientes;
    private JTable tabla;
    private ArticulosCompradosGui artCom;
    private List prodComprados;
    
    List<Cliente> cl;

    public ClienteControlador(AplicacionGui apg){
        abrirBase();
        apliGui = apg;
        cl = new LinkedList<Cliente>();
        cb = new busqueda();
        clienteGui = apg.getAbmClienteGui();
        abmCliente = new ABMCliente();
        clienteGui.setActionListener(this);
        nuevoPulsado = false;
        modificarPulsado = false;
        ba = clienteGui.getBusquedaApellido();
        ba.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyReleased(java.awt.event.KeyEvent evt) {
                busquedaApellidoKeyReleased(evt);
            }
        });
        bc = clienteGui.getBusquedaCodigo();
        bc.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyReleased(java.awt.event.KeyEvent evt) {
                busquedaCodigoKeyReleased(evt);
            }
        });
        tabla = clienteGui.getTabla();
        tabla.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tablaMouseClicked(evt);
            }
        });
        tablaClientes = clienteGui.getTablaClientes();  
        cl = cb.filtroCliente("","","");
        actualizarLista();
        Base.close();

    }
    
    private void abrirBase(){
        if (!Base.hasConnection()){
            Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/sexshop","root", "root");
        }
    }
    
    private void actualizarLista(){

        tablaClientes.setRowCount(0);
        Iterator<Cliente> it = cl.iterator();
        while(it.hasNext()){
            Cliente a = it.next();
            String row[] = new String[3];
            row[0] = a.getId().toString();
            row[2] = a.getString("nombre");
            row[1] = a.getString("apellido");
            tablaClientes.addRow(row);
        }

    }
    
    private void agregarFila(Cliente c){
        String row[] = new String[3];
        row[0] = c.getId().toString();
        row[2] = c.getString("nombre");
        row[1] = c.getString("apellido");
        tablaClientes.addRow(row);  
    }
    
    /*setea un cliente con los datos de los campos*/
    private void cargarDatosCliente(Cliente c, boolean id){
        c.set("apellido",TratamientoString.eliminarTildes(clienteGui.getApellido().getText()).toUpperCase());
        c.set("nombre",TratamientoString.eliminarTildes(clienteGui.getNombre().getText()).toUpperCase());
        c.set("celular",TratamientoString.eliminarTildes(clienteGui.getCelular().getText()).toUpperCase());
        c.set("telefono",TratamientoString.eliminarTildes(clienteGui.getTelFijo().getText()).toUpperCase());
        c.set("mail",TratamientoString.eliminarTildes(clienteGui.getEmail().getText()).toUpperCase());
        if(id) c.setId(TratamientoString.eliminarTildes(clienteGui.getIdCliente().getText()).toUpperCase());
    }
    
    public void busquedaApellidoKeyReleased(java.awt.event.KeyEvent evt){
        abrirBase();
        cl = cb.filtroCliente("",ba.getText(),bc.getText());
        actualizarLista();
        Base.close();
    }
    
    public void busquedaCodigoKeyReleased(java.awt.event.KeyEvent evt){
        abrirBase();
        cl = cb.filtroCliente("",ba.getText(),bc.getText());
        actualizarLista();
        Base.close();
    }
    
    
    public void tablaMouseClicked(java.awt.event.MouseEvent evt){
        abrirBase();
        clienteGui.habilitarCampos(false);
        nuevoPulsado = false;
        modificarPulsado = false;
        int r = tabla.getSelectedRow();
        Cliente c = cb.buscarCliente(tabla.getValueAt(r, 0));
        clienteGui.CargarCampos(c);
        Base.close();
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        abrirBase();
        JButton b = (JButton)e.getSource();
        if(b.equals(clienteGui.getArticulosComprados())){
            if(nuevoPulsado){
                Base.close();
                return;
            }
            artCom = new ArticulosCompradosGui(apliGui ,true);
            prodComprados = cb.filtroAdquiridos(clienteGui.getIdCliente().getText(),"");
            Iterator it = prodComprados.iterator();
            String row[] = new String[5];
            while(it.hasNext()){     
                ClientesProductos cp = (ClientesProductos)it.next();
                Producto p = Producto.findById(cp.get("producto_id"));
                row[0] = p.get("numero_producto").toString();
                row[1] = p.get("nombre").toString();
                row[2] = p.get("marca").toString();
                row[3] = p.get("tipo").toString();
                row[4] = cp.get("cantidad").toString();
                artCom.getTablaProductosComprados().addRow(row);
            }
            artCom.setVisible(true);
        }
        if(b.equals(clienteGui.getSiguiente())){ //permite avanzar al siguiente cliente de la lista
            int r = tabla.getSelectedRow();
            if(tablaClientes.getRowCount()-1>r){
                tabla.changeSelection(r+1,0, false, false);
                r++;
                Cliente c = cb.buscarCliente(tabla.getValueAt(r, 0));
                clienteGui.CargarCampos(c);
            }
        }
        if(b.equals(clienteGui.getAnterior())){////permite retroceder al cliente anterior de la lista
            int r = tabla.getSelectedRow();
            if(r>0){
                tabla.changeSelection(tabla.getSelectedRow()-1,0, false, false);
                r--;
                Cliente c = cb.buscarCliente(tabla.getValueAt(r, 0));
                clienteGui.CargarCampos(c);
            }
        }
        if(b.equals(clienteGui.getNuevo())){ //permite crear un nuevo cliente
            System.out.println("nuevo pulsado");
            clienteGui.limpiarCampos();
            clienteGui.habilitarCampos(true);
            nuevoPulsado = true;
            modificarPulsado = false;
        }
        if(b.equals(clienteGui.getGuardar()) && nuevoPulsado){//intenta guardar el nuevo cliente creado
            System.out.println("pulsado guardar crear");
            Cliente c = new Cliente();
            cargarDatosCliente(c,false);
            if(c.getString("nombre").equals("") || c.getString("apellido").equals("")){
                JOptionPane.showMessageDialog(clienteGui,"Un cliente debe tener nombre y apellido");
                Base.close();
                return;
            }
            if(abmCliente.alta(c)){
                
                JOptionPane.showMessageDialog(clienteGui,"Cliente registrado exitosamente");
                c = abmCliente.getCliente(c);
                agregarFila(c);
            }    
            else
                JOptionPane.showMessageDialog(clienteGui,"Cliente ya existente");  
            clienteGui.limpiarCampos();
            nuevoPulsado = false;
            clienteGui.habilitarCampos(false);

        }
        if(b.equals(clienteGui.getGuardar()) && modificarPulsado){  //intenta guardar la modificacion de un cliente
            System.out.println("pulsado guardar modificar");
            Cliente cliente = new Cliente();
            cargarDatosCliente(cliente,true);
            if(cliente.getString("nombre").equals("") || cliente.getString("apellido").equals("")){
                JOptionPane.showMessageDialog(clienteGui,"Un cliente debe tener nombre y apellido");
                Base.close();
                return;
            }
            if(abmCliente.modificar(cliente)){
                JOptionPane.showMessageDialog(clienteGui,"Modificacion realizada con exito");
                cl = cb.filtroCliente("","","");
                actualizarLista();
            }   
            else{
                JOptionPane.showMessageDialog(clienteGui,"No hay ningun cliente seleccionado");
            }
            clienteGui.limpiarCampos();
            modificarPulsado = false;
            clienteGui.habilitarCampos(false);
        }
        if(b.equals(clienteGui.getModificar())){  //permite modificar un cliente existente
            System.out.println("modificar pulsado");
            clienteGui.habilitarCampos(true);  
            modificarPulsado = true;
            nuevoPulsado = false;
        }
        if(b.equals(clienteGui.getBorrar())){  //intenta borrar un cliente seleccionado
            confirmarBorrar = JOptionPane.showConfirmDialog(clienteGui,"¿borrar cliente?","Confirmar Borrado",JOptionPane.YES_NO_OPTION);
            if (JOptionPane.OK_OPTION == confirmarBorrar){
                 System.out.println("confirmado");
                 Cliente cliente = new Cliente();
                 cargarDatosCliente(cliente,true);
                 if(abmCliente.baja(cliente)){
                     JOptionPane.showMessageDialog(clienteGui,"Cliente borrado exitosamente");
                     cl = cb.filtroCliente("","","");
                     actualizarLista();
                     clienteGui.limpiarCampos();
                 }
                 else
                     JOptionPane.showMessageDialog(clienteGui,"No hay ningun cliente seleccionado");
            }     
        }
        Base.close();
    }  
}
