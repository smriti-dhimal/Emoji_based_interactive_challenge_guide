package np.com.emoji_based_interactive_challenge_guide.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import np.com.emoji_based_interactive_challenge_guide.data.models.*
import np.com.emoji_based_interactive_challenge_guide.data.models.ACHIEVEMENTS

class AchievementRepository private constructor() {

    private val _userAchievements = MutableStateFlow<List<UserAchievement>>(emptyList())
    val userAchievements: Flow<List<UserAchievement>> = _userAchievements.asStateFlow()

    private val _unlockedAchievements = MutableStateFlow<List<Achievement>>(emptyList())
    val unlockedAchievements: Flow<List<Achievement>> = _unlockedAchievements.asStateFlow()

    companion object {
        @Volatile
        private var INSTANCE: AchievementRepository? = null

        fun getInstance(): AchievementRepository {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: AchievementRepository().also { INSTANCE = it }
            }
        }
    }

    fun checkAndUnlockAchievements(
        totalPoints: Int,
        currentStreak: Int,
        completedChallenges: List<String>,
        puzzleTasksCompleted: Int = 0,
        creativeTasksCompleted: Int = 0,
        socialTasksCompleted: Int = 0
    ) {
        val newlyUnlocked = mutableListOf<Achievement>()
        val currentUnlocked = _unlockedAchievements.value.map { it.id }.toSet()

        ACHIEVEMENTS.forEach { achievement ->
            if (!currentUnlocked.contains(achievement.id)) {
                val shouldUnlock = when (achievement.unlockCondition) {
                    UnlockCondition.FIRST_CHALLENGE -> completedChallenges.isNotEmpty()
                    UnlockCondition.POINTS_50 -> totalPoints >= 50
                    UnlockCondition.POINTS_100 -> totalPoints >= 100
                    UnlockCondition.STREAK_3 -> currentStreak >= 3
                    UnlockCondition.STREAK_7 -> currentStreak >= 7
                    UnlockCondition.ALL_MOODS -> {
                        val moods = setOf("happy", "sad", "cool", "thinking", "tired")
                        completedChallenges.any { it.contains("happy") } &&
                                completedChallenges.any { it.contains("sad") } &&
                                completedChallenges.any { it.contains("cool") } &&
                                completedChallenges.any { it.contains("thinking") } &&
                                completedChallenges.any { it.contains("tired") }
                    }
                    UnlockCondition.PUZZLE_MASTER -> puzzleTasksCompleted >= 10
                    UnlockCondition.CREATIVE_GENIUS -> creativeTasksCompleted >= 10
                    UnlockCondition.SOCIAL_BUTTERFLY -> socialTasksCompleted >= 10
                    UnlockCondition.CHALLENGE_WARRIOR -> completedChallenges.size >= 10
                    UnlockCondition.PERFECT_PUZZLE -> false // Would need puzzle accuracy tracking
                }

                if (shouldUnlock) {
                    newlyUnlocked.add(achievement)
                    val userAchievement = UserAchievement(
                        achievementId = achievement.id,
                        unlockedAt = System.currentTimeMillis()
                    )
                    _userAchievements.value = _userAchievements.value + userAchievement
                }
            }
        }

        if (newlyUnlocked.isNotEmpty()) {
            _unlockedAchievements.value = _unlockedAchievements.value + newlyUnlocked
        }
    }

    fun getUnlockedAchievements(): List<Achievement> {
        val unlockedIds = _userAchievements.value.map { it.achievementId }.toSet()
        return ACHIEVEMENTS.filter { it.id in unlockedIds }
    }

    fun getRecentAchievements(limit: Int = 5): List<Achievement> {
        return getUnlockedAchievements()
            .sortedByDescending { achievement ->
                _userAchievements.value
                    .find { it.achievementId == achievement.id }?.unlockedAt ?: 0
            }
            .take(limit)
    }

    fun getTotalAchievementPoints(): Int {
        return getUnlockedAchievements().sumOf { it.points }
    }

    fun getAchievementProgress(achievement: Achievement): Int {
        // This would return progress percentage for achievements that support progress
        return if (getUnlockedAchievements().any { it.id == achievement.id }) 100 else 0
    }
}
