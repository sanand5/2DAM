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
            File AlumnosList = new File("Files/ModulsList.txt");
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

    public static void carregarAlumnes() {
        try {
            File AlumnosList = new File("Files/AlumnosList.txt");
            Scanner list = new Scanner(new FileReader(AlumnosList));

            while (list.hasNextLine()) {
                String ln = list.nextLine();
                if (ln.contains("{")) {
                    String[] dades = ln.split(";");
                    String nom = dades[0];
                    String nia = dades[1].substring(0, dades[1].length() - 2);
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
    
    public static void escribir(String msg, boolean option) {
        String path;
        if (option) {
            path = "Files/AlumnosList.txt";
        }else {
            path = "Files/ModulsList.txt";
        }
        try {
            File fl = new File(path);
            FileWriter fr = new FileWriter(fl, true); // TODO comprovar que era lo de true false asi
            fr.write(msg + "\n");
            System.out.println(msg);
            fr.close();
            
        } catch (Exception e) {
        }
        
    }

}
