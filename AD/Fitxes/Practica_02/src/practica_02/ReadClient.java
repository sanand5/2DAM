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
    
    public void bufferClear() {
        sc.nextLine();
    }

    public int pedirInteger(String msg) {
        int num = 0;
        boolean repit;
        do {
            repit = false;
            try {
                System.out.print(msg);
                num = sc.nextInt();
                
            } catch (InputMismatchException e) {
                Colors.warMsg("Deus introduir un número enter");
                sc.next();
                repit = true;
            }
        } while (repit);
        return num;
    }

    public double pedirDouble(String msg, Double min, Double max) {
        double num = 0;
        boolean repit;
        String rang="";

        do {
            repit = false;
            try {
                System.out.print(msg);
                num = sc.nextDouble();
                if ((min != null && num < min) || (max != null && num > max)) {
                    rang = min + " - " + max ;
                    Colors.warMsg("Deus introduir un número entre " + rang);
                    repit = true;
                }
            } catch (InputMismatchException e) {
                sc.next();
                Colors.warMsg("Deus introduir un número");
                repit = true;
            }
        } while (repit);

        return num;
    }

    public String pedirString(String msg) {
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
        return str;
    }
}
