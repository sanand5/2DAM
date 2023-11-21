package Practica_01;
import iohelpers.ReadClient;
public class Practica_01 {
    public static void main(String[] args) {
        ReadClient rc = new ReadClient();
        int nCoches = rc.pedirInteger("Numero de coches: ");
        int nPlazas = rc.pedirInteger("Numero de plazas: ");
        Parking parking = new Parking(nPlazas);
        for (int i = 1; i <= nCoches; i++) {
            Coche coche = new Coche(i, parking);
            coche.start();
        }
    }
    
}
