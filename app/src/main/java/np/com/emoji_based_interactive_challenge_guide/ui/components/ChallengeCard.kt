package np.com.emoji_based_interactive_challenge_guide.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import np.com.emoji_based_interactive_challenge_guide.data.models.Challenge
import np.com.emoji_based_interactive_challenge_guide.ui.theme.*

@Composable
fun ChallengeCard(
    challenge: Challenge,
    onChallengeClick: () -> Unit,
    modifier: Modifier = Modifier,
    isCompleted: Boolean = false
) {
    val moodColor = when (challenge.moodType) {
        np.com.emoji_based_interactive_challenge_guide.data.models.MoodType.HAPPY -> EmojiTheme.colors.happy
        np.com.emoji_based_interactive_challenge_guide.data.models.MoodType.SAD -> EmojiTheme.colors.sad
        np.com.emoji_based_interactive_challenge_guide.data.models.MoodType.COOL -> EmojiTheme.colors.cool
        np.com.emoji_based_interactive_challenge_guide.data.models.MoodType.THINKING -> EmojiTheme.colors.thinking
        np.com.emoji_based_interactive_challenge_guide.data.models.MoodType.TIRED -> EmojiTheme.colors.tired
    }

    Card(
        modifier = modifier
            .fillMaxWidth()
            .clip(CardShape)
            .clickable { onChallengeClick() },
        shape = CardShape,
        colors = CardDefaults.cardColors(
            containerColor = if (isCompleted) {
                MaterialTheme.colorScheme.surfaceVariant
            } else {
                MaterialTheme.colorScheme.surface
            }
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Emoji and title
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = challenge.emojiHint,
                        fontSize = 32.sp,
                        modifier = Modifier.padding(end = 12.dp)
                    )
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = challenge.title,
                            style = MaterialTheme.typography.challengeTitle,
                            fontWeight = FontWeight.SemiBold,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                        Text(
                            text = challenge.description,
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis,
                            modifier = Modifier.padding(top = 4.dp)
                        )
                    }
                }

                // Points badge
                PointsBadge(
                    points = challenge.points,
                    backgroundColor = moodColor
                )
            }

            // Progress indicator
            ChallengeProgress(
                totalTasks = challenge.tasks.size,
                completedTasks = challenge.tasks.count { it.isCompleted },
                moodColor = moodColor,
                modifier = Modifier.padding(top = 12.dp)
            )

            // Completion status
            if (isCompleted) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "✅ Completed",
                        style = MaterialTheme.typography.labelMedium,
                        color = EmojiTheme.colors.success,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
        }
    }
}


@Composable
fun ChallengeProgress(
    totalTasks: Int,
    completedTasks: Int,
    moodColor: Color,
    modifier: Modifier = Modifier
) {
    val progress = if (totalTasks > 0) completedTasks.toFloat() / totalTasks else 0f

    Column(modifier = modifier) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Progress",
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Text(
                text = "$completedTasks/$totalTasks tasks",
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                fontWeight = FontWeight.SemiBold
            )
        }

        LinearProgressIndicator(
            progress = progress,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 4.dp)
                .height(6.dp)
                .clip(RoundedCornerShape(3.dp)),
            color = moodColor,
            trackColor = moodColor.copy(alpha = 0.2f)
        )
    }
}
