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

    String URL;
    String User;
    String Password;
    Connection con = null;

    public Conexion(String URL, String User, String Password) {
        this.URL = URL;
        this.User = User;
        this.Password = Password;
    }

    public Connection getConnection() {
        try {
            con = DriverManager.getConnection(URL, User, Password);
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

}
