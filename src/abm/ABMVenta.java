/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package abm;

import java.util.Iterator;
import java.util.LinkedList;
import modelos.Producto;
import modelos.ProductosVendido;
import modelos.Venta;
import net.sf.jasperreports.engine.util.Pair;

/**
 *
 * @author eze
 */
public class ABMVenta implements ABMInterface<Venta> {

    public ABMVenta() {
    }

    @Override
    public void alta(Venta v) {
        Integer idCliente = (Integer) v.get("idcliente");
        v.set("monto", calcularMonto(v.getProductos()));//seteo el monto de la venta total en el modelo
        Venta venta = Venta.create("monto", v.get("monto"), "idcliente", idCliente, "fecha", v.get("fecha"));
        venta.saveIt();//guardo la venta
        int idVenta = venta.getInteger("id");
        cargarProductosVendidos(idVenta,v.getProductos());//guardo los productos vendidos
        actualizarStock(v.getProductos());//actualizo el stock de productos vendidos
        actualizarAdquisicionCliente(idCliente,v.getProductos());//actualizo la tabla de productos adquiridos por clientes
    }

    @Override
    public void baja(Venta v) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void modificar(Venta v) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /*Recibe lista de pares <Producto,cantidad> retorna precio total de la venta de todos
    los productos de la lista, multiplicados por su cantidad correspondiente*/
    private Double calcularMonto(LinkedList<Pair> productos) {
        Iterator itr = productos.iterator();
        Pair par;
        Producto prod;
        Double acumMonto = 0.0;
        Double cant;
        while (itr.hasNext()) {
            par = (Pair) itr.next(); //saco el par de la lista
            prod = (Producto) par.first(); //saco el producto del par
            cant = (Double) par.second();//saco la cantidad del par
            acumMonto += (prod.getDouble("precio_venta") * cant); //multiplico el precio del producto por la cantidad del mismo
        }
        return acumMonto;
    }
    
    
    //Carga los productos y cantidades en la tabla productos_vendidos
    private void cargarProductosVendidos(int idVenta, LinkedList<Pair> productos){
        Iterator itr = productos.iterator();
        Producto prod;
        Pair par;
        Integer cant;
        while (itr.hasNext()) {
            par = (Pair) itr.next(); //saco el par de la lista
            prod = (Producto) par.first(); //saco el producto del par
            cant = (Integer) par.second();//saco la cantidad del par
            ProductosVendido prodVendido = ProductosVendido.create("idventa",idVenta,"idproducto",prod.get("numero_producto"),"cantidad",cant);
            prodVendido.saveIt();
        } 
    }
    
    //Actualiza el stock de los productos vendidos
    private void actualizarStock(LinkedList<Pair> productos){
        Iterator itr = productos.iterator();
        Producto prodViejo;
        Pair par;
        Integer cant;
        ABMProducto abmProd = new ABMProducto();
        while (itr.hasNext()) {
            par = (Pair) itr.next(); //saco el par de la lista
            prodViejo = (Producto) par.first(); //saco el producto del par
            cant = (Integer) par.second();//saco la cantidad del par
            cant = prodViejo.getInteger("stock") - cant;//asigno a cant el valor nuevo del stock
            prodViejo.set("stock", cant);//actualizo el stock del producto
            abmProd.modificar(prodViejo);//actualizo el producto en la BD
            
        }
    }
    
    //Agrego los productos adquiridos por el cliente a la tabla adquirio
    private void actualizarAdquisicionCliente(int idCliente, LinkedList<Pair> productos){
        
    }

}
