/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package practica_02;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author andre
 */
public class Modul {

    String nom;
    Matricula m;

    public Modul(String nom) { //TODO es raro lo de la matricula
        this.nom = nom;
        m = new Matricula();
    }
    
    public void eliminar() {
        
        try {
            this.finalize();
        } catch (Throwable ex) {
            Logger.getLogger(Modul.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
