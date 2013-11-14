/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package busquedas;

import abm.ConnectionDataBase;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelos.*;

/**
 *
 * @author joako
 */
public class busqueda {
    
    public busqueda(){
        
    }
    
    public LinkedList<Object> filtro(String objeto, String campo, String filtro){
        ResultSet rs = null;
        LinkedList<Object> result = new LinkedList();
        String query = "SELECT "+campo+" FROM "+objeto+" WHERE "+campo+filtro;
        try {
            Statement st = ConnectionDataBase.getConnection().createStatement();
            rs = st.executeQuery(query);
            result = procesar(rs, objeto);
            rs.close();
            st.close();
        } catch (SQLException ex) {
            Logger.getLogger(busqueda.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    
    public LinkedList<Object> procesar(ResultSet rs, String objeto){
        LinkedList<Object> result = new LinkedList();
        try {
            if (objeto.equalsIgnoreCase("cliente")){
                String nombre = null, apellido, telefono, mail, celular;
                Integer id;
                while (rs.next())
                    nombre = rs.getString("nombre");
                    apellido = rs.getString("apellido");
                    telefono = rs.getString("telefono");
                    mail = rs.getString("mail");
                    celular = rs.getString("celular");
                    id = rs.getInt("id");
                    Cliente c = new Cliente(nombre, apellido, telefono, celular, mail, null);
                    c.setId(id);
                    result.addLast(c);       
            } if (objeto.equalsIgnoreCase("producto")){
                String nombre = null, marca, tipo;
                Integer stock, numeroProducto;
                Double precioCompra, precioVenta;
                while (rs.next())
                    nombre = rs.getString("nombre");
                    marca = rs.getString("marca");
                    tipo = rs.getString("tipo");
                    numeroProducto = rs.getInt("numero_producto");
                    stock = rs.getInt("stock");
                    precioCompra = rs.getDouble("precio_compra");
                    precioVenta = rs.getDouble("precio_venta");
                    Producto p = new Producto(precioVenta, precioCompra, stock, numeroProducto, nombre, marca, tipo, null);
                    result.addLast(p);       
            }
        
            
            
            
        } catch (SQLException ex) {
            Logger.getLogger(busqueda.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
