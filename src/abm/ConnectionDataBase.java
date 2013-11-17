/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package abm;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author eze
 */
public class ConnectionDataBase {

    public static Connection connection;

    public ConnectionDataBase() {
        connection = null;
    }
    
    public void createConnection() {
        try {
            //Cargamos el puente JDBC =&gt; Mysql
            System.out.println("Intentando cargar el conector...");
            Class.forName("com.mysql.jdbc.Driver");
            //Intentamos conectarnos a la base de Datos en este caso una base llamada temp
            System.out.println("Conectando a la base...");
            connection = DriverManager.getConnection("jdbc:mysql://localhost/sexshop", "root", "root"); //modificar acaaA!!!!!
            System.out.println("Conexion a BD establecida");
        } catch (SQLException ex) {
            System.out.println("Error de mysql");
        } catch (ClassNotFoundException e) {
        } catch (Exception e) {
            System.out.println("Se produjo un error inesperado: " + e.getMessage());
        }
        setConnection(connection);
    }

    public boolean connectionIsClose() {
        return connection == null;
    }
    
    public void closeConnection() {
        try {
            ConnectionDataBase.connection.close();
            System.out.println("Cerrar conexion con jdbc:mysql://localhost/sexshop ... OK");
        } catch (SQLException ex) {
            System.out.println("Imposible cerrar conexion ... FAIL");
        }
    }

    public static Connection getConnection() {
        return connection;
    }

    public static void setConnection(Connection connection) {
        ConnectionDataBase.connection = connection;
    }

    
    
}
