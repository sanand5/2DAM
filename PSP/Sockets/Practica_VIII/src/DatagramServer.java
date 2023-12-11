import java.net.*;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class DatagramServer {
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
            DatagramSocket socket = new DatagramSocket(SERVIDOR_PORT);
            System.out.println("> Servidor a la espera de peticiones en puerto 5555");
            while (true) {
                byte[] buffer = new byte[1024];
                DatagramPacket paqueteRecibido = new DatagramPacket(buffer, buffer.length);
                socket.receive(paqueteRecibido);
                String pais = new String(paqueteRecibido.getData(), 0, paqueteRecibido.getLength());

                System.out.println("> Petición recibida: " + pais);
                String capital = mapaPaisesCapitales.getOrDefault(pais, "Desconocida");

                byte[] respuestaBytes = capital.getBytes();
                InetAddress clienteAddress = paqueteRecibido.getAddress();
                int clientePort = paqueteRecibido.getPort();
                DatagramPacket respuestaPaquete = new DatagramPacket(respuestaBytes, respuestaBytes.length, clienteAddress, clientePort);
                socket.send(respuestaPaquete);
                System.out.println("> Respuesta petición " + pais + " → " + capital);
            }
        } catch (SocketException e) {
            System.err.println("Error al abrir o utilizar el socket: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Error de entrada/salida: " + e.getMessage());
        }
    }
}
