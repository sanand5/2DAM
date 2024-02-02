import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        int port = 25;

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Servidor FTP iniciado en el puerto " + port);

            while (true) {
                try {
                    Socket clientSocket = serverSocket.accept();
                    System.out.println("Cliente conectado desde " + clientSocket.getInetAddress());
                    Thread clientThread = new Thread(new Client(clientSocket));
                    clientThread.start();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
