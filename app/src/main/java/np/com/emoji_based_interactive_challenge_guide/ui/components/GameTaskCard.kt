package np.com.emoji_based_interactive_challenge_guide.ui.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import np.com.emoji_based_interactive_challenge_guide.data.models.Difficulty
import np.com.emoji_based_interactive_challenge_guide.data.models.Task

@Composable
fun GameTaskCard(
    task: Task,
    isCurrentTask: Boolean = false,
    onTaskClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    var scale by remember { mutableStateOf(1f) }
    val scaleAnimation = animateFloatAsState(
        targetValue = if (isCurrentTask) 1.05f else 1f,
        animationSpec = tween(
            durationMillis = 200,
            easing = EaseOutCubic
        ),
        label = "scale"
    )

    scale = scaleAnimation.value

    val difficultyColor = when (task.difficulty) {
        Difficulty.EASY -> Color(0xFF4CAF50) // Green
        Difficulty.MEDIUM -> Color(0xFFFF9800) // Orange
        Difficulty.HARD -> Color(0xFFF44336) // Red
    }

    val difficultyLabel = when (task.difficulty) {
        Difficulty.EASY -> "EASY"
        Difficulty.MEDIUM -> "MEDIUM"
        Difficulty.HARD -> "HARD"
    }

    Card(
        modifier = modifier
            .fillMaxWidth()
            .scale(scale)
            .clip(RoundedCornerShape(12.dp)),
        colors = CardDefaults.cardColors(
            containerColor = if (isCurrentTask) {
                MaterialTheme.colorScheme.primaryContainer
            } else {
                MaterialTheme.colorScheme.surface
            }
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = if (isCurrentTask) 8.dp else 2.dp
        ),
        onClick = onTaskClick
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // Task info
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.weight(1f)
            ) {
                // Emoji hint
                Surface(
                    modifier = Modifier.size(48.dp),
                    shape = RoundedCornerShape(8.dp),
                    color = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.3f)
                ) {
                    Box(
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = task.emojiHint,
                            fontSize = 24.sp
                        )
                    }
                }

                Column(
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Text(
                        text = task.description,
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = if (isCurrentTask) FontWeight.Bold else FontWeight.Normal,
                        color = MaterialTheme.colorScheme.onSurface,
                        maxLines = 2
                    )

                    // Difficulty badge
                    Surface(
                        modifier = Modifier.widthIn(min = 60.dp),
                        shape = RoundedCornerShape(4.dp),
                        color = difficultyColor.copy(alpha = 0.2f)
                    ) {
                        Text(
                            text = difficultyLabel,
                            style = MaterialTheme.typography.labelSmall,
                            color = difficultyColor,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp)
                        )
                    }
                }
            }

            // Points and status
            Column(
                horizontalAlignment = Alignment.End,
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                // Points
                PointsBadge(
                    points = task.points,
                    backgroundColor = if (task.isCompleted) {
                        MaterialTheme.colorScheme.primary
                    } else {
                        MaterialTheme.colorScheme.secondary
                    }
                )

                // Completion status
                if (task.isCompleted) {
                    Text(
                        text = "✅",
                        fontSize = 20.sp
                    )
                }
            }
        }
    }
}
