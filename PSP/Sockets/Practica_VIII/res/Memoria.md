<div style="text-align: justify;">

# Memoria Practica VIII Sockets(II)

## Que 

Mediante el uso de Datagrams Sockets, realiza una pareja de programas en el cual el cliente indicará un país al servidor, y éste le devolverá la capital si la conoce y “desconocido” si no la conoce.
La salida por pantalla deberá ser:

#### En el cliente:
```
> Obtener capital de España  
> Capital de España: Madrid  
…  
> Obtener capital de Ruanda  
> No se ha podido obtener la capital de Ruanda  
```
#### En el servidor:
```
> DatagramServer a la espera de peticiones en puerto 5555  
> Petición recibida: España  
> Respuesta petición España → Madrid  
…
> Petición recibida: Ruanda  
> Respuesta petición Ruanda → Desconocida  
```
Modifica el programa anterior, para que se utilicen Sockets Streams en lugar de Datagrams Sockets.
Además, en caso de que el servidor desconozca la respuesta, el cliente podrá darle la respuesta al servidor y éste la almacenará:

## Para que

Este ejercicio ha resultado útil para aprender y practicar el funcionamiento de los Sockets tanto para servidores UDP como TCP. La
actividad sirve para tener una idea básica de la programación en TCP y UDP con Sockets con Java o C,
dependiendo del lenguaje que queramos usar. Yo he decidido utilizar Java, ya que estoy más
familiarizado con él.


<div style="page-break-before:always"></div>

## Pseudocodigo
### Gestor
```
Inicio

Clase Gestor {
    Cadena path
    Archivo fl
    EscritorDeArchivo fw
    Escaner scf
    Colores cl = Nuevo Colors()

    Función Gestor(nuevaPath):
        path = nuevaPath
        Intentar
            Verificar si el archivo existe, y si no, crearlo
        Capturar Excepción (NuloPuntero | IOException e):
            Manejar errores, si los hay
        FinIntentar
    FinFunción

    Función pull():
        Mapa<String, String> mapa = Nuevo Mapa()
        Intentar
            Crear un escáner para leer el archivo
            Entero cont = 0
            Mientras (scf.tieneSiguienteLinea()):
                cont++
                Cadena ln = scf.obtenerSiguienteLinea()
                Intentar
                    Dividir la línea en partes usando "#"
                    Agregar al mapa en minúsculas el país y la capital
                Capturar Excepción (Excepción e):
                    Informar si hay un problema con el formato de la línea
                FinIntentar
            FinMientras
        Capturar Excepción (FileNotFoundException e):
            Manejar errores si el archivo no se encuentra
        Finalmente
            Cerrar el escáner después de leer el archivo
        FinIntentar
        Devolver mapa
    FinFunción


    Función push(capitales):
        Intentar
            Crear un escritor para escribir en el archivo (borrando el contenido existente)
            capitales.paraCada((pais, capital) -> {
                Cadena ln = pais + "#" + capital

                Intentar
                    Escribir la línea en minúsculas en el archivo
                Capturar Excepción (IOException e):
                    Manejar errores si hay problemas al escribir
                FinIntentar
            })
            Cerrar el escritor después de escribir en el archivo
        Capturar Excepción (IOException e):
            Manejar errores si hay problemas al cerrar el escritor
        FinIntentar
    FinFunción
}
Fin
```
<div style="page-break-before:always"></div>

