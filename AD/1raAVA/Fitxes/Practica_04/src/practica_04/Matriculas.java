/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package practica_04;

import iohelpers.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;
import static practica_04.practica_04.MATRICULES_PATH;
import static practica_04.practica_04.alumnesList;
import static practica_04.practica_04.modulsList;

/**
 *
 * @author andre
 */
public class Matriculas {

    ReadClient rc = new ReadClient();


    public void menu() {
        int menu;
        boolean repetir = true;
        while (repetir) {
            Matricula matr;
            ArrayList<Matricula> list;
            System.out.println("""
                                   Menu Avaluar
                                   (0) Salir
                                   (1) Qualificar
                                   (2) Modificar
                                   (3) Traure bolletí de notes""");
            menu = rc.pedirInteger("?");
            switch (menu) {
                case 0:
                    repetir = false;
                    System.out.println("Has ixit del menu Avaluar");
                    break;
                case 1:
                    list = leerFicher();
                    matr = dades(list);
                    if (matr != null) {
                        qualificar(matr);
                    }
                    Alumnes.escribir(fromString(list), MATRICULES_PATH);
                    break;
                case 2:
                    list = leerFicher();
                    matr = dades(list);
                    if (matr != null) {
                        modificar(matr);
                    }
                    Alumnes.escribir(fromString(list), MATRICULES_PATH);
                    break;
                case 3:
                    mostrar();
                    break;
                default:
                    Colors.warMsg("Deus introduir un valor valid");
            }

        }

    }

    public Matricula dades(ArrayList<Matricula> list) {//Compreobar escribir
        
        Matricula matr = null;
        if (!list.isEmpty()) {
            boolean ok = false;
            do {
                String nia = Alumne.pedirNia();
                String idModul = Modul.pedirId();
                matr = enlazarMatricula(nia, idModul, list);
                if (matr != null) {
                    ok = true;
                    Colors.okMsg("Dades correctes");
                } else {
                    Colors.errMsg("Dades incorrectes");
                }
            } while (!ok);
        } else {
            Colors.warMsg("No hi han matricules registrades");
        }
        return matr;
    }


    public Matricula enlazarMatricula(String nia, String idModul, ArrayList<Matricula> list) {//Compreobar escribir
        ArrayList<Matricula> matrs = list;
        Matricula matr = null;
        for (int i = 0; i < matrs.size(); i++) {
            String idList = matrs.get(i).idMdoul;
            String niaList = matrs.get(i).nia;
            boolean ok = (idList.equals(idModul)) && (niaList.equals(nia));
            if (ok) {
                matr = matrs.get(i);
            }

        }
        return matr;
    }


    public void qualificar(Matricula matr) {
        if (matr != null) {
            int cant = rc.pedirInteger("Quantes notes vols afegir: ");
            for (int i = 0; i < cant; i++) {
                double nota = rc.pedirDouble("Nota a afegir: ", 0.0, 10.0);
                matr.addNota(nota);
                Colors.okMsg("La nota s'ha afegit");
            }
        }
        
    }

    public void qualificar(Matricula matr, double[] notes) {
        matr.addNota(notes);
    }


    private void modificar(Matricula matr) {
        if (matr.mostrarNotes(true) != -1) {
            int pos = rc.pedirInteger("Quina nota vols afegir: ") - 1;
            double nota = rc.pedirDouble("Disme la nota que vols ficar: ", 0.0, 10.0);
            matr.setNota(nota, pos);
        }
    }

    public void mostrar() {
        ArrayList<Matricula> list = leerFicher();
        String mostrar = "\n";
        ArrayList<Alumne> alumnesList = Alumnes.leerFicher(practica_04.ALUMNES_PATH);
        for (int i = 0; i < alumnesList.size(); i++) {
            Alumne alm = alumnesList.get(i);
            mostrar += alm.toString() + ": \n"; //nom - nia
            String nia = alm.nia;
            for (int j = 0; j < list.size(); j++) { //moduls + notes
                Matricula matr = list.get(j);
                if (matr.nia.equals(nia)) {
                    mostrar += "\t" + matr.toString() + "\n";
                }
            }
        }
        System.out.println(mostrar);
    }
    
    public static ArrayList<Matricula> leerFicher() {
        ArrayList<Matricula> matrs = new ArrayList<>();
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
                        Matricula matr = modulsList.matricularAlumne(entidad[0], entidad[1], matrs);
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
        return matrs;
    }

    public static String fromString(ArrayList<Matricula> list) {
        String objs = "";
        for (int i = 0; i < list.size(); i++) {
            objs += list.get(i).fromString() + ";";
        }
        return objs;

    }
}
