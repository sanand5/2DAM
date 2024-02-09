package gestor;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.util.Base64;

public class ExistDBConnector {
    private static final String EXIST_DB_URL = "http://localhost:8080/exist/rest";
    private static final String USERNAME = "admin";
    private static final String PASSWORD = USERNAME;

    private static final HttpClient CLIENT = HttpClient.newHttpClient();

    public static HttpClient getClient() {
        return CLIENT;
    }

    public static HttpRequest buildRequest(String collectionName, String method, String xmlData)
            throws URISyntaxException {
        String credentials = USERNAME + ":" + PASSWORD;
        String encodedCredentials = Base64.getEncoder().encodeToString(credentials.getBytes());

        return HttpRequest.newBuilder()
                .uri(new URI(EXIST_DB_URL + collectionName))
                .header("Authorization", "Basic " + encodedCredentials)
                .method(method, HttpRequest.BodyPublishers.ofString(xmlData))
                .build();
    }
}
