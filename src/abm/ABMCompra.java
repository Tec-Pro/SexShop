/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package abm;

import java.util.Iterator;
import java.util.LinkedList;
import modelos.Compra;
import modelos.Producto;
import modelos.ProductosCompras;
import modelos.Proveedor;
import net.sf.jasperreports.engine.util.Pair;
import org.javalite.activejdbc.Base;
import org.javalite.activejdbc.LazyList;

/**
 *
 * @author eze
 */
public class ABMCompra implements ABMInterface<Compra> {

    public ABMCompra() {
    }

    //FUNCIONA CORRECTAMENTE
    @Override
    public boolean alta(Compra c) {
        Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/sexshop", "root", "root");
        Base.openTransaction();
        boolean resultOp = true;
        Integer idProveedor = (Integer) c.get("idproveedor");
        c.set("monto", calcularMonto(c.getProductos()));//seteo el monto de la venta total en el modelo
        Compra compra = Compra.create("monto", c.get("monto"), "idproveedor", idProveedor, "fecha", c.get("fecha"));
        resultOp = resultOp && compra.saveIt();//guardo la venta
        int idCompra = compra.getInteger("id");
        resultOp = resultOp && cargarProductosComprass(idCompra, c.getProductos());//guardo los productos vendidos
        resultOp = resultOp && actualizarStock(c.getProductos());//actualizo el stock de productos vendidos
        Base.commitTransaction();
        Base.close();
        return resultOp;
    }

    
    //fUNCIONA CORRECTAMENTE
    /*Elimino una compra y los productos ligados a ella, sin hacer devolucion de stock,
     * ni actualizacion de tablas de productos_comprados
     */
    @Override
    public boolean baja(Compra c) {
        boolean resultOp = true;
        Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/sexshop", "root", "root");
        Base.openTransaction();
        Integer idCompra = c.getInteger("id");//saco el idCompra
        Compra compra = Compra.findById(idCompra);//la busco en BD y la traigo
        ProductosCompras.delete("idcompra = ?", idCompra);//elimino todos los productoscomprados
        resultOp = resultOp && compra.delete();//elimino la compra y todos los registros que la referencian
        Base.commitTransaction();
        Base.close();
        return resultOp;
    }

    
    //FUNCIONA CORRECTAMENTE
    /*Setear el id de la compra a modificar, la lista de productos nuevos, el idproveedor nuevo 
     * y la fecha nueva, busca la venta vieja y modifica todos sus atributos calculando el nuevo 
     * monto en base a la nueva lista de productos
     */
    @Override
    public boolean modificar(Compra c) {
        Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/sexshop", "root", "root");
        Base.openTransaction();
        boolean resultOp = true;
        Integer idCompra = c.getInteger("id");
        Integer idProveedorNuevo = c.getInteger("idproveedor");
        c.set("monto",calcularMonto(c.getProductos()));//calculo nuevo monto
        Compra compra = Compra.findById(idCompra);
        compra.set("monto", c.get("monto"));
        compra.set("fecha", c.get("fecha"));
        compra.set("idproveedor", idProveedorNuevo);
        compra.saveIt();
        LinkedList<Pair> viejosProductos = buscarProductosComprass(idCompra); //saco los viejos productos de la compra y los elimino de la misma
        resultOp = resultOp && devolucionStock(viejosProductos);//actualizo el stock por haber sacado los viejos productos
        resultOp = resultOp && cargarProductosComprass(idCompra, c.getProductos());//guardo los productos nuevos
        resultOp = resultOp && actualizarStock(c.getProductos());//actualizo el stock de productos comprados
        Base.commitTransaction();
        Base.close();
        return resultOp;
    }

    //FUNCIONA CORRECTAMENTE
    public boolean bajaConDevolucion(Compra c){
        Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/sexshop", "root", "root");
        Base.openTransaction();
        boolean resultOp = true;
        Integer idCompra = c.getInteger("id");//saco el idCompra
        Compra compra = Compra.findById(idCompra);//la busco en BD y la traigo
        LinkedList<Pair> viejosProductos = buscarProductosComprass(idCompra); //saco los viejos productos de la venta
        resultOp = resultOp && devolucionStock(viejosProductos);//actualizo el stock por haber sacado los viejos productos
        ProductosCompras.delete("idcompra = ?", idCompra);//elimino todos los productosvendidos
        resultOp = resultOp && compra.delete(); //elimino la venta
        Base.commitTransaction();
        Base.close();
        return resultOp;
    }
    
