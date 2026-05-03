package np.com.emoji_based_interactive_challenge_guide.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import np.com.emoji_based_interactive_challenge_guide.ui.screens.*
import np.com.emoji_based_interactive_challenge_guide.viewmodel.AuthViewModel
import np.com.emoji_based_interactive_challenge_guide.viewmodel.ChallengeViewModel
import np.com.emoji_based_interactive_challenge_guide.data.models.MoodType

sealed class Screen(val route: String) {
    object Splash : Screen("splash")
    object Login : Screen("login")
    object Register : Screen("register")
    object FaceDetection : Screen("face_detection")
    object Challenge : Screen("challenge")
    object Result : Screen("result")
    object Progress : Screen("progress")
}

@Composable
fun AppNavigation(
    navController: NavHostController = rememberNavController(),
    authViewModel: AuthViewModel = viewModel(),
    challengeViewModel: ChallengeViewModel = viewModel()
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route
    ) {
        composable(Screen.Splash.route) {
            SplashScreen(
                onSplashComplete = {
                    if (authViewModel.uiState.value.isLoggedIn) {
                        navController.navigate(Screen.FaceDetection.route) {
                            popUpTo(Screen.Splash.route) { inclusive = true }
                        }
                    } else {
                        navController.navigate(Screen.Login.route) {
                            popUpTo(Screen.Splash.route) { inclusive = true }
                        }
                    }
                }
            )
        }

        composable(Screen.Login.route) {
            val uiState by authViewModel.uiState.collectAsState()
            
            // Navigate to face detection after successful login
            LaunchedEffect(uiState.isLoggedIn) {
                if (uiState.isLoggedIn) {
                    navController.navigate(Screen.FaceDetection.route) {
                        popUpTo(Screen.Login.route) { inclusive = true }
                    }
                }
            }
            
            LoginScreen(
                onLogin = { username, password ->
                    authViewModel.login(username, password)
                },
                onRegister = {
                    navController.navigate(Screen.Register.route)
                },
                isLoading = uiState.isLoading,
                error = uiState.error
            )
        }

        composable(Screen.Register.route) {
            val uiState by authViewModel.uiState.collectAsState()
            
            // Navigate to face detection after successful registration
            LaunchedEffect(uiState.isLoggedIn) {
                if (uiState.isLoggedIn) {
                    navController.navigate(Screen.FaceDetection.route) {
                        popUpTo(Screen.Register.route) { inclusive = true }
                    }
                }
            }
            
            RegisterScreen(
                onRegister = { username, email, password ->
                    authViewModel.register(username, email, password)
                },
                onBackToLogin = {
                    navController.navigateUp()
                },
                isLoading = uiState.isLoading,
                error = uiState.error
            )
        }

        composable(Screen.FaceDetection.route) {
            FaceDetectionScreen(
                onMoodDetected = { mood ->
                    challengeViewModel.detectMood(mood)
                    navController.navigate(Screen.Challenge.route)
                }
            )
        }

        composable(Screen.Challenge.route) {
            val uiState by challengeViewModel.uiState.collectAsState()
            val currentChallenge = uiState.currentChallenge
            
            currentChallenge?.let { challenge ->
                ChallengeScreen(
                    challenge = challenge,
                    currentTaskIndex = uiState.currentTaskIndex,
                    onTaskComplete = { taskId ->
                        challengeViewModel.completeTask(taskId)
                    },
                    onChallengeComplete = {
                        navController.navigate(Screen.Result.route)
                    }
                )
            }
        }

        composable(Screen.Result.route) {
            val challengeUiState by challengeViewModel.uiState.collectAsState()
            val authUiState by authViewModel.uiState.collectAsState()
            
            ResultScreen(
                totalPoints = authUiState.currentUser?.totalPoints ?: 0,
                challengePoints = challengeUiState.points,
                onContinue = {
                    navController.navigate(Screen.Progress.route) {
                        popUpTo(Screen.FaceDetection.route)
                    }
                }
            )
        }

        composable(Screen.Progress.route) {
            val authUiState by authViewModel.uiState.collectAsState()
            
            ProgressScreen(
                totalPoints = authUiState.currentUser?.totalPoints ?: 0,
                completedChallenges = authUiState.currentUser?.completedChallenges ?: emptyList(),
                onNewChallenge = {
                    navController.navigate(Screen.FaceDetection.route) {
                        popUpTo(Screen.Progress.route)
                    }
                }
            )
        }
    }
}

// Navigation helpers
fun navigateToLogin(navController: NavHostController) {
    navController.navigate(Screen.Login.route) {
        popUpTo(0) { inclusive = true }
    }
}

fun navigateToChallenge(navController: NavHostController) {
    navController.navigate(Screen.Challenge.route)
}

fun navigateToResult(navController: NavHostController) {
    navController.navigate(Screen.Result.route)
}

fun navigateToProgress(navController: NavHostController) {
    navController.navigate(Screen.Progress.route)
}
