
import utilidades.Colors;
import utilidades.ReadClient;

public class menus {

    ReadClient rc = new ReadClient();
    
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
                    importar();
                    break;
                case 5:
                    exportar();
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
                    (3) Listar
                        Modificar:
                    (4)         Nombre
                    (5)         Apellidos
                    (6)         Fecha de Nacimiento
                    (7)         NIA""");
            menu = rc.pedirIntRango("?", 0, 7);
            switch (menu) {
                case 0:
                    System.out.println("Has salido del menu de Alumnos");
                    repetir = false;
                    break;
                case 1:
                    
                    break;
                case 2:
                    
                    break;
                case 3:
                    
                    break;
                case 4:
                    String nombre = rc.pedirString("Nuevo nombre de el alumno: ", false);
                    
                    break;
                case 5:
                    String apellidos = rc.pedirString("Nuevos apellidos de el alumno: ", false);
                    
                    break;
                case 6:
                    int dia = rc.pedirIntRango("Nuevo dia nacimiento de el alumno: ", 1, 31);
                    int mes = rc.pedirIntRango("Nuevo mes nacimiento de el alumno: ", 1, 12);
                    int ano = rc.pedirIntRango("Nuevo año nacimiento de el alumno: ", 1900, 2024);
                    String fecha = String.format("%02d/%02d/%d", dia, mes, ano);
                    
                    break;
                case 7:
                    System.out.println("Introduce primero el nuevo nia del alumno");
                    String nia = "";
                    System.out.println("Introduce el nia antiguo del alumno");
                    
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
                    
                    break;
                case 2:
                    
                    break;
                case 3:
                    
                    break;
                case 4:
                    
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
                    (3) Qualificar y Modificar
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
                    
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    break;
                default:
                    Colors.warMsg("Debes introducir un valor valido");
            }

        }
    }

    public void exportar() {
        try {
        } catch (Exception e) {
            Colors.errMsg("Imposible conectar a MongoDB.");
        }
    }
    
    public void importar() {
        try {
        } catch (Exception e) {
            Colors.errMsg("Imposible conectar a MongoDB.");
        }
    }
}
