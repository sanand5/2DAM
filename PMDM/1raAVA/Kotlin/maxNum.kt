fun main(args: Array<String>) {
    val numa = 40
    val numb = 20
    val numc = 30
    var max : Int
    max = foo(numa, numb)
    max = foo(max, numc)
    println("max = ${max}")
}
fun foo(numa: Int, numb: Int): Int {
    if (numa > numb) {  
        return numa
    }else {
        return numb
    }
}