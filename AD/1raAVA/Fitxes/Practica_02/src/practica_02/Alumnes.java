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

    static ArrayList<Alumne> list = new ArrayList<>(); //TODO es estatic
    ReadClient rc = new ReadClient();

    public void menu() {
        int menu = 0;
        try {
            boolean repetir = true;
            while (repetir) {
                System.out.print("""
                                   Menu Alumne
                                   (0) Salir
                                   (1) Alta
                                   (2) Baixa
                                   (3) Llista""");
                menu = rc.pedirInteger("?");
                switch (menu) {
                    case 0 -> {
                        System.out.println("Has ixit del menu Alumnes");
                        repetir = false;
                    }
                    case 1 ->
                        alta();
                    case 2 ->
                        baixa();
                    case 3 ->
                        mostrarLista();
                    case -1 ->
                        test();
                    default -> 
                        System.out.println(Colors.ANSI_YELLOW+"Deus introduir un valor valid");
                }
            }
        } catch (InputMismatchException e) {
            System.out.println(Colors.ANSI_YELLOW+"Deus introduir un numero");
            menu();
        }
    }

    public void mostrarLista() {
        for (int i = 0; i < list.size(); i++) {
            System.out.println(i + 1 + ". " + list.get(i).nom + " - " + list.get(i).nia);
        }
    }

    public void alta() {
        String nom = rc.pedirString("Nom: ");
        String nia = rc.pedirString("NIA: ");
        if (buscarNia(nia) == -1) {
            list.add(new Alumne(nom, nia));
        } else {
            System.out.println(Colors.ANSI_YELLOW+"El alumne ja existeix");
        }
    }

    public void baixa() {
        int pos = buscarNia(rc.pedirString("Nia: ")); //TODO excepcion
        if (pos == -1) {
            System.out.println(Colors.ANSI_RED+"Error: El alumne no existeix");
        } else {
            list.remove(pos); //Todo comprovar que la funcion remove esta bien para eliminar entidades en un arraylist
        }

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

    public void test() { //Falta comprobar si el alumne escriu el que no toca
        //Donar de alta
        System.out.println("Donar de alta a un alumne");
        String nom = "Andreu Sanz";
        String nia = "10813358";
        if (buscarNia(nia) == -1) {
            list.add(new Alumne(nom, nia));
        } else {
            System.out.println("El alumne ja existeix");
        }
        mostrarLista();
        System.out.println("Donar de alta a una altra alumna");
        nom = "Laura Llorens";
        nia = "4562187124";
        if (buscarNia(nia) == -1) {
            list.add(new Alumne(nom, nia));
        } else {
            System.out.println("El alumne ja existeix");
        }
        mostrarLista();
        System.out.println("Probocar Error perque el alumne ja existeix");
        nom = "Andreu Sanz";
        nia = "10813358";
        if (buscarNia(nia) == -1) {
            list.add(new Alumne(nom, nia));
        } else {
            System.out.println("El alumne ja existeix");
        }
        mostrarLista();
        //Donar de baixa
        System.out.println("Eliminem a Andreu Sanz");
        int pos;
        pos = buscarNia("10813358");
        if (pos == -1) {
            System.out.println("Error: El alumne no existeix");
        } else {
            list.remove(pos);
        }
        mostrarLista();
        System.out.println("Intentem eliminar a un alumne que no existeix");
        pos = buscarNia("10813358");
        if (pos == -1) {
            System.out.println("Error: El alumne no existeix");
        } else {
            list.remove(pos);
        }
        mostrarLista();
    }

}
