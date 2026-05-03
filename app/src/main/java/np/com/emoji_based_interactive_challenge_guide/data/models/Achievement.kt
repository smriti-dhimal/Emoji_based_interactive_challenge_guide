package np.com.emoji_based_interactive_challenge_guide.data.models

data class Achievement(
    val id: String,
    val title: String,
    val description: String,
    val emoji: String,
    val isUnlocked: Boolean = false,
    val unlockCondition: UnlockCondition,
    val points: Int = 0
)

data class UserAchievement(
    val achievementId: String,
    val unlockedAt: Long = System.currentTimeMillis(),
    val progress: Int = 0,
    val isCompleted: Boolean = true
)

enum class UnlockCondition {
    FIRST_CHALLENGE,          // Complete first challenge
    POINTS_50,               // Earn 50 total points
    POINTS_100,              // Earn 100 total points
    STREAK_3,                // Complete 3 challenges in a row
    STREAK_7,                // Complete 7 challenges in a row
    ALL_MOODS,               // Complete challenges for all moods
    PUZZLE_MASTER,           // Complete 10 puzzle tasks
    CREATIVE_GENIUS,         // Complete 10 creative tasks
    SOCIAL_BUTTERFLY,        // Complete 10 social tasks
    PERFECT_PUZZLE,          // Complete 5 puzzles without mistakes
    CHALLENGE_WARRIOR        // Complete 10 total challenges
}

// Predefined achievements
val ACHIEVEMENTS = listOf(
    Achievement(
        id = "first_challenge",
        title = "First Steps",
        description = "Complete your first challenge",
        emoji = "🎯",
        unlockCondition = UnlockCondition.FIRST_CHALLENGE,
        points = 5
    ),
    Achievement(
        id = "points_50",
        title = "Rising Star",
        description = "Earn 50 total points",
        emoji = "⭐",
        unlockCondition = UnlockCondition.POINTS_50,
        points = 10
    ),
    Achievement(
        id = "points_100",
        title = "Champion",
        description = "Earn 100 total points",
        emoji = "🏆",
        unlockCondition = UnlockCondition.POINTS_100,
        points = 20
    ),
    Achievement(
        id = "streak_3",
        title = "On Fire",
        description = "Complete 3 challenges in a row",
        emoji = "🔥",
        unlockCondition = UnlockCondition.STREAK_3,
        points = 15
    ),
    Achievement(
        id = "streak_7",
        title = "Unstoppable",
        description = "Complete 7 challenges in a row",
        emoji = "💥",
        unlockCondition = UnlockCondition.STREAK_7,
        points = 25
    ),
    Achievement(
        id = "all_moods",
        title = "Emotion Master",
        description = "Complete challenges for all mood types",
        emoji = "🌈",
        unlockCondition = UnlockCondition.ALL_MOODS,
        points = 30
    ),
    Achievement(
        id = "puzzle_master",
        title = "Puzzle Master",
        description = "Complete 10 puzzle tasks correctly",
        emoji = "🧩",
        unlockCondition = UnlockCondition.PUZZLE_MASTER,
        points = 20
    ),
    Achievement(
        id = "creative_genius",
        title = "Creative Genius",
        description = "Complete 10 creative tasks",
        emoji = "🎨",
        unlockCondition = UnlockCondition.CREATIVE_GENIUS,
        points = 20
    ),
    Achievement(
        id = "social_butterfly",
        title = "Social Butterfly",
        description = "Complete 10 social tasks",
        emoji = "🦋",
        unlockCondition = UnlockCondition.SOCIAL_BUTTERFLY,
        points = 20
    ),
    Achievement(
        id = "challenge_warrior",
        title = "Challenge Warrior",
        description = "Complete 10 total challenges",
        emoji = "⚔️",
        unlockCondition = UnlockCondition.CHALLENGE_WARRIOR,
        points = 30
    )
)
