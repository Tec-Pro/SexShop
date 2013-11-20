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
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author max
 */
public class AbmProveedorGui extends javax.swing.JPanel {

    DefaultTableModel tablaProveedores; //Tabla Default de los Proveedores


    /**
     * Creates new form AbmProductoGui
     */
    public AbmProveedorGui() {
        initComponents();
        tablaProveedores = (DefaultTableModel) tabla.getModel(); //convierto la tabla
    }


    public void setActionListener(ActionListener lis) {
        this.guardar.addActionListener(lis);
        this.borrar.addActionListener(lis);
        this.nuevo.addActionListener(lis);
        this.siguiente.addActionListener(lis);
        this.anterior.addActionListener(lis);
        this.modificar.addActionListener(lis);
        this.buttonArticulos.addActionListener(lis);
    }   

    public void habilitarCampos(boolean b){
        campoApellido.setEditable(b);
        campoNombre.setEditable(b);
        campoDni.setEditable(b);
        campoTelefono.setEditable(b);
        campoCelular.setEditable(b);
        campoEmail.setEditable(b);
        campoCuil.setEditable(b);
        campoBanco.setEditable(b);
        campoNumCuenta.setEditable(b);
        campoTipoCuenta.setEditable(b);
        campoSucursal.setEditable(b);
        campoCompraMinima.setEditable(b);
    }
    
    public void limpiarCampos(){
        campoApellido.setText("");
        campoNombre.setText("");
        campoDni.setText("");
        campoTelefono.setText("");
        campoCelular.setText("");
        campoEmail.setText("");
        campoCuil.setText("");
        campoBanco.setText("");
        campoNumCuenta.setText("");
        campoSucursal.setText("");
        campoCompraMinima.setText("");   
    }

    public JButton getAnterior() {
        return anterior;
    }

    public JButton getSiguiente() {
        return siguiente;
    }
    
    public DefaultTableModel getTablaProveedores() {
        return tablaProveedores;
    }

    public JTextField getBusquedaArticulo() {
        return busquedaArticulo;
    }

    public JTextField getBusquedaDni() {
        return busquedaDni;
    }

    public JTextField getBusquedaNombre() {
        return busquedaNombre;
    }

    public JTextField getCampoApellido() {
        return campoApellido;
    }

    public JTextField getCampoBanco() {
        return campoBanco;
    }

    public JTextField getCampoCelular() {
        return campoCelular;
    }

    public JTextField getCampoCompraMinima() {
        return campoCompraMinima;
    }

    public JTextField getCampoCuil() {
        return campoCuil;
    }

    public JTextField getCampoDni() {
        return campoDni;
    }

    public JTextField getCampoEmail() {
        return campoEmail;
    }

    public JTextField getCampoNombre() {
        return campoNombre;
    }

    public JTextField getCampoNumCuenta() {
        return campoNumCuenta;
    }

    public JTextField getCampoSucursal() {
        return campoSucursal;
    }

    public JTextField getCampoTelefono() {
        return campoTelefono;
    }