### DatagramServer
```
Inicio del programa DatagramServer:
    Crear HashMap mapaPaisesCapitales
    Definir SERVIDOR_PORT como 5555
    Crear instancia de Gestor como gs con ruta "./logs.txt"

    Intentar:
        Crear DatagramSocket con SERVIDOR_PORT y almacenar en socket
        Imprimir "> Servidor a la espera de peticiones en puerto 5555"

        Mientras (verdadero):
            Crear buffer de bytes con longitud 1024
            Crear DatagramPacket con buffer y longitud de buffer, almacenar en paqueteRecibido
            Recibir paqueteRecibido a través del socket

            Convertir paqueteRecibido.getData() a cadena y almacenar en pais
            Imprimir "> Petición recibida: " + pais

            Asignar a mapaPaisesCapitales el resultado de gs.pull()

            Obtener la capital correspondiente al país desde mapaPaisesCapitales, almacenar en capital
            Si no existe la capital, asignar "Desconocida" a capital

            Convertir capital a array de bytes y almacenar en respuestaBytes
            Obtener InetAddress del cliente desde paqueteRecibido y almacenar en clienteAddress
            Obtener puerto del cliente desde paqueteRecibido y almacenar en clientePort

            Crear DatagramPacket con respuestaBytes, longitud de respuestaBytes, clienteAddress y clientePort, almacenar en respuestaPaquete
            Enviar respuestaPaquete a través del socket

            Imprimir "> Respuesta petición " + pais + " → " + capital

    Capturar SocketException como e:
        Imprimir "Error al abrir o utilizar el socket: " + e.getMessage()

    Capturar IOException como e:
        Imprimir "Error de entrada/salida: " + e.getMessage()

Fin del programa DatagramServer
```
<div style="page-break-before:always"></div>

### DatagramClient
```
Inicio del programa DatagramClient:
    Definir SERVIDOR_PORT como 5555
    Definir IP como "localhost"

    Mientras (verdadero):
        Crear instancia de ReadClient como rc
        Crear instancia de Colors como cl
        Imprimir "/c para salir"

        Pedir cadena pais a rc.pedirStringLow("> Obtener capital de ", Falso)

        Si (pais no es igual a "/c"):
            Intentar:
                Crear DatagramSocket como socket
                Convertir pais a array de bytes y almacenar en solicitudBytes
                Obtener InetAddress del servidor usando InetAddress.getByName(IP) y almacenar en servidorAddress

                Crear DatagramPacket con solicitudBytes, longitud de solicitudBytes, servidorAddress y SERVIDOR_PORT, almacenar en solicitudPaquete
                Enviar solicitudPaquete a través del socket

                Crear buffer de bytes con longitud 1024
                Crear DatagramPacket con buffer y longitud de buffer, almacenar en respuestaPaquete
                Recibir respuestaPaquete a través del socket

                Convertir respuestaPaquete.getData() a cadena y almacenar en capital
                Imprimir "Capital de " + pais + ": " + capital

            Capturar SocketException como e:
                Imprimir "Error al abrir o utilizar el socket: " + e.getMessage()

            Capturar IOException como e:
                Imprimir "Error de entrada/salida: " + e.getMessage()

        Sino:
            Salir del bucle

    Imprimir "Adios!"

Fin del programa DatagramClient
```
<div style="page-break-before:always"></div>

### StreamServer
```
Inicio del programa StreamServer:
    Crear HashMap mapaPaisesCapitales
    Definir SERVIDOR_PORT como 5555
    Crear instancia de Gestor como gs con ruta "logs.txt"

    Intentar:
        Crear ServerSocket con SERVIDOR_PORT, almacenar en serverSocket
        Imprimir "> Servidor a la espera de peticiones en puerto 5555"

        Mientras (verdadero):
            Aceptar conexión del cliente usando serverSocket.accept(), almacenar en socketCliente
            Imprimir "> Cliente conectado desde: " + socketCliente.getInetAddress().getHostAddress()

            Crear BufferedReader entrada con InputStreamReader y socketCliente.getInputStream()
            Crear PrintWriter salida con socketCliente.getOutputStream(), autoflush activado

            Leer línea desde entrada y almacenar en pais
            Imprimir "> Petición recibida: " + pais

            Asignar a mapaPaisesCapitales el resultado de gs.pull()
            Obtener la capital correspondiente al país desde mapaPaisesCapitales, almacenar en capital
            Si no existe la capital, asignar "Desconocida" a capital

            Enviar capital a través de salida.println(capital)
            Imprimir "> Respuesta petición " + pais + " → " + capital

            Si (capital es igual a "Desconocida"):
                Leer línea desde entrada y almacenar en respuestaCliente
                Si (respuestaCliente no es nulo):
                    Imprimir "> Respuesta recibida: " + respuestaCliente
                    Asignar respuestaCliente a mapaPaisesCapitales[pais]
                    Llamar gs.push(mapaPaisesCapitales)
                    Imprimir "> Capital de " + pais + " → " + respuestaCliente
                FinSi
            FinSi

            Cerrar entrada
            Cerrar salida
            Cerrar socketCliente

    Capturar IOException como e:
        Imprimir "Error de entrada/salida: " + e.getMessage()

Fin del programa StreamServer
```
<div style="page-break-before:always"></div>

