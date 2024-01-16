/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package practica_06;

import practica_06.gestor.gestor;

/**
 *
 * @author andre
 */
public class Practica_06 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        gestor gs = new gestor();
        if (gs.testConexion()) {
            menus menu = new menus();
            menu.mainMenu();
        }
    }
}
