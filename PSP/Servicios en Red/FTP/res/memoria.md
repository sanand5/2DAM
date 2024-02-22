# Memoria Practica Servidor FTP / Servicios en Red
## Que 
<div style="text-align: justify;">
Servidor ftp

## Para que
Este ejercicio ha resultado útil para aprender y practicar el funcionamiento de los Sockets y comprender como funciona el protocolo TCP. La actividad sirve para repasar la programación con hilos y concurrenceia, además de tener una idea básica de la programación de un servidor TCP con Java o C, dependiendo del lenguaje que queramos usar. Yo he decidido utilizar Java, ya que estoy más familiarizado con él.

<div style="page-break-before:always"></div>

## Como
#### Clase Server
Esta clase solo contiene el metodo principal que empieza definiendo el puerto que se va a utlizar asignandolo a una variable y crea un `ServerSocket` en el puerto especificado. Este es responsable de aceptar conexiones entrantes de clientes. 
El servidor entra en un bucle para esperar y aceptar conexiones continuamente. Cuando un cliente se conecta, el método `accept()` del `ServerSocket` devuelve un objeto Socket que representa la conexión con ese cliente. Despues se crea un nuevo hilo (`Thread`) para manejar la conexión con el cliente. El hilo utiliza la clase Client que implementa la lógica específica para manejar las operaciones del cliente. El hilo recién creado se inicia con el método `start()`. Esto permite que el servidor continúe aceptando conexiones mientras maneja las operaciones de clientes en hilos separados.

#### Clase Client

    Este código Java implementa la lógica del lado del cliente para un servidor FTP básico. A continuación, se proporciona una explicación detallada de cada parte del código:

    1. **Declaración de variables de instancia:**
    ```java
    private Socket clientSocket;
    private BufferedReader reader;
    private PrintWriter writer;
    private boolean isAuthenticated = false;
    private String currentDirectory = System.getProperty("user.dir");
    ```

    - `clientSocket`: Representa el socket de conexión con el cliente.
    - `reader`: Permite la lectura de datos del cliente.
    - `writer`: Permite la escritura de datos al cliente.
    - `isAuthenticated`: Indica si el usuario está autenticado.
    - `currentDirectory`: Almacena el directorio actual del cliente, inicializado con el directorio de trabajo del usuario del sistema.

    2. **Constructor `Client(Socket clientSocket)`:**
    ```java
    public Client(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }
    ```

    - Inicializa la instancia de `Client` con el socket de conexión proporcionado.

    3. **Método `run()`:**
    ```java
    @Override
    public void run() {
        try {
            // Configuración de flujos de entrada y salida
            reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            writer = new PrintWriter(clientSocket.getOutputStream(), true);

            // Mensaje de bienvenida al cliente
            writer.println("220 Servidor FTP listo.");

            // Bucle para manejar comandos del cliente
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
            // Cierre del socket del cliente al finalizar
            try {
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    ```

    - Configura flujos de entrada y salida para la comunicación con el cliente.
    - Envía un mensaje de bienvenida al cliente.
    - Entra en un bucle para leer comandos del cliente.
    - Llama al método `handleCommand()` para procesar cada comando del cliente.
    - Finalmente, cierra el socket del cliente.

    4. **Método `handleCommand(String command)`:**
    ```java
    private void handleCommand(String command) {
        // Lógica para manejar distintos comandos FTP
    }
    ```

    - Este método se encarga de dirigir la ejecución a funciones específicas según el comando recibido del cliente.

    5. **Métodos específicos de comandos (ej. `handleUserCommand`, `handlePassCommand`, etc.):**
    - Cada uno de estos métodos maneja un tipo específico de comando y realiza acciones correspondientes, como autenticación, cambio de directorio, listar archivos, etc.

    6. **Método `handleMkdCommand(String directory)`:**
    ```java
    private void handleMkdCommand(String directory) {
        // Lógica para manejar el comando MKD (Make Directory)
    }
    ```

    - Este método se encarga de manejar el comando MKD para crear un nuevo directorio.
    - Verifica la autenticación del usuario y la validez del directorio.
    - Responde al cliente con mensajes adecuados según el resultado de la operación.

    7. **Otros métodos auxiliares (`getAbsolutePath`, `isValidDirectory`, `nlstHelper`, etc.):**
    - Proporcionan funcionalidades auxiliares para operaciones como obtener la ruta absoluta de un directorio, verificar la validez de un directorio, ayudar en la obtención de la lista de archivos, etc.

    8. **Manejo de la excepción `IOException`:**
    - La estructura `try-catch` se utiliza para manejar excepciones relacionadas con operaciones de entrada/salida.

    En resumen, este código implementa un cliente FTP básico que maneja diversos comandos proporcionados por el usuario. Cada comando tiene su propia lógica de manejo, y el código se organiza de manera estructurada para facilitar la extensión y mantenimiento. Además, se incluye manejo de excepciones para garantizar un comportamiento robusto.

## Conclusión
En conclusión, considero que esta actividad ha sido útil y ha contribuido significativamente a mejorar mis habilidades de programación con `Sockets` Y `Threads`. 

Además, me intriga conocer las diferentes aproximaciones que mis compañeros han tomado para abordar esta actividad, ya que soy consciente de que hay varias formas de implementarla. Consultar a mis amigos y obtener explicaciones sobre sus enfoques podría proporcionarme valiosas perspectivas y aprender nuevas técnicas. También ver cómo se hace esta actividad en `C` para poder comprender un poco más cómo funciona `C`. 

En general, me ha parecido interesante descubrir como desarrollar una idea muy basica de los tipos de servidores `TCP`. Considero que la dificultad de esta práctica no ha sido elevada excepcionando algunos comandos que no he sabido como impelemtarlos.

</div>
