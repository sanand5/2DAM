import utilidades.Gestor;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.HashMap;

public class DatagramServer {
    static HashMap<String, String> mapaPaisesCapitales = new HashMap<>();
    static final int SERVIDOR_PORT = 5555;

    static Gestor gs = new Gestor("./log.txt");

    public static void main(String[] args) {


        try {
            DatagramSocket socket = new DatagramSocket(SERVIDOR_PORT);
            System.out.println("> Servidor a la espera de peticiones en puerto 5555");
            while (true) {
                byte[] buffer = new byte[1024];
                DatagramPacket paqueteRecibido = new DatagramPacket(buffer, buffer.length);
                socket.receive(paqueteRecibido);
                String pais = new String(paqueteRecibido.getData(), 0, paqueteRecibido.getLength());

                System.out.println("> Petición recibida: " + pais);
                mapaPaisesCapitales = gs.pull();
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
