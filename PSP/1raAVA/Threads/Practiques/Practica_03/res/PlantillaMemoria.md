# Memoria Practica I Threads

## Que 
<div style="text-align: justify;">
Se desea implementar una carrera de 100m lisos:

Por un lado, tenemos 8 Atletas dispuestos a correr. Cada uno tiene un atributo dorsal.
Por otro lado, tenemos una clase principal Carrera. Ésta indica el pistoletazo de salida y el resultado de la carrera.

Todos los Atletas comienzan pero se quedan parados esperando el pistoletazo de salida.

Luego comienzan a correr (tardan entre 9 y 11s).

Al llegar a meta notifican a la carrera su dorsal y terminan.

La Carrera escribe “preparados” y espera 1s, luego escribe “listos” y espera 1s, finalmente escribe “ya!” y notifica a los hilos de los Atletas
Cada vez que un atleta le notifica su dorsal, escribe por pantalla lo que ha tardado el atleta con la ayuda de: System.currentTimeMillis()
NOTA: podéis hacer uso de una clase adicional, por ejemplo, Salida, donde déis las señales de salida y llegada.
Ejemplo de ejecución:
```
CARRERA 100M LISOS.


Corredores a la pista…

>> Dorsal 1 Listo
>> Dorsal 2 Listo
>> Dorsal 4 Listo
>> Dorsal 5 Listo
>> Dorsal 8 Listo
>> Dorsal 6 Listo
>> Dorsal 3 Listo
>> Dorsal 7 Listo
EMPIEZA LA CARRERA
Preparados!
Listos!
Ya!
Resultados llegada:
Dorsal 3 tarda 9.11
Dorsal 7 tarda 9.24
…

Ganador de la carrera: Dorsal 3!
```
</div>

## Para que
<div style="text-align: justify;">

</div>
<div style="page-break-before:always"></div>

## Pseudocodigo
### Nombre  de la clase
``` 

```
<div style="page-break-before:always"></div>

## Como
<div style="text-align: justify;">


</div>

## Conclusión
<div style="text-align: justify;">

</div>

