/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package abm;


import java.util.logging.Level;
import java.util.logging.Logger;
import modelos.Producto;
import modelos.Proveedor;
import org.javalite.activejdbc.Base;

/**
 *
 * @author joako
 */
public class ABMProducto implements ABMInterface<Producto> {

    public boolean findProducto(Producto p){
        return (Producto.first("id = ?",p.get("id"))!= null);
    }
    
    public Producto getProducto(Producto p){
        Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/sexshop", "root", "root");
        Producto prod = Producto.first("nombre = ? and marca = ?", p.get("nombre"), p.get("marca"));
        Base.close();
        return prod;
    }
    
    @Override
    public boolean alta(Producto p) {
        Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/sexshop", "root", "root");
        if (!findProducto(p)){
            Proveedor pr = Proveedor.first("cuil = ?", p.getCuilProveedor());
            if (pr!=null){
                Base.openTransaction();
                Producto nuevo = Producto.create("precio_venta",p.get("precio_venta"),"precio_compra",p.get("precio_compra"),"stock",p.get("stock"),"numero_producto",p.get("numero_producto"),"nombre",p.get("nombre"),"tipo",p.get("tipo"),"marca",p.get("marca"));
                nuevo.saveIt();
                pr.add(nuevo);
                Base.commitTransaction();
                Base.close();
                return true;
            } else {
                Base.close();
                System.out.println("Error proveedor");
                return false;
            }
        } else {
            Base.close();
            System.out.println("Error producto");
            return false;
        }
    }   
    

    @Override
    public boolean baja(Producto p) {
        Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/sexshop", "root", "root");
       if (findProducto(p)){
            Base.openTransaction();
            p.delete();
            Base.commitTransaction();
            Base.close();
            return true;
        }
        Base.close();
        return false;
    }

    @Override
    public boolean modificar(Producto p) {
       Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/sexshop", "root", "root");
       Producto viejo = Producto.findFirst("numero_producto = ?", p.get("numero_producto"));
       if (viejo!=null){
            Base.openTransaction();
            viejo.set("precio_venta",p.get("precio_venta"),"precio_compra",p.get("precio_compra"),"stock",p.get("stock"),"numero_producto",p.get("numero_producto"),"nombre",p.get("nombre"),"tipo",p.get("tipo"),"marca",p.get("marca")).saveIt();
            Base.commitTransaction();
            Base.close();
            return true;
       }
       Base.close();
       return false;
    }
    
}
