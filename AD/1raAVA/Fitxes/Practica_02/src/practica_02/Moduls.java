/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package practica_02;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author andre
 */
public class Moduls {

    ArrayList<Modul> list = new ArrayList<>();

    public void menu() {
        Scanner sc = new Scanner(System.in);
        int menu = 0;
        try {
            boolean repetir = true;
            while (repetir) {
                repetir = false;
                System.out.println("""
                                   Menu Modul
                                   (0) Salir
                                   (1) Alta
                                   (2) Baixa
                                   (3) Llista
                                   (4) Matricular Alumen 
                                   """); 
                menu = sc.nextInt();
                if (menu < 0 || menu > 4) {
                    System.out.println("Deus introduir un valor dins del rang");
                    repetir = true;
                    break;
                }
                switch (menu) {
                    case 0 ->
                        System.out.println("Has ixit del menu Moduls");
                    case 1 ->
                        alta();
                    case 2 ->
                        baixa();
                    case 3 ->
                        mostrarLista();
                    case 4 ->
                        matricularAlumne2();
                    default ->
                        System.out.println("Eixa opció no existeix, tornaras al menu principal");
                }
            }
        } catch (InputMismatchException e) {
            System.out.println("Deus introduir un numero");
            menu();
        }
    }

    public void mostrarLista() {
        if (list.isEmpty()) {
            System.out.println("No hi han moduls");
        } else {
            for (int i = 0; i < list.size(); i++) {
                System.out.println(i + 1 + ". " + list.get(i).nom);
            }
        }
    }

    public void alta() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nom: ");
        String nom = sc.nextLine();
        if (buscarModul(nom) == -1) {
            list.add(new Modul(nom));
        } else {
            System.out.println("El modul ja existeix");
        }
    }

    public void baixa() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nom: ");
        int pos = buscarModul(sc.nextLine());
        if (pos == -1) {
            System.out.println("Error: El modul no s'ha trobat");
        } else {
            list.remove(pos); //Todo comprovar que la funcion remove esta bien para eliminar entidades en un arraylist
        }//TODO compravar si no trova el modul lo que retorna
    }

    public void matricularAlumne() { //gestionar excepcions
        Scanner sc = new Scanner(System.in);
        
        System.out.print("Nia del alumne: ");
        String nia = sc.nextLine();
        
        System.out.print("Nom del modul: ");
        String modul = sc.nextLine();

        int posModul = buscarModul(modul);
        int posNia = Alumnes.buscarNia(nia);
        if (posModul != -1 || posNia != -1) {
            Modul m = list.get(posModul);
            Alumne matriculat = Alumnes.list.get(posNia); //alumne no trobat
            matriculat.matriculasList.add(new Matricula(m));
        } else {
            System.out.println("Error: El modul o el nia no s'han trobat");
        }

    }
    public void matricularAlumne2() { //TODO gestionar excepcions
        Scanner sc = new Scanner(System.in);
        
        System.out.print("Nia del alumne: ");
        String nia = sc.nextLine();
        
        System.out.print("Nom del modul: ");
        String modul = sc.nextLine();

        int posModul = buscarModul(modul);
        int posNia = Alumnes.buscarNia(nia);
        if (posModul != -1 || posNia != -1) {
            Modul m = list.get(posModul);
            Alumne matriculat = Alumnes.list.get(posNia);
            matriculat.modulsList.add(m);
        } else {
            System.out.println("Error: El modul o el alumne no s'han trobat");
        }

    }

    public int buscarModul(String nom) {
        int retorno = -1;
        for (int i = 0; i < list.size();
                i++) {
            if (list.get(i).nom.equals(nom)) {
                retorno = i;
            }
        }
        return retorno;
    }
}
