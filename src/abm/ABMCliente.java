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

    @Override
    public void alta(Cliente c) {
        if (c.getId()==null){
        try {
            Statement st = ConnectionDataBase.getConnection().createStatement();
            st.executeUpdate("INSERT INTO cliente(nombre, apellido, telefono, celular, mail) VALUES('"+c.getNombre()+"','"+c.getApellido()+"','"+c.getTelefono()+"','"+c.getCelular()+"','"+c.getMail()+"')");
            st.close();
        } catch (SQLException ex) {
            Logger.getLogger(ABMCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        }else{
            System.out.println("Registro ya existente");
        }
            
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
        try {
            Statement st = ConnectionDataBase.getConnection().createStatement();
            if (c.getNombre() != null){
                st.executeUpdate("UPDATE cliente SET nombre ='"+c.getNombre()+"' WHERE id ='"+c.getId()+"'");
            }if (c.getApellido() != null){
                st.executeUpdate("UPDATE cliente SET apellido ='"+c.getApellido()+"' WHERE id ='"+c.getId()+"'");
            }if (c.getTelefono() != null){
                st.executeUpdate("UPDATE cliente SET telefono ='"+c.getTelefono()+"' WHERE id ='"+c.getId()+"'");
            }if (c.getCelular() != null){
                st.executeUpdate("UPDATE cliente SET celular ='"+c.getCelular()+"' WHERE id ='"+c.getId()+"'");
            }if (c.getMail() != null){
                st.executeUpdate("UPDATE cliente SET mail ='"+c.getMail()+"' WHERE id ='"+c.getId()+"'");
            }
            st.close();
        } catch (SQLException ex) {
            Logger.getLogger(ABMCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
