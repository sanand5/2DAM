import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Cliente {

    public static void main(String[] args) {
        final String SERVIDOR_IP = "localhost";
        final int PUERTO = 5432;

        try (
                Socket socket = new Socket(SERVIDOR_IP, PUERTO);
                BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter salida = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in))
        ) {
            System.out.print("Ingrese usuario: ");
            String usuario = teclado.readLine();
            System.out.print("Ingrese contraseña: ");
            String contrasena = teclado.readLine();

            salida.println(usuario);
            salida.println(contrasena);

            String respuestaServidor = entrada.readLine();
            System.out.println("Respuesta del servidor: " + respuestaServidor);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
