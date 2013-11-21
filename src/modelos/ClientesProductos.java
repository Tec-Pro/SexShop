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
public class ClientesProductos extends Model{
    static{
        validatePresenceOf("producto_id","cliente_id","cantidad");
    }
}
