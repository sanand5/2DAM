package mongo;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;
import utilidades.ExportarImportar;
//import utilidades.Conexion;
//import utilidades.ExportarImportar;

public class JDBC {

    public static void main(String[] args) throws IOException, SQLException {
        try (Scanner scanner = new Scanner(System.in)) {
            int opcion = 0;
            int base_de_datos = 0;
            boolean seleccionValida = false;

//            do {
//                System.out.println("Seleccione el tipo de base de datos:");
//                System.out.println("1. MySQL");
//                System.out.println("2. PostgreSQL");
//                System.out.print("Ingrese el número de la opción deseada: ");
//
//                if (scanner.hasNextInt()) {
//                    base_de_datos = scanner.nextInt();
//
//                    switch (base_de_datos) {
//                        case 1:
//                            base_de_datos = 1;
//                            seleccionValida = true;
//                            break;
//                        case 2:
//                            base_de_datos = 2;
//                            seleccionValida = true;
//                            break;
//                        default:
//                            System.out.println("Opción no válida. Inténtelo de nuevo.");
//                    }
//                    
//                } else {
//                    System.out.println("Entrada no válida. Inténtelo de nuevo.");
//                    scanner.next();
//                }
//
//            } while (!seleccionValida);
            do {
                try {
                    System.out.println("\nMenú Principal:");
                    System.out.println("1. Mantener Alumnos");
                    System.out.println("2. Mantener Módulos");
                    System.out.println("3. Evaluar");
                    System.out.println("4. Exportar");
                    System.out.println("5. Importar");
                    System.out.println("6. Salir");
                    System.out.print("Elija una opción: ");

                    opcion = scanner.nextInt();
                    scanner.nextLine();

                    switch (opcion) {
                        case 1:
                            menuMantenerAlumnos();
                            break;
                        case 2:
                            menuMantenerModulos();
                            break;
                        case 3:
                            evaluarAlumnos();
                            break;

                        case 4:
                            ExportarImportar.exportarDatos();

                            break;

                        case 5:

                            String respuesta;

                            do {
                                System.out.println("¿Desea importar datos? Si hay datos duplicados no se importarán. (s/n)");
                                respuesta = scanner.nextLine();

                                if (respuesta.equalsIgnoreCase("s")) {
                                    ExportarImportar.importarDatos();
                                    System.out.println("Operación de importación completada.");
                                    break;
                                } else if (respuesta.equalsIgnoreCase("n")) {
                                    System.out.println("Operación de importación cancelada.");
                                    break;
                                } else {
                                    System.out.println("Opción no válida. Por favor, ingrese 's' o 'n'.");
                                }
                            } while (true);

                            break;

                        case 6: {
                            System.out.println("Saliendo del programa. ¡Hasta luego!");
                            break;
                        }
                        default:
                            System.out.println("Opción no válida. Intente nuevamente.");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Error: Ingrese una opción válida.");
                    scanner.nextLine();
                }
            } while (opcion != 6);
        }

    }

    private static void menuMantenerAlumnos() {
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\nMantener Alumnos:");
            System.out.println("1. Dar de Alta Alumno");
            System.out.println("2. Dar de Baja Alumno");
            System.out.println("3. Listar Alumnos");
            System.out.println("4. Volver al Menú Principal");
            System.out.print("Elija una opción: ");

            try {
                opcion = scanner.nextInt();
                scanner.nextLine();

                switch (opcion) {
                    case 1:
                        Alumno.darDeAltaAlumno();
                        break;

                    case 2:
                        Alumno.darDeBajaAlumnoPorDNI();
                        break;

                    case 3:
                        Alumno.listarAlumnos();
                        break;

                    case 4:
                        System.out.println("Volviendo al Menú Principal.");
                        break;
                    default:
                        System.out.println("Opción no válida. Intente nuevamente.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Ingrese una opción válida.");
                scanner.nextLine();
                opcion = 0;
            }
        } while (opcion != 4);
    }

    private static void menuMantenerModulos() {
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\nMantener Módulos:");
            System.out.println("1. Dar de Alta Módulo");
            System.out.println("2. Dar de Baja Módulo");
            System.out.println("3. Listar Módulos");
            System.out.println("4. Matricular Alumno a un Módulo");
            System.out.println("5. Volver al Menú Principal");
            System.out.print("Elija una opción: ");

            try {
                opcion = scanner.nextInt();
                scanner.nextLine();

                switch (opcion) {
                    case 1:
                        Modulo.darDeAltaModulo();
                        break;

                    case 2:
                        Modulo.darDeBajaModuloPorCodigo();
                        break;

                    case 3:
                        Modulo.listarModulos();
                        break;

                    case 4:
                        Matricula.matricularAlumnoPorDNIyCodigoModulo();
                        break;

                    case 5:
                        System.out.println("Volviendo al Menú Principal.");
                        break;
                    default:
                        System.out.println("Opción no válida. Intente nuevamente.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Ingrese una opción válida.");
                scanner.nextLine();
                opcion = 0;
            }
        } while (opcion != 5);
    }

    private static void evaluarAlumnos() {
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\nMenú de Evaluación:");
            System.out.println("1. Calificar");
            System.out.println("2. Modificar");
            System.out.println("3. Mostrar boletín de notas");
            System.out.println("4. Volver al Menú Principal");
            System.out.print("Elija una opción: ");

            try {
                opcion = scanner.nextInt();
                scanner.nextLine();

                switch (opcion) {
                    case 1:
                        //Evaluacion.calificarEvaluaciones();
                        break;

                    case 2:
                        //Evaluacion.modificarEvaluaciones();
                        break;

                    case 3:
                        //Evaluacion.listarEvaluacionesPorAlumno();
                        break;

                    case 4:
                        System.out.println("Volviendo al Menú Principal.");
                        break;
                    default:
                        System.out.println("Opción no válida. Intente nuevamente.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Ingrese una opción válida.");
                scanner.nextLine();
                opcion = 0;
            }
        } while (opcion != 4);
    }

}
