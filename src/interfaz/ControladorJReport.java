/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaz;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author nico
 */
public class ControladorJReport {
    private JasperReport reporte;

    
    public ControladorJReport(String jasper) throws JRException {

        reporte = (JasperReport) JRLoader.loadObject(getClass().getResource("/reporte/"+jasper));//cargo el reporte


    }
    
     public void mostrarReporte(Connection connection) throws ClassNotFoundException, SQLException, JRException{    
             JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, null, connection);
          JasperViewer.viewReport( jasperPrint , false );
    }
     

}
