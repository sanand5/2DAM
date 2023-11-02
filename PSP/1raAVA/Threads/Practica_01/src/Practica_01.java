import java.util.Random;

class Parking {
    private int totalPlazas;
    private int plazasDisponibles;
    private boolean[] plazasOcupadas;

    public Parking(int totalPlazas) {
        this.totalPlazas = totalPlazas;
        this.plazasDisponibles = totalPlazas;
        this.plazasOcupadas = new boolean[totalPlazas];
    }

    public synchronized boolean entrarParking(int cocheID) {
        if (plazasDisponibles > 0) {
            for (int i = 0; i < totalPlazas; i++) {
                if (!plazasOcupadas[i]) {
                    plazasOcupadas[i] = true;
                    plazasDisponibles--;
                    System.out.println("ENTRADA: Coche " + cocheID + " aparca en " + i);
                    mostrarEstadoParking();
                    return true;
                }
            }
        }
        return false;
    }

    public synchronized void salirParking(int cocheID) {
        for (int i = 0; i < totalPlazas; i++) {
            if (plazasOcupadas[i]) {
                plazasOcupadas[i] = false;
                plazasDisponibles++;
                System.out.println("SALIDA: Coche " + cocheID + " sale de la plaza " + i);
                mostrarEstadoParking();
                break;
            }
        }
    }

    public void mostrarEstadoParking() {
        System.out.println("Plazas libres: " + plazasDisponibles);
        System.out.print("Parking: ");
        for (int i = 0; i < totalPlazas; i++) {
            System.out.print("[" + (plazasOcupadas[i] ? i + 1 : 0) + "] ");
        }
        System.out.println("\n");
    }
}

class Coche extends Thread {
    private int cocheID;
    private Parking parking;

    public Coche(int cocheID, Parking parking) {
        this.cocheID = cocheID;
        this.parking = parking;
    }

    @Override
    public void run() {
        while (true) {
            boolean aparcado = parking.entrarParking(cocheID);
            if (aparcado) {
                try {//TODO: El tiempo de espera es muy corto
                    Thread.sleep(1000 + new Random().nextInt(3000)); // Tiempo aleatorio en el parking
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                parking.salirParking(cocheID);
                try {
                    Thread.sleep(1000 + new Random().nextInt(3000)); // Tiempo aleatorio fuera del parking
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            } else {
                System.out.println("Coche " + cocheID + " esperando para entrar al parking.");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }
}

public class Practica_01 {
    public static void main(String[] args) {
        int numCoches = 10;
        int numPlazas = 5;

        Parking parking = new Parking(numPlazas);

        for (int i = 1; i <= numCoches; i++) {
            Coche coche = new Coche(i, parking);
            coche.start();
            
        }
    }
}
