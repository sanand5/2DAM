# Memoria Practica X Tema

## Que 
<div style="text-align: justify;">
Se desea realizar un sistema de login básico.

El programa cliente al iniciarse solicitará un usuario y contraseña. Este usuario y contraseña se enviará al servidor.

El programa servidor al recibir el usuario y la contraseña el servidor comprueba si el usuario está activo. Si lo está, responderá al usuario con el mensaje, “el usuario se ha desconectado”.

En el caso de que el usuario no esté activo, comprobará el usuario y contraseña en el fichero: usuarios.txt que se encontrará en C:/sistemalogin. Este fichero tiene el formato:
```
ID#usuario#contraseña  
1#pocoyo#1234
```
Si existe el usuario y contraseña, el usuario pasará al estado activo y contestará al cliente con el mensaje: “el usuario se ha conectado correctamente”.

En el caso de que el usuario y/o contraseña sean incorrectos, el servidor le enviará el mensaje “usuario y/o contraseña incorrecto”.
</div>

## Para que
<div style="text-align: justify;">
Este ejercicio ha resultado útil para aprender y practicar el funcionamiento de los Sockets. También ha sido beneficioso para adquirir conocimientos sobre bases de datos, ya que fue necesario crearla y realizar un par de consultas en ella. La lógica del ORM en sí no resultó ser muy complicada. En mi experiencia, la dificultad de este programa radicó en la conexión con la base de datos y el tiempo de programación, dado que es un programa extenso. Además, invertí tiempo en la búsqueda y corrección de errores. En resumen, la actividad fue útil para comprender mejor el funcionamiento de un ORM, así como para aprender a crear sesiones, realizar consultas, entre otros aspectos.
</div>
<div style="page-break-before:always"></div>

## Pseudocodigo
### Servidor
``` 
Inicio del programa Servidor:
    Definir PUERTO como 5432
    Crear HashMap activos para almacenar el estado de los usuarios

    Intentar:
        Crear ServerSocket con PUERTO
        Imprimir "Servidor esperando conexiones..."

        Mientras (true):
            Aceptar conexión del cliente usando accept()
            Imprimir "Cliente accedió desde: " + dirección IP del cliente

            Iniciar un nuevo hilo para manejar la conexión del cliente usando manejarConexion()

    Capturar IOException:
        Imprimir traza de pila del error

Fin del programa Servidor

Función manejarConexion(socketCliente):
    Intentar:
        Crear BufferedReader para entrada desde el socket
        Crear PrintWriter para salida hacia el socket

        Leer usuario desde entrada
        Leer contraseña desde entrada

        Si usuario está activo:
            Enviar "El usuario se ha desconectado" al cliente
            Marcar usuario como inactivo en el HashMap

        Sino:
            Si verificarCredenciales(usuario, contraseña):
                Enviar "El usuario se ha conectado correctamente" al cliente
                Marcar usuario como activo en el HashMap
            Sino:
                Enviar "Usuario y/o contraseña incorrecto" al cliente

        Cerrar socketCliente

    Capturar IOException:
        Imprimir traza de pila del error

Función usuarioEstaActivo(usuario):
    Devolver el valor asociado a usuario en el HashMap activos (o false si no existe)

Función verificarCredenciales(usuario, contraseña):
    Definir rutaArchivo como "res/usuarios.txt"

    Intentar:
        Crear BufferedReader para leer desde rutaArchivo

        Mientras haya líneas en el archivo:
            Leer línea
            Dividir la línea en campos usando "#"

            Si hay 3 campos y el segundo campo es igual a usuario y el tercer campo es igual a contraseña:
                Devolver true

    Capturar IOException:
        Imprimir traza de pila del error

    Devolver false

```
### Cliente
``` 
Inicio del programa Cliente:
    Definir SERVIDOR_IP como "localhost"
    Definir PUERTO como 5432

    Intentar:
        Crear Socket con SERVIDOR_IP y PUERTO
        Crear BufferedReader para entrada desde el socket
        Crear PrintWriter para salida hacia el socket
        Crear BufferedReader para entrada desde el teclado

        Imprimir "Ingrese usuario: "
        Leer usuario desde teclado
        Imprimir "Ingrese contraseña: "
        Leer contraseña desde teclado

        Enviar usuario al servidor usando PrintWriter
        Enviar contraseña al servidor usando PrintWriter

        Leer respuesta del servidor desde BufferedReader
        Imprimir "Respuesta del servidor: " + respuesta del servidor

    Capturar IOException:
        Imprimir traza de pila del error

Fin del programa Cliente

```
## Como
<div style="text-align: justify;">

#### Clase

</div>

## Conclusión
<div style="text-align: justify;">


</div>
