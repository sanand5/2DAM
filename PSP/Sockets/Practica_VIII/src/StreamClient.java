import java.io.*;
import java.net.*;
public class StreamClient {
    public static void main(String[] args) {
        try {
            // Crear un socket de cliente y conectarse al servidor en el puerto 12345
            Socket socket = new Socket("localhost", 5432);

            // Configurar flujos de entrada/salida
            BufferedReader entradaServidor = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter salidaServidor = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader entradaUsuario = new BufferedReader(new InputStreamReader(System.in));

            while (true) {
                // Leer la entrada del usuario
                System.out.print("> Obtener capital de ");
                String pais = entradaUsuario.readLine();

                // Enviar la petición al servidor
                salidaServidor.println(pais);

                // Recibir la respuesta del servidor
                String respuesta = entradaServidor.readLine();
                System.out.println("> " + respuesta);

                // En caso de capital desconocida, permitir al usuario proporcionar la respuesta
                if (respuesta.startsWith("No se ha podido obtener la capital")) {
                    System.out.print("> ¿Introducir capital? (s/n): ");
                    String introducirCapital = entradaUsuario.readLine();

                    if (introducirCapital.equalsIgnoreCase("s")) {
                        salidaServidor.println(entradaUsuario.readLine());
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
