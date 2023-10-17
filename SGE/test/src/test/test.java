/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package test;

import iohelpers.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author 2DAM_Sanz_Andreu
 */
public class test {

    /**
     * @param args the command line arguments
     */
    static Alumnes alumnesList = new Alumnes();
    static final String ALUMNES_PATH = "./Files/AlumnesList.txt";
    static Moduls modulsList = new Moduls();
    static final String MODULS_PATH = "./Files/ModulsList.txt";
    static Matriculas matriculasList = new Matriculas();
    static final String MATRICULES_PATH = "./Files/MatriculesList.txt";

    public static void main(String[] args) {
        carregar(ALUMNES_PATH, true);
        carregar(MODULS_PATH, false);
        carregarMatricules();
        ReadClient rc = new ReadClient();
        boolean repit = true;
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
                    matriculasList.menu();
                default -> {
                    Colors.warMsg("Deus de introduir un valor valid");
                }
            }
        }
        escribir(alumnesList.fromString(), ALUMNES_PATH);
        escribir(modulsList.fromString(), MODULS_PATH);
        escribir(matriculasList.fromString(), MATRICULES_PATH);

    }

    public static void carregar(String path, boolean modo) {
        try {
            File fl = new File(path);
            Scanner list = new Scanner(new FileReader(fl));
            while (list.hasNextLine()) {
                String ln = list.nextLine();
                String entidades[] = ln.split(";");
                for (String entidadString : entidades) {
                    String entidad[] = entidadString.split(",");
                    if (modo) { //TODO cambiar de forma
                        alumnesList.alta(entidad[0], entidad[1]);
                    } else {
                        modulsList.alta(entidad[0], entidad[1]);
                    }
                }
            }
            fl = null;
            list.close();
            list = null;
        } catch (FileNotFoundException e) {
            Colors.errMsg("Fallo en el ficher");
        } catch (Exception e) {
            Colors.errMsg("Alguna cosa a ixit malament");
        }
    }

    public static void carregarMatricules() {
        try {
            File fl = new File(MATRICULES_PATH);
            Scanner list = new Scanner(new FileReader(fl));
            while (list.hasNextLine()) {
                String ln = list.nextLine();
                String entidades[] = ln.split(";");
                for (String entidadString : entidades) {
                    String entidad[] = entidadString.split(",");
                    String nia = entidad[0];
                    String idModul = entidad[1];
                    boolean niaExist = (alumnesList.buscarNia(nia) != -1);
                    boolean modulExist = (modulsList.buscarModul(idModul) != -1);
                    if (niaExist && modulExist) {
                        Matricula matr = modulsList.matricularAlumne(entidad[0], entidad[1]);
                        String[] notes = entidad[2].split(" ");
                        for (String note : notes) {
                            try {
                                double nota = Double.parseDouble(note);
                                matr.addNota(nota);
                            } catch (NumberFormatException e) {
                                Colors.errMsg("La nota no te el format correcte");
                            }
                        }
                    } else {
                        Colors.errMsg("El alumne amb nia " + nia + " o el modul amb id " + idModul + " no existeixen");
                    }
                }
            }
            fl = null;
            list.close();
            list = null;
        } catch (FileNotFoundException e) {
            Colors.errMsg("Fallo en el ficher");
        } catch (Exception e) {
            Colors.errMsg("Alguna cosa a ixit malament");
        }
    }

    public static void escribir(String list, String path) {
        try {
            File fl = new File(path);
            FileWriter fw = new FileWriter(fl, false);
            fw.write(list);

            fl = null;
            fw.close();
            fw = null;

        } catch (IOException e) {
            Colors.errMsg("Alguna cosa a ixit malament en el fitcher");
        } catch (Exception e) {
            Colors.errMsg("Alguna cosa a ixit malament");
        }

    }

    
}