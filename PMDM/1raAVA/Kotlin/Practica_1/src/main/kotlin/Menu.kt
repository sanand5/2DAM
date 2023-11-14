class Menu(private val rc : ReadClient = ReadClient()) {
    fun menu(): Unit {
        println("""
        1. Añadir Producto
        2. Mostrar todo
        3. Mostrar por campo
        4. Filtrar
        5. Calcular
        """.trimIndent())
        val opcion = rc.pedirIntEnRango("Dame un valor entre 1-5",1,5)
        when (opcion) {
            1 -> addList()
            2 -> mostrarproducto("TOTAL LISTA",OrdenarProductos())
            3 -> menuPorCampo()
            4 -> menuFiltrar()
            5 -> menuCalcular()
        }
    }
    private fun menuPorCampo(): Unit {
        println("1. Mostrar por Nombre")
        println("2. Mostrar por Tipo")
        println("3. Mostrar por Precio")
        println("4. Mostrar por ID")
        val opcion = rc.pedirIntEnRango("Dame un valor entre 1-4",1,5)
        when (opcion) {
            1 -> mostrarproducto("TOTAL LISTA POR NOMBRE", productsList.sortedBy { it.nombre })
            2 -> mostrarproducto("TOTAL LISTA POR TIPO", productsList.sortedBy { it.tipo })
            3 -> mostrarproducto("TOTAL LISTA POR PRECIO", productsList.sortedBy { it.precio })
            4 -> mostrarproducto("TOTAL LISTA POR ID", productsList.sortedBy { it.id })
        }
    }

    private fun menuFiltrar(): Unit {
        println("1. Filtrar por ID")
        println("2. Filtrar por Nombre")
        println("3. Filtrar por Tipo")
        println("4. Filtrar por Precio")
        val opcion = rc.pedirIntEnRango("Dame un valor entre 1-4",1,5)
        when (opcion) {
            1 -> {
                val id = rc.pedirString("Dime el ID del producto: ")
                //TODO: gestionar que si la lista esta vacia
                mostrarproducto(
                    "PRODUCTO CON ID: $id ",
                    productsList.filter { it.id.toString() == id})
            }
            2 -> {
                val nombre = rc.pedirString("Dime el nombre del producto: ")
                mostrarproducto(
                    "PRODUCTOS CON NOMBRE ",
                    productsList.filter { it.nombre == nombre })
            }
            3 -> mostrarproducto(productsList.filter { it.tipo == rc.pedirString("Dime el tipo del producto: ") })
            4 -> {
                println("1. Mayor que...")
                println("2. Menor de...")
                val rango = rc.pedirIntEnRango("Dame un valor entre 1-2",1,2)
                when (rango) {
                    1 -> mostrarproducto(productsList.filter { it.precio > rc.pedirDouble("Dime el precio del producto: ") })
                    2 -> mostrarproducto(productsList.filter { it.precio < rc.pedirDouble("Dime el precio del producto: ") })
                }
            }
        }
    }
    private fun menuCalcular(): Unit {
        println("1. Sumatorio total")
        println("2. Sumatorio por tipo")
        val opcion = rc.pedirIntEnRango("\tDame un valor entre 1-2",1,2)
        when (opcion) {
            1 -> {
                val total = productsList.fold(0.0){total, product -> total + product.precio}
                println("El valor total de los productos es: $total")
            }
            2 -> {
                val tipos = productsList.groupBy { it.tipo }
                tipos.forEach {
                    val total = it.value.fold(0.0){total, product -> total + product.precio}
                    println("El total de los productos del tipo ${it.key} son: $total")

                }
            }
        }
    }

}