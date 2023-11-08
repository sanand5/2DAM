class CuentaBancaria {
    private int saldo;

    public CuentaBancaria(int saldoInicial) {
        this.saldo = saldoInicial;
    }

    public synchronized void realizarIngreso(String concepto, int cantidad, int tiempoEspera) {
        saldo += cantidad;
        System.out.println("Op. #" + obtenerNumeroOperacion() + ": Ingreso " + concepto + ": +" + cantidad + " €");
        System.out.println(">> Saldo actual: " + saldo + " €");
        esperar(tiempoEspera);
    }

    public synchronized void realizarCobro(String concepto, int cantidad, int tiempoEspera) {
        saldo -= cantidad;
        System.out.println("Op. #" + obtenerNumeroOperacion() + ": Cobro " + concepto + ": -" + cantidad + " €");
        System.out.println(">> Saldo actual: " + saldo + " €");
        esperar(tiempoEspera);
    }

    private int numeroOperacion = 0;

    private int obtenerNumeroOperacion() {
        return ++numeroOperacion;
    }

    private void esperar(int tiempoEspera) {
        try {
            Thread.sleep(tiempoEspera);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

public class SimuladorCuentaBancaria {
    public static void main(String[] args) {
        CuentaBancaria cuenta = new CuentaBancaria(4000);

        Thread nominaThread = new Thread(() -> {
            cuenta.realizarIngreso("nómina", 1200, 3000);
        });

        Thread hipotecaThread = new Thread(() -> {
            cuenta.realizarCobro("hipoteca", 400, 3000);
        });

        Thread luzThread = new Thread(() -> {
            cuenta.realizarCobro("luz", 40, 3000);
        });

        Thread aguaThread = new Thread(() -> {
            cuenta.realizarCobro("agua", 30, 3000);
        });

        Thread comprasThread = new Thread(() -> {
            cuenta.realizarCobro("compras", 50, 1000);
        });

        Thread retiradaEfectivoThread = new Thread(() -> {
            cuenta.realizarCobro("retirada efectivo", 20, 300);
        });

        nominaThread.start();
        hipotecaThread.start();
        luzThread.start();
        aguaThread.start();
        comprasThread.start();
        retiradaEfectivoThread.start();
    }
}
