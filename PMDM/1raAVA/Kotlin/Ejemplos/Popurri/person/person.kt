
class Person(val id : Int, val name: String, val surname: String, val age: Int) {
    fun isFullAge(): Boolean {
        return age >= 18
    }
    fun isRetired(): Boolean {
        return age >= 65
    }
    fun ageDiff(personaB: Person ): Int {
        return age - personaB.age
    }
    override fun toString(): String {
        return "Person(id=$id, name='$name', surname='$surname', age=$age)"
    }
}
fun main() {
    val personaA = Person(1, "Juan", "Luis", 70)
    val personaB = Person(1, "Luis", "Juan", 10)

    
    println("${personaA.name} ${if (personaA.isFullAge()) "es mayor de edad" else "no es mayor de edad"}")
    println("${personaB.name} ${if (personaB.isFullAge()) "es mayor de edad" else "no es mayor de edad"}")

    println("${personaA.name} ${if (personaA.isFullAge()) "esta retirado" else "no esta retirado"}")
    println("${personaB.name} ${if (personaB.isFullAge()) "esta retirado" else "no esta retirado"}")

    println("${personaA.name} se lleva con ${personaB.name} ${personaA.ageDiff(personaB)} años")
}