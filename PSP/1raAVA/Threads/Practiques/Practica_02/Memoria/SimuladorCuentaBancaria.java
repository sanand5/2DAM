import java.util.Scanner;

class CuentaBancaria {
    private int saldo;
    private static int numeroOp = 1;
    
    public CuentaBancaria(int saldoInicial) {
        this.saldo = saldoInicial;
    }
    private static synchronized int obtenerNumeroOp() {
        return numeroOp++;
    }
//TODO: metodos ingresar, cobrar, con minuscula
    public synchronized void Ingresar(String concepto, int cantidad, int tiempoEspera) {
        saldo += cantidad;
        System.out.println("Op. #" + obtenerNumeroOp() + ": Ingreso " + concepto + ": +" + cantidad + " €");
        System.out.println(">> Saldo actual: " + saldo + " €");
        dormir(tiempoEspera);
    }

    public synchronized void Cobrar(String concepto, int cantidad, int tiempoEspera) {
        saldo -= cantidad;
        System.out.println("Op. #" + obtenerNumeroOp() + ": Cobro " + concepto + ": -" + cantidad + " €");
        System.out.println(">> Saldo actual: " + saldo + " €");
        dormir(tiempoEspera);
    }

    private void dormir(int tiempo) {
        try {
            Thread.sleep(tiempo);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

}

class OpNomina extends Thread {
    private CuentaBancaria cuenta;

    public OpNomina(CuentaBancaria cuenta) {
        this.cuenta = cuenta;
    }

    @Override
    public void run() {
        cuenta.Ingresar("nómina", 1200, 3000);
    }
}

class OpHipoteca extends Thread {
    private CuentaBancaria cuenta;

    public OpHipoteca(CuentaBancaria cuenta) {
        this.cuenta = cuenta;
    }

    @Override
    public void run() {
        cuenta.Cobrar("hipoteca", 400, 3000);
    }
}



class OpLuz extends Thread {
    private CuentaBancaria cuenta;

    public OpLuz(CuentaBancaria cuenta) {
        this.cuenta = cuenta;
    }

    @Override
    public void run() {
        cuenta.Cobrar("luz", 40, 3000);
    }
}

class OpAgua extends Thread {
    private CuentaBancaria cuenta;

    public OpAgua(CuentaBancaria cuenta) {
        this.cuenta = cuenta;
    }

    @Override
    public void run() {
        cuenta.Cobrar("agua", 30, 3000);
    }
}

class OpCompras extends Thread {
    private CuentaBancaria cuenta;

    public OpCompras(CuentaBancaria cuenta) {
        this.cuenta = cuenta;
    }

    @Override
    public void run() {
        cuenta.Cobrar("compras", 50, 1000);
    }
}

class OpRetiradaEfectivo extends Thread {
    private CuentaBancaria cuenta;

    public OpRetiradaEfectivo(CuentaBancaria cuenta) {
        this.cuenta = cuenta;
    }

    @Override
    public void run() {
        cuenta.Cobrar("retirada efectivo", 20, 300);
    }
}

/**
 * Esta clase se encarga de finalizar el programa.
 */
class Salir extends Thread {
    @Override
    public void run() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            String input = sc.nextLine();
            if (input.equalsIgnoreCase("c")) {
                //En principio el system exit no lo debemos gastar, la otra manera sería esperar a que los hilos en ejecución terminen o finalizar cada hilo que quede
                System.exit(0);
            }
        }
    }
}

public class SimuladorCuentaBancaria {
    public static void main(String[] args) {
        CuentaBancaria cuenta = new CuentaBancaria(4000);
        Salir salirThread = new Salir();
        salirThread.start();
        while (true) {
            OpNomina nominaThread = new OpNomina(cuenta);
            OpHipoteca hipotecaThread = new OpHipoteca(cuenta);
            OpLuz luzThread = new OpLuz(cuenta);
            OpAgua aguaThread = new OpAgua(cuenta);
            OpCompras comprasThread = new OpCompras(cuenta);
            OpRetiradaEfectivo retiradaThread = new OpRetiradaEfectivo(cuenta);

            nominaThread.start();
            hipotecaThread.start();
            luzThread.start();
            aguaThread.start();
            comprasThread.start();
            retiradaThread.start();

        }
    }
}

