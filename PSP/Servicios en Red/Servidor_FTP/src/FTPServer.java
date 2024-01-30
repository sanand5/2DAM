import org.apache.ftpserver.FtpServer; //The import org.apache cannot be resolved
import org.apache.ftpserver.FtpServerFactory;
import org.apache.ftpserver.listener.ListenerFactory;

public class FTPServer {

    public static void main(String[] args) {
        FtpServerFactory serverFactory = new FtpServerFactory();

        // Configuración del listener
        ListenerFactory listenerFactory = new ListenerFactory();
        listenerFactory.setPort(21); // Puerto FTP por defecto

        serverFactory.addListener("default", listenerFactory.createListener());

        // Inicia el servidor FTP
        FtpServer server = serverFactory.createServer();
        try {
            server.start();
            System.out.println("Servidor FTP iniciado en el puerto 21.");
        } catch (Exception e) {
            System.err.println("Error al iniciar el servidor FTP: " + e.getMessage());
        }
    }
}
