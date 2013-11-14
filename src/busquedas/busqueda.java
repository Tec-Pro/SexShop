/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package busquedas;

import abm.ConnectionDataBase;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author joako
 */
public class busqueda {
    
    public busqueda(){
        
    }
    
    public ResultSet filtro(String objeto, String campo, String filtro){
        ResultSet rs = null;
        String query = "SELECT "+campo+" FROM "+objeto+" WHERE "+campo+filtro;
        try {
            Statement st = ConnectionDataBase.getConnection().createStatement();
            rs = st.executeQuery(query);
            rs.close();
            st.close();
        } catch (SQLException ex) {
            Logger.getLogger(busqueda.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }
}
