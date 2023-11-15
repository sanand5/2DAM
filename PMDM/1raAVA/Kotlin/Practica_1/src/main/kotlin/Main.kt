// Crear un mapa de productos y trabajar desde ahí
fun addList() {
    val rc = ReadClient()
    val forma = rc.pedirIntEnRango("Dime si es un producto físico(0) o digital(1): ", 0, 1)
    var id = rc.pedirInt("Ingrese el id del producto: ")
    /*var idRep = false
    productsList.forEach(){if (it.id == id){ idRep = true; return@forEach} }*/
    while (productsList.any {it.id == id}){
        id = rc.pedirInt("Ya existe un producto con este id, ingrese otro: ")
    }
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

fun mostrarproducto(msg: String, list: List<Producto>) {
    if (list.isNotEmpty()) {
        println("######################## $msg ######################## ")
        list.forEach { println(it) }
    } else {
        println("¡Error! No se han encontrado productos")
    }
    print("#########################")
    repeat(msg.length) { print("#") } /* msg.forEach { print("#")}  */
    print("#########################")
    println()
}

//TODO: esto no funciona
val tiposProductos = arrayOf("LIBROS", "CD", "PINTURA", "SOFTWARE", "DVD")
val orden = {
    var tipos = "("
    tiposProductos.forEach { tipos += "$it|" }
    tipos.removeSuffix("|") // Elimina el último "|"
    tipos += ")"
    tipos
}
val tiposProductoRgx = Regex(orden())
val productsList = mutableListOf<Producto>()
var exit = false

fun main() {
    crearProductos()
    do {
       Menu().menu()
    }while (!exit)

}

fun crearProductos() {
    productsList.add(PrdFisico(1, "Libro de Ciencia", "LIBROS", 20.0, 500.0))
    productsList.add(PrdFisico(2, "CD de Música", "CD", 15.0, 150.0))
    productsList.add(PrdFisico(3, "Pintura Abstracta", "PINTURA", 50.0, 2.5))

    productsList.add(PrdDigital(4, "Software de Edición", "SOFTWARE", 30.0, 500.0))
    productsList.add(PrdDigital(5, "Película en HD", "DVD", 10.0, 4.2))
    productsList.add(PrdDigital(6, "Libro Digital", "LIBROS", 15.0, 2.0))
    productsList.add(PrdFisico(7, "Pintura Abstracta", "LIBROS", 50.0, 2.5))
}