package gestor;


import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.nio.file.Files;

import org.bson.Document;
import org.bson.types.ObjectId;

public class Gestor {
    
    public <T> void insertarDocumento(String nombreColeccion, T documento) {
        try {
            MongoDatabase database = Conexion.getConexion();
            MongoCollection<T> collection = database.getCollection(nombreColeccion, (Class<T>) documento.getClass());
            collection.insertOne(documento);
        } catch (Exception e) {
            System.err.println("Error al conectar a MongoDB: " + e.getMessage());
        }
    }

    public <T> void eliminarDocumento(String nombreColeccion, Document filtro) {
        try {
            MongoDatabase database = Conexion.getConexion();
            MongoCollection<T> collection = database.getCollection(nombreColeccion, (Class<T>) Document.class);
            collection.deleteOne(filtro);
        } catch (Exception e) {
            System.err.println("Error al conectar a MongoDB: " + e.getMessage());
        }
    }

    public <T> void updateDocumento(String nombreColeccion, Document filtro, T documento) {
        try {
            MongoDatabase database = Conexion.getConexion();
            MongoCollection<T> collection = database.getCollection(nombreColeccion, (Class<T>) documento.getClass());
            collection.updateOne(filtro, new Document("$set", documento));
        } catch (Exception e) {
            System.err.println("Error al conectar a MongoDB: " + e.getMessage());
        }
    }

    public FindIterable<Document> realizarConsultaMongoDB(String nombreColeccion, Document filtro) {
        try {
            MongoDatabase database = Conexion.getConexion();
            MongoCollection<Document> collection = database.getCollection(nombreColeccion);
            return collection.find(filtro);

        } catch (Exception e) {
            System.err.println("Error al conectar a MongoDB: " + e.getMessage());
        }
        return null;
    }

    //Nose si el nombre es el correcto
    public <T> T getAtribute(String nombreColeccion, Document filtro, String nombreAtributo, Class<T> tipoResultado) {
        FindIterable<Document> fr = realizarConsultaMongoDB(nombreColeccion, filtro);
        T resObject = null;
        if (fr != null) {
            Document document = fr.first();
            if (document != null) {
                resObject = document.get(nombreAtributo, tipoResultado);
            }
        }
        return resObject;
    }

    public ObjectId getID(String nombreAtributo, String atributo, String collection) {
        Document filtro = new Document(nombreAtributo, atributo);
        FindIterable<Document> fr = realizarConsultaMongoDB(collection, filtro);
        if (fr != null) {
            Document document = fr.first();
            if (document != null) {
                return document.getObjectId("_id");
            }
        }
        return null;
    }

    public void export(String collectionName, String path) {
        try {
            File file = new File(path);
            if (file.exists()) {
                file.delete();
            }
            file.createNewFile();
            MongoDatabase database = Conexion.getConexion();
            MongoCollection<Document> collection = database.getCollection(collectionName);
            FindIterable<Document> documents = collection.find();
            try (FileWriter fileWriter = new FileWriter(file, true)) {
                for (Document document : documents) {
                    fileWriter.write(document.toJson() + "\n");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void importar(String collectionName, String path) {
        try {
            if (!Files.exists(Paths.get(path))) {
                System.out.println("El archivo no existe. No se puede realizar la importación.");
                return;
            }
            MongoDatabase database = Conexion.getConexion();
            MongoCollection<Document> collection = database.getCollection(collectionName);
            Files.lines(Paths.get(path)).forEach(line -> {
                Document document = Document.parse(line);
                collection.insertOne(document);
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
