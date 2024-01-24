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
### DatagramServer
### DatagramClient
### StreamServer
### StreamClient

## Como
En primer lugar, no hablare de las clases `ReadClient` y `Colors` porque son clases genericas que siempre utilizo para leer datos del usuario y para sacar mensajes mas coloridos por consola. Pero si que hablare de las demas clases.

#### Clase Gestor
No quiero entretenerme mucho con esta clase, simplemente dire que esta clase es la que se encarga de leer y escribir en el fichero y crear-lo si no existe. Contiene dos metodos `pull` y `push`, el metodo `pull` se encarga de leer el fichero y convertir-lo a un `HashMap` gestionando los posibles errores y el metodo `push` se encarga de convertir un `HashMap` pedido como parametro de entrada a el formato del fichero ademas de reescribir en el fichero los datos del `HashMap`.

#### Clase DatagramClient
#### Clase DatagramServer
#### Clase StreamClient
#### Clase StreamServer

## Conclusión
En conclusión, considero que esta actividad ha sido útil y ha contribuido significativamente a mejorar mis habilidades de programación con Sockets.

Además, me intriga conocer las diferentes aproximaciones que mis compañeros han tomado para abordar esta actividad, ya que soy consciente de que hay varias formas de implementarla. Consultar a mis amigos y obtener explicaciones sobre sus enfoques podría proporcionarme valiosas perspectivas y aprender nuevas técnicas. También ver cómo se hace esta actividad en C para poder comprender un poco más cómo funciona C.

En general, me ha parecido interesante descubrir como desarrollar una idea muy basica de los diferentes tipos de servidores TCP/UDP. Considero que la dificultad de esta práctica no ha sido elevada excepcionando el tema de los caracteres especiales ya que no he sabido como implementarlo y me gustaria ver una forma valida de hacer-lo.


</div>

## Fedback
Lo mas importante del programa es definir el protocolo, es decir que envia cada uno de ellos y cuando y que espera recibir cada uno de ellos en cada momento. Como se implementa ese intercambio de mensajes, ese protocolo, aunque lo has de explicar, tiene mucho menos peso en la nota. No veo la definicion del protocolo en ningun sitio
