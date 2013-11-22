/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaz;

import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import modelos.Producto;

/**
 *
 * @author nico
 */
public class AbmProductoGui extends javax.swing.JPanel {

    DefaultTableModel tablaArticulos; //Tabla Default para tener las opciones de instar y elimnar filas
   // JPopupMenu popUpMenuTabla;         //popUp sobre la tabla al hacer click derecho
    //JMenuItem modificarPrecioPorcentaje;          //menu del popUp
    //JMenuItem modificarPrecioPesos;

    /**
     * Creates new form AbmProductoGui
     */
    public AbmProductoGui() {
        initComponents();
        tablaArticulos = (DefaultTableModel) tabla.getModel(); //convierto la tabla
       // popUpMenu();    //inicializo el  popUp
       // tabla.setComponentPopupMenu(popUpMenuTabla);//agrego el popUp a la tabla
    }

    /**
     * Constructor para el menu emergente que se encuentra en la tabla al hacer
     * click derecho
     *
     * @param
     * @return void
     * @exception
     */
  /*  private void popUpMenu() {
        popUpMenuTabla = new JPopupMenu();
        modificarPrecioPorcentaje = new JMenuItem("Modificar precio de venta en %");
        popUpMenuTabla.add(modificarPrecioPorcentaje);
        modificarPrecioPesos = new JMenuItem("Modificar precio de venta en $");
        popUpMenuTabla.add(modificarPrecioPesos);
    }*/

    /**
     * Setea los actionListener para los botones guardar, borrar, nuevo,
     * siguiente, anterior, modificar, modificarPrecioPorcentaje,
     * modificarPrecioPesos
     *
     * @param ActionListener lis
     * @return void
     * @exception
     */
    public void setActionListener(ActionListener lis) {
        this.guardar.addActionListener(lis);
        this.borrar.addActionListener(lis);
        this.nuevo.addActionListener(lis);
        this.siguiente.addActionListener(lis);
        this.anterior.addActionListener(lis);
        this.modificar.addActionListener(lis);
        this.modificarPrecios.addActionListener(lis);
        //this.modificarPrecioPorcentaje.addActionListener(lis);
        //this.modificarPrecioPesos.addActionListener(lis);
    }

    /**
     * Habilita los campos para la edicion de un producto
     *
     * @param boolean habilitado
     * @return void
     * @exception
     */
    public void habilitarCampos(boolean b) {
        marca.setEditable(b);
        stock.setEditable(b);
        tipo.setEditable(b);
        idArticulo.setEditable(b);
        nombre.setEditable(b);
        precioCompra.setEditable(b);
        precioVenta.setEditable(b);
        proveedores.setEditable(b);
    }

    public JButton getModificarPrecios() {
        return modificarPrecios;
    }

    /**
     * Limpia los campos de la ventana
     *
     * @param
     * @return void
     * @exception
     */
    public void limpiarCampos() {
        marca.setText("");
        stock.setText("");
        tipo.setText("");
        idArticulo.setText("");
        nombre.setText("");
        precioCompra.setText("");
        precioVenta.setText("");
    }
    
    public void CargarCampos(Producto p) {
        marca.setText(p.getString("marca"));
        stock.setText(p.getString("stock"));
        tipo.setText(p.getString("tipo"));
        idArticulo.setText(p.getString("numero_producto"));
        nombre.setText(p.getString("nombre"));
        precioCompra.setText(p.getString("precio_compra"));
        precioVenta.setText(p.getString("precio_venta"));
    }

    /**
     * Retorna la tabla Articulos de tipo DefaultTableModel para poder realizar
     * inserciones y eliminaciones de filas facilmente
     *
     * @param
     * @return DefaultTableModel
     * @exception
     */
    public DefaultTableModel getTablaArticulos() {
        return tablaArticulos;
    }

    /**
     * Retorna el campo busquedacodigo para filtrar las busquedas por codigo del
     * producto
     *
     * @param
     * @return JTextField
     * @exception
     */
    public JTextField getBusquedaCodigo() {
        return busquedaCodigo;
    }

    /**
     * Retorno el campo busquedaMarca para filtrar las busquedas por marca del
     * producto
     *
     * @param
     * @return JTextField
     * @exception
     */
    public JTextField getBusquedaMarca() {
        return busquedaMarca;
    }

