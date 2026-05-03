package np.com.emoji_based_interactive_challenge_guide.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import np.com.emoji_based_interactive_challenge_guide.data.models.Achievement
import np.com.emoji_based_interactive_challenge_guide.data.repository.AchievementRepository
import np.com.emoji_based_interactive_challenge_guide.ui.components.*
import np.com.emoji_based_interactive_challenge_guide.ui.theme.EmojiTheme

@Composable
fun ProgressScreen(
    totalPoints: Int,
    completedChallenges: List<String>,
    currentStreak: Int = 1,
    onNewChallenge: () -> Unit,
    modifier: Modifier = Modifier
) {
    val achievementRepository = AchievementRepository.getInstance()
    val recentAchievements by remember { mutableStateOf(achievementRepository.getRecentAchievements()) }
    Column(modifier = modifier.fillMaxSize()) {
        EmojiTopAppBar(
            title = "Your Progress",
            showPoints = true,
            points = totalPoints
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp)
        ) {
            // Progress overview
            ProgressOverviewCard(
                totalPoints = totalPoints,
                completedChallenges = completedChallenges.size,
                currentStreak = currentStreak,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(24.dp))

            // New challenge button
            EmojiButton(
                text = "Start New Challenge",
                emoji = "🎯",
                onClick = onNewChallenge,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(24.dp))

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.weight(1f)
            ) {
                // Recent Achievements Section
                item {
                    Text(
                        text = "Recent Achievements",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )

                    if (recentAchievements.isEmpty()) {
                        Text(
                            text = "No achievements yet. Complete challenges to unlock badges!",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            modifier = Modifier.padding(16.dp)
                        )
                    } else {
                        recentAchievements.take(3).forEach { achievement ->
                            AchievementBadgeItem(achievement = achievement)
                            Spacer(modifier = Modifier.height(8.dp))
                        }
                    }
                }

                // Completed Challenges Section
                item {
                    Text(
                        text = "Completed Challenges",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )

                    if (completedChallenges.isEmpty()) {
                        Text(
                            text = "No challenges completed yet. Start your journey!",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            modifier = Modifier.padding(16.dp)
                        )
                    } else {
                        completedChallenges.take(5).forEach { challengeId ->
                            AchievementItem(challengeId = challengeId)
                            Spacer(modifier = Modifier.height(8.dp))
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ProgressOverviewCard(
    totalPoints: Int,
    completedChallenges: Int,
    currentStreak: Int = 1,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        shape = MaterialTheme.shapes.large,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = "📊",
                fontSize = 48.sp
            )

            Text(
                text = "Your Journey",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                ProgressStat(
                    value = totalPoints.toString(),
                    label = "Total Points",
                    emoji = "⭐"
                )
                ProgressStat(
                    value = completedChallenges.toString(),
                    label = "Challenges",
                    emoji = "🏆"
                )
                ProgressStat(
                    value = currentStreak.toString(),
                    label = "Streak",
                    emoji = "🔥"
                )
            }
        }
    }
}

@Composable
fun ProgressStat(
    value: String,
    label: String,
    emoji: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Text(
            text = emoji,
            fontSize = 24.sp
        )
        Text(
            text = value,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )
        Text(
            text = label,
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onPrimaryContainer
        )
    }
}

@Composable
fun AchievementItem(
    challengeId: String,
    modifier: Modifier = Modifier
) {
    // Extract challenge info from the ID
    val challengeInfo = getChallengeInfo(challengeId)

    Card(
        modifier = modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.medium
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text(
                    text = challengeInfo.emoji,
                    fontSize = 24.sp
                )
                Column {
                    Text(
                        text = challengeInfo.title,
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.SemiBold
                    )
                    Text(
                        text = challengeInfo.description,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            PointsBadge(
                points = challengeInfo.points,
                backgroundColor = MaterialTheme.colorScheme.primary
            )
        }
    }
}

@Composable
fun AchievementBadgeItem(
    achievement: Achievement,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.medium,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.3f)
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Surface(
                    modifier = Modifier.size(48.dp),
                    shape = MaterialTheme.shapes.medium,
                    color = MaterialTheme.colorScheme.primary
                ) {
                    Box(
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = achievement.emoji,
                            fontSize = 28.sp
                        )
                    }
                }
                Column {
                    Text(
                        text = achievement.title,
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.SemiBold
                    )
                    Text(
                        text = achievement.description,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            PointsBadge(
                points = achievement.points,
                backgroundColor = MaterialTheme.colorScheme.primary
            )
        }
    }
}

@Composable
private fun getChallengeInfo(challengeId: String): ChallengeInfo {
    return when {
        challengeId.contains("happy") -> ChallengeInfo(
            emoji = "😊",
            title = "Happy Challenge",
            description = "Spread joy and positivity",
            points = if (challengeId.contains("2")) 30 else 30
        )
        challengeId.contains("sad") -> ChallengeInfo(
            emoji = "💙",
            title = "Comfort Challenge",
            description = "Gentle healing activities",
            points = if (challengeId.contains("2")) 30 else 25
        )
        challengeId.contains("cool") -> ChallengeInfo(
            emoji = "😎",
            title = "Cool Challenge",
            description = "Confidence building tasks",
            points = if (challengeId.contains("2")) 50 else 40
        )
        challengeId.contains("thinking") -> ChallengeInfo(
            emoji = "🤔",
            title = "Brain Challenge",
            description = "Mind-stimulating puzzles",
            points = if (challengeId.contains("2")) 60 else 40
        )
        challengeId.contains("tired") -> ChallengeInfo(
            emoji = "😴",
            title = "Relax Challenge",
            description = "Calming activities",
            points = if (challengeId.contains("2")) 20 else 20
        )
        else -> ChallengeInfo(
            emoji = "🎯",
            title = "Challenge Completed",
            description = "Great job!",
            points = 10
        )
    }
}

private data class ChallengeInfo(
    val emoji: String,
    val title: String,
    val description: String,
    val points: Int
)

