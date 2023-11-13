class CuentaBancaria {
    private double saldo;
    private static int numeroOp = 1;

    public CuentaBancaria(double saldoInicial) {
        this.saldo = saldoInicial;
    }

    public synchronized void Ingresar(String concepto, double cantidad, int tiempoEspera) {
        saldo += cantidad;
        System.out.println("Op. #" + obtenerNumeroOp() + ": Ingreso " + concepto + ": +" + cantidad + " €");
        System.out.println(">> Saldo actual: " + saldo + " €");
        dormir(tiempoEspera);
    }

    public synchronized void Cobrar(String concepto, double cantidad, int tiempoEspera) {
        boolean saldoCobrado = (saldo - cantidad <= 0);
        if (saldoCobrado && concepto.matches("(compras|retirada efectivo)")) {
            System.out.println(">> No ha sido posible cobrar " + concepto + ": " + cantidad + " €");
            dormir(tiempoEspera);
        } else {
            saldo -= cantidad;
            System.out.println("Op. #" + obtenerNumeroOp() + ": Cobro " + concepto + ": -" + cantidad + " €");
            System.out.println(">> Saldo actual: " + saldo + " €");
            dormir(tiempoEspera);
        }


    }

    private void dormir(int tiempo) {
        try {
            Thread.sleep(tiempo);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private static synchronized int obtenerNumeroOp() {
        return numeroOp++;
    }
}
class Transaccion extends Thread {
    private CuentaBancaria cuenta;
    private String operacion;
    private String concepto;
    private double cantidad;
    private int tiempoEspera;

    public Transaccion(CuentaBancaria cuenta, String operacion, String concepto, double cantidad, int tiempoEspera) {
        this.cuenta = cuenta;
        this.operacion = operacion;
        this.concepto = concepto;
        this.cantidad = cantidad;
        this.tiempoEspera = tiempoEspera;
    }

    @Override
    public void run() {
        if (this.operacion.equals("ingreso")) {
            cuenta.Ingresar(this.concepto, this.cantidad, this.tiempoEspera);
        } else if (this.operacion.equals("cobro")) {
            cuenta.Cobrar(this.concepto, this.cantidad, this.tiempoEspera);
        }
    }
}

public class SimuladorCuentaBancaria {
    public static void main(String[] args) {
        CuentaBancaria cuenta = new CuentaBancaria(4000);
        while (true) {
            Transaccion nominaThread = new Transaccion(cuenta, "ingreso", "nomina", 1200, 3000);
            Transaccion hipotecaThread = new Transaccion(cuenta, "cobro", "hipoteca", 400, 3000);
            Transaccion luzThread = new Transaccion(cuenta, "cobro", "luz", 40, 3000);
            Transaccion aguaThread = new Transaccion(cuenta, "cobro", "agua", 30, 3000);
            Transaccion comprasThread = new Transaccion(cuenta, "cobro", "compras", 50, 1000);
            Transaccion retiradaThread = new Transaccion(cuenta, "cobro", "retirada efectivo", 20, 300);

            nominaThread.start();
            hipotecaThread.start();
            luzThread.start();
            aguaThread.start();
            comprasThread.start();
            retiradaThread.start();
        }
    }
}

