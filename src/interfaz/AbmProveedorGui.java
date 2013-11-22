/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaz;

import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import modelos.Proveedor;

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
        tablaProveedores = (DefaultTableModel) tabla1.getModel(); //convierto la tabla
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
    
    public void CargarCampos(Proveedor c) {
        cuil.setText(c.get("cuil").toString());
        nombre.setText(c.get("nombre").toString());
        if(c.get("apellido")==null){apellido.setText("");}
        else{apellido.setText(c.get("apellido").toString());}
        if(c.get("telefono")==null){telefono.setText("");}
        else{telefono.setText(c.get("telefono").toString());}
        if(c.get("mail")==null){email.setText("");}
        else{email.setText(c.get("mail").toString());}
        if(c.get("celular")==null){celular.setText("");}
        else{celular.setText(c.get("celular").toString());}
        if(c.get("dni")==null){dni.setText("");}
        else{dni.setText(c.get("dni").toString());}
        if(c.get("nombre_banco")==null){banco.setText("");}
        else{banco.setText(c.get("nombre_banco").toString());}
        if(c.get("cuenta")==null){numDeCuenta.setText("");}
        else{numDeCuenta.setText(c.get("cuenta").toString());}
        if(c.get("tipo_cuenta")==null){tipoCuenta.setText("");}
        else{tipoCuenta.setText(c.get("tipo_cuenta").toString());}
        if(c.get("sucursal")==null){sucursal.setText("");}
        else{sucursal.setText(c.get("sucursal").toString());}
        if(c.get("compra_minima")==null){compraMinima.setText("");}
        else{compraMinima.setText(c.get("compra_minima").toString());}
    }
    
    public void habilitarCampos(boolean b,boolean cui){
        apellido.setEditable(b);
        nombre.setEditable(b);
        dni.setEditable(b);
        telefono.setEditable(b);
        celular.setEditable(b);
        email.setEditable(b);
        cuil.setEditable(cui);
        banco.setEditable(b);
        numDeCuenta.setEditable(b);
        tipoCuenta.setEditable(b);
        sucursal.setEditable(b);
        compraMinima.setEditable(b);
    }
    
    public void limpiarCampos(){
        apellido.setText("");
        nombre.setText("");
        dni.setText("");
        telefono.setText("");
        celular.setText("");
        email.setText("");
        cuil.setText("");
        banco.setText("");
        numDeCuenta.setText("");
        sucursal.setText("");
        compraMinima.setText("");
        tipoCuenta.setText("");  
    }

    public JButton getArticulos(){
        return buttonArticulos;
    }
    
    public JButton getAnterior() {
        return anterior;
    }

    public JButton getSiguiente() {
        return siguiente;
    }
    
    public JButton getNuevo() {
        return nuevo;
    }
    
    public JButton getModificar() {
        return modificar;
    }
    
    public JButton getGuardar() {
        return guardar;
    }
    
    public JButton getBorrar() {
        return borrar;
    }
    
    public DefaultTableModel getTablaProveedores() {
        return tablaProveedores;
    }
    
    public JTable getTablaProv(){
        return tabla1;
    } 
    
    public JTextField getBusquedaCuil(){
        return busquedaDni;
    }
    
    public JTextField getBusquedaId() {
        return busquedaId;
    }

    public JTextField getBusquedaNombre() {
        return busquedaNombre;
    }

    public JTextField getApellido() {
        return apellido;
    }

    public JTextField getBanco() {
        return banco;
    }

    public JTextField getCelular() {
        return celular;
    }

    public JTextField getCompraMinima() {
        return compraMinima;
    }

    public JTextField getCuil() {
        return cuil;
    }

    public JTextField getTipoCuenta(){
        return tipoCuenta ;
    }
    
    public JTextField getDni() {
        return dni;
    }

    public JTextField getEmail() {
        return email;
    }

    public JTextField getNombre() {
        return nombre;
    }

    public JTextField getNumCuenta() {
        return numDeCuenta;
    }

    public JTextField getSucursal() {
        return sucursal;
    }

    public JTextField getTelefono() {
        return telefono;
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
        fondoImagen = new javax.swing.JPanel();
        panelTitulo = new org.edisoncor.gui.panel.PanelImage();
        titulo = new javax.swing.JLabel();
        panelControlArticulo = new javax.swing.JPanel();
        nuevo = new javax.swing.JButton();
        modificar = new javax.swing.JButton();
        guardar = new javax.swing.JButton();
        borrar = new javax.swing.JButton();
        anterior = new javax.swing.JButton();
        siguiente = new javax.swing.JButton();
        panelDatosProveedor = new javax.swing.JPanel();
        LabelDni = new javax.swing.JLabel();
        dni = new javax.swing.JTextField();
        email = new javax.swing.JTextField();
        labelNombre = new javax.swing.JLabel();
        labelTelefono = new javax.swing.JLabel();
        telefono = new javax.swing.JTextField();
        labelEmail = new javax.swing.JLabel();
        celular = new javax.swing.JTextField();
        labelApellido = new javax.swing.JLabel();
        labelCelular = new javax.swing.JLabel();
        labelCuil = new javax.swing.JLabel();
        nombre = new javax.swing.JTextField();
        cuil = new javax.swing.JTextField();
        apellido = new javax.swing.JTextField();
        labelBanco = new javax.swing.JLabel();
        banco = new javax.swing.JTextField();
        numDeCuenta = new javax.swing.JTextField();
        labelNumCuenta = new javax.swing.JLabel();
        sucursal = new javax.swing.JTextField();
        labelSucursal = new javax.swing.JLabel();
        labelTipoCuenta = new javax.swing.JLabel();
        labelCompraMinima = new javax.swing.JLabel();
        compraMinima = new javax.swing.JTextField();
        buttonArticulos = new javax.swing.JButton();
        tipoCuenta = new javax.swing.JTextField();
        panelBusqueda = new javax.swing.JPanel();
        labelBusquedaDni = new javax.swing.JLabel();
        labelBusquedaNombre = new javax.swing.JLabel();
        busquedaDni = new javax.swing.JTextField();
        busquedaNombre = new javax.swing.JTextField();
        jScrollPane6 = new javax.swing.JScrollPane();
        tabla1 = new javax.swing.JTable();
        busquedaId = new javax.swing.JTextField();
        labelBusquedaId = new javax.swing.JLabel();

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
        nuevo.setToolTipText("Proveedor nuevo");
        nuevo.setPreferredSize(new java.awt.Dimension(70, 70));
        nuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nuevoActionPerformed(evt);
            }
        });
        panelControlArticulo.add(nuevo);

        modificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaz/Icons/modificar.png"))); // NOI18N
        modificar.setToolTipText("Modificar proveedor");
        modificar.setPreferredSize(new java.awt.Dimension(70, 70));
        modificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modificarActionPerformed(evt);
            }
        });
        panelControlArticulo.add(modificar);

        guardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaz/Icons/guardar.png"))); // NOI18N
        guardar.setToolTipText("Guardar proveedor");
        guardar.setPreferredSize(new java.awt.Dimension(70, 70));
        panelControlArticulo.add(guardar);

        borrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaz/Icons/borrar.png"))); // NOI18N
        borrar.setToolTipText("Borrar proveedor");
        borrar.setPreferredSize(new java.awt.Dimension(70, 70));
        panelControlArticulo.add(borrar);

        anterior.setIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaz/Icons/anterior.png"))); // NOI18N
        anterior.setToolTipText("Proveedor anterior");
        anterior.setPreferredSize(new java.awt.Dimension(70, 70));
        panelControlArticulo.add(anterior);

        siguiente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaz/Icons/siguiente.png"))); // NOI18N
        siguiente.setToolTipText("Proximo Proveedor");
        siguiente.setPreferredSize(new java.awt.Dimension(70, 70));
        panelControlArticulo.add(siguiente);

        panelDatosProveedor.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Datos del Proveedor", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Century Schoolbook L", 3, 18))); // NOI18N
        panelDatosProveedor.setPreferredSize(new java.awt.Dimension(557, 279));

        LabelDni.setBackground(new java.awt.Color(137, 98, 59));
        LabelDni.setFont(new java.awt.Font("Century Schoolbook L", 0, 15)); // NOI18N
        LabelDni.setText("Dni");
        LabelDni.setBorder(null);

        dni.setToolTipText("Dni del proveedor");
        dni.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dniActionPerformed(evt);
            }
        });

        email.setToolTipText("E-mail del proveedor");

        labelNombre.setBackground(new java.awt.Color(137, 98, 59));
        labelNombre.setFont(new java.awt.Font("Century Schoolbook L", 0, 15)); // NOI18N
        labelNombre.setText("Nombre");
        labelNombre.setBorder(null);

        labelTelefono.setBackground(new java.awt.Color(137, 98, 59));
        labelTelefono.setFont(new java.awt.Font("Century Schoolbook L", 0, 15)); // NOI18N
        labelTelefono.setText("Telefono");
        labelTelefono.setBorder(null);

        telefono.setToolTipText("Telefono Fijo");

        labelEmail.setBackground(new java.awt.Color(137, 98, 59));
        labelEmail.setFont(new java.awt.Font("Century Schoolbook L", 0, 15)); // NOI18N
        labelEmail.setText("E-mail");
        labelEmail.setBorder(null);

        celular.setToolTipText("Telefono Móvil");
        celular.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                celularActionPerformed(evt);
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

        nombre.setToolTipText("Nombre del proveedor");

        cuil.setToolTipText("Cuil del proveedor");

        apellido.setToolTipText("Apellido del proveedor");

        labelBanco.setBackground(new java.awt.Color(137, 98, 59));
        labelBanco.setFont(new java.awt.Font("Century Schoolbook L", 0, 15)); // NOI18N
        labelBanco.setText("Banco");
        labelBanco.setBorder(null);

        banco.setToolTipText("Nombre del Banco");

        numDeCuenta.setToolTipText("Numero de cuenta del banco");

        labelNumCuenta.setBackground(new java.awt.Color(137, 98, 59));
        labelNumCuenta.setFont(new java.awt.Font("Century Schoolbook L", 0, 15)); // NOI18N
        labelNumCuenta.setText("Num de Cuenta");
        labelNumCuenta.setBorder(null);

        sucursal.setToolTipText("Sucursal");

        labelSucursal.setBackground(new java.awt.Color(137, 98, 59));
        labelSucursal.setFont(new java.awt.Font("Century Schoolbook L", 0, 15)); // NOI18N
        labelSucursal.setText("Sucursal");
        labelSucursal.setBorder(null);

        labelTipoCuenta.setBackground(new java.awt.Color(137, 98, 59));
        labelTipoCuenta.setFont(new java.awt.Font("Century Schoolbook L", 0, 15)); // NOI18N
        labelTipoCuenta.setText("Tipo de Cuenta");
        labelTipoCuenta.setBorder(null);

        labelCompraMinima.setBackground(new java.awt.Color(137, 98, 59));
        labelCompraMinima.setFont(new java.awt.Font("Century Schoolbook L", 0, 15)); // NOI18N
        labelCompraMinima.setText("Compra minima");
        labelCompraMinima.setBorder(null);

        compraMinima.setToolTipText("Compra Minima");

        buttonArticulos.setText("Articulos");
        buttonArticulos.setToolTipText("Articulos que provee");

        tipoCuenta.setToolTipText("Tipo de cuenta del banco");

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
                        .addComponent(compraMinima, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)
                        .addComponent(buttonArticulos, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelDatosProveedorLayout.createSequentialGroup()
                        .addGroup(panelDatosProveedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelTelefono)
                            .addComponent(labelEmail))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelDatosProveedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelDatosProveedorLayout.createSequentialGroup()
                                .addComponent(telefono, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(labelCelular)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(celular, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panelDatosProveedorLayout.createSequentialGroup()
                                .addGroup(panelDatosProveedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(labelNumCuenta)
                                    .addGroup(panelDatosProveedorLayout.createSequentialGroup()
                                        .addComponent(email, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(labelCuil))
                                    .addComponent(labelTipoCuenta))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panelDatosProveedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cuil, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(numDeCuenta, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(tipoCuenta, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(panelDatosProveedorLayout.createSequentialGroup()
                        .addComponent(LabelDni)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dni, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(labelNombre)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(nombre, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(labelApellido)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(apellido, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelDatosProveedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(panelDatosProveedorLayout.createSequentialGroup()
                            .addGap(6, 6, 6)
                            .addComponent(labelBanco)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(banco, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelDatosProveedorLayout.createSequentialGroup()
                            .addComponent(labelSucursal)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(sucursal, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(13, Short.MAX_VALUE))
        );
        panelDatosProveedorLayout.setVerticalGroup(
            panelDatosProveedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDatosProveedorLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelDatosProveedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LabelDni)
                    .addComponent(dni, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelNombre)
                    .addComponent(labelApellido)
                    .addComponent(nombre, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(apellido, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelDatosProveedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelTelefono)
                    .addComponent(telefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelCelular)
                    .addComponent(celular, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelDatosProveedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelEmail)
                    .addComponent(email, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelCuil)
                    .addComponent(cuil, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelDatosProveedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(numDeCuenta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelNumCuenta)
                    .addComponent(banco, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelBanco))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelDatosProveedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelDatosProveedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(labelSucursal)
                        .addComponent(sucursal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelDatosProveedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(labelTipoCuenta)
                        .addComponent(tipoCuenta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelDatosProveedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelCompraMinima)
                    .addComponent(compraMinima, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonArticulos))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelBusqueda.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Proveedores", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Century Schoolbook L", 3, 18))); // NOI18N

        labelBusquedaDni.setFont(new java.awt.Font("Century Schoolbook L", 0, 15)); // NOI18N
        labelBusquedaDni.setText("Cuil");

        labelBusquedaNombre.setFont(new java.awt.Font("Century Schoolbook L", 0, 15)); // NOI18N
        labelBusquedaNombre.setText("Nombre");

        busquedaDni.setToolTipText("Filtrar por dni");

        busquedaNombre.setToolTipText("Filtrar por nombre");

        tabla1.setAutoCreateRowSorter(true);
        tabla1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Cuil", "Nombre"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class
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

        busquedaId.setToolTipText("Filtrar por dni");

        labelBusquedaId.setFont(new java.awt.Font("Century Schoolbook L", 0, 15)); // NOI18N
        labelBusquedaId.setText("Id");

        javax.swing.GroupLayout panelBusquedaLayout = new javax.swing.GroupLayout(panelBusqueda);
        panelBusqueda.setLayout(panelBusquedaLayout);
        panelBusquedaLayout.setHorizontalGroup(
            panelBusquedaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBusquedaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelBusquedaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBusquedaLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(panelBusquedaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(panelBusquedaLayout.createSequentialGroup()
                                .addComponent(labelBusquedaDni)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(busquedaDni))
                            .addGroup(panelBusquedaLayout.createSequentialGroup()
                                .addComponent(labelBusquedaNombre)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(busquedaNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(panelBusquedaLayout.createSequentialGroup()
                        .addComponent(labelBusquedaId)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(busquedaId)))
                .addContainerGap())
        );
        panelBusquedaLayout.setVerticalGroup(
            panelBusquedaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBusquedaLayout.createSequentialGroup()
                .addGroup(panelBusquedaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelBusquedaId)
                    .addComponent(busquedaId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelBusquedaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelBusquedaDni)
                    .addComponent(busquedaDni, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelBusquedaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelBusquedaNombre)
                    .addComponent(busquedaNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                    .addComponent(panelDatosProveedor, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 529, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        fondoImagenLayout.setVerticalGroup(
            fondoImagenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(fondoImagenLayout.createSequentialGroup()
                .addComponent(panelTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(fondoImagenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, fondoImagenLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(panelControlArticulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(fondoImagenLayout.createSequentialGroup()
                        .addComponent(panelDatosProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addGap(81, 81, 81))
                    .addComponent(panelBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3))
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

    private void dniActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dniActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_dniActionPerformed

    private void celularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_celularActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_celularActionPerformed

    private void nuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nuevoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nuevoActionPerformed

    private void modificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modificarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_modificarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel LabelDni;
    private javax.swing.JButton anterior;
    private javax.swing.JTextField apellido;
    private javax.swing.JTextField banco;
    private javax.swing.JButton borrar;
    private javax.swing.JTextField busquedaDni;
    private javax.swing.JTextField busquedaId;
    private javax.swing.JTextField busquedaNombre;
    private javax.swing.JButton buttonArticulos;
    private javax.swing.JTextField celular;
    private javax.swing.JTextField compraMinima;
    private javax.swing.JTextField cuil;
    private javax.swing.JTextField dni;
    private javax.swing.JTextField email;
    private javax.swing.JPanel fondoImagen;
    private javax.swing.JButton guardar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JLabel labelApellido;
    private javax.swing.JLabel labelBanco;
    private javax.swing.JLabel labelBusquedaDni;
    private javax.swing.JLabel labelBusquedaId;
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
    private javax.swing.JTextField nombre;
    private javax.swing.JButton nuevo;
    private javax.swing.JTextField numDeCuenta;
    private javax.swing.JPanel panelBusqueda;
    private javax.swing.JPanel panelControlArticulo;
    private javax.swing.JPanel panelDatosProveedor;
    private org.edisoncor.gui.panel.PanelImage panelTitulo;
    private javax.swing.JButton siguiente;
    private javax.swing.JTextField sucursal;
    private javax.swing.JTable tabla;
    private javax.swing.JTable tabla1;
    private javax.swing.JTextField telefono;
    private javax.swing.JTextField tipoCuenta;
    private javax.swing.JLabel titulo;
    // End of variables declaration//GEN-END:variables
}
