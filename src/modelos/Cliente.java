/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import abm.ConnectionDataBase;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author joako
 */
public class Cliente {

 
    private String nombre, apellido, telefono, celular, mail;
    private Integer id;
    private LinkedList<Producto> productos;

    public Cliente() {
        this.nombre = null;
        this.apellido= null;
        this.telefono = null;
        this.celular = null;
        this.mail = null;
        this.productos = null;
    }


    public Cliente(String nombre, String apellido, String telefono, String celular, String mail, LinkedList<Producto> productos) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.celular = celular;
        this.mail = mail;
        this.productos = productos;
    }
    
    
    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the apellido
     */
    public String getApellido() {
        return apellido;
    }

    /**
     * @param apellido the apellido to set
     */
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
   
    /**
     * @return the telefono
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * @param telefono the telefono to set
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    /**
     * @return the celular
     */
    public String getCelular() {
        return celular;
    }

    /**
     * @param celular the celular to set
     */
    public void setCelular(String celular) {
        this.celular = celular;
    }

    /**
     * @return the mail
     */
    public String getMail() {
        return mail;
    }

    /**
     * @param mail the mail to set
     */
    public void setMail(String mail) {
        this.mail = mail;
    }

    /**
     * @return the productos
     */
    public LinkedList<Producto> getProductos() {
        return productos;
    }

    /**
     * @param productos the productos to set
     */
    public void setProductos(LinkedList<Producto> productos) {
        this.productos = productos;
    }

    /**
     * @return the id
     */
    public Integer getId(){
        try{
           Statement st = ConnectionDataBase.getConnection().createStatement();
           ResultSet rs=st.executeQuery("SELECT id FROM cliente WHERE nombre='"+nombre+"' AND apellido='"+apellido+"' AND telefono='"+telefono+"'");
           if (rs.next()) return rs.getInt("id");
        }
         catch (SQLException ex) {
            Logger.getLogger(Proveedor.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }
}
