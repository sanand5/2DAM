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

    public boolean testConexion() {
        try {
            Connection connection = conexion.getConnection();
            if (connection != null && !connection.isClosed()) {
                Colors.okMsg("¡Conexión exitosa!");
                return true;
            } else {
                Colors.errMsg("La conexión está cerrada o es nula.");

            }
        } catch (SQLException e) {
            Colors.errMsg("No se ha podido conectar a la base de datos");
        }
        return false;
    }

    protected void executeUpdate(String query) {
        try (Connection connection = conexion.getConnection()) {
            if (connection != null) {
                try (PreparedStatement statement = connection.prepareStatement(query)) {
                    statement.executeUpdate();
                }
            } else {
                Colors.errMsg("No se ha podido conectar a la base de datos");
            }
        } catch (SQLException e) {
            Colors.errMsg("Imposible hacer la consulta");
        } catch (Exception e) {
            Colors.errMsg("Error inesperado");
        }
    }
    
    protected <T> T select(String select, String from, String where, Class<T> returnType, Object... params) {
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
            Colors.errMsg("Imposible hacer la consulta");
        }

        return result;
    }

    protected ResultSet executeSelect(String query, Object... params) {
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
            Colors.errMsg("Imposible hacer la consulta");
        } catch (Exception e) {
            Colors.errMsg("Error inesperado");
        }
        return null;
    }

    protected void createTable(String tableName, String queryMYSQL, String queryPOSTGRESQL) {
        DatabaseType databaseType = Conexion.getDataBaseType();
        if (!tableExists(tableName)) {
            Colors.debMsg("Tabla " + tableName + " creada.");
            String createTableQuery = "";

            if (databaseType == DatabaseType.MYSQL) {
                createTableQuery = queryMYSQL.formatted(tableName);
            } else if (databaseType == DatabaseType.POSTGRESQL) {
                createTableQuery = queryPOSTGRESQL;
            }
            executeUpdate(createTableQuery);
        }
    }

    protected String getTitulo(String tableName) {
        String titulo = String.format("##### LISTA DE %s EN DB : %s #####%n", tableName.toUpperCase(), Conexion.DB);
        String subrallado = "";
        for (int i = 2; i < titulo.length(); i++) {
            subrallado += "#";
        }
        return titulo + subrallado;
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
                Colors.okMsg("Datos escritos con éxito en el archivo: " + path);
            }
        } catch (IOException e) {
            Colors.errMsg("Nop de ha podido escribir en el archivo: " + path);
        } catch (Exception e) {
            Colors.errMsg("Error inesperado");
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
            Colors.errMsg("El archivo no se ha podido leer: " + path);
        } catch (Exception e) {
            Colors.errMsg("Error inesperado");
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
                Colors.errMsg("Error en la ejecución.");
                return false;
            }
        }
    }
}