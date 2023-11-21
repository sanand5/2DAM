/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package practica_02;

import java.util.ArrayList;

/**
 *
 * @author andre
 */
public class Matricula {
    private ArrayList<Double> notes;
    double mitjana;

    public Matricula() {
        notes = new ArrayList<>();
    }

    public void addNota(double n) { //TODO que pugues afegir mes de una amb ...
        notes.add(n);
        updateMitjana();
    }

    public void setNota(double nota, int pos) {
        notes.set(pos, nota);
        Colors.okMsg("La nota s'ha modificat");
        updateMitjana();
    }

    public void delNota(int pos) {//TODO que pugues eliminar mes de una amb ...
        notes.remove(pos);
        updateMitjana();
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
                System.out.printf(Colors.PURPLE_ANSI + "%.2f%n" + Colors.WHITE_ANSI, mitjana);
            }
            retorno = 0;
        } else {
            retorno = -1;
            Colors.warMsg("Este alumne no te notes");
        }
        return retorno;

    }

    private void updateMitjana() {
        double sumatorio = 0;
        for (int i = 0; i < notes.size(); i++) {
            sumatorio += notes.get(i);
        }
        this.mitjana = sumatorio / notes.size();
        Colors.okMsg("La mitjana s'ha actualitzat");
    }
}
