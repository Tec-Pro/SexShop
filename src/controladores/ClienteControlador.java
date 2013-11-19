/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import busquedas.busqueda;
import abm.ABMCliente;
import interfaz.AbmClienteGui;
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
import org.javalite.activejdbc.Base;
/**
 *
 * @author agustin
 */
public class ClienteControlador implements ActionListener {
    
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
    List<Cliente> cl;

    public ClienteControlador(AbmClienteGui c){
        Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/sexshop", "root", "root");
        cl = new LinkedList<Cliente>();
        cb = new busqueda();
        clienteGui = c; //new AbmClienteGui();
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
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaMouseClicked(evt);
            }
        });
        tablaClientes = clienteGui.getTablaClientes();  
        cl = cb.filtroCliente("","");
        actualizarLista();
        Base.close();    
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
    
    private void quitarFila(){
        int r = tabla.getSelectedRow();
        tablaClientes.removeRow(r);
    }
    
    private void modificarFila(Cliente c){
        int r = tabla.getSelectedRow();
        tablaClientes.setValueAt(c.getId().toString(), r, 0);
        tablaClientes.setValueAt(c.get("apellido").toString(), r, 1);
        tablaClientes.setValueAt(c.get("nombre").toString(), r, 2);
    }
    
    /*setea un cliente con los datos de los campos*/
    private void cargarDatosCliente(Cliente c, boolean id){
        c.set("apellido",clienteGui.getApellido().getText());
        c.set("nombre",clienteGui.getNombre().getText());
        c.set("celular",clienteGui.getCelular().getText());
        c.set("telefono",clienteGui.getTelFijo().getText());
        c.set("mail",clienteGui.getEmail().getText());
        if(id) c.setId(clienteGui.getIdCliente().getText());
    }
    
    public void busquedaApellidoKeyReleased(java.awt.event.KeyEvent evt){
        Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/sexshop", "root", "root");
        cl = cb.filtroCliente(ba.getText(),bc.getText());
        actualizarLista();
        Base.close();      
    }
    
     public void busquedaCodigoKeyReleased(java.awt.event.KeyEvent evt){
        Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/sexshop", "root", "root");
        cl = cb.filtroCliente(ba.getText(),bc.getText());
        actualizarLista();
        Base.close();      
    }
    
    
    public void tablaMouseClicked(java.awt.event.MouseEvent evt){
        if(!Base.hasConnection()){
        Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/sexshop", "root", "root");
        }
        clienteGui.habilitarCampos(false);
        nuevoPulsado = false;
        modificarPulsado = false;
        System.out.println("tabla pulsada");
        int r = tabla.getSelectedRow();
        Cliente c = Cliente.findById(tabla.getValueAt(r, 0));
        clienteGui.CargarCampos(c);
        Base.close();
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/sexshop", "root", "root");
        JButton b = (JButton)e.getSource();
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
            if(abmCliente.modificar(cliente)){
                JOptionPane.showMessageDialog(clienteGui,"Modificacion realizada con exito");
                modificarFila(cliente);
            }   
            else
                JOptionPane.showMessageDialog(clienteGui,"No hay ningun cliente seleccionado");
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
                     quitarFila();
                 }
                 else
                     JOptionPane.showMessageDialog(clienteGui,"No hay ningun cliente seleccionado");
            }     
        }
        Base.close();
    }  
}
