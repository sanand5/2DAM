# Memoria Practica I Threads
## Que 
Tu texto justificado aquí.
Se desea realizar una clase llamada Parking que reciba el número de plazas del parking y el número de coches que tiene el sistema.

Se deben crear tantos threads como coches hayan. El parking dispondrá de una única entrada y una única salida. En la entrada de vehículos habrá un dispositivo de control que permitia o oimpida el acceso de los mismos al parking, dependiendo del estado actual del mismo (plazas de aparcamiento disponibles).

Los tiempos de espera de los vehículos dentro del párking son aleatorios. En el momento en el que un vehículo sale del párking, notifica al dispositivo de control el número de plaza que tenía asignada y se libera la plaza que estuviera ocupando, quedando así estas nuevamente disponibles. Un vehículo que ha salido del párking esperará un tiempo aleatorio para volver a entrar nuevamente en el mismo. Por tanto, los vehículos estarán entrando y saliendo indefinidamente del párking.

Es importante que se diseñar el programa de tal forma que se asegure que, antes o después, un vehículo que permanece esperando a la entrada del párking entrará en el mismo (no se produzca inanición).
```
Número de coches: 10

Número de plazas: 5


ENTRADA: Coche 1 aparca en 0

Plazas libres: 4

Parking: [1] [2] [3] [0] [0]


ENTRADA: Coche 2 aparca en 1

Plazas libres: 3

Parking: [1] [2] [3] [0] [0]


ENTRADA: Coche 3 aparca en 2

Plazas libres: 2

Parking: [1] [2] [3] [0] [0]

ENTRADA: Coche 4 aparca en 3

Plazas libres: 1

Parking: [1] [2] [3] [4] [0]


SALIDA: Coche 2 sale

Plazas libres: 2

Parking: [1] [0] [3] [4] [0]
...
```
## Para que
Para que aprendamos la utilización de los hilos en java, con esta actividad nos enseñamos de manera basica a utilizar los threads y ver como es su funcionamiento. Esta practica es útil ja que de manera sencilla hemos visto como actuan los hilos y las distintas formas de hacer-lo y de aplicarlo.
## Pseudocodigo
### Parking
``` 
Clase Parking:
    Atributos:
        - plazas (Arreglo de enteros)
        - numeroPlazas (Entero)

    Método Parking(numeroPlazas: Entero):
        this.numeroPlazas = numeroPlazas
        this.plazas = Nuevo Arreglo de Enteros de tamaño numeroPlazas
        Para i desde 0 hasta numeroPlazas-1:
            plazas[i] = 0

    Método aparcar(cocheID: Entero): Entero
        Mientras numeroPlazas sea igual a 0:
            Intentar:
                Mostrar "Coche " + cocheID + " esperando para entrar al parking."
                Esperar
            Capturar InterruptedException como e:
                HiloActual().interrumpir()

        plaza = buscarPlazaLibre()
        plazas[plaza] = cocheID
        numeroPlazas--

        Mostrar "ENTRADA: Coche " + cocheID + " aparca en plaza " + plaza
        Mostrar "Plazas libres: " + numeroPlazas
        Mostrar "Parking: " + toString()
        Devolver plaza

    Método desaparcar(plaza: Entero, cocheID: Entero)
        plazas[plaza] = 0
        numeroPlazas++

        Mostrar "SALIDA: Coche " + cocheID + " sale de plaza " + plaza
        Mostrar "Plazas libres: " + numeroPlazas
        Mostrar "Parking: " + toString()
        Notificar()

    Método buscarPlazaLibre(): Entero
        Para i desde 0 hasta Tamaño de plazas - 1:
            Si plazas[i] == 0:
                Devolver i
        Devolver -1

    Método toString(): Cadena
        resultado = ""
        Para i desde 0 hasta Tamaño de plazas - 1:
            resultado += "[" + (plazas[i] > 0 ? plazas[i] : "0") + "] "
        Devolver resultado

```
### Coches
```
Módulo Coche:
    Entrada: cocheID (entero), parking (objeto de la clase Parking)
    
    Repetir indefinidamente:
        min = 1000
        max = 5000
        Generar un número aleatorio entre min y max y almacenarlo en la variable random
        Dormir el hilo actual durante random milisegundos
        Capturar y manejar excepción InterruptedException si ocurre
        
        Llamar al método aparcar del objeto parking, pasando cocheID como argumento y almacenar el resultado en la variable plaza
        Generar un número aleatorio entre min y max y almacenarlo en la variable random
        Dormir el hilo actual durante random milisegundos
        Capturar y manejar excepción InterruptedException si ocurre
        
        Llamar al método desaparcar del objeto parking, pasando plaza y cocheID como argumentos
Fin del Módulo Coche

```
### Practica_01
```
Módulo principal Practica_01:
    Crear un objeto ReadClient llamado rc
    
    Solicitar al usuario "Número de coches: "
    Leer y almacenar el número en la variable numeroCoches
    
    Solicitar al usuario "Número de plazas: "
    Leer y almacenar el número en la variable numeroPlazas
    
    Crear un objeto Parking llamado parking, con el número de plazas igual a numeroPlazas
    
    Para i desde 1 hasta numeroCoches:
        Crear un objeto Coche llamado coche, pasando i y parking como argumentos
        Iniciar el hilo del coche

Fin del Módulo principal

```
## Como
## Conclusión
Esta practica ha sido útil para aprender como programar con hilos en java, tabien he visto distintas maneras de havcerlo por internet, con semaforos i con el uso de la Runneable, pero al final me he decidido por hacer-lo de esta manera porque es la que más me ha resultado sencilla i facil de usar.
