package utilidades;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class ReadClient {

    public int pedirInt(String msg) {
        int num = 0;
        boolean repit;
        do {
            repit = false;
            try {
                num = Integer.parseInt(pedirString(msg, false));

            } catch (InputMismatchException | NumberFormatException e) {
                Colors.warMsg("Debes introducir un número entero");
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
                num = Double.parseDouble(pedirString(msg, false));
            } catch (InputMismatchException | NumberFormatException e) {
                Colors.warMsg("Debes introducir un número decimal");
                repit = true;
            }
        } while (repit);
        return num;
    }

    public int pedirIntPositivo(String msg) {
        int num;
        do {
            num = pedirInt(msg);
            if (num <= 0) {
                Colors.warMsg("El número debe ser positivo.");
            }
        } while (num <= 0);
        return num;
    }

    public int pedirIntRango(String msg, int min, int max) {
        int num;
        do {
            num = pedirInt(msg);
            if (num < min || num > max) {
                Colors.warMsg("El número debe estar en el rango [" + min + ", " + max + "].");
            }
        } while (num < min || num > max);
        return num;
    }

    public double pedirDoublePositivo(String msg) {
        double num;
        do {
            num = pedirDouble(msg);
            if (num <= 0) {
                Colors.warMsg("El número debe ser positivo.");
            }
        } while (num <= 0);
        return num;
    }

    public double pedirDoubleRango(String msg, double min, double max) {
        double num;
        do {
            num = pedirDouble(msg);
            if (num < min || num > max) {
                Colors.warMsg("El número debe estar en el rango [" + min + ", " + max + "].");
            }
        } while (num < min || num > max);
        return num;
    }

    public String pedirString(String msg, boolean allowNull) {
        Scanner sc = new Scanner(System.in);
        String str = null;
        boolean repit;
        do {
            System.out.print(msg);
            repit = false;
            try {
                str = sc.nextLine();
                if (!allowNull && (str == null || str.trim().isEmpty())) {
                    repit = true;
                    Colors.warMsg("No puedes dejar el campo vacío");
                }
            } catch (NoSuchElementException e) {
                Colors.warMsg("Debes introducir un texto");
                repit = true;
            }
        } while (repit);
        sc.close();
        return str;
    }
}
