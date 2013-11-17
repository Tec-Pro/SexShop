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
import modelos.Cliente;
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

    public ClienteControlador(AbmClienteGui c){
        clienteGui = c; //new AbmClienteGui();
        abmCliente = new ABMCliente();
        clienteGui.setActionListener(this);
        nuevoPulsado = false;
        modificarPulsado = false;
        
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
            System.out.println("pulsado guardar crear");
            Cliente cliente = new Cliente();
            cliente.setApellido(clienteGui.getApellido().getText());
            System.out.println(clienteGui.getApellido().getText());
            cliente.setNombre(clienteGui.getNombre().getText());
            cliente.setCelular(clienteGui.getCelular().getText());
            cliente.setTelefono(clienteGui.getTelFijo().getText());
            cliente.setMail(clienteGui.getEmail().getText());
            abmCliente.alta(cliente);
            clienteGui.limpiarCampos();
            nuevoPulsado = false;
        }
        if(b.equals(clienteGui.getGuardar()) && modificarPulsado){
            System.out.println("pulsado guardar modificar");
            Cliente cliente = new Cliente();
            cliente.setApellido(clienteGui.getApellido().getText());
            System.out.println(clienteGui.getApellido().getText());
            cliente.setNombre(clienteGui.getNombre().getText());
            cliente.setCelular(clienteGui.getCelular().getText());
            cliente.setTelefono(clienteGui.getTelFijo().getText());
            cliente.setMail(clienteGui.getEmail().getText());
            abmCliente.modificar(cliente);
            clienteGui.limpiarCampos();
            modificarPulsado = false;
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
                 System.out.println("confirmado");
                 Cliente cliente = new Cliente();
                 try{
                    cliente.setId(Integer.parseInt(clienteGui.getIdCliente().getText()));
                 }
                 catch(NumberFormatException num){
                     JOptionPane.showMessageDialog(clienteGui,"No hay ningun cliente seleccionado");
                 }
                 abmCliente.baja(cliente);
            }     
            else
                System.out.println("cancelado");
        }
    }
    
    
    
}
