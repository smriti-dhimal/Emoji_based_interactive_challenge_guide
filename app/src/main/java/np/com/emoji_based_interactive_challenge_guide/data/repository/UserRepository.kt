package np.com.emoji_based_interactive_challenge_guide.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import np.com.emoji_based_interactive_challenge_guide.data.models.User

class UserRepository private constructor() {
    private val _currentUser = MutableStateFlow<User>(User())
    val currentUser: Flow<User> = _currentUser.asStateFlow()

    companion object {
        @Volatile
        private var INSTANCE: UserRepository? = null

        fun getInstance(): UserRepository {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: UserRepository().also { INSTANCE = it }
            }
        }
    }

    suspend fun loginUser(username: String, password: String): Result<User> {
        return try {
            // Simulate authentication
            val user = User(
                id = "user_${System.currentTimeMillis()}",
                username = username,
                email = "$username@example.com",
                totalPoints = 0,
                currentStreak = 0
            )
            _currentUser.value = user
            Result.success(user)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun registerUser(username: String, email: String, password: String): Result<User> {
        return try {
            // Simulate registration
            val user = User(
                id = "user_${System.currentTimeMillis()}",
                username = username,
                email = email,
                totalPoints = 0,
                currentStreak = 0
            )
            _currentUser.value = user
            Result.success(user)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun updateUserPoints(points: Int) {
        val currentUser = _currentUser.value
        _currentUser.value = currentUser.copy(
            totalPoints = currentUser.totalPoints + points,
            currentStreak = currentUser.currentStreak + 1
        )
    }

    suspend fun addCompletedChallenge(challengeId: String) {
        val currentUser = _currentUser.value
        _currentUser.value = currentUser.copy(
            completedChallenges = currentUser.completedChallenges + challengeId
        )
    }
}
