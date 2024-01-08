import utilidades.Colors;
import utilidades.ReadClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class StreamClient {
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
                    Socket socket = new Socket(IP, SERVIDOR_PORT);
                    PrintWriter salida = new PrintWriter(socket.getOutputStream(), true);
                    BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    salida.println(pais);
                    String capital = entrada.readLine();
                    System.out.println("> Capital de " + pais + ": " + capital);

                    if (capital.equals("Desconocida")) {
                        String respuestaUsuario = rc.pedirStringLow("> No se ha podido obtener la capital de \" + pais + \"\\n> ¿Introducir capital? (s/n): ", true);
                        if (respuestaUsuario.equalsIgnoreCase("s")) {
                            salida.println(rc.pedirStringLow("> Nueva capital para " + pais + ": ", false));
                        }
                    }
                    salida.close();
                    entrada.close();
                    socket.close();
                } catch (IOException e) {
                    cl.errMsg("Error de entrada/salida: " + e.getMessage());
                }
            } else {
                loop = false;
            }
        }
        System.out.println("Adios!");
    }
}