### StreamClient
```
Inicio del programa StreamClient:
    Definir SERVIDOR_PORT como 5555
    Definir IP como "localhost"

    Mientras (verdadero):
        Crear instancia de ReadClient como rc
        Crear instancia de Colors como cl
        Imprimir "/c para salir"

        Pedir cadena pais a rc.pedirStringLow("> Obtener capital de ", Falso)

        Si (pais no es igual a "/c"):
            Intentar:
                Crear Socket con IP y SERVIDOR_PORT, almacenar en socket
                Crear PrintWriter salida con socket.getOutputStream() y autoflush activado
                Crear BufferedReader entrada con InputStreamReader y socket.getInputStream()

                Enviar pais a través de salida.println(pais)
                Leer línea desde entrada y almacenar en capital
                Imprimir "> Capital de " + pais + ": " + capital

                Si (capital es igual a "Desconocida"):
                    Pedir cadena respuestaUsuario a rc.pedirStringLow("> No se ha podido obtener la capital de " + pais + "\\n> ¿Introducir capital? (s/n): ", Verdadero)
                    Si (respuestaUsuario es igual a "s"):
                        Enviar nueva capital para pais a través de salida.println(rc.pedirStringLow("> Nueva capital para " + pais + ": ", Falso))
                    FinSi
                FinSi

                Cerrar salida
                Cerrar entrada
                Cerrar socket

            Capturar IOException como e:
                Imprimir "Error de entrada/salida: " + e.getMessage()

        Sino:
            Salir del bucle

    Imprimir "Adios!"

Fin del programa StreamClient
```
<div style="page-break-before:always"></div>

## Cómo
En primer lugar, no hablaré de las clases `ReadClient` y `Colors` porque son clases genéricas que siempre utilizo para leer datos del usuario y para sacar mensajes más coloridos por consola. Pero sí hablaré de las demás clases.

#### Clase Gestor
No quiero detenerme mucho con esta clase; simplemente diré que esta clase se encarga de leer y escribir en el fichero y de crearlo si no existe. Contiene dos métodos: `pull` y `push`. El método `pull` se encarga de leer el fichero y convertirlo en un `HashMap`, gestionando los posibles errores, y el método `push` se encarga de convertir un `HashMap` pasado como parámetro de entrada al formato del fichero, además de reescribir en el fichero los datos del `HashMap`.

#### Clase DatagramClient
Esta clase tiene dos variables `final`: la IP a la que se conecta el cliente y el puerto. Esta función solo tiene la función principal. Comienza con un bucle que no para hasta que el cliente decide escribir "`/c`". Si el cliente escribe un país, el programa crea un DatagramSocket. Es necesario crear un socket Datagram porque se quiere implementar la comunicación UDP. Los sockets Datagram son adecuados para la transmisión de datos en modo no conectado, lo que significa que cada mensaje enviado es independiente y no hay un establecimiento de conexión previa, a diferencia de TCP. Esto prioriza la velocidad y eficiencia, pero no la entrega de la información. Luego, se convierte a un array de bytes para poderse enviar como un paquete datagram, se crea un objeto InetAddress con la IP del servidor necesaria para la creación del paquete datagram y se envía con una función del socket `socket.send()`. Para finalizar, necesitamos saber qué nos ha devuelto el servidor. Para ello, creamos otro array de bytes, creamos otro paquete y lo recibimos con `socket.receive()`. Con este paquete, creamos un string con la información del servidor y la mostramos por pantalla.

