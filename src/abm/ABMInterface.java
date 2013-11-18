/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package abm;

import java.sql.Connection;

/**
 *
 * @author joako
 */


interface ABMInterface<T> {
  
    boolean alta(T o);
    
    boolean baja(T o);
    
    boolean modificar(T o);
}
