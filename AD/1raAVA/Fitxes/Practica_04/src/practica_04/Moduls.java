/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package practica_04;

import java.util.ArrayList;
import iohelpers.*;
import static practica_04.practica_04.MODULS_PATH;
import static practica_04.practica_04.MATRICULES_PATH;
/**
 *
 * @author andre OK
 */
public class Moduls extends Mantenibles {

    ReadClient rc = new ReadClient();

    public void menu() {
        int menu = 0;
        boolean repetir = true;
        while (repetir) {
            System.out.println("""
                                   Menu Mòdul
                                   (0) Salir
                                   (1) Alta
                                   (2) Baixa
                                   (3) Llista
                                   (4) Matricular Alumne""");
            menu = rc.pedirInteger("?");
            switch (menu) {
                case 0:
                    repetir = false;
                    System.out.println("Has ixit del menu Mòdul");
                    break;
                case 1:
                    String nom = rc.pedirString("Nom del mòdul: ");
                    alta(nom);//TODO
                    
                    break;
                case 2:
                    baixa(MODULS_PATH);
                    break;
                case 3:
                    super.mostrarLista(MODULS_PATH);
                    break;
                case 4:
                    String nia = Mantenible.pedirId();
                    String idModul = Mantenible.pedirId();
                    matricularAlumne(nia, idModul, Matriculas.leerFicher()); //TODO
                    break;
                default:
                    Colors.warMsg("Deus introduir un valor valid");
                    break;
            }
        }

    }


    public void alta(String... modul) {
        ArrayList<Mantenible> list = super.leerFicher(MODULS_PATH);
        for (String name : modul) {
            if (super.buscarList(name, MODULS_PATH, false) == -1) {
                
                list.add(new Modul(name, generarId()));
                Colors.okMsg(String.format("%s s'ha donat de alta", name));
            } else {
                Colors.errMsg("El mòdul ja existeix");
            }
        }
        super.escribir(super.fromString(list), MODULS_PATH);
    }
    public static String generarId() {
        int min = 10000000, max = 99999999;
        int random = (int) (Math.random() * (max - min + 1) + min);
        return random+"";
    }
    public void baixa(String path) {
        ArrayList<Mantenible> list = leerFicher(path);
        int pos = buscarList(Mantenible.pedirId(), path, true);
        if (pos == -1) {
            Colors.errMsg("El mòdul no existeix");
        } else {
            desmatricularAlumnes(list.get(pos).id, Matriculas.leerFicher());
            list.remove(pos);
            Colors.okMsg("El mòdul s'ha donat de baixa correctament");
        }
        escribir(fromString(list), path);
    }
    
    /*
    El problema esta en est alinea pero he de descubrir perque cride a matricular alumne i si la gaste en una altrar funcio
    
    */
    public Matricula matricularAlumne(String nia, String idModul, ArrayList<Matricula> list) {
        ArrayList<Matricula> matrs = list;
        matrs.add(new Matricula(nia, idModul));
        super.escribir(Matriculas.paraString(list), MATRICULES_PATH);
        return matrs.get(matrs.size() - 1);
    }
    public void desmatricularAlumnes(String idModul, ArrayList<Matricula> list) {//TODO
        ArrayList<Matricula> matrList = list;
        int matrSize = matrList.size();
        for (int i = 0; i < matrSize; i++) {
            Matricula matr = matrList.get(i);
            if (matr.idMdoul.equals(idModul)) {
                matrList.remove(matr);
            }
        }

    }
        

}
