import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class Servidor {

    static HashMap<String, Boolean> activos = new HashMap<>();


    public static void main(String[] args) {
        final int PUERTO = 5432;

        try (ServerSocket serverSocket = new ServerSocket(PUERTO)) {
            System.out.println("Servidor esperando conexiones...");

            while (true) {
                Socket socketCliente = serverSocket.accept();
                System.out.println("Cliente accedió desde: " + socketCliente.getInetAddress());

                new Thread(() -> manejarConexion(socketCliente)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void manejarConexion(Socket socketCliente) {
        try (
                BufferedReader entrada = new BufferedReader(new InputStreamReader(socketCliente.getInputStream()));
                PrintWriter salida = new PrintWriter(socketCliente.getOutputStream(), true);
        ) {
            String usuario = entrada.readLine();
            String contrasena = entrada.readLine();

            if (usuarioEstaActivo(usuario)) {
                salida.println("El usuario se ha desconectado");
                activos.put(usuario, false);
            } else {
                if (verificarCredenciales(usuario, contrasena)) {
                    salida.println("El usuario se ha conectado correctamente");
                    activos.put(usuario, true);
                } else {
                    salida.println("Usuario y/o contraseña incorrecto");
                }
            }
            socketCliente.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static boolean usuarioEstaActivo(String usuario) {
        return activos.getOrDefault(usuario, false);
    }

    private static boolean verificarCredenciales(String usuario, String contrasena) {
        String rutaArchivo = "C:\\sistemalogin\\usuarios.txt";

        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                // Dividir la línea en campos usando #
                String[] campos = linea.split("#");
                if (campos.length == 3 && campos[1].equals(usuario) && campos[2].equals(contrasena)) {
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }
}
