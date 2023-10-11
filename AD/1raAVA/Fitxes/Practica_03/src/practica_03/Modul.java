/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package practica_03;

/**
 *
 * @author andre
 */
public class Modul {

    String nom;
    Matricula matr;

    public Modul(String nom) {
        this.nom = nom;
        this.matr = new Matricula();
    }

    public String fromSring() {
        String obj = "";
        obj = nom + "," + matr.fromString();
        return obj;
    }

}
