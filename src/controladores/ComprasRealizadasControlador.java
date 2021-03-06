/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import abm.ABMCompra;
import busquedas.busqueda;
import com.toedter.calendar.JDateChooser;
import interfaz.AplicacionGui;
import interfaz.ComprasRealizadas;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import modelos.Compra;
import modelos.Producto;
import modelos.ProductosCompras;
import modelos.Proveedor;
import org.javalite.activejdbc.Base;

/**
 *
 * @author agustin
 */
public class ComprasRealizadasControlador implements ActionListener {

    private AplicacionGui apliGui;
    private ComprasRealizadas comprasGui;
    private JTextField filtNomb;
    private JTextField filtCuil;
    private JTextField filtId;
    private JDateChooser calenDesde;
    private JDateChooser calenHasta;
    private String dateDesde;
    private String dateHasta;
    private DefaultTableModel modelCompras;
    private DefaultTableModel modelCompra;
    private JTable tablaCompras;
    private JTable tablaCompra;
    private busqueda buscar;
    private List<Proveedor> provList;
    private List<Compra> compraList;
    private List<ProductosCompras> prodCompras;
    private ABMCompra abmCompra;

    public ComprasRealizadasControlador(AplicacionGui app) {
        abrirBase();
        apliGui = app;
        comprasGui = apliGui.getComprasRealizadas();
        comprasGui.setActionListener(this);
        filtNomb = comprasGui.getFiltroNombre();
        filtCuil = comprasGui.getFiltroCuil();
        filtId = comprasGui.getFiltroId();
        calenDesde = comprasGui.getCalendarioDesde();
        calenHasta = comprasGui.getCalendarioHasta();
        tablaCompras = comprasGui.getTablaCompras();
        modelCompras = comprasGui.getTablaComprasDefault();
        tablaCompra = comprasGui.getTablaCompra();
        modelCompra = comprasGui.getTablaCompraDefault();
        dateDesde = "0-0-0";
        dateHasta = "9999-0-0";
        buscar = new busqueda();
        abmCompra = new ABMCompra();


        tablaCompras.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tablaComprasMouseReleased(evt);
            }
        });


        filtNomb.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyReleased(java.awt.event.KeyEvent evt) {
                filtroNombreKeyReleased(evt);
            }
        });
        filtCuil.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyReleased(java.awt.event.KeyEvent evt) {
                filtroCuilKeyReleased(evt);
            }
        });
        filtId.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyReleased(java.awt.event.KeyEvent evt) {
                filtroIdKeyReleased(evt);
            }
        });
        calenDesde.getJCalendar().addPropertyChangeListener("calendar", new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent e) {
                calenDesdePropertyChange(e);
            }
        });
        calenHasta.getJCalendar().addPropertyChangeListener("calendar", new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent e) {
                calenHastaPropertyChange(e);
            }
        });
                comprasGui.getCalenDesdeText().addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyReleased(java.awt.event.KeyEvent evt) {
                dateDesde=comprasGui.getCalenDesdeText().getText();
                abrirBase();
                actualizarListaCompras();
                if(Base.hasConnection()){
                    Base.close();
                }
                
            }
        });
                comprasGui.getCalenHastaText().addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyReleased(java.awt.event.KeyEvent evt) {
                dateHasta=comprasGui.getCalenHastaText().getText();
                abrirBase();
                actualizarListaCompras();
                if(Base.hasConnection()){
                    Base.close();
                }
                
            }
        });
        provList = buscar.filtroProveedor("", "", "");
        actualizarListaCompras();
        if (Base.hasConnection())
        Base.close();

    }

    public void actualizarListaCompras() {
        abrirBase();
        modelCompras.setRowCount(0);
        for (Proveedor p : provList) {
            compraList = buscar.filtroCompra(p.getId().toString(), dateDesde, dateHasta);

            for (Compra c : compraList) {
                String row[] = new String[3];
                row[0] = c.getId().toString();
                row[1] = p.get("nombre") + " " + p.get("apellido");
                row[2] = c.get("fecha").toString();
                modelCompras.addRow(row);
            }
        }
        comprasGui.getModificar().setEnabled(false);
        comprasGui.getDevolucion().setEnabled(false);
        comprasGui.getEliminar().setEnabled(false);
        if (Base.hasConnection())
            Base.close();
    }

    private void actualizarCompra() {
        abrirBase();
        modelCompra.setRowCount(0);
        for (ProductosCompras pv : prodCompras) {
            Object row[] = new Object[4];
            row[0] = Integer.parseInt(pv.get("cantidad").toString());
            Producto p = Producto.findFirst("numero_producto = ?",pv.get("producto_id"));
            row[1] = p.get("nombre") + ", " + p.get("marca");
            Float a = Float.parseFloat(pv.get("precio_final").toString());
            row[2] = a;
            row[3] = Integer.parseInt(pv.get("cantidad").toString()) * a;
            modelCompra.addRow(row);
        }
        setTotal();
        if (Base.hasConnection())
            Base.close();
    }

    private void setTotal() {
        Float total = Float.parseFloat("0.0");
        BigDecimal totalBig = BigDecimal.ZERO;

        for (int i = 0; i < tablaCompra.getRowCount(); i++) {
            total += Float.parseFloat(modelCompra.getValueAt(i, 3).toString());
            totalBig=BigDecimal.valueOf(total.doubleValue()).setScale(2, RoundingMode.CEILING);
        }
        comprasGui.getTotalCompra().setText(totalBig.toPlainString() );
    }

    public void calenDesdePropertyChange(PropertyChangeEvent e) {
        final Calendar c = (Calendar) e.getNewValue();

        dateDesde = c.get(Calendar.YEAR) + "-" + c.get(Calendar.MONTH) + "-" + c.get(Calendar.DATE);
        abrirBase();
        actualizarListaCompras();
        if (Base.hasConnection())
        Base.close();
    }

    public void calenHastaPropertyChange(PropertyChangeEvent e) {
        final Calendar c = (Calendar) e.getNewValue();
        dateHasta = c.get(Calendar.YEAR) + "-" + c.get(Calendar.MONTH) + "-" + c.get(Calendar.DATE);
        abrirBase();
        actualizarListaCompras();
        if (Base.hasConnection())
        Base.close();
    }

    public void filtroNombreKeyReleased(java.awt.event.KeyEvent evt) {
        abrirBase();
        provList = buscar.filtroProveedor(filtCuil.getText(), filtNomb.getText(), filtId.getText());
        actualizarListaCompras();
        if (Base.hasConnection())
        Base.close();
    }

    public void filtroCuilKeyReleased(java.awt.event.KeyEvent evt) {
        abrirBase();
        provList = buscar.filtroProveedor(filtCuil.getText(), filtNomb.getText(), filtId.getText());
        actualizarListaCompras();
        if (Base.hasConnection())
        Base.close();
    }

    public void filtroIdKeyReleased(java.awt.event.KeyEvent evt) {
        abrirBase();
        provList = buscar.filtroProveedor(filtCuil.getText(), filtNomb.getText(), filtId.getText());
        actualizarListaCompras();
        if (Base.hasConnection())
        Base.close();

    }

    public void tablaComprasMouseReleased(java.awt.event.MouseEvent evt) {
        comprasGui.getModificar().setEnabled(true);
        comprasGui.getDevolucion().setEnabled(true);
        comprasGui.getEliminar().setEnabled(true);
        abrirBase();
        int r = tablaCompras.getSelectedRow();
        //Proveedor  = Proveedor.findById(tablaCompras.getValueAt(r, 0));
        comprasGui.getProveedorCompra().setText(tablaCompras.getValueAt(r, 1).toString());
        comprasGui.getCalendarioCompra().setDate(Date.valueOf(tablaCompras.getValueAt(r, 2).toString()));
        if (r > -1) {
            prodCompras = buscar.filtroComprados(tablaCompras.getValueAt(r, 0).toString(), "");
        }
        actualizarCompra();
        if (Base.hasConnection())
        Base.close();

    }

    private void abrirBase() {
        if (!Base.hasConnection()) {
            Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/sexshop", "root", "root");
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton b = (JButton) e.getSource();
        if (b.equals(comprasGui.getEliminar())) {
            int r = tablaCompras.getSelectedRow();
            if (r < 0) {
                JOptionPane.showMessageDialog(comprasGui, "No hay ninguna compra seleccionada");
                return;
            }
            int confirmarBorrar = JOptionPane.showConfirmDialog(comprasGui, "¿borrar Compra?", "Confirmar Borrado", JOptionPane.YES_NO_OPTION);
            if (JOptionPane.OK_OPTION == confirmarBorrar) {
                abrirBase();
                Compra c = new Compra();
                c.setId(modelCompras.getValueAt(r, 0));
                abmCompra.baja(c);
                actualizarListaCompras();
                comprasGui.limpiarCompra();
                JOptionPane.showMessageDialog(comprasGui, "Compra borrada exitosamente");
                if (Base.hasConnection())
                Base.close();
            }
        }
        if (b.equals(comprasGui.getDevolucion())) {
            int r = tablaCompras.getSelectedRow();
            if (r < 0) {
                JOptionPane.showMessageDialog(comprasGui, "No hay ninguna compra seleccionada");
                return;
            }
            int confirmarBorrar = JOptionPane.showConfirmDialog(comprasGui, "¿cancelar compra?", "Confirmar Borrado", JOptionPane.YES_NO_OPTION);
            if (JOptionPane.OK_OPTION == confirmarBorrar) {
                abrirBase();
                Compra c = new Compra();
                c.setId(modelCompras.getValueAt(r, 0));
                if (abmCompra.bajaConDevolucion(c)) {
                    JOptionPane.showMessageDialog(comprasGui, "Compra cancelada exitosamente");
                    comprasGui.limpiarCompra();
                    actualizarListaCompras();
                } else {
                    JOptionPane.showMessageDialog(comprasGui, "Error al cancelar compra");
                }
                if (Base.hasConnection())
                Base.close();
            }
        }
    }
}
