/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package abm;

import java.util.Iterator;
import java.util.LinkedList;
import modelos.*;
import net.sf.jasperreports.engine.util.Pair;
import org.javalite.activejdbc.Base;
import org.javalite.activejdbc.LazyList;

/**
 *
 * @author eze
 */
public class ABMVenta implements ABMInterface<Venta> {
    
    int ultimoIdVenta;
    public ABMVenta() {
    }

    //FUNCIONA CORRECTAMENTE
    @Override
    public boolean alta(Venta v) {
        Base.openTransaction();
        boolean resultOp = true;
               if (v == null) {
             resultOp = false;
         } else {
             Integer idCliente = (Integer) v.get("cliente_id");
             v.set("monto", calcularMonto(v.getProductos()));//seteo el monto de la venta total en el modelo
             Venta venta = Venta.create("monto", v.get("monto"), "cliente_id", idCliente, "fecha", v.get("fecha"));
             resultOp = resultOp && venta.saveIt();//guardo la venta
             int idVenta = venta.getInteger("id");
             ultimoIdVenta=idVenta;
             resultOp = resultOp && cargarProductosVendidos(idVenta, v.getProductos());//guardo los productos vendidos
             resultOp = resultOp && actualizarStock(v.getProductos());//actualizo el stock de productos vendidos
             resultOp = resultOp && actualizarAdquisicionCliente(idCliente, v.getProductos());//actualizo la tabla de productos adquiridos por clientes
         }
        Base.commitTransaction();
        return resultOp;
    }

    public int getUltimoIdVenta() {
        return ultimoIdVenta;
    }
    
    

    //FUNCIONA CORRECTAMENTE
    /*Elimino una venta y los productos ligados a ella, sin hacer devolucion de stock,
     * ni actualizacion de tablas de adquisicion ni tabla de productos_vendidos
     */
    @Override
    public boolean baja(Venta v) {
        Base.openTransaction();
        boolean resultOp = true;
        Integer idVenta = v.getInteger("id");//saco el idVenta
        Venta venta = Venta.findById(idVenta);//la busco en BD y la traigo
        
        if (venta == null) {
             resultOp = false;
         } else {
             ProductosVentas.delete("venta_id = ?", idVenta);//elimino todos los productosvendidos
             resultOp = resultOp && venta.delete();//elimino la venta
         }
        Base.commitTransaction();
        return resultOp;
    }

    //FUNCIONA CORRECTAMENTE
    /*Setear el id de la venta a modificar, la lista de productos nuevos, el idproveedor nuevo 
     * y la fecha nueva, busca la venta vieja y modifica todos sus atributos calculando el nuevo 
     * monto en base a la nueva lista de productos
     */
    @Override
    public boolean modificar(Venta v) {
        Base.openTransaction();
        boolean resultOp = true;
                if (v == null) {
             resultOp = false;
         } else {
             Integer idVenta = v.getInteger("id");
             Venta venta = Venta.findById(idVenta);//saco la venta
             Integer idClienteNuevo = v.getInteger("cliente_id");
             v.set("monto", calcularMonto(v.getProductos()));//seteo el monto de la venta total en el modelo
             Integer idVentaVieja = (Integer) venta.getId();
             venta.set("monto", v.get("monto"));
             venta.set("fecha", v.get("fecha"));
             venta.set("cliente_id", idClienteNuevo);
             venta.saveIt();
             LinkedList<Pair> viejosProductos = buscarProductosVendidos(idVenta); //saco los viejos productos de la venta
             resultOp = resultOp && devolucionStock(viejosProductos);//actualizo el stock por haber sacado los viejos productos
             resultOp = resultOp && eliminarAdquisicionCliente(idVentaVieja, viejosProductos);//actualizo los productos adquiridos quitando los viejos productos
             resultOp = resultOp && cargarProductosVendidos(idVenta, v.getProductos());//guardo los productos nuevos
             resultOp = resultOp && actualizarStock(v.getProductos());//actualizo el stock de productos vendidos
             resultOp = resultOp && actualizarAdquisicionCliente(idClienteNuevo, v.getProductos());//actualizo la tabla de productos adquiridos por clientes con los nuevos productos
         }
        Base.commitTransaction();
        return resultOp;
    }
    
