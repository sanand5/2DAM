package gestor;


import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

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

    public <T> void eliminarDocumento(String nombreColeccion, String campoClave, Object valorClave) {
        try {
            MongoDatabase database = Conexion.getConexion();
            MongoCollection<T> collection = database.getCollection(nombreColeccion, (Class<T>) Document.class);
            Document filtro = new Document(campoClave, valorClave);
            collection.deleteOne(filtro);
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
}
