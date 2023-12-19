package utilidades;

import utilidades.Gestor;

import java.util.HashMap;

public class test {
    static Gestor gs = new Gestor("./res/logs.txt");
    static HashMap map = new HashMap();
    public static void main(String[] args) {
        //testMap();
        HashMap o = gs.pull();
        HashMap m = gs.pull();
        see(o);
        see(m);
    }
    public static void testMap(){

        System.out.println("Mostar nada");
        see(map);
        map.put("hola", "adios");

        System.out.println("Mostar holaAdios");
        see(map);
        map = gs.pull();

        System.out.println("Mostar nada");
        see(map);
        map.put("hola","adios");

        System.out.println("Mostar holaAdios");
        see(map);

        System.out.println("Escribir holaAdios");
        gs.push(map);

        System.out.println("Borrar todo");
        map.clear();

        System.out.println("Mostar nada");
        see(map);
        map = gs.pull();
        System.out.println("Mostar holaAdios");
        see(map);
    }

    public static void see(HashMap map){
        map.forEach((pais, capital) -> {
            System.out.println("Capital de " + pais + ": " + capital);
        });
    }
}
