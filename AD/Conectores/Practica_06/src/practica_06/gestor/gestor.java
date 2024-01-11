/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package practica_06.gestor;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import practica_06.utilidades.Colors;
import practica_06.utilidades.ReadClient;

/**
 *
 * @author andre
 */
public class gestor {

    ReadClient rc = new ReadClient();

    public void executeUpdate(String query) {
        Conexion conexion = new Conexion(DataConexion.URL, DataConexion.USER, DataConexion.PASSWORD);

        try (Connection connection = conexion.getConnection()) {
            // Check if the connection is successful
            if (connection != null) {
                Colors.debMsg("Connected to the database!");

                // Execute the provided SQL query
                try (PreparedStatement statement = connection.prepareStatement(query)) {
                    int rowsAffected = statement.executeUpdate();

                    if (rowsAffected > 0) {
                        Colors.debMsg("Update successful. Rows affected: " + rowsAffected);
                    } else {
                        Colors.debMsg("Update failed. No rows affected.");
                    }
                }
            } else {
                Colors.debMsg("Failed to connect to the database.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Close the connection when done
            conexion.closeConnection();
        }
    }

    // Si no encunetra devuelve?
    public <T> T select(String select, String from, String where, Class<T> returnType, Object... params) {
        String query = "SELECT " + select + " FROM " + from + " WHERE " + where;
        T result = null;

        try {
            ResultSet rs = executeSelect(query, params);
            if (rs.next()) {
                if (returnType.equals(Integer.class)) {
                    result = returnType.cast(rs.getInt(1));
                } else if (returnType.equals(String.class)) {
                    result = returnType.cast(rs.getString(1));
                }
            } else {
                Colors.debMsg("No se encontraron resultados para la consulta.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    public ResultSet executeSelect(String query, Object... params) {
        Conexion conexion = new Conexion(DataConexion.URL, DataConexion.USER, DataConexion.PASSWORD);
        try {
            Connection connection = conexion.getConnection();
            if (connection != null) {
                PreparedStatement statement = connection.prepareStatement(query);
                for (int i = 0; i < params.length; i++) {
                    statement.setObject(i + 1, params[i]);
                }
                return statement.executeQuery();
            } else {
                Colors.errMsg("No se ha podido conectar a la base de datos.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    protected void write(String path, String datos) {
        try {
            File file = new File(path);
            if (file.exists()) {
                file.delete();
            } else {
                file.createNewFile();
            }
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                writer.write(datos);
                System.out.println("Datos escritos con éxito en el archivo: " + path);
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error al escribir en el archivo: " + path);
        }
    }
    
    protected ArrayList<String> read(String path) {
        ArrayList<String> lines = new ArrayList<>();

        try {
            File file = new File(path);
            if (!file.exists()) {
                Colors.errMsg("El archivo no existe: " + path);
                return lines;
            }
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    lines.add(line);
                }
                Colors.debMsg("Datos leídos con éxito desde el archivo: " + path);
            }
        } catch (IOException e) {
            e.printStackTrace();
            Colors.errMsg("El archivo no se ha podido leer: " + path);
        }

        return lines;
    }

}
