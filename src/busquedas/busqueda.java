/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package busquedas;



import java.util.List;
import modelos.*;


/**
 *
 * @author joako
 */
public class busqueda {

    
    public busqueda(){
        
    }
    
    public List<Cliente> filtroCliente(String apellido, String codigo){
        List<Cliente> result;
        result = Cliente.where("apellido like ? and id like ?", "%"+apellido+"%","%"+codigo+"%");
        return result;
    }
    
    public List<Producto> filtroProducto(String codigo, String nombre, String marca){
        List<Producto> result;
        result = Producto.where("codigo_producto like ? and nombre like ? and marca like ?", "%"+codigo+"%","%"+nombre+"%","%"+marca+"%");
        return result;
    }

    public List<Venta> filtroVenta(String idcliente, String fecha){
        List<Venta> result;
        result = Venta.where("idcliente like ? and fecha like ?", "%"+idcliente+"%","%"+fecha+"%");
        return result;
    }
    
   public List<Compra> filtroCompra(String idproveedor, String fecha){
        List<Compra> result;
        result = Compra.where("idproveedor like ? and fecha like ?", "%"+idproveedor+"%","%"+fecha+"%");
        return result;
    }
    
    public List<Proveedor> filtroProveedor(String cuil,String nombre, String apellido) {
        List<Proveedor> result;
        result = Proveedor.where("cuil like ? and nombre like ? and apellido like ?", "%"+cuil+"%","%"+nombre+"%","%"+apellido+"%");
        return result;
    }
    
    public List<ProductosComprado> filtroComprados(String idcompra, String idproducto){
        List<ProductosComprado> result;
        result = ProductosComprado.where("idcompra like ? and idproducto like ?", "%"+idcompra+"%","%"+idproducto+"%");
        return result;
    }
     
    public List<ProductosVendido> filtroVendidos(String idventa, String idproducto){
        List<ProductosVendido> result;
        result = ProductosVendido.where("idventa like ? and idproducto like ?", "%"+idventa+"%","%"+idproducto+"%");
        return result;
    }
    
     public List<Adquirido> filtroAdquiridos(String idcliente, String idproducto){
        List<Adquirido> result;
        result = Adquirido.where("idcliente like ? and idproducto like ?", "%"+idcliente+"%","%"+idproducto+"%");
        return result;
    }
}

