class Questionn <T>(
    val questionText : String,
    val answer : T,
    val difficult : Difficulty
)
enum class Difficulty {
    Easy,
    Medium,
    Hard
}
fun main() {
    val q1 = Questionn<Int> ("Finger in a hand?", 5, Difficulty.Easy)

}