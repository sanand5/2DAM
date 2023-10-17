/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package test;

import java.util.ArrayList;
import iohelpers.*;

/**
 *
 * @author andre
 */
public class Alumnes{

    ArrayList<Alumne> list = new ArrayList<>();
    ReadClient rc = new ReadClient();

    public void menu() {

        int menu = 0;
        boolean repetir = true;
        while (repetir) {
            System.out.println("""
                                   Menu Alumne
                                   (0) Salir
                                   (1) Alta
                                   (2) Baixa
                                   (3) Llista""");
            menu = rc.pedirInteger("?");
            switch (menu) {
                case 0 -> {
                    System.out.println("Has ixit del menu Alumnes");
                    repetir = false;
                }
                case 1 -> {
                    String nom = Alumne.pedirName();
                    String nia = Alumne.pedirNia();
                    alta(nom, nia); //descomentar
                }
                case 2 ->
                    baixa();
                case 3 ->
                    mostrarLista();
                default -> {
                    Colors.warMsg("Deus introduir un valor valid");
                }

            }
        }
    }

    public void mostrarLista() {
        for (int i = 0; i < list.size(); i++) {
            System.out.println(i + 1 + ". " + list.get(i).toString());
        }
        
    }
    
    public int alta(String nom, String nia) {
        if (buscarNia(nia) == -1) {
            list.add(new Alumne(nom, nia));
            Colors.okMsg(String.format("S'ha donat de alta ha %s amb NIA: %s", nom, nia));
        } else {
            Colors.warMsg("El alumne ja existeix");
        }
        
        return list.size()-1;
    }

    private void baixa() {
        int pos = buscarNia(Alumne.pedirNia());
        if (pos == -1) {
            Colors.errMsg("El alumne no existeix");
        } else {
            list.remove(pos);
            Colors.okMsg("El alumne s'ha donat de baixa correctament");
        }

    }

    public int buscarNia(String nia) {
        int retorno = -1;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).nia.equals(nia)) {
                retorno = i;
            }
        }
        return retorno;
    }
    
    public String fromString() {
        String objs = "";
        for (int i = 0; i < list.size(); i++) {
            objs += list.get(i).fromString()+";";
        }
        return objs;
    }
    

}
