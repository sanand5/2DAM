fun main(args: Array<String>) {
    val table = 5
    tabla(table)

}
fun tabla(table: Int): Unit {
    
    for (i in 1..10) {
        println("$table x $i = ${i*table}")
    }
}