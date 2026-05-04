package np.com.emoji_based_interactive_challenge_guide.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import np.com.emoji_based_interactive_challenge_guide.data.models.Challenge
import np.com.emoji_based_interactive_challenge_guide.data.models.MoodType
import np.com.emoji_based_interactive_challenge_guide.data.models.Task
import np.com.emoji_based_interactive_challenge_guide.data.models.TaskType
import np.com.emoji_based_interactive_challenge_guide.data.models.VerificationStatus
import np.com.emoji_based_interactive_challenge_guide.data.repository.AchievementRepository
import np.com.emoji_based_interactive_challenge_guide.data.repository.ChallengeRepository
import np.com.emoji_based_interactive_challenge_guide.data.repository.UserRepository

data class ChallengeUiState(
    val currentChallenge: Challenge? = null,
    val currentTaskIndex: Int = 0,
    val isChallengeCompleted: Boolean = false,
    val points: Int = 0,
    val detectedMood: MoodType = MoodType.HAPPY,
    val isLoading: Boolean = false,
    val error: String? = null,
    val showResult: Boolean = false,
    val isAnswerCorrect: Boolean = false,
    val correctAnswer: String = ""
)

class ChallengeViewModel(application: Application) : AndroidViewModel(application) {
    private val challengeRepository = ChallengeRepository.getInstance()
    private val userRepository = UserRepository.getInstance(application)
    private val achievementRepository = AchievementRepository.getInstance()

