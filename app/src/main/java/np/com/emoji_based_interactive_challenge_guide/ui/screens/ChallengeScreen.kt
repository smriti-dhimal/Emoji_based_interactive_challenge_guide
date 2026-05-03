package np.com.emoji_based_interactive_challenge_guide.ui.screens

import androidx.compose.animation.core.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import np.com.emoji_based_interactive_challenge_guide.data.models.Challenge
import np.com.emoji_based_interactive_challenge_guide.data.models.MoodType
import np.com.emoji_based_interactive_challenge_guide.ui.components.*

@Composable
fun ChallengeScreen(
    challenge: Challenge,
    currentTaskIndex: Int,
    onTaskComplete: (String) -> Unit,
    onChallengeComplete: () -> Unit,
    modifier: Modifier = Modifier
) {
    val currentTask = challenge.tasks.getOrNull(currentTaskIndex)
    val progress = (currentTaskIndex + 1).toFloat() / challenge.tasks.size

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
                TaskCard(
                    task = task,
                    onTaskClick = { onTaskComplete(task.id) },
                    isCurrentTask = true,
                    modifier = Modifier.fillMaxWidth()
                )

                // Task completion button
                if (!task.isCompleted) {
                    EmojiButton(
                        text = "Complete Task",
                        emoji = "✅",
                        onClick = { onTaskComplete(task.id) },
                        modifier = Modifier.padding(top = 16.dp)
                    )
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
                        text = "You've earned ${challenge.points} points!",
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
                        TaskCard(
                            task = task,
                            onTaskClick = { /* Disabled for upcoming tasks */ },
                            modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)
                        )
                    }
                }
            }
        }
    }
}
