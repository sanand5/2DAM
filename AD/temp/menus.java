package com.existdb;

import com.existdb.gestor.ClientComands;
import com.existdb.utilidades.Colors;
import com.existdb.utilidades.ReadClient;

public class menus {

    ReadClient rc = new ReadClient();
    ClientComands c = new ClientComands();

    public void mainMenu() {
        boolean repit = true;
        while (repit) {
            System.out.println("""
                    (0) Salir
                    (1) Menu Alumnos
                    (2) Menu Modulos
                    (3) Evaluar
                    (4) Importar
                    (5) Exportar""");
            int menu = rc.pedirIntRango("?", 0, 5);
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
                case 4:
                    c.importarAll();
                    ;
                    break;
                case 5:
                    c.exportarAll();
                    break;
                default:
                    Colors.warMsg("Debes introducir un valor valido");
                    break;
            }
        }
    }

    private void menuAlumnos() {
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
                    c.altaAlumno();
                    break;
                case 2:
                    c.bajaAlumno();
                    break;
                case 3:
                    c.listarAlumnos();
                    break;
                default:
                    Colors.warMsg("Debes introducir un valor valido");
                    break;
            }
        }
    }

    private void menuModulos() {
        int menu = 0;
        boolean repetir = true;
        while (repetir) {
            System.out.println("""
                    Menu Modulo
                    (0) Salir
                    (1) Alta
                    (2) Baja
                    (3) Listar
                    (4) Modificar Nombre""");
            menu = rc.pedirIntRango("?", 0, 4);
            switch (menu) {
                case 0:
                    repetir = false;
                    System.out.println("Has salido del menu de Modulo");
                    break;
                case 1:
                    c.altaModulo();
                    break;
                case 2:
                    c.bajaModulo();
                    break;
                case 3:
                    c.listarModulos();
                    break;
                default:
                    Colors.warMsg("Debes introducir un valor valido");
                    break;
            }
        }
    }

    private void menuMatriculas() {
        int menu;
        boolean repetir = true;
        while (repetir) {
            System.out.println("""
                    Menu Evaluar
                    (0) Salir
                    (1) Matricular Alumno
                    (2) Desmatricular Alumno
                    (3) Añadir Nota
                    (4) Modificar Nota
                    (5) Eliminar Nota
                    (6) Mostrar Notas del Centro""");
            menu = rc.pedirIntRango("?", 0, 6);
            switch (menu) {
                case 0:
                    repetir = false;
                    System.out.println("Has salido del menu de Evaluar");
                    break;
                case 1:
                    c.altaMatricula();
                    break;
                case 2:
                    c.bajaMatricula();
                    break;
                case 3:
                    c.anadirNota();
                    break;
                case 4:
                    c.modificarNota();
                    break;
                case 5:
                    c.eliminarNota();
                    break;
                case 6:
                    c.listarCentor();
                    break;
                default:
                    Colors.warMsg("Debes introducir un valor valido");
            }

        }
    }
}
