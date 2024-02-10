package com.existdb;

import com.existdb.gestor.Conexion;
import com.existdb.utilidades.Colors;

public class Main {
    public static void main(String[] args) {
        try {
            Conexion.startConexion();
            if (Conexion.isConexionEstablecida()) {
                menus menus = new menus();
                menus.mainMenu();
            }
        } catch (Exception e) {
            Colors.errMsg("Error inesperado");
        }
    }
}