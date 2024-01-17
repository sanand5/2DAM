package utilidades;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

public class Conexion {

    private static final String HOST = "localhost";
    private static final int PUERTO = 27017;
    private static final String NOMBRE_BD = "10810393";

    private static MongoClient mongoClient;

    public static MongoDatabase obtenerConexion() {
        if (mongoClient == null) {
            mongoClient = MongoClients.create("mongodb://10707423:10707423@10.0.219.21:27017/");
        }
        return mongoClient.getDatabase(NOMBRE_BD);
    }

    public static void cerrarConexion() {
        if (mongoClient != null) {
            mongoClient.close();
            mongoClient = null;
        }
    }
}
