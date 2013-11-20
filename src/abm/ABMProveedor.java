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
        abrirBase();
        boolean result = (Proveedor.first("cuil = ?", p.get("cuil"))!=null);
        Base.close();
        return result;
    }
    
    public void abrirBase(){
        Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/sexshop","root", "root");
    }

    @Override
    public boolean alta(Proveedor p) {
        abrirBase();
        if (!findProveedor(p)){
            Base.openTransaction();
            Proveedor nuevo = Proveedor.create("nombre",p.get("nombre"),"apellido",p.get("apellido"),"telefono",p.get("telefono"),"celular",p.get("celular"),"mail",p.get("mail"),"cuil",p.get("cuil"),"nombre_banco",p.get("nombre_banco"),"sucursal",p.get("sucursal"),"tipo_cuenta",p.get("tipo_cuenta"),"cuenta",p.get("cuenta"),"compra_minima",p.get("compra_minima"));
            nuevo.saveIt();
            Base.commitTransaction();
            Base.close();
            return true;
        } else{
            System.out.println("Proveedor ya existente");
            Base.close();
            return false;
        }
    }

    @Override
    public boolean baja(Proveedor p) {
        abrirBase();
        if (findProveedor(p)){
            Base.openTransaction();
            p.delete();
            Base.commitTransaction();
            Base.close();
            return true;
        }
        else {
            Base.close();
            return false;
        }
    }

    @Override
    public boolean modificar(Proveedor p) {
       abrirBase(); 
       Proveedor viejo = Proveedor.findFirst("cuil = ?", p.get("cuil"));
       if (viejo!=null){
            Base.openTransaction();
            viejo.set("nombre",p.get("nombre"),"apellido",p.get("apellido"),"telefono",p.get("telefono"),"celular",p.get("celular"),"mail",p.get("mail"),"cuil",p.get("cuil"),"nombre_banco",p.get("nombre_banco"),"sucursal",p.get("sucursal"),"tipo_cuenta",p.get("tipo_cuenta"),"cuenta",p.get("cuenta"),"compra_minima",p.get("compra_minima")).saveIt();
            Base.commitTransaction();
            Base.close();
            return true;
       }
       else {
           Base.close();
           return false;
       }
    }
    
}