    //FUNCIONA CORRECTAMENTE
    /*Recibe lista de pares <Producto,cantidad> retorna precio total de la venta de todos
     los productos de la lista, multiplicados por su cantidad correspondiente*/
    private Double calcularMonto(LinkedList<Pair> productos) {
        Iterator itr = productos.iterator();
        Pair par;
        Producto prod;
        Double acumMonto = 0.0;
        Integer cant;
        while (itr.hasNext()) {
            par = (Pair) itr.next(); //saco el par de la lista
            prod = (Producto) par.first(); //saco el producto del par
            cant = (Integer) par.second();//saco la cantidad del par
            acumMonto += (prod.getDouble("precio_compra") * cant); //multiplico el precio del producto por la cantidad del mismo
        }
        return acumMonto;
    }

    
    //FUNCIONA CORRECTAMENTE
    //Carga los productos y cantidades en la tabla productos_comprados
    private boolean cargarProductosComprass(int idCompra, LinkedList<Pair> productos) {
       boolean resultOp = true;
        Iterator itr = productos.iterator();
        Producto prod;
        Pair par;
        Integer cant;
        while (itr.hasNext()) {
            par = (Pair) itr.next(); //saco el par de la lista
            prod = (Producto) par.first(); //saco el producto del par
            cant = (Integer) par.second();//saco la cantidad del par
            ProductosCompras prodComprado = ProductosCompras.create("idcompra", idCompra, "idproducto", prod.get("numero_producto"), "cantidad", cant);
            resultOp = resultOp && prodComprado.saveIt();
        }
        return resultOp;
    }

    
    //FUNCIONA CORRECTAMENTE
    /*Retorna una lista de pares producto-cantidad de una compra(la busca en
     * productos_comprados y a su vez
     * elimina estos productos de la base de la misma tabla
     */
    private LinkedList<Pair> buscarProductosComprass(int idCompra) {
       Integer cant;
        ProductosCompras prodComprado;
        Producto prod;
        LinkedList<Pair> listaDePares = new LinkedList<Pair>();
        LazyList<ProductosCompras> productos = ProductosCompras.find("idcompra = ?", idCompra);
        Iterator itr = productos.iterator();
        while (itr.hasNext()) {
            prodComprado = (ProductosCompras) itr.next(); //saco el modelo de la lista
            prod = Producto.findFirst("numero_producto = ?", prodComprado.getInteger("idproducto"));//saco el producto del modelo
            cant = (Integer) prodComprado.getInteger("cantidad");//saco la cantidad del modelo
            Pair par = new Pair(prod, cant); //creo el par producto-cantidad
            listaDePares.add(par);//agrego el par a la lista de pares
            ProductosCompras.delete("idcompra = ? AND idproducto = ?",prodComprado.getInteger("idcompra"),prodComprado.getInteger("idproducto"));//elimino el modelo de la base de datos
        }
        return listaDePares;
    }

    
    //FUNCIONA CORRECTAMENTE
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
            resultOp = resultOp && prodViejo.setInteger("stock", cant).saveIt();//actualizo el stock del producto
            Proveedor.findById(prodViejo.get("proveedor_id")).add(prodViejo);//creo la relacion
        }
        return resultOp;
    }

    //FUNCIONA CORRECTAMENTE
    //actualiza el stock (decrementandolo) cuando se elimina una venta o se modifica los productos comprados
    private boolean devolucionStock(LinkedList<Pair> productos) {
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
            cant = prodViejo.getInteger("stock") - cant;//devuelvo el stock anterior a la venta del producto
            resultOp = resultOp && prodViejo.setInteger("stock", cant).saveIt();//actualizo el stock del producto
            Proveedor.findById(prodViejo.get("proveedor_id")).add(prodViejo);//creo la relacion
        }
        return resultOp;
    }
}
