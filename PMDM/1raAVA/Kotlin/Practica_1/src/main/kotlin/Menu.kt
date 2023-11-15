class Menu(private val rc: ReadClient = ReadClient()) {
    fun menu() {
        println(
            """
        0. Salir
        1. Añadir Producto
        2. Mostrar todo
        3. Mostrar por campo
        4. Filtrar
        5. Calcular
        """.trimIndent()
        )
        val option = rc.pedirIntEnRango("? ", 1, 5)
        println()
        when (option) {
            0 -> exit = true
            1 -> addList()
            2 -> mostrarproducto("TOTAL LISTA", productsList.sortedBy { it.id })
            3 -> menuPorCampo()
            4 -> menuFiltrar()
            5 -> menuCalcular()
        }
    }

    private fun menuPorCampo() {
        println("0. Salir")
        println("1. Mostrar por Nombre")
        println("2. Mostrar por Tipo")
        println("3. Mostrar por Precio")
        println("4. Mostrar por ID")
        var option = rc.pedirIntEnRango("? ", 0, 4)
        if (option != 0 && rc.pedirIntEnRango(
                "En que orden quieres mostrar los productos (0)Ascendente o (1)Descendente: ",
                0,
                1
            ) == 0
        ) {
            option += 4

        }
        when (option) {
            0 -> {}
            1 -> mostrarproducto("TOTAL LISTA POR NOMBRE ASCENDENTE", productsList.sortedBy { it.nombre })
            2 -> mostrarproducto("TOTAL LISTA POR TIPO ASCENDENTE", productsList.sortedBy { it.tipo })
            3 -> mostrarproducto("TOTAL LISTA POR PRECIO ASCENDENTE", productsList.sortedBy { it.precio })
            4 -> mostrarproducto("TOTAL LISTA POR ID ASCENDENTE", productsList.sortedBy { it.id })
            5 -> mostrarproducto("TOTAL LISTA POR NOMBRE DESCENDENTE", productsList.sortedByDescending { it.nombre })
            6 -> mostrarproducto("TOTAL LISTA POR TIPO DESCENDENTE", productsList.sortedByDescending { it.tipo })
            7 -> mostrarproducto("TOTAL LISTA POR PRECIO DESCENDENTE", productsList.sortedByDescending { it.precio })
            8 -> mostrarproducto("TOTAL LISTA POR ID DESCENDENTE", productsList.sortedByDescending { it.id })
        }
    }

    private fun menuFiltrar() {
        println("0. Salir")
        println("1. Filtrar por ID")
        println("2. Filtrar por Nombre")
        println("3. Filtrar por Tipo")
        println("4. Filtrar por Precio")
        val option = rc.pedirIntEnRango("? ", 0, 4)
        when (option) {
            0 -> {}
            1 -> {
                val id = rc.pedirInt("Dime el ID del producto: ")
                //TODO: gestionar que si la lista esta vacia
                mostrarproducto(
                    "PRODUCTO CON ID: $id ",
                    productsList.filter { it.id == id })
            }

            2 -> {
                val nombre = rc.pedirString("Dime el nombre del producto: ")
                mostrarproducto(
                    "PRODUCTOS CON NOMBRE: $nombre ",
                    productsList.filter { it.nombre == nombre })
            }

            3 -> {//TODO: comprobar tipo
                val tipo = rc.pedirString("Dime el tipo del producto: ")
                mostrarproducto(
                    "PRODUCTOS CON TIPO: $tipo ",
                    productsList.filter { it.tipo == tipo })
            }

            4 -> {
                println("0. Salir")
                println("1. Mayor que...")
                println("2. Menor de...")
                val rango = rc.pedirIntEnRango("? ", 0, 2)
                var precio = 0.0
                if (rango != 0) { precio = rc.pedirDouble("Dime el precio base: ") }
                when (rango) {
                    0 -> {}
                    1 -> mostrarproducto(
                        "PRODUCTOS PRECIO MAYOR QUE: $precio",
                        productsList.filter { it.precio > precio })

                    2 -> mostrarproducto(
                        "PRODUCTOS PRECIO MENOR QUE: $precio",
                        productsList.filter { it.precio < precio })
                }
            }
        }
    }

    private fun menuCalcular() {
        println("0. Salir")
        println("1. Sumatorio total")
        println("2. Sumatorio por tipo")
        val option = rc.pedirIntEnRango("? ", 0, 2)
        when (option) {
            0 -> {}
            1 -> println("El valor total de los productos es: ${productsList.fold(0.0) { total, product -> total + product.precio }} €")
            2 -> {
                productsList.groupBy { it.tipo }.forEach {
                    println("El total de los productos del tipo ${it.key} son: ${it.value.fold(0.0) { total, product -> total + product.precio }} €")
                }
            }
        }
    }

}