import java.io.*;
import java.net.*;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

public class FTPServer {

    private ServerSocket serverSocket;

    public FTPServer(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        System.out.println("Servidor FTP iniciado en el puerto " + port);
    }

    public void start() {
        while (true) {
            try {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Cliente conectado: " + clientSocket.getInetAddress());

                // Manejar la sesión del cliente en un nuevo hilo
                new ClientHandler(clientSocket).start();
            } catch (IOException e) {
                System.out.println("Error al aceptar la conexión del cliente: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        int port = 2121; // Puedes cambiar el puerto si es necesario
        try {
            FTPServer server = new FTPServer(port);
            server.start();
        } catch (IOException e) {
            System.out.println("No se pudo iniciar el servidor: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static class ClientHandler extends Thread {

        private Socket clientSocket;
        private PrintWriter out;
        private BufferedReader in;
        private boolean isLoggedIn;
        private InputStream dataInputStream;
        private OutputStream dataOutputStream;
        private String currentDirectory;
        private Path mountedStructure;
        private ServerSocket dataServerSocket;
        private Socket dataSocket;
        private DataMode dataMode = DataMode.BINARY;
        private InetAddress dataAddress;
        private int dataPort;

        public ClientHandler(Socket socket) {
            this.clientSocket = socket;
            this.isLoggedIn = false;

        }

        public void run() {
            try {
                // Inicializar el BufferedReader y el PrintWriter
                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                out = new PrintWriter(clientSocket.getOutputStream(), true);

                // Saludo al cliente
                saludo();

                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    System.out.println("Comando recibido: " + inputLine);
                    if (inputLine.isEmpty()) {
                        continue; // Evitar procesar líneas vacías
                    }
                    String[] commands = inputLine.split(" ");
                    if (commands.length == 0) {
                        continue; // Evitar errores si no hay comandos
                    }
                    String command = commands[0].toUpperCase();
                    String argument = commands.length > 1 ? commands[1] : null;

                    switch (command) {
                        case "USER":
                            handleUser(argument);
                            break;
                        case "PASS":
                            handlePass(argument);
                            break;
                        case "ACCT":
                            handleAcct(argument);
                            break;
                        case "PORT":
                            handlePort(argument);
                            break;
                        case "CWD":
                            handleCwd(argument);
                            break;
                        case "CDUP":
                            handleCdup();
                            break;
                        case "SMNT":
                            handleSmnt(argument);
                            break;
                        case "QUIT":
                            handleQuit();
                            return;
                        case "SYST":
                            handleSyst();
                            break;
                        case "PWD":
                            handlePwd();
                            break;
                        case "TYPE":
                            handleType(argument);
                            break;
                        case "HELP":
                            handleHelp();
                            break;
                        case "NOOP":
                            handleNoop();
                            break;
                        case "LIST":
                            handleList();
                            break;
                        case "EPSV":
                            handleEpsv();
                            break;
                        case "EPRT":
                            handleEprt(argument);
                            break;
                        default:
                            out.println("502 Comando no implementado");
                            break;
                    }
                }
            } catch (IOException e) {
                System.out.println("Error al manejar la sesión del cliente: " + e.getMessage());
            } finally {
                despedida();
                closeResources();
            }
        }

        // private void openDataChannel() throws IOException {
        // // Elige un puerto para el canal de datos
        // int dataPort = 2020; // Ejemplo de puerto, puedes cambiarlo
        // dataServerSocket = new ServerSocket(dataPort);
        // dataServerSocket.setReuseAddress(true); // Permite la reutilización de
        // direcciones

        // // Enviar comando al cliente para que se conecte al puerto de datos
        // out.println("227 Entering Passive Mode (127,0,0,1," + (dataPort >> 8) + "," +
        // (dataPort & 0xff) + ")");
        // }

        private void openDataChannel() throws IOException {
            try {
                // Elige un puerto disponible para el canal de datos
                dataServerSocket = new ServerSocket(0);
                dataServerSocket.setReuseAddress(true); // Permite la reutilización de direcciones

                // Obtiene el puerto asignado
                int port = dataServerSocket.getLocalPort();

                // Envía el comando al cliente para que se conecte al puerto de datos
                out.println("227 Entering Passive Mode (127,0,0,1," + (port >> 8) + "," + (port & 0xff) + ")");

                // Añade mensajes de depuración
                System.out.println("Debug: Data channel opened on port " + port);
            } catch (IOException e) {
                e.printStackTrace();
                out.println("425 Can't open data connection.");
            }
        }

        private void acceptDataConnection() throws IOException {
            dataSocket = dataServerSocket.accept();
        }

        private void closeDataChannel() throws IOException {
            if (dataSocket != null) {
                dataSocket.close();
            }
            if (dataServerSocket != null) {
                dataServerSocket.close();
            }
        }

        private void handleListss() throws IOException {
            if (isLoggedIn) {
                openDataChannel();
                acceptDataConnection();

                try (PrintWriter dataOut = new PrintWriter(dataSocket.getOutputStream(), true)) {
                    File directory = new File(currentDirectory);

                    // Verificar si el directorio existe y es un directorio
                    if (directory.exists() && directory.isDirectory()) {
                        // Obtener los archivos y directorios dentro del directorio actual
                        File[] files = directory.listFiles();

                        if (files != null) {
                            for (File file : files) {
                                // Construye la información del archivo/directorio
                                // Aquí se puede mejorar para mostrar más detalles como en el comando 'ls -l' de
                                // Unix
                                String fileInfo = (file.isDirectory() ? "d" : "-") + " " + file.getName();
                                dataOut.println(fileInfo);
                            }
                        }
                        out.println("226 Transfer complete.");
                    } else {
                        out.println("550 Directory not found.");
                    }
                } catch (Exception e) {
                    out.println("550 Error during directory listing.");
                    e.printStackTrace();
                } finally {
                    closeDataChannel();
                }
            } else {
                out.println("530 You must log in with USER and PASS first.");
            }
        }

        private void saludo() {
            out.println("220 Servidor FTP FTPServer listo");
            System.out.println("Conexión establecida con el cliente: " + clientSocket.getInetAddress());
        }

        private void despedida() {
            out.println("221 Servicio de FTP cerrando la conexión de control.");
        }

        private void handleUser(String username) {
            if ("u".equals(username)) {
                isLoggedIn = true;
                out.println("331 Usuario válido, proporcionar contraseña.");
            } else {
                out.println("530 Usuario no válido.");
            }
        }

        private void handlePass(String password) {
            if (isLoggedIn) {
                // Verificar la contraseña (podrías tener una lógica más robusta aquí)
                if ("c".equals(password)) {
                    out.println("230 Autenticación exitosa.");
                } else {
                    out.println("530 Contraseña incorrecta.");
                }
            } else {
                out.println("503 Debes iniciar sesión con USER primero.");
            }
        }

        private void handleAcct(String account) {
            // Puedes implementar lógica adicional aquí según tus necesidades
            if (isLoggedIn) {
                System.out.println("Comando ACCT recibido. Usuario autenticado.");
                System.out.println("Cuenta identificada: " + account);
                out.println("230 Cuenta identificada: " + account);
            } else {
                System.out.println("Comando ACCT recibido, pero el usuario no está autenticado.");
                // Puedes enviar una respuesta diferente si el usuario no está autenticado.
                out.println("530 Debes iniciar sesión con USER y PASS primero.");
            }
        }

        private void handleQuit() {
            try {
                out.println("221 Servicio de FTP cerrando la conexión de control.");
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        private void handlePort(String portArgument) {
            if (portArgument != null) {
                String[] parts = portArgument.split(",");
                if (parts.length == 6) {
                    String host = parts[0] + "." + parts[1] + "." + parts[2] + "." + parts[3];
                    int port = Integer.parseInt(parts[4]) * 256 + Integer.parseInt(parts[5]);

                    try {
                        dataAddress = InetAddress.getByName(host);
                    } catch (UnknownHostException e) {
                        out.println("501 Host desconocido: " + host);
                        return;
                    }

                    dataPort = port;
                    out.println("200 Conexión de datos establecida");
                } else {
                    out.println("501 Sintaxis de argumento no válida para PORT");
                }
            } else {
                out.println("501 Falta el argumento para PORT");
            }
        }

        private void handleSyst() {
            out.println("215 " + getSystemInformation());
        }

        private String getSystemInformation() {
            // Obtener información del sistema
            Properties systemProperties = System.getProperties();
            String osName = systemProperties.getProperty("os.name");
            String osVersion = systemProperties.getProperty("os.version");
            String osArch = systemProperties.getProperty("os.arch");
            String javaVersion = systemProperties.getProperty("java.version");

            // Obtener información del usuario actual
            String userName = System.getProperty("user.name");

            // Crear una cadena que represente la información del sistema
            return osName + " " + osVersion + " (" + osArch + ") / Java " + javaVersion + " / User " + userName;
        }

        private void handlePwd() {
            // Obtén la ruta del directorio actual del sistema
            Path currentPath = Paths.get("").toAbsolutePath();

            // Convierte la ruta a una cadena de texto
            String currentDirectory = currentPath.toString();

            // Envía la respuesta al cliente con la ruta del directorio actual
            out.println("257 \"" + currentDirectory + "\" is the current directory");
        }

        private void handleCwd(String directory) {
            if (isLoggedIn) {
                try {
                    // Imprime la ruta actual antes de cambiarla
                    System.out.println("Ruta actual antes del cambio: " + System.getProperty("user.dir"));

                    // Intenta cambiar el directorio de trabajo utilizando el método
                    if (changeWorkingDirectory(directory)) {
                        // Envía una respuesta al cliente
                        out.println("250 Directory successfully changed.");

                        // Agrega un mensaje de registro para verificar si se está ejecutando
                        System.out
                                .println("Comando CWD recibido. Directorio cambiado. Nueva ruta: " + currentDirectory);
                    } else {
                        // Envía una respuesta al cliente indicando que el cambio de directorio falló
                        out.println("550 Failed to change directory. Directory does not exist.");
                    }

                    // Imprime la nueva ruta después de cambiarla
                    System.out.println("Ruta actual después del cambio: " + System.getProperty("user.dir"));
                } catch (Exception e) {
                    // Maneja cualquier error que pueda ocurrir al cambiar el directorio
                    out.println("550 Failed to change directory.");
                    e.printStackTrace();
                }
            } else {
                out.println("530 You must log in with USER and PASS first.");
            }
        }

        private boolean changeWorkingDirectory(String directory) {
            if (directory == null) {
                return false;
            }

            // Obtén la ruta completa del directorio
            Path newDirectory = Paths.get(directory);

            // Verifica si el directorio es válido
            if (Files.isDirectory(newDirectory)) {
                // Cambia el directorio de trabajo
                currentDirectory = directory;
                System.out.println("Comando CWD recibido. Directorio cambiado. Nueva ruta: " + currentDirectory);
                return true;
            } else {
                return false;
            }
        }

        private void handleCdup() {
            if (isLoggedIn) {
                try {
                    // Verifica si el directorio actual es el directorio raíz
                    if (currentDirectory != null && !currentDirectory.equals("/")) {
                        // Obtén el nombre del directorio padre
                        String parentDirectory = new File(currentDirectory).getParent();

                        // Cambia el directorio de trabajo al directorio padre
                        currentDirectory = parentDirectory;

                        // Envía una respuesta al cliente indicando el cambio de directorio
                        out.println(
                                "200 Directory successfully changed. Current directory is now: " + currentDirectory);

                        // Muestra en la consola del servidor el mensaje del comando y la nueva ruta
                        System.out
                                .println("Comando CDUP recibido. Directorio cambiado. Nueva ruta: " + currentDirectory);
                    } else {
                        // El usuario ya está en el directorio raíz o currentDirectory es null
                        out.println("550 Already at root directory or current directory is not set.");
                    }
                } catch (Exception e) {
                    // Maneja cualquier error que pueda ocurrir al cambiar el directorio
                    out.println("550 Failed to change directory.");
                    e.printStackTrace();
                }
            } else {
                out.println("530 You must log in with USER and PASS first.");
            }
        }

        private void handleSmnt(String path) {
            if (isLoggedIn) {
                // Construir el path absoluto utilizando el path actual y la ruta proporcionada
                // en SMNT
                Path absolutePath = FileSystems.getDefault().getPath(path).toAbsolutePath();

                // Verificar si el directorio existe en el sistema de archivos
                if (absolutePath.toFile().exists() && absolutePath.toFile().isDirectory()) {
                    mountedStructure = absolutePath;
                    System.out.println("Comando SMNT recibido. Estructura montada: " + mountedStructure);

                    // Respondemos al cliente indicando que la estructura se ha montado
                    out.println("250 Structure successfully mounted: " + mountedStructure);
                } else {
                    System.out.println("Comando SMNT recibido. No se pudo montar la estructura: " + path);

                    // Respondemos al cliente indicando que no se pudo montar la estructura
                    out.println("550 Failed to mount structure: " + path);
                }
            } else {
                out.println("530 You must log in with USER and PASS first.");
            }
        }

        private void handleType(String typeArgument) {
            if (isLoggedIn) {
                switch (typeArgument.toUpperCase()) {
                    case "A":
                        out.println("200 Type set to A");
                        dataMode = DataMode.ASCII;
                        break;
                    case "I":
                        out.println("200 Type set to I");
                        dataMode = DataMode.BINARY;
                        break;
                    default:
                        out.println("504 Command not implemented for that parameter");
                        break;
                }
            } else {
                out.println("530 You must log in with USER and PASS first.");
            }
        }

        private enum DataMode {
            ASCII, BINARY
        }

        // ...

        // private void handleList() throws IOException {
        // if (isLoggedIn) {
        // openDataChannel();
        // out.println("150 Opening " + (dataMode == DataMode.ASCII ? "ASCII" :
        // "binary") + " mode data connection for file list");

        // try {
        // // Resto del código para enviar la lista de archivos, según el modo ASCII o
        // binario
        // // ...
        // } catch (Exception e) {
        // out.println("500 Internal Server Error.");
        // e.printStackTrace();
        // } finally {
        // closeDataChannel();
        // }
        // } else {
        // out.println("530 You must log in with USER and PASS first.");
        // }
        // }

        private void handleHelp() {
            out.println("214-The following commands are recognized:");
            out.println(" USER PASS ACCT CWD CDUP SMNT QUIT REIN PORT PASV TYPE STRU MODE RETR STOR STOU");
            out.println(" APPE ALLO REST RNFR RNTO ABOR DELE RMD MKD PWD LIST NLST SITE SYST STAT HELP NOOP");
            out.println("214 Help OK.");
        }

        private void handleNoop() {
            out.println("200 Command OK.");
        }

        private void handleEpsv() {
            if (isLoggedIn) {
                try {
                    openDataChannel();
                    out.println("229 Entering Extended Passive Mode (|||" + dataServerSocket.getLocalPort() + "|)");
                } catch (IOException e) {
                    out.println("500 EPSV command failed.");
                    e.printStackTrace();
                }
            } else {
                out.println("530 You must log in with USER and PASS first.");
            }
        }

        private void handleEprt(String argument) {
            if (isLoggedIn) {
                try {
                    String[] parts = argument.split("\\|");
                    // No necesitas proporcionar un puerto específico aquí

                    // Puedes usar parts[2] para obtener la dirección IP si es necesario
                    openDataChannel(); // Llama al método modificado

                    out.println("200 EPRT command successful.");
                } catch (Exception e) {
                    out.println("500 EPRT command failed.");
                    e.printStackTrace();
                }
            } else {
                out.println("530 You must log in with USER and PASS first.");
            }
        }

        private void handleList() throws IOException {
            if (isLoggedIn) {
                openDataChannel();
                out.println("150 Opening ASCII mode data connection for file list");

                try {
                    // Añade esta línea para imprimir el estado de dataSocket
                    System.out.println("Debug: dataSocket is " + (dataSocket == null ? "null" : "not null"));

                    // Añade esta línea para imprimir el estado de dataServerSocket
                    System.out
                            .println("Debug: dataServerSocket is " + (dataServerSocket == null ? "null" : "not null"));

                    if (dataSocket != null) {
                        try (PrintWriter dataOut = new PrintWriter(dataSocket.getOutputStream(), true)) {
                            File directory = new File(currentDirectory);

                            if (directory.exists() && directory.isDirectory()) {
                                File[] files = directory.listFiles();

                                if (files != null) {
                                    for (File file : files) {
                                        String fileInfo = (file.isDirectory() ? "d" : "-") + " " + file.getName();
                                        dataOut.println(fileInfo);
                                    }
                                }
                                out.println("226 Transfer complete.");
                            } else {
                                out.println("550 Directory not found.");
                            }
                        } catch (Exception e) {
                            out.println("550 Error during directory listing.");
                            e.printStackTrace();
                        } finally {
                            closeDataChannel();
                        }
                    } else {
                        out.println("425 Data connection not established.");
                    }
                } catch (Exception e) {
                    out.println("500 Internal Server Error.");
                    e.printStackTrace();
                }
            } else {
                out.println("530 You must log in with USER and PASS first.");
            }
        }

        private void closeResources() {
            try {
                if (clientSocket != null) {
                    clientSocket.close();
                }
                if (in != null) {
                    in.close();
                }
                if (out != null) {
                    out.close();
                }
                closeDataConnection();
                System.out.println("Recursos del cliente cerrados.");
            } catch (IOException ex) {
                System.out.println("Error cerrando recursos: " + ex.getMessage());
            }
        }

        private void closeDataConnection() {
            try {
                if (dataInputStream != null) {
                    dataInputStream.close();
                }
                if (dataOutputStream != null) {
                    dataOutputStream.close();
                }
                System.out.println("Conexión de datos cerrada.");
            } catch (IOException ex) {
                System.out.println("Error cerrando la conexión de datos: " + ex.getMessage());
            }
        }
    }
}