/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package practica_06.gestor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author andre
 */
public class Conexion {

    final static String URL = "jdbc:postgresql://192.168.0.36/10813358";
    //final static String URL = "jdbc:mysql://localhost/10813358_";
    final static String USER = "10813358";
    final static String PASSWORD = "10813358";
    Connection con = null;

    public Connection getConnection() {
        try {
            con = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.getMessage();
        }
        return con;
    }
    
    public void closeConnection() {
        try {
            con.close();
            con = null;
        } catch (SQLException e) {
            e.getMessage();
        }
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
