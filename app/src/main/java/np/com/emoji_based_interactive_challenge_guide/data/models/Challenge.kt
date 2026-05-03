package np.com.emoji_based_interactive_challenge_guide.data.models

data class Challenge(
    val id: String = "",
    val title: String = "",
    val description: String = "",
    val moodType: MoodType = MoodType.HAPPY,
    val difficulty: Difficulty = Difficulty.EASY,
    val points: Int = 10,
    val emojiHint: String = "",
    val tasks: List<Task> = emptyList()
)

data class Task(
    val id: String = "",
    val description: String = "",
    val emojiHint: String = "",
    val isCompleted: Boolean = false
)

enum class MoodType {
    HAPPY, SAD, COOL, THINKING, TIRED
}

enum class Difficulty {
    EASY, MEDIUM, HARD
}
