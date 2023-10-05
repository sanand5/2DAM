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

    
    
    //double notes[] = new double[4];
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
        updateMitjana();
        System.out.println(Colors.ANSI_GREEN+"La nota s'ha modificat");
    }
    public void delNota(int pos) {//TODO que pugues eliminar mes de una amb ...
        notes.remove(pos);
        updateMitjana();
    }
    public void mostrarNotes() {
        for (int i = 0; i < notes.size(); i++) {
            System.out.printf("%02d.- %d", i+1, notes.get(i));
        }
    }
    
    
    private void updateMitjana() {
        double sumatorio=0;
        for (int i = 0; i < notes.size(); i++) {
            sumatorio += notes.get(i);
        }
        this.mitjana = sumatorio/notes.size();
    }
    
    

}
