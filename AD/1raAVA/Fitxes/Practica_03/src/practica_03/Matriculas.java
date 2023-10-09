/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package practica_03;

import iohelpers.*;

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
                    qualificar(dades());
                    break;
                case 2:
                    modificar(dades());
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
        String nia = Alumne.pedirNia();
        String modul = rc.pedirString("Mòdul a qualificar: ");
        Matricula matr = enlazarMatricula(nia, modul);
        return matr;
    }

    public Matricula enlazarMatricula(String nia, String modul) {
        Matricula matr = null;
        int posNia = practica_03.alumnesList.buscarNia(nia);
        if (posNia != -1) {
            Alumne a = practica_03.alumnesList.list.get(posNia);
            int posModul = practica_03.modulsList.buscarModul(modul, a.modulsList);
            if (posModul != -1) {
                matr = a.modulsList.get(posModul).matr;
            } else {
                Colors.errMsg("No es posible avaluar al alumne perque no esta donat de alta en eixe Mòdul");
            }
        } else {
            Colors.errMsg("No es posible avaluar al alumne perque no esta donat de alta");
        }
        return matr;
    }

    private void qualificar(Matricula matr) {
        int cant = rc.pedirInteger("Quantes notes vols afegir: ");
        for (int i = 0; i < cant; i++) {
            double nota = rc.pedirDouble("Nota a afegir: ", 0.0, 10.0);
            matr.addNota(nota);
            Colors.okMsg("La nota s'ha afegit");
        }
    }

    private void modificar(Matricula matr) {
        if (matr.mostrarNotes(true) != -1) {
            int pos = rc.pedirInteger("Disme la posició de la nota que vols modificar: ") - 1;
            double nota = rc.pedirDouble("Disme la nota que vols ficar: ", 0.0, 10.0);
            matr.setNota(nota, pos);
        }
    }

    public void mostrar() {
        System.out.println("### Bolletí de notes ###");
        for (int i = 0; i < practica_03.alumnesList.list.size(); i++) {
            practica_03.alumnesList.list.get(i).mostrar();
        }
    }
}
