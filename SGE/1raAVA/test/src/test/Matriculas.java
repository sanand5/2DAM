/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package test;

import iohelpers.*;
import java.util.ArrayList;

/**
 *
 * @author andre
 */
public class Matriculas {

    ReadClient rc = new ReadClient();
    ArrayList<Matricula> list = new ArrayList<>();


    public void menu() {
        int menu;
        boolean repetir = true;
        while (repetir) {
            Matricula matr;
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
                    matr = dades();
                    if (matr != null) {
                        qualificar(matr);
                    }
                    break;
                case 2:
                    matr = dades();
                    if (matr != null) {
                        modificar(matr);
                    }
                    break;
                case 3:
                    mostrar();
                    break;
                default:
                    Colors.warMsg("Deus introduir un valor valid");
            }

        }

    }

    public Matricula dades() {
        Matricula matr = null;
        if (!test.matriculasList.list.isEmpty()) {
            boolean ok = false;
            do {
                String nia = Alumne.pedirNia();
                String idModul = Modul.pedirId();
                matr = enlazarMatricula(nia, idModul);
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


    public Matricula enlazarMatricula(String nia, String idModul) {
        ArrayList<Matricula> matrs = test.matriculasList.list;
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
        String mostrar = "\n";
        ArrayList<Alumne> alumnesList = test.alumnesList.list;
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

    public String fromString() {
        String objs = "";
        for (int i = 0; i < list.size(); i++) {
            objs += list.get(i).fromString() + ";";
        }
        return objs;

    }
}
