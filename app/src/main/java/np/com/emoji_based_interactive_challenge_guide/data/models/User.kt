package np.com.emoji_based_interactive_challenge_guide.data.models

data class User(
    val id: String = "",
    val username: String = "",
    val email: String = "",
    val totalPoints: Int = 0,
    val currentStreak: Int = 0,
    val completedChallenges: List<String> = emptyList()
)