    public JComboBox getCampoTipoCuenta() {
        return campoTipoCuenta;
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane5 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        jScrollPane1 = new javax.swing.JScrollPane();
        fondoImagen = new org.edisoncor.gui.panel.PanelImage();
        panelTitulo = new org.edisoncor.gui.panel.PanelImage();
        titulo = new javax.swing.JLabel();
        panelControlArticulo = new org.edisoncor.gui.panel.PanelImage();
        nuevo = new javax.swing.JButton();
        modificar = new javax.swing.JButton();
        guardar = new javax.swing.JButton();
        borrar = new javax.swing.JButton();
        anterior = new javax.swing.JButton();
        siguiente = new javax.swing.JButton();
        panelDatosProveedor = new org.edisoncor.gui.panel.PanelImage();
        LabelDni = new javax.swing.JLabel();
        campoDni = new javax.swing.JTextField();
        campoEmail = new javax.swing.JTextField();
        labelNombre = new javax.swing.JLabel();
        labelTelefono = new javax.swing.JLabel();
        campoTelefono = new javax.swing.JTextField();
        labelEmail = new javax.swing.JLabel();
        campoCelular = new javax.swing.JTextField();
        labelApellido = new javax.swing.JLabel();
        labelCelular = new javax.swing.JLabel();
        labelCuil = new javax.swing.JLabel();
        campoNombre = new javax.swing.JTextField();
        campoCuil = new javax.swing.JTextField();
        campoApellido = new javax.swing.JTextField();
        labelBanco = new javax.swing.JLabel();
        campoBanco = new javax.swing.JTextField();
        campoNumCuenta = new javax.swing.JTextField();
        labelNumCuenta = new javax.swing.JLabel();
        campoSucursal = new javax.swing.JTextField();
        labelSucursal = new javax.swing.JLabel();
        labelTipoCuenta = new javax.swing.JLabel();
        campoTipoCuenta = new javax.swing.JComboBox();
        labelCompraMinima = new javax.swing.JLabel();
        campoCompraMinima = new javax.swing.JTextField();
        buttonArticulos = new javax.swing.JButton();
        panelBusqueda = new org.edisoncor.gui.panel.PanelImage();
        labelBusquedaDni = new javax.swing.JLabel();
        labelBusquedaNombre = new javax.swing.JLabel();
        busquedaDni = new javax.swing.JTextField();
        busquedaNombre = new javax.swing.JTextField();
        jScrollPane6 = new javax.swing.JScrollPane();
        tabla1 = new javax.swing.JTable();
        labelBusquedaArticulo = new javax.swing.JLabel();
        busquedaArticulo = new javax.swing.JTextField();

        tabla.setAutoCreateRowSorter(true);
        tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Dni", "Nombre", "Telefono"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class
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

        setPreferredSize(new java.awt.Dimension(825, 407));

        jScrollPane1.setPreferredSize(new java.awt.Dimension(822, 402));

        fondoImagen.setPreferredSize(new java.awt.Dimension(820, 400));

        panelTitulo.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        panelTitulo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaz/fondoCliente2.jpg"))); // NOI18N
        panelTitulo.setPreferredSize(new java.awt.Dimension(329, 49));

        titulo.setFont(new java.awt.Font("Century Schoolbook L", 3, 24)); // NOI18N
        titulo.setText("CONTROL DE PROVEEDORES");
        panelTitulo.add(titulo);

        panelControlArticulo.setLayout(new java.awt.GridLayout(1, 0));

        nuevo.setBackground(javax.swing.UIManager.getDefaults().getColor("Button.background"));
        nuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaz/Icons/agregar.png"))); // NOI18N
        nuevo.setToolTipText("Articulo nuevo");
        nuevo.setPreferredSize(new java.awt.Dimension(70, 70));
        nuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nuevoActionPerformed(evt);
            }
        });
        panelControlArticulo.add(nuevo);

        modificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaz/Icons/modificar.png"))); // NOI18N
        modificar.setToolTipText("Modificar articulo");
        modificar.setPreferredSize(new java.awt.Dimension(70, 70));
        modificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modificarActionPerformed(evt);
            }
        });
        panelControlArticulo.add(modificar);

        guardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaz/Icons/guardar.png"))); // NOI18N
        guardar.setToolTipText("Guardar articulo");
        guardar.setPreferredSize(new java.awt.Dimension(70, 70));
        panelControlArticulo.add(guardar);

        borrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaz/Icons/borrar.png"))); // NOI18N
        borrar.setToolTipText("Borrar articulo");
        borrar.setPreferredSize(new java.awt.Dimension(70, 70));
        panelControlArticulo.add(borrar);

        anterior.setIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaz/Icons/anterior.png"))); // NOI18N
        anterior.setToolTipText("Articulo anterior");
        anterior.setPreferredSize(new java.awt.Dimension(70, 70));
        panelControlArticulo.add(anterior);

        siguiente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaz/Icons/siguiente.png"))); // NOI18N
        siguiente.setToolTipText("Proximo articulo");
        siguiente.setPreferredSize(new java.awt.Dimension(70, 70));
        panelControlArticulo.add(siguiente);

        panelDatosProveedor.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Datos del Proveedor", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Century Schoolbook L", 3, 18))); // NOI18N
        panelDatosProveedor.setPreferredSize(new java.awt.Dimension(557, 279));

        LabelDni.setBackground(new java.awt.Color(137, 98, 59));
        LabelDni.setFont(new java.awt.Font("Century Schoolbook L", 0, 15)); // NOI18N
        LabelDni.setText("Dni");
        LabelDni.setBorder(null);

        campoDni.setToolTipText("Dni del proveedor");
        campoDni.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campoDniActionPerformed(evt);
            }
        });

        campoEmail.setToolTipText("E-mail del proveedor");

        labelNombre.setBackground(new java.awt.Color(137, 98, 59));
        labelNombre.setFont(new java.awt.Font("Century Schoolbook L", 0, 15)); // NOI18N
        labelNombre.setText("Nombre");
        labelNombre.setBorder(null);

        labelTelefono.setBackground(new java.awt.Color(137, 98, 59));
        labelTelefono.setFont(new java.awt.Font("Century Schoolbook L", 0, 15)); // NOI18N
        labelTelefono.setText("Telefono");
        labelTelefono.setBorder(null);

        campoTelefono.setToolTipText("Telefono Fijo");

        labelEmail.setBackground(new java.awt.Color(137, 98, 59));
        labelEmail.setFont(new java.awt.Font("Century Schoolbook L", 0, 15)); // NOI18N
        labelEmail.setText("E-mail");
        labelEmail.setBorder(null);

        campoCelular.setToolTipText("Telefono Móvil");
        campoCelular.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campoCelularActionPerformed(evt);
            }
        });

        labelApellido.setBackground(new java.awt.Color(137, 98, 59));
        labelApellido.setFont(new java.awt.Font("Century Schoolbook L", 0, 15)); // NOI18N
        labelApellido.setText("Apellido");
        labelApellido.setBorder(null);

        labelCelular.setBackground(new java.awt.Color(137, 98, 59));
        labelCelular.setFont(new java.awt.Font("Century Schoolbook L", 0, 15)); // NOI18N
        labelCelular.setText("Celular");
        labelCelular.setBorder(null);

        labelCuil.setFont(new java.awt.Font("Century Schoolbook L", 0, 15)); // NOI18N
        labelCuil.setText("Cuil");

        campoNombre.setToolTipText("Nombre del proveedor");

        campoCuil.setToolTipText("Cuil del proveedor");

        campoApellido.setToolTipText("Apellido del proveedor");

        labelBanco.setBackground(new java.awt.Color(137, 98, 59));
        labelBanco.setFont(new java.awt.Font("Century Schoolbook L", 0, 15)); // NOI18N
        labelBanco.setText("Banco");
        labelBanco.setBorder(null);

        campoBanco.setToolTipText("Nombre del Banco");

        campoNumCuenta.setToolTipText("Numero de cuenta del banco");

        labelNumCuenta.setBackground(new java.awt.Color(137, 98, 59));
        labelNumCuenta.setFont(new java.awt.Font("Century Schoolbook L", 0, 15)); // NOI18N
        labelNumCuenta.setText("Num de Cuenta");
        labelNumCuenta.setBorder(null);

        campoSucursal.setToolTipText("Sucursal");

        labelSucursal.setBackground(new java.awt.Color(137, 98, 59));
        labelSucursal.setFont(new java.awt.Font("Century Schoolbook L", 0, 15)); // NOI18N
        labelSucursal.setText("Sucursal");
        labelSucursal.setBorder(null);

        labelTipoCuenta.setBackground(new java.awt.Color(137, 98, 59));
        labelTipoCuenta.setFont(new java.awt.Font("Century Schoolbook L", 0, 15)); // NOI18N
        labelTipoCuenta.setText("Tipo de Cuenta");
        labelTipoCuenta.setBorder(null);

        campoTipoCuenta.setToolTipText("");

        labelCompraMinima.setBackground(new java.awt.Color(137, 98, 59));
        labelCompraMinima.setFont(new java.awt.Font("Century Schoolbook L", 0, 15)); // NOI18N
        labelCompraMinima.setText("Compra minima");
        labelCompraMinima.setBorder(null);

        campoCompraMinima.setToolTipText("Compra Minima");

        buttonArticulos.setText("Articulos");
        buttonArticulos.setToolTipText("Articulos que provee");

        javax.swing.GroupLayout panelDatosProveedorLayout = new javax.swing.GroupLayout(panelDatosProveedor);
        panelDatosProveedor.setLayout(panelDatosProveedorLayout);
        panelDatosProveedorLayout.setHorizontalGroup(
            panelDatosProveedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDatosProveedorLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelDatosProveedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelDatosProveedorLayout.createSequentialGroup()
                        .addComponent(labelCompraMinima)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(campoCompraMinima, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)
                        .addComponent(buttonArticulos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(12, 12, 12))
                    .addGroup(panelDatosProveedorLayout.createSequentialGroup()
                        .addGroup(panelDatosProveedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelTelefono)
                            .addComponent(labelEmail))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelDatosProveedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelDatosProveedorLayout.createSequentialGroup()
                                .addComponent(campoTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(labelCelular)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(campoCelular, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panelDatosProveedorLayout.createSequentialGroup()
                                .addGroup(panelDatosProveedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(labelNumCuenta)
                                    .addGroup(panelDatosProveedorLayout.createSequentialGroup()
                                        .addComponent(campoEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(labelCuil))
                                    .addComponent(labelTipoCuenta))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panelDatosProveedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(campoCuil, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(campoNumCuenta)
                                    .addComponent(campoTipoCuenta, 0, 150, Short.MAX_VALUE)))))
                    .addGroup(panelDatosProveedorLayout.createSequentialGroup()
                        .addComponent(LabelDni)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(campoDni, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(labelNombre)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(campoNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(labelApellido)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(campoApellido, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelDatosProveedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(panelDatosProveedorLayout.createSequentialGroup()
                            .addGap(6, 6, 6)
                            .addComponent(labelBanco)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(campoBanco, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelDatosProveedorLayout.createSequentialGroup()
                            .addComponent(labelSucursal)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(campoSucursal, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(14, Short.MAX_VALUE))
        );
        panelDatosProveedorLayout.setVerticalGroup(
            panelDatosProveedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDatosProveedorLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelDatosProveedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LabelDni)
                    .addComponent(campoDni, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelNombre)
                    .addComponent(labelApellido)
                    .addComponent(campoNombre, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(campoApellido, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelDatosProveedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelTelefono)
                    .addComponent(campoTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelCelular)
                    .addComponent(campoCelular, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelDatosProveedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelEmail)
                    .addComponent(campoEmail, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelCuil)
                    .addComponent(campoCuil, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelDatosProveedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(campoNumCuenta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelNumCuenta)
                    .addComponent(campoBanco, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelBanco))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelDatosProveedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelDatosProveedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(labelSucursal)
                        .addComponent(campoSucursal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelDatosProveedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(labelTipoCuenta)
                        .addComponent(campoTipoCuenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelDatosProveedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelCompraMinima)
                    .addComponent(campoCompraMinima, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonArticulos))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelBusqueda.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Proveedores", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Century Schoolbook L", 3, 18))); // NOI18N

        labelBusquedaDni.setFont(new java.awt.Font("Century Schoolbook L", 0, 15)); // NOI18N
        labelBusquedaDni.setText("Dni");

        labelBusquedaNombre.setFont(new java.awt.Font("Century Schoolbook L", 0, 15)); // NOI18N
        labelBusquedaNombre.setText("Nombre");

        busquedaDni.setToolTipText("Filtrar por dni");

        busquedaNombre.setToolTipText("Filtrar por nombre");

        tabla1.setAutoCreateRowSorter(true);
        tabla1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Dni", "Nombre", "Telefono"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class
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
        jScrollPane6.setViewportView(tabla1);

        labelBusquedaArticulo.setFont(new java.awt.Font("Century Schoolbook L", 0, 15)); // NOI18N
        labelBusquedaArticulo.setText("Articulo");

        busquedaArticulo.setToolTipText("Filtrar por nombre");

        javax.swing.GroupLayout panelBusquedaLayout = new javax.swing.GroupLayout(panelBusqueda);
        panelBusqueda.setLayout(panelBusquedaLayout);
        panelBusquedaLayout.setHorizontalGroup(
            panelBusquedaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBusquedaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelBusquedaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelBusquedaLayout.createSequentialGroup()
                        .addGroup(panelBusquedaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(panelBusquedaLayout.createSequentialGroup()
                                .addComponent(labelBusquedaDni)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(busquedaDni))
                            .addGroup(panelBusquedaLayout.createSequentialGroup()
                                .addComponent(labelBusquedaNombre)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(busquedaNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panelBusquedaLayout.createSequentialGroup()
                                .addComponent(labelBusquedaArticulo)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(busquedaArticulo, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );
        panelBusquedaLayout.setVerticalGroup(
            panelBusquedaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBusquedaLayout.createSequentialGroup()
                .addGroup(panelBusquedaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelBusquedaDni)
                    .addComponent(busquedaDni, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelBusquedaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelBusquedaNombre)
                    .addComponent(busquedaNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelBusquedaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelBusquedaArticulo)
                    .addComponent(busquedaArticulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout fondoImagenLayout = new javax.swing.GroupLayout(fondoImagen);
        fondoImagen.setLayout(fondoImagenLayout);
        fondoImagenLayout.setHorizontalGroup(
            fondoImagenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(fondoImagenLayout.createSequentialGroup()
                .addComponent(panelBusqueda, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(fondoImagenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelControlArticulo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 529, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panelDatosProveedor, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 530, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        fondoImagenLayout.setVerticalGroup(
            fondoImagenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(fondoImagenLayout.createSequentialGroup()
                .addComponent(panelTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(fondoImagenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, fondoImagenLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(panelControlArticulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(fondoImagenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(panelBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(panelDatosProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, 0))
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

    private void campoDniActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoDniActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campoDniActionPerformed

    private void campoCelularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoCelularActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campoCelularActionPerformed

    private void nuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nuevoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nuevoActionPerformed

    private void modificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modificarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_modificarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel LabelDni;
    private javax.swing.JButton anterior;
    private javax.swing.JButton borrar;
    private javax.swing.JTextField busquedaArticulo;
    private javax.swing.JTextField busquedaDni;
    private javax.swing.JTextField busquedaNombre;
    private javax.swing.JButton buttonArticulos;
    private javax.swing.JTextField campoApellido;
    private javax.swing.JTextField campoBanco;
    private javax.swing.JTextField campoCelular;
    private javax.swing.JTextField campoCompraMinima;
    private javax.swing.JTextField campoCuil;
    private javax.swing.JTextField campoDni;
    private javax.swing.JTextField campoEmail;
    private javax.swing.JTextField campoNombre;
    private javax.swing.JTextField campoNumCuenta;
    private javax.swing.JTextField campoSucursal;
    private javax.swing.JTextField campoTelefono;
    private javax.swing.JComboBox campoTipoCuenta;
    private org.edisoncor.gui.panel.PanelImage fondoImagen;
    private javax.swing.JButton guardar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JLabel labelApellido;
    private javax.swing.JLabel labelBanco;
    private javax.swing.JLabel labelBusquedaArticulo;
    private javax.swing.JLabel labelBusquedaDni;
    private javax.swing.JLabel labelBusquedaNombre;
    private javax.swing.JLabel labelCelular;
    private javax.swing.JLabel labelCompraMinima;
    private javax.swing.JLabel labelCuil;
    private javax.swing.JLabel labelEmail;
    private javax.swing.JLabel labelNombre;
    private javax.swing.JLabel labelNumCuenta;
    private javax.swing.JLabel labelSucursal;
    private javax.swing.JLabel labelTelefono;
    private javax.swing.JLabel labelTipoCuenta;
    private javax.swing.JButton modificar;
    private javax.swing.JButton nuevo;
    private org.edisoncor.gui.panel.PanelImage panelBusqueda;
    private org.edisoncor.gui.panel.PanelImage panelControlArticulo;
    private org.edisoncor.gui.panel.PanelImage panelDatosProveedor;
    private org.edisoncor.gui.panel.PanelImage panelTitulo;
    private javax.swing.JButton siguiente;
    private javax.swing.JTable tabla;
    private javax.swing.JTable tabla1;
    private javax.swing.JLabel titulo;
    // End of variables declaration//GEN-END:variables
}