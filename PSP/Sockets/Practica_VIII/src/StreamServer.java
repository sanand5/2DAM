import java.io.*;
import java.net.*;
import java.util.HashMap;
import java.util.Map;

public class StreamServer {
    static Map<String, String> mapaPaisesCapitales = new HashMap<>();
    static final int SERVIDOR_PORT = 5555;

    public static void main(String[] args) {
        mapaPaisesCapitales.put("Estados Unidos", "Washington, D.C.");
        mapaPaisesCapitales.put("Canadá", "Ottawa");
        mapaPaisesCapitales.put("Reino Unido", "Londres");
        mapaPaisesCapitales.put("Francia", "París");
        mapaPaisesCapitales.put("Alemania", "Berlín");
        mapaPaisesCapitales.put("España", "Madrid");

        try {
            ServerSocket serverSocket = new ServerSocket(SERVIDOR_PORT);
            System.out.println("> Servidor a la espera de peticiones en puerto 5555");

            while (true) {
                Socket socketCliente = serverSocket.accept();
                System.out.println("> Cliente conectado desde: " + socketCliente.getInetAddress().getHostAddress());

                BufferedReader entrada = new BufferedReader(new InputStreamReader(socketCliente.getInputStream()));
                PrintWriter salida = new PrintWriter(socketCliente.getOutputStream(), true);

                String pais = entrada.readLine();
                System.out.println("> Petición recibida: " + pais);

                String capital = mapaPaisesCapitales.getOrDefault(pais, "Desconocida");
                salida.println(capital);
                System.out.println("> Respuesta petición " + pais + " → " + capital);

                // Verificar si el servidor desconoce la respuesta
                if (capital.equals("Desconocida")) {
                    String respuestaCliente = entrada.readLine();
                    System.out.println("> Respuesta recibida: " + respuestaCliente);
                    // Almacenar la respuesta proporcionada por el cliente
                    mapaPaisesCapitales.put(pais, respuestaCliente);
                    System.out.println("> Capital de " + pais + " → " + respuestaCliente);
                }

                entrada.close();
                salida.close();
                socketCliente.close();
            }
        } catch (IOException e) {
            System.err.println("Error de entrada/salida: " + e.getMessage());
        }
    }

}
