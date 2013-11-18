/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package abm;


import modelos.Proveedor;
import modelos.Producto;
/**
 *
 * @author joako
 */
/*public class ABMProveedor implements ABMInterface<Proveedor> {
    
    public boolean findProveedor(Proveedor p){
        return (Proveedor.first("cuil = ?", p.get("cuil"))!=null);
    }

    @Override
    public void alta(Proveedor p) {
       if (!findProveedor(p)){
            Proveedor nuevo = Proveedor.create("nombre",p.get("nombre"),"apellido",p.get("apellido"),"telefono",p.get("telefono"),"celular",p.get("celular"),"mail",p.get("mail"),"cuil",p.get("cuil"),"nombre_banco",p.get("nombre_banco"),"sucursal",p.get("sucursal"),"tipo_cuenta",p.get("tipo_cuenta"),"cuenta",p.get("cuenta"),"compra_minima",p.get("compra_minima"));
            nuevo.saveIt();  
        } else{
            System.out.println("Proveedor ya existente");
        } 
    }

    @Override
    public void baja(Proveedor p) {
        Proveedor viejo = Proveedor.findFirst("cuil = ?", p.get("cuil"));
        if (findProveedor(viejo)){
            viejo.delete();
        }
    }

    @Override
    public void modificar(Proveedor p) {
        Proveedor viejo = Proveedor.findFirst("cuil = ?", p.get("cuil"));
       if (findProveedor(viejo)){
            viejo.set("nombre",p.get("nombre"),"apellido",p.get("apellido"),"telefono",p.get("telefono"),"celular",p.get("celular"),"mail",p.get("mail"),"cuil",p.get("cuil"),"nombre_banco",p.get("nombre_banco"),"sucursal",p.get("sucursal"),"tipo_cuenta",p.get("tipo_cuenta"),"cuenta",p.get("cuenta"),"compra_minima",p.get("compra_minima")).saveIt();
       }
    }
    
}*/
