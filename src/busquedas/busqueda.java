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

    
    public void abrirBase(){
        Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/sexshop","root", "root");
    }
    
     public List<Cliente> filtroCliente(String nombre, String apellido, String codigo){
        abrirBase(); 
        List<Cliente> result;
        result = Cliente.where("nombre like ? and apellido like ? and id like ?","%"+nombre+"%", "%"+apellido+"%","%"+codigo+"%");
        Base.close();
        return result;
    }
    
    public List<Producto> filtroProducto(String codigo, String nombre, String marca){
        abrirBase(); 
        List<Producto> result;
        result = Producto.where("codigo_producto like ? and nombre like ? and marca like ?", codigo+"%","%"+nombre+"%","%"+marca+"%");
        Base.close();
        return result;
    }

    public List<Venta> filtroVenta(String idcliente, String desde, String hasta){
        abrirBase(); 
        List<Venta> result;
        result = Venta.where("idcliente like ? and fecha between ? and ?", idcliente+"%",desde, hasta);
        Base.close();
        return result;
    }
    
   public List<Compra> filtroCompra(String idproveedor, String desde, String hasta){
        abrirBase();  
        List<Compra> result;
        result = Compra.where("idproveedor like ? and fecha between ? and ?", idproveedor+"%",desde,hasta);
        Base.close();
        return result;
    }
    
    public List<Proveedor> filtroProveedor(String cuil,String nombre, String apellido) {
        abrirBase(); 
        List<Proveedor> result;
        result = Proveedor.where("cuil like ? and nombre like ? and apellido like ?", "%"+cuil+"%","%"+nombre+"%","%"+apellido+"%");
        Base.close();
        return result;
    }
    
    public List<ProductosComprado> filtroComprados(String idcompra, String idproducto){
        abrirBase(); 
        List<ProductosComprado> result;
        result = ProductosComprado.where("idcompra like ? and idproducto like ?", idcompra+"%",idproducto+"%");
        Base.close();
        return result;
    }
     
    public List<ProductosVendido> filtroVendidos(String idventa, String idproducto){
        abrirBase(); 
        List<ProductosVendido> result;
        result = ProductosVendido.where("idventa like ? and idproducto like ?", idventa+"%",idproducto+"%");
        Base.close();
        return result;
    }
    
     public List<Adquirido> filtroAdquiridos(String idcliente, String idproducto){
        abrirBase(); 
        List<Adquirido> result;
        result = Adquirido.where("idcliente like ? and idproducto like ?", idcliente+"%",idproducto+"%");
        Base.close();
        return result;
    }

     public List<Producto> productosAdquiridos(String idcliente){
        abrirBase(); 
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
}

