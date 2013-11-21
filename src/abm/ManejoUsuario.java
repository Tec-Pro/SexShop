/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package abm;

import modelos.Usuario;
import org.javalite.activejdbc.Base;
/**
 *
 * @author luciano
 */
public class ManejoUsuario {
    
    public void abrirBase(){
        Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/sexshop","root", "root");
    }
    
    public void crearUsuario(){
        abrirBase();
        if (Usuario.findAll().isEmpty()){
            Usuario nuevo = Usuario.createIt();            
        }
        Base.close();
    }
    
    public void modificarNombre(String nombre){
        abrirBase();
        Usuario u = Usuario.findById(1);
        u.set("nombre",nombre);
        Base.close();
    }
    
      public void modificarPass(String pass){
        abrirBase();
        Usuario u = Usuario.findById(1);
        u.set("pass",pass);
        Base.close();
    }
}
