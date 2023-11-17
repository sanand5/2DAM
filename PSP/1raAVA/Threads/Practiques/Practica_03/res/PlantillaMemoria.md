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

# Memoria Raquel
## Para que 
Este ejercicio sirve para poner más en práctica la utilización de hilos, la sincronización lo cuál permite que un solo hilo lo ejecute a la vez, en este caso el método llegada. Y además algo que he puesto en práctica es el uso del join(), que sirve para esperar a que todos los hilos terminen. Así que gracias a el join(), primero espera a que todos los hilos terminen y después poder anunciar el ganador.
Y algo nuevo de esta práctica es el método System.currentTimeMillis() que para esta práctica nos ha sido útil para calcular el tiempo que tarda un atleta en correr la carrera

## Conclusión
Como conclusión, teniendo en cuenta las otras prácticas, me parece un buen ejercicio porque así entendemos mejor como funciona la utilización de los hilos. Algo que me ha llamado la atención de esta práctica ha sido la utilización del System.currentTimeMillis(), ya que gracias a esto hemos podido calcular el tiempo que ha tardado el atleta en correr la carrera. 
Y por último, en mi caso he necesitado el synchronized para el método llegada, y gracias a esto he podido evitar las condiciones de carrera como en el anterior ejercicio. En general, me ha parecido bastante útil porque hemos gastado nuevas cosas y además hemos puesto en práctica todo lo anterior, y esto me ayuda a comprender más a fondo cada detalle de los hilos

# Memoria Raul
## Para que
Para aprender cómo funcionan exactamente los hilos, he diseñado un programa que simula operaciones bancarias como nóminas, hipotecas, pagos de servicios y compras. Para lograr esto, he creado una clase llamada `CuentaBancaria`.

En el proceso de simular estas operaciones, utilizo hilos para representar cada una de ellas, asignándoles su correspondiente cantidad y tiempo de espera post-ejecución. Con el fin de evitar problemas de concurrencia y garantizar la integridad de los datos, he implementado el uso de `synchronized` en mis métodos.

Esta elección asegura que los hilos interactúen de manera segura con la cuenta bancaria, evitando condiciones de carrera. Considero que esta práctica es fundamental para comprender y aplicar eficazmente el uso de hilos en programas concurrentes.

## Conclusión
En esta práctica de simulación de operaciones bancarias utilizando hilos en Java, me ha ayudado a entender bien el funcionamiento de la sincronización y la gestión de concurrencia. La implementación de la clase `CuentaBancaria` me ha servido para la creación de múltiples hilos que simulan diversas operaciones, ya sean ingresos o cobros, cada una con su correspondiente tiempo de espera y cantidad asociada.

La importancia de la sincronización en el programa es evidente, siendo crucial para evitar condiciones de carrera. El uso de la palabra clave `synchronized` en métodos o secciones específicas ha sido determinante para garantizar la integridad de los datos al manipular la cuenta bancaria y prevenir posibles inconsistencias.

En resumen, este ejercicio no solo me ha ayudado a entender mejor la implementación de hilos en Java, sino que también me ha ayudado a entender lo importante que es gestionar la concurrencia de manera segura. Por todos estos motivos considero que ha sido una muy buena práctica y que ha sido muy amena de hacer.