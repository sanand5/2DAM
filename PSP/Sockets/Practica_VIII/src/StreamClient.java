import java.io.*;
import java.net.*;
import lector.ReadClient;
public class StreamClient {
    static final int SERVIDOR_PORT = 5555;
    static final String IP = "localhost";

    public static void main(String[] args) {
        ReadClient rc = new ReadClient();
        try {
            while (true) {
                Socket socket = new Socket(IP, SERVIDOR_PORT);

                PrintWriter salida = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                String pais = rc.pedirString("> Obtener capital de ", false);
                salida.println(pais);

                String capital = entrada.readLine();
                System.out.println("> Capital de " + pais + ": " + capital);

                // Verificar si el servidor desconoce la respuesta
                if (capital.equals("Desconocida")) {
                    System.out.print("> No se ha podido obtener la capital de " + pais + "\n> ¿Introducir capital? (s/n): ");
                    String respuestaUsuario = rc.pedirString("", true);
                    if (respuestaUsuario.equalsIgnoreCase("s")) {
                        salida.println(rc.pedirString("> Nueva capital para " + pais + ": ", false));
                    }
                }

                salida.close();
                entrada.close();
                socket.close();
            }
        } catch (IOException e) {
            System.err.println("Error de entrada/salida: " + e.getMessage());
        }
    }
}
/*
 * Mirar si esta gestionat el error de si el servidor esta apagat
 * Comprovar Codic
 */