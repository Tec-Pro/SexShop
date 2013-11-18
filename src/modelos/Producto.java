/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import org.javalite.activejdbc.Model;

/**
 *
 * @author joako
 */
public class Producto extends Model {
    private String cuilProveedor;

    /**
     * @return the cuilProveedor
     */
    public String getCuilProveedor() {
        return cuilProveedor;
    }

    /**
     * @param cuilProveedor the cuilProveedor to set
     */
    public void setCuilProveedor(String cuilProveedor) {
        this.cuilProveedor = cuilProveedor;
    }
    
}
