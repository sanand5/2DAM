package mongo;

import com.mongodb.client.FindIterable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import utilidades.Conexion;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import utilidades.Lector;

public class Alumno {

    private String nombre;
    private String apellido;
    private String dni;

    public Alumno(String nombre, String apellido, String dni) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getDni() {
        return dni;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public static Alumno darDeAltaAlumno() {
        String nombre = Lector.pedirString("Nombre: ");
        String apellido = Lector.pedirString("Apellido: ");
        String dni = Lector.pedirString("DNI: ");

        MongoDatabase database = Conexion.obtenerConexion();
        MongoCollection<Document> collection = database.getCollection("alumnos");

        Document alumnoDoc = new Document("nombre", nombre)
                .append("apellido", apellido)
                .append("dni", dni);

        if (collection.countDocuments(new Document("dni", dni)) == 0) {
            collection.insertOne(alumnoDoc);
            System.out.println("Alumno dado de alta correctamente.");
            return new Alumno(nombre, apellido, dni);
        } else {
            System.out.println("Error: Ya existe un alumno con el mismo DNI en la base de datos. DNI: " + dni);
            return null;
        }
    }

    public static void darDeBajaAlumnoPorDNI() {
        String dniBaja = Lector.pedirString("Ingrese el DNI del alumno a dar de baja: ");

        MongoDatabase database = Conexion.obtenerConexion();
        MongoCollection<Document> collection = database.getCollection("alumnos");

        if (collection.countDocuments(new Document("dni", dniBaja)) > 0) {
            collection.deleteOne(new Document("dni", dniBaja));
            System.out.println("Alumno con DNI " + dniBaja + " dado de baja correctamente.");
        } else {
            System.out.println("No se encontró ningún alumno con DNI " + dniBaja + ".");
        }
    }

    private static void borrarEvaluacion(long evaluacionId, Connection connection) throws SQLException {
        String deleteEvaluacionQuery = "DELETE FROM evaluaciones WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(deleteEvaluacionQuery)) {
            preparedStatement.setLong(1, evaluacionId);
            preparedStatement.executeUpdate();
        }
    }

    private static void borrarMatricula(long matriculaId, Connection connection) throws SQLException {
        String deleteMatriculaQuery = "DELETE FROM matriculas WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(deleteMatriculaQuery)) {
            preparedStatement.setLong(1, matriculaId);
            preparedStatement.executeUpdate();
        }
    }

    private static void borrarAlumno(String dni, Connection connection) throws SQLException {
        String deleteAlumnoQuery = "DELETE FROM alumnos WHERE dni = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(deleteAlumnoQuery)) {
            preparedStatement.setString(1, dni);
            preparedStatement.executeUpdate();
            System.out.println("Alumno con DNI " + dni + " dado de baja correctamente.");
        }
    }

    public static List<Alumno> listarAlumnos() {
        MongoDatabase database = Conexion.obtenerConexion();
        MongoCollection<Document> collection = database.getCollection("alumnos");

        FindIterable<Document> documents = collection.find();
        List<Alumno> alumnos = new ArrayList<>();

        for (Document document : documents) {
            String nombre = document.getString("nombre");
            String apellido = document.getString("apellido");
            String dni = document.getString("dni");

            System.out.println("Nombre: " + nombre + ", Apellido: " + apellido + ", DNI: " + dni);

            alumnos.add(new Alumno(nombre, apellido, dni));
        }

        if (alumnos.isEmpty()) {
            System.out.println("No hay ningún alumno registrado.");
        }

        return alumnos;
    }

    public static Alumno buscarAlumnoPorDNI(String dniBusqueda) {
        MongoDatabase database = Conexion.obtenerConexion();
        MongoCollection<Document> collection = database.getCollection("alumnos");

        Document query = new Document("dni", dniBusqueda);
        Document result = collection.find(query).first();

        if (result != null) {
            String nombre = result.getString("nombre");
            String apellido = result.getString("apellido");
            String dni = result.getString("dni");
            return new Alumno(nombre, apellido, dni);
        } else {
            System.out.println("No se encontró ningún alumno con DNI " + dniBusqueda + ".");
            return null;
        }
    }

}
