import org.exist.xmldb.DatabaseImpl;
import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.Collection;
import org.xmldb.api.base.Database;
import org.xmldb.api.base.XMLDBException; //Cannot resolve symbol 'xmldb'

public class ExistDBConnector {
    private static final String URI = "xmldb:exist://localhost:8080/exist/xmlrpc";
    private static final String DRIVER = "org.exist.xmldb.DatabaseImpl";
    private static final String USER = "your_username";
    private static final String PASSWORD = "your_password";

    public static Collection connectExistDB() {
        try {
            // Cargar el controlador de la base de datos
            Class<?> cl = Class.forName(DRIVER);
            Database database = (Database) cl.newInstance();
            DatabaseManager.registerDatabase(database);

            // Conectar a la base de datos
            return DatabaseManager.getCollection(URI + "/db", USER, PASSWORD);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | XMLDBException e) {
            e.printStackTrace();
            return null;
        }
    }
}
