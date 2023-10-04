/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package practica_02;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

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
            double n[] = ma.notes;
            System.out.printf("\t %s: %.2f - %.2f - %.2f - %.2f%n", m.nom, n[0], n[1], n[2], n[3]);
        }
    }
    
    public void eliminar() {
        try {
            this.finalize();
        } catch (Throwable ex) {//TODO mensaje
            Logger.getLogger(Alumne.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    
    
    
    
}
