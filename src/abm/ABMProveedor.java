/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package abm;


import modelos.Proveedor;
import modelos.Producto;
import org.javalite.activejdbc.Base;


/**
 *
 * @author joako
 */
public class ABMProveedor implements ABMInterface<Proveedor> {
    
    public boolean findProveedor(Proveedor p){
        return (Proveedor.first("cuil = ?", p.get("cuil"))!=null);
    }
    

    @Override
    public boolean alta(Proveedor p) {
        if (!findProveedor(p)){
            Base.openTransaction();
            Proveedor nuevo = Proveedor.create("nombre",p.get("nombre"),"apellido",p.get("apellido"),"dni",p.get("dni"),"telefono",p.get("telefono"),"celular",p.get("celular"),"mail",p.get("mail"),"cuil",p.get("cuil"),"nombre_banco",p.get("nombre_banco"),"sucursal",p.get("sucursal"),"tipo_cuenta",p.get("tipo_cuenta"),"cuenta",p.get("cuenta"),"compra_minima",p.get("compra_minima"));
            nuevo.saveIt();
            Base.commitTransaction();
            return true;
        } else{
            System.out.println("Proveedor ya existente");
            return false;
        }
    }

    @Override
    public boolean baja(Proveedor p) {
        if (findProveedor(p)){
            Base.openTransaction();
            p.delete();
            Base.commitTransaction();
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public boolean modificar(Proveedor p) {
       Proveedor viejo = Proveedor.findFirst("cuil = ?", p.get("cuil"));
       if (viejo!=null){
            Base.openTransaction();
            viejo.set("nombre",p.get("nombre"),"apellido",p.get("apellido"),"telefono",p.get("telefono"),"celular",p.get("celular"),"mail",p.get("mail"),"cuil",p.get("cuil"),"nombre_banco",p.get("nombre_banco"),"sucursal",p.get("sucursal"),"tipo_cuenta",p.get("tipo_cuenta"),"cuenta",p.get("cuenta"),"compra_minima",p.get("compra_minima")).saveIt();
            Base.commitTransaction();
            return true;
       }
       else {
           return false;
       }
    }
    
}
