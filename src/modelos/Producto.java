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
public class Producto {
    private Double precioVenta,precioCompra;
    private Integer stock, numeroProducto;
    private String nombre, tipo, marca;
    private LinkedList<Proveedor> proveedor;

    public Producto() {
        this.marca =null;
        this.nombre =null;
        this.numeroProducto= null;
        this.precioVenta = null;
        this.precioCompra =null;
        this.proveedor = null;
        this.stock = null;
        this.tipo = null;
    }

    public Producto(Double precioVenta,Double precioCompra, Integer stock, Integer numeroProducto, String nombre, String tipo, String marca, LinkedList<Proveedor> proveedor) {
        this.precioVenta = precioVenta;
        this.precioCompra = precioCompra;
        this.stock = stock;
        this.numeroProducto = numeroProducto;
        this.nombre = nombre;
        this.tipo = tipo;
        this.marca = marca;
        this.proveedor = proveedor;
    }

    
    
    /**
     * @return the numeroProducto
     */
    public Integer getNumeroProducto() {
        return numeroProducto;
    }

    /**
     * @param numeroProducto the numeroProducto to set
     */
    public void setNumeroProducto(Integer numeroProducto) {
        this.numeroProducto = numeroProducto;
    }

    /**
     * @return the precio
     */
    public Double getPrecioVenta() {
        return precioVenta;
    }

    /**
     * @param precio the precio to set
     */
    public void setPrecioVenta(Double precio) {
        this.precioVenta = precio;
    }
    
     /**
     * @return the precio
     */
    public Double getPrecioCompra() {
        return precioCompra;
    }

    /**
     * @param precio the precio to set
     */
    public void setPrecioCompra(Double precio) {
        this.precioCompra = precio;
    }

    /**
     * @return the stock
     */
    public Integer getStock() {
        return stock;
    }

    /**
     * @param stock the stock to set
     */
    public void setStock(Integer stock) {
        this.stock = stock;
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
     * @return the tipo
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    /**
     * @return the marca
     */
    public String getMarca() {
        return marca;
    }

    /**
     * @param marca the marca to set
     */
    public void setMarca(String marca) {
        this.marca = marca;
    }

    /**
     * @return the proveedor
     */
    public LinkedList<Proveedor> getProveedor() {
        return proveedor;
    }

    /**
     * @param proveedor the proveedor to set
     */
    public void setProveedor(LinkedList<Proveedor> proveedor) {
        this.proveedor = proveedor;
    }
}
