/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package practica_04;

import iohelpers.Colors;
import iohelpers.ReadClient;
/**
 *
 * @author andre
 */
public class Mantenible {

    String nom;
    String id;
    static ReadClient rc = new ReadClient();

    public Mantenible(String nom, String id) {
        this.nom = nom;
        this.id = id;
    }
    
    public static String pedirNom() {
        String nom;
        do {
            nom = rc.pedirString("Nom: ");
        } while (!comprabarDatos(nom, true, "El nom no pot contindre números"));
        return nom;
    }

    public static String pedirId() {
        String id;
        do {
            id = rc.pedirString("Id: ");
        } while (!comprabarDatos(id, false, "El id han de ser sols 8 números"));
        return id;
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
    

    @Override
    public String toString() {
        return nom + " - " + id;
    }

    public String fromString() {
        String obj = nom + "," + id;
        return obj;
    }
}
