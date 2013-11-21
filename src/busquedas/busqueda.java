/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package busquedas;



import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import modelos.*;
import org.javalite.activejdbc.Base;


/**
 *
 * @author joako
 */
public class busqueda {

    /*
     * No hace falta distinguir entre mayúsculas y minúsculas.
     */
    public void abrirBase(){
        Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/sexshop","root", "root");
    }
    
    
     public Cliente buscarCliente(Object id){
        abrirBase();
        Cliente result = Cliente.findById(id);
        Base.close();
        return result;
     }   
    
    /*
     * @params nombre, apellido e id del cliente.
     * Filtra los que tienen nombre y apellido similar a los pasados y 
     * a los que empiezan con el código pasado.
     * @returns lista filtrada de clientes.
     */
     public List<Cliente> filtroCliente(String nombre, String apellido, String codigo){
        if (!Base.hasConnection()){
            abrirBase(); 
        }
        List<Cliente> result;
        result = Cliente.where("nombre like ? and apellido like ? and id like ?","%"+nombre+"%", "%"+apellido+"%", codigo+"%");
        Base.close();
        return result;
    }
    
     /*
     * @params codigo_producto, nombre producto, marca.
     * Filtra aquellos que empiecen con el codigo pasado y que contengan el nombre y marca.
     * @returns lista filtrada de productos
     */
    public List<Producto> filtroProducto(String codigo, String nombre, String marca){
        if (!Base.hasConnection()){
            abrirBase(); 
        } 
        List<Producto> result;
        result = Producto.where("numero_producto like ? and nombre like ? and marca like ?", codigo+"%","%"+nombre+"%","%"+marca+"%");
        Base.close();
        return result;
    }

    /*
     * @params idcliente, fecha desde (Pasada como string), fecha hasta (Pasada como String).
     * Filtra las ventas de los clientes que empiezan con idcliente entre las fechas pasadas.
     * @returns lista filtrada de ventas.
     */
    public List<Venta> filtroVenta(String idcliente, String desde, String hasta){
       // if (!Base.hasConnection()){ //perdon por estooo!!!!
       //     abrirBase(); 
       // } 
        List<Venta> result;
        result = Venta.where("idcliente like ? and fecha between ? and ?", idcliente+"%",desde, hasta);
       // Base.close();
        return result;
    }
    
    /*
     * @params idproveedor, fecha desde (Pasada como string), fecha hasta (Pasada como String).
     * Filtra las compras a los proveedores que empiezan con idproveedores entre las fechas pasadas.
     * @returns lista filtrada de compras.
     */
    
   public List<Compra> filtroCompra(String idproveedor, String desde, String hasta){
        if (!Base.hasConnection()){
            abrirBase(); 
        }  
        List<Compra> result;
        result = Compra.where("idproveedor like ? and fecha between ? and ?", idproveedor+"%",desde,hasta);
        Base.close();
        return result;
    }
    /*
     * @params cuil, nombre, apellido.
     * Filtra los proveedores que tienen lo pasado en cuil, dentro de su campo cuil, y contienen a nombre y apellido.
     * @returns lista filtrada de proveedores.
     */
    public List<Proveedor> filtroProveedor(String cuil, String nombre, String apellido) {
        if (!Base.hasConnection()){
            abrirBase(); 
        } 
        List<Proveedor> result;
        result = Proveedor.where("cuil like ? and nombre like ? and apellido like ?", "%"+cuil+"%","%"+nombre+"%","%"+apellido+"%");
        Base.close();
        return result;
    }
    
    /*
     * @params idcompra, idproducto.
     * Filtra los productos comprados en idcompra o las compras que contienen idproducto o ambas.
     * @returns lista filtrada de productos comprados.
     */
    public List<ProductosComprado> filtroComprados(String idcompra, String idproducto){
        if (!Base.hasConnection()){
            abrirBase(); 
        } 
        List<ProductosComprado> result;
        result = ProductosComprado.where("idcompra like ? and idproducto like ?", idcompra+"%",idproducto+"%");
        Base.close();
        return result;
    }
     /*
     * @params idventa, idproducto.
     * Filtra los productos vendidos en idventa o las ventas que contienen idproducto o ambas.
     * @returns lista filtrada de productos vendidos.
     */
    public List<ProductosVendido> filtroVendidos(String idventa, String idproducto){
        if (!Base.hasConnection()){
            abrirBase(); 
        }
        List<ProductosVendido> result;
        result = ProductosVendido.where("idventa like ? and idproducto like ?", idventa+"%",idproducto+"%");
        Base.close();
        return result;
    }
    /*
     * @params idcliente, idproducto.
     * Filtra los productos adquiridos por idcliente o los clientes que adquirieron idproducto.
     * @returns lista filtrada de productos adquiridos.
     */
     public List<Adquirido> filtroAdquiridos(String idcliente, String idproducto){
        if (!Base.hasConnection()){
            abrirBase(); 
        }
        List<Adquirido> result;
        result = Adquirido.where("idcliente like ? and idproducto like ?", idcliente+"%",idproducto+"%");
        Base.close();
        return result;
    }

     /*
     * @params idcliente.
     * Devuelve todos los productos asociados a un cliente.
     * @returns lista filtrada de productos.
     */
     public List<Producto> productosAdquiridos(String idcliente){
        if (!Base.hasConnection()){
            abrirBase(); 
        }
        List<Adquirido> adquiridos;
        List<Producto> result = new LinkedList<Producto>();
        adquiridos = Adquirido.where("idcliente like ?", "%"+idcliente+"%");
        Iterator<Adquirido> it = adquiridos.iterator();
        while(it.hasNext()){
            Adquirido a = it.next();
            Producto p = Producto.first("numero_producto = ?", a.get("idproducto"));
            result.add(p);
        }
        Base.close();
        return result;
    }
     /*
     * @params.
     * Devuelve todos los proveedores de la base de datos.
     * @returns lista de todos los proveedores.
     */
    public List<Proveedor> proveedores(){
       // abrirBase(); 
        return Proveedor.findAll();
        
    }
}

