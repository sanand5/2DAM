import org.xmldb.api.base.*;
import org.xmldb.api.modules.*;
import org.xmldb.api.base.Collection;
import org.xmldb.api.base.Resource;
import org.xmldb.api.base.CollectionManagementService;
import javax.xml.xquery.*;
import javax.xml.namespace.QName;
import net.xqj.exist.ExistXQDataSource;

import java.io.File;


public class ExistDBJava {

    private static final String IP = "localhost";
    private static final String PORT = "8080";
    private static final String URI = "xmldb:exist://"+IP+":"+PORT+"/exist/xmlrpc/db";
    private static final String driver = "org.exist.xmldb.DatabaseImpl";
    private static final String usuari = "admin";
    private static final String contrasenya = "admin";
    private static final String XMLFile = "mondial.xml";
    private static final String novaColeccio ="/aacereteCollection";

    public static void main(String args[]) throws XQException {

        try{

            //Creem una col·leccio
            crearColeccio();
            System.out.println("Col·lecció creada");

            //Afegim un arxiu Xml a la col·lecció
            afegirRecursColeccio();
            System.out.println("Arxiu xml afegit");

            //Fem una query sobre els arxius de la nostra col·leccio i separem els resultats a partir de splits (Mostrarem la llista de noms paisos)
            String query = "collection('aacereteCollection')/mondial/country/name";
            String nomPaisos = realitzarQuery(query);
            String [] names = nomPaisos.replaceAll("</name>","").split("<name>");

            for (String resultat: names) {

                System.out.print(resultat);

            }

        }catch (XMLDBException e){
            e.printStackTrace();
        }catch (ClassNotFoundException e){
            System.out.println(e);
        }catch (IllegalAccessException e){
            System.out.println(e);
        }catch (InstantiationException e){
            System.out.println(e);
        }catch (XQException e){
            System.out.println(e);
        }
    }

    private static void crearColeccio() throws XMLDBException, ClassNotFoundException, IllegalAccessException, InstantiationException{

        //Realitzem la connexio
        Class clas = Class.forName(driver);
        Database database = (Database) clas.newInstance();
        database.setProperty("create-database", "true");
        DatabaseManager.registerDatabase(database);

        //Creem la nova col·lecció
        Collection parent = DatabaseManager.getCollection(URI, usuari, contrasenya);
        CollectionManagementService c = (CollectionManagementService) parent.getService("CollectionManagementService", "1.0");
        c.createCollection(novaColeccio);
    }

    private static void afegirRecursColeccio() throws XMLDBException, ClassNotFoundException, IllegalAccessException, InstantiationException{

        File f = new File("mondial.xml");

        //Realitzem la connexio
        Class clas = Class.forName(driver);
        Database database = (Database) clas.newInstance();
        database.setProperty("create-database", "true");

       //Creem manegador
        DatabaseManager.registerDatabase(database);

        //Instanciem la col·lecció a la que afegim el recurs xml
        Collection collection = DatabaseManager.getCollection(URI+novaColeccio, usuari, contrasenya);

        //Afegim el recurs
        Resource resource = collection.createResource("mondial.xml", "XMLResource");
        resource.setContent(f);
        collection.storeResource(resource);
    }

    private static String realitzarQuery(String query) throws XQException{

        //Realitzem la connexio
        XQDataSource source = new ExistXQDataSource();
        source.setProperty("serverName", IP);
        source.setProperty("port", PORT);
        XQConnection connection = source.getConnection();

        //Preparem i executem la consulta
        XQPreparedExpression expression = connection.prepareExpression(query);
        XQResultSequence result = expression.executeQuery();

        //Ordenem el resultat de la consulta
        String resultado="";
        String linea;

        while (result.next()){

            linea = result.getItemAsString(null);
            resultado = resultado+"\n"+linea;
        }

        connection.close();

        return resultado;
    }
}