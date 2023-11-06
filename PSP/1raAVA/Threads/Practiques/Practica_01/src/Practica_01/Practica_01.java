/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Practica_01;

/**
 *
 * @author andre
 */
import iohelpers.ReadClient;
public class Practica_01 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ReadClient rc = new ReadClient();
        int nCoches = rc.pedirInteger("Numero de coches: ");
        int nPlazas = rc.pedirInteger("Numero de plazas: ");
        Parking parking = new Parking(nPlazas);
        for (int i = 1; i <= nCoches; i++) {
            Coche coche = new Coche(i, parking);
            coche.start();
        }
    }
    
}