    private val _uiState = MutableStateFlow(ChallengeUiState())
    val uiState: StateFlow<ChallengeUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            challengeRepository.currentChallenge.collect { challenge ->
                _uiState.value = _uiState.value.copy(currentChallenge = challenge)
            }
        }
    }

    fun detectMood(moodType: MoodType) {
        _uiState.value = _uiState.value.copy(detectedMood = moodType)
        loadChallengeForMood(moodType)
    }

    private fun loadChallengeForMood(moodType: MoodType) {
        _uiState.value = _uiState.value.copy(isLoading = true)

        val challenge = challengeRepository.getChallengeByMood(moodType)
        if (challenge != null) {
            challengeRepository.setCurrentChallenge(challenge)
            _uiState.value = _uiState.value.copy(
                currentChallenge = challenge,
                currentTaskIndex = 0,
                isChallengeCompleted = false,
                isLoading = false
            )
        } else {
            _uiState.value = _uiState.value.copy(
                isLoading = false,
                error = "No challenge found for this mood"
            )
        }
    }

    fun submitTaskAnswer(taskId: String, userAnswer: String) {
        val currentChallenge = _uiState.value.currentChallenge ?: return
        val currentTaskIndex = _uiState.value.currentTaskIndex
        val task = currentChallenge.tasks.getOrNull(currentTaskIndex) ?: return

        // Check if answer is correct for different task types
        val isCorrect = when (task.taskType) {
            TaskType.PUZZLE -> {
                userAnswer.trim().lowercase() == task.solution.lowercase()
            }
            TaskType.CREATIVE -> {
                // Verify creative work by checking if user provided meaningful description
                userAnswer.trim().length >= 10 // At least 10 characters to count as valid
            }
            TaskType.SOCIAL -> {
                // Verify social activity by checking if user provided meaningful description
                userAnswer.trim().length >= 10 // At least 10 characters to count as valid
            }
        }

        // Update task with user answer and correctness
        val updatedTasks = currentChallenge.tasks.map { t ->
            if (t.id == taskId) t.copy(userAnswer = userAnswer, verificationStatus = if (isCorrect) VerificationStatus.VERIFIED else VerificationStatus.REJECTED) else t
        }

        val updatedChallenge = currentChallenge.copy(tasks = updatedTasks)
        challengeRepository.setCurrentChallenge(updatedChallenge)

        _uiState.value = _uiState.value.copy(
            showResult = true,
            isAnswerCorrect = isCorrect,
            correctAnswer = if (!isCorrect && task.taskType == TaskType.PUZZLE) task.solution else ""
        )
    }

    fun completeTask(taskId: String) {
        val currentChallenge = _uiState.value.currentChallenge ?: return
        val currentTaskIndex = _uiState.value.currentTaskIndex

        val completedTask = currentChallenge.tasks.find { it.id == taskId }

        val updatedTasks = currentChallenge.tasks.map { task ->
            if (task.id == taskId) task.copy(isCompleted = true) else task
        }

        val updatedChallenge = currentChallenge.copy(tasks = updatedTasks)
        challengeRepository.setCurrentChallenge(updatedChallenge)

        // Check if answer was correct before awarding points
        val isAnswerCorrect = _uiState.value.isAnswerCorrect

        // Only award points if answer was correct
        if (isAnswerCorrect) {
            completedTask?.let { task ->
                viewModelScope.launch {
                    userRepository.updateUserPoints(task.points)

                    // Check for achievements after each task completion
                    val currentUser = userRepository.currentUser.collect { user ->
                        val completedChallenges = user.completedChallenges
                        achievementRepository.checkAndUnlockAchievements(
                            totalPoints = user.totalPoints,
                            currentStreak = user.currentStreak,
                            completedChallenges = completedChallenges
                        )
                    }

                    _uiState.value = _uiState.value.copy(
                        points = _uiState.value.points + task.points
                    )
                }
            }
        }

        val nextTaskIndex = currentTaskIndex + 1
        val isChallengeCompleted = nextTaskIndex >= updatedTasks.size

        _uiState.value = _uiState.value.copy(
            currentTaskIndex = nextTaskIndex,
            isChallengeCompleted = isChallengeCompleted,
            showResult = false
        )

        if (isChallengeCompleted) {
            completeChallenge()
        }
    }

    fun declineTask(taskId: String) {
        val currentChallenge = _uiState.value.currentChallenge ?: return
        val currentTaskIndex = _uiState.value.currentTaskIndex

        val declinedTask = currentChallenge.tasks.find { it.id == taskId }

        val updatedTasks = currentChallenge.tasks.map { task ->
            if (task.id == taskId) task.copy(
                isCompleted = true,
                userAnswer = "skipped"
            ) else task
        }

        val updatedChallenge = currentChallenge.copy(tasks = updatedTasks)
        challengeRepository.setCurrentChallenge(updatedChallenge)

        // NO POINTS DEDUCTED for skipping - just move to next task
        // Skipping should not affect points at all

        val nextTaskIndex = currentTaskIndex + 1
        val isChallengeCompleted = nextTaskIndex >= updatedTasks.size

        _uiState.value = _uiState.value.copy(
            currentTaskIndex = nextTaskIndex,
            isChallengeCompleted = isChallengeCompleted,
            showResult = false
        )

        // Only call completeChallenge if this is the last task AND at least one task was actually completed
        if (isChallengeCompleted) {
            val actuallyCompletedTasks = updatedTasks.filter { task ->
                task.isCompleted && task.userAnswer.isNotBlank() && task.userAnswer != "skipped"
            }

            // Only complete if there's actual work done
            if (actuallyCompletedTasks.isNotEmpty()) {
                completeChallenge()
            }
        }
    }

    fun continueToNextTask() {
        val currentTask = getCurrentTask() ?: return
        completeTask(currentTask.id)
    }

    private fun completeChallenge() {
        val currentChallenge = _uiState.value.currentChallenge ?: return

        viewModelScope.launch {
            // Check if any tasks were actually completed (not just skipped)
            val actuallyCompletedTasks = currentChallenge.tasks.filter { task ->
                task.isCompleted && task.userAnswer.isNotBlank() && task.userAnswer != "skipped"
            }

            // Only add challenge completion if at least one task was actually completed
            if (actuallyCompletedTasks.isNotEmpty()) {
                userRepository.addCompletedChallenge(currentChallenge.id)

                // NO BONUS POINTS - only count actual task points
                // This ensures 5+5 = 10, not 12
            }

            // Check for achievements
            val currentUser = userRepository.currentUser.collect { user ->
                val completedChallenges = user.completedChallenges
                achievementRepository.checkAndUnlockAchievements(
                    totalPoints = user.totalPoints,
                    currentStreak = user.currentStreak,
                    completedChallenges = completedChallenges
                )
            }
        }
    }

    fun resetChallenge() {
        _uiState.value = ChallengeUiState(detectedMood = _uiState.value.detectedMood)
    }

    fun clearError() {
        _uiState.value = _uiState.value.copy(error = null)
    }

    fun getCurrentTask(): Task? {
        val challenge = _uiState.value.currentChallenge
        val index = _uiState.value.currentTaskIndex
        return challenge?.tasks?.getOrNull(index)
    }
}
