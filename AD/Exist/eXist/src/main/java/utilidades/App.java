package utilidades;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

import centro.Personas;
//import centro.Alumnos;
//import centro.Modulos;
//import centro.Matriculas;
//import centro.Notas;
import persistencia.Conexion;
import persistencia.Lector;

public class App {

  public static void main(String[] args) throws IOException {
    Scanner reader = new Scanner(System.in);
    int opcion = 0;
    try {
      Conexion.iniciarConexion();

      // DESCOMENTA LA SIGUIENTE LINEA PARA HACER UNA CONEXION CON USUARIO A LA BASE
      // DE DATOS.
      // Conexion.setDatabaseDetails("127.0.0.1", 27017, "12549472", "12549472",
      // "12549472");
    } catch (Exception e) {
      System.out
          .println("No se ha podido conectar al servidor, comprueba la dirección o si el servidor está encendido.");
      return;
    }

    opcion = 0;
    do {
      System.out.println("1) Personas");
      System.out.println("2) Alumnos");
      System.out.println("3) Módulos");
      System.out.println("4) Evaluar");
      System.out.println("5) Exportar");
      System.out.println("6) Importar");
      System.out.println("7) Salir");
      System.out.print("¿Que opción desea elegir? (Elegir índice): ");
      try {
        opcion = reader.nextInt();
        switch (opcion) {
          case 1:
            Personas.menuPersonas();
            break;
          case 2:
            // Alumnos.menuAlumnos();
            break;
          case 3:
            // Modulos.menuModulos();
            break;
          case 4:
            // Notas.menuEvaluar();
            break;
          case 5:
            exportar();
            break;
          case 6:
            importar();
            break;
          case 7:
            System.out.println("Gracias por utilizar nuestro programa.");
            Lector.cerrarScanner();
            break;
          default:
            System.out.println("Tienes que elegir un número comprendido en el menú");
            break;
        }
      } catch (InputMismatchException e) {
        System.out.println("Debes introducir un número.");
        reader.nextLine();
      } catch (Exception e) {
        e.printStackTrace();
      }

    } while (opcion != 7);
  }

  public static void importar() {
    Personas.importar();
    /*
     * Alumnos.importar();
     * Modulos.importar();
     * Matriculas.importar();
     * Notas.importar();
     */
    System.out.println("Importación completada.");
  }

  public static void exportar() {
    Personas.exportar();
    /*
     * Alumnos.exportar();
     * Modulos.exportar();
     * Matriculas.exportar();
     * Notas.exportar();
     */
  }

}