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
public class Alumnes {

    static ArrayList<Alumne> list = new ArrayList<>();

    public void menu() {
        Scanner sc = new Scanner(System.in);
        int menu = 0;
        try {
            boolean repetir = true;
            while (repetir) {
                repetir = false;
                System.out.println("""
                                   Menu Alumne
                                   (0) Salir
                                   (1) Alta
                                   (2) Baixa
                                   (3) Llista
                                   """);
                menu = sc.nextInt();
                if (menu < 0 || menu > 3) {
                    System.out.println("Deus introduir un valor dins del rang");
                    repetir = true;
                    break;
                }
                switch (menu) {
                    case 0 ->
                        System.out.println("Has ixit del menu Alumnes");
                    case 1 ->
                        alta();
                    case 2 ->
                        baixa();
                    case 3 ->
                        mostrarLista();
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
        for (int i = 0; i < list.size(); i++) {
            System.out.println(i + 1 + ". " + list.get(i).nom + " - " + list.get(i).nia);
        }
    }

    public void alta() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nom: ");
        String nom = sc.nextLine();
        System.out.print("NIA: ");
        String nia = sc.nextLine();
        if (buscarNia(nia) == -1) {
            list.add(new Alumne(nom, nia));
        }else {
            System.out.println("El alumne ja existeix");
        }
    }

    public void baixa() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nia: ");
        list.remove(buscarNia(sc.nextLine())); //Todo comprovar que la funcion remove esta bien para eliminar entidades en un arraylist
        //TODO mirar que pasa si no esta en la llista
    }

    public static int buscarNia(String nia) {
        int retorno = -1;
        for (int i = 0; i < list.size();
                i++) {
            if (list.get(i).nia.equals(nia)) {
                retorno = i;
            }
        }
        return retorno;
    }
}
