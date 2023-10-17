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
}
