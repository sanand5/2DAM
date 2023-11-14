import java.util.*

class ReadClient() {

    fun pedirDouble(msg: String): Double {
        val scanner = Scanner(System.`in`)
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
        scanner.close()
    }

    fun pedirInt(msg: String): Int {
        val scanner = Scanner(System.`in`)
        println(msg)
        while (true) {
            try {
                val input = scanner.nextLine()
                return input.toInt()
            } catch (e: NumberFormatException) {
                println("¡Error! Ingrese un número entero válido.")
                println(msg)
            }
        }
        scanner.close()
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
        val scanner = Scanner(System.`in`)
        var userInput: String
        do {
            println(msg)
            userInput = scanner.nextLine().uppercase()
        } while (userInput.trim().isEmpty())  // Repite la solicitud mientras el string esté vacío
        scanner.close()
        return userInput
    }
}