import java.util.Scanner

var exit = false
private val rc = ReadClient()
private val inv = Inventory()
fun main() {
    inv.crearProducts()
    do {
        menu()
    } while (!exit)
    println("******* Bye! *******")

}

fun menu() {
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
        2 -> inv.showProduct("TOTAL LIST", inv.productsList.sortedBy { it.id })
        3 -> inv.showProductName(rc.pedirString("Enter the product name:"))
        4 -> inv.showProductType(inv.pedirType())
        5 -> inv.modifyProductPrice()
        6 -> inv.showProductPrice()
        7 -> inv.calculateType(null)
        8 -> inv.calculateType(inv.pedirType())
        9 -> inv.showProductsByType()
        0 -> exit = true
    }
}

open class Product(
    val id: Int,
    val name: String,
    val type: Type,
    var price: Double
)

enum class Type {
    BOOK, DVD, CD, SOFTWARE, PAINTING
}

class PrdPhysical(id: Int, name: String, type: Type, price: Double, private val weight: Int) :
    Product(id, name, type, price) {
    override fun toString(): String {
        return "$id; ${type.name}; $name; $price €; $weight g"
    }
}

class PrdDigital(id: Int, name: String, type: Type, price: Double, private val fsize: Int) :
    Product(id, name, type, price) {
    override fun toString(): String {
        return "$id; ${type.name}; $name; $price €; $fsize MB"
    }
}

class Inventory {
    private val order = { val str = "(" + Type.entries.joinToString("|") + ")"; str }
    private val typesString = { val str = "(" + Type.entries.joinToString("/") + ")"; str }
    private val typesProductRgx = Regex(order())
    val productsList = mutableListOf<Product>()

    fun addList() {
        val id = pedirID(false)
        val nombre = rc.pedirString("Enter the product name: ")
        val tipo = inv.pedirType()
        val precio = rc.pedirDoublePositivo("Enter the product price (in euros): ")
        if (tipo == Type.BOOK || tipo == Type.PAINTING) {
            val peso = rc.pedirIntPositivo("Enter the product weight (in grams): ")
            productsList.add(PrdPhysical(id, nombre, tipo, precio, peso))
        } else {
            val fsize = rc.pedirIntPositivo("Enter the product size (in MB): ")
            productsList.add(PrdDigital(id, nombre, tipo, precio, fsize))
        }
    }

    /**
     * @param exist true if you want that the ID exists in the list, false if you don't want
     */
    fun pedirID(exist: Boolean): Int {
        var id = rc.pedirIntPositivo("Enter the product ID: ")
        val boolID = { productsList.any { it.id == id } }
        if (exist) {
            while (!boolID()) {
                id = rc.pedirIntPositivo("A product with this ID no exists, please enter another one: ")
            }
        } else {
            while (boolID()) {
                id = rc.pedirIntPositivo("A product with this ID already exists, please enter another one: ")
            }
        }
        return id
    }

    fun pedirType(): Type {
        var type = rc.pedirString("Enter the product type ${typesString()}: ").uppercase()
        while (!type.matches(typesProductRgx)) {
            println("Error! Please enter a valid product type.")
            type = rc.pedirString("Enter the product type ${typesString()}: ").uppercase()
        }
        return Type.entries.find { it.name == type }!!
    }

    fun showProduct(msg: String, list: List<Product>) {
        if (list.isNotEmpty()) {
            println("######################## ${msg.uppercase()} ########################")
            list.forEach { println(it) }
            print("#########################")
            repeat(msg.length) { print("#") } // it's the same that: msg.forEach { print("#")}
            print("#########################")
        } else {
            println("Error! No products found.")
        }
        println("\n")
    }

    fun showProductName(name: String) {
        inv.showProduct(
            "PRODUCTS WITH NAME: $name",
            inv.productsList.filter { it.name.equals(name, true) }.sortedBy { it.id })
    }

    fun showProductType(type: Type) {
        inv.showProduct("PRODUCTS WITH TYPE: $type", inv.productsList.filter { it.type == type }.sortedBy { it.id })
    }

