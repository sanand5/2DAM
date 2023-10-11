/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package practica_03;

import iohelpers.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author 2DAM_Sanz_Andreu
 */
public class practica_03 {

    /**
     * @param args the command line arguments
     */
    static Alumnes alumnesList = new Alumnes();
    static Moduls modulsList = new Moduls();
    static Matriculas matriculas = new Matriculas();

    public static void main(String[] args) {
//      carregarModuls();
//      carregarAlumnes();
        normal();
        escribir(alumnesList.list.get(1),true);
        
    }

    /**
     * Esta funcio simplement serveix per a que en el main puga fer proves
     */
    public static void normal() {
        ReadClient rc = new ReadClient();
        boolean repit = true;
        carregarModuls();
        carregarAlumnes();
        while (repit) {
            System.out.println("""
                           (0) Eixir
                           (1) Menu Alumnes
                           (2) Menu Mòdul
                           (3) Avaluar""");
            int menu = rc.pedirInteger("?");
            switch (menu) {

                case 0 ->
                    repit = false;
                case 1 ->
                    alumnesList.menu();
                case 2 ->
                    modulsList.menu();
                case 3 ->
                    matriculas.menu();
                default -> {
                    Colors.warMsg("Deus de introduir un valor valid");
                }
            }
        }
    }

    public static void carregarModuls() {
        try {
            File AlumnosList = new File("./Files/ModulsList.txt");
            Scanner list = new Scanner(new FileReader(AlumnosList));
            ArrayList<String> moduls = new ArrayList<>();
            while (list.hasNextLine()) {
                int i = 0;
                String ln = list.nextLine();
                moduls.add(ln);
            }
            modulsList.alta(moduls.toArray(String[]::new));
        } catch (FileNotFoundException e) {
            Colors.errMsg("Fallo en el ficher");
        } catch (Exception e) {
            Colors.errMsg("Alguns alumnes no s'han pogut carregar");
        }
    }

    public static void carregarAlumnes() {//TODO mirar com reduir esta funció
        try {
            File AlumnosList = new File("Files/AlumnosList.txt");
            Scanner list = new Scanner(new FileReader(AlumnosList));

            while (list.hasNextLine()) {
                String ln = list.nextLine();
                if (ln.contains("{")) {
                    String[] dades = ln.split(";");
                    String nom = dades[0];
                    String nia = dades[1].substring(0, dades[1].length() - 1);
                    boolean nomOk = Alumne.comprabarDatos(nom, true, "El format de nom per a " + nom + " es incorrecte.");
                    boolean niaOk = Alumne.comprabarDatos(nia, false, "El format de NIA per al NIA: " + nia + " es incorrecte.");
                    if (nomOk && niaOk) {
                        alumnesList.alta(nom, nia); //Afegit alumne a la llista
                        boolean repit = true;
                        while (repit && list.hasNextLine()) {
                            ln = list.nextLine();
                            if (!("".equals(ln)) && !ln.contains("}")) {
                                try {
                                    String[] ModNot = ln.split(";");
                                    String modul = ModNot[0];
                                    modulsList.matricularAlumne(nia, modul); //Matricula del Alumne creada
                                    Matricula matr = matriculas.enlazarMatricula(nia, modul); //Matricula creada
                                    String[] notesString = ModNot[1].split(" ");
                                    for (String notes : notesString) {//Notes del modul del alumne afegides
                                        try {
                                            matr.addNota(Double.valueOf(notes));
                                        } catch (NumberFormatException e) {
                                            Colors.errMsg("El format de la nota registrada " + notes + " es incorrecte");
                                        }
                                    }
                                } catch (NullPointerException e) {
                                    Colors.errMsg("Fallo en el mòdul");
                                }
                            } else {
                                repit = false;
                            }
                        }
                    } else {//TODO comprovar que aso funciona
                        while (list.nextLine().contains("}")) {
                        }
                        Colors.errMsg("El alumne amb \"NIA\": " + nia + " no s'ha pogut registarar.");
                    }

                }

            }
            list.close();
        } catch (FileNotFoundException e) {
            Colors.errMsg("Fallo en el ficher");
        } catch (Exception e) {
            Colors.errMsg("Alguns alumnes no s'han pogut carregar");
        }
    }

    /**
     * La idea de esta funcio es que borre el objecte del ficher i fique el
     * objecte actualitzat
     *
     * @param alu el objecte a modificar
     * @param option si el objecte es un modul o un alumne
     */
    public static void escribir(Alumne alu, boolean option) {

        String path;
        if (option) {
            path = "./Files/AlumnosListTest.txt";
        } else {
            path = "./Files/ModulsListTest.txt";
        }
        try {

            File AlumnosList = new File(path);
            Scanner sclist = new Scanner(new FileReader(AlumnosList));
            boolean stop = false;
            String strAlumnosList = "";
            String rm;
            while (!stop) {
                if (sclist.hasNextLine()) {
                    String alumno = sclist.nextLine();
                    if (alumno.contains(alu.nia)) {
                        strAlumnosList += alu.fromString();
                        do {
                            rm = sclist.nextLine();
                            Colors.warMsg(rm);
                        } while (!rm.contains("}"));
                    } else {
                        strAlumnosList += alumno +"\n";
                    }
                }else {
                    stop = true;
                }
            }
            
            System.out.println(strAlumnosList);
            FileWriter fr = new FileWriter(AlumnosList, false);
            fr.write(strAlumnosList);
            fr.close();

        } catch (Exception e) {
        }

    }

}
/**
 * TODO
 * Fer lo de una clase pare per a Alumnes, Moduls i matricules
 * No antenc perque el buffer del escanner mel guarda o algo aixina
 * 
 */
