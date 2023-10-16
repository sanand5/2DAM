/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package practica_04;

import iohelpers.*;

/**
 *
 * @author 2DAM_Sanz_Andreu
 */
public class practica_04 {

    /**
     * @param args the command line arguments
     */
    static Alumnes alumnesList = new Alumnes();
    static final String ALUMNES_PATH = "./Files/AlumnesList.txt";
    static Moduls modulsList = new Moduls();
    static final String MODULS_PATH = "./Files/ModulsList.txt";
    static Matriculas matriculasList = new Matriculas();
    static final String MATRICULES_PATH = "./Files/MatriculesList.txt";

    public static void main(String[] args) {
        ReadClient rc = new ReadClient();
        boolean repit = true;
        while (repit) {
            System.out.println("""
                           (0) Eixir
                           (1) Menu Alumnes
                           (2) Menu Mòdul
                           (3) Avaluar""");
            int menu = rc.pedirInteger("?");
            switch (menu) {

                case 0 ->
                    repit = false;
                case 1 ->
                    alumnesList.menu();
                case 2 ->
                    modulsList.menu();
                case 3 ->
                    matriculasList.menu();
                default -> {
                    Colors.warMsg("Deus de introduir un valor valid");
                }
            }
        }

    }
    
}
