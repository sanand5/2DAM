/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package practica_02;

import java.util.InputMismatchException;

/**
 *
 * @author andre
 */
public class Matriculas {

    ReadClient rc = new ReadClient();

    public void menu() {
        int menu = 0;
        try {
            // TODO Pedir datos alumno
            String nia = rc.pedirString("Nia del alumne: ");
            String modul = rc.pedirString("Modul a qualificar: ");
            Alumne a = Alumnes.list.get(Alumnes.buscarNia(nia));
            int posModul = a.buscarModul(modul);
            Matricula matr = a.modulsList.get(posModul).m;

            boolean repetir = true;
            while (repetir) {

                System.out.print("""
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
                        qualificar(matr);
                        break;
                    case 2:
                        //TODO Modificar
                        break;
                    case 3:
                        mostrar();
                        break;
                    default:
                        System.out.println(Colors.ANSI_YELLOW + "Deus introduir un valor valid");
                }
            }
        } catch (InputMismatchException e) {
            System.out.println(Colors.ANSI_YELLOW + "Deus introduir un numero");
            menu();
        }
    }

    public void qualificar(Matricula matr) {//TODO buffer
        int cant = rc.pedirInteger("Quantes notes vols afegir: ");
        for (int i = 0; i < cant; i++) {
            double nota = rc.pedirDoubleEntre(10, 0, "Nota a afegir: ");
            matr.addNota(nota);
        }
    }

    public void modificar(Matricula matr) { //TODO era static

        matr.mostrarNotes();
        int pos = rc.pedirInteger("Quina nota vols afegir: ")-1;
        double nota = rc.pedirDoubleEntre(10, 0, "Disme la nota que vols ficar: ");
        matr.setNota(nota, pos);

    }

    public void mostrar() {
        for (int i = 0; i < Alumnes.list.size(); i++) {
            Alumnes.list.get(i).mostrar();
        }
    }
}
