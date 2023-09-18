/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package computerworks;

import java.util.Scanner;

/**
 *
 * @author 2DAM_Sanz_Andreu
 */
public class ComputerWorks {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner sc = new Scanner(System.in);

            boolean rep;
        do {
            rep=false;
            String[] areas = {"logística", "contabilidad", "finanzas", "informático", "marketing", "ventas", "recursos humanos"};
            System.out.println("Dime el numero del departamento : ");
           
            int menu = sc.nextInt();
            switch (menu) {
                case 1:
                    System.out.println("La teua peticio va al departament de :" + areas[0]);
                    break;
                case 2:
                    System.out.println("La teua peticio va al departament de :" + areas[1]);
                    break;
                case 3:
                    System.out.println("La teua peticio va al departament de :" + areas[2]);
                    break;
                case 4:
                    System.out.println("La teua peticio va al departament de :" + areas[3]);
                    break;
                case 5:
                    System.out.println("La teua peticio va al departament de :" + areas[4]);
                    break;
                case 6:
                    System.out.println("La teua peticio va al departament de :" + areas[5]);
                    break;
                default:
                    rep = true;
            }
        } while (rep);
    }
}
