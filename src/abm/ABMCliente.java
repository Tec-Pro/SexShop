/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package abm;

import modelos.Cliente;
import org.javalite.activejdbc.Base;

/**
 *
 * @author joako
 */
public class ABMCliente implements ABMInterface<Cliente>{
  
    public Cliente getCliente(Cliente c){
        return Cliente.first("nombre = ? and apellido = ?", c.get("nombre"), c.get("apellido"));
    }
    
    public boolean findCliente(Cliente c){
        return (Cliente.first("nombre = ? and apellido = ?", c.get("nombre"), c.get("apellido"))!= null);
    }
    
    
    @Override
    public boolean alta(Cliente c) {
        if (!findCliente(c)){
            Base.openTransaction();
            Cliente nuevo = Cliente.create("nombre",c.get("nombre"),"apellido",c.get("apellido"),"telefono",c.get("telefono"),"celular",c.get("celular"),"mail",c.get("mail"));
            nuevo.saveIt();
            Base.commitTransaction();
            return true;
        } else{
            return false;
        }
    }   
    

    @Override
    public boolean baja(Cliente c) {       
        Cliente viejo = Cliente.findById(c.getId());
        if (viejo!=null){
            Base.openTransaction();
            viejo.delete();
            Base.commitTransaction();
            return true;
        }
        return false;
    }

    @Override
    public boolean modificar(Cliente c) {
       Cliente viejo = Cliente.findById(c.getId());
       if (viejo!=null){
            Base.openTransaction();
            viejo.set("nombre",c.get("nombre"),"apellido",c.get("apellido"),"telefono",c.get("telefono"),"celular",c.get("celular"),"mail",c.get("mail")).saveIt();
            Base.commitTransaction();
            return true;
       }
       return false;
    }
}