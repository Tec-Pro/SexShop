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
import modelos.Proveedor;
import modelos.Venta;
import net.sf.jasperreports.engine.util.Pair;
import org.javalite.activejdbc.LazyList;

/**
 *
 * @author eze
 */
public class ABMVenta implements ABMInterface<Venta> {

    public ABMVenta() {
    }

    //FUNCIONA CORRECTAMENTE
    @Override
    public boolean alta(Venta v) {
        boolean resultOp = true;
        Integer idCliente = (Integer) v.get("idcliente");
        v.set("monto", calcularMonto(v.getProductos()));//seteo el monto de la venta total en el modelo
        Venta venta = Venta.create("monto", v.get("monto"), "idcliente", idCliente, "fecha", v.get("fecha"));
        resultOp = resultOp && venta.saveIt();//guardo la venta
        int idVenta = venta.getInteger("id");
        resultOp = resultOp && cargarProductosVendidos(idVenta, v.getProductos());//guardo los productos vendidos
        resultOp = resultOp && actualizarStock(v.getProductos());//actualizo el stock de productos vendidos
        resultOp = resultOp && actualizarAdquisicionCliente(idCliente, v.getProductos());//actualizo la tabla de productos adquiridos por clientes
        return resultOp;
    }

    //FUNCIONA CORRECTAMENTE
    /*Elimino una venta y los productos ligados a ella, sin hacer devolucion de stock,
     * ni actualizacion de tablas de adquisicion ni tabla de productos_vendidos
     */
    @Override
    public boolean baja(Venta v) {
        Integer idVenta = v.getInteger("id");//saco el idVenta
        Venta venta = Venta.findById(idVenta);//la busco en BD y la traigo
        ProductosVendido.delete("idventa = ?", idVenta);//elimino todos los productosvendidos
        return venta.delete(); //elimino la venta
    }

    //PROBAR
    @Override
    public boolean modificar(Venta v) {
        boolean resultOp = true;
        Integer idCliente = v.getInteger("idcliente");
        Integer idVenta = v.getInteger("id");
        Venta.update("monto = ? AND fecha = ? AND idcliente =?", "id = ?", v.get("monto"), v.get("fecha"),idCliente,idVenta);//actualizo la venta por su id
        LinkedList<Pair> viejosProductos = buscarProductosVendidos(idVenta); //saco los viejos productos de la venta
        resultOp = resultOp && devolucionStock(viejosProductos);//actualizo el stock por haber sacado los viejos productos
        resultOp = resultOp && eliminarAdquisicionCliente(idCliente, viejosProductos);//actualizo los productos adquiridos quitando los viejos productos
        resultOp = resultOp && cargarProductosVendidos(idVenta, v.getProductos());//guardo los productos nuevos
        resultOp = resultOp && actualizarStock(v.getProductos());//actualizo el stock de productos vendidos
        resultOp = resultOp && actualizarAdquisicionCliente(idCliente, v.getProductos());//actualizo la tabla de productos adquiridos por clientes con los nuevos productos
        return resultOp;
    }

    //FUNCIONA CORRECTAMENTE
    /*Recibe lista de pares <Producto,cantidad> retorna precio total de la venta de todos
     los productos de la lista, multiplicados por su cantidad correspondiente*/
    public Double calcularMonto(LinkedList<Pair> productos) {
        Iterator itr = productos.iterator();
        Pair par;
        Producto prod;
        Double acumMonto = 0.0;
        Integer cant;
        while (itr.hasNext()) {
            par = (Pair) itr.next(); //saco el par de la lista
            prod = (Producto) par.first(); //saco el producto del par
            cant = (Integer) par.second();//saco la cantidad del par
            acumMonto += (prod.getDouble("precio_venta") * cant); //multiplico el precio del producto por la cantidad del mismo
        }
        return acumMonto;
    }

    //FUNCIONA CORRECTAMENTE
    //Carga los productos y cantidades en la tabla productos_vendidos
    private boolean cargarProductosVendidos(int idVenta, LinkedList<Pair> productos) {
        boolean resultOp = true;
        Iterator itr = productos.iterator();
        Producto prod;
        Pair par;
        Integer cant;
        while (itr.hasNext()) {
            par = (Pair) itr.next(); //saco el par de la lista
            prod = (Producto) par.first(); //saco el producto del par
            cant = (Integer) par.second();//saco la cantidad del par
            ProductosVendido prodVendido = ProductosVendido.create("idventa", idVenta, "idproducto", prod.get("numero_producto"), "cantidad", cant);
            resultOp = resultOp && prodVendido.saveIt();
        }
        return resultOp;
    }

