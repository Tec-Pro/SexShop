/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaz;

import java.sql.SQLException;
import javax.swing.JFrame;
import javax.swing.UIManager;
import net.sf.jasperreports.engine.JRException;

/**
 *
 * @author nico
 */
public class MainControladorTrucho {

    public static void main(String[] args) throws InterruptedException, ClassNotFoundException, SQLException, JRException {
        PantallaBienvenida p = new PantallaBienvenida();
        p.setVisible(true);
        JFrame.setDefaultLookAndFeelDecorated(true); //Le agrego un tema lindo al programa
        try {
            JFrame.setDefaultLookAndFeelDecorated(true);
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }
        AplicacionGui app = new AplicacionGui();
        app.setVisible(true);
        p.dispose();
    }
}
