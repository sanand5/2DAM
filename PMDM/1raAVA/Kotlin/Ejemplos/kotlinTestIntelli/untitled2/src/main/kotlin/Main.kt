class Person(val name: String, val age: Int) {
    override fun toString(): String {
        return "Person(name='$name', age=$age)"
    }
}

fun main(args: Array<String>) {
    val ages = mutableMapOf("Alice" to 10, "Bob" to 20, "Charlie" to 30)
    println(ages)
    println(ages.get("Bob"))
    ages.remove("Alice") // Now this will work
    println(ages)

    val people = mutableMapOf(1 to Person("Alice", 10), 2 to Person("Bob", 20))
    println(people)
}