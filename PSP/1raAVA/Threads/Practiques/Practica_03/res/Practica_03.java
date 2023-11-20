class Atleta extends Thread {
    int dorsal;
   // private final Carrera carrera;

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
        long tiempo = (endTime - startTime);
        System.out.println("Dorsal " + dorsal + " tarda " + tiempo / 1000.0 + " segundos");
        carrera.notificarLlegada(dorsal, tiempo);
    }
}

class Carrera {
    private final int NUM_ATLETAS;
    private final Atleta[] atletas;
    private long[] ganador = {0,12000};

    public Carrera(int numAtletas) {
        this.NUM_ATLETAS = numAtletas;
        this.atletas = new Atleta[NUM_ATLETAS];
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
        Carrera.dormir(8500);
        System.out.println("Resultados llegada:\n");
        for (Atleta atleta : atletas) {
            try {
                atleta.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public synchronized void notificarLlegada(int dorsal, long tiempo) {
        if (tiempo < ganador[1]){
            ganador[0] = dorsal;
            ganador[1] = tiempo;
        }
    }

    public long obtenerGanador() {
        return ganador[0];
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

        System.out.println("\nGanador de la carrera: Dorsal " + carrera.obtenerGanador() + "!");
    }
}