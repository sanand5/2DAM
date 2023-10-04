/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package practica_02;

/**
 *
 * @author andre
 */
public class Matricula {

    
    
    double notes[] = new double[4];
    public Matricula() {
    }
    
    public Matricula(double a, double b, double c) {
        notes[0] = a;
        notes[1] = b;
        notes[2] = c;
        notes[3] = (a + b + c) / (notes.length-1);
    }

    public void setNotes(double nota, int pos) {
        this.notes[pos] = nota;
        notes[3] = (notes[0] + notes[1] + notes[2]) / (notes.length-1);
    }
    

}
