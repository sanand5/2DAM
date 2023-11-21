import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Cliente {

    public static void main(String[] args) {
        final String HOST = "localhost";
        final int PUERTO = 1234;

        try (Socket socket = new Socket(HOST, PUERTO);
             BufferedReader inputReader = new BufferedReader(new InputStreamReader(System.in));
             PrintWriter outputWriter = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader serverReader = new BufferedReader(new InputStreamReader(socket.getInputStream()))
        ) {
            System.out.print("Ingrese usuario: ");
            String usuario = inputReader.readLine();

            System.out.print("Ingrese contraseña: ");
            String contraseña = inputReader.readLine();

            String userInput = usuario + "#" + contraseña;
            outputWriter.println(userInput);

            // Esperar respuesta del servidor
            String respuesta = serverReader.readLine();
            System.out.println("Respuesta del servidor: " + respuesta);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
