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
            boolean repetir = true;
            while (repetir) {
                repetir = false;
                System.out.println("""
                                   Menu Avaluar
                                   (0) Salir
                                   (1) Qualificar
                                   (2) Modificar
                                   (3) Traure bolletí de notes
                                   """);
                menu = sc.nextInt();
                if (menu < 0 || menu > 3) {
                    System.out.println("Deus introduir un valor dins del rang");
                    repetir = true;
                    break;
                }
                //sw
            }
        } catch (InputMismatchException e) {
            System.out.println("Deus introduir un numero");
            menu();
        }
    }
    public void qualificar() {//TODO buffer
        Scanner sc = new Scanner(System.in);
        System.out.print("Nia del alumne: ");
        String nia = sc.nextLine();;
        System.out.print("Modul a qualificar: ");
        String modul = sc.nextLine();;
        System.out.print("Quina Avaluacio vols qualificar: ");
        System.out.println("""
                           (1) 1ra Avaluació
                           (2) 2na Avaluació
                           (3) 3ra Avaluació
                           (4) Totes
                           """);
        int qualificar = sc.nextInt();
        int posAlumne = Alumnes.buscarNia(nia);
        int posModul = Alumne.buscarModul(modul);
        Matricula matr = Alumne.modulsList.get(posModul).m;
        double nota;
        switch (qualificar) {
            case 1:
                System.out.print("Nota 1ra Avaluació: ");
                 nota = sc.nextDouble(); //TODO input mismach
                matr = new Matricula();
                matr.setNotes(nota, 0);
                break;
            case 2:
                System.out.print("Nota 2na Avaluació: ");
                 nota = sc.nextDouble(); //TODO input mismach
                matr = new Matricula();
                matr.setNotes(nota, 1);
                break;
            case 3:
                System.out.print("Nota 3ra Avaluació: ");
                 nota = sc.nextDouble(); //TODO input mismach
                matr = new Matricula();
                matr.setNotes(nota, 2);
                break;
            case 4:
                double notes[] = new double[3];
                for (int i = 0; i < notes.length; i++) {
                    System.out.println("Nota "+i+1+": ");
                    notes[i] = sc.nextDouble();
                }
                matr = new Matricula(notes[0], notes[1], notes[2]);
                break;
            default:
                throw new AssertionError();//todo
        }
        
        
    }
}
