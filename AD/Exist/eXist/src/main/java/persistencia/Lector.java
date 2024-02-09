package persistencia;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Lector {
  private static Scanner scanner = new Scanner(System.in);

  public static String leerString() {
    return scanner.nextLine();
  }

  public static int leerInt() {
    while (true) {
      try {
        int num = scanner.nextInt();
        return num;
      } catch (InputMismatchException e) {
        System.out.print("Por favor, introduce un número entero válido:");
      } catch (Exception e) {
      } finally {
        limpiarBuffer();
      }
    }
  }

  public static void limpiarBuffer() {
    scanner.nextLine();
  }

  public static double leerDouble() {
    while (true) {
      try {
        return scanner.nextDouble();
      } catch (InputMismatchException e) {
        System.out.print("Por favor, introduce un número decimal válido: ");
      } finally {
        limpiarBuffer();
      }
    }
  }

  public static float leerFloat() {
    while (true) {
      try {
        return scanner.nextFloat();
      } catch (InputMismatchException e) {
        System.out.println("Por favor, introduce un número flotante válido.");
      } finally {
        limpiarBuffer();
      }
    }
  }

  public static String leerDNI() {
    String dni;
    do {
      dni = scanner.next();
      if (!verificarFormatoDNI(dni)) {
        System.out.println("Introduce un DNI con formato correcto.");
      }
    } while (!verificarFormatoDNI(dni));
    return dni;

  }

  public static boolean verificarFormatoDNI(String dni) {
    String regex = "\\d{8}[a-zA-Z]";
    return Pattern.matches(regex, dni);
  }

  public static double leerNota() {
    while (true) {
      try {
        String linea = scanner.nextLine();
        Double nota = Double.parseDouble(linea);
        if (nota >= 0 && nota <= 10) {
          return nota;
        } else {
          System.out.println("Introduce un número del 0 al 10");
        }
      } catch (NumberFormatException e) {
        System.out.println(
            "Entrada inválida. Por favor, introduce un float. (recuerda que los decimales se escriben con puntos)");
      }
    }
  }

  public static boolean cerrarScanner() {
    scanner.close();
    return true;
  }
}