#### Clase DatagramServer
Esta clase actúa como servidor UDP y tiene un HashMap donde guarda los datos de los países, el puerto y también creo un objeto de la clase Gestor para poder leer y escribir en el fichero que le paso como parámetro. DatagramServer solo tiene el método principal. Voy a explicar brevemente cómo he desarrollado esta clase, ya que la teoría necesaria para entenderlo ya la he explicado en la clase anterior. Dentro de un `try`, declaro un `DatagramSocket` con el puerto definido, informo por pantalla el puerto que estoy usando y creo un bucle para que ejecute lo siguiente: recibo la petición del cliente en un paquete datagram que he creado. Luego creo un `String` con ese paquete y actualizo mi `HashMap` con la función `pull` de la clase `Gestor`. Con la función `getOrDefault()` de la clase Map, compruebo si existe y lo guardo en un String. Si no, en el mismo string se guardará "Desconocida", y le envío la respuesta al cliente creando un array de bytes de la capital, creando un objeto InetAddress para crear el paquete y enviárselo al cliente. Muestro un mensaje de la respuesta que le he dado al cliente.
<div style="page-break-before:always"></div>

#### Clase StreamClient
La clase StreamClient tiene las mismas variables `final` que el DatagramClient. Su función principal es similar al Cliente Datagram. Este también tiene un bucle para no dejar de pedirle al cliente un país. En él, se pide al cliente el país y, si no es la opción para salir, se establece una conexión con el servidor mediante la creación de un socket. Se utiliza un objeto PrintWriter para enviar los datos al servidor y se crea un BufferedReader para recibir datos del servidor. Hasta este punto, ya podemos enviar y recibir datos del servidor. Luego, enviamos el nombre del país al servidor y guardamos en un string su respuesta. Si la respuesta es "Desconocida", le damos al cliente la opción de introducir una capital. Para ello, le pedimos amablemente que introduzca s/n. Si la respuesta es afirmativa, le pedimos al cliente que introduzca la capital y la enviamos al servidor. Finalmente, cerramos el socket, el PrintWriter y el BufferedReader y volvemos al inicio del while.

#### Clase StreamServer
Esta clase es similar a la de DatagramSocket, tiene las mismas variables generales: el puerto, el HashMap y un objeto de la clase Gestor. Primero, crea un objeto ServerSocket que sirve para escuchar desde un puerto específico, en este caso, 5555. Luego, dentro de un bucle, crea un Socket aceptando la petición de conexión del cliente, además de un PrintWriter y un BufferedReader para enviar y leer datos. Lee la petición del cliente, actualiza el HashMap y con la función `getOrDefault()` busca la capital o devuelve "Desconocida", y envía la respuesta. Si la capital es desconocida, lee del cliente y comprueba si lo que ha escrito el cliente es `null`. Si es así, significa que el cliente ha dicho que no a añadir una capital, pero si no es `null`, se guarda en el HashMap el país y su capital y se escribe en el fichero con la ayuda de la clase Gestor y su función `push()`. Finalmente, informa de la actualización y cierra el socketCliente, el PrintWriter y el BufferedReader.

## Conclusión
En conclusión, considero que esta actividad ha sido útil y ha contribuido significativamente a mejorar mis habilidades de programación con Sockets.

Además, me intriga conocer las diferentes aproximaciones que mis compañeros han tomado para abordar esta actividad, ya que soy consciente de que hay varias formas de implementarla. Consultar a mis amigos y obtener explicaciones sobre sus enfoques podría proporcionarme valiosas perspectivas y aprender nuevas técnicas. También ver cómo se hace esta actividad en C para poder comprender un poco más cómo funciona C.

En general, me ha parecido interesante descubrir como desarrollar una idea muy basica de los diferentes tipos de servidores TCP/UDP. Considero que la dificultad de esta práctica no ha sido elevada excepcionando el tema de los caracteres especiales ya que no he sabido como implementarlo y me gustaria ver una forma valida de hacer-lo.

</div>
