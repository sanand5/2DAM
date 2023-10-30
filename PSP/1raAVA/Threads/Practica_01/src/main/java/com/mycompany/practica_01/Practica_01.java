/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.practica_01;

import iohelpers.ReadClient;

/**
 *
 * @author andre
 */
public class Practica_01 {

    public static void main(String[] args) {
        ReadClient rc = new ReadClient();
//        String placas = rc.pedirString("Dime el numero de placas: ");
//        int coches = rc.pedirInteger("Dime el numero de coches: ");
        int placas = rc.pedirInteger("Dime el numero de placas: ");
        int coches = rc.pedirInteger("Dime el numero de coches: ");
        
        
        
    }
}
class DispositivoControl {
    int numPlazas;
    int plazas[];
    public DispositivoControl(int numPlazas) {
        this.numPlazas = numPlazas;
        plazas = new int[numPlazas];
    }
    
    public void plazaLibre(int plaza) {
        plazas[plaza]=0;
    }
    public void plazaOcupada(int coche, int plaza) {
        plazas[plaza]=coche;
    }    
}

class Coche {
    int id;
    int plaza;

    public Coche(int id) {//el id puede que se asige automaticamente luego en el contructor
        this.id = id;
    }

    public void setPlaza(int plaza) {
        this.plaza = plaza;
    }    
}