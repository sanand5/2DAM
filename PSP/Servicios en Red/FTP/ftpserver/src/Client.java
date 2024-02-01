import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.File;

public class Client implements Runnable {
    private Socket clientSocket;
    private BufferedReader reader;
    private PrintWriter writer;
    private boolean isAuthenticated = false;
    private String currentDirectory = System.getProperty("user.dir");

    public Client(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        try {
            reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            writer = new PrintWriter(clientSocket.getOutputStream(), true);

            writer.println("220 Servidor FTP listo.");

            String clientCommand;
            while ((clientCommand = reader.readLine()) != null) {
                System.out.println("Comando recibido: " + clientCommand);
                if (clientCommand.equalsIgnoreCase("QUIT")) {
                    break;
                }
                handleCommand(clientCommand);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void handleCommand(String command) {
        String[] commandParts = command.split("\\s+", 2);
        String cmd = commandParts[0].toUpperCase(); // Convertir a mayúsculas
        String arg = commandParts.length > 1 ? commandParts[1].trim() : "";

        switch (cmd) {
            case "USER":
                handleUserCommand(arg);
                break;
            case "PASS":
                handlePassCommand(arg);
                break;
            case "CWD":
                if (isAuthenticated) {
                    handleCwdCommand(arg);
                } else {
                    writer.println(
                            "530 Usuario no conectado. Inicie sesión con USER antes de ejecutar otros comandos.");
                }
                break;
            case "PWD":
                if (isAuthenticated) {
                    handlePwdCommand();
                } else {
                    writer.println(
                            "530 Usuario no conectado. Inicie sesión con USER antes de ejecutar otros comandos.");
                }

                break;
            case "LIST":
                if (isAuthenticated) {
                    handleListCommand(arg);
                } else {
                    writer.println(
                            "530 Usuario no conectado. Inicie sesión con USER antes de ejecutar otros comandos.");
                }

                break;
            case "HELP":
                if (isAuthenticated) {
                    handleHelpCommand();
                } else {
                    writer.println(
                            "530 Usuario no conectado. Inicie sesión con USER antes de ejecutar otros comandos.");
                }

                break;
            case "NOOP":
                if (isAuthenticated) {
                    handleNoopCommand();
                } else {
                    writer.println(
                            "530 Usuario no conectado. Inicie sesión con USER antes de ejecutar otros comandos.");
                }

                break;
            case "REIN":
                if (isAuthenticated) {
                    handleReinCommand();
                } else {
                    writer.println(
                            "530 Usuario no conectado. Inicie sesión con USER antes de ejecutar otros comandos.");
                }
                break;
            case "MKD":
                handleMkdCommand(arg);
                break;
            default:
                writer.println("500 Comando no encontrado");
                break;
        }
    }

    private void handleUserCommand(String username) {
        if (username.equals("u")) {
            writer.println("331 Usuario OK, por favor ingrese la contraseña.");
        } else {
            writer.println("530 Usuario no válido.");
        }
    }

    private void handlePassCommand(String password) {
        if (password.equals("p")) {
            isAuthenticated = true;
            writer.println("230 Autenticación exitosa.");
        } else {
            writer.println("530 Usuario o contraseña incorrecta.");
        }
    }

    private void handleCwdCommand(String args) {
        // Lógica para manejar el comando CWD
        // Puedes implementar tu lógica específica aquí
        // String newDirectory = getAbsolutePath(directory);

        // if (isValidDirectory(newDirectory)) {
        // currentDirectory = newDirectory;
        // writer.println("250 Directorio cambiado correctamente: " + currentDirectory);
        // } else {
        // writer.println("550 Directorio no válido: " + directory);
        // }
        String filename = currentDirectory;

        // go one level up (cd ..)
        if (args.equals("..")) {
            int ind = filename.lastIndexOf("\\");
            if (ind > 0) {
                filename = filename.substring(0, ind);
            }
        }

        // if argument is anything else (cd . does nothing)
        else if ((args != null) && (!args.equals("."))) {
            filename = filename + "\\" + args;
        }

        // check if file exists, is directory and is not above root directory
        File f = new File(filename);

        if (f.exists() && f.isDirectory() && (filename.length() >= currentDirectory.length())) {
            currentDirectory = filename;
            writer.println("250 The current directory has been changed to " + currentDirectory);
        } else {
            writer.println("550 Requested action not taken. File unavailable.");
        }
    }

    private String getAbsolutePath(String directory) {
        Path newPath = Paths.get(directory);

        // Verificar si la ruta es relativa
        if (!newPath.isAbsolute()) {
            // Convertir a ruta absoluta utilizando el directorio actual
            newPath = Paths.get(currentDirectory).resolve(newPath);
        }

        // Verificar si el directorio ya existe
        File dir = newPath.toFile();
        if (dir.exists()) {
            return null; // Directorio ya existe
        }

        return newPath.toString();
    }

    private boolean isValidDirectory(String directory) {
        if (directory.startsWith("//")) {
            return false;
        }
        Path path = Paths.get(directory);
        if (!Files.exists(path) || !Files.isDirectory(path)) {
            return false;
        }

        return true;
    }

    private void handleListCommand(String directory) {
        if (!isAuthenticated) {
            writer.println("530 Inicie sesión con USER antes de ejecutar otros comandos.");
            return;
        }

        String[] dirContent = nlstHelper(directory);

        if (dirContent == null) {
            writer.println("550 File does not exist.");
        } else {
            writer.println("125 Opening ASCII mode data connection for file list.");

            for (int i = 0; i < dirContent.length; i++) {
                writer.println(dirContent[i]);
            }

            writer.println("226 Transfer complete.");
            writer.println();
        }
    }

    private String[] nlstHelper(String args) {
        // Construct the name of the directory to list.
        String filename = currentDirectory;
        if (args != null) {
            filename = filename + "/" + args;
        }

        // Now get a File object, and see if the name we got exists and is a
        // directory.
        File f = new File(filename);

        if (f.exists() && f.isDirectory()) {
            return f.list();
        } else if (f.exists() && f.isFile()) {
            String[] allFiles = new String[1];
            allFiles[0] = f.getName();
            return allFiles;
        } else {
            return null;
        }
    }

    private void handleHelpCommand() {
        writer.println("214-The following commands are recognized:");
        writer.println(" USER    PASS    CWD     PWD     LIST    HELP    NOOP    REIN    QUIT");
        writer.println("214 Help OK.");
    }

    private void handleNoopCommand() {
        writer.println("200 Comando NOOP OK.");
    }

    private void handlePwdCommand() {
        writer.println("257 \"" + currentDirectory + "\" es el directorio actual.");
    }

    private void handleReinCommand() {
        isAuthenticated = false;
        // Puedes realizar otras tareas de reinicialización aquí si es necesario
        writer.println("220 Reinicialización exitosa. Listo para recibir nuevo nombre de usuario.");
    }

    private void handleMkdCommand(String directory) {
        if (isAuthenticated) {
            String newDirectory = getAbsolutePath(directory);

            if (isValidDirectory(newDirectory)) {
                File newDir = new File(newDirectory);
                if (newDir.mkdir()) {
                    writer.println("257 Directorio creado correctamente: " + newDirectory);
                } else {
                    writer.println("550 No se pudo crear el directorio: " + newDirectory);
                }
            } else {
                writer.println("550 Directorio no válido: " + directory);
            }
        } else {
            writer.println("530 Usuario no conectado. Inicie sesión con USER antes de ejecutar otros comandos.");
        }
    }
}
