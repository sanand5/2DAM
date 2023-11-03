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

## Pseudocodigo
### Parking
``` 

```
### Coches
```

```
### Practica_01
```

```
## Como
## Conclusión