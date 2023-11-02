/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package practica_01v3;

/**
 *
 * @author andre
 */
public class Coche extends Thread {
    int id;
    Parking Parking;

    public Coche(int id, Parking Parking) {
        this.id = id;
        this.Parking = Parking;
    }
    
    @Override
    public void run() {
        
    }
}
