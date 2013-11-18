/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package abm;


import java.util.logging.Level;
import java.util.logging.Logger;
import modelos.Producto;
import modelos.Proveedor;

/**
 *
 * @author joako
 */
public class ABMProducto implements ABMInterface<Producto> {

    public boolean findProducto(Producto p){
        return (Producto.first("numero_producto = ?",p.get("numero_producto"))!= null);
    }
    
    
    @Override
    public boolean alta(Producto p) {
        if (!findProducto(p)){
            Proveedor pr = Proveedor.first("cuil = ?", p.getCuilProveedor());
            if (pr!=null){
                Producto nuevo = Producto.create("precio_venta",p.get("precio_venta"),"precio_compra",p.get("precio_compra"),"stock",p.get("stock"),"numero_producto",p.get("numero_producto"),"nombre",p.get("nombre"),"tipo",p.get("tipo"),"marca",p.get("marca"));
                nuevo.saveIt();
                pr.add(nuevo);
                return true;
            } else {
                System.out.println("Error proveedor");
                return false;
            }
        } else {
            System.out.println("Error producto");
            return false;
        }
    }   
    

    @Override
    public boolean baja(Producto p) {
        if (findProducto(p)){
            p.delete();
            return true;
        }
        return false;
    }

    @Override
    public boolean modificar(Producto p) {
       Producto viejo = Producto.findFirst("numero_producto = ?", p.get("numero_producto"));
       if (viejo!=null){
            viejo.set("precio_venta",p.get("precio_venta"),"precio_compra",p.get("precio_compra"),"stock",p.get("stock"),"numero_producto",p.get("numero_producto"),"nombre",p.get("nombre"),"tipo",p.get("tipo"),"marca",p.get("marca")).saveIt();
            return true;
       }
       return false;
    }
    
}
