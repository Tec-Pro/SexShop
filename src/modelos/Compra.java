/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import java.util.LinkedList;

/**
 *
 * @author eze
 */
public class Compra {
    private Proveedor proveedor;
    private LinkedList<Producto> productos;
    private Double monto;
    
    public Compra(){
        proveedor = null;
        productos = null;
        monto = null;
    }

    public Compra(Proveedor proveedor, LinkedList<Producto> productos, Double monto) {
        this.proveedor = proveedor;
        this.productos = productos;
        this.monto = monto;
    }
    

    /**
     * @return the proveedor
     */
    public Proveedor getProveedor() {
        return proveedor;
    }

    /**
     * @param proveedor the proveedor to set
     */
    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
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
     * @return the monto
     */
    public Double getMonto() {
        return monto;
    }

    /**
     * @param monto the monto to set
     */
    public void setMonto(Double monto) {
        this.monto = monto;
    }
    
    
}
