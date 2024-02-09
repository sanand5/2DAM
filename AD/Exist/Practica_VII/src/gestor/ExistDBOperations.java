package gestor;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ExistDBOperations {

    public static void createData(String collectionName, String xmlData, HttpClient client)
            throws IOException, InterruptedException, URISyntaxException {
        HttpRequest request = ExistDBConnector.buildRequest(collectionName, "POST",
                "<alumnos>" + xmlData + "</alumnos>");

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println("Respuesta de eXistDB (CREATE):");
        System.out.println(response.body());
    }

    public static void readData(String collectionName, HttpClient client)
            throws IOException, InterruptedException, URISyntaxException {
        HttpRequest request = ExistDBConnector.buildRequest(collectionName, "GET", "");

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println("Respuesta de eXistDB (READ):");
        System.out.println(response.body());
    }

    // Agrega métodos para otras operaciones CRUD (read, update, delete) aquí
}