    //FUNCIONA CORRECTAMENTE
    public boolean bajaConDevolucion(Venta v){
        Base.openTransaction();
        boolean resultOp = true;
        Integer idVenta = v.getInteger("id");//saco el idVenta
        Venta venta = Venta.findById(idVenta);//la busco en BD y la traigo
        if (venta == null) {
             resultOp = false;
         } else {
             Integer idCliente = (Integer) venta.get("cliente_id");//saco el idcliente de esa venta
             LinkedList<Pair> viejosProductos = buscarProductosVendidos(idVenta); //saco los viejos productos de la venta
             resultOp = resultOp && devolucionStock(viejosProductos);//actualizo el stock por haber sacado los viejos productos
             resultOp = resultOp && eliminarAdquisicionCliente(idCliente, viejosProductos);//actualizo los productos adquiridos quitando los viejos productos
             ProductosVentas.delete("venta_id = ?", idVenta);//elimino todos los productosvendidos
             resultOp = resultOp && venta.delete(); //elimino la venta
         }
        Base.commitTransaction();
        return resultOp;
    }

    //FUNCIONA CORRECTAMENTE
    /*Recibe lista de pares <Producto,cantidad> retorna precio total de la venta de todos
     los productos de la lista, multiplicados por su cantidad correspondiente*/
    public Double calcularMonto(LinkedList<Pair> productos) {
        Double acumMonto = 0.0;
        if (productos.isEmpty()) {
             return acumMonto;
         } else {
             Iterator itr = productos.iterator();
             Pair par;
             Producto prod;
             Integer cant;
             Double precioFinal=0.0;
             while (itr.hasNext()) {
                 par = (Pair) itr.next(); //saco el par de la lista
                 prod = (Producto) par.first(); //saco el producto del par
                 cant = (Integer)((Pair) par.second()).first();//saco la cantidad del par
                 precioFinal= (Double)((Pair) par.second()).second(); //saco el percio al que se vendio
                 acumMonto += (precioFinal * cant); //multiplico el precio del producto por la cantidad del mismo
             }
             return acumMonto;
        }
        }

