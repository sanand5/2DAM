/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package practica_01v3;

/**
 *
 * @author andre
 */
public class Parking {
    private int totalPlazas;
    private int plazasLibres;
    private boolean[] plazasOcupadas;

    public Parking(int numPlazas) {
        this.totalPlazas = totalPlazas;
        this.plazasLibres = totalPlazas;
        this.plazasOcupadas = new boolean[totalPlazas];
    }
    
    public synchronized void entrar(int idCoche) {
        
    }
    
    public synchronized void salir(int idCoche) {
        
    }
    
    public void mostrar() {
    }
}
