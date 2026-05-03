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
    val isCompleted: Boolean = false,
    val taskType: TaskType = TaskType.PUZZLE,
    val solution: String = "",
    val userAnswer: String = "",
    val verificationMethod: VerificationMethod = VerificationMethod.NONE,
    val userProof: String = "",
    val verificationStatus: VerificationStatus = VerificationStatus.PENDING,
    val difficulty: Difficulty = Difficulty.EASY,
    val points: Int = getPointsForDifficulty(Difficulty.EASY)
)

enum class MoodType {
    HAPPY, SAD, COOL, THINKING, TIRED
}

enum class Difficulty {
    EASY, MEDIUM, HARD
}

enum class TaskType {
    PUZZLE, CREATIVE, SOCIAL
}

enum class VerificationMethod {
    NONE,           // For puzzles (automatic)
    TEXT_INPUT,     // User describes what they did
    PHOTO_UPLOAD,   // User uploads photo (simulated with text)
    SELF_REPORT     // User confirms completion
}

enum class VerificationStatus {
    PENDING,
    SUBMITTED,
    VERIFIED,
    REJECTED
}

// Helper function to calculate points based on difficulty
fun getPointsForDifficulty(difficulty: Difficulty): Int {
    return when (difficulty) {
        Difficulty.EASY -> 5
        Difficulty.MEDIUM -> 10
        Difficulty.HARD -> 15
    }
}
