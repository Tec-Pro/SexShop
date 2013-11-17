/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package abm;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
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
        Cliente nuevo = Cliente.create("nombre",c.get("nombre"),"apellido",c.get("apellido"),"telefono",c.get("telefono"),"celular",c.get("celular"),"mail",c.get("mail"));
        nuevo.saveIt();          
    }   
    

    @Override
    public void baja(Cliente c) {
        try {
            Statement st = ConnectionDataBase.getConnection().createStatement();
            st.executeUpdate("DELETE FROM cliente WHERE id = '"+c.getId()+"'");
            st.close();
        } catch (SQLException ex) {
            Logger.getLogger(ABMCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void modificar(Cliente c) {
       
    }
    
}
