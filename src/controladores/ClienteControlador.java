/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import abm.ABMCliente;
import interfaz.AbmClienteGui;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
    
    private AbmClienteGui clienteGui;
    private ABMCliente abmCliente;
    //private Cliente cliente;
    private boolean nuevoPulsado;
    private boolean modificarPulsado;
    private int confirmarBorrar;
    private JTextField ba;
    private DefaultTableModel tablaClientes;
    private JTable tabla;

    public ClienteControlador(AbmClienteGui c){
       
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
        tabla = clienteGui.getTabla();
        tabla.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaMouseClicked(evt);
            }
        });
        tablaClientes = clienteGui.getTablaClientes();
        
    }
    
    public void busquedaApellidoKeyReleased(java.awt.event.KeyEvent evt){
        if(ba.getText().isEmpty()){
            
        }
        else{
            
        }
            
    }
    
    public void tablaMouseClicked(java.awt.event.MouseEvent evt){
        System.out.println("tabla pulsada");
        int r = tabla.getSelectedRow();
        System.out.println(String.valueOf(tabla.getValueAt(r,1)));
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton b = (JButton)e.getSource();
        if(b.equals(clienteGui.getNuevo())){
            System.out.println("nuevo pulsado");
            clienteGui.limpiarCampos();
            clienteGui.habilitarCampos(true);
            nuevoPulsado = true;
            modificarPulsado = false;
        }
        if(b.equals(clienteGui.getGuardar()) && nuevoPulsado){
            Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/sexshop", "root", "root");
            System.out.println("pulsado guardar crear");
            Cliente c = new Cliente();
            c.set("apellido",clienteGui.getApellido().getText());
            c.set("nombre",clienteGui.getNombre().getText());
            c.set("celular",clienteGui.getCelular().getText());
            c.set("telefono",clienteGui.getTelFijo().getText());
            c.set("mail",clienteGui.getEmail().getText());
            if(abmCliente.alta(c)){
                JOptionPane.showMessageDialog(clienteGui,"Cliente registrado exitosamente");
                String row[] = new String[3];
                //row[0] = ;
                row[2] = clienteGui.getNombre().getText();
                row[1] = clienteGui.getApellido().getText();
                tablaClientes.addRow(row);
            }    
            else
                JOptionPane.showMessageDialog(clienteGui,"Cliente ya existente");  
            clienteGui.limpiarCampos();
            nuevoPulsado = false;
            clienteGui.habilitarCampos(false);
            Base.close();
        }
        if(b.equals(clienteGui.getGuardar()) && modificarPulsado){
            Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/sexshop", "root", "root");
            System.out.println("pulsado guardar modificar");
            Cliente cliente = new Cliente();
            cliente.set("apellido",clienteGui.getApellido().getText());
            cliente.set("nombre",clienteGui.getNombre().getText());
            cliente.set("celular",clienteGui.getCelular().getText());
            cliente.set("telefono",clienteGui.getTelFijo().getText());
            cliente.set("mail",clienteGui.getEmail().getText());
            abmCliente.modificar(cliente);
            clienteGui.limpiarCampos();
            modificarPulsado = false;
            clienteGui.habilitarCampos(false);
            Base.close();
        }
        if(b.equals(clienteGui.getModificar())){
            System.out.println("modificar pulsado");
            clienteGui.habilitarCampos(true);  
            modificarPulsado = true;
            nuevoPulsado = false;
        }
        if(b.equals(clienteGui.getBorrar())){
            confirmarBorrar = JOptionPane.showConfirmDialog(clienteGui,"¿borrar cliente?","Confirmar Borrado",JOptionPane.YES_NO_OPTION);
            if (JOptionPane.OK_OPTION == confirmarBorrar){
                 Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/sexshop", "root", "root");
                 System.out.println("confirmado");
                 Cliente cliente = new Cliente();
                 cliente.set("apellido",clienteGui.getApellido().getText());
                 cliente.set("nombre",clienteGui.getNombre().getText());
                 cliente.set("telefono",clienteGui.getTelFijo().getText());
                 System.out.println(cliente.get("apellido"));
                 System.out.println(cliente.get("nombre"));
                 System.out.println(cliente.get("telefono"));
                 if(abmCliente.baja(cliente))
                     JOptionPane.showMessageDialog(clienteGui,"Cliente borrado exitosamente");
                 else
                     JOptionPane.showMessageDialog(clienteGui,"No se pudo borrar el cliente");
                 Base.close();
            }     
            else
                System.out.println("cancelado");
        }
    }
    
    
    
}
