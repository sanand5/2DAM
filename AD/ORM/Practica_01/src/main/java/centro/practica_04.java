/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package centro;

import org.hibernate.Session;
import org.hibernate.query.NativeQuery;

import persistencia.Conexion;
import persistencia.ORM;
import iohelpers.*;
import java.util.List;

/**
 *
 * @author 2DAM_Sanz_Andreu
 */
public class practica_04 {
    private static final Conexion conexion=new Conexion();
    /**
     * @param args the command line arguments
     */

    public static void main(String[] args) {
        Session sesion = conexion.getSessionFactory().openSession();
        String consultaSQL = "SELECT * FROM Persona";
        NativeQuery<Persona> nativeQuery = sesion.createNativeQuery(consultaSQL, Persona.class);
        List<Persona> personas = nativeQuery.getResultList(); //error: List cannot be resolved to a type
        System.out.println(personas);
    }

}
