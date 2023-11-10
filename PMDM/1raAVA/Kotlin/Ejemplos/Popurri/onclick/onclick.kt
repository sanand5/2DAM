fun main() {
    val sum  = calculate(
        10, 
        5, 
        fun(x: Int, y: Int) : Int { 
            return x + y 
        }
        )
        val diff =
        calculate(
            10,
            5,
            fun(x: Int, y: Int): Int {
                return x - y
            }
            )
        println(sum)
        println(diff)
}
fun calculate(x: Int, y: Int, operation :(Int, Int) -> Int): Int {
    return operation(x, y)
}