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
import net.sf.jasperreports.engine.util.Pair;
import org.javalite.activejdbc.LazyList;

/**
 *
 * @author eze
 */
public class ABMCompra implements ABMInterface<Compra> {

    public ABMCompra() {
    }

    @Override
    public boolean alta(Compra c) {
        boolean resultOp = true;
        Integer idProveedor = (Integer) c.get("idproveedor");
        c.set("monto", calcularMonto(c.getProductos()));//seteo el monto de la venta total en el modelo
        Compra compra = Compra.create("monto", c.get("monto"), "idproveedor", idProveedor, "fecha", c.get("fecha"));
        resultOp = resultOp && compra.saveIt();//guardo la venta
        int idCompra = compra.getInteger("id");
        resultOp = resultOp && cargarProductosComprados(idCompra, c.getProductos());//guardo los productos vendidos
        resultOp = resultOp && actualizarStock(c.getProductos());//actualizo el stock de productos vendidos
        return resultOp;
    }

    /*Elimino una compra y los productos ligados a ella, sin hacer devolucion de stock,
     * ni actualizacion de tablas de productos_comprados
     */
    @Override
    public boolean baja(Compra c) {
        Integer idCompra = c.getInteger("id");//saco el idCompra
        Compra compra = Compra.findById(idCompra);//la busco en BD y la traigo
        compra.deleteCascade();//elimino la compra y todos los registros que la referencian
        return true;
    }

    @Override
    public boolean modificar(Compra c) {
        boolean resultOp = true;
        Integer idCompra = c.getInteger("id");
        Compra compraVieja = Compra.findById(idCompra); //busco compra vieja
        resultOp = resultOp && compraVieja.set("monto", c.get("monto")).set("fecha", c.get("fecha")).set("idproveedor", c.get("idproveedor")).saveIt(); //actualizo la compra vieja con la nueva
        LinkedList<Pair> viejosProductos = buscarProductosComprados(idCompra); //saco los viejos productos de la compra
        resultOp = resultOp && devolucionStock(viejosProductos);//actualizo el stock por haber sacado los viejos productos
        resultOp = resultOp && cargarProductosComprados(idCompra, c.getProductos());//guardo los productos nuevos
        resultOp = resultOp && actualizarStock(c.getProductos());//actualizo el stock de productos comprados
        return resultOp;
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
    private boolean cargarProductosComprados(int idCompra, LinkedList<Pair> productos) {
        boolean resultOp = true;
        Iterator itr = productos.iterator();
        Producto prod;
        Pair par;
        Integer cant;
        while (itr.hasNext()) {
            par = (Pair) itr.next(); //saco el par de la lista
            prod = (Producto) par.first(); //saco el producto del par
            cant = (Integer) par.second();//saco la cantidad del par
            ProductosComprado prodVendido = ProductosComprado.create("idcompra", idCompra, "idproducto", prod.get("numero_producto"), "cantidad", cant);
            resultOp = resultOp && prodVendido.saveIt();
        }
        return resultOp;
    }

    /*Retorna una lista de pares producto-cantidad de una compra(la busca en
     * productos_comprados y a su vez
     * elimina estos productos de la base de la misma tabla
     */
    private LinkedList<Pair> buscarProductosComprados(int idCompra) {
        Integer cant;
        ProductosComprado prodComprado;
        Producto prod;
        LinkedList<Pair> listaDePares = new LinkedList<Pair>();
        LazyList<ProductosComprado> productos = ProductosComprado.find("idcompra = ?", idCompra);
        Iterator itr = productos.iterator();
        while (itr.hasNext()) {
            prodComprado = (ProductosComprado) itr.next(); //saco el modelo de la lista
            prod = (Producto) prodComprado.get("idproducto");//saco el producto del modelo
            cant = (Integer) prodComprado.getInteger("cantidad");//saco la cantidad del modelo
            Pair par = new Pair(prod, cant); //creo el par producto-cantidad
            listaDePares.add(par);//agrego el par a la lista de pares
            prodComprado.delete();//elimino el modelo de la base de datos
        }
        return listaDePares;
    }

    //Actualiza el stock de los productos comprados (incrementandolo)
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
            cant = prodViejo.getInteger("stock") + cant;//asigno a cant el valor nuevo del stock
            prodViejo.set("stock", cant);//actualizo el stock del producto
            resultOp = resultOp && abmProd.modificar(prodViejo);//actualizo el producto en la BD

        }
        return resultOp;
    }

    //actualiza el stock (decrementandolo) cuando se elimina una venta o se modifica los productos comprados
    private boolean devolucionStock(LinkedList<Pair> productos) {
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
}
