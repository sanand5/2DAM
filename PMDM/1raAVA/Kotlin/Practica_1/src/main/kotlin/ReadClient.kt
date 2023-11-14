import java.util.*

class ReadClient(private val scanner: Scanner = Scanner(System.`in`)) {

    fun pedirDouble(msg: String): Double {
        print(msg)
        while (true) {
            try {
                val input = scanner.nextLine()
                return input.toDouble()
            } catch (e: NumberFormatException) {
                println("¡Error! Ingrese un número decimal válido.")
                print(msg)
            }
        }
    }

    fun pedirInt(msg: String): Int {
        print(msg)
        while (true) {
            try {
                val input = scanner.nextLine()
                return input.toInt()
            } catch (e: NumberFormatException) {
                println("¡Error! Ingrese un número entero válido.")
                print(msg)
            }
        }
    }

    fun pedirIntEnRango(msg: String, rangoInicio: Int, rangoFin: Int): Int {
        var valorInt = pedirInt(msg)
        while (valorInt !in rangoInicio..rangoFin) {
            println("¡Error! Ingrese un número entero en el rango ($rangoInicio, $rangoFin).")
            valorInt = pedirInt(msg)
        }
        return valorInt
    }

    fun pedirString(msg: String): String {
        print(msg)
        return scanner.nextLine().uppercase()
    }

    // Cerrar el Scanner al finalizar
    fun cerrarScanner() {
        scanner.close()
    }
}