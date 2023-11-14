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