    //FUNCIONA CORRECTAMENTE
    //Carga los productos y cantidades en la tabla productos_vendidos
    private boolean cargarProductosVendidos(int idVenta, LinkedList<Pair> productos) {
        boolean resultOp = true;
        Iterator itr = productos.iterator();
        Producto prod;
        Pair par;
        Integer cant;
        Double precioFinal=0.0;
        while (itr.hasNext()) {
            par = (Pair) itr.next(); //saco el par de la lista
            prod = (Producto) par.first(); //saco el producto del par
            cant = (Integer)((Pair) par.second()).first();//saco la cantidad del par
            precioFinal= (Double)((Pair) par.second()).second(); //saco el percio al que se vendio
            ProductosVentas prodVendido = ProductosVentas.create("venta_id", idVenta, "producto_id", prod.get("numero_producto"), "cantidad", cant, "precio_final", (cant * precioFinal));
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
        while (itr.hasNext()) {
            par = (Pair) itr.next(); //saco el par de la lista
            prodViejo = (Producto) par.first(); //saco el producto del par
            cant = (Integer)((Pair) par.second()).first();//saco la cantidad del par
            cant = prodViejo.getInteger("stock") - cant;//asigno a cant el valor nuevo del stock
            resultOp = resultOp && prodViejo.setInteger("stock", cant).saveIt();//actualizo el stock del producto
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
            cant = (Integer)((Pair) par.second()).first();//saco la cantidad del par
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
            cant = (Integer)((Pair) par.second()).first();//saco la cantidad del par
            ClientesProductos prodAdquirido;
            prodAdquirido = ClientesProductos.findFirst("cliente_id = ? AND producto_id = ?", idCliente, prod.get("numero_producto"));
            if (prodAdquirido == null) { // sino lo agrego a la tabla
                prodAdquirido = ClientesProductos.create("cliente_id", idCliente, "producto_id", prod.get("numero_producto"), "cantidad", cant);
                resultOp = resultOp && prodAdquirido.saveIt();
            } else { //si existe modifico la cantidad
                cant = prodAdquirido.getInteger("cantidad") + cant;//asigno a cant el valor nuevo de cantidad
                ClientesProductos.update("cantidad = ?", "cliente_id = ? AND producto_id = ?", cant,idCliente, prod.get("numero_producto"));
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
            cant = (Integer)((Pair) par.second()).first();//saco la cantidad del par
            ClientesProductos prodAdquirido;
            prodAdquirido = ClientesProductos.findFirst("cliente_id = ? AND producto_id = ?", idCliente, prod.get("numero_producto"));
            if (prodAdquirido == null) { //si existe modifico la cantidad
                System.out.println("ERROR - PRODUCTO NO ENCONTRADO EN TABLA DE ADQUISICIONES DE CLIENTE");
            }
            else{
                if (prodAdquirido.getInteger("cantidad") - cant > 0) {
                    cant = prodAdquirido.getInteger("cantidad") - cant;//asigno a cant el valor nuevo de cantidad
                    ClientesProductos.update("cantidad = ?", "cliente_id = ? AND producto_id = ?", cant,idCliente, prod.get("numero_producto"));
                }
                else {
                    if (prodAdquirido.getInteger("cantidad") - cant == 0) {
                        ClientesProductos.delete("cliente_id = ? AND producto_id = ?",idCliente, prod.get("numero_producto"));
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

    
    
    //FUNCIONA CORRECTAMENTE
    /*Retorna una lista de pares producto-cantidad de una compra(la busca en
     * productos_comprados y a su vez
     * elimina estos productos de la base de la misma tabla
     */
private LinkedList<Pair> buscarProductosVendidos(int idVenta) {
        Integer cant;
        ProductosVentas prodVendido;
        Producto prod;
        Double precioFinal;
        LinkedList<Pair> listaDePares = new LinkedList<Pair>();
        LazyList<ProductosVentas> productos = ProductosVentas.find("venta_id = ?", idVenta);
        Iterator itr = productos.iterator();
        while (itr.hasNext()) {
            prodVendido = (ProductosVentas) itr.next(); //saco el modelo de la lista
            prod = Producto.findFirst("numero_producto = ?", prodVendido.getInteger("producto_id"));//saco el producto del modelo
            cant = prodVendido.getInteger("cantidad");//saco la cantidad del modelo
            precioFinal = prodVendido.getDouble("precio_final");
            Pair parInterno = new Pair(cant,precioFinal);
            Pair par = new Pair(prod, parInterno); //creo el par producto-cantidad
            listaDePares.add(par);//agrego el par a la lista de pares
            ProductosVentas.delete("venta_id = ? AND producto_id = ?",prodVendido.getInteger("venta_id"),prodVendido.getInteger("producto_id"));//elimino el modelo de la base de datos
        }
        return listaDePares;
    }
    
    
    public LinkedList<Pair> buscarAuxiliar(int idVenta) {
        Integer cant;
        ProductosVentas prodVendido;
        Producto prod;
        LinkedList<Pair> listaDePares = new LinkedList<Pair>();
        LazyList<ProductosVentas> productos = ProductosVentas.find("venta_id = ?", idVenta);
        Iterator itr = productos.iterator();
        while (itr.hasNext()) {
            prodVendido = (ProductosVentas) itr.next(); //saco el modelo de la lista
            prod = Producto.findFirst("numero_producto = ?", prodVendido.getInteger("producto_id"));//saco el producto del modelo
            cant = (Integer) prodVendido.getInteger("cantidad");//saco la cantidad del modelo
            Pair par = new Pair(prod, cant); //creo el par producto-cantidad
            listaDePares.add(par);//agrego el par a la lista de pares
            //ProductosVendido.delete("idventa = ? AND idproducto = ?",prodVendido.getInteger("idventa"),prodVendido.getInteger("idproducto"));//elimino el modelo de la base de datos
        }
        return listaDePares;
    }
}
