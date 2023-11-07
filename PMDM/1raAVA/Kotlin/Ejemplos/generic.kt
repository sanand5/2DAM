class Question <Q, T>(
    val questionText : String,
    val answer : T,
    val difficult : Q
)
fun main() {
    val q1 = Question<String, String>("Capital od China is ___", "Bejing", "medium")

}