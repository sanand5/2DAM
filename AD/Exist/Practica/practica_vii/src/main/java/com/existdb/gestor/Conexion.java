package com.existdb.gestor;

import java.lang.reflect.InvocationTargetException;

import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.Collection;
import org.xmldb.api.base.Database;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.XMLResource;

public class Conexion {

    private static String uri = "xmldb:exist://127.0.0.1:8080/exist/xmlrpc/db/10813358";
    private static String username = "admin";
    private static String password = "admin";
    private static Collection collection;

    private Conexion() {
    }

    public static void iniciarConexion()
            throws ClassNotFoundException, InstantiationException, IllegalAccessException, XMLDBException,
            IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
        Class<?> cl = Class.forName("org.exist.xmldb.DatabaseImpl");
        Database database = (Database) cl.getDeclaredConstructor().newInstance();
        // Comentar la siguiente línea si no se desea crear la base de datos
        // automáticamente
        database.setProperty("create-database", "true");
        DatabaseManager.registerDatabase(database);
        collection = DatabaseManager.getCollection(uri, username, password);
    }

    public static Collection getDatabase() {
        return collection;
    }

    public static XMLResource getDocument(String nombreDocumento) {
        XMLResource document = null;

        try {
            document = (XMLResource) collection.getResource(nombreDocumento);
            if (document != null) {
                return document;
            }

        } catch (XMLDBException e) {
            System.out.println("No se ha podido cargar el documento.");
            System.out.println(e.getMessage());
        }

        return null;
    }

    public static void cerrarConexion() {
        if (collection != null) {
            try {
                collection.close();
            } catch (XMLDBException e) {
                e.printStackTrace();
            }
        }
    }

    public static boolean isConexionEstablecida() {
        return collection != null;
    }
}
