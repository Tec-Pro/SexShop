/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package abm;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelos.Producto;
import modelos.Proveedor;

/**
 *
 * @author joako
 */
public class ABMProducto implements ABMInterface<Producto>{

    @Override
    public void alta(Producto p) {
        try {
            if(p.getProveedor() != null){
                Statement st = ConnectionDataBase.getConnection().createStatement();
                st.executeUpdate("INSERT INTO producto VALUES('"+p.getPrecio()+"','"+p.getStock()+"','"+p.getNumeroProducto()+"','"+p.getNombre()+"','"+p.getTipo()+"','"+p.getMarca()+"')");
            } 
        } catch (SQLException ex) {
            Logger.getLogger(ABMCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void baja(Producto p) {
        try {
            Statement st = ConnectionDataBase.getConnection().createStatement();
            st.executeUpdate("DELETE FROM producto WHERE numero_producto = '"+p.getNumeroProducto()+"'");
        } catch (SQLException ex) {
            Logger.getLogger(ABMCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void modificar(Producto p) {
        try {
            Statement st = ConnectionDataBase.getConnection().createStatement();
            if (p.getPrecio()!= null){
                st.executeUpdate("UPDATE producto SET precio_venta ='"+p.getPrecio()+"' WHERE numero_producto ='"+p.getNumeroProducto()+"'");
            }if (p.getStock()!= null){
                st.executeUpdate("UPDATE producto SET stock ='"+p.getStock()+"' WHERE numero_producto ='"+p.getNumeroProducto()+"'");
            }if (p.getNumeroProducto()!= null){
                st.executeUpdate("UPDATE producto SET numero_producto ='"+p.getNumeroProducto()+"' WHERE numero_producto ='"+p.getNumeroProducto()+"'");
            }if (p.getNombre()!= null){
                st.executeUpdate("UPDATE producto SET nombre ='"+p.getNombre()+"' WHERE numero_producto ='"+p.getNumeroProducto()+"'");
            }if (p.getTipo()!= null){
                st.executeUpdate("UPDATE producto SET tipo ='"+p.getTipo()+"' WHERE numero_producto ='"+p.getNumeroProducto()+"'");
            }if (p.getMarca()!= null){
                st.executeUpdate("UPDATE producto SET marca ='"+p.getMarca()+"' WHERE numero_producto ='"+p.getNumeroProducto()+"'");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ABMCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
