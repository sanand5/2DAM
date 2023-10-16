/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package practica_04;

import java.util.ArrayList;
import iohelpers.*;
import static practica_04.practica_04.MODULS_PATH;

/**
 *
 * @author andre
 */
public class Matricula {

    private ArrayList<Double> notes;
    double mitjana;
    String idMdoul;
    String nia;

    public Matricula(String nia, String idMdoul) {
        this.idMdoul = idMdoul;
        this.nia = nia;
        this.notes = new ArrayList<>();
    }

    public void addNota(double ...nota) {
        for (double n : nota) {
            notes.add(n);
        }
        updateMitjana();
    }

    public void addNota(double nota) {
        notes.add(nota);
        updateMitjana();
    }

    public void setNota(double nota, int pos) {
        notes.set(pos, nota);
        Colors.okMsg("La nota s'ha modificat");
        updateMitjana();
    }

    public void delNota(int pos) {
        notes.remove(pos);
        updateMitjana();
    }

    @Override
    public String toString() {
        int posModul = Mantenibles.buscarList(idMdoul, MODULS_PATH, true);
        Mantenible modul = Mantenibles.leerFicher(MODULS_PATH).get(posModul);
        String matricula = modul.nom + ": ";
        for (int i = 0; i < notes.size(); i++) {
            matricula += notes.get(i) + " ";
        }
        matricula += Colors.PURPLE_ANSI + mitjana + Colors.RESET_ANSI;
        return matricula;
    }

    public int mostrarNotes(boolean lista) {
        int retorno;
        if (!notes.isEmpty()) {
            if (lista) {
                for (int i = 0; i < notes.size(); i++) {
                    System.out.printf("%02d.- %.2f%n", i + 1, notes.get(i));
                }
            } else {
                for (int i = 0; i < notes.size(); i++) {
                    System.out.printf("%.2f - ", notes.get(i));
                }
                System.out.printf(Colors.PURPLE_ANSI + "%.2f%n" + Colors.RESET_ANSI, mitjana);
            }
            retorno = 0;
        } else {
            retorno = -1;
            Colors.warMsg("Este alumne no te notes");
        }
        return retorno;

    }

    public void updateMitjana() {
        double sumatorio = 0;
        for (int i = 0; i < notes.size(); i++) {
            sumatorio += notes.get(i);
        }
        this.mitjana = sumatorio / notes.size();
        Colors.okMsg("La mitjana s'ha actualitzat");
    }

    public String fromString() {
        String obj = nia + "," + idMdoul + ",";
        for (int i = 0; i < notes.size(); i++) {
            obj += notes.get(i) + " ";
        }

        return obj;

    }
}
