/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package computerworks;

import java.util.InputMismatchException;
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
        System.out.println("""
                           Departamentos :
                           (0) Salir
                           (1) Logistica
                           (2) Contabilidad
                           (3) Finanzas
                           (4) Informa
                           (5) Marketing y ventas
                           (6) Recursos Humanos
                           """);

        boolean rep=true;
        do {
            try {
                String[] areas = {"logistica", "contabilidad", "finanzas", "informático", "marketing", "ventas", "recursos humanos"};
                System.out.print("Dime el numero del departamento : ");
                int menu = sc.nextInt();

                switch (menu) {
                    case 0:
                        System.out.println("FIN");
                        rep=false;
                        break;
                    case 1:
                        System.out.println("Tu peticion va al departamento de " + areas[0]);
                        break;
                    case 2:
                        System.out.println("Tu peticion va al departamento de " + areas[1]);
                        break;
                    case 3:
                        System.out.println("Tu peticion va al departamento de " + areas[2]);
                        break;
                    case 4:
                        System.out.println("Tu peticion va al departamento de " + areas[3]);
                        break;
                    case 5:
                        System.out.println("Tu peticion va al departamento de " + areas[4]);
                        break;
                    case 6:
                        System.out.println("Tu peticion va al departamento de " + areas[5]);
                        break;
                    default:
                        System.out.println("Debes introducir un numero entre 1-6");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("***Err : Debes introducir un numero***");
                sc.next();
            } catch (Exception e) {
                System.out.println("***Err : Error inesperado***");
                sc.next();
            }
        } while (rep);
    }
}
