/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaz;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
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
    private final String logo= "/reporte/logo.png";

    
    public ControladorJReport(String jasper) throws JRException {

        reporte = (JasperReport) JRLoader.loadObject(getClass().getResource("/reporte/"+jasper));//cargo el reporte


    }
    
    //listado de clientes productos y proveedores.
     public void mostrarReporte(Connection connection) throws ClassNotFoundException, SQLException, JRException{    
         Map parametros = new HashMap();
          parametros.clear();   
          parametros.put("logo", this.getClass().getResourceAsStream(logo));
         JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, connection);
          JasperViewer.viewReport( jasperPrint , false );
    }
     

     
    
}
