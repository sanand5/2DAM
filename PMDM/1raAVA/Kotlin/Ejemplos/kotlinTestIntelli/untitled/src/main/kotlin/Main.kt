
fun main(args: Array<String>) {
    val list = mutableListOf("Raul","Andreu","Raquel","Cristhian")
    println(list)

    for (planet in list) {
        println(planet)
    }
    list.add("Logan")
    list.add(3,"Marius")
    println(list)
    list[3] = "Pepe"

}