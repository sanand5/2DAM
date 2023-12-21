import utilidades.Colors;
import utilidades.ReadClient;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class DatagramClient {
    static final int SERVIDOR_PORT = 5555;
    static final String IP = "localhost";

    public static void main(String[] args) {
        boolean loop = true;
        while (loop) {
            ReadClient rc = new ReadClient();
            Colors cl = new Colors();
            System.out.println(cl.GREEN_ANSI + "/c para salir" + cl.RESET_ANSI);
            String pais = rc.pedirStringLow("> Obtener capital de ", false);
            if (!pais.equals("/c")) {
                try {
                    DatagramSocket socket = new DatagramSocket();
                    byte[] solicitudBytes = pais.getBytes();
                    InetAddress servidorAddress = InetAddress.getByName(IP);

                    DatagramPacket solicitudPaquete = new DatagramPacket(solicitudBytes, solicitudBytes.length, servidorAddress, SERVIDOR_PORT);
                    socket.send(solicitudPaquete);
                    byte[] buffer = new byte[1024];
                    DatagramPacket respuestaPaquete = new DatagramPacket(buffer, buffer.length);
                    socket.receive(respuestaPaquete);
                    String capital = new String(respuestaPaquete.getData(), 0, respuestaPaquete.getLength());
                    System.out.println("> Capital de " + pais + ": " + capital);
                } catch (SocketException e) {
                    System.err.println("Error al abrir o utilizar el socket: " + e.getMessage());
                } catch (IOException e) {
                    System.err.println("Error de entrada/salida: " + e.getMessage());
                }
            } else {
                loop = false;
            }
        }

        System.out.println("Adios!");
    }
}
