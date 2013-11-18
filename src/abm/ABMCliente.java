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

    
    public boolean findCliente(Cliente c){
        return (Cliente.first("nombre = ? and apellido = ? and telefono = ?", c.get("nombre"), c.get("apellido"), c.get("telefono"))!= null);
    }
    
    
    @Override
    public void alta(Cliente c) {
        if (!findCliente(c)){
            Cliente nuevo = Cliente.create("nombre",c.get("nombre"),"apellido",c.get("apellido"),"telefono",c.get("telefono"),"celular",c.get("celular"),"mail",c.get("mail"));
            nuevo.saveIt();  
        } else{
            System.out.println("Cliente ya existente");
        }
    }   
    

    @Override
    public void baja(Cliente c) {
        Cliente viejo = Cliente.findFirst("nombre = ? and apellido = ? and telefono = ?", c.get("nombre"), c.get("apellido"), c.get("telefono"));
        if (findCliente(viejo)){
            viejo.delete();
        }
    }

    @Override
    public void modificar(Cliente c) {
       Cliente viejo = Cliente.findFirst("nombre = ? and apellido = ? and telefono = ?", c.get("nombre"), c.get("apellido"), c.get("telefono"));
       if (findCliente(viejo)){
            viejo.set("nombre",c.get("nombre"),"apellido",c.get("apellido"),"telefono",c.get("telefono"),"celular",c.get("celular"),"mail",c.get("mail")).saveIt();
       }
    }
}
