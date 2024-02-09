package com.existdb;

import com.existdb.gestor.Conexion;
import com.existdb.gestor.ClientComands;
public class Main {
    public static void main(String[] args) {
        try {
            ClientComands c = new ClientComands();
            Conexion.iniciarConexion();
            //c.altaAlumno();
            //c.altaAlumno();
            c.bajaAlumno();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}