    /**
     * retorno el campo busquedaNombre para poder filtrar busquedas por nombre
     * del procuto
     *
     * @param
     * @return
     * @exception
     */
    public JTextField getBusquedaNombre() {
        return busquedaNombre;
    }

    /**
     * retorno el campo idArticulo
     *
     * @param
     * @return JTextField
     * @exception
     */
    public JTextField getIdArticulo() {
        return idArticulo;
    }

    /**
     * retorno el campo marca de un producto
     *
     * @param
     * @return JTextField
     * @exception
     */
    public JTextField getMarca() {
        return marca;
    }

    /**
     * Retorno el campo nombre de un producto
     *
     * @param
     * @return JTextField
     * @exception
     */
    public JTextField getNombre() {
        return nombre;
    }

    /**
     * retorno combo proveedores para obtener el proveedor seleccionado de un
     * producto
     *
     * @param
     * @return JtextField
     * @exception
     */
    public JComboBox getProveedores() {
        return proveedores;
    }

    /**
     * Retorno el campo stock de un producto
     *
     * @param
     * @return JTextField
     * @exception
     */
    public JTextField getStock() {
        return stock;
    }

    /**
     * Retorno el campo tipo de un producto
     *
     * @param
     * @return JTextField
     * @exception
     */
    public JTextField getTipo() {
        return tipo;
    }

    /**
     * Retorno el boton anterior
     *
     * @param
     * @return JButton
     * @exception
     */
    public JButton getAnterior() {
        return anterior;
    }

    /**
     * Retorno el boton borrar
     *
     * @param
     * @return JButton
     * @exception
     */
    public JButton getBorrar() {
        return borrar;
    }

    /**
     * Retorno el boton guardar
     *
     * @param
     * @return JButton
     * @exception
     */
    public JButton getGuardar() {
        return guardar;
    }

    /**
     * Retorno el boton modificar
     *
     * @param
     * @return JButton
     * @exception
     */
    public JButton getModificar() {
        return modificar;
    }

    /**
     * Retorno el boton nuevo
     *
     * @param
     * @return JButton
     * @exception
     */
    public JButton getNuevo() {
        return nuevo;
    }

    /**
     * Retorno el boton siguiente
     *
     * @param
     * @return JButton
     * @exception
     */
    public JButton getSiguiente() {
        return siguiente;
    }

    /**
     * Retorno el menuItem modificarPrecioPorcentaje del popUpMenu
     *
     * @param
     * @return JMenuItem
     * @exception
     */
    /*public JMenuItem getModificarPrecioPorcentaje() {
        return modificarPrecioPorcentaje;
    }*/

    /**
     * Retorno el menuItem modificarPrecioPesos del popUpMenu
     *
     * @param
     * @return JMenuItem
     * @exception
     */
   /* public JMenuItem getModificarPrecioPesos() {
        return modificarPrecioPesos;
    }*/

    /**
     * Retorno el campo precioCompra
     *
     * @param
     * @return JTextField
     * @exception
     */
    public JTextField getPrecioCompra() {
        return precioCompra;
    }

    /**
     * Retorno el campo precioVenta
     *
     * @param
     * @return JTextField
     * @exception
     */
    public JTextField getPrecioVenta() {
        return precioVenta;
    }

    /**
     * retorno la tabla original (se usa solo para ver los seleccionados, en
     * otros casos se debe usar la default)
     *
     * @param
     * @return JTable
     * @exception
     */
    public JTable getTabla() {
        return tabla;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        fondoImagen = new javax.swing.JPanel();
        panelTitulo = new org.edisoncor.gui.panel.PanelImage();
        titulo = new javax.swing.JLabel();
        panelControlArticulo = new javax.swing.JPanel();
        nuevo = new javax.swing.JButton();
        modificar = new javax.swing.JButton();
        borrar = new javax.swing.JButton();
        guardar = new javax.swing.JButton();
        anterior = new javax.swing.JButton();
        siguiente = new javax.swing.JButton();
        panelArticulo = new javax.swing.JPanel();
        labelCodigo = new javax.swing.JLabel();
        idArticulo = new javax.swing.JTextField();
        nombre = new javax.swing.JTextField();
        labelNombre = new javax.swing.JLabel();
        labelMarca = new javax.swing.JLabel();
        marca = new javax.swing.JTextField();
        labelProveedor = new javax.swing.JLabel();
        precioCompra = new javax.swing.JTextField();
        tipo = new javax.swing.JTextField();
        labelTipo = new javax.swing.JLabel();
        labelPrecio = new javax.swing.JLabel();
        proveedores = new javax.swing.JComboBox();
        labelStock = new javax.swing.JLabel();
        stock = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        precioVenta = new javax.swing.JTextField();
        panelArticulos = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        labelBusquedaCodigo = new javax.swing.JLabel();
        labelBusquedaNombre = new javax.swing.JLabel();
        labelBusquedaMarca = new javax.swing.JLabel();
        busquedaCodigo = new javax.swing.JTextField();
        busquedaNombre = new javax.swing.JTextField();
        busquedaMarca = new javax.swing.JTextField();
        modificarPrecios = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(825, 407));

