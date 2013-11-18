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
public class ProductosVendido extends Model {
    static {
        validatePresenceOf("idventa","idproducto","cantidad");
    }
}
