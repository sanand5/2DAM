/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package practica_02;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author andre
 */
public class Matriculas {

    public void menu() {
        Scanner sc = new Scanner(System.in);
        int menu = 0;
        try {
            //Pedir datos alumno
            System.out.print("Nia del alumne: ");
            String nia = sc.nextLine();//TODO inputmismach
            System.out.print("Modul a qualificar: ");
            String modul = sc.nextLine();//TODO inputmismach
            Alumne a = Alumnes.list.get(Alumnes.buscarNia(nia));
            int posModul = a.buscarModul(modul);
            Matricula matr = a.modulsList.get(posModul).m;
            
            boolean repetir = true;
            while (repetir) {

                System.out.print("""
                                   Menu Avaluar
                                   (0) Salir
                                   (1) Qualificar
                                   (2) Modificar
                                   (3) Traure bolletí de notes
                                   
                                   ?
                                   """);
                menu = sc.nextInt();
                switch (menu) {
                    case 0:
                        repetir = false;
                        System.out.println("Has ixit del menu Avaluar");
                        break;
                    case 1:
                        qualificar(matr);
                        break;
                    case 2:
                        //TODO Modificar
                        break;
                    case 3:
                        mostrar();
                        break;
                    default:
                        System.out.println(Colors.ANSI_YELLOW+"Deus introduir un valor valid");
                }
            }
        } catch (InputMismatchException e) {
            System.out.println(Colors.ANSI_YELLOW+"Deus introduir un numero");
            menu();
        }
    }

    public void qualificar(Matricula matr) {//TODO buffer
        Scanner sc = new Scanner(System.in);

        System.out.print("Quantes notes vols afegir: ");
        int cant = sc.nextInt(); //TODO inputmismach

        ;

        for (int i = 0; i < cant; i++) {
            System.out.print("Nota a afegir: ");
            double nota = sc.nextDouble();
            matr.addNota(nota);
        }
    }

    public static void modificar(Matricula matr) {
        Scanner sc = new Scanner(System.in);
        matr.mostrarNotes();
        System.out.print("Quina nota vols afegir: ");
        int pos = sc.nextInt()-1; //TODO input mismach
        System.out.print("Disme la nota que vols ficar: ");
        double nota = sc.nextDouble();//TODO input mismach
        matr.setNota(nota, pos);
        
    }

    public void mostrar() {
        for (int i = 0; i < Alumnes.list.size(); i++) {
            Alumnes.list.get(i).mostrar();
        }
    }
}
