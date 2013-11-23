/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import abm.*;
import busquedas.busqueda;
import interfaz.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import modelos.Cliente;
import modelos.Producto;
import modelos.Proveedor;
import net.sf.jasperreports.engine.util.Pair;
import org.javalite.activejdbc.Base;
import org.javalite.activejdbc.LazyList;
import org.javalite.activejdbc.Model;

/**
 *
 * @author alan
 */
public class ArticulosControlador implements ActionListener {

    AbmProductoGui prodGui;
    ABMProducto abmProd;
    AplicacionGui apgui;
    modificarPrecios1 mpp;
    private boolean nuevoPulsado;
    private boolean modificarPulsado;
    private int confirmarBorrar;
    private JTextField bn;
    private JTextField bc;
    private JTextField bm;
    private DefaultTableModel tablaProductos;
    private JTable tabla;
    List<Producto> pl;
    private busqueda pb;

    public ArticulosControlador(AbmProductoGui apg) {

        //Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/sexshop", "root", "root");
        pl = new LinkedList<Producto>();
        pb = new busqueda();
        abmProd = new ABMProducto();
        this.prodGui = apg;
        nuevoPulsado = false;
        modificarPulsado = false;
        apg.setActionListener(this);
        bn = prodGui.getBusquedaNombre();
        mpp = new modificarPrecios1(apgui, true);
        bn.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyReleased(java.awt.event.KeyEvent evt) {
                busquedaNombreKeyReleased(evt);
            }
        });
        bc = prodGui.getBusquedaCodigo();
        bc.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyReleased(java.awt.event.KeyEvent evt) {
                busquedaCodigoKeyReleased(evt);
            }
        });
        tabla = prodGui.getTabla();
        tabla.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaMouseClicked(evt);
            }
        });

        bm = prodGui.getBusquedaMarca();
        bm.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyReleased(java.awt.event.KeyEvent evt) {
                busquedaMarcaKeyReleased(evt);
            }
        });

        tablaProductos = prodGui.getTablaArticulos();
        pl = pb.filtroProducto("", "", "");
        actualizarLista();

    }

    public void busquedaNombreKeyReleased(java.awt.event.KeyEvent evt) {


        pl = pb.filtroProducto(bc.getText(), bn.getText(), bm.getText());
        actualizarLista();

    }

    public void busquedaMarcaKeyReleased(java.awt.event.KeyEvent evt) {

        pl = pb.filtroProducto(bc.getText(), bn.getText(), bm.getText());
        actualizarLista();

    }

    public void busquedaCodigoKeyReleased(java.awt.event.KeyEvent evt) {

        pl = pb.filtroProducto(bc.getText(), bn.getText(), bm.getText());
        actualizarLista();

    }

    private void actualizarLista() {
        if (!Base.hasConnection()) {
            Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/sexshop", "root", "root");
        }
        tablaProductos.setRowCount(0);
        Iterator<Producto> it = pl.iterator();
        while (it.hasNext()) {
            Producto a = it.next();
            String row[] = new String[3];
            row[0] = a.getString("numero_producto");
            row[1] = a.getString("nombre");
            row[2] = a.getString("marca");
            tablaProductos.addRow(row);
        }
        Base.close();
    }

    private void agregarFila(Producto c) {
        String row[] = new String[3];
        row[0] = c.getString("numero_producto");
        row[1] = c.getString("nombre");
        row[2] = c.getString("marca");
        tablaProductos.addRow(row);
    }

    public void tablaMouseClicked(java.awt.event.MouseEvent evt) {
        prodGui.getProveedores().removeAllItems();
        abrirBase();
        prodGui.habilitarCampos(false);
        prodGui.getModificar().setEnabled(true);
        prodGui.getBorrar().setEnabled(true);
        prodGui.getGuardar().setEnabled(false);
        nuevoPulsado = false;
        modificarPulsado = false;
        System.out.println("tabla pulsada");
        int r = tabla.getSelectedRow();
        Producto p = Producto.first("numero_producto = ?", tabla.getValueAt(r, 0));
        if (p != null) {
            prodGui.CargarCampos(p);
            Proveedor prov = Proveedor.findById(p.getInteger("proveedor_id"));
            String nom = prov.getString("nombre");
            String cuil = prov.getString("cuil");
            String pr = nom + ";" + cuil;
            prodGui.getProveedores().addItem(pr);
        }
        Base.close();
    }

    private void cargarDatosProd(Producto c, boolean id) {
        try {
            String nombre = TratamientoString.eliminarTildes(prodGui.getNombre().getText()).toUpperCase();
            c.set("nombre", nombre);
        } catch (ClassCastException e) {
            JOptionPane.showMessageDialog(prodGui, "Error en el nombre", "Error!", JOptionPane.ERROR_MESSAGE);
        }
        try {
            String marca = TratamientoString.eliminarTildes(prodGui.getMarca().getText()).toUpperCase();
            c.set("marca", marca);
        } catch (ClassCastException e) {
            JOptionPane.showMessageDialog(prodGui, "Error en la marca", "Error!", JOptionPane.ERROR_MESSAGE);
        }
        try {
            String tipo = TratamientoString.eliminarTildes(prodGui.getTipo().getText()).toUpperCase();
            c.set("tipo", tipo);
        } catch (ClassCastException e) {
            JOptionPane.showMessageDialog(prodGui, "Error en el tipo", "Error!", JOptionPane.ERROR_MESSAGE);
        }
        try {
            Double precioCompra = Double.valueOf(TratamientoString.eliminarTildes(prodGui.getPrecioCompra().getText()).toUpperCase());
            c.set("precio_compra", BigDecimal.valueOf(precioCompra).setScale(2, RoundingMode.CEILING));
        } catch (NumberFormatException | ClassCastException e) {
            JOptionPane.showMessageDialog(prodGui, "Error en precio de compra", "Error!", JOptionPane.ERROR_MESSAGE);
        }
        try {
            Double precioVenta = Double.valueOf(TratamientoString.eliminarTildes(prodGui.getPrecioVenta().getText()).toUpperCase());
            c.set("precio_venta", BigDecimal.valueOf(precioVenta).setScale(2, RoundingMode.CEILING));
        } catch (NumberFormatException | ClassCastException e) {
            JOptionPane.showMessageDialog(prodGui, "Error en precio de venta", "Error!", JOptionPane.ERROR_MESSAGE);
        }

        c.set("stock", prodGui.getStock().getValue());

        try {
            Integer numeroProducto = Integer.valueOf(prodGui.getIdArticulo().getText());
            c.set("numero_producto", numeroProducto);
        } catch (NumberFormatException | ClassCastException e) {
            JOptionPane.showMessageDialog(prodGui, "Error en el numero de producto", "Error!", JOptionPane.ERROR_MESSAGE);
        }

        //if(id) c.setId(TratamientoString.eliminarTildes(prodGui.getIdArticulo().getText()).toUpperCase());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == prodGui.getModificar()) {
            prodGui.getGuardar().setEnabled(true);
            modificarPulsado = true;
            nuevoPulsado = false;
            prodGui.habilitarCampos(true);
            prodGui.getIdArticulo().setEditable(false);
            prodGui.getProveedores().removeAllItems();
            abrirBase();
            int r = tabla.getSelectedRow();
            if(r>-1){
            Producto p = Producto.first("numero_producto = ?", tabla.getValueAt(r, 0));
            
            LazyList<Proveedor> l = Proveedor.findAll();
            Iterator<Proveedor> it = l.iterator();
            while (it.hasNext()) {
                Proveedor prov = it.next();
                String nom = prov.getString("nombre");
                String cuil = prov.getString("cuil");
                String prove = nom + ";" + cuil;
                prodGui.getProveedores().addItem(prove);
            }
            if (p != null) {
                prodGui.CargarCampos(p);
                Proveedor prov = Proveedor.findById(p.getInteger("proveedor_id"));
                String nom = prov.getString("nombre");
                String cuil = prov.getString("cuil");
                String pr = nom + ";" + cuil;
                prodGui.getProveedores().setSelectedItem(pr);
            }
        }
            Base.close();

            //Agregar al combo todos los proveedores!
        }
        if (e.getSource() == prodGui.getNuevo()) {
            prodGui.getGuardar().setEnabled(true);
            nuevoPulsado = true;
            modificarPulsado=false;
            prodGui.getModificar().setEnabled(false);
            prodGui.getBorrar().setEnabled(false);
            prodGui.limpiarCampos();
            prodGui.habilitarCampos(true);
            prodGui.getProveedores().removeAllItems();
            abrirBase();
            LazyList<Proveedor> l = Proveedor.findAll();
            Iterator<Proveedor> it = l.iterator();
            while (it.hasNext()) {
                Proveedor prov = it.next();
                String nom = prov.getString("nombre");
                String cuil = prov.getString("cuil");
                String prove = nom + ";" + cuil;
                prodGui.getProveedores().addItem(prove);
            }
            Base.close();
            //Agregar al combo todos los proveedores
        }
        if (e.getSource() == prodGui.getGuardar() && modificarPulsado) {
            String id = prodGui.getIdArticulo().getText();
            abrirBase();
            Producto p = Producto.first("numero_producto = ?", id);
            cargarDatosProd(p, false);
            Base.close();
            String prov = (String) prodGui.getProveedores().getSelectedItem();
            String[] proveedor = prov.split(";");
            p.setCuilProveedor(proveedor[1]);
            // p.set("priveedor_id");
            abrirBase();
            Proveedor proveed = Proveedor.first("cuil = ?", proveedor[1]);
            p.set("proveedor_id", proveed.get("id"));
            if (abmProd.modificar(p)) {
                JOptionPane.showMessageDialog(apgui, "Producto modificado exitosamente.");
                pl = pb.filtroProducto("", "", "");
                actualizarLista();
                modificarPulsado = false;
                prodGui.habilitarCampos(false);
                prodGui.getModificar().setEnabled(false);
                prodGui.getGuardar().setEnabled(false);
            } else {
                JOptionPane.showMessageDialog(prodGui, "El número de producto ya existe");
            }
        }
        if (e.getSource() == prodGui.getGuardar() && nuevoPulsado) {
            Producto p = new Producto();
            cargarDatosProd(p, false);
            String prov = (String) prodGui.getProveedores().getSelectedItem();
            String[] proveedor = prov.split(";");
            p.setCuilProveedor(proveedor[1]);
            if (p.getString("nombre").equals("") || p.getString("marca").equals("")) {
                JOptionPane.showMessageDialog(prodGui, "Un producto debe tener nombre y marca");
            }
            abrirBase();
            if (abmProd.alta(p)) {
                JOptionPane.showMessageDialog(prodGui, "Producto registrado exitosamente");
                p = abmProd.getProducto(p);
                agregarFila(p);
                nuevoPulsado = false;
                prodGui.habilitarCampos(false);
                prodGui.getGuardar().setEnabled(false);

            } else {
                JOptionPane.showMessageDialog(prodGui, "Producto existente");
            }
            Base.close();
        }


        if (e.getSource() == prodGui.getBorrar()) {
            confirmarBorrar = JOptionPane.showConfirmDialog(prodGui, "¿borrar producto?", "Confirmar Borrado", JOptionPane.YES_NO_OPTION);
            if (JOptionPane.OK_OPTION == confirmarBorrar) {
                abrirBase();
                Producto p = Producto.first("numero_producto = ?", prodGui.getIdArticulo().getText());
                if (abmProd.baja(p)) {
                    JOptionPane.showMessageDialog(prodGui, "Producto borrado exitosamente");
                    pl = pb.filtroProducto("", "", "");
                    actualizarLista();
                    prodGui.limpiarCampos();
                    prodGui.getBorrar().setEnabled(false);
                    prodGui.getModificar().setEnabled(false);
                    prodGui.getGuardar().setEnabled(false);

                } else {
                    JOptionPane.showMessageDialog(prodGui, "No se ha borrado el producto");
                }
            }
        }
        if (e.getSource() == prodGui.getAnterior()) {
            prodGui.getProveedores().removeAllItems();
            int r = tabla.getSelectedRow();
            if (r > 0) {
                tabla.changeSelection(tabla.getSelectedRow() - 1, 0, false, false);
                r--;
                abrirBase();
                Producto c = Producto.first("numero_producto =?", tabla.getValueAt(r, 0));
                Proveedor p = Proveedor.first("id = ?", c.getString("proveedor_id"));
                Base.close();

                prodGui.CargarCampos(c);
                String nom = p.getString("nombre");
                String cuil = p.getString("cuil");
                String pr = nom + ";" + cuil;
                prodGui.getProveedores().addItem(pr);
                prodGui.repaint();

            }
        }

        if (e.getSource() == prodGui.getSiguiente()) { //permite avanzar al siguiente cliente de la lista
            prodGui.getProveedores().removeAllItems();
            int r = tabla.getSelectedRow();
            if (tablaProductos.getRowCount() - 1 > r) {
                tabla.changeSelection(r + 1, 0, false, false);
                r++;
                abrirBase();
                Producto c = Producto.first("numero_producto =?", tabla.getValueAt(r, 0));
                Proveedor p = Proveedor.first("id = ?", c.getString("proveedor_id"));
                Base.close();
                prodGui.CargarCampos(c);
                String nom = p.getString("nombre");
                String cuil = p.getString("cuil");
                String pr = nom + ";" + cuil;
                prodGui.getProveedores().addItem(pr);
            }
        }

        if (e.getSource() == prodGui.getModificarPrecios()) {
            DefaultTableModel t = mpp.getTablaArticulos();
            t.setRowCount(0);
            abrirBase();
            LazyList<Producto> prod = Producto.findAll();
            Iterator<Producto> it = prod.iterator();
            while (it.hasNext()) {
                Producto a = it.next();
                Object row[] = new Object[7];
                row[0] = a.getString("numero_producto");
                row[1] = a.getString("nombre");
                row[2] = a.getString("marca");
                row[3] = a.getString("tipo");
                row[4] = a.getString("precio_compra");
                row[5] = BigDecimal.valueOf(Double.valueOf(a.getString("precio_venta")));
                row[6] = BigDecimal.valueOf(Double.valueOf(a.getString("precio_venta")));
                t.addRow(row);
            }
            Base.close();
            mpp.setLocationRelativeTo(prodGui);
            mpp.setVisible(true);
            if (mpp.getReturnStatus() == 1) {
                Iterator<Pair> list = mpp.getProductosModificados().iterator();
                while (list.hasNext()) {
                    Pair par = list.next();
                    abrirBase();
                    Producto p = Producto.findFirst("numero_producto =?", (String) par.first());
                    p.set("precio_venta", par.second());
                    if (p.saveIt()) {
                        JOptionPane.showMessageDialog(prodGui, "Precios modificados exitosamente", "Precios modificados", JOptionPane.INFORMATION_MESSAGE);
                    }
                    Base.close();
                }

            }


        }
        /*if(e.getSource() == mpp.getAceptar()){
         int srow = prodGui.getTabla().getSelectedRow();
         Object id = prodGui.getTablaArticulos().getValueAt(srow, 0);
         Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/sexshop", "root", "root");
         Producto p = Producto.first("id = ?", id);
         p.set("precio_venta", mpp.getPesos().getText());
         p.saveIt();
         Base.close();
         mpp.dispose();
         JOptionPane.showMessageDialog(apgui, "Precio modificado exitosamente.");
            
         }
         if(e.getSource() == mpp.getCancelar()){
         mpp.dispose();
         }
         */
    }

    private void abrirBase() {
        if (!Base.hasConnection()) {
            Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/sexshop", "root", "root");
        }
    }
}
