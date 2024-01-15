package practica_06;

import practica_06.gestor.gsAlumnos;
import practica_06.gestor.gsMatriculas;
import practica_06.gestor.gsModulo;
import practica_06.utilidades.Colors;
import practica_06.utilidades.ReadClient;

public class menus {

    ReadClient rc = new ReadClient();
    gsAlumnos gsAl = new gsAlumnos();
    gsModulo gsMod = new gsModulo();
    gsMatriculas gsMat = new gsMatriculas();
    
    public void mainMenu() {
        crearTablas();
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
                    System.out.println("Desarrolando");
                    importar();
                    break;
                case 5:
                    System.out.println("Desarrolando");
                    exportar();
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
        gsModulo gsMod = new gsModulo();
        int menu = 0;
        boolean repetir = true;
        while (repetir) {
            System.out.println("""
                    Menu Modulo
                    (0) Salir
                    (1) Alta
                    (2) Baja
                    (3) Listar""");
            menu = rc.pedirIntRango("?", 0, 3);
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
                    (1) Matricular Alumno
                    (2) Qualificar
                    (3) Modificar
                    (4) Mostrar Notas de un Modulo de un Alumno
                    (5) Mostrar Notas de un Alumno
                    (6) Mostrar Notas del Centro""");
            menu = rc.pedirIntRango("?", 0, 6);
            switch (menu) {
                case 0:
                    repetir = false;
                    System.out.println("Has salido del menu de Evaluar");
                    break;
                case 1:
                    gsMat.crearMatricula();
                    break;
                case 2:
                    gsMat.qualificar();
                    break;
                case 3:
                    gsMat.modificarNotas();
                    break;
                case 4:
                    gsMat.mostrarModuloAlumno();
                    break;
                case 5:
                    int id = gsAl.getIDConNIA();
                    gsMat.mostrarModulosAlumno(id);
                    break;
                case 6:
                    gsMat.mostrarCentro();
                    break;
                default:
                    Colors.warMsg("Debes introducir un valor valido");
            }

        }
    }

    public void crearTablas() {
        gsAl.createTable();
        gsMod.createTable();
        gsMat.createTable();
    }
    
    public void importar() {
        gsAl.importTable();
        gsMod.importTable();
        gsMat.importTable();
    }
    public void exportar() {
        gsAl.exportTable();
        gsMod.exportTable();
        gsMat.exportTable();
    }
}
