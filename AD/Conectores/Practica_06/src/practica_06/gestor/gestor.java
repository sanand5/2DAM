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
import practica_06.gestor.Conexion.DatabaseType;
import practica_06.utilidades.Colors;
import practica_06.utilidades.ReadClient;

/**
 *
 * @author andre
 */
public class gestor {

    ReadClient rc = new ReadClient();
    Conexion conexion = new Conexion();

    public void executeUpdate(String query) {
        try (Connection connection = conexion.getConnection()) {
            // Check if the connection is successful
            if (connection != null) {

                // Execute the provided SQL query
                try (PreparedStatement statement = connection.prepareStatement(query)) {
                    statement.executeUpdate();
                }
            } else {
                Colors.errMsg("Failed to connect to the database.");
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

    // TODO Si no encunetra devuelve?
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
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    public ResultSet executeSelect(String query, Object... params) {
        try {
            Connection connection = conexion.getConnection();
            if (connection != null) {
                PreparedStatement statement = connection.prepareStatement(query);
                for (int i = 0; i < params.length; i++) {
                    statement.setObject(i + 1, params[i]);
                }
                return statement.executeQuery();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
// TODO cerrar conexion
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
            }
        } catch (IOException e) {
            e.printStackTrace();
            Colors.errMsg("El archivo no se ha podido leer: " + path);
        }
        return lines;
    }
    
    protected boolean tableExists(String tableName) {
    DatabaseType databasetype = Conexion.getDataBaseType();
    String query;
    
    if (null == databasetype) {
        Colors.errMsg("Tipo de base de datos no compatible.");
        return false;
    } else {
        switch (databasetype) {
            case MYSQL:
                query = String.format("SELECT COUNT(*) FROM information_schema.tables WHERE table_schema = DATABASE() AND table_name = '%s'", tableName);
                break;
            case POSTGRESQL:
                query = String.format("SELECT COUNT(*) FROM information_schema.tables WHERE table_schema = 'public' AND table_name = '%s'", tableName);
                break;
            default:
                Colors.errMsg("Tipo de base de datos no compatible.");
                return false;
        }

        try (ResultSet resultSet = executeSelect(query)) {
            resultSet.next();
            return resultSet.getInt(1) > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}

}
/* TODO
 * Gestionar lo de abstract
*/
