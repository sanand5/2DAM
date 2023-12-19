package utilidades;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class ReadClient {
    private static Colors cl = new Colors();
    Scanner sc = new Scanner(System.in);

    /**
     * Solicita un entero al usuario desde la consola.
     *
     * @param msg Mensaje que se muestra al usuario.
     * @return El entero introducido por el usuario.
     */
    public int pedirInt(String msg) {
        int num = 0;
        boolean repit;
        do {
            repit = false;
            try {
                num = Integer.parseInt(pedirString(msg, false));

            } catch (InputMismatchException | NumberFormatException e) {
                cl.warMsg("Debes introducir un número entero");
                repit = true;
            }
        } while (repit);
        return num;
    }
    
    /**
     * Solicita un número decimal al usuario desde la consola.
     *
     * @param msg Mensaje que se muestra al usuario.
     * @return El número decimal introducido por el usuario.
     */
    public double pedirDouble(String msg) {
        double num = 0;
        boolean repit;
        do {
            repit = false;
            try {
                num = Double.parseDouble(pedirString(msg, false));
            } catch (InputMismatchException | NumberFormatException e) {
                cl.warMsg("Debes introducir un número decimal");
                repit = true;
            }
        } while (repit);
        return num;
    }

    /**
     * Solicita un entero positivo al usuario desde la consola.
     *
     * @param msg Mensaje que se muestra al usuario.
     * @return El entero positivo introducido por el usuario.
     */
    public int pedirIntPositivo(String msg) {
        int num;
        do {
            num = pedirInt(msg);
            if (num <= 0) {
                cl.warMsg("El número debe ser positivo.");
            }
        } while (num <= 0);
        return num;
    }

    /**
     * Solicita un entero dentro de un rango al usuario desde la consola.
     *
     * @param msg Mensaje que se muestra al usuario.
     * @param min Valor mínimo permitido.
     * @param max Valor máximo permitido.
     * @return El entero dentro del rango introducido por el usuario.
     */
    public int pedirIntRango(String msg, int min, int max) {
        int num;
        do {
            num = pedirInt(msg);
            if (num < min || num > max) {
                cl.warMsg("El número debe estar en el rango [" + min + ", " + max + "].");
            }
        } while (num < min || num > max);
        return num;
    }

    /**
     * Solicita un número decimal positivo al usuario desde la consola.
     *
     * @param msg Mensaje que se muestra al usuario.
     * @return El número decimal positivo introducido por el usuario.
     */
    public double pedirDoublePositivo(String msg) {
        double num;
        do {
            num = pedirDouble(msg);
            if (num <= 0) {
                cl.warMsg("El número debe ser positivo.");
            }
        } while (num <= 0);
        return num;
    }

    /**
     * Solicita un número decimal dentro de un rango al usuario desde la consola.
     *
     * @param msg Mensaje que se muestra al usuario.
     * @param min Valor mínimo permitido.
     * @param max Valor máximo permitido.
     * @return El número decimal dentro del rango introducido por el usuario.
     */
    public double pedirDoubleRango(String msg, double min, double max) {
        double num;
        do {
            num = pedirDouble(msg);
            if (num < min || num > max) {
                cl.warMsg("El número debe estar en el rango [" + min + ", " + max + "].");
            }
        } while (num < min || num > max);
        return num;
    }

    /**
     * Solicita una cadena de texto al usuario desde la consola.
     *
     * @param msg       Mensaje que se muestra al usuario.
     * @param allowNull Indica si se permite una cadena de texto nula o vacía.
     * @return La cadena de texto introducida por el usuario.
     */
    public String pedirString(String msg, boolean allowNull) {
        String str = null;
        boolean repit;
        do {
            System.out.print(msg);
            repit = false;
            try {
                str = sc.nextLine();
                if (!allowNull && (str == null || str.trim().isEmpty())) {
                    repit = true;
                    cl.warMsg("No puedes dejar el campo vacío");
                }
            } catch (NoSuchElementException e) {
                cl.warMsg("Debes introducir un texto");
                repit = true;
            }
        } while (repit);
        return str;
    }
    public String pedirStringLow(String msg, boolean allowNull) {
        return pedirString(msg, allowNull).toLowerCase();
    }
}
