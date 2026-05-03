package np.com.emoji_based_interactive_challenge_guide.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import np.com.emoji_based_interactive_challenge_guide.data.models.Challenge
import np.com.emoji_based_interactive_challenge_guide.data.models.MoodType
import np.com.emoji_based_interactive_challenge_guide.data.models.Task
import np.com.emoji_based_interactive_challenge_guide.data.repository.ChallengeRepository
import np.com.emoji_based_interactive_challenge_guide.data.repository.UserRepository

data class ChallengeUiState(
    val currentChallenge: Challenge? = null,
    val currentTaskIndex: Int = 0,
    val isChallengeCompleted: Boolean = false,
    val points: Int = 0,
    val detectedMood: MoodType = MoodType.HAPPY,
    val isLoading: Boolean = false,
    val error: String? = null
)

class ChallengeViewModel(
    private val challengeRepository: ChallengeRepository = ChallengeRepository(),
    private val userRepository: UserRepository = UserRepository()
) : ViewModel() {

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

    fun completeTask(taskId: String) {
        val currentChallenge = _uiState.value.currentChallenge ?: return
        val currentTaskIndex = _uiState.value.currentTaskIndex
        
        val updatedTasks = currentChallenge.tasks.map { task ->
            if (task.id == taskId) task.copy(isCompleted = true) else task
        }
        
        val updatedChallenge = currentChallenge.copy(tasks = updatedTasks)
        challengeRepository.setCurrentChallenge(updatedChallenge)
        
        val nextTaskIndex = currentTaskIndex + 1
        val isChallengeCompleted = nextTaskIndex >= updatedTasks.size
        
        _uiState.value = _uiState.value.copy(
            currentTaskIndex = nextTaskIndex,
            isChallengeCompleted = isChallengeCompleted
        )
        
        if (isChallengeCompleted) {
            completeChallenge()
        }
    }

    private fun completeChallenge() {
        val currentChallenge = _uiState.value.currentChallenge ?: return
        
        viewModelScope.launch {
            userRepository.updateUserPoints(currentChallenge.points)
            userRepository.addCompletedChallenge(currentChallenge.id)
            
            _uiState.value = _uiState.value.copy(
                points = _uiState.value.points + currentChallenge.points
            )
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
