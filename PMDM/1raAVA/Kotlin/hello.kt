fun main() {
    val t=2
    var x = ""
    when{
        t < 15 -> x = "COLD"   
        t in 15..24 -> x = "OK"   
        t > 25 -> x = "HOT"   
    }
    println("${x}")

    when(t){
        in 1..3 -> x = "a"   
        in 4..6 -> x = "b"   
        in 7..9 -> x = "c"   
        in 10..12 -> x = "d"   
    }
    println("${x}")
    
    foo()
    
    var alto = 10.0
    var ancho = 5.0
    println("El perimetro de el rectangulo es: ${Perimetro(alto, ancho)}")
    println("El area de el rectangulo es: ${Area(alto, ancho)}")
}
fun foo()  {
    try {
        throw Exception("Excepcion")
    }
    catch(e: Exception) {
        println("Excepcion catch")
    }finally{
        println("Finally")
    }
    // TODO: jajjajaj
}

fun Perimetro(alto: Double, ancho: Double): Double {
    return (alto+ancho)*2
}

fun Area(alto : Double, ancho : Double): Double{
    return alto*ancho
}