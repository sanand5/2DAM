import kotlin.random.Random

open class Person(val id: Int, val name: String, val surname: String, val age: Int) {
    override fun toString(): String {
        return "Id: $id, Name: $name, Surname: $surname, Age: $age"
    }
}

class Student(id: Int, name: String, surname: String, age: Int, val level: String, val school: HighSchool) :
    Person(id, name, surname, age) {
    override fun toString(): String {
        return super.toString() + ", Level: ${level}"
    }
}

class Teacher(id: Int, name: String, surname: String, age: Int, val center: String, val school: HighSchool) :
    Person(id, name, surname, age) {
    override fun toString(): String {
        return super.toString() + ", Center: ${center}"
    }
}

data class HighSchool(
    var numStudents: Int,
    var numTeachers: Int,
    var nombre: String
)

fun main() {
    val list = mutableListOf<Person>()
    val schools = mutableListOf<HighSchool>(
        HighSchool(589, 78, "IES MARIA ENRIQUEZ"),
        HighSchool(456, 98, "IES AUSIAS MARC"),
        HighSchool(789, 85, "IES RAQUEL RIVAS"),
        HighSchool(741, 74, "IES TIRANT LO BLANC")
    )
    for (i in 1..20) {
        val id = Random.nextInt(100, 999)
        val age = Random.nextInt(12, 17)
        val level = Random.nextInt(1, 4)
        val school = schools[Random.nextInt(0, schools.size)]
        //id random, name, surname, age random, level random, school random
        list.add(Student(id, "NAME" + i, "SURNAME" + i, age, level.toString(), school))
        println(list.get(i - 1))
    }


}