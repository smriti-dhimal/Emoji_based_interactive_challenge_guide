package np.com.emoji_based_interactive_challenge_guide.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import np.com.emoji_based_interactive_challenge_guide.data.models.Challenge
import np.com.emoji_based_interactive_challenge_guide.data.models.Difficulty
import np.com.emoji_based_interactive_challenge_guide.data.models.MoodType
import np.com.emoji_based_interactive_challenge_guide.data.models.Task

class ChallengeRepository {
    
    private val challenges = listOf(
        Challenge(
            id = "happy_1",
            title = "Smile Challenge",
            description = "Complete these happy tasks to boost your mood!",
            moodType = MoodType.HAPPY,
            difficulty = Difficulty.EASY,
            points = 10,
            emojiHint = "😊",
            tasks = listOf(
                Task("1", "Think of 3 things that made you smile today", "😄"),
                Task("2", "Share a compliment with someone", "💝"),
                Task("3", "Do your favorite happy dance", "🕺")
            )
        ),
        Challenge(
            id = "sad_1",
            title = "Comfort Challenge",
            description = "Gentle activities to help you feel better",
            moodType = MoodType.SAD,
            difficulty = Difficulty.EASY,
            points = 15,
            emojiHint = "💙",
            tasks = listOf(
                Task("1", "Listen to your favorite comforting song", "🎵"),
                Task("2", "Write down your feelings", "📝"),
                Task("3", "Hug a pillow or pet", "🤗")
            )
        ),
        Challenge(
            id = "cool_1",
            title = "Confidence Boost",
            description = "Show off your cool side!",
            moodType = MoodType.COOL,
            difficulty = Difficulty.MEDIUM,
            points = 20,
            emojiHint = "😎",
            tasks = listOf(
                Task("1", "Strike a confident pose", "🦸"),
                Task("2", "Share your favorite achievement", "🏆"),
                Task("3", "Learn something new and impressive", "🧠")
            )
        ),
        Challenge(
            id = "thinking_1",
            title = "Mind Challenge",
            description = "Exercise your brain with thoughtful activities",
            moodType = MoodType.THINKING,
            difficulty = Difficulty.MEDIUM,
            points = 25,
            emojiHint = "🤔",
            tasks = listOf(
                Task("1", "Solve a puzzle", "🧩"),
                Task("2", "Read an interesting article", "📚"),
                Task("3", "Plan your week ahead", "📅")
            )
        ),
        Challenge(
            id = "tired_1",
            title = "Relaxation Challenge",
            description = "Gentle activities to help you unwind",
            moodType = MoodType.TIRED,
            difficulty = Difficulty.EASY,
            points = 10,
            emojiHint = "😴",
            tasks = listOf(
                Task("1", "Take 5 deep breaths", "🫁"),
                Task("2", "Stretch for 2 minutes", "🧘"),
                Task("3", "Close your eyes and rest", "😌")
            )
        )
    )

    private val _currentChallenge = MutableStateFlow<Challenge?>(null)
    val currentChallenge: Flow<Challenge?> = _currentChallenge.asStateFlow()

    fun getChallengeByMood(moodType: MoodType): Challenge? {
        return challenges.find { it.moodType == moodType }
    }

    fun setCurrentChallenge(challenge: Challenge) {
        _currentChallenge.value = challenge
    }

    fun getAllChallenges(): List<Challenge> = challenges

    fun getChallengeById(id: String): Challenge? {
        return challenges.find { it.id == id }
    }
}
