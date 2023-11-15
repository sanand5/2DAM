import java.util.*

class ReadClient(private val scanner: Scanner = Scanner(System.`in`)) {

    fun pedirString(msg: String): String {
        var userInput: String
        do {
            print(msg)
            userInput = scanner.nextLine()
            userInput = userInput.uppercase()
        } while (userInput.trim().isEmpty())  // Repite la solicitud mientras el string esté vacío
        return userInput
    }

    fun pedirDouble(msg: String): Double {
        while (true) {
            try {
                val input = pedirString(msg)
                return input.toDouble()
            } catch (e: NumberFormatException) {
                println("¡Error! Ingrese un número decimal válido.")
                print(msg)
            }
        }
    }

    fun pedirInt(msg: String): Int {
        while (true) {
            try {
                val input = pedirString(msg)
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
}