        jScrollPane1.setPreferredSize(new java.awt.Dimension(822, 402));

        fondoImagen.setPreferredSize(new java.awt.Dimension(820, 400));

        panelTitulo.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        panelTitulo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaz/fondoCliente2.jpg"))); // NOI18N
        panelTitulo.setPreferredSize(new java.awt.Dimension(329, 49));

        titulo.setFont(new java.awt.Font("Century Schoolbook L", 3, 24)); // NOI18N
        titulo.setText("CONTROL DE ARTICULOS");
        panelTitulo.add(titulo);

        panelControlArticulo.setLayout(new java.awt.GridLayout(1, 0));

        nuevo.setBackground(javax.swing.UIManager.getDefaults().getColor("Button.background"));
        nuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaz/Icons/agregar.png"))); // NOI18N
        nuevo.setToolTipText("Articulo nuevo");
        nuevo.setPreferredSize(new java.awt.Dimension(70, 70));
        panelControlArticulo.add(nuevo);

        modificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaz/Icons/modificar.png"))); // NOI18N
        modificar.setToolTipText("Modificar articulo");
        modificar.setPreferredSize(new java.awt.Dimension(70, 70));
        panelControlArticulo.add(modificar);

        borrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaz/Icons/borrar.png"))); // NOI18N
        borrar.setToolTipText("Borrar articulo");
        borrar.setPreferredSize(new java.awt.Dimension(70, 70));
        panelControlArticulo.add(borrar);

        guardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaz/Icons/guardar.png"))); // NOI18N
        guardar.setToolTipText("Guardar articulo");
        guardar.setPreferredSize(new java.awt.Dimension(70, 70));
        panelControlArticulo.add(guardar);

        anterior.setIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaz/Icons/anterior.png"))); // NOI18N
        anterior.setToolTipText("Articulo anterior");
        anterior.setPreferredSize(new java.awt.Dimension(70, 70));
        panelControlArticulo.add(anterior);

        siguiente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaz/Icons/siguiente.png"))); // NOI18N
        siguiente.setToolTipText("Proximo articulo");
        siguiente.setPreferredSize(new java.awt.Dimension(70, 70));
        panelControlArticulo.add(siguiente);

        panelArticulo.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Datos del artículo", 0, 0, new java.awt.Font("Century Schoolbook L", 3, 18))); // NOI18N
        panelArticulo.setPreferredSize(new java.awt.Dimension(549, 279));

        labelCodigo.setBackground(new java.awt.Color(137, 98, 59));
        labelCodigo.setFont(new java.awt.Font("Century Schoolbook L", 0, 15)); // NOI18N
        labelCodigo.setText("Código");
        labelCodigo.setBorder(null);

        idArticulo.setEditable(false);
        idArticulo.setToolTipText("ID articulo");

        nombre.setEditable(false);
        nombre.setToolTipText("Nombre del articulo");

        labelNombre.setBackground(new java.awt.Color(137, 98, 59));
        labelNombre.setFont(new java.awt.Font("Century Schoolbook L", 0, 15)); // NOI18N
        labelNombre.setText("Nombre");
        labelNombre.setBorder(null);

        labelMarca.setBackground(new java.awt.Color(137, 98, 59));
        labelMarca.setFont(new java.awt.Font("Century Schoolbook L", 0, 15)); // NOI18N
        labelMarca.setText("Marca");
        labelMarca.setBorder(null);

        marca.setEditable(false);
        marca.setToolTipText("Marca del articulo");

