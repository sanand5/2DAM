/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package practica_04;

import iohelpers.Colors;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author andre
 */
public abstract class Mantenibles {
    public static ArrayList<Mantenible> leerFicher(String path) {
        ArrayList<Mantenible> list = new ArrayList<>();
        File fl;
        Scanner sc;
        try {
            fl = new File(path);
            sc = new Scanner(new FileReader(fl));
            while (sc.hasNextLine()) {
                String ln = sc.nextLine();
                String entidades[] = ln.split(";");
                for (String entidadString : entidades) {
                    String entidad[] = entidadString.split(",");
                    list.add(new Mantenible(entidad[0], entidad[1]));

                }
            }

        sc.close();
        } catch (FileNotFoundException e) {
            Colors.errMsg("Fallo en el ficher");
        } catch (Exception e) {
            Colors.errMsg("Alguna cosa a ixit malament");
        } finally {
            fl = null;
            sc = null;
        }
        return list;
    }
    public void escribir(String str, String path) {
        File fl;
        FileWriter fw;
        try {
            fl = new File(path);
            fw = new FileWriter(fl, false);
            fw.write(str);
        fw.close();
        } catch (IOException e) {
            Colors.errMsg("Alguna cosa a ixit malament en el fitcher");
        } catch (Exception e) {
            Colors.errMsg("Alguna cosa a ixit malament");
        }finally{
            fl = null;
            fw = null;
        }
    }
    public static int buscarList(String str, String path, boolean modo) {
        ArrayList<Mantenible> list = leerFicher(path);
        int retorno = -1;
        for (int i = 0; i < list.size(); i++) {
            Mantenible m = list.get(i);
            String buscar;
            if (modo) {
                buscar = m.id;
            } else {
                buscar = m.nom;
            }
            if (buscar.equals(str)) {
                retorno = i;
            }
        }
        return retorno;
    }
    public void mostrarLista(String path) {
        ArrayList<Mantenible> list = leerFicher(path);
        for (int i = 0; i < list.size(); i++) {
            System.out.println(i + 1 + ". " + list.get(i).toString());
        }

    }  
    public String fromString(ArrayList<Mantenible> list) {
        String objs = "";
        for (int i = 0; i < list.size(); i++) {
            objs += list.get(i).fromString() + ";";
        }
        return objs;
    }

}
