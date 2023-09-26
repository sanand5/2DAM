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
        ArrayList<Alumne> alumnes = new ArrayList();
        System.out.println("""
                            Mantindre Alumnes
                                (0) Eixir
                                (1) Donar de Alta
                                (2) Donar de Baxa
                                (3) Llistar
                           
                            Mantindre Mòduls
                                (4) Donar de Alta
                                (5) Donar de Baixa
                                (6) Llistar
                                (7) Matricular Alumne a un modul

                            Avaluar
                                (8) Qualificar
                                (9) Modificar
                                (10) Traure bolletí de notes
                           """);
        boolean exit = false;
        do {
            try {
                
            } catch (InputMismatchException e) {
                System.out.println("Error : Deus introduir numeros entre el 0 i el 10");
            }
            switch (sc.nextInt()) {
            case 0:
                System.out.println("Adeu!!");
                exit = true;
                break;
            case 1:
                System.out.println("Replena el formulari: ");
                String nom, cognmos, nia, DataNaixement;
                System.out.print("Nom del alumne: ");
                nom = sc.nextLine();
                System.out.print("Cignoms del alumne: ");
                cognmos = sc.nextLine();
                System.out.print("NIA del alumne: ");
                nia = sc.nextLine();
                System.out.print("Data de naixement del alumne (XX/XX/XXXX): ");
                DataNaixement = sc.nextLine();
                alumnes.add(new Alumne(nom, cognmos, nia, DataNaixement));
                break;
            case 2:
                
                break;
            case 3:
                
                break;    
            case 4:
                
                break;
            case 5:
                
                break;
            case 6:
                
                break;
            case 7:
                
                break;
            case 8:
                
                break;
            case 9:
                
                break;
            case 10:
                
                break;
                
            default:
                throw new AssertionError();
        }
        } while (exit);
        
        
        
        
        
    }
    
}
