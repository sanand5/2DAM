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
public class Moduls {
    
    ArrayList<Modul> list = new ArrayList<>();
    ReadClient rc = new ReadClient();
    
    public void menu() {
        int menu = 0;
        boolean repetir = true;
        while (repetir) {
            System.out.println("""
                                   Menu Modul
                                   (0) Salir
                                   (1) Alta
                                   (2) Baixa
                                   (3) Llista
                                   (4) Matricular Alumen""");
            menu = rc.pedirInteger("?");
            switch (menu) {
                case 0 -> {
                    repetir = false;
                    System.out.println("Has ixit del menu Moduls");
                }
                case 1 ->
                    alta();
                case 2 ->
                    baixa();
                case 3 ->
                    mostrarLista();
                case 4 ->
                    matricularAlumne();
                default -> {
                    Colors.warMsg("Deus introduir un valor valid");
                }
            }
        }
        
    }
    
    public void mostrarLista() {
        if (list.isEmpty()) {
            Colors.warMsg("No hi han moduls");
        } else {
            for (int i = 0; i < list.size(); i++) {
                System.out.println(i + 1 + ". " + list.get(i).nom);
            }
        }
    }
    
    public void alta() {
        rc.bufferClear();
        String nom = rc.pedirString("Nom del modul: ");
        if (buscarModul(nom) == -1) {
            list.add(new Modul(nom));
            Colors.okMsg(String.format("%s s'ha donat de alta", nom));
        } else {
            Colors.errMsg("El modul ja existeix");
        }
    }
    
    public void baixa() {
        rc.bufferClear();
        String modul = rc.pedirString("Nom del modul: ");
        int pos = buscarModul(modul);
        if (pos != -1) {
            list.remove(pos);
            Colors.okMsg("El modul s'ha eliminat");
            desmatricularAlumnes(modul);
            Colors.okMsg("Els alumnes s'han actualitzat");
        } else {
            Colors.errMsg("El modul no s'ha trobat");
        }
    }
    
    public void matricularAlumne() {//TODO no va
        rc.bufferClear();
        String nia = rc.pedirString("Nia del alumne: ");
        String modul = rc.pedirString("Nom del modul: ");
        
        int posModul = buscarModul(modul);
        int posNia = Practica_02.alumnesList.buscarNia(nia);
        if (posModul != -1 && posNia != -1) { //El modul o el alumne no existeixen
            Alumne matriculat = Practica_02.alumnesList.list.get(posNia);
            if (buscarModul(modul, matriculat.modulsList) == -1) { // El modul existeix en la llista del alumne
                Modul m = new Modul(modul);
                matriculat.modulsList.add(m);
                Colors.okMsg("El alumne s'ha matriculat en el modul " + m.nom);
            } else {
                Colors.errMsg("El alumne ja esta matricular a aquest modul");
            }
        } else {
            Colors.errMsg("El modul o el alumne no s'han trobat");
        }
        
    }
    
    public void desmatricularAlumnes(String modul) {
        ArrayList<Alumne> list = Practica_02.alumnesList.list;
        for (int i = 0; i < list.size(); i++) {
            Alumne matriculat = list.get(i);
            int posModul = buscarModul(modul, matriculat.modulsList);
            if (posModul != -1) {
                Modul m = matriculat.modulsList.get(i);
                matriculat.modulsList.remove(posModul);
            }
        }
        
    }
    
    public int buscarModul(String nom) {
        int retorno = -1;
        for (int i = 0; i < list.size();
                i++) {
            if (list.get(i).nom.equals(nom)) {
                retorno = i;
            }
        }
        return retorno;
    }
    
    public int buscarModul(String nom, ArrayList<Modul> list) {
        int retorno = -1;
        for (int i = 0; i < list.size();
                i++) {
            if (list.get(i).nom.equals(nom)) {
                retorno = i;
            }
        }
        return retorno;
    }
}
