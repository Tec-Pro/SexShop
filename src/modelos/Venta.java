/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import java.util.LinkedList;
import net.sf.jasperreports.engine.util.Pair;
import org.javalite.activejdbc.Model;

/**
 *
 * @author eze
 */
public class Venta extends Model{
    static{
        validatePresenceOf("monto","cliente_id","fecha");
    }

    public Venta() {
        this.productos = null;
    }
   
    //Lista de pares <Producto,cantidad>
    private LinkedList<Pair> productos;

    public LinkedList<Pair> getProductos() {
        return productos;
    }

    public void setProductos(LinkedList<Pair> productos) {
        this.productos = productos;
    }
    
    
}
