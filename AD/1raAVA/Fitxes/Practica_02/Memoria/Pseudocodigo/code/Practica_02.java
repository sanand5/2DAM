/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package practica_02;
/**
 *
 * @author 2DAM_Sanz_Andreu
 */
public class Practica_02 {
    /**
     * @param args the command line arguments
     */
    static Alumnes alumnesList = new Alumnes();
    static Moduls modulsList = new Moduls();

    public static void main(String[] args) {
        
        ReadClient rc = new ReadClient();
        
        Matriculas matriculas = new Matriculas();
        boolean repit = true;

        while (repit) {
            System.out.println("""
                           (0) Eixir
                           (1) Menu alumnes
                           (2) Menu Modul
                           (3) Avaluar""");
            int menu = rc.pedirInteger("?");
            switch (menu) {

                case -1 ->
                    repit = false;
                case 1 ->
                    alumnesList.menu();
                case 2 ->
                    modulsList.menu();
                case 3 ->
                    matriculas.menu();
                default -> {
                    Colors.warMsg("Deus de introduir un valor valid");
                }

            }

        }

    }
}