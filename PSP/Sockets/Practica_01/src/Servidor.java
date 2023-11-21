import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {

    public static void main(String[] args) {
        final int PUERTO = 1234;

        try (ServerSocket serverSocket = new ServerSocket(PUERTO)) {
            System.out.println("Servidor esperando conexiones en el puerto " + PUERTO);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Cliente conectado desde " + clientSocket.getInetAddress().getHostAddress());

                new Thread(() -> handleClient(clientSocket)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void handleClient(Socket clientSocket) {
        try (
                BufferedReader inputReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter outputWriter = new PrintWriter(clientSocket.getOutputStream(), true)
        ) {
            String userInput = inputReader.readLine();
            System.out.println("Usuario y contraseña recibidos: " + userInput);

            // Verificar usuario y contraseña
            if (checkCredentials(userInput)) {
                outputWriter.println("El usuario se ha conectado correctamente");
            } else {
                outputWriter.println("Usuario y/o contraseña incorrecto");
            }

            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static boolean checkCredentials(String userInput) {
        // Verificar las credenciales en el archivo usuarios.txt
        // Formato del archivo: ID#usuario#contraseña
        File file = new File("C:/sistemalogin/usuarios.txt");

        try (BufferedReader fileReader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = fileReader.readLine()) != null) {
                if (line.equals(userInput)) {
                    // Usuario y contraseña válidos
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }
}
