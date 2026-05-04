package np.com.emoji_based_interactive_challenge_guide.data.repository

import android.content.Context
import android.content.SharedPreferences
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import np.com.emoji_based_interactive_challenge_guide.data.models.RegisteredUser
import np.com.emoji_based_interactive_challenge_guide.data.models.User
import org.json.JSONArray
import org.json.JSONObject

class UserRepository private constructor(context: Context) {

    private val prefs: SharedPreferences =
        context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)

    private val _currentUser = MutableStateFlow(User())
    val currentUser: StateFlow<User> = _currentUser

    private val registeredUsers = mutableMapOf<String, RegisteredUser>()

    companion object {
        private var INSTANCE: UserRepository? = null

        fun getInstance(context: Context): UserRepository {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: UserRepository(context.applicationContext).also {
                    INSTANCE = it
                }
            }
        }

        private fun hashPassword(password: String): String {
            return "${password.hashCode()}_${password.reversed().hashCode()}"
        }

        private fun verifyPassword(password: String, hashed: String): Boolean {
            return hashPassword(password) == hashed
        }

        private const val KEY_USERS = "users"
        private const val KEY_LOGGED_IN = "logged_in"
        private const val KEY_CURRENT_USER = "current_user"
    }

    init {
        loadUsers()
        loadLoggedInUser()
    }

    // ---------------- USERS ----------------

    private fun loadUsers() {
        val json = prefs.getString(KEY_USERS, null) ?: return
        val array = JSONArray(json)

        for (i in 0 until array.length()) {
            val obj = array.getJSONObject(i)
            val user = RegisteredUser(
                obj.getString("username"),
                obj.getString("email"),
                obj.getString("password")
            )
            registeredUsers[user.username] = user
        }
    }

    private fun saveUsers() {
        val array = JSONArray()
        registeredUsers.values.forEach {
            val obj = JSONObject()
            obj.put("username", it.username)
            obj.put("email", it.email)
            obj.put("password", it.password)
            array.put(obj)
        }
        prefs.edit().putString(KEY_USERS, array.toString()).apply()
    }

    // ---------------- LOGIN STATE ----------------

    private fun saveLoggedInUser(user: User) {
        val obj = JSONObject()
        obj.put("username", user.username)
        obj.put("email", user.email)

        prefs.edit()
            .putBoolean(KEY_LOGGED_IN, true)
            .putString(KEY_CURRENT_USER, obj.toString())
            .apply()
    }

    private fun loadLoggedInUser() {
        // Don't auto-login on app restart - always start logged out
        // Users must manually login each time
        _currentUser.value = User()
    }

    fun logout() {
        prefs.edit().putBoolean(KEY_LOGGED_IN, false).apply()
        _currentUser.value = User()
    }

    suspend fun registerUser(
        username: String,
        email: String,
        password: String
    ): Result<User> {
        return try {
            if (registeredUsers.containsKey(username)) {
                return Result.failure(Exception("Username already exists"))
            }

            val regUser = RegisteredUser(username, email, hashPassword(password))
            registeredUsers[username] = regUser
            saveUsers()

            val user = User(
                id = "user_${System.currentTimeMillis()}",
                username = username,
                email = email
            )

            _currentUser.value = user
            saveLoggedInUser(user)

            Result.success(user)

        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun loginUser(username: String, password: String): Result<User> {
        return try {
            val regUser = registeredUsers[username]

            if (regUser != null && verifyPassword(password, regUser.password)) {

                val user = User(
                    id = "user_${System.currentTimeMillis()}",
                    username = username,
                    email = regUser.email
                )

                _currentUser.value = user
                saveLoggedInUser(user)

                Result.success(user)

            } else {
                Result.failure(Exception("Invalid username or password"))
            }

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
