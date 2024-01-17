package gestor;

import org.bson.types.ObjectId;

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

    public void crearAlumno() {
        String nombre = rc.pedirString("Nombre de el alumno: ", false);
        String apellidos = rc.pedirString("Apellidos de el alumno: ", false);
        int dia = rc.pedirIntRango("Dia nacimiento de el alumno: ", 1, 31);
        int mes = rc.pedirIntRango("Mes nacimiento de el alumno: ",1, 12);
        int ano = rc.pedirIntRango("Año nacimiento de el alumno: ",1900, 2024);
        String nia = rc.pedirString("NIA de el alumno: ", false);
        String fecha = String.format("%02d/%02d/%d", dia, mes, ano);
        insertAlumno(nombre, apellidos, fecha, nia);
    }
    
    public void eliminarAlumno() {
        String nia = rc.pedirString("NIA de el alumno: ", false);
        deleteAlumno(nia);
    }
}
