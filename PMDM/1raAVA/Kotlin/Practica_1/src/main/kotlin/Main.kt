fun addList() {
    val rc = ReadClient()
    val forma = rc.pedirIntEnRango("Tell me if it's a physical product (0) or digital (1):", 0, 1)
    var id = rc.pedirInt("Enter the product ID:")
    /*var idRep = false
    productsList.forEach(){if (it.id == id){ idRep = true; return@forEach} }*/
    while (productsList.any {it.id == id}){
        id = rc.pedirInt("A product with this ID already exists, please enter another one:")
    }
    val nombre = rc.pedirString("Enter the product name:")
    var tipo = rc.pedirString("Enter the product type $tiposString:").uppercase()
    while (!tipo.matches(tiposProductoRgx)) {
        println("Error! Please enter a valid product type.")
        tipo = rc.pedirString("Enter the product type $tiposString:").uppercase()
    }
    //val objTipo = Tipo.values().find{it.name == tipo}
    val precio = rc.pedirDouble("Enter the product price (in euros):")
    if (forma == 0) {
        val peso = rc.pedirDouble("Enter the product weight (in grams):")
        val producto = PrdFisico(id, nombre, tipo, precio, peso)
        productsList.add(producto)
    } else {
        val fsize = rc.pedirDouble("Enter the product size (in MB):")
        val producto = PrdDigital(id, nombre, tipo, precio, fsize)
        productsList.add(producto)
    }
}

fun mostrarproducto(msg: String, list: List<Producto>) {
    if (list.isNotEmpty()) {
        println("######################## $msg ######################## ")
        list.forEach { println(it) }
    } else {
        println("Error! No products found.")
    }
    print("#########################")
    repeat(msg.length) { print("#") } /* msg.forEach { print("#")}  */
    print("#########################")
    println()
}
//todo ENUM CLASS
val tiposProductos = arrayOf("LIBROS", "CD", "PINTURA", "SOFTWARE", "DVD")
val orden = {
    var tipos = "("
    tiposProductos.forEach { tipos += "$it|" }
    tipos.removeSuffix("|") // Elimina el último "|"
    tipos += ")"
    tipos
}
val tiposString = {
    Tipo.values().joinToString("/")
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
    /*productsList.add(PrdFisico(1, "Libro de Ciencia", "LIBROS", 20.0, 500.0))
    productsList.add(PrdFisico(2, "CD de Música", "CD", 15.0, 150.0))
    productsList.add(PrdFisico(3, "Pintura Abstracta", "PINTURA", 50.0, 2.5))

    productsList.add(PrdDigital(4, "Software de Edición", "SOFTWARE", 30.0, 500.0))
    productsList.add(PrdDigital(5, "Película en HD", "DVD", 10.0, 4.2))
    productsList.add(PrdDigital(6, "Libro Digital", "LIBROS", 15.0, 2.0))
    productsList.add(PrdFisico(7, "Pintura Abstracta", "LIBROS", 50.0, 2.5))*/
}