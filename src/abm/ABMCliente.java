/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package abm;

import modelos.Cliente;

/**
 *
 * @author joako
 */
public class ABMCliente implements ABMInterface<Cliente>{

    
    public Cliente getCliente(Cliente c){
        return Cliente.first("nombre = ? and apellido = ? and telefono = ?", c.get("nombre"), c.get("apellido"), c.get("telefono"));
    }
    
    public boolean findCliente(Cliente c){
        return (Cliente.first("nombre = ? and apellido = ? and telefono = ?", c.get("nombre"), c.get("apellido"), c.get("telefono"))!= null);
    }
    
    
    @Override
    public boolean alta(Cliente c) {
        if (!findCliente(c)){
            Cliente nuevo = Cliente.create("nombre",c.get("nombre"),"apellido",c.get("apellido"),"telefono",c.get("telefono"),"celular",c.get("celular"),"mail",c.get("mail"));
            nuevo.saveIt();  
            return true;
        } else{
            return false;
        }
    }   
    

    @Override
    public boolean baja(Cliente c) {
        Cliente viejo = Cliente.findById(c.getId());
        if (viejo!=null){
            viejo.delete();
            return true;
        }
        return false;
    }

    @Override
    public boolean modificar(Cliente c) {
       Cliente viejo = Cliente.findById(c.getId());
       if (viejo!=null){
            viejo.set("nombre",c.get("nombre"),"apellido",c.get("apellido"),"telefono",c.get("telefono"),"celular",c.get("celular"),"mail",c.get("mail")).saveIt();
            return true;
       }
       return false;
    }
}
