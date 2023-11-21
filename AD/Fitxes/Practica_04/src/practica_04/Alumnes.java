/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package practica_04;

import iohelpers.*;
import java.util.ArrayList;
import static practica_04.practica_04.ALUMNES_PATH;

/**
 *
 * @author andre
 */
public class Alumnes extends Mantenibles{
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
                    String nom = Mantenible.pedirNom();
                    String nia = Mantenible.pedirId();
                    alta(nom, nia, leerFicher(ALUMNES_PATH), ALUMNES_PATH);
                }
                case 2 ->
                    baixa(ALUMNES_PATH);
                case 3 ->
                    super.mostrarLista(ALUMNES_PATH);
                default -> {
                    Colors.warMsg("Deus introduir un valor valid");
                }

            }
            
        }
        
    }
    public int alta(String nom, String id, ArrayList<Mantenible> list, String path) {
        if (buscarList(id, path, true) == -1) {
            list.add(new Alumne(nom, id));
            Colors.okMsg(String.format("S'ha donat de alta a %s amb ID: %s", nom, id));
        } else {
            Colors.warMsg("Ja existeix");
        }
        escribir(super.fromString(list), path);
        return list.size() - 1;
    }
    public void baixa(String path) {
        ArrayList<Mantenible> list = leerFicher(path);
        int pos = buscarList(Mantenible.pedirId(), path, true);
        if (pos == -1) {
            Colors.errMsg("El alumne no existeix");
        } else {
            list.remove(pos);
            Colors.okMsg("El alumne s'ha donat de baixa correctament");
        }
        escribir(fromString(list), path);
    }
    
}
