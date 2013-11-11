/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaz;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author nico
 */
public class AplicacionGui extends javax.swing.JFrame implements ActionListener {

    private AbmClienteGui abmCliente; //Panel abmCliente
    private AbmProductoGui abmProducto; //Panel abmProducto
    
    //estos deben estar en el controlador
    ArticulosCompradosGui art;
    ListadoClientesGui listadoCliente;
    ListadoArticulosGui listadoArticulos;
    ModificarPrecioGui modificarPrecioGui;

    /**
     * Creates new form AplicacionGui
     */
    public AplicacionGui() {
        initComponents();
        abmCliente = new AbmClienteGui(); //creo el panel de abmClente
        abmProducto = new AbmProductoGui();//creo panel abmProducto
        tab.add("Cliente", abmCliente);//se los agrego al contenedor de tabs
        tab.add("Producto", abmProducto);
        JFrame.setDefaultLookAndFeelDecorated(true); //Le agrego un tema lindo al programa
        try {
            JFrame.setDefaultLookAndFeelDecorated(true);
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }

        ProbarListas();
    }

    //borrar despueś, es para probar como se ven las listas
    public void ProbarListas() {
        abmCliente.getArticulosComprados().addActionListener(this);
        for (int i = 4000; i < 4030; i++) {
            abmCliente.getTablaClientes().addRow(new Object[]{i, "Nicolas", "Column 3"});

        }
        art = new ArticulosCompradosGui(this, true);
        for (int i = 4000; i < 4030; i++) {
            art.getTablaProductosComprados().addRow(new Object[]{i, "Nicolas", "Column 3", "asd"});
        }
        listarClientes.addActionListener(this);
        listadoCliente = new ListadoClientesGui(this, true);
        for (int i = 4000; i < 4030; i++) {
            listadoCliente.getListadoClientes().addRow(new Object[]{i, "Orcasitas", "Nicolas", "12341", "12351", "nico.orcasitas@gmail.com"});
        }
        listarArticulos.addActionListener(this);
        listadoArticulos = new ListadoArticulosGui(this, true);
        for (int i = 4000; i < 4030; i++) {
            listadoArticulos.getListadoArticulos().addRow(new Object[]{i, "nombre", "tipo", "marca", 123.13, "proveedor", 123});
        }
                for (int i = 4000; i < 4030; i++) {
            abmProducto.getTablaArticulos().addRow(new Object[]{i, "Articulo", "Marca"});

        }
                modificarPrecioGui= new ModificarPrecioGui(this, true);
                abmProducto.getModificarPrecio().addActionListener(this);
                
                
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tab = new javax.swing.JTabbedPane();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        listar = new javax.swing.JMenu();
        listarClientes = new javax.swing.JMenuItem();
        listarArticulos = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Programa");
        setLocationByPlatform(true);
        setPreferredSize(new java.awt.Dimension(879, 488));
        getContentPane().add(tab, java.awt.BorderLayout.CENTER);

        jMenu1.setText("File");
        jMenuBar1.add(jMenu1);

        listar.setText("Listado");

        listarClientes.setText("Listado Clientes");
        listarClientes.setToolTipText("Listado de todos los clientes");
        listarClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                listarClientesActionPerformed(evt);
            }
        });
        listar.add(listarClientes);

        listarArticulos.setText("Listado Articulos");
        listarArticulos.setToolTipText("Listado de todos los artículos");
        listarArticulos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                listarArticulosActionPerformed(evt);
            }
        });
        listar.add(listarArticulos);

        jMenuBar1.add(listar);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    //borrar después
    private void listarClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_listarClientesActionPerformed
        listadoCliente.setLocationRelativeTo(this);
        listadoCliente.setVisible(true);

    }//GEN-LAST:event_listarClientesActionPerformed

    //borrar después
    private void listarArticulosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_listarArticulosActionPerformed
        listadoArticulos.setLocationRelativeTo(this);
        listadoArticulos.setVisible(true);
    }//GEN-LAST:event_listarArticulosActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(AplicacionGui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AplicacionGui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AplicacionGui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AplicacionGui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AplicacionGui().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenu listar;
    private javax.swing.JMenuItem listarArticulos;
    private javax.swing.JMenuItem listarClientes;
    private javax.swing.JTabbedPane tab;
    // End of variables declaration//GEN-END:variables

    
    //borrar después, son pruebas
    @Override
    public void actionPerformed(ActionEvent ae) {
        if (abmCliente.getArticulosComprados() == ae.getSource()) {
            art.setLocationRelativeTo(abmCliente);
            art.setVisible(true);
        }
        if(abmProducto.getModificarPrecio()==ae.getSource()){
            modificarPrecioGui.setVisible(true);
            int[] seleccionados=abmProducto.getTabla().getSelectedRows();
            for (int i=0;i<seleccionados.length;i++){
                 System.out.println(abmProducto.getTablaArticulos().getValueAt(seleccionados[i], 0).toString());
            }
                    
        }
    }
}
