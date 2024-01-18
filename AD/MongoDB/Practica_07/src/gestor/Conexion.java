package gestor;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

public class Conexion {
    final static String DB = "10813358";
    final static String USER = "10813358";
    final static String PASSWORD = "10813358";
    final static String IP = "10.0.219.21";
    final static String PORT = "27017";
    final static String URL = String.format("mongodb://%s:%s@%s:%s/", USER, PASSWORD, IP, PORT);
    //final static String URL = "mongodb://localhost:27017/";

    private static MongoClient mongoClient;

    public static MongoDatabase getConexion() {
        if (mongoClient == null) {
            mongoClient = MongoClients.create(URL);
        }
        return mongoClient.getDatabase(DB);
    }

    public static void cerrarConexion() {
        if (mongoClient != null) {
            mongoClient.close();
            mongoClient = null;
        }

    }
}
