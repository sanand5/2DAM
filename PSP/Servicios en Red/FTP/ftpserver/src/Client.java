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
        String cmd = commandParts[0].toUpperCase();
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
                writer.print(" ");
                writer.flush();
                if (isAuthenticated) {
                    handleListCommand();
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
        String filename = currentDirectory;
        if (args.equals("..")) {
            int ind = filename.lastIndexOf("\\");
            if (ind > 0) {
                filename = filename.substring(0, ind);
            }
        }
        else if ((args != null) && (!args.equals("."))) {
            filename = filename + "\\" + args;
        }
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


        if (!newPath.isAbsolute()) {

            newPath = Paths.get(currentDirectory).resolve(newPath);
        }


        File dir = newPath.toFile();
        if (dir.exists()) {
            return null;
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

    private void handleListCommand() {
        if (!isAuthenticated) {
            writer.println("530 Inicie sesión con USER antes de ejecutar otros comandos.");
            return;
        }
        File carpeta = new File(currentDirectory);
        if (carpeta.exists() && carpeta.isDirectory()) {
            String[] dirContent = carpeta.list();
            writer.println("125 Opening ASCII mode data connection for file list.");
            for (int i = 0; i < dirContent.length; i++) {
                writer.println("\t" + dirContent[i]);
            }
            writer.println("226 Transfer complete.");
        } else {
            writer.println("550 Directory does not exist.");
        }
    }

    private void handleHelpCommand() {
        writer.println("214-The following commands are recognized:");
        writer.println(" USER    PASS    CWD     PWD     LIST    HELP    NOOP    REIN    QUIT  examen PSP");
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
        
        writer.println("220 Reinicialización exitosa. Listo para recibir nuevo nombre de usuario.");
    }

    private void handleMkdCommand(String directory) {
        if (isAuthenticated) {
            try {
                File newDirectory = new File(currentDirectory, directory);

                if (!newDirectory.exists() && newDirectory.mkdir()) {
                    writer.println("257 Directorio creado correctamente: " + newDirectory);

                } else {
                    writer.println("550 No se pudo crear el directorio: " + newDirectory);
                }
            } catch (Exception e) {
                writer.println("550 Directorio no válido: " + directory);
                e.printStackTrace();
            }
        } else {
            writer.println("530 Usuario no conectado. Inicie sesión con USER antes de ejecutar otros comandos.");
        }
    }
}
