package np.com.emoji_based_interactive_challenge_guide.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import np.com.emoji_based_interactive_challenge_guide.data.models.User
import np.com.emoji_based_interactive_challenge_guide.data.repository.UserRepository

data class AuthUiState(
    val isLoading: Boolean = false,
    val isLoggedIn: Boolean = false,
    val currentUser: User? = null,
    val error: String? = null
)

class AuthViewModel(application: Application) : AndroidViewModel(application) {

    private val repo = UserRepository.getInstance(application)

    private val _uiState = MutableStateFlow(AuthUiState())
    val uiState: StateFlow<AuthUiState> = _uiState

    init {
        viewModelScope.launch {
            repo.currentUser.collect { user ->
                _uiState.value = _uiState.value.copy(
                    isLoggedIn = user.id.isNotEmpty(),
                    currentUser = user
                )
            }
        }
    }

    fun login(username: String, password: String) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, error = null)

            repo.loginUser(username, password)
                .onFailure {
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        error = it.message
                    )
                }
        }
    }

    fun register(username: String, email: String, password: String) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, error = null)

            repo.registerUser(username, email, password)
                .onFailure {
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        error = it.message
                    )
                }
        }
    }

    fun logout() {
        repo.logout()
    }

    fun clearError() {
        _uiState.value = _uiState.value.copy(error = null)
    }
}
