import utilidades.Gestor;

import java.io.*;
import java.net.*;
import java.util.HashMap;
import java.util.Map;

public class StreamServer {
    static HashMap<String, String> mapaPaisesCapitales = new HashMap<>();
    static final int SERVIDOR_PORT = 5555;
    static Gestor gs = new Gestor("./res/logs.txt");

    public static void main(String[] args) {
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

                mapaPaisesCapitales = gs.pull();
                String capital = mapaPaisesCapitales.getOrDefault(pais, "Desconocida");
                salida.println(capital);
                System.out.println("> Respuesta petición " + pais + " → " + capital);

                if (capital.equals("Desconocida")) {
                    String respuestaCliente = entrada.readLine();
                    System.out.println("> Respuesta recibida: " + respuestaCliente);
                    if (respuestaCliente != null) {
                        mapaPaisesCapitales.put(pais, respuestaCliente);
                        gs.push(mapaPaisesCapitales);
                        System.out.println("> Capital de " + pais + " → " + respuestaCliente);
                    }
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
