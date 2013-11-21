/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import org.javalite.activejdbc.Model;

/**
 *
 * @author eze
 */
public class ProductosVentas extends Model {
    static {
        validatePresenceOf("venta_id","producto_id","cantidad","precio_final");
    }
}
