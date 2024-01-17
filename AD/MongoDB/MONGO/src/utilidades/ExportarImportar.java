package utilidades;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import java.io.BufferedReader;
import org.bson.Document;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class ExportarImportar {

    public static void exportarDatos() {

        exportarColeccion("alumnos.json", "alumnos");
        exportarColeccion("modulos.json", "modulos");
        exportarColeccion("matriculas.json", "matriculas");
        //exportarColeccion("evaluaciones.json", "evaluaciones");
    }

    public static void importarDatos() {

        importarColeccion("alumnos.json", "alumnos");
        importarColeccion("modulos.json", "modulos");
        importarColeccion("matriculas.json", "matriculas");
        //exportarColeccion("evaluaciones.json", "evaluaciones");
    }

    public static void exportarColeccion(String nombreArchivo, String nombreColeccion) {
        // Obtener la ruta del directorio actual del proyecto
        String rutaDirectorioActual = System.getProperty("user.dir");

        // Construir la ruta completa del archivo en el directorio actual
        String rutaArchivo = rutaDirectorioActual + File.separator + nombreArchivo;

        try (MongoClient mongoClient = MongoClients.create("mongodb://10707423:10707423@10.0.219.21:27017/")) {
            MongoDatabase database = mongoClient.getDatabase("10707423");
            MongoCollection<Document> collection = database.getCollection(nombreColeccion);

            // Crear directorios si no existen (opcional, según tus necesidades)
            File archivo = new File(rutaArchivo);
            archivo.getParentFile().mkdirs();

            try (MongoCursor<Document> cursor = collection.find().iterator(); PrintWriter writer = new PrintWriter(new FileWriter(archivo))) {

                // Escribir documentos BSON directamente en formato JSON
                cursor.forEachRemaining(document -> {
                    writer.println(document.toJson());
                });

                System.out.println("Datos exportados de la colección " + nombreColeccion + " al archivo " + rutaArchivo);

            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    public static void importarColeccion(String nombreArchivo, String nombreColeccion) {
        try (MongoClient mongoClient = MongoClients.create("mongodb://10707423:10707423@10.0.219.21:27017/")) {
            MongoDatabase database = mongoClient.getDatabase("10707423");
            MongoCollection<Document> collection = database.getCollection(nombreColeccion);

            // Leer el archivo JSON y agregar documentos a la colección
            try (BufferedReader reader = new BufferedReader(new FileReader(nombreArchivo))) {
                String linea;
                while ((linea = reader.readLine()) != null) {
                    // Analizar la línea como un documento JSON y agregarlo a la colección
                    Document document = Document.parse(linea);
                    collection.insertOne(document);
                }

                System.out.println("Datos importados desde el archivo " + nombreArchivo + " a la colección " + nombreColeccion);

            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

}
