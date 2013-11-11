/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package abm;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelos.Proveedor;
/**
 *
 * @author joako
 */
public class ABMProveedor implements ABMInterface<Proveedor> {

    @Override
    public void alta(Proveedor p) {
        try {
            Statement st = ConnectionDataBase.getConnection().createStatement();
            st.executeUpdate("INSERT INTO proveedor VALUES('"+p.getNombre()+"','"+p.getApellido()+"','"+p.getDni()+"','"+p.getTelefono()+"','"+p.getCelular()+"','"+p.getMail()+"','"+p.getCuil()+"','"+p.getNombreBanco()+"','"+p.getSucursal()+"','"+p.getTipoCuenta()+"','"+p.getCuenta()+"','"+p.getCompraMinima()+"')");
        } catch (SQLException ex) {
            Logger.getLogger(ABMProveedor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void baja(Proveedor p) {
        try {
            Statement st = ConnectionDataBase.getConnection().createStatement();
            st.executeUpdate("DELETE FROM proveedor WHERE dni = '"+p.getDni()+"'");
        } catch (SQLException ex) {
            Logger.getLogger(ABMProveedor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void modificar(Proveedor p) {
         try {
            Statement st = ConnectionDataBase.getConnection().createStatement();
            if (p.getNombre() != null)
                st.executeUpdate("UPDATE proveedor SET nombre ='"+p.getNombre()+"' WHERE dni ='"+p.getDni()+"'");
            if (p.getApellido() != null)
                st.executeUpdate("UPDATE proveedor SET apellido ='"+p.getApellido()+"' WHERE dni ='"+p.getDni()+"'");
            if (p.getTelefono() != null)
                st.executeUpdate("UPDATE proveedor SET telefono ='"+p.getTelefono()+"' WHERE dni ='"+p.getDni()+"'");
            if (p.getCelular() != null)
                st.executeUpdate("UPDATE proveedor SET celular ='"+p.getCelular()+"' WHERE dni ='"+p.getDni()+"'");
            if (p.getMail() != null)
                st.executeUpdate("UPDATE proveedor SET mail ='"+p.getMail()+"' WHERE dni ='"+p.getDni()+"'");
            if (p.getCuil() != null)
                st.executeUpdate("UPDATE proveedor SET cuil ='"+p.getCuil()+"' WHERE dni ='"+p.getDni()+"'");
            if (p.getNombreBanco() != null)
                st.executeUpdate("UPDATE proveedor SET nombre_banco ='"+p.getNombreBanco()+"' WHERE dni ='"+p.getDni()+"'");
            if (p.getSucursal() != null)
                st.executeUpdate("UPDATE proveedor SET sucursal ='"+p.getSucursal()+"' WHERE dni ='"+p.getDni()+"'");
            if (p.getTipoCuenta() != null)
                st.executeUpdate("UPDATE proveedor SET tipo_cuenta ='"+p.getTipoCuenta()+"' WHERE dni ='"+p.getDni()+"'");
            if (p.getCuenta() != null)
                st.executeUpdate("UPDATE proveedor SET cuenta ='"+p.getCuenta()+"' WHERE dni ='"+p.getDni()+"'");
            if (p.getCompraMinima() != null)
                st.executeUpdate("UPDATE proveedor SET compra_minima ='"+p.getCompraMinima()+"' WHERE dni ='"+p.getDni()+"'");
        } catch (SQLException ex) {
            Logger.getLogger(ABMCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
