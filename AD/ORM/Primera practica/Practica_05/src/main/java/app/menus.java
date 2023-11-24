package app;

import utilidades.Colors;
import utilidades.ReadClient;
import gestor.*;

public class menus {
    ReadClient rc = new ReadClient();
    gsAlumnos gsAl = new gsAlumnos();
    gsModulos gsMod = new gsModulos();
    gsMatriculas gsMat = new gsMatriculas();

    public void mainMenu() {
        boolean repit = true;
        while (repit) {
            System.out.println("""
                    (0) Salir
                    (1) Menu Alumnos
                    (2) Menu Modulos
                    (3) Evaluar""");
            int menu = rc.pedirIntRango("?", 0, 3);
            switch (menu) {
                case 0:
                    System.out.println("Programa cerrado");
                    repit = false;
                    break;
                case 1:
                    menuAlumnos();
                    break;
                case 2:
                    menuModulos();
                    break;
                case 3:
                    menuMatriculas();
                    break;
                default:
                    Colors.warMsg("Debes introducir un valor valido");
                    break;
            }
        }
    }

    public void menuAlumnos() {
        int menu = 0;
        boolean repetir = true;
        while (repetir) {
            System.out.println("""
                    Menu Alumno
                    (0) Salir
                    (1) Alta
                    (2) Baja
                    (3) Listar""");
            menu = rc.pedirIntRango("?", 0, 3);
            switch (menu) {
                case 0:
                    System.out.println("Has salido del menu de Alumnos");
                    repetir = false;
                    break;
                case 1:
                    gsAl.alta();
                    break;
                case 2:
                    gsAl.baja();
                    break;
                case 3:
                    gsAl.mostrarAlumnos();
                    break;

                default:
                    Colors.warMsg("Debes introducir un valor valido");
                    break;
            }
        }
    }

    public void menuModulos() {
        gsModulos gsMod = new gsModulos();
        int menu = 0;
        boolean repetir = true;
        while (repetir) {
            System.out.println("""
                    Menu Modulo
                    (0) Salir
                    (1) Alta
                    (2) Baja
                    (3) Listar
                    (4) Matricular Alumno""");
            menu = rc.pedirIntRango("?", 0, 4);
            switch (menu) {
                case 0:
                    repetir = false;
                    System.out.println("Has salido del menu de Modulo");
                    break;
                case 1:
                    gsMod.alta();
                    break;
                case 2:
                    gsMod.baja();
                    break;
                case 3:
                    gsMod.mostrarModulos();
                    break;
                case 4:
                    gsMod.matricular();
                    break;
                default:
                    Colors.warMsg("Debes introducir un valor valido");
                    break;
            }
        }
    }

    public void menuMatriculas() {
        int menu;
        boolean repetir = true;
        while (repetir) {
            System.out.println("""
                    Menu Evaluar
                    (0) Salir
                    (1) Qualificar
                    (2) Modificar
                    (3) Mostrar Notas de un Modulo
                    (4) Mostrar Notas de un Alumno
                    (5) Mostrar Notas del Centro""");
            menu = rc.pedirIntRango("?", 0, 5);
            switch (menu) {
                case 0:
                    repetir = false;
                    System.out.println("Has salido del menu de Evaluar");
                    break;
                case 1:
                    gsMat.qualificar();
                    break;
                case 2:
                    gsMat.modificar();
                    break;
                case 3:
                    gsMat.mostrarNotas();
                    break;
                case 4:
                    gsMat.mostrarAlumno();
                    break;
                case 5:
                    gsMat.mostrarAll();
                    break;
                default:
                    Colors.warMsg("Debes introducir un valor valido");
            }

        }
    }
}
