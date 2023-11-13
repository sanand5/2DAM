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
        return scanner.nextLine()
    }

    // Cerrar el Scanner al finalizar
    fun cerrarScanner() {
        scanner.close()
    }
}

open class Producto(
    val id: Int,
    var nombre: String,
    var tipo: String,
    var precio: Double
)

class PrdFisico(id: Int, nombre: String, tipo: String, precio: Double, var peso: Double) : Producto(id, nombre, tipo, precio){
    val formato = "FISICO"
    override fun toString(): String {
        return "$id; $formato; $nombre; $tipo; $peso g; $precio €"
    }
}

class PrdDigital(id: Int, nombre: String, tipo: String, precio: Double, var fsize: Double) : Producto(id, nombre, tipo, precio){
    val formato = "DIGITAL"
    override fun toString(): String {
        return "$id; $formato; $nombre; $tipo; $fsize MB; $precio €"
    }
}


// Crear un mapa de productos i trabajar desde ahí
fun addList() {
    val rc = ReadClient()
    val forma = rc.pedirIntEnRango("Dime si es un producto fisico(0) o digital(1): ", 0, 1)
    val id = rc.pedirInt("Ingrese el id del producto: ")
    val nombre = rc.pedirString("Ingrese el nombre del producto: ")

    var tipo = rc.pedirString("Ingrese el tipo de producto (LIBROS/CD/PINTURA/SOFTWARE/DVD): ").uppercase()
    while (!tipo.matches(tiposProductoRgx)){
        println("¡Error! Ingrese un tipo de producto válido.")
        tipo = rc.pedirString("Ingrese el tipo de producto (LIBROS/CD/PINTURA/SOFTWARE/DVD): ").uppercase()
    }

    val precio = rc.pedirDouble("Ingrese el precio del producto (en euros): ")

    if (forma == 0) {
        val peso = rc.pedirDouble("Ingrese el peso del producto (en gramos): ")
        val producto = PrdFisico(id, nombre, tipo, precio, peso)
        productsList?.add(producto)
    } else {
        val fsize = rc.pedirDouble("Ingrese el tamaño del producto (en MB): ")
        val producto = PrdDigital(id, nombre, tipo, precio, fsize)
        productsList?.add(producto)
    }
}

fun OrdenarProductos(): List<Producto> {
    return productsList.sortedBy { it.id }
}
fun mostrarProductos(){
    val list = OrdenarProductos()
    list.forEach { println(it) }

}
fun mostrarProductosFiltro(tipo: String): Unit {
    if (tipo.matches(tiposProductoRgx)){

    }else{
        println("¡Error! Ingrese un tipo de producto válido.")
    }

}
//TODO: comprobar que esto funciona
val tiposProductos = arrayOf("LIBROS", "CD", "PINTURA", "SOFTWARE", "DVD")
val orden = {
    var tipos = ""
    tiposProductos.forEach { tipos += it + "|" }
    tipos.removeSuffix("|") // Elimina el último "|"
}
val tiposProductoRgx = Regex("($orden())")

val tipoFisico = "FISICO"
val tipoDigital = "DIGITAL"
val productsList = mutableListOf<Producto>()
fun main(args: Array<String>) {
    for (i in 1..5) {
        val productoFisico = PrdFisico(i, "ProductoFisico$i", tiposProductos[ (Math.random() * tiposProductos.size).toInt() ],  10.0 * i,  100.0 * i)
        productsList.add(productoFisico)

        val productoDigital = PrdDigital(i + 5, "ProductoDigital$i", tipoDigital, 5.0 * i, 50.0 * i)
        productsList.add(productoDigital)
    }
    println(tiposProductoRgx.toString())
    mostrarProductos()
}
