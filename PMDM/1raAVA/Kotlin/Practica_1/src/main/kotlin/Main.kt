import java.util.*


open class Producto(
    val id: Int,
    var nombre: String,
    var tipo: String,
    var precio: Double
)

class PrdFisico(id: Int, nombre: String, tipo: String, precio: Double, var peso: Double) :
    Producto(id, nombre, tipo, precio) {
    val formato = "FISICO"
    override fun toString(): String {
        return "$id; $formato; $nombre; $tipo; $peso g; $precio €"
    }
}

class PrdDigital(id: Int, nombre: String, tipo: String, precio: Double, var fsize: Double) :
    Producto(id, nombre, tipo, precio) {
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
    while (!tipo.matches(tiposProductoRgx)) {
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

fun mostrarProductos() {
    val list = OrdenarProductos()
    list.forEach { println(it) }

}

fun comprobarCampo(campo: String): Boolean {
    var retorno = false
    when (campo) {
        "ID" -> {
            retorno = true
        }

        "NOMBRE" -> {
            retorno = true
        }

        "TIPO" -> {
            retorno = true
        }

        "PRECIO" -> {
            retorno = true
        }

        "PESO" -> {
            retorno = true
        }

        "FSIZE" -> {
            retorno = true
        }
    }
    return retorno
}

fun filtrarProducots(campo: String, valor: String): List<Producto> {
    return when (campo) {
        "ID" -> productsList.filter { it.id.toString() == valor }
        "NOMBRE" -> productsList.filter { it.nombre == valor }
        "TIPO" -> productsList.filter { it.tipo == valor }
        "PRECIO" -> {
            //TODO: hacer que puedan ser . o ,
            productsList.filter { it.precio.toString() == valor }
        }
        "PESO" -> productsList.filter { it is PrdFisico && it.peso.toString() == valor }
        "FSIZE" -> productsList.filter { it is PrdDigital && it.fsize.toString() == valor }
        else -> {
            //TODO>plantear la opcion de que devuelva el mismo array para quitarme la funcion  mostrarProductos()
            println("¡Error! Ingrese un campo válido.")
            emptyList()
        }
    }
}
fun mostrarproducto(list: List<Producto>): Unit {
    list.forEach { println(it) }
}


fun mostrarProductosFiltro(tipo: String): Unit {
    if (tipo.matches(tiposProductoRgx)) {

    } else {
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
    //Crear y Mostrar productos
    crearProductos()
    mostrarProductos()
    // Mostrar detalles de producto
    mostrarproducto(filtrarProducots("TIPO", "DVD"))
    //TODO: filtyrar productos crec que no va bé
}

fun crearProductos(): Unit {
    for (i in 1..5) {
        val productoFisico = PrdFisico(
            i+0,
            "ProductoFisico$i",
            tiposProductos[(Math.random() * tiposProductos.size).toInt()]+"",
            10.0 * i,
            100.0 * i
        )
        productsList.add(productoFisico)
        val productoDigital = PrdDigital(
            i + 5,
            "ProductoDigital$i",
            tiposProductos[(Math.random() * tiposProductos.size).toInt()]+"",
            5.0 * i,
            50.0 * i
        )
        productsList.add(productoDigital)
    }
}