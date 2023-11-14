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
        productsList.add(producto)
    } else {
        val fsize = rc.pedirDouble("Ingrese el tamaño del producto (en MB): ")
        val producto = PrdDigital(id, nombre, tipo, precio, fsize)
        productsList.add(producto)
    }
}

fun OrdenarProductos(): List<Producto> {
    return productsList.sortedBy { it.id }
}

fun mostrarproducto(msg: String, list: List<Producto>): Unit {
    println("################ $msg #################")
    if (list.isNotEmpty()) {
        list.forEach { println(it) }
    } else {
        println("¡Error! No se han encontrado productos")
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
val productsList = mutableListOf<Producto>()


fun main(args: Array<String>) {
    //Crear y Mostrar productos
    crearProductos()
    do {
        Menu().menu()
    }while (true)

}


fun crearProductos(): Unit {
    productsList.add(PrdFisico(1, "Libro de Ciencia", "LIBROS", 20.0, 500.0))
    productsList.add(PrdFisico(2, "CD de Música", "CD", 15.0, 150.0))
    productsList.add(PrdFisico(3, "Pintura Abstracta", "PINTURA", 50.0, 2.5))

    productsList.add(PrdDigital(4, "Software de Edición", "SOFTWARE", 30.0, 500.0))
    productsList.add(PrdDigital(5, "Película en HD", "DVD", 10.0, 4.2))
    productsList.add(PrdDigital(6, "Libro Digital", "LIBROS", 15.0, 2.0))
    productsList.add(PrdFisico(7, "Pintura Abstracta", "LIBROS", 50.0, 2.5))
}