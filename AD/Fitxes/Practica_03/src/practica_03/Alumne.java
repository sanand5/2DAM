/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package practica_03;

import iohelpers.Colors;
import iohelpers.ReadClient;
import java.util.ArrayList;

/**
 *
 * @author andre
 */
public class Alumne {

    String nom, nia;
    private static ReadClient rc = new ReadClient();
    
    public Alumne(String nom, String nia) {
        this.nom = nom;
        this.nia = nia; 
    }
    
    public static boolean comprabarDatos(String str, boolean option, String msgErr) { //option = name or nia
        boolean correcto = false;
        String matches;
        if (option) {
            matches = "\\D*";
        } else {
            matches = "\\d{8}";
        }

        if (str.matches(matches)) {
            correcto = true;
        } else {
            Colors.warMsg(msgErr);
            correcto = false;
        }
        return correcto;
    }
    
    public static String pedirName() {
        String name;
        do {
            name = rc.pedirString("Nom del alumne: ");
        } while (!comprabarDatos(name, true, "El nom no pot contindre números"));
        return name;
    }
    
    public static String pedirNia() {
        String nia;
        do {
            nia = rc.pedirString("Nia del alumne: ");
        } while (!comprabarDatos(nia, false, "El han de ser sols 8 números"));
        return nia;    }
    
    
    @Override
    public String toString() {
        return nom + " - " + nia;
    }
    public String fromString() {
        String obj = nom+","+nia;
        return obj;
    }
}
