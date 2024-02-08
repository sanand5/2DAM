import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Base64;

public class ExistDBClient {

    private static final String EXIST_DB_URL = "http://localhost:8080/exist/rest";
    private static final String COLLECTION_NAME = "/db/10813358";
    private static final String USERNAME = "admin";
    private static final String PASSWORD = USERNAME;

    private static final HttpClient CLIENT = HttpClient.newHttpClient();

    public static void main(String[] args) {
        // Ejemplo de operación CRUD
        //readData();
        createData("<alumno><nombre>Carlos</nombre><edad>25</edad></alumno>");
        readData();
        //updateData("<alumno><nombre>juan</nombre><edad>25</edad></alumno>", "alumnos");
        // deleteData("documento-a-eliminar.xml");
        // readData();
    }

    public static void readData() {
        try {
            String credentials = USERNAME + ":" + PASSWORD;
            String encodedCredentials = Base64.getEncoder().encodeToString(credentials.getBytes());

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(EXIST_DB_URL + COLLECTION_NAME + "/alumnos"))
                    .header("Authorization", "Basic " + encodedCredentials)
                    .GET()
                    .build();

            HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());

            System.out.println("Respuesta de eXistDB (READ):");
            System.out.println(response.body());

        } catch (URISyntaxException | IOException | InterruptedException e) {
            System.out.println();
        }
    }

    public static void createData(String xmlData) {
        try {
            String credentials = USERNAME + ":" + PASSWORD;
            String encodedCredentials = Base64.getEncoder().encodeToString(credentials.getBytes());

            // Asegúrate de que el elemento raíz sea "alumnos"
            String correctedXml = "<alumnos>" + xmlData + "</alumnos>";

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(EXIST_DB_URL + COLLECTION_NAME + "/alumnos"))
                    .header("Authorization", "Basic " + encodedCredentials)
                    .POST(HttpRequest.BodyPublishers.ofString(correctedXml))
                    .build();

            HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());

            System.out.println("Respuesta de eXistDB (CREATE):");
            System.out.println(response.body());

        } catch (URISyntaxException | IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void updateData(String xmlData, String documentName) {
        try {
            String credentials = USERNAME + ":" + PASSWORD;
            String encodedCredentials = Base64.getEncoder().encodeToString(credentials.getBytes());

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(EXIST_DB_URL + COLLECTION_NAME + "/" + documentName))
                    .header("Authorization", "Basic " + encodedCredentials)
                    .PUT(HttpRequest.BodyPublishers.ofString(xmlData))
                    .build();

            HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());

            System.out.println("Respuesta de eXistDB (UPDATE):");
            System.out.println(response.body());

        } catch (URISyntaxException | IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void deleteData(String documentName) {
        try {
            String credentials = USERNAME + ":" + PASSWORD;
            String encodedCredentials = Base64.getEncoder().encodeToString(credentials.getBytes());

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(EXIST_DB_URL + COLLECTION_NAME + "/" + documentName))
                    .header("Authorization", "Basic " + encodedCredentials)
                    .DELETE()
                    .build();

            HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());

            System.out.println("Respuesta de eXistDB (DELETE):");
            System.out.println(response.body());

        } catch (URISyntaxException | IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
