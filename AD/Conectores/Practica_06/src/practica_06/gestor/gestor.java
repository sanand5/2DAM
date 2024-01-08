/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package practica_06.gestor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import practica_06.utilidades.Colors;

/**
 *
 * @author andre
 */
public class gestor {
    public void executeUpdate(String query) {
        Conexion conexion = new Conexion(DataConexion.URL, DataConexion.USER, DataConexion.PASSWORD);

        try (Connection connection = conexion.getConnection()) {
            // Check if the connection is successful
            if (connection != null) {
                System.out.println("Connected to the database!");

                // Execute the provided SQL query
                try (PreparedStatement statement = connection.prepareStatement(query)) {
                    int rowsAffected = statement.executeUpdate();

                    if (rowsAffected > 0) {
                        System.out.println("Update successful. Rows affected: " + rowsAffected);
                    } else {
                        System.out.println("Update failed. No rows affected.");
                    }
                }
            } else {
                System.out.println("Failed to connect to the database.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close the connection when done
            conexion.closeConnection();
        }
    }
    
    public ResultSet executeSelect(String query) {
    // Verifica si la lista de columnas no está vacía

    Conexion conexion = new Conexion(DataConexion.URL, DataConexion.USER, DataConexion.PASSWORD);

    try {
        Connection connection = conexion.getConnection();

        // Check if the connection is successful
        if (connection != null) {

            PreparedStatement statement = connection.prepareStatement(query);
            return statement.executeQuery();
        } else {
            Colors.errMsg("No se ha podido conectar a la base de datos.");
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return null;
}


}
