<div style="text-align: justify;">

# ¡IMPORTANTE: LOS ARCHIVOS SE COMPIRMEN EN ZIP!
# Memoria Practica X Tema

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
Clase Gestor {
    String path
    File fl
    FileWriter fw
    Scanner scf
    Colors cl

    Método Gestor(String path)
        this.path = path
        Intentar
            fl = nuevo File(path)
        Capturar NullPointerException e
            cl.errMsg(e.getMessage())

    Método HashMap pull()
        HashMap map = nuevo HashMap()
        Intentar
            scf = nuevo Scanner(nuevo FileReader(fl))
            int cont = 0
            Mientras scf tenga siguiente línea
                cont++
                ln = scf.nextLine()
                Intentar
                    log = ln.split("#")
                    map.put(log[0].toLowerCase(), log[1].toLowerCase())
                Capturar Exception e
                    cl.warMsg("La línea " + cont + " está vacía o no tiene el formato correcto")
        Capturar FileNotFoundException e
            cl.errMsg(e.getMessage())
        Finalmente
            scf.close()
        Devolver map

    Método push(HashMap capitales)
        Intentar
            fw = nuevo FileWriter(fl, false)
            capitales.forEach((pais, capital) ->
                ln = pais + "#" + capital
                Intentar
                    fw.write(ln.toLowerCase() + "\n")
                Capturar IOException e
                    cl.errMsg(e.getMessage())
            )
            fw.close()
        Capturar IOException e
            cl.errMsg(e.getMessage())
}
```
### DatagramServer
``` 
Clase DatagramServer {
    static HashMap<String, String> mapaPaisesCapitales
    static final int SERVIDOR_PORT = 5555
    static Gestor gs

    Método main(String[] args)
        Intentar
            DatagramSocket socket = nuevo DatagramSocket(SERVIDOR_PORT)
            Imprimir "> Servidor a la espera de peticiones en puerto 5555"
            Mientras verdadero
                byte[] buffer = nuevo byte[1024]
                DatagramPacket paqueteRecibido = nuevo DatagramPacket(buffer, buffer.length)
                socket.receive(paqueteRecibido)
                String pais = nuevo String(paqueteRecibido.getData(), 0, paqueteRecibido.getLength())
                Imprimir "> Petición recibida: " + pais
                mapaPaisesCapitales = gs.pull()
                String capital = mapaPaisesCapitales.getOrDefault(pais, "Desconocida")
                byte[] respuestaBytes = capital.getBytes()
                InetAddress clienteAddress = paqueteRecibido.getAddress()
                int clientePort = paqueteRecibido.getPort()
                DatagramPacket respuestaPaquete = nuevo DatagramPacket(respuestaBytes, respuestaBytes.length, clienteAddress, clientePort)
                socket.send(respuestaPaquete)
                Imprimir "> Respuesta petición " + pais + " → " + capital
        Capturar SocketException e
            Imprimir "Error al abrir o utilizar el socket: " + e.getMessage()
        Capturar IOException e
            Imprimir "Error de entrada/salida: " + e.getMessage()
}

```
### DatagramClient
``` 
Clase DatagramClient {
    static final int SERVIDOR_PORT = 5555
    static final String IP = "localhost"

    Método main(String[] args)
        ReadClient rc = nuevo ReadClient()
        Intentar
            DatagramSocket socket = nuevo DatagramSocket()
            Mientras verdadero
                String pais = rc.pedirStringLow("> Obtener capital de ", falso)
                byte[] solicitudBytes = pais.getBytes()
                InetAddress servidorAddress = InetAddress.getByName(IP)
                DatagramPacket solicitudPaquete = nuevo DatagramPacket(solicitudBytes, solicitudBytes.length, servidorAddress, SERVIDOR_PORT)
                socket.send(solicitudPaquete)
                byte[] buffer = nuevo byte[1024]
                DatagramPacket respuestaPaquete = nuevo DatagramPacket(buffer, buffer.length)
                socket.receive(respuestaPaquete)
                String capital = nuevo String(respuestaPaquete.getData(), 0, respuestaPaquete.getLength())
                Imprimir "> Capital de " + pais + ": " + capital
        Capturar SocketException e
            Imprimir "Error al abrir o utilizar el socket: " + e.getMessage()
        Capturar IOException e
            Imprimir "Error de entrada/salida: " + e.getMessage()
}
```
### StreamServer
``` 
Clase StreamServer {
    static HashMap<String, String> mapaPaisesCapitales
    static final int SERVIDOR_PORT = 5555
    static Gestor gs

    Método main(String[] args)
        Intentar
            ServerSocket serverSocket = nuevo ServerSocket(SERVIDOR_PORT)
            Imprimir "> Servidor a la espera de peticiones en puerto 5555"
            Mientras verdadero
                Socket socketCliente = serverSocket.accept()
                Imprimir "> Cliente conectado desde: " + socketCliente.getInetAddress().getHostAddress()
                BufferedReader entrada = nuevo BufferedReader(new InputStreamReader(socketCliente.getInputStream()))
                PrintWriter salida = nuevo PrintWriter(socketCliente.getOutputStream(), verdadero)

                String pais = entrada.readLine()
                Imprimir "> Petición recibida: " + pais

                mapaPaisesCapitales = gs.pull()
                String capital = mapaPaisesCapitales.getOrDefault(pais, "Desconocida")
                salida.println(capital)
                Imprimir "> Respuesta petición " + pais + " → " + capital

                Si capital es igual a "Desconocida"
                    String respuestaCliente = entrada.readLine()
                    Imprimir "> Respuesta recibida: " + respuestaCliente
                    mapaPaisesCapitales.put(pais, respuestaCliente)
                    gs.push(mapaPaisesCapitales)
                    Imprimir "> Capital de " + pais + " → " + respuestaCliente

                entrada.close()
                salida.close()
                socketCliente.close()
        Capturar IOException e
            Imprimir "Error de entrada/salida: " + e.getMessage()
}

