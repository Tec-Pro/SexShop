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
        try {
            Statement st = ConnectionDataBase.getConnection().createStatement();
            st.executeUpdate("INSERT INTO cliente(nombre, apellido, dni, telefono, celular, mail) VALUES('"+c.getNombre()+"','"+c.getApellido()+"','"+c.getDni()+"','"+c.getTelefono()+"','"+c.getCelular()+"','"+c.getMail()+"')");
        } catch (SQLException ex) {
            Logger.getLogger(ABMCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void baja(Cliente c) {
        try {
            Statement st = ConnectionDataBase.getConnection().createStatement();
            st.executeUpdate("DELETE FROM cliente WHERE dni = '"+c.getDni()+"'");
        } catch (SQLException ex) {
            Logger.getLogger(ABMCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void modificar(Cliente c) {
        try {
            Statement st = ConnectionDataBase.getConnection().createStatement();
            if (c.getNombre() != null){
                st.executeUpdate("UPDATE cliente SET nombre ='"+c.getNombre()+"' WHERE dni ='"+c.getDni()+"'");
            }if (c.getApellido() != null){
                st.executeUpdate("UPDATE cliente SET apellido ='"+c.getApellido()+"' WHERE dni ='"+c.getDni()+"'");
            }if (c.getTelefono() != null){
                st.executeUpdate("UPDATE cliente SET telefono ='"+c.getTelefono()+"' WHERE dni ='"+c.getDni()+"'");
            }if (c.getCelular() != null){
                st.executeUpdate("UPDATE cliente SET celular ='"+c.getCelular()+"' WHERE dni ='"+c.getDni()+"'");
            }if (c.getMail() != null){
                st.executeUpdate("UPDATE cliente SET mail ='"+c.getMail()+"' WHERE dni ='"+c.getDni()+"'");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ABMCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
