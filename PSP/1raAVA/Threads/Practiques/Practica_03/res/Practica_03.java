import java.util.LinkedHashMap;
import java.util.Map;

class Atleta extends Thread {
    int dorsal;
    private final Carrera carrera;
    public Atleta(int dorsal, Carrera carrera) {
        this.dorsal = dorsal;
        this.carrera = carrera;
    }

    @Override
    public void run() {
        synchronized (carrera) {
            try {
                System.out.println(">> Dorsal " + dorsal + " Listo");
                carrera.wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        long startTime = System.currentTimeMillis();
        Carrera.dormir((int) (Math.random() * (11000 - 9000 + 1) + 9000));
        long endTime = System.currentTimeMillis();

        carrera.notificarLlegada(dorsal, endTime - startTime);
    }
}

class Carrera {
    private final int NUM_ATLETAS;
    private final Atleta[] atletas;
    public LinkedHashMap<Integer, Long> tiempos;

    public Carrera(int numAtletas) {
        this.NUM_ATLETAS = numAtletas;
        this.atletas = new Atleta[NUM_ATLETAS];
        this.tiempos = new LinkedHashMap<>();
    }

    public void prepararAtletas(){
        for (int i = 0; i < NUM_ATLETAS; i++) {
            atletas[i] = new Atleta(i + 1, this);
            atletas[i].start();
        }
    }

    public void correr(){
        synchronized (this){
            System.out.println("Preparados!");
            Carrera.dormir(1000);
            System.out.println("Listos!");
            Carrera.dormir(1000);
            System.out.println("Ya!\n");
            notifyAll();
        }

        for (Atleta atleta : atletas) {
            try {
                atleta.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public synchronized void notificarLlegada(int dorsal, long tiempo) {
        tiempos.put(dorsal, tiempo);
    }

    public Map.Entry<Integer, Long> obtenerGanador() {
        Map.Entry<Integer, Long> ganador = null;
        for (Map.Entry<Integer, Long> entry : tiempos.entrySet()) {
            if (ganador == null || entry.getValue() < ganador.getValue()) {
                ganador = entry;
            }
        }
        return ganador;
    }

    public static void dormir(int mseconds){
        try {
            Thread.sleep(mseconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
class Practica_03{
    public static void main(String[] args) {
        System.out.println("Carrera 100M LISOS.\n");

        Carrera carrera = new Carrera(8);

        System.out.println("Corredores a la pista…\n");
        carrera.prepararAtletas();
        Carrera.dormir(1000);

        System.out.println("\nEMPIEZA LA CARRERA\n");
        carrera.correr();

        System.out.println("Resultados llegada:\n");
        carrera.tiempos.forEach((dorsal, tiempo) -> System.out.println("Dorsal " + dorsal + " tarda " + (tiempo / 1000.0) + " segundos"));

        Map.Entry<Integer, Long> ganador = carrera.obtenerGanador();
        System.out.println("\nGanador de la carrera: Dorsal " + ganador.getKey() + "!");
    }
}