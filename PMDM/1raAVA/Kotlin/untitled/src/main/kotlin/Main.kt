import java.util.Scanner

private var exit = false
private val rc = ReadClient()
private val inv = Inventory()
fun main() {
    inv.crearProducts()
    do {
        menu()
    } while (!exit)
    println("******* Bye! *******")

}

private fun menu() {
    println(
        """
            1. Add a new product
            2. Show ALL the products details
            3. Show details of a given product name
            4. Show the details of a given product typo of products
            5. Modify the price of a product
            6. Show products above/below a given price 
            7. Calculate total SUM of all products
            8. Calculate SUM of a given type of products
            9. Sort the list of products by ...
            0. EXIT
        """.trimIndent()
    )
    val option = rc.pedirIntEnRango("? ", 0, 9)
    println()
    when (option) {
        1 -> inv.addList()
        2 -> inv.mostrarproduct("TOTAL LIST", inv.productsList.sortedBy { it.id })
        3 -> {
            val nombre = rc.pedirString("Enter the product name:")
            val list = inv.productsList.filter { it.name.equals(nombre, true)/*contains*/ }
            if (list.isNotEmpty()) {
                inv.mostrarproduct("PRODUCTS WITH NAME: $nombre", list.sortedBy { it.id })
            } else {
                println("No products found with that name!")
            }
        }

        4 -> {
            val tipo = inv.pedirType()
            inv.mostrarproduct(
                "PRODUCTS WITH TYPE: $tipo",
                inv.productsList.filter { it.type == tipo }.sortedBy { it.id })
        }

        5 -> {
            val id = rc.pedirInt("Enter the product ID: ")
            inv.productsList.filter { it.id == id }[0].price += rc.pedirDouble("Enter the price to ADD for the product (in euros): ")
            println("The product price has been modified!")
        }

        6 -> {
            println("0. EXIT")
            println("1. Greater than...")
            println("2. Less than...")
            val rango = rc.pedirIntEnRango("? ", 0, 2)
            var precio = 0.0
            if (rango != 0) {
                precio = rc.pedirDouble("Enter the base price:")
            }
            when (rango) {
                0 -> {}
                1 -> inv.mostrarproduct(
                    "PRODUCTS PRICE GREATER THAN $precio",
                    inv.productsList.filter { it.price >= precio }.sortedBy { it.price })

                2 -> inv.mostrarproduct(
                    "PRODUCTS PRICE LESS THAN $precio",
                    inv.productsList.filter { it.price <= precio }.sortedByDescending { it.price })
            }
        }

        7 -> println("The total value of the products is: ${inv.productsList.fold(0.0) { total, product -> total + product.price }} €\n")
        8 -> {
            val tipo = inv.pedirType()
            println("The total value of products of type $tipo is: ${
                inv.productsList
                    .filter { it.type == tipo }
                    .fold(0.0) { total, product -> total + product.price }
            } €\n"
            )
        }

        9 -> menuPorCampo()
        0 -> exit = true
    }
}

private fun menuPorCampo() {
    println("1. Show by Name")
    println("2. Show by Type")
    println("3. Show by Price")
    println("4. Show by ID")
    println("0. EXIT")
    var option = rc.pedirIntEnRango("? ", 0, 4)
    if (option != 0 && rc.pedirIntEnRango(
            "In which order do you want to display the products? (0) Ascending or (1) Descending:",
            0,
            1
        ) == 0
    ) {
        option += 4
    }
    when (option) {
        1 -> inv.mostrarproduct("TOTAL LIST BY NAME ASCENDING", inv.productsList.sortedBy { it.name })
        2 -> inv.mostrarproduct("TOTAL LIST BY TYPE ASCENDING", inv.productsList.sortedBy { it.type })
        3 -> inv.mostrarproduct("TOTAL LIST BY PRICE ASCENDING", inv.productsList.sortedBy { it.price })
        4 -> inv.mostrarproduct("TOTAL LIST BY ID ASCENDING", inv.productsList.sortedBy { it.id })
        5 -> inv.mostrarproduct("TOTAL LIST BY NAME DESCENDING", inv.productsList.sortedByDescending { it.name })
        6 -> inv.mostrarproduct("TOTAL LIST BY TYPE DESCENDING", inv.productsList.sortedByDescending { it.type })
        7 -> inv.mostrarproduct("TOTAL LIST BY PRICE DESCENDING", inv.productsList.sortedByDescending { it.price })
        8 -> inv.mostrarproduct("TOTAL LIST BY ID DESCENDING", inv.productsList.sortedByDescending { it.id })
        0 -> {}
    }
}