    fun modifyProductPrice() {
        val id = pedirID(true)
        val list = inv.productsList.filter { it.id == id }
        val precioProducto = list.first().price
        var precioAdd = rc.pedirDouble("Enter the price to ADD for the product (in euros): ")
        while (!(precioProducto + precioAdd > 0)){
            println("¡Error! The price cannot be negative or zero.")
            precioAdd = rc.pedirDouble("Enter the price to ADD for the product (in euros): ")
        }
        list.first().price += precioAdd
        println("The product price has been modified!\n")
    }

    fun showProductPrice() {
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
            1 -> inv.showProduct(
                "PRODUCTS PRICE GREATER THAN $precio",
                inv.productsList.filter { it.price >= precio }.sortedBy { it.price })

            2 -> inv.showProduct(
                "PRODUCTS PRICE LESS THAN $precio",
                inv.productsList.filter { it.price <= precio }.sortedByDescending { it.price })
        }
    }

    fun calculateType(type: Type?) {
        if (type == null) {
            println("The total value of the products is: ${inv.productsList.fold(0.0) { total, product -> total + product.price }} €\n")
        } else {
            println("The total value of products of type $type is: ${
                inv.productsList.filter { it.type == type }.fold(0.0) { total, product -> total + product.price }
            } €\n"
            )
        }
    }

    fun showProductsByType() {
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
            1 -> inv.showProduct("TOTAL LIST BY NAME ASCENDING", inv.productsList.sortedBy { it.name })
            2 -> inv.showProduct("TOTAL LIST BY TYPE ASCENDING", inv.productsList.sortedBy { it.type })
            3 -> inv.showProduct("TOTAL LIST BY PRICE ASCENDING", inv.productsList.sortedBy { it.price })
            4 -> inv.showProduct("TOTAL LIST BY ID ASCENDING", inv.productsList.sortedBy { it.id })
            5 -> inv.showProduct("TOTAL LIST BY NAME DESCENDING", inv.productsList.sortedByDescending { it.name })
            6 -> inv.showProduct("TOTAL LIST BY TYPE DESCENDING", inv.productsList.sortedByDescending { it.type })
            7 -> inv.showProduct("TOTAL LIST BY PRICE DESCENDING", inv.productsList.sortedByDescending { it.price })
            8 -> inv.showProduct("TOTAL LIST BY ID DESCENDING", inv.productsList.sortedByDescending { it.id })
            0 -> {}
        }
    }

    fun crearProducts() {
        productsList.add(PrdPhysical(1, "El Quijote", Type.BOOK, 25.54, 800))
        productsList.add(PrdPhysical(3, "Dogs and Cats", Type.PAINTING, 124.0, 300))
        productsList.add(PrdPhysical(6, "El Quijote", Type.BOOK, 552.50, 1500))
        productsList.add(PrdPhysical(7, "Pintura Picasso", Type.BOOK, 32.01, 700))

        productsList.add(PrdDigital(2, "Hamlet", Type.CD, 30.0, 15))
        productsList.add(PrdDigital(4, "MS Office", Type.SOFTWARE, 230.5, 1100))
        productsList.add(PrdDigital(5, "Queen", Type.DVD, 78.5, 3500))
        productsList.add(PrdDigital(2, "Hamlet", Type.DVD, 80.0, 2800))
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
                var input = pedirString(msg)
                input = input.replace(",", ".")
                return input.toDouble()
            } catch (e: NumberFormatException) {
                println("Error! Enter a valid decimal number.")
                print(msg)
            }
        }
    }

    fun pedirDoublePositivo(msg: String): Double {
        var num = pedirDouble(msg)
        while (num < 0) {
            println("¡Error! You have to enter a positive number.")
            num = pedirDouble(msg)
        }
        return num
    }
    fun PedirInt(msg: String): Int {
        while (true) {
            try {
                val input = pedirString(msg).trim()
                return input.toInt()
            } catch (e: NumberFormatException) {
                println("Error! Enter a valid integer.")
            }
        }
    }

    fun pedirIntPositivo(msg: String): Int {
        var num = PedirInt(msg)
        while (num < 0) {
            println("¡Error! You have to enter a positive number.")
            num = PedirInt(msg)
        }
        return num
    }

    fun pedirIntEnRango(msg: String, rangoInicio: Int, rangoFin: Int): Int {
        var valorInt = PedirInt(msg)
        while (valorInt !in rangoInicio..rangoFin) {
            println("Error! Enter an integer in the range ($rangoInicio, $rangoFin).")
            valorInt = PedirInt(msg)
        }
        return valorInt
    }
}