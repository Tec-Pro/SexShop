/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import java.util.LinkedList;

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
        nombre = "";
        apellido = "";
        dni = "";
        telefono = "";
        celular = "";
        mail = "";
        cuil = "";
        nombreBanco = "";
        sucursal = "";
        tipoCuenta = "";
        cuenta = null;
        compraMinima = 0;
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
