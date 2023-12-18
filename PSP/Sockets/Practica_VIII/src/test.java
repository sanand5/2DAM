import java.util.HashMap;

public class test {
    static Gestor gs = new Gestor("C:\\Users\\andre\\Documents\\2DAM\\PSP\\Sockets\\Practica_VIII\\src\\logs.txt");
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
