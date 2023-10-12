/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package iohelpers;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 *
 * @author 2DAM_Sanz_Andreu
 */
public class ReadClient {

    //TECLADO
    public int pedirInteger(String msg) {
        Scanner sc = new Scanner(System.in);
        int num = 0;
        boolean repit;
        do {
            repit = false;
            try {
                System.out.print(msg);
                num = Integer.parseInt(sc.nextLine());

            } catch (InputMismatchException | NumberFormatException e) {
                Colors.warMsg("Deus introduir un número enter");
//                sc.next();
                repit = true;
            }
        } while (repit);
        sc = null;
        System.gc();
        return num;
    }

    public double pedirDouble(String msg, Double min, Double max) {
        Scanner sc = new Scanner(System.in);
        double num = 0;
        boolean repit;
        String rang = "";

        do {
            repit = false;
            try {
                System.out.print(msg);
                num = Double.parseDouble(sc.nextLine());
                if ((min != null && num < min) || (max != null && num > max)) {
                    rang = min + " - " + max;
                    Colors.warMsg("Deus introduir un número entre " + rang);
                    repit = true;
                }
            } catch (InputMismatchException | NumberFormatException | NullPointerException e) {
//                sc.next();
                Colors.warMsg("Deus introduir un número");
                repit = true;
            }
        } while (repit);
        sc = null;
        System.gc();
        return num;
    }

    public String pedirString(String msg) {
        Scanner sc = new Scanner(System.in);
        String str = null;
        boolean repit;
        do {
            System.out.print(msg);
            repit = false;
            try {
                str = sc.nextLine();
                if (str == null || str == "") {
                    repit = true;
                    Colors.warMsg("No pots deixar un camp buit");
                }

            } catch (NoSuchElementException e) {
                Colors.errMsg("Deus introduir un text");
                repit = true;
            }
        } while (repit);
        sc = null;
        System.gc();
        return str;
    }
    
    //FICHEROS: Segurament no me servixguen
    /*
    public File createFile(String path) {
        File f = null;
        try {
            f = new File(path);
        } catch (NullPointerException e) {
            Colors.errMsg("El ficher no s'ha trobat");//TODO msg no apropiat
        }
        return f;
        
    }
    
    public FileWriter writeFile(File fl) {
        FileWriter fr = null;
        try {
            fr = new FileWriter(fl, true);
        } catch (FileNotFoundException e) {
            Colors.errMsg("El ficher " + fl.getPath() + " no s'ha trobat");
        } catch (IOException e){
            Colors.errMsg("La escritura del ficher no ha sigut posible");
        }
        return fr;
    }

    public Scanner readFile(String path) {
        Scanner sc = null;
        try {
            File file = new File(path);
            sc = new Scanner(new FileReader(file));

        } catch (FileNotFoundException e) {
            Colors.errMsg("El ficher " + path + " no s'ha trobat");
        }
        return sc;
    }
*/
}