```
### StreamClient
``` 
Clase StreamClient {
    static final int SERVIDOR_PORT = 5555
    static final String IP = "localhost"

    Método main(String[] args)
        ReadClient rc = nuevo ReadClient()
        Intentar
            Mientras verdadero
                Socket socket = nuevo Socket(IP, SERVIDOR_PORT)
                PrintWriter salida = nuevo PrintWriter(socket.getOutputStream(), verdadero)
                BufferedReader entrada = nuevo BufferedReader(new InputStreamReader(socket.getInputStream()))

                String pais = rc.pedirStringLow("> Obtener capital de ", falso)
                salida.println(pais)

                String capital = entrada.readLine()
                Imprimir "> Capital de " + pais + ": " + capital

                Si capital es igual a "Desconocida"
                    Imprimir "> No se ha podido obtener la capital de " + pais + "\n> ¿Introducir capital? (s/n): "
                    String respuestaUsuario = rc.pedirStringLow("", verdadero)
                    Si respuestaUsuario es igual a "s"
                        salida.println(rc.pedirStringLow("> Nueva capital para " + pais + ": ", falso))

                salida.close()
                entrada.close()
                socket.close()
        Capturar IOException e
            Imprimir "Error de entrada/salida: " + e.getMessage()
}

```
## Como
En primer lugar, no hablare de las clases `ReadClient` y `Colors` porque son clases genericas que siempre utilizo para leer datos del usuario y para sacar mensajes mas coloridos por consola. Pero si que hablare de las demas clases.

#### Clase Gestor
No quiero entretenerme muchoi con esta clase, simplemente dire que esta clase es la que se encarga de escribir en el fichero. Contiene dos metodos `pull` y `push`, el metodo `pull` se encarga de leer el fichero y convertir-lo a un `HashMap` gestionando los posibles errores y el metodo `push` se encarga de convertir un `HashMap` pedido como parametro de entrada a el formato del fichero ademas de reescribir en el fichero los datos del `HashMap`.

#### Clase DatagramClient
#### Clase DatagramServer
#### Clase StreamClient
#### Clase StreamServer

## Conclusión
En conclusión, considero que esta actividad ha sido útil y ha contribuido significativamente a mejorar mis habilidades de programación con Sockets.

Además, me intriga conocer las diferentes aproximaciones que mis compañeros han tomado para abordar esta actividad, ya que soy consciente de que hay varias formas de implementarla. Consultar a mis amigos y obtener explicaciones sobre sus enfoques podría proporcionarme valiosas perspectivas y aprender nuevas técnicas. También ver cómo se hace esta actividad en C para poder comprender un poco más cómo funciona C.

En general, me ha parecido interesante descubrir como desarrollar una idea muy basica de los diferentes tipos de servidores TCP/UDP. Considero que la dificultad de esta práctica no ha sido elevada excepcionando el tema de los caracteres especiales ya que no he sabido como implementarlo y me gustaria ver una forma valida de hacer-lo.


</div>

```
src/
  DatagramClient.java
  DatagramServer.java
  StreamClient.java
  StreamServer.java
  utilidades/
    Colors.java
    Gestor.java
    ReadClient.java
```

# TODO
- [x] Pasar a fichers
  - [x] Fun pull, comprobar que si no te el format correcte no dona error
  - [x] Segurament estiga mal lo de el hasNextLine, perque com no es pot tirar arrere si faig dos pull seguits petara
  - [x] Comprobar perque va soles amb la ruta absoluta
- [ ] Comprobar que funciona en visual
- [x] Canviar la lectura del usuario a readClient
- [x] Hacer que ignore las mayusculas
- [c] utilizar lo de el timeout
- [x] si quiero volver a preguntar
- [x] gestionar que no escriba null en el fichero
