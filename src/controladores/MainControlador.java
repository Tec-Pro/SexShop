/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import interfaz.AplicacionGui;
import interfaz.PantallaBienvenida;
import java.sql.SQLException;
import javax.swing.JFrame;
import javax.swing.UIManager;
import net.sf.jasperreports.engine.JRException;
import abm.ConnectionDataBase;
import abm.ManejoUsuario;
import interfaz.ComprasRealizadas;
import interfaz.LoginGui;
import interfaz.VentaGui;
import interfaz.VentasRealizadas;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Iterator;
import java.util.LinkedList;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelos.Cliente;
import modelos.Producto;
import modelos.ProductosVentas;
import modelos.Venta;
import org.javalite.activejdbc.Base;
import org.javalite.activejdbc.LazyList;
import org.javalite.activejdbc.Model;

/**
 *
 * @author agustin
 */
public class MainControlador implements ActionListener{
    
     private LoginGui log;
     private ConnectionDataBase cdb;
     private PantallaBienvenida pb;
     private AplicacionGui app;
     private VentasRealizadasControlador vtasRealiz;
     private  ClienteControlador cl;
     private ArticulosControlador ac;
     private VentaControlador ventacont;
     private ProveedoresControlador provContr;
     private CompraControlador compraControlador;
     private ComprasRealizadasControlador compraRealiz;
     private ManejoUsuario mu;
     private char[] pass;
     private String user;
     
     @SuppressWarnings("CallToThreadDumpStack")
     public MainControlador() throws ClassNotFoundException, JRException, SQLException{
        JFrame.setDefaultLookAndFeelDecorated(true); //Le agrego un tema lindo al programa
        try {
            JFrame.setDefaultLookAndFeelDecorated(true);
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }
        pb = new PantallaBienvenida();
        pb.setVisible(true);
        log = new LoginGui();
        app = new AplicacionGui();
        log.setActionListener(this);
        vtasRealiz = new VentasRealizadasControlador(app);
        cl = new ClienteControlador(app);
        ac = new ArticulosControlador(app.getAbmProductoGui());
        compraRealiz = new ComprasRealizadasControlador(app);
        ventacont = new VentaControlador(app.getVenta());
        provContr = new ProveedoresControlador(app);
        compraControlador = new CompraControlador(app.getCompraGui(), app.getComprasRealizadas() );
        log.setVisible(true);
        pb.dispose();
        mu = new ManejoUsuario();
        mu.crearUsuario();
        app.getVentasRealizadas().getModificar().addActionListener(this);
      
     }
    
     public static void main(String[] args) throws InterruptedException, ClassNotFoundException, SQLException, JRException {
         MainControlador mainControlador = new MainControlador();       

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton b = (JButton)e.getSource();
        if(b.equals(log.getLogin())){
            user = log.getUsuario().getText();
            pass = log.getPassword().getPassword();
            if (mu.login(user,pass)){
                log.dispose();
                app.setVisible(true);
            } else{
                    JOptionPane.showMessageDialog(app, "INTENTE NUEVAMENTE", "¡DATOS INCORRECTOS!",JOptionPane.ERROR_MESSAGE);
            }
            
        } 
        if(b.equals(log.getSalir())){
            System.exit(0);
        }
        if(b.equals(app.getVentasRealizadas().getModificar())){
            System.out.println("entre");
            app.getTab().setSelectedIndex(2);
            VentaGui v= app.getVenta();
            VentasRealizadas vr= app.getVentasRealizadas();
            Integer idFac= Integer.valueOf((String)vr.getTablaFacturas().getValueAt( vr.getTablaFacturas().getSelectedRow(),0));
            
            System.out.println("factura:"+idFac);
            abrirBase();
            Venta factura= Venta.findById(idFac);
            Object idCliente= factura.get("cliente_id");
            DefaultTableModel tablita=v.getTablaFacturaDefault();
            tablita.setRowCount(0);
                Cliente cliente= Cliente.findById(idCliente);
                String nombreYApellido= idCliente +" "+ cliente.getString("nombre")+" "+cliente.getString("apellido");
                v.getClienteFactura().setText(nombreYApellido);
                LazyList<ProductosVentas> pr= ProductosVentas.find("venta_id = ?", idFac);
                Iterator<ProductosVentas> it= pr.iterator();
                while(it.hasNext()){
                    ProductosVentas prod= it.next();
                    Producto producto = Producto.findFirst("numero_producto = ?",prod.get("producto_id"));
                    Integer numeroProducto= (Integer)producto.getInteger("numero_producto");
                    String nombre= producto.getString("nombre") + " "+ producto.getString("marca");
                    Float precio= prod.getFloat("precio_final");
                    Integer cantidad= prod.getInteger("cantidad");
                    Object cols[] = new Object[5];
                        cols[0] = numeroProducto;
                        cols[1] = cantidad;
                        cols[2] = nombre;
                        cols[3] = BigDecimal.valueOf(precio).setScale(2, RoundingMode.CEILING);
                        cols[4] = BigDecimal.valueOf(precio*cantidad).setScale(2, RoundingMode.CEILING);
                        v.getTablaFacturaDefault().addRow(cols);
                    }
                v.getTotalFactura().setText(String.valueOf(factura.getFloat("monto")));
                Base.close();
                ventacont.setIdFacturaAModificar(idFac);
                v.getModificar().setEnabled(true);
                v.getRealizarVenta().setEnabled(false);
                ventacont.setCellEditor();
                
       
    }
    }
        
            
    private void abrirBase(){
        if (!Base.hasConnection()){
            Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/sexshop","root", "root");
        }
    }
}
