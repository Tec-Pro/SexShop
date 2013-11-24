/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sexshop;

import abm.ABMProducto;
import modelos.Producto;
import org.javalite.activejdbc.Base;

/**
 *
 * @author joako
 */
public class SexShop {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/sexshop","root", "root");
        Producto p;
        ABMProducto abm = new ABMProducto();
        p = Producto.findById(1);
        p.setCuilProveedor("22222");
        abm.changeProveedor(p);
        Base.close();
    }
}