        labelProveedor.setBackground(new java.awt.Color(137, 98, 59));
        labelProveedor.setFont(new java.awt.Font("Century Schoolbook L", 0, 15)); // NOI18N
        labelProveedor.setText("Proveedor");
        labelProveedor.setBorder(null);

        precioCompra.setEditable(false);
        precioCompra.setToolTipText("Precio del articulo para la compra(Numero real)");

        tipo.setEditable(false);
        tipo.setToolTipText("Tipo del articulo");

        labelTipo.setBackground(new java.awt.Color(137, 98, 59));
        labelTipo.setFont(new java.awt.Font("Century Schoolbook L", 0, 15)); // NOI18N
        labelTipo.setText("Tipo");
        labelTipo.setBorder(null);

        labelPrecio.setBackground(new java.awt.Color(137, 98, 59));
        labelPrecio.setFont(new java.awt.Font("Century Schoolbook L", 0, 15)); // NOI18N
        labelPrecio.setText("Precio de compra");
        labelPrecio.setBorder(null);

        proveedores.setToolTipText("Proveedor del articulo");

        labelStock.setFont(new java.awt.Font("Century Schoolbook L", 0, 15)); // NOI18N
        labelStock.setText("Stock");

        stock.setEditable(false);
        stock.setToolTipText("Stock del articulo");

        jLabel1.setText("Precio de venta");

        precioVenta.setEditable(false);
        precioVenta.setToolTipText("Precio del producto para la venta");

