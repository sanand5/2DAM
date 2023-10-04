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
                
                System.out.print("""
                                   Menu Modul
                                   (0) Salir
                                   (1) Alta
                                   (2) Baixa
                                   (3) Llista
                                   (4) Matricular Alumen 
                                   
                                   ?
                                   """); 
                menu = sc.nextInt();
                switch (menu) {
                    case -1 ->
                        test();
                    case 0 ->{
                        repetir = false;
                        System.out.println("Has ixit del menu Moduls");
                    }
                    case 1 ->
                        alta();
                    case 2 ->
                        baixa();
                    case 3 ->
                        mostrarLista();
                    case 4 ->
                        matricularAlumne2();
                    default ->
                        System.out.println("Deus introduir un valor valid");
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
            list.get(pos).eliminar();
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
            //matriculat.matriculasList.add(new Matricula(m));
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
        if (posModul != -1 && posNia != -1) {
            Modul m = new Modul(modul);
            Alumne matriculat = Alumnes.list.get(posNia);
            matriculat.modulsList.add(m); //TODO comprobar per a con clabar a un alumen en el mateix modul varies vegades
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
    
    public void test() {
        //ALta
        System.out.println("Mostrar llista buida");
        mostrarLista();
        
        System.out.println("Afegir un modul a la llista");
        String nom = "Matematiques";
        if (buscarModul(nom) == -1) {
            list.add(new Modul(nom));
        } else {
            System.out.println("El modul ja existeix");
        }
        mostrarLista();
        System.out.println("Afegir un modul ja existent a la llista");
        nom = "Matematiques";
        if (buscarModul(nom) == -1) {
            list.add(new Modul(nom));
        } else {
            System.out.println("El modul ja existeix");
        }
        mostrarLista();
        System.out.println("Afegir 3 moduls a la llista");
        nom = "Castella";
        if (buscarModul(nom) == -1) {
            list.add(new Modul(nom));
        } else {
            System.out.println("El modul ja existeix");
        }
        nom = "Valencia";
        if (buscarModul(nom) == -1) {
            list.add(new Modul(nom));
        } else {
            System.out.println("El modul ja existeix");
        }
        nom = "Angles";
        if (buscarModul(nom) == -1) {
            list.add(new Modul(nom));
        } else {
            System.out.println("El modul ja existeix");
        }
        mostrarLista();
        
        //Baixa
        System.out.println("Eliminar modul");
        int pos = buscarModul("Matematiques");
        if (pos == -1) {
            System.out.println("Error: El modul no s'ha trobat");
        } else {
            list.get(pos).eliminar();
            list.remove(pos);
        }
        System.out.println("Eliminar modul que no existeix");
        pos = buscarModul("Matematiques");
        if (pos == -1) {
            System.out.println("Error: El modul no s'ha trobat");
        } else {
            list.get(pos).eliminar();
            list.remove(pos);
        }
        
        //Matricular alumne
        System.out.println("Afegir un alumne a un modul");
        String nia = "4562187124";
        String modul = "Valencia";
        int posModul, posNia;
        posModul = buscarModul(modul);
        posNia = Alumnes.buscarNia(nia);
        if (posModul != -1 || posNia != -1) {
            Modul m = new Modul(modul);
            Alumne matriculat = Alumnes.list.get(posNia);
            matriculat.modulsList.add(m); 
        } else {
            System.out.println("Error: El modul o el alumne no s'han trobat");
        }
        System.out.println("Nia no existeix");
        nia = "4562123424234214";
        modul = "Valencia";
        posModul = buscarModul(modul);
        posNia = Alumnes.buscarNia(nia);
        if (posModul != -1 && posNia != -1) {
            Modul m = new Modul(modul);
            Alumne matriculat = Alumnes.list.get(posNia);
            matriculat.modulsList.add(m); 
        } else {
            System.out.println("Error: El modul o el alumne no s'han trobat");
        }
        System.out.println("Modul no existeix");
        nia = "4562187124";
        modul = "Vasdffdsfdsafdsaflencia";
        posModul = buscarModul(modul);
        posNia = Alumnes.buscarNia(nia);
        if (posModul != -1 && posNia != -1) {
            Modul m = new Modul(modul);
            Alumne matriculat = Alumnes.list.get(posNia);
            matriculat.modulsList.add(m); 
        } else {
            System.out.println("Error: El modul o el alumne no s'han trobat");
        }
        System.out.println("Afegir un alumne a varios moduls");
         nia = "4562187124";
         modul = "Castella";
       
        posModul = buscarModul(modul);
        posNia = Alumnes.buscarNia(nia);
        if (posModul != -1 && posNia != -1) {
            Modul m = new Modul(modul);
            Alumne matriculat = Alumnes.list.get(posNia);
            matriculat.modulsList.add(m); 
        } else {
            System.out.println("Error: El modul o el alumne no s'han trobat");
        }
        System.out.println("Angles");
        modul = "Angles";
       
        posModul = buscarModul(modul);
        posNia = Alumnes.buscarNia(nia);
        if (posModul != -1 && posNia != -1) {
            Modul m = new Modul(modul);
            Alumne matriculat = Alumnes.list.get(posNia);
            matriculat.modulsList.add(m); 
        } else {
            System.out.println("Error: El modul o el alumne no s'han trobat");
        }
        Alumnes.list.get(0).mostrar();
        
    }
}
