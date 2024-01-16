/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package practica_06.gestor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import practica_06.utilidades.Colors;

/**
 *
 * @author andre
 */
public class Conexion {

    //final static String URL = "jdbc:postgresql://localhost/";
    final static String URL = "jdbc:mysql://10.0.219.21/";
    final static String DB = "10813358";
    final static String USER = "10813358";
    final static String PASSWORD = "10813358";
    Connection con = null;

    public Connection getConnection() {
        try {
            con = DriverManager.getConnection(URL+DB, USER, PASSWORD);
        } catch (SQLException e) {
            Colors.errMsg("No se ha podido realizar la conexión correctamente");
        }
        return con;
    }
    
    public enum DatabaseType {
        MYSQL,
        POSTGRESQL
    }
    
    public static DatabaseType getDataBaseType() {
        DatabaseType databasetype = null;
        if (URL.contains("postgresql")) {
            databasetype = DatabaseType.POSTGRESQL;
        }else if (URL.contains("mysql")) {
            databasetype = DatabaseType.MYSQL;
        }
        return databasetype;
    }
}
