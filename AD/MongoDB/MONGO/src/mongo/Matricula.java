package mongo;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import utilidades.Conexion;
import utilidades.Lector;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Matricula {

    private Alumno alumno;
    private Modulo modulo;
    private Date fechaMatriculacion;

    public Matricula() {

    }

    public Matricula(Alumno alumno, Modulo modulo, Date fechaMatriculacion) {
        this.alumno = alumno;
        this.modulo = modulo;
        this.fechaMatriculacion = fechaMatriculacion;
    }

    public Alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }

    public Modulo getModulo() {
        return modulo;
    }

    public void setModulo(Modulo modulo) {
        this.modulo = modulo;
    }

    public Date getFechaMatriculacion() {
        return fechaMatriculacion;
    }

    public void setFechaMatriculacion(Date fechaMatriculacion) {
        this.fechaMatriculacion = fechaMatriculacion;
    }

    

    public static void matricularAlumnoPorDNIyCodigoModulo() {
        MongoDatabase database = Conexion.obtenerConexion();
        MongoCollection<Document> collectionMatriculas = database.getCollection("matriculas");

        String dni = Lector.pedirString("Ingrese el DNI del alumno:");

        String codigoModulo = Lector.pedirString("Ingrese el código del módulo:");

        Alumno alumno = Alumno.buscarAlumnoPorDNI(dni);
        Modulo modulo = Modulo.buscarModuloPorCodigo(codigoModulo);

        if (alumno == null) {
            System.out.println("Error: No se encontró ningún alumno con DNI " + dni);
            return;
        }

        if (modulo == null) {
            System.out.println("Error: No se encontró ningún módulo con código " + codigoModulo);
            return;
        }

        if (buscarMatriculaExistente(alumno.getDni(), modulo.getCodigoModulo())) {
            System.out.println("Error: El alumno ya está matriculado en este módulo.");
            return;
        }

        Date fechaMatriculacion = Lector.pedirFecha("Ingrese la fecha de matriculación (formato yyyy-MM-dd):");

        Document matriculaDoc = new Document("alumno_dni", alumno.getDni())
                .append("modulo_codigo", modulo.getCodigoModulo())
                .append("fecha_matriculacion", fechaMatriculacion);

        collectionMatriculas.insertOne(matriculaDoc);

        System.out.println("Alumno matriculado correctamente.");
    }

    public List<Matricula> listarMatriculas() {
        List<Matricula> matriculas = new ArrayList<>();
        MongoDatabase database = Conexion.obtenerConexion();
        MongoCollection<Document> collectionMatriculas = database.getCollection("matriculas");

        FindIterable<Document> documents = collectionMatriculas.find();

        for (Document document : documents) {
            String dniAlumno = document.getString("alumno_dni");
            Alumno alumno = Alumno.buscarAlumnoPorDNI(dniAlumno);
            Modulo modulo = Modulo.buscarModuloPorCodigo(document.getString("modulo_codigo"));
            Date fechaMatriculacion = document.getDate("fecha_matriculacion");

            Matricula matricula = new Matricula(alumno, modulo, fechaMatriculacion);
            matriculas.add(matricula);
        }

        return matriculas;
    }

    public static boolean buscarMatriculaExistente(String alumnoDni, String moduloCodigo) {
        MongoDatabase database = Conexion.obtenerConexion();
        MongoCollection<Document> collectionMatriculas = database.getCollection("matriculas");

        Document query = new Document("alumno_dni", alumnoDni)
                .append("modulo_codigo", moduloCodigo);

        return collectionMatriculas.countDocuments(query) > 0;
    }

    public static List<Matricula> buscarMatriculasPorDNI(String dniAlumno) {
        List<Matricula> matriculas = new ArrayList<>();
        MongoDatabase database = Conexion.obtenerConexion();
        MongoCollection<Document> collectionMatriculas = database.getCollection("matriculas");

        Document query = new Document("alumno_dni", dniAlumno);
        FindIterable<Document> documents = collectionMatriculas.find(query);

        for (Document document : documents) {
            Alumno alumno = Alumno.buscarAlumnoPorDNI(dniAlumno);
            Modulo modulo = Modulo.buscarModuloPorCodigo(document.getString("modulo_codigo"));
            Date fechaMatriculacion = document.getDate("fecha_matriculacion");

            Matricula matricula = new Matricula(alumno, modulo, fechaMatriculacion);
            matriculas.add(matricula);
        }

        return matriculas;
    }

    public static List<Matricula> buscarMatriculasPorCodigoModulo(String codigoModulo) {
        List<Matricula> matriculas = new ArrayList<>();
        MongoDatabase database = Conexion.obtenerConexion();
        MongoCollection<Document> collectionMatriculas = database.getCollection("matriculas");

        Document query = new Document("modulo_codigo", codigoModulo);
        FindIterable<Document> documents = collectionMatriculas.find(query);

        for (Document document : documents) {
            String dniAlumno = document.getString("alumno_dni");
            Alumno alumno = Alumno.buscarAlumnoPorDNI(dniAlumno);
            Modulo modulo = Modulo.buscarModuloPorCodigo(document.getString("modulo_codigo"));
            Date fechaMatriculacion = document.getDate("fecha_matriculacion");

            Matricula matricula = new Matricula(alumno, modulo, fechaMatriculacion);
            matriculas.add(matricula);
        }

        return matriculas;
    }
}
