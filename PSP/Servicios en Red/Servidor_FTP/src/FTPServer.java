import java.io.*;
import java.net.*;

public class FTPServer {

    private ServerSocket serverSocket;

    public FTPServer(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        System.out.println("Servidor FTP iniciado en el puerto " + port);
    }

    public void start() {
        while (true) {
            try {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Cliente conectado: " + clientSocket.getInetAddress());

                // Manejar la sesión del cliente en un nuevo hilo
                new ClientHandler(clientSocket).start();
            } catch (IOException e) {
                System.out.println("Error al aceptar la conexión del cliente: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        int port = 2121; // Puedes cambiar el puerto si es necesario
        try {
            FTPServer server = new FTPServer(port);
            server.start();
        } catch (IOException e) {
            System.out.println("No se pudo iniciar el servidor: " + e.getMessage());
            e.printStackTrace();
        }
    }

    
}

