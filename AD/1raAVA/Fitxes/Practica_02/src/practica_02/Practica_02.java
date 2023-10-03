/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package practica_02;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author 2DAM_Sanz_Andreu
 */
public class Practica_02 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner sc = new Scanner(System.in);
        Alumnes alumnesList = new Alumnes();
        Moduls modulsList = new Moduls();
        boolean repit = true;
        while (repit) {
            System.out.println("""
                           (0) Eixir
                           (1) Menu alumnes
                           (2) Menu Modul
                           (3) Avaluar
                           """);
            System.out.print("Que vols fer: ");
            switch (sc.nextInt()) { //TODO caso  default
                case 0 ->
                    repit = false;
                case 1 ->
                    alumnesList.menu();
                case 2 ->
                    modulsList.menu();
                case 3 -> {//TODO
                }
                default ->
                    throw new AssertionError();
            }
            
        }

    }

}
/*
TODO
- Crec que ni ha una funcio que te busca en un arraylist

*/
