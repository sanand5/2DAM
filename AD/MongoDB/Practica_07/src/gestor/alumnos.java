package gestor;

import org.bson.types.ObjectId;

import com.mongodb.client.FindIterable;

import utilidades.Colors;
import utilidades.ReadClient;

import org.bson.Document;

public class alumnos extends Gestor {
    final String collection = "alumnos";
    ReadClient rc = new ReadClient();

    public void insertAlumno(String nombre, String apellidos, String fecha, String nia) {
        Document alumno = new Document()
                .append("_id", new ObjectId())
                .append("nombre", nombre)
                .append("apellidos", apellidos)
                .append("fecha", fecha)
                .append("nia", nia);
        super.insertarDocumento(collection, alumno);
    }

    public void deleteAlumno(String nia) {
        super.eliminarDocumento(collection, "nia", nia);
    }

    public String encontrarID(String nia) {
        Document filtro = new Document("nia", nia);
        FindIterable<Document> fr = realizarConsultaMongoDB(collection, filtro);
        if (fr != null) {
            Document document = fr.first();
            if (document != null) {
                return document.getObjectId("_id").toString();
            }
        }
        return null;
    }

    public boolean comprobarAlumno(String nia) {
        return encontrarID(nia) != null;
    }

    /**
     * 
     * @param exist true para que el nia exista, false para que no exista
     * @return
     */
    public String pedirNIA(boolean exist) {
        String nia;
        String cancel = exist ? ", 0 para salir" : "";
        String errMsg = exist ? "El NIA no existe." : "El NIA ya existe.";

        do {
            nia = rc.pedirString("NIA del alumno" + cancel + ": ", false);
            if (nia.equals("0") && exist == true) {
                break;
            }
            if (!nia.matches("\\d+") || Integer.parseInt(nia) <= 0) {
                Colors.errMsg("NIA inválido. Debe ser un número positivo sin letras.");
            } else if ((exist && !comprobarAlumno(nia)) || (!exist && comprobarAlumno(nia))) {
                Colors.errMsg(errMsg);
            }

        } while ((exist && !comprobarAlumno(nia)) || (!exist && comprobarAlumno(nia)));

        return nia;
    }

    public void alta() {
        String nombre = rc.pedirString("Nombre de el alumno: ", false);
        String apellidos = rc.pedirString("Apellidos de el alumno: ", false);
        int dia = rc.pedirIntRango("Dia nacimiento de el alumno: ", 1, 31);
        int mes = rc.pedirIntRango("Mes nacimiento de el alumno: ", 1, 12);
        int ano = rc.pedirIntRango("Año nacimiento de el alumno: ", 1900, 2024);
        String nia = pedirNIA(false);
        String fecha = String.format("%02d/%02d/%d", dia, mes, ano);
        System.out.println("Resumen de los datos:\n");
        System.out.printf("Nombre completo: %s %s %nFecha de nacimiento: %s%nNia: %s%n%n", nombre, apellidos, fecha, nia);

        String opcion = rc.pedirOpcion("Quieres dar de alta al alumno", "s", "n");
        if (opcion == "s") {
            
        } else {
            insertAlumno(nombre, apellidos, fecha, nia);
        }
    }

    public void baja() {
        String nia = pedirNIA(true);
        if (!nia.equals("0")) {
            deleteAlumno(nia);
        }
    }
}
