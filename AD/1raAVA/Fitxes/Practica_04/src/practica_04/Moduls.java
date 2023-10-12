/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package practica_04;

import java.util.ArrayList;
import iohelpers.*;

/**
 *
 * @author andre OK
 */
public class Moduls {

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
                    alta(nom);
                    break;
                case 2:
                    String id = rc.pedirString("Id del mòdul: ");
                    baixa(id);
                    break;
                case 3:
                    mostrarLista();
                    break;
                case 4:
                    String nia = Alumne.pedirNia();
                    String idModul = Modul.pedirId();
                    matricularAlumne(nia, idModul, Matriculas.leerFicher());
                    break;
                default:
                    Colors.warMsg("Deus introduir un valor valid");
                    break;
            }
        }

    }

    public void mostrarLista() {
        ArrayList<Modul> list = Alumnes.leerFicher(practica_04.MODULS_PATH);
        if (list.isEmpty()) {
            Colors.warMsg("No hi han mòduls");
        } else {
            for (int i = 0; i < list.size(); i++) {
                System.out.println(i + 1 + ". " + list.get(i).nom + " : " + list.get(i).id);
            }
        }
    }

    public void alta(String... modul) {
        ArrayList<Modul> list = Alumnes.leerFicher(practica_04.MODULS_PATH);
        for (String name : modul) {
            if (buscarModul(name) == -1) {
                list.add(new Modul(name));
                Colors.okMsg(String.format("%s s'ha donat de alta", name));
            } else {
                Colors.errMsg("El mòdul ja existeix");
            }
        }
        Alumnes.escribir(fromString(list), practica_04.MODULS_PATH);
    }

    public void alta(String modul, String id) {
        ArrayList<Modul> list = Alumnes.leerFicher(practica_04.MODULS_PATH);
        if (buscarModul(modul) == -1) {
            list.add(new Modul(modul, id));
            Colors.okMsg(String.format("%s s'ha donat de alta", modul));
        } else {
            Colors.errMsg("El mòdul ja existeix");
        }
        Alumnes.escribir(fromString(list), practica_04.MODULS_PATH);
    }

    private void baixa(String idModul) {
        ArrayList<Modul> list = Alumnes.leerFicher(practica_04.MODULS_PATH);
        int pos = buscarModul(idModul);
        if (pos != -1) {
            list.remove(pos);
            Colors.okMsg("El mòdul s'ha eliminat");
            desmatricularAlumnes(idModul, Matriculas.leerFicher());
            Colors.okMsg("Els alumnes s'han actualitzat");
        } else {
            Colors.errMsg("El mòdul no s'ha trobat");
        }
        Alumnes.escribir(fromString(list), practica_04.MODULS_PATH);
    }

    public Matricula matricularAlumne(String nia, String idModul, ArrayList<Matricula> list) {
        ArrayList<Matricula> matrs = list;
        matrs.add(new Matricula(nia, idModul));
        Alumnes.escribir(Matriculas.fromString(list), practica_04.MATRICULES_PATH);
        return matrs.get(matrs.size() - 1);
    }

    private void desmatricularAlumnes(String idModul, ArrayList<Matricula> list) {//TODO
        ArrayList<Matricula> matrList = list;
        int matrSize = matrList.size();
        for (int i = 0; i < matrSize; i++) {
            Matricula matr = matrList.get(i);
            if (matr.idMdoul.equals(idModul)) {
                matrList.remove(matr);
            }
        }

    }

    public int buscarModul(String idModul) {
        ArrayList<Modul> list = Alumnes.leerFicher(practica_04.MODULS_PATH);
        int retorno = -1;
        for (int i = 0; i < list.size();
                i++) {
            if (list.get(i).id.equals(idModul)) {
                retorno = i;
            }
        }
        return retorno;
    }

    public String fromString(ArrayList<Modul> list) {
        String objs = "";
        for (int i = 0; i < list.size(); i++) {
            objs += list.get(i).fromString() + ";";
        }
        return objs;
    }
}
