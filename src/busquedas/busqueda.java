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
    
    
    
    /**
     *
     * @param id
     * @return Cliente asociado a esa id.
     */
    public Cliente buscarCliente(Object id){
        abrirBase();
        Cliente result = Cliente.findById(id);
        Base.close();
        return result;
    }   
    
    /**
     * @param nombre, @param apellido e @param id del cliente.
     * Filtra los que tienen nombre y apellido similar a los pasados y 
     * a los que empiezan con el código pasado.
     * @return lista filtrada de clientes.
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
    
     /**
     * @param código_producto, @param nombre_producto, @param marca.
     * Filtra aquellos que empiecen con el código pasado y que contengan el nombre y marca.
     * @return lista filtrada de productos
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

    /**
     * @param idcliente, @param fecha desde (Pasada como string), @param fecha hasta (Pasada como String).
     * Filtra las ventas de los clientes que empiezan con idcliente entre las fechas pasadas.
     * @return lista filtrada de ventas.
     */
    public List<Venta> filtroVenta(String idcliente, String desde, String hasta){
        if (!Base.hasConnection()){
            abrirBase(); 
        } 
        List<Venta> result;
        result = Venta.where("cliente_id like ? and fecha between ? and ?", idcliente+"%",desde, hasta);
        Base.close();
        return result;
    }
    
    /**
     * @param idproveedor, @param fecha desde (Pasada como string), @param fecha hasta (Pasada como String).
     * Filtra las compras a los proveedores que empiezan con idproveedores entre las fechas pasadas.
     * @return lista filtrada de compras.
     */
    
   public List<Compra> filtroCompra(String idproveedor, String desde, String hasta){
        if (!Base.hasConnection()){
            abrirBase(); 
        }  
        List<Compra> result;
        result = Compra.where("proveedor_id like ? and fecha between ? and ?", idproveedor+"%",desde,hasta);
        Base.close();
        return result;
    }
    /**
     * @param cuil, @param nombre, @param apellido.
     * Filtra los proveedores que tienen lo pasado en cuil, dentro de su campo cuil, y contienen a nombre y apellido.
     * @return lista filtrada de proveedores.
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
    
    /**
     * @param idcompra, @param idproducto.
     * Filtra los productos comprados en idcompra o las compras que contienen idproducto o ambas.
     * @returns lista filtrada de productos comprados.
     */
    public List<ProductosCompras> filtroComprados(String idcompra, String idproducto){
        if (!Base.hasConnection()){
            abrirBase(); 
        } 
        List<ProductosCompras> result;
        result = ProductosCompras.where("compra_id like ? and producto_id like ?", idcompra+"%",idproducto+"%");
        Base.close();
        return result;
    }
     /**
     * @param idventa, @param idproducto.
     * Filtra los productos vendidos en idventa o las ventas que contienen idproducto o ambas.
     * @returns lista filtrada de productos vendidos.
     */
    public List<ProductosVentas> filtroVendidos(String idventa, String idproducto){
        if (!Base.hasConnection()){
            abrirBase(); 
        }
        List<ProductosVentas> result;
        result = ProductosVentas.where("venta_id like ? and producto_id like ?", idventa+"%",idproducto+"%");
        Base.close();
        return result;
    }
    /**
     * @param idcliente, @param idproducto.
     * Filtra los productos adquiridos por idcliente o los clientes que adquirieron idproducto.
     * @returns lista filtrada de productos adquiridos.
     */
     public List<ClientesProductos> filtroAdquiridos(String idcliente, String idproducto){
        if (!Base.hasConnection()){
            abrirBase(); 
        }
        List<ClientesProductos> result;
        result = ClientesProductos.where("cliente_id like ? and producto_id like ?", idcliente+"%",idproducto+"%");
        Base.close();
        return result;
    }

     /**
     * @param idcliente.
     * Devuelve todos los productos asociados a un cliente.
     * @returns lista filtrada de productos.
     */
     public List<Producto> productosAdquiridos(String idcliente){
        if (!Base.hasConnection()){
            abrirBase(); 
        }
        List<ClientesProductos> adquiridos;
        List<Producto> result = new LinkedList<Producto>();
        adquiridos = ClientesProductos.where("cliente_id like ?", "%"+idcliente+"%");
        Iterator<ClientesProductos> it = adquiridos.iterator();
        while(it.hasNext()){
            ClientesProductos a = it.next();
            Producto p = Producto.first("numero_producto = ?", a.get("idproducto"));
            result.add(p);
        }
        Base.close();
        return result;
    }
     /**
     * @param 
     * Devuelve todos los proveedores de la base de datos.
     * @returns lista de todos los proveedores.
     */
    public List<Proveedor> proveedores(){
        return Proveedor.findAll();
    }
}
