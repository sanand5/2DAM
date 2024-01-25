// import java.io.IOException;
// import java.net.URI;
// import java.net.http.HttpClient;
// import java.net.http.HttpRequest;
// import java.net.http.HttpResponse;
// import java.net.http.HttpResponse.BodyHandler;

// public class App {
//     /**
//      * @param args
//      */
//     public static void main(String[] args) {
//         HttpClient client = HttpClient.newHttpClient();
//         HttpRequest request = HttpRequest.newBuilder()
//                 .uri(URI.create("http://10.0.219.21:8080/exist/rest/db/"))
//                 .GET()
//                 .build();
//         try {
//             HttpResponse<String> respuesta = client.send(request, HttpResponse.BodyHandlers.ofString());
//             System.out.println(respuesta.body());
//         } catch (IOException | InterruptedException e) {
//             // TODO Auto-generated catch block
//             e.printStackTrace();
//         }
//     }
// }
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class App {
    public static void main(String[] args) {
        String existDBApiUrl = "http://10.0.219.21:8080/exist/rest";
        String collectionName = "/db/carmon01/biblioteca";

        String username = "10813358";
        String password = username;

        try {
            String credentials = username + ":" + password;
            String encodedCredentials = java.util.Base64.getEncoder().encodeToString(credentials.getBytes());

            HttpClient client = HttpClient.newHttpClient();

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(existDBApiUrl + collectionName))
                    .header("Authorization", "Basic " + encodedCredentials)
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            System.out.println("Respuesta de eXistDB:");
            System.out.println(response.body());

        } catch (URISyntaxException | IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}