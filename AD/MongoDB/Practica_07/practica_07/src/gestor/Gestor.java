package gestor;


import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class Gestor {
    
    public <T> void insertarDocumento(String nombreColeccion, T documento) {
        try {
            // Obtener la base de datos
            MongoDatabase database = Conexion.getConexion();

            // Obtener o crear la colección
            MongoCollection<T> collection = database.getCollection(nombreColeccion, (Class<T>) documento.getClass());

            // Insertar el documento en la colección
            collection.insertOne(documento);

            System.out.println("Documento insertado exitosamente en MongoDB en la colección: " + nombreColeccion);
        } catch (Exception e) {
            System.err.println("Error al conectar a MongoDB: " + e.getMessage());
        }
    }

    public <T> void eliminarDocumento(String nombreColeccion, String campoClave, Object valorClave) {
        try {
            // Obtener la base de datos
            MongoDatabase database = Conexion.getConexion();

            // Obtener o crear la colección
            MongoCollection<T> collection = database.getCollection(nombreColeccion, (Class<T>) Document.class);

            // Crear el filtro para encontrar el documento a eliminar
            Document filtro = new Document(campoClave, valorClave);

            // Eliminar el documento de la colección
            collection.deleteOne(filtro);

            System.out.println("Documento eliminado exitosamente de MongoDB en la colección: " + nombreColeccion);
        } catch (Exception e) {
            System.err.println("Error al conectar a MongoDB: " + e.getMessage());
        }
    }
}