open class Product(val id: Int, val name: String, val type: Type, var price: Double)

enum class Type { BOOK, DVD, CD, SOFTWARE, PAINTING }

class PrdPhysical(
    id: Int,
    name: String,
    type: Type,
    prive: Double,
    val weight: Double,
    val format: String = "PHYSICAL"
) :
    Product(id, name, type, prive) {
    override fun toString(): String {
        return "$id; ${type.name}; $name; $price €; $weight g"
    }
}

class PrdDigital(id: Int, name: String, type: Type, price: Double, val fsize: Double, val format: String = "DIGITAL") :
    Product(id, name, type, price) {
    override fun toString(): String {
        return "$id; ${type.name}; $name; $price €; $fsize MB"
    }
}

class Inventory(private val rc: ReadClient = ReadClient()) {
    val order = { val str = "(" + Type.entries.joinToString("|") + ")"; str }
    val typesString = { val str = "(" + Type.entries.joinToString("/") + ")"; str }
    val typesProductRgx = Regex(order())
    val productsList = mutableListOf<Product>()

    fun addList() {
        var id = rc.pedirInt("Enter the product ID: ")
        /*
        var idRep = false
        productsList.forEach(){if (it.id == id){ idRep = true; return@forEach} }
        */
        while (productsList.any { it.id == id }) {
            id = rc.pedirInt("A product with this ID already exists, please enter another one:")
        }
        val nombre = rc.pedirString("Enter the product name: ")
        val tipo = pedirType()
        val precio = rc.pedirDouble("Enter the product price (in euros): ")
        if (tipo == Type.BOOK || tipo == Type.PAINTING) {
            val peso = rc.pedirDouble("Enter the product weight (in grams): ")
            val producto = PrdPhysical(id, nombre, tipo, precio, peso)
            productsList.add(producto)
        } else {
            val fsize = rc.pedirDouble("Enter the product size (in MB): ")
            val producto = PrdDigital(id, nombre, tipo, precio, fsize)
            productsList.add(producto)
        }
    }

    fun pedirType(): Type {
        var type = rc.pedirString("Enter the product type ${typesString()}: ").uppercase()
        while (!type.matches(typesProductRgx)) {
            println("Error! Please enter a valid product type.")
            type = rc.pedirString("Enter the product type ${typesString()}: ").uppercase()
        }
        return Type.entries.find { it.name == type }!!
    }

    fun mostrarproduct(msg: String, list: List<Product>) {
        if (list.isNotEmpty()) {
            println("######################## ${msg.uppercase()} ########################")
            list.forEach { println(it) }
        } else {
            println("Error! No products found.")
        }
        print("#########################")
        repeat(msg.length) { print("#") } // it the same that: msg.forEach { print("#")}
        print("#########################")
        println("\n")
    }

    fun crearProducts() {
        productsList.add(PrdPhysical(1, "Libro de Ciencia", Type.BOOK, 20.0, 500.0))
        productsList.add(PrdPhysical(3, "Pintura Abstracta", Type.PAINTING, 50.0, 2.5))
        productsList.add(PrdPhysical(6, "Libro de Filosofía", Type.BOOK, 15.0, 2.0))
        productsList.add(PrdPhysical(7, "Pintura Picasso", Type.PAINTING, 50.0, 2.5))

        productsList.add(PrdDigital(2, "CD de Música", Type.CD, 15.0, 150.0))
        productsList.add(PrdDigital(4, "Software de Edición", Type.SOFTWARE, 30.0, 500.0))
        productsList.add(PrdDigital(5, "Película en HD", Type.DVD, 10.0, 4.2))
    }
}

class ReadClient(private val scanner: Scanner = Scanner(System.`in`)) {
    fun pedirString(msg: String): String {
        var userInput: String
        do {
            print(msg)
            userInput = scanner.nextLine()
        } while (userInput.trim().isEmpty())
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
                val input = pedirString(msg).trim()
                return input.toInt()
            } catch (e: NumberFormatException) {
                println("¡Error! Ingrese un número entero válido.")
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