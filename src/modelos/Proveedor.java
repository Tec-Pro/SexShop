/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import abm.ConnectionDataBase;
import java.util.LinkedList;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author joako
 */
public class Proveedor {
       private String nombre, apellido, dni, telefono, celular, mail, cuil, 
               nombreBanco, sucursal, tipoCuenta;
       private Integer cuenta, compraMinima;
       private LinkedList<Producto> provee;

    public Proveedor() {
        nombre = null;
        apellido = null;
        dni = null;
        telefono = null;
        celular = null;
        mail = null;
        cuil = null;
        nombreBanco = null;
        sucursal = null;
        tipoCuenta = null;
        cuenta = null;
        compraMinima = null;
        provee = null;
    }

    public Proveedor(String nombre, String apellido, String dni, String telefono, String celular, String mail, String cuil, String nombreBanco, String sucursal, String tipoCuenta, Integer cuenta, Integer compraMinima, LinkedList<Producto> provee) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.telefono = telefono;
        this.celular = celular;
        this.mail = mail;
        this.cuil = cuil;
        this.nombreBanco = nombreBanco;
        this.sucursal = sucursal;
        this.tipoCuenta = tipoCuenta;
        this.cuenta = cuenta;
        this.compraMinima = compraMinima;
        this.provee = provee;
    }
       
    
    
    public Integer getId(){
        try{
           Statement st = ConnectionDataBase.getConnection().createStatement();
           ResultSet rs=st.executeQuery("SELECT id FROM proveedor WHERE nombre='"+nombre+"' AND apellido='"+apellido+"' AND dni='"+dni+"' AND telefono='"+telefono+"' AND celular='"+celular+"' AND mail='"+mail+"' AND cuil='"+cuil+"' AND nombre_banco='"+nombreBanco+"' AND sucursal='"+sucursal+"' AND tipo_cuenta='"+tipoCuenta+"' AND cuenta='"+cuenta+"' AND compra_minima='"+compraMinima+"'");
           if (rs.next()) return rs.getInt("id");
        }
         catch (SQLException ex) {
            Logger.getLogger(Proveedor.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
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
     * @return the dni
     */
    public String getDni() {
        return dni;
    }

    /**
     * @param dni the dni to set
     */
    public void setDni(String dni) {
        this.dni = dni;
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
     * @return the cuil
     */
    public String getCuil() {
        return cuil;
    }

    /**
     * @param cuil the cuil to set
     */
    public void setCuil(String cuil) {
        this.cuil = cuil;
    }

    /**
     * @return the nombreBanco
     */
    public String getNombreBanco() {
        return nombreBanco;
    }

    /**
     * @param nombreBanco the nombreBanco to set
     */
    public void setNombreBanco(String nombreBanco) {
        this.nombreBanco = nombreBanco;
    }

    /**
     * @return the sucursal
     */
    public String getSucursal() {
        return sucursal;
    }

    /**
     * @param sucursal the sucursal to set
     */
    public void setSucursal(String sucursal) {
        this.sucursal = sucursal;
    }

    /**
     * @return the tipoCuenta
     */
    public String getTipoCuenta() {
        return tipoCuenta;
    }

    /**
     * @param tipoCuenta the tipoCuenta to set
     */
    public void setTipoCuenta(String tipoCuenta) {
        this.tipoCuenta = tipoCuenta;
    }

    /**
     * @return the cuenta
     */
    public Integer getCuenta() {
        return cuenta;
    }

    /**
     * @param cuenta the cuenta to set
     */
    public void setCuenta(Integer cuenta) {
        this.cuenta = cuenta;
    }

    /**
     * @return the compraMinima
     */
    public Integer getCompraMinima() {
        return compraMinima;
    }

    /**
     * @param compraMinima the compraMinima to set
     */
    public void setCompraMinima(Integer compraMinima) {
        this.compraMinima = compraMinima;
    }

    /**
     * @return the provee
     */
    public LinkedList<Producto> getProvee() {
        return provee;
    }

    /**
     * @param provee the provee to set
     */
    public void setProvee(LinkedList<Producto> provee) {
        this.provee = provee;
    }
}
