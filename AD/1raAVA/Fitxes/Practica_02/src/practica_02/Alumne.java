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
public class Alumne {
    String nom, nia;
    ArrayList<Modul> modulsList;
    
    public Alumne(String nom, String nia) {
        this.nom = nom;
        this.nia = nia;
        modulsList = new ArrayList<>();
    }
    public int buscarModul(String nom) {
        int retorno = -1;
        for (int i = 0; i < modulsList.size();
                i++) {
            if (modulsList.get(i).nom.equals(nom)) {
                retorno = i;
            }
        }
        return retorno;
    }
    public void mostrar() {
        System.out.printf("""
                           %s / %s
                           """, nom, nia);
        for (int i = 0; i < modulsList.size(); i++) {
            Modul m = modulsList.get(i);
            Matricula ma = m.m;
            ma.mostrarNotes(); //TODO mostrar en gions en una altra funció
        }
    }   
}