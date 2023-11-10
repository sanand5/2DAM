import kotlin.random.Random
fun main() {
    val institutos = arrayOf("Maria Enriquewz", "Escalves", "Gregori", "Titant lo blanc")
    val listaStudents = mutableListOf<Student>()
    for (i in 1..20) {
        val aleatorioInst = Random.nextInt(1, institutos.size)
        val nomInstituto : String = institutos[aleatorioInst]
        val aleatorioId = Random.nextInt(1, 500)
        val aleatorioAge = Random.nextInt(1, 20)
        val aleatorioLevel = Random.nextInt(1,5)
        listaStudents.add(Student(aleatorioId, "Name${i}", "Surname${i}", aleatorioAge, nomInstituto, aleatorioLevel))
    }

    for(s in listaStudents) {
        println(s)
    }
}
data class HighSchool{

}

open class Person (val id: Int,val name: String, val surname: String, val age:Int ) {
    override fun toString() : String {
        return "Id: $id, Name: $name, Surname: $surname, Age: $age"
    }
}
class Student(id: Int, name: String, surname: String, age:Int,val center : String, val level: Int ) : Person (id,name,surname,age){
    override  fun toString(): String {
        return super.toString()+ ", Center: $center, Level: ${level}"
    }
}