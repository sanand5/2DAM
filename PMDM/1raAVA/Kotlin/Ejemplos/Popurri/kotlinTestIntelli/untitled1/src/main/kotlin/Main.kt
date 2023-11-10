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
    val list = mutableListOf<Student>()
    val schools = mutableListOf<HighSchool>(
        HighSchool(589, 78, "IES MARIA ENRIQUEZ"),
        HighSchool(456, 98, "IES AUSIAS MARC"),
        HighSchool(789, 85, "IES RAQUEL RIVAS"),
        HighSchool(741, 74, "IES TIRANT LO BLANC")
    )
    for (i in 1..20) {
        val id = Random.nextInt(100, 999)
        val age = Random.nextInt(12, 17)
        val level = Random.nextInt(1, 5)
        val school = schools[Random.nextInt(0, schools.size)]
        //id random, name, surname, age random, level random, school random
        list.add(Student(id, "NAME" + i, "SURNAME" + i, age, level.toString(), school))
        println(list.get(i - 1))
    }

    //TODO: hacer que en el mapa se guarden la lista de alumnos que pertenecen a cada escuela
    val mapa: MutableMap<String, MutableList<Student>> = mutableMapOf()

    // Iterar sobre la lista de estudiantes y asignarlos a la escuela correspondiente en el mapa
    for (student in list) {
        val schoolName = student.school.nombre
        when (schoolName) {
            "IES MARIA ENRIQUEZ" -> mapa.getOrPut(schoolName) { mutableListOf() }.add(student)
            "IES AUSIAS MARC" -> mapa.getOrPut(schoolName) { mutableListOf() }.add(student)
            "IES RAQUEL RIVAS" -> mapa.getOrPut(schoolName) { mutableListOf() }.add(student)
            "IES TIRANT LO BLANC" -> mapa.getOrPut(schoolName) { mutableListOf() }.add(student)
        }
    }

    println("IES MARIA ENRIQUEZ -> ${mapa["IES MARIA ENRIQUEZ"]?.size}")
    println("IES AUSIAS MARC -> ${mapa["IES AUSIAS MARC"]?.size}")
    println("IES RAQUEL RIVAS -> ${mapa["IES RAQUEL RIVAS"]?.size}")
    println("IES TIRANT LO BLANC -> ${mapa["IES TIRANT LO BLANC"]?.size}")

    // Imprimir el contenido del mapa
    println("----------IES MARIA ENRIQUEZ----------")
    mapa["IES MARIA ENRIQUEZ"]?.forEach { println(it) }
    println("----------IES AUSIAS MARC----------")
    mapa["IES AUSIAS MARC"]?.forEach { println(it) }
    println("----------IES RAQUEL RIVAS----------")
    mapa["IES RAQUEL RIVAS"]?.forEach { println(it) }
    println("----------IES TIRANT LO BLANC----------")
    mapa["IES TIRANT LO BLANC"]?.forEach { println(it) }

}