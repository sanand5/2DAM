package gestor;

import org.bson.types.ObjectId;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import utilidades.Colors;
import utilidades.ReadClient;

import java.io.FileWriter;
import java.io.IOException;

import org.bson.Document;

public class alumnos extends Gestor {
    public final static String collection = "alumnos";
    public final static String path = "res/alumnos.json";
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
        super.eliminarDocumento(collection, new Document("nia", nia));
    }

    public ObjectId getID(String nia) {
        return super.getID("nia", nia, collection);
    }

    public boolean comprobarAlumno(String nia) {
        return getID(nia) != null;
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
        try {
            String nombre = rc.pedirString("Nombre de el alumno: ", false);
            String apellidos = rc.pedirString("Apellidos de el alumno: ", false);
            int dia = rc.pedirIntRango("Dia nacimiento de el alumno: ", 1, 31);
            int mes = rc.pedirIntRango("Mes nacimiento de el alumno: ", 1, 12);
            int ano = rc.pedirIntRango("Año nacimiento de el alumno: ", 1900, 2024);
            String nia = pedirNIA(false);
            String fecha = String.format("%02d/%02d/%d", dia, mes, ano);
            System.out.println("Resumen de los datos:\n");
            System.out.printf("Nombre completo: %s %s %nFecha de nacimiento: %s%nNia: %s%n%n", nombre, apellidos, fecha,
                    nia);
            String opcion = rc.pedirOpcion("Quieres dar de alta al alumno", "s", "n");
            if (opcion.equals("s")) {
                insertAlumno(nombre, apellidos, fecha, nia);
                Colors.okMsg("Se a dado de alta el alumno :)");
            } else {
                Colors.warMsg("Se ha cancelado la operación");
            }
        } catch (Exception e) {
            Colors.errMsg("Imposible conectar a MongoDB.");
        }
    }

    public void baja() {
        try {

            matriculas matr = new matriculas();
            String nia = pedirNIA(true);
            if (!nia.equals("0")) {
                ObjectId id = getID(nia);
                FindIterable<Document> fr = super.realizarConsultaMongoDB(matriculas.collection,
                        new Document("idAlumno", id));
                for (Document doc : fr) {
                    ObjectId matrId = doc.getObjectId("_id");
                    matr.deleteMatricula(matrId);
                }
                Colors.okMsg("Se han eliminado todas las matriculas con ese alumno");
                deleteAlumno(nia);
                Colors.okMsg("Se a dado de baja el alumno :()");
            }
        } catch (Exception e) {
            Colors.errMsg("Imposible conectar a MongoDB.");

        }
    }

    public void mostrarAlumnos() {
        try {
            FindIterable<Document> fr = realizarConsultaMongoDB(collection, new Document());
            for (Document doc : fr) {
                String nia = doc.getString("nia");
                String nombre = doc.getString("nombre");
                String apellidos = doc.getString("apellidos");
                String fecha = doc.getString("fecha");
                System.out.printf("%s : %s %s, %s%n", nia, nombre, apellidos, fecha);
            }
        } catch (Exception e) {
            Colors.errMsg("Imposible conectar a MongoDB.");
        }
    }

    public void modificar(String atributo, String valor) {
        try {
            String nia = pedirNIA(true);
            if (nia != null && valor != null) {
                super.updateDocumento(alumnos.collection, new Document("nia", nia), new Document(atributo, valor));
                Colors.okMsg(String.format("Se a modificado '%s' del alumno", atributo));
            }
        } catch (Exception e) {
            Colors.errMsg("Imposible conectar a MongoDB.");
        }
    }
}
