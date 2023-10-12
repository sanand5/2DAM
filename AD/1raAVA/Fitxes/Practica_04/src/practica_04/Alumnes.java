/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package practica_04;

import java.util.ArrayList;
import iohelpers.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author andre
 */
public class Alumnes{

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
                    alta(nom, nia, leerFicher(practica_04.ALUMNES_PATH));
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
        ArrayList<Alumne> list = leerFicher(practica_04.ALUMNES_PATH);
        for (int i = 0; i < list.size(); i++) {
            System.out.println(i + 1 + ". " + list.get(i).toString());
        }
        
    }
    
    public int alta(String nom, String nia, ArrayList<Alumne> list) {
        
        if (buscarNia(nia) == -1) {
            list.add(new Alumne(nom, nia));
            Colors.okMsg(String.format("S'ha donat de alta ha %s amb NIA: %s", nom, nia));
        } else {
            Colors.warMsg("El alumne ja existeix");
        }
        escribir(fromString(list), practica_04.ALUMNES_PATH);
        return list.size()-1;
    }

    private void baixa() {
        ArrayList<Alumne> list = leerFicher(practica_04.ALUMNES_PATH);
        int pos = buscarNia(Alumne.pedirNia());
        if (pos == -1) {
            Colors.errMsg("El alumne no existeix");
        } else {
            list.remove(pos);
            Colors.okMsg("El alumne s'ha donat de baixa correctament");
        }
        escribir(fromString(list), practica_04.ALUMNES_PATH);
    }

    public int buscarNia(String nia) {
        ArrayList<Alumne> list = leerFicher(practica_04.ALUMNES_PATH);
        int retorno = -1;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).nia.equals(nia)) {
                retorno = i;
            }
        }
        return retorno;
    }
    /*TODO
    - Deuria de modificar esta per a <Alumne> pero quan tinga lo de la calse genral
    */
    public static ArrayList leerFicher(String path) {
        ArrayList<Alumne> list = new ArrayList<>();
        try {
            File fl = new File(path);
            Scanner sc = new Scanner(new FileReader(fl));
            while (sc.hasNextLine()) {
                String ln = sc.nextLine();
                String entidades[] = ln.split(";");
                for (String entidadString : entidades) {
                    String entidad[] = entidadString.split(",");
                    list.add(new Alumne(entidad[0], entidad[1]));
                    
                }
            }
            fl = null;
            sc.close();
            sc = null;
        } catch (FileNotFoundException e) {
            Colors.errMsg("Fallo en el ficher");
        } catch (Exception e) {
            Colors.errMsg("Alguna cosa a ixit malament");
        }
        return list;
    }
    
    public static void escribir(String list, String path) {
        try {
            File fl = new File(path);
            FileWriter fw = new FileWriter(fl, false);
            fw.write(list);

            fl = null;
            fw.close();
            fw = null;

        } catch (IOException e) {
            Colors.errMsg("Alguna cosa a ixit malament en el fitcher");
        } catch (Exception e) {
            Colors.errMsg("Alguna cosa a ixit malament");
        }
    }
    
    public String fromString(ArrayList<Alumne> list) {
        String objs = "";
        for (int i = 0; i < list.size(); i++) {
            objs += list.get(i).fromString()+";";
        }
        return objs;
    }
    
    

}
