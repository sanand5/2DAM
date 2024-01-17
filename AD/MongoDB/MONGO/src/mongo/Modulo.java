package mongo;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import utilidades.Lector;
import java.util.ArrayList;
import java.util.List;
import utilidades.Conexion;

public class Modulo {

    private String nombreModulo;
    private String codigoModulo;

    public Modulo(String nombreModulo, String codigoModulo) {
        this.nombreModulo = nombreModulo;
        this.codigoModulo = codigoModulo;
    }

    public String getNombreModulo() {
        return nombreModulo;
    }

    public String getCodigoModulo() {
        return codigoModulo;
    }

    public void setNombreModulo(String nombreModulo) {
        this.nombreModulo = nombreModulo;
    }

    public void setCodigoModulo(String codigoModulo) {
        this.codigoModulo = codigoModulo;
    }

    public static Modulo darDeAltaModulo() {
        String nombreModulo = Lector.pedirString("Nombre del módulo: ");
        String codigoModulo = Lector.pedirString("Código del módulo: ");

        MongoDatabase database = Conexion.obtenerConexion();
        MongoCollection<Document> collection = database.getCollection("modulos");

        Document moduloDoc = new Document("nombre_modulo", nombreModulo)
                .append("codigo_modulo", codigoModulo);

        if (collection.countDocuments(new Document("codigo_modulo", codigoModulo)) == 0) {
            collection.insertOne(moduloDoc);
            System.out.println("Módulo dado de alta correctamente.");
            return new Modulo(nombreModulo, codigoModulo);
        } else {
            System.out.println("Error: Ya existe un módulo con el mismo código en la base de datos. Código: " + codigoModulo);
            return null;
        }
    }

    public static void darDeBajaModuloPorCodigo() {
        String codigoBaja = Lector.pedirString("Ingrese el código del módulo a dar de baja: ");

        MongoDatabase database = Conexion.obtenerConexion();
        MongoCollection<Document> collection = database.getCollection("modulos");

        if (collection.countDocuments(new Document("codigo_modulo", codigoBaja)) > 0) {
            collection.deleteOne(new Document("codigo_modulo", codigoBaja));
            System.out.println("Módulo con código " + codigoBaja + " dado de baja correctamente.");
        } else {
            System.out.println("No se encontró ningún módulo con el código " + codigoBaja + ".");
        }
    }

    public static List<Modulo> listarModulos() {
        MongoDatabase database = Conexion.obtenerConexion();
        MongoCollection<Document> collection = database.getCollection("modulos");

        FindIterable<Document> documents = collection.find();
        List<Modulo> modulos = new ArrayList<>();

        for (Document document : documents) {
            String nombreModulo = document.getString("nombre_modulo");
            String codigoModulo = document.getString("codigo_modulo");

            System.out.println("Nombre Módulo: " + nombreModulo + ", Código Módulo: " + codigoModulo);

            modulos.add(new Modulo(nombreModulo, codigoModulo));
        }

        if (modulos.isEmpty()) {
            System.out.println("No hay ningún módulo registrado.");
        }

        return modulos;
    }

    public static Modulo buscarModuloPorCodigo(String codigoBusqueda) {
        MongoDatabase database = Conexion.obtenerConexion();
        MongoCollection<Document> collection = database.getCollection("modulos");

        Document query = new Document("codigo_modulo", codigoBusqueda);
        Document result = collection.find(query).first();

        if (result != null) {
            String nombreModulo = result.getString("nombre_modulo");
            String codigoModulo = result.getString("codigo_modulo");
            return new Modulo(nombreModulo, codigoModulo);
        } else {
            System.out.println("No se encontró ningún módulo con código " + codigoBusqueda + ".");
            return null;
        }
    }
}
