class Menu(private val rc: ReadClient = ReadClient()) {
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
        val option = rc.pedirIntEnRango("? ", 1, 5)
        println()
        when (option) {
            1 -> addList()
            2 -> mostrarproducto("TOTAL LIST", productsList.sortedBy { it.id })
            3 -> {
                val nombre = rc.pedirString("Enter the product name:")
                val list = productsList.filter { it.nombre == nombre }
                if (list.isNotEmpty()) {
                    mostrarproducto(
                        "PRODUCTS WITH NAME: $nombre",
                        list
                    )
                }else{
                    println("No products found with that name!")
                }
            }
            4 -> {
                val tipo = pedirTipo()
                mostrarproducto(
                    "PRODUCTS WITH TYPE: $tipo",
                    productsList.filter { it.tipo.toString() == tipo })
            }
            5 -> productsList.filter {
                it.id == rc.pedirInt("Enter the product ID:")
            }.get(0).precio += rc.pedirDouble("Enter the price to ADD for the product (in euros):")
            6 -> {
                println("0. EXIT")
                println("1. Greater than...")
                println("2. Less than...")
                val rango = rc.pedirIntEnRango("? ", 0, 2)
                var precio = 0.0
                if (rango != 0) { precio = rc.pedirDouble("Enter the base price:") }
                when (rango) {
                    0 -> {}
                    1 -> mostrarproducto(
                        "PRODUCTS PRICE GREATER THAN $precio",
                        productsList.filter { it.precio >= precio })

                    2 -> mostrarproducto(
                        "PRODUCTS PRICE LESS THAN $precio",
                        productsList.filter { it.precio <= precio })
                }
            }
            7 -> println("The total value of the products is: ${productsList.fold(0.0) { total, product -> total + product.precio }} €")
            8 -> {
                val tipo = pedirTipo()
                println("The total value of products of type $tipo is: ${productsList
                    .filter { it.tipo.toString() == tipo }
                    .fold(0.0) { total, product -> total + product.precio }} €")
            }
            9 -> menuPorCampo()
            0 -> exit = true
        }
    }
    private fun menuPorCampo() {
        println("0. EXIT")
        println("1. Show by Name")
        println("2. Show by Type")
        println("3. Show by Price")
        println("4. Show by ID")
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
            0 -> {}
            1 -> mostrarproducto("TOTAL LIST BY NAME ASCENDING", productsList.sortedBy { it.nombre })
            2 -> mostrarproducto("TOTAL LIST BY TYPE ASCENDING", productsList.sortedBy { it.tipo })
            3 -> mostrarproducto("TOTAL LIST BY PRICE ASCENDING", productsList.sortedBy { it.precio })
            4 -> mostrarproducto("TOTAL LIST BY ID ASCENDING", productsList.sortedBy { it.id })
            5 -> mostrarproducto("TOTAL LIST BY NAME DESCENDING", productsList.sortedByDescending { it.nombre })
            6 -> mostrarproducto("TOTAL LIST BY TYPE DESCENDING", productsList.sortedByDescending { it.tipo })
            7 -> mostrarproducto("TOTAL LIST BY PRICE DESCENDING", productsList.sortedByDescending { it.precio })
            8 -> mostrarproducto("TOTAL LIST BY ID DESCENDING", productsList.sortedByDescending { it.id })
        }
    }

    private fun pedirTipo(): String {
        var tipo = rc.pedirString("Enter the product type: $tiposString: ")
        while (!tipo.matches(tiposProductoRgx)){
            println("Error! Please enter a valid product type.")
            tipo = rc.pedirString("Enter the product type: $tiposString: ")
        }
        return tipo
    }

}