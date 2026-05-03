package np.com.emoji_based_interactive_challenge_guide.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import np.com.emoji_based_interactive_challenge_guide.data.models.TaskType

@Composable
fun ResultCard(
    isCorrect: Boolean,
    correctAnswer: String,
    taskType: TaskType,
    modifier: Modifier = Modifier
) {
    val backgroundColor = if (isCorrect) {
        Color(0xFFE8F5E8) // Light green
    } else {
        Color(0xFFFFF3E8) // Light orange
    }

    val borderColor = if (isCorrect) {
        Color(0xFF4CAF50) // Green
    } else {
        Color(0xFFFF9800) // Orange
    }

    val titleText = when {
        isCorrect && taskType == TaskType.PUZZLE -> "🎉 Correct!"
        !isCorrect && taskType == TaskType.PUZZLE -> "🤔 Not Quite"
        isCorrect && taskType == TaskType.CREATIVE -> "✨ Creative Master!"
        !isCorrect && taskType == TaskType.CREATIVE -> "📝 Add More Details"
        isCorrect && taskType == TaskType.SOCIAL -> "🤝 Social Star!"
        !isCorrect && taskType == TaskType.SOCIAL -> "💬 Tell Us More"
        else -> "✅ Complete!"
    }

    val messageText = when {
        isCorrect && taskType == TaskType.PUZZLE -> "You solved it perfectly!"
        !isCorrect && taskType == TaskType.PUZZLE -> "The correct answer was: $correctAnswer"
        isCorrect && taskType == TaskType.CREATIVE -> "Your creative work has been verified!"
        !isCorrect && taskType == TaskType.CREATIVE -> "Please provide more details about your creative work (at least 10 characters)"
        isCorrect && taskType == TaskType.SOCIAL -> "Your social interaction has been verified!"
        !isCorrect && taskType == TaskType.SOCIAL -> "Please provide more details about your social activity (at least 10 characters)"
        else -> "Task completed successfully!"
    }

    Card(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp)),
        colors = CardDefaults.cardColors(
            containerColor = backgroundColor
        ),
        border = androidx.compose.foundation.BorderStroke(
            width = 2.dp,
            color = borderColor
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = titleText,
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                color = borderColor,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            Text(
                text = messageText,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            if (!isCorrect && taskType == TaskType.PUZZLE && correctAnswer.isNotEmpty()) {
                Text(
                    text = "Keep trying! You'll get it next time.",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}
