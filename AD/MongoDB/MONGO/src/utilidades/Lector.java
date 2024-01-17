package utilidades;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Lector {
    private static final Scanner scanner = new Scanner(System.in);

    public static String pedirString(String pregunta) {
        System.out.print(pregunta);
        return scanner.nextLine();
    }

    public static Date pedirFecha(String mensaje) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);

        Date fecha = null;
        boolean formatoCorrecto = false;

        do {
            System.out.print(mensaje);

            try {
                String input = scanner.nextLine();
                fecha = dateFormat.parse(input);
                System.out.println(fecha);
                formatoCorrecto = true;
            } catch (ParseException e) {
                System.out.println("Formato de fecha incorrecto. Inténtelo nuevamente.");
            }

        } while (!formatoCorrecto);

        return fecha;
    }
}
