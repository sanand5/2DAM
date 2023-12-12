import java.io.*;
import java.net.*;
import java.util.HashMap;
import java.util.Map;

public class StreamServer {
    static Map<String, String> mapaPaisesCapitales = new HashMap<>();
    static final int SERVIDOR_PORT = 5432;
    public static void main(String[] args) {
        mapaPaisesCapitales.put("Estados Unidos", "Washington, D.C.");
        mapaPaisesCapitales.put("Canadá", "Ottawa");
        mapaPaisesCapitales.put("Reino Unido", "Londres");
        mapaPaisesCapitales.put("Francia", "París");
        mapaPaisesCapitales.put("Alemania", "Berlín");
        mapaPaisesCapitales.put("España", "Madrid");

        try {
            // Crear un socket de servidor en el puerto 12345
            ServerSocket serverSocket = new ServerSocket(SERVIDOR_PORT);
            System.out.println("Servidor esperando conexiones en el puerto " + SERVIDOR_PORT);

            // Aceptar conexiones entrantes
            Socket clienteSocket = serverSocket.accept();
            System.out.println("> Cliente conectado desde: " + clienteSocket.getInetAddress().getHostAddress());

            // Configurar flujos de entrada/salida
            BufferedReader entradaCliente = new BufferedReader(new InputStreamReader(clienteSocket.getInputStream()));
            PrintWriter salidaCliente = new PrintWriter(clienteSocket.getOutputStream(), true);

            // Configurar flujos de entrada/salida para la entrada del servidor
            BufferedReader entradaServidor = new BufferedReader(new InputStreamReader(System.in));

            // Leer la petición del cliente
            String pais = entradaCliente.readLine();
            System.out.println("> Petición recibida: " + pais);

            // (Simulación) Obtener la capital correspondiente al país
            String capital = mapaPaisesCapitales.getOrDefault(pais, "Desconocida");
            gestionarCapital(pais, capital, entradaServidor, entradaCliente, salidaCliente);

            // Cerrar conexiones
            entradaCliente.close();
            salidaCliente.close();
            entradaServidor.close();
            clienteSocket.close();
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static void gestionarCapital(String pais, String capital, BufferedReader entradaServidor,
                                         BufferedReader entradaCliente, PrintWriter salidaCliente) throws IOException {
        if (capital.equals("Desconocida")) {
            // Si la capital es desconocida, solicitarla al cliente
            salidaCliente.println("No se ha podido obtener la capital de " + pais);
            String respuesta = entradaServidor.readLine();

            if (respuesta.equalsIgnoreCase("s")) {
                salidaCliente.println("Introduce la capital de " + pais + ": ");
                capital = entradaCliente.readLine();
                System.out.println("> Respuesta recibida: " + capital);
            }
        } else {
            // Si se conoce la capital, enviarla al cliente
            salidaCliente.println("Respuesta petición " + pais + " → " + capital);
        }

        // Mostrar la capital en el servidor
        System.out.println("> Capital de " + pais + " → " + capital);
    }

}
