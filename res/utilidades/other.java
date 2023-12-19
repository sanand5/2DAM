import java.io.File;

public class other {
    public static void main(String[] args) {
        // Pedir la ruta de la carpeta al usuario
        String rutaCarpeta = pedirRutaCarpeta();

        // Listar archivos y carpetas
        listarArchivos(rutaCarpeta);
    }

    private static String pedirRutaCarpeta() {
        System.out.print("Ingrese la ruta de la carpeta: ");
        return new java.util.Scanner(System.in).nextLine();
    }

    private static void listarArchivos(String rutaCarpeta) {
        File carpeta = new File(rutaCarpeta);

        if (carpeta.exists() && carpeta.isDirectory()) {
            System.out.println("Archivos en la carpeta " + carpeta.getName() + ":");
            listarArchivosRecursivo(carpeta, "");
        } else {
            System.out.println("La carpeta no existe o no es válida.");
        }
    }

    private static void listarArchivosRecursivo(File carpeta, String prefijo) {
        File[] archivos = carpeta.listFiles();

        if (archivos != null) {
            for (File archivo : archivos) {
                if (archivo.isDirectory()) {
                    // Si es una carpeta, mostrar y recursivamente listar sus archivos
                    System.out.println(prefijo + archivo.getName() + "/");
                    listarArchivosRecursivo(archivo, prefijo + "  ");
                } else {
                    // Si es un archivo, mostrar el nombre
                    System.out.println(prefijo + archivo.getName());
                }
            }
        }
    }
}

