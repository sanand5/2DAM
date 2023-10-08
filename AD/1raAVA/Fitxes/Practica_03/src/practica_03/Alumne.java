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
    ArrayList<Modul> modulsList;
    private static ReadClient rc = new ReadClient();

    public Alumne(String nom, String nia) {
        this.nom = nom;
        this.nia = nia;
        modulsList = new ArrayList<>();
    }

    public void mostrar() {
        System.out.printf("""
                           %s / %s
                           """, nom, nia);
        for (int i = 0; i < modulsList.size(); i++) {
            Modul m = modulsList.get(i);
            System.out.print(m.nom + ": ");
            Matricula ma = m.matr;
            ma.mostrarNotes(false);
        }
    }
    
    private static String comprabarDatos(String matches, String msg, String msgErr) { //TODO comprovar bucle
        String str = null;
        boolean correcto = false;
        while (!correcto) {
            str = rc.pedirString(msg);
            if (str.matches(matches)) {
                correcto = true;
            } else {
                Colors.warMsg(msgErr);
                correcto = false;
            }
        }
        return str;
    }
    
    public static String pedirName() {
        return comprabarDatos("\\D*", "Nom del alumne: ", "El nom no pot contindre números");
    }
    
    public static String pedirNia() {
        return comprabarDatos("\\d{8}", "Nia del alumne: ", "El han de ser sols 8 números");
    }
}
