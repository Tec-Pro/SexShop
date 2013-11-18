/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package abm;

import java.util.Iterator;
import java.util.LinkedList;
import modelos.Compra;
import modelos.Producto;
import modelos.ProductosComprado;
import modelos.Venta;
import net.sf.jasperreports.engine.util.Pair;

/**
 *
 * @author eze
 */
public class ABMCompra implements ABMInterface<Compra> {

    public ABMCompra(){}

    @Override
    public boolean alta(Compra c) {
        boolean resultOp = true;
        Integer idProveedor = (Integer) c.get("idproveedor");
        c.set("monto", calcularMonto(c.getProductos()));//seteo el monto de la venta total en el modelo
        Compra compra = Compra.create("monto", c.get("monto"), "idproveedor", idProveedor, "fecha", c.get("fecha"));
        resultOp = resultOp && compra.saveIt();//guardo la venta
        int idCompra = compra.getInteger("id");
        resultOp = resultOp && cargarProductosComprados(idCompra,c.getProductos());//guardo los productos vendidos
        resultOp = resultOp && actualizarStock(c.getProductos());//actualizo el stock de productos vendidos
        return resultOp;
    }
    @Override
    public boolean baja(Compra c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean modificar(Compra c) {
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
            acumMonto += (prod.getDouble("precio_compra") * cant); //multiplico el precio del producto por la cantidad del mismo
        }
        return acumMonto;
    }
    
    //Carga los productos y cantidades en la tabla productos_comprados
    private boolean cargarProductosComprados(int idCompra, LinkedList<Pair> productos){
        boolean resultOp = true;
        Iterator itr = productos.iterator();
        Producto prod;
        Pair par;
        Integer cant;
        while (itr.hasNext()) {
            par = (Pair) itr.next(); //saco el par de la lista
            prod = (Producto) par.first(); //saco el producto del par
            cant = (Integer) par.second();//saco la cantidad del par
            ProductosComprado prodVendido = ProductosComprado.create("idcompra",idCompra,"idproducto",prod.get("numero_producto"),"cantidad",cant);
            resultOp = resultOp && prodVendido.saveIt();
        } 
        return resultOp;
    }
    
    //Actualiza el stock de los productos comprados
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
            cant = prodViejo.getInteger("stock") + cant;//asigno a cant el valor nuevo del stock
            prodViejo.set("stock", cant);//actualizo el stock del producto
            resultOp = resultOp && abmProd.modificar(prodViejo);//actualizo el producto en la BD
            
        }
        return resultOp;
    }
}
