fun main() {
    val max = 5
    println("El sumatorio es: ${sumatorio(max)}")
    println("El productorio es: ${productorio(max)}")
    println(max/2)

}

fun sumatorio(max: Int): Int {
    var num = 0
    for (i in 1..max) {
        num += i
    }
    return num   
}

fun productorio(max: Int): Int {
    var num = 0 
    for (i in 1..max) {
        num *= i
    }
    return num

}