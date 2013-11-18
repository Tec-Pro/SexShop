/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package abm;

import java.util.Iterator;
import java.util.LinkedList;
import modelos.Adquirido;
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
        public boolean alta(Venta v) {
        boolean resultOp = true;
        Integer idCliente = (Integer) v.get("idcliente");
        v.set("monto", calcularMonto(v.getProductos()));//seteo el monto de la venta total en el modelo
        Venta venta = Venta.create("monto", v.get("monto"), "idcliente", idCliente, "fecha", v.get("fecha"));
        resultOp = resultOp && venta.saveIt();//guardo la venta
        int idVenta = venta.getInteger("id");
        resultOp = resultOp && cargarProductosVendidos(idVenta,v.getProductos());//guardo los productos vendidos
        resultOp = resultOp && actualizarStock(v.getProductos());//actualizo el stock de productos vendidos
        resultOp = resultOp && actualizarAdquisicionCliente(idCliente,v.getProductos());//actualizo la tabla de productos adquiridos por clientes
        return resultOp;
    }

    @Override
        public boolean baja(Venta v) {
         Integer idVenta = v.getInteger("id");//saco el idVenta
         Venta venta = Venta.findById(idVenta);//la busco en BD y la traigo
         venta.deleteCascade();//elimino la venta y todos los registros que la referencian
         return true;
    }

    @Override
        public boolean modificar(Venta v) {
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
    private boolean cargarProductosVendidos(int idVenta, LinkedList<Pair> productos){
        boolean resultOp = true;
        Iterator itr = productos.iterator();
        Producto prod;
        Pair par;
        Integer cant;
        while (itr.hasNext()) {
            par = (Pair) itr.next(); //saco el par de la lista
            prod = (Producto) par.first(); //saco el producto del par
            cant = (Integer) par.second();//saco la cantidad del par
            ProductosVendido prodVendido = ProductosVendido.create("idventa",idVenta,"idproducto",prod.get("numero_producto"),"cantidad",cant);
            resultOp = resultOp && prodVendido.saveIt();
        } 
        return resultOp;
    }
    
    //Actualiza el stock de los productos vendidos
    private boolean actualizarStock(LinkedList<Pair> productos){
        boolean resultOp = true;
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
            resultOp = resultOp && abmProd.modificar(prodViejo);//actualizo el producto en la BD
            
        }
        return resultOp;
    }
    
    /*Agrego los productos adquiridos por el cliente a la tabla adquirio,
     * retorna un booleano que es true si las adquisiciones se actualizaron
     * con exito
     */
    private boolean actualizarAdquisicionCliente(int idCliente, LinkedList<Pair> productos){
        boolean resultOp = true;
        Iterator itr = productos.iterator();
        Producto prod;
        Pair par;
        while (itr.hasNext()) {
            par = (Pair) itr.next(); //saco el par de la lista
            prod = (Producto) par.first(); //saco el producto del par
            if (!Adquirido.exists(prod)){ //si existe no lo agrego a la tabla
                Adquirido prodAdquirido = Adquirido.create("idcliente",idCliente,"idproducto",prod.get("numero_producto"));
                resultOp = resultOp && prodAdquirido.saveIt();
            }
        }
        return resultOp;
    }



}
