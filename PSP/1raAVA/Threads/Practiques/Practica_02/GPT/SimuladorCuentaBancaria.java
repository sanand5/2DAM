class CuentaBancaria {
    private int saldo;

    public CuentaBancaria(int saldoInicial) {
        this.saldo = saldoInicial;
    }

    public synchronized void realizarIngreso(String concepto, int cantidad, int tiempoEspera) {
        saldo += cantidad;
        System.out.println("Op. #" + obtenerNumeroOperacion() + ": Ingreso " + concepto + ": +" + cantidad + " €");
        System.out.println(">> Saldo actual: " + saldo + " €");
        dormir(tiempoEspera);
    }

    public synchronized void realizarCobro(String concepto, int cantidad, int tiempoEspera) {
        saldo -= cantidad;
        System.out.println("Op. #" + obtenerNumeroOperacion() + ": Cobro " + concepto + ": -" + cantidad + " €");
        System.out.println(">> Saldo actual: " + saldo + " €");
        dormir(tiempoEspera);
    }

    private void dormir(int tiempo) {
        try {
            Thread.sleep(tiempo);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static int numeroOperacion = 1;

    private static synchronized int obtenerNumeroOperacion() {
        return numeroOperacion++;
    }
}

class OperacionNomina extends Thread {
    private CuentaBancaria cuenta;

    public OperacionNomina(CuentaBancaria cuenta) {
        this.cuenta = cuenta;
    }

    @Override
    public void run() {
        cuenta.realizarIngreso("nómina", 1200, 3000);
    }
}

class OperacionHipoteca extends Thread {
    private CuentaBancaria cuenta;

    public OperacionHipoteca(CuentaBancaria cuenta) {
        this.cuenta = cuenta;
    }

    @Override
    public void run() {
        cuenta.realizarCobro("hipoteca", 400, 3000);
    }
}

class OperacionLuz extends Thread {
    private CuentaBancaria cuenta;

    public OperacionLuz(CuentaBancaria cuenta) {
        this.cuenta = cuenta;
    }

    @Override
    public void run() {
        cuenta.realizarCobro("luz", 40, 3000);
    }
}

class OperacionAgua extends Thread {
    private CuentaBancaria cuenta;

    public OperacionAgua(CuentaBancaria cuenta) {
        this.cuenta = cuenta;
    }

    @Override
    public void run() {
        cuenta.realizarCobro("agua", 30, 3000);
    }
}

class OperacionCompras extends Thread {
    private CuentaBancaria cuenta;

    public OperacionCompras(CuentaBancaria cuenta) {
        this.cuenta = cuenta;
    }

    @Override
    public void run() {
        cuenta.realizarCobro("compras", 50, 1000);
    }
}

class OperacionRetiradaEfectivo extends Thread {
    private CuentaBancaria cuenta;

    public OperacionRetiradaEfectivo(CuentaBancaria cuenta) {
        this.cuenta = cuenta;
    }

    @Override
    public void run() {
        cuenta.realizarCobro("retirada efectivo", 20, 300);
    }
}

public class SimuladorCuentaBancaria {
    public static void main(String[] args) {
        CuentaBancaria cuenta = new CuentaBancaria(4000);

        OperacionNomina nominaThread = new OperacionNomina(cuenta);
        OperacionHipoteca hipotecaThread = new OperacionHipoteca(cuenta);
        OperacionLuz luzThread = new OperacionLuz(cuenta);
        OperacionAgua aguaThread = new OperacionAgua(cuenta);
        OperacionCompras comprasThread = new OperacionCompras(cuenta);
        OperacionRetiradaEfectivo retiradaThread = new OperacionRetiradaEfectivo(cuenta);

        for (int i = 0; i < 10; i++) {
            nominaThread.start();
            hipotecaThread.start();
            luzThread.start();
            aguaThread.start();
            comprasThread.start();
            retiradaThread.start();
        }
    }
}
