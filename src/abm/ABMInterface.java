/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package abm;

/**
 *
 * @author joako
 */
interface ABMInterface<T> {
    
    void alta(T o);
    
    void baja(T o);
    
    void modificar(T o);
}
