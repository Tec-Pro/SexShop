/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package abm;


import modelos.Producto;

/**
 *
 * @author joako
 */
public class ABMProducto implements ABMInterface<Producto>{

    public boolean findCliente(Producto p){
        return (Producto.first("numero_producto",p.get("numero_producto"))!= null);
    }
    
    
    @Override
    public void alta(Producto p) {
        if (!findCliente(p)){
            Producto nuevo = Producto.create("precio_venta",p.get("precio_venta"),"precio_compra",p.get("precio_compra"),"stock",p.get("stock"),"numero_producto",p.get("numero_producto"),"nombre",p.get("nombre"),"tipo",p.get("tipo"),"marca",p.get("marca"));
            nuevo.saveIt();
        } else {
            System.out.println("Producto existente");
        }
    }   
    

    @Override
    public void baja(Producto p) {
        Producto viejo = Producto.findFirst("numero_producto", p.get("numero_producto"));
        if (findCliente(viejo)){
            viejo.delete();
        }
    }

    @Override
    public void modificar(Producto p) {
       Producto viejo = Producto.findFirst("numero_producto", p.get("numero_producto"));
       if (findCliente(viejo)){
            viejo.set("precio_venta",p.get("precio_venta"),"precio_compra",p.get("precio_compra"),"stock",p.get("stock"),"numero_producto",p.get("numero_producto"),"nombre",p.get("nombre"),"tipo",p.get("tipo"),"marca",p.get("marca")).saveIt();
       }
    }
    
}
