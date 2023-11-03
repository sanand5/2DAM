/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Practica_01;

/**
 *
 * @author andre
 */
public class Parking {
    private int[] plazas;
    private int numeroPlazas;

    public Parking(int numeroPlazas) {
        this.numeroPlazas = numeroPlazas;
        plazas = new int[numeroPlazas];
        for (int i = 0; i < numeroPlazas; i++) { //Cada plaza = 0
            plazas[i] = 0;
        }
    }

    public synchronized int aparcar(int cocheID) {
        while (numeroPlazas == 0) {
            try {
                System.out.println("Coche " + cocheID + " esperando para entrar al parking.");
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        int plaza = buscarPlazaLibre();
        plazas[plaza] = cocheID;
        numeroPlazas--;

        System.out.println("ENTRADA: Coche " + cocheID + " aparca en plaza " + plaza);
        System.out.println("Plazas libres: " + numeroPlazas);
        System.out.println("Parking: " + toString());
        System.out.println();
        return plaza;
    }

    public synchronized void desaparcar(int plaza, int cocheID) {
        plazas[plaza] = 0;
        numeroPlazas++;

        System.out.println("SALIDA: Coche " + cocheID + " sale de plaza " + plaza);
        System.out.println("Plazas libres: " + numeroPlazas);
        System.out.println("Parking: " + toString());
        System.out.println();

        notify();
    }

    private int buscarPlazaLibre() {
        for (int i = 0; i < plazas.length; i++) {
            if (plazas[i] == 0) {
                return i;
            }
        }
        return -1; // Plazas no disponibles
    }

    public String toString() {
        String result = "";
        for (int i = 0; i < plazas.length; i++) {
            result += "[" + (plazas[i] > 0 ? plazas[i] : "0") + "] ";
        }
        return result;
    }
}