    //FUNCIONA CORRECTAMENTE
    //Actualiza el stock de los productos vendidos
    private boolean actualizarStock(LinkedList<Pair> productos) {
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
            Proveedor.findById(prodViejo.get("proveedor_id")).add(prodViejo);//creo la relacion
        }
        return resultOp;
    }

    
    //FUNCIONA CORRECTAMENTE
    /*Actualiza el stock de los productos vendidos cuando se da de baja una venta
     * o cuando se modifica (incrementando el stock nuevamente)
     */
    public boolean devolucionStock(LinkedList<Pair> productos) {
        boolean resultOp = true;
        Iterator itr = productos.iterator();
        Producto prodViejo;
        Pair par;
        Integer cant;
        while (itr.hasNext()) {
            cant = 0;
            par = null;
            par = (Pair) itr.next(); //saco el par de la lista
            prodViejo = (Producto) par.first(); //saco el producto del par
            cant = (Integer) par.second();//saco la cantidad del par
            cant = prodViejo.getInteger("stock") + cant;//devuelvo el stock anterior a la venta del producto
            resultOp = resultOp && prodViejo.setInteger("stock", cant).saveIt();//actualizo el stock del producto
            Proveedor.findById(prodViejo.get("proveedor_id")).add(prodViejo);//creo la relacion
        }
        return resultOp;
    }

    //FUNCIONA CORRECTAMENTE
    /*Agrego los productos adquiridos por el cliente a la tabla adquirio,
     * retorna un booleano que es true si las adquisiciones se actualizaron
     * con exito
     */
    private boolean actualizarAdquisicionCliente(int idCliente, LinkedList<Pair> productos) {
        boolean resultOp = true;
        Iterator itr = productos.iterator();
        Producto prod;
        Pair par;
        Integer cant;
        while (itr.hasNext()) {
            par = (Pair) itr.next(); //saco el par de la lista
            prod = (Producto) par.first(); //saco el producto del par
            cant = (Integer) par.second();//saco la cantidad del par
            Adquirido prodAdquirido;
            prodAdquirido = Adquirido.findFirst("idcliente = ? AND idproducto = ?", idCliente, prod.get("numero_producto"));
            if (prodAdquirido == null) { // sino lo agrego a la tabla
                prodAdquirido = Adquirido.create("idcliente", idCliente, "idproducto", prod.get("numero_producto"), "cantidad", cant);
                resultOp = resultOp && prodAdquirido.saveIt();
                Producto.findById(prod.getInteger("numero_producto")).add(prodAdquirido);//creo relacion producto-prodAdquirido
            } else { //si existe modifico la cantidad
                cant = prodAdquirido.getInteger("cantidad") + cant;//asigno a cant el valor nuevo de cantidad
                Adquirido.update("cantidad = ?", "idcliente = ? AND idproducto = ?", cant,idCliente, prod.get("numero_producto"));
            }
        }
        return resultOp;
    }
    
    
    //FUNCIONA CORRECTAMENTE
    /*Busca los productos adquiridos por el cliente y actualiza su cantidad tras la eliminacion
     * o modificacion de una venta, si la cantidad del producto adquirido es 0 lo borra de la tabla
     * sino decrementa en la cantidad que este fue adquirido
     */
    public boolean eliminarAdquisicionCliente(int idCliente, LinkedList<Pair> productos) {
        boolean resultOp = true;
        Iterator itr = productos.iterator();
        Producto prod;
        Pair par;
        Integer cant;
        while (itr.hasNext()) {
            par = (Pair) itr.next(); //saco el par de la lista
            prod = (Producto) par.first(); //saco el producto del par
            cant = (Integer) par.second();//saco la cantidad del par
            Adquirido prodAdquirido;
            prodAdquirido = Adquirido.findFirst("idcliente = ? AND idproducto = ?", idCliente, prod.get("numero_producto"));
            if (prodAdquirido == null) { //si existe modifico la cantidad
                System.out.println("ERROR - PRODUCTO NO ENCONTRADO EN TABLA DE ADQUISICIONES DE CLIENTE");
            }
            else{
                if (prodAdquirido.getInteger("cantidad") - cant > 0) {
                    cant = prodAdquirido.getInteger("cantidad") - cant;//asigno a cant el valor nuevo de cantidad
                    Adquirido.update("cantidad = ?", "idcliente = ? AND idproducto = ?", cant,idCliente, prod.get("numero_producto"));
                }
                else {
                    if (prodAdquirido.getInteger("cantidad") - cant == 0) {
                        Adquirido.delete("idcliente = ? AND idproducto = ?",idCliente, prod.get("numero_producto"));
                    }
                    else {
                        resultOp = false;
                        System.out.println("ERROR LA CANTIDAD DE PRODUCTOS ADQUIRIDOS ES MENOR A LA VENDIDA");
                    }
                }
            }
        }
        return resultOp;
    }

    
    
    //PROBAR
    /*Retorna una lista de pares producto-cantidad de una compra(la busca en
     * productos_comprados y a su vez
     * elimina estos productos de la base de la misma tabla
     */
    public LinkedList<Pair> buscarProductosVendidos(int idVenta) {
        Integer cant;
        ProductosVendido prodVendido;
        Producto prod;
        LinkedList<Pair> listaDePares = new LinkedList<Pair>();
        LazyList<ProductosVendido> productos = ProductosVendido.find("idventa = ?", idVenta);
        Iterator itr = productos.iterator();
        while (itr.hasNext()) {
            prodVendido = (ProductosVendido) itr.next(); //saco el modelo de la lista
            prod = Producto.findFirst("numero_producto = ?", prodVendido.getInteger("idproducto"));//saco el producto del modelo
            cant = (Integer) prodVendido.getInteger("cantidad");//saco la cantidad del modelo
            Pair par = new Pair(prod, cant); //creo el par producto-cantidad
            listaDePares.add(par);//agrego el par a la lista de pares
            prodVendido.delete();//elimino el modelo de la base de datos
        }
        return listaDePares;
    }
}
