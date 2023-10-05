/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package practica_02;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 *
 * @author 2DAM_Sanz_Andreu
 */
public class ReadClient {

    Scanner sc;

    public ReadClient() {
        sc = new Scanner(System.in);
    }

    public int pedirInteger(String msg) {
        int num = 0;
        boolean repit;
        do {
            repit = false;
            try {
                System.out.print(Colors.ANSI_BLACK + msg);
                num = sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println(Colors.ANSI_YELLOW + "Err: Deus introduir un numero enter");
                sc.next();
                repit = true;
            }
        } while (repit);
        return num;
    }

    public double pedirDouble(String msg) {
        double num = 0;
        boolean repit;
        do {
            repit = false;
            try {
                System.out.print(Colors.ANSI_BLACK + msg);
                num = sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println(Colors.ANSI_YELLOW + "Err: Deus introduir un numero");
                sc.next();
                repit = true;
            }
        } while (repit);
        return num;
    }

    public double pedirDoubleEntre(double max, double min, String msg) {
        double num = 0;
        boolean repit;
        do {
            repit = false;
            try {
                System.out.print(Colors.ANSI_BLACK + msg);
                num = sc.nextInt();
                if (min > num || max < num) {
                    repit = true;
                }
            } catch (InputMismatchException e) {
                System.out.println(Colors.ANSI_YELLOW + "Err: Deus introduir un numero entre " + min + " - " + max);
                sc.next();
                repit = true;
            }
        } while (repit);
        return num;
    }

    public String pedirString(String msg) {
        String str = null;
        boolean repit;
        do {
            System.out.print(Colors.ANSI_BLACK + msg);
            repit = false;
            try {
                str = sc.nextLine();
            } catch (NoSuchElementException e) {//TODO excepcion
                System.out.println(Colors.ANSI_YELLOW + "Err: Deus introduir un text");
                sc.next();//TODO comprovar el buffer
                repit = true;
            }
        } while (repit);
        return str;
    }
}
