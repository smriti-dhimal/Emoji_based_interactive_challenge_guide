package np.com.emoji_based_interactive_challenge_guide.ui.screens

import androidx.compose.animation.core.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import np.com.emoji_based_interactive_challenge_guide.data.models.Challenge
import np.com.emoji_based_interactive_challenge_guide.data.models.MoodType
import np.com.emoji_based_interactive_challenge_guide.data.models.TaskType
import np.com.emoji_based_interactive_challenge_guide.data.models.VerificationStatus
import np.com.emoji_based_interactive_challenge_guide.ui.components.*

@Composable
fun ChallengeScreen(
    challenge: Challenge,
    currentTaskIndex: Int,
    onTaskComplete: (String) -> Unit,
    onChallengeComplete: () -> Unit,
    onAnswerSubmit: (String, String) -> Unit,
    onTaskDecline: (String) -> Unit = { taskId -> },
    showResult: Boolean = false,
    isAnswerCorrect: Boolean = false,
    correctAnswer: String = "",
    modifier: Modifier = Modifier
) {
    val currentTask = challenge.tasks.getOrNull(currentTaskIndex)
    val progress = (currentTaskIndex + 1).toFloat() / challenge.tasks.size
    var userAnswer by remember { mutableStateOf("") }

    Column(modifier = modifier.fillMaxSize()) {
        EmojiTopAppBar(
            title = challenge.title,
            showPoints = true,
            points = challenge.points
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            // Challenge emoji and description
            Text(
                text = challenge.emojiHint,
                fontSize = 64.sp,
                modifier = Modifier.scale(1.2f)
            )

            Text(
                text = challenge.description,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.padding(horizontal = 16.dp)
            )

            // Progress indicator
            TaskProgressIndicator(
                currentTaskIndex = currentTaskIndex,
                totalTasks = challenge.tasks.size,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Current task
            currentTask?.let { task ->
                GameTaskCard(
                    task = task,
                    onTaskClick = { /* Handle click based on task type */ },
                    isCurrentTask = true,
                    modifier = Modifier.fillMaxWidth()
                )

                // Show result if available
                if (showResult) {
                    ResultCard(
                        isCorrect = isAnswerCorrect,
                        correctAnswer = correctAnswer,
                        taskType = task.taskType,
                        modifier = Modifier.fillMaxWidth().padding(top = 16.dp)
                    )
                }

                // Task completion based on type
                if (!task.isCompleted && !showResult) {
                    when (task.taskType) {
                        TaskType.PUZZLE -> {
                            // Puzzle input field
                            OutlinedTextField(
                                value = userAnswer,
                                onValueChange = { userAnswer = it },
                                label = { Text("Your Answer") },
                                placeholder = { Text("Enter your solution...") },
                                modifier = Modifier.fillMaxWidth().padding(top = 16.dp),
                                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                                singleLine = true
                            )

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                EmojiButton(
                                    text = "Submit",
                                    emoji = "🧩",
                                    onClick = {
                                        onAnswerSubmit(task.id, userAnswer)
                                    },
                                    modifier = Modifier.weight(1f),
                                    enabled = userAnswer.isNotBlank()
                                )

                                // Auto-advance hint for perfect flow
                                if (userAnswer.isNotBlank()) {
                                    Text(
                                        text = "💡 Press Enter to submit quickly",
                                        style = MaterialTheme.typography.bodySmall,
                                        color = MaterialTheme.colorScheme.primary,
                                        modifier = Modifier.padding(top = 4.dp)
                                    )
                                }

                                EmojiButton(
                                    text = "Skip",
                                    emoji = "⏭️",
                                    onClick = {
                                        onTaskDecline(task.id)
                                    },
                                    modifier = Modifier.weight(1f),
                                    backgroundColor = MaterialTheme.colorScheme.errorContainer,
                                    contentColor = MaterialTheme.colorScheme.onErrorContainer
                                )
                            }
                        }

                        TaskType.CREATIVE -> {
                            // Creative task verification
                            var creativeProof by remember { mutableStateOf("") }

                            OutlinedTextField(
                                value = creativeProof,
                                onValueChange = { creativeProof = it },
                                label = { Text("Describe what you created") },
                                placeholder = { Text("Tell us about your creative work...") },
                                modifier = Modifier.fillMaxWidth().padding(top = 16.dp),
                                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                                maxLines = 3
                            )

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                EmojiButton(
                                    text = "Submit",
                                    emoji = "🎨",
                                    onClick = {
                                        if (creativeProof.isNotBlank()) {
                                            onAnswerSubmit(task.id, creativeProof)
                                        }
                                    },
                                    modifier = Modifier.weight(1f),
                                    enabled = creativeProof.isNotBlank()
                                )

                                EmojiButton(
                                    text = "Skip",
                                    emoji = "⏭️",
                                    onClick = {
                                        onTaskDecline(task.id)
                                    },
                                    modifier = Modifier.weight(1f),
                                    backgroundColor = MaterialTheme.colorScheme.errorContainer,
                                    contentColor = MaterialTheme.colorScheme.onErrorContainer
                                )
                            }
                        }

                        TaskType.SOCIAL -> {
                            // Social task verification
                            var socialProof by remember { mutableStateOf("") }

                            OutlinedTextField(
                                value = socialProof,
                                onValueChange = { socialProof = it },
                                label = { Text("Describe your social interaction") },
                                placeholder = { Text("How did you connect with someone?") },
                                modifier = Modifier.fillMaxWidth().padding(top = 16.dp),
                                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                                maxLines = 3
                            )

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                EmojiButton(
                                    text = "Submit",
                                    emoji = "🤝",
                                    onClick = {
                                        if (socialProof.isNotBlank()) {
                                            onAnswerSubmit(task.id, socialProof)
                                        }
                                    },
                                    modifier = Modifier.weight(1f),
                                    enabled = socialProof.isNotBlank()
                                )

                                EmojiButton(
                                    text = "Skip",
                                    emoji = "⏭️",
                                    onClick = {
                                        onTaskDecline(task.id)
                                    },
                                    modifier = Modifier.weight(1f),
                                    backgroundColor = MaterialTheme.colorScheme.errorContainer,
                                    contentColor = MaterialTheme.colorScheme.onErrorContainer
                                )
                            }
                        }
                    }
                }

                // Continue button after showing result with perfect flow
                if (showResult) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        if (isAnswerCorrect) {
                            Text(
                                text = "✨ Perfect! Well done!",
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.primary
                            )
                        }

                        EmojiButton(
                            text = if (isAnswerCorrect) "Next Task" else "Try Again",
                            emoji = if (isAnswerCorrect) "➡️" else "🔄",
                            onClick = { onTaskComplete(task.id) },
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }
            }

            // Challenge completion
            if (currentTaskIndex >= challenge.tasks.size) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Text(
                        text = "🎉",
                        fontSize = 80.sp
                    )

                    Text(
                        text = "Challenge Complete!",
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    )

                    Text(
                        text = "You've earned ${challenge.tasks.filter { it.isCompleted && it.verificationStatus == VerificationStatus.VERIFIED }.sumOf { it.points }} points!",
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )

                    EmojiButton(
                        text = "Continue",
                        emoji = "🚀",
                        onClick = onChallengeComplete
                    )
                }
            }

            // Upcoming tasks preview
            if (currentTaskIndex < challenge.tasks.size - 1) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Upcoming Tasks",
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )

                    challenge.tasks.drop(currentTaskIndex + 1).take(2).forEach { task ->
                        GameTaskCard(
                            task = task,
                            onTaskClick = { /* Disabled for upcoming tasks */ },
                            isCurrentTask = false,
                            modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)
                        )
                    }
                }
            }
        }
    }
}
