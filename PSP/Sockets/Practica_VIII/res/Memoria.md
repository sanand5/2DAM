# ¡IMPORTANTE: LOS ARCHIVOS SE COMPIRMEN EN ZIP!
# Memoria Practica X Tema

## Que 
<div style="text-align: justify;">
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

</div>

## Para que
<div style="text-align: justify;">

</div>
<div style="page-break-before:always"></div>

## Pseudocodigo
### Clase
``` 

```
## Como
<div style="text-align: justify;">

#### Clase

</div>

## Conclusión
<div style="text-align: justify;">


</div>

# TODO
- [] Pasar a fichers
  - fun pull, comprobar que si no te el format correcte no dona error
  - podria fer una funció que  comprobe que tot el ficher estiga bé
  - segurament estiga mal lo de el hasNextLine, perque com no es pot tirar arrere si faig dos pull seguits petara
  - Si no encuentra la ruta que la pida

# GPT
# Gestor
```java
import utilidades.Colors;

import java.io.*;
import java.util.HashMap;
import java.util.Scanner;
public class Gestor {
    String path;
    File fl;
    Scanner scf;
    FileWriter fw;
    Colors cl = new Colors();


    public Gestor(String path){
        this.path = path;
        try{
            fl = new File(path);
            scf = new Scanner(new FileReader(fl));
            fw = new FileWriter(fl, false);
        }catch (Exception e){
            cl.errMsg(e.getMessage());
        }
    }

    public HashMap pull() {
        HashMap<String, String> map = new HashMap<>();
        if (scf != null){
            while (scf.hasNextLine()){
                String ln = scf.nextLine();
                String log[] = ln.split("#");
                map.put(log[0], log[1]);
            }
        }else {
            cl.warMsg("El archivo esta vacio");
        }
        return map;
    }

    public void push(HashMap capitales){
        capitales.forEach((pais, capital) -> {
            String ln = pais + "#" + capital;
            try {
                fw.write(ln + "\n");
            } catch (IOException e) {
                cl.errMsg(e.getMessage());
            }
        });
    }
}

```
# test
```java
import java.util.HashMap;

public class test {
    static Gestor gs = new Gestor("./logs.txt");
    static HashMap map;
    public static void main(String[] args) {
        map = gs.pull();
        see(map);
        map.put("hola", "adios");
        see(map);
        map = gs.pull();
        map.put("hola","adios");
        gs.push(map);
        map.clear();
        map = gs.pull();
        see(map);
    }
    public static void see(HashMap map){
        map.forEach((pais, capital) -> {
            System.out.println("Capital de " + pais + ": " + capital);
        });
    }
}

```
# Error
```
Error: .\logs.txt (El sistema no puede encontrar el archivo especificado)
War: El archivo esta vacio
Capital de hola: adios
War: El archivo esta vacio
Exception in thread "main" java.lang.NullPointerException: Cannot invoke "java.io.FileWriter.write(String)" because "this.fw" is null
	at Gestor.lambda$push$0(Gestor.java:43)
	at java.base/java.util.HashMap.forEach(HashMap.java:1429)
	at Gestor.push(Gestor.java:40)
	at test.main(test.java:13)

```