        javax.swing.GroupLayout panelArticuloLayout = new javax.swing.GroupLayout(panelArticulo);
        panelArticulo.setLayout(panelArticuloLayout);
        panelArticuloLayout.setHorizontalGroup(
            panelArticuloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelArticuloLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(panelArticuloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelArticuloLayout.createSequentialGroup()
                        .addComponent(labelCodigo)
                        .addGap(18, 18, 18)
                        .addComponent(idArticulo, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(labelTipo)
                        .addGap(19, 19, 19)
                        .addComponent(tipo, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelArticuloLayout.createSequentialGroup()
                        .addComponent(labelNombre)
                        .addGap(12, 12, 12)
                        .addComponent(nombre, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(labelPrecio)
                        .addGap(18, 18, 18)
                        .addComponent(precioCompra, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelArticuloLayout.createSequentialGroup()
                        .addComponent(labelMarca)
                        .addGap(24, 24, 24)
                        .addComponent(marca, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(41, 41, 41)
                        .addComponent(jLabel1)
                        .addGap(12, 12, 12)
                        .addComponent(precioVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelArticuloLayout.createSequentialGroup()
                        .addComponent(labelProveedor)
                        .addGap(12, 12, 12)
                        .addComponent(proveedores, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(labelStock)
                        .addGap(12, 12, 12)
                        .addComponent(stock, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelArticuloLayout.setVerticalGroup(
            panelArticuloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelArticuloLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(panelArticuloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelCodigo)
                    .addComponent(idArticulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelArticuloLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(labelTipo))
                    .addComponent(tipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelArticuloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelArticuloLayout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(labelNombre))
                    .addComponent(nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelArticuloLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(labelPrecio))
                    .addComponent(precioCompra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelArticuloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(marca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(precioVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelArticuloLayout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addGroup(panelArticuloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelMarca)
                            .addComponent(jLabel1))))
                .addGap(18, 18, 18)
                .addGroup(panelArticuloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelArticuloLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(labelProveedor))
                    .addComponent(proveedores, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelArticuloLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(labelStock))
                    .addComponent(stock, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelArticulos.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Artículos", 0, 0, new java.awt.Font("Century Schoolbook L", 3, 18))); // NOI18N

        tabla.setAutoCreateRowSorter(true);
        tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Cod.", "Nombre", "Marca"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane5.setViewportView(tabla);
        tabla.getColumnModel().getColumn(0).setPreferredWidth(80);
        tabla.getColumnModel().getColumn(0).setMaxWidth(80);

        labelBusquedaCodigo.setFont(new java.awt.Font("Century Schoolbook L", 0, 15)); // NOI18N
        labelBusquedaCodigo.setText("Código");

        labelBusquedaNombre.setFont(new java.awt.Font("Century Schoolbook L", 0, 15)); // NOI18N
        labelBusquedaNombre.setText("Nombre");

        labelBusquedaMarca.setFont(new java.awt.Font("Century Schoolbook L", 0, 15)); // NOI18N
        labelBusquedaMarca.setText("Marca");

        busquedaCodigo.setToolTipText("Filtrar por código");

        busquedaNombre.setToolTipText("Filtrar por nombre");

        busquedaMarca.setToolTipText("Filtrar por marca");

        modificarPrecios.setText("Modificar precios");

        javax.swing.GroupLayout panelArticulosLayout = new javax.swing.GroupLayout(panelArticulos);
        panelArticulos.setLayout(panelArticulosLayout);
        panelArticulosLayout.setHorizontalGroup(
            panelArticulosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelArticulosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelArticulosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelArticulosLayout.createSequentialGroup()
                        .addComponent(labelBusquedaMarca)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(busquedaMarca))
                    .addGroup(panelArticulosLayout.createSequentialGroup()
                        .addComponent(labelBusquedaCodigo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(busquedaCodigo))
                    .addGroup(panelArticulosLayout.createSequentialGroup()
                        .addComponent(labelBusquedaNombre)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(busquedaNombre, javax.swing.GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE)))
                .addGap(38, 38, 38))
            .addComponent(modificarPrecios, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );
        panelArticulosLayout.setVerticalGroup(
            panelArticulosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelArticulosLayout.createSequentialGroup()
                .addGroup(panelArticulosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelBusquedaCodigo)
                    .addComponent(busquedaCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelArticulosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelBusquedaNombre)
                    .addComponent(busquedaNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelArticulosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelBusquedaMarca)
                    .addComponent(busquedaMarca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(modificarPrecios))
        );

        javax.swing.GroupLayout fondoImagenLayout = new javax.swing.GroupLayout(fondoImagen);
        fondoImagen.setLayout(fondoImagenLayout);
        fondoImagenLayout.setHorizontalGroup(
            fondoImagenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(fondoImagenLayout.createSequentialGroup()
                .addGap(1, 1, 1)
                .addComponent(panelArticulos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(fondoImagenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panelArticulo, javax.swing.GroupLayout.DEFAULT_SIZE, 531, Short.MAX_VALUE)
                    .addComponent(panelControlArticulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
            .addComponent(panelTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        fondoImagenLayout.setVerticalGroup(
            fondoImagenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(fondoImagenLayout.createSequentialGroup()
                .addComponent(panelTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(fondoImagenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(fondoImagenLayout.createSequentialGroup()
                        .addComponent(panelArticulo, javax.swing.GroupLayout.DEFAULT_SIZE, 262, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(panelControlArticulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(panelArticulos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(12, 12, 12))
        );

        jScrollPane1.setViewportView(fondoImagen);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 825, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 407, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton anterior;
    private javax.swing.JButton borrar;
    private javax.swing.JTextField busquedaCodigo;
    private javax.swing.JTextField busquedaMarca;
    private javax.swing.JTextField busquedaNombre;
    private javax.swing.JPanel fondoImagen;
    private javax.swing.JButton guardar;
    private javax.swing.JTextField idArticulo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JLabel labelBusquedaCodigo;
    private javax.swing.JLabel labelBusquedaMarca;
    private javax.swing.JLabel labelBusquedaNombre;
    private javax.swing.JLabel labelCodigo;
    private javax.swing.JLabel labelMarca;
    private javax.swing.JLabel labelNombre;
    private javax.swing.JLabel labelPrecio;
    private javax.swing.JLabel labelProveedor;
    private javax.swing.JLabel labelStock;
    private javax.swing.JLabel labelTipo;
    private javax.swing.JTextField marca;
    private javax.swing.JButton modificar;
    private javax.swing.JButton modificarPrecios;
    private javax.swing.JTextField nombre;
    private javax.swing.JButton nuevo;
    private javax.swing.JPanel panelArticulo;
    private javax.swing.JPanel panelArticulos;
    private javax.swing.JPanel panelControlArticulo;
    private org.edisoncor.gui.panel.PanelImage panelTitulo;
    private javax.swing.JTextField precioCompra;
    private javax.swing.JTextField precioVenta;
    private javax.swing.JComboBox proveedores;
    private javax.swing.JButton siguiente;
    private javax.swing.JTextField stock;
    private javax.swing.JTable tabla;
    private javax.swing.JTextField tipo;
    private javax.swing.JLabel titulo;
    // End of variables declaration//GEN-END:variables
}
