import java.io.*;
import java.net.*;
public class StreamServer {
    public static void main(String[] args) {
        try {
            // Crear un socket de servidor en el puerto 12345
            ServerSocket serverSocket = new ServerSocket(12345);
            System.out.println("> Servidor a la espera de peticiones en puerto 12345");

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
            String capital = obtenerCapital(pais);

            if (capital.equals("Desconocida")) {
                // Si la capital es desconocida, solicitarla al cliente
                salidaCliente.println("No se ha podido obtener la capital de " + pais);
                System.out.print("> ¿Introducir capital? (s/n): ");
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
    private static String obtenerCapital(String pais) {
        // Simulación: Devolver la capital si se conoce, "Desconocida" si no
        switch (pais.toLowerCase()) {
            case "españa":
                return "Madrid";
            // Agrega más países y sus capitales aquí...
            default:
                return "Desconocida";
        }
    }

}
