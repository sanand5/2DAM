package gestor;


import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import utilidades.Colors;
import utilidades.ReadClient;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.function.Function;
import java.nio.file.Files;

import org.bson.Document;
import org.bson.types.ObjectId;

public class Gestor {
    
    public <T> void insertarDocumento(String nombreColeccion, T documento) {
        
            MongoDatabase database = Conexion.getConexion();
            MongoCollection<T> collection = database.getCollection(nombreColeccion, (Class<T>) documento.getClass());
            collection.insertOne(documento);
        
    }

    public <T> void eliminarDocumento(String nombreColeccion, Document filtro) {
        
            MongoDatabase database = Conexion.getConexion();
            MongoCollection<T> collection = database.getCollection(nombreColeccion, (Class<T>) Document.class);
            collection.deleteOne(filtro);
        
    }

    public <T> void updateDocumento(String nombreColeccion, Document filtro, T documento) {
        
            MongoDatabase database = Conexion.getConexion();
            MongoCollection<T> collection = database.getCollection(nombreColeccion, (Class<T>) documento.getClass());
            collection.updateOne(filtro, new Document("$set", documento));
        
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
            Colors.okMsg("Datos exportados correctamente a " + path);
        } catch (IOException e) {
            Colors.errMsg("Imposible exportar datos");
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
                ObjectId id = getAtribute(collectionName, new Document("_id", document.getObjectId("_id")), "_id",
                        ObjectId.class);
                if (id == null) {
                    collection.insertOne(document);
                } else {
                    updateDocumento(collectionName, new Document("_id", id), document);
                }
            });
            Colors.okMsg("Datos importados correctamente de " + path);
        } catch (IOException e) {
            Colors.errMsg("Imposible importar datos");
        }
    }
    
    public static void crearTablas() {
        try {
            MongoDatabase database = Conexion.getConexion();
            crearTablaSiNoExiste(database, "alumnos");
            crearTablaSiNoExiste(database, "modulos");
            crearTablaSiNoExiste(database, "matriculas");
        } catch (Exception e) {
            System.err.println("Error al conectar a MongoDB: " + e.getMessage());
        }
    }

    private static void crearTablaSiNoExiste(MongoDatabase database, String tablaNombre) {
        if (!existeTabla(database, tablaNombre)) {
            database.createCollection(tablaNombre);
            Colors.okMsg("Tabla '" + tablaNombre + "' creada.");
        }
    }

    private static boolean existeTabla(MongoDatabase database, String tablaNombre) {
        for (String existingCollection : database.listCollectionNames()) {
            if (existingCollection.equals(tablaNombre)) {
                return true;
            }
        }
        return false;
    }
}
