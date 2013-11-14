/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package abm;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelos.Producto;
import modelos.Venta;

/**
 *
 * @author eze
 */
public class ABMVenta implements ABMInterface<Venta> {

    public ABMVenta() {
    }

    @Override
    public void alta(Venta v) {
        ResultSet claveGenerada;
        PreparedStatement statement;
        v.setMonto(calcularMonto(v.getProductos())); //Calculo el monto de la venta
        try {
            //inserto venta en base de datos
            statement = ConnectionDataBase.getConnection().prepareStatement("INSERT INTO venta VALUES (null,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setDouble(1, v.getMonto());
            statement.setString(2, v.getCliente().getId().toString());
            statement.executeUpdate();

            // Se obtiene la clave de la venta generada por la base de datos (idVenta)
            claveGenerada = statement.getGeneratedKeys();
            if (claveGenerada.next()) {
                int idVenta = claveGenerada.getInt(1);

                //inserto los productos vendidos en una venta en base de datos 
                Iterator itr = v.getProductos().iterator();
                Producto prod;
                while (itr.hasNext()) {
                    prod = (Producto) itr.next();
                    statement = ConnectionDataBase.getConnection().prepareStatement("INSERT INTO productosvendidos VALUES (?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
                    statement.setInt(1, idVenta);
                    statement.setInt(2, prod.getNumeroProducto());
                    statement.executeUpdate();
                }
            } else {
                throw new SQLException("Error al crear venta, no se obtuvo la clave generada (idVenta).");
            }

        } catch (SQLException ex) {
            Logger.getLogger(ABMVenta.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void baja(Venta v) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void modificar(Venta v) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private Double calcularMonto(LinkedList<Producto> productos) {
        Iterator itr = productos.iterator();
        Producto prod;
        Double acumMonto = 0.0;
        while (itr.hasNext()) {
            prod = (Producto) itr.next();
            acumMonto += prod.getPrecioVenta();
        }
        return acumMonto;
    }
}
