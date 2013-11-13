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
import java.util.LinkedList;
import modelos.Producto;
/**
 *
 * @author joako
 */
public class ABMProveedor implements ABMInterface<Proveedor> {

    @Override
    public void alta(Proveedor p) {
        if (p.getId()==null)
        try {
            Statement st = ConnectionDataBase.getConnection().createStatement();
            st.executeUpdate("INSERT INTO proveedor(nombre,apellido,dni,telefono,celular,mail,cuil,nombre_banco,sucursal,tipo_cuenta,cuenta,compra_minima) VALUES('"+p.getNombre()+"','"+p.getApellido()+"','"+p.getDni()+"','"+p.getTelefono()+"','"+p.getCelular()+"','"+p.getMail()+"','"+p.getCuil()+"','"+p.getNombreBanco()+"','"+p.getSucursal()+"','"+p.getTipoCuenta()+"','"+p.getCuenta()+"','"+p.getCompraMinima()+"')");
            LinkedList<Producto> l=p.getProvee();
            Producto aux;
            while (!l.isEmpty()){
               aux=l.removeFirst();
               st.executeUpdate("INSERT INTO provee VALUES('"+p.getId()+"','"+aux.getNumeroProducto()+"')");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ABMProveedor.class.getName()).log(Level.SEVERE, null, ex);
        }
        else return; 
    }

    @Override
    public void baja(Proveedor p) {
        try {
            Statement st = ConnectionDataBase.getConnection().createStatement();
            st.executeUpdate("DELETE FROM proveedor WHERE id = '"+p.getId()+"'");
        } catch (SQLException ex) {
            Logger.getLogger(ABMProveedor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void modificar(Proveedor p) {
         try {
            Statement st = ConnectionDataBase.getConnection().createStatement();
            if (p.getNombre() != null)
                st.executeUpdate("UPDATE proveedor SET nombre ='"+p.getNombre()+"' WHERE id ='"+p.getId()+"'");
            if (p.getApellido() != null)
                st.executeUpdate("UPDATE proveedor SET apellido ='"+p.getApellido()+"' WHERE id ='"+p.getId()+"'");
            if (p.getTelefono() != null)
                st.executeUpdate("UPDATE proveedor SET telefono ='"+p.getTelefono()+"' WHERE id ='"+p.getId()+"'");
            if (p.getCelular() != null)
                st.executeUpdate("UPDATE proveedor SET celular ='"+p.getCelular()+"' WHERE id ='"+p.getId()+"'");
            if (p.getMail() != null)
                st.executeUpdate("UPDATE proveedor SET mail ='"+p.getMail()+"' WHERE id ='"+p.getId()+"'");
            if (p.getCuil() != null)
                st.executeUpdate("UPDATE proveedor SET cuil ='"+p.getCuil()+"' WHERE id ='"+p.getId()+"'");
            if (p.getNombreBanco() != null)
                st.executeUpdate("UPDATE proveedor SET nombre_banco ='"+p.getNombreBanco()+"' WHERE id ='"+p.getId()+"'");
            if (p.getSucursal() != null)
                st.executeUpdate("UPDATE proveedor SET sucursal ='"+p.getSucursal()+"' WHERE id ='"+p.getId()+"'");
            if (p.getTipoCuenta() != null)
                st.executeUpdate("UPDATE proveedor SET tipo_cuenta ='"+p.getTipoCuenta()+"' WHERE id ='"+p.getId()+"'");
            if (p.getCuenta() != null)
                st.executeUpdate("UPDATE proveedor SET cuenta ='"+p.getCuenta()+"' WHERE id ='"+p.getId()+"'");
            if (p.getCompraMinima() != null)
                st.executeUpdate("UPDATE proveedor SET compra_minima ='"+p.getCompraMinima()+"' WHERE id ='"+p.getId()+"'");
        } catch (SQLException ex) {
            Logger.getLogger(ABMCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
