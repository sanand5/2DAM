package Practica_01;
public class Coche extends Thread{
    private int cocheID;
    private Parking parking;

    public Coche(int cocheID, Parking parking) {
        this.cocheID = cocheID;
        this.parking = parking;
    }
    
    @Override
    public void run() {
        int min = 1000, max = 5000;
        while (true) {
            try {
                int random = (int) (Math.random() * (max - min + 1) + min);
                sleep(random);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            int plaza = parking.aparcar(cocheID);
            try {
                int random = (int) (Math.random() * (max - min + 1) + min);
                sleep(random);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            parking.salir(plaza, cocheID);
        }
    }
}
