open class School() {


}

open class Person(val id: Int, val name: String, val surname: String, val age: Int) {
    fun isFullAge(): Boolean {
        return age >= 18
    }

    fun isRetired(): Boolean {
        return age >= 65
    }

    fun ageDiff(personB: Person): Int {
        return age - personB.age
    }

    override fun toString(): String {
        return "Person(id=$id, name='$name', surname='$surname', age=$age)"
    }
}

class Student(id: Int, name: String, surname: String, age: Int, val level: String) : Person(id, name, surname, age) {
    override fun toString(): String {
        return "Student(id=$id, name='$name', surname='$surname', age=$age, level='$level')"
    }
}

class Teacher(id: Int, name: String, surname: String, age: Int, val center: String) : Person(id, name, surname, age) {
    override fun toString(): String {
        return "Teacher(id=$id, name='$name', surname='$surname', age=$age, center='$center')"
    }
}

fun main() {
    val student = Student(1, "Juan", "Perez", 20, "Undergraduate")
    val teacher = Teacher(2, "Maria", "Gomez", 35, "School A")

    println("Estudiante: $student")
    println("Profesor: $teacher")

    val ageDifference = student.ageDiff(teacher)
    println("Diferencia de edad entre el estudiante y el profesor: $ageDifference años")
}
