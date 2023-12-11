import lector.ReadClient;

import java.net.*;
import java.io.*;

public class DatagramClient {
    static final int SERVIDOR_PORT = 5555;
    static final String IP = "localhost";

    public static void main(String[] args) {
        ReadClient rc = new ReadClient();
        try {
            DatagramSocket socket = new DatagramSocket();
            while (true) {
                String pais = rc.pedirString("> Obtener capital de ", false);
                byte[] solicitudBytes = pais.getBytes();

                InetAddress servidorAddress = InetAddress.getByName(IP);

                DatagramPacket solicitudPaquete = new DatagramPacket(solicitudBytes, solicitudBytes.length, servidorAddress, SERVIDOR_PORT);
                socket.send(solicitudPaquete);
                byte[] buffer = new byte[1024];
                DatagramPacket respuestaPaquete = new DatagramPacket(buffer, buffer.length);
                socket.receive(respuestaPaquete);
                String capital = new String(respuestaPaquete.getData(), 0, respuestaPaquete.getLength());
                System.out.println("> Capital de " + pais + ": " + capital);
            }
        } catch (SocketException e) {
            System.err.println("Error al abrir o utilizar el socket: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Error de entrada/salida: " + e.getMessage());
        }
    }
}
