package np.com.emoji_based_interactive_challenge_guide.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import np.com.emoji_based_interactive_challenge_guide.data.models.Challenge
import np.com.emoji_based_interactive_challenge_guide.data.models.Difficulty
import np.com.emoji_based_interactive_challenge_guide.data.models.MoodType
import np.com.emoji_based_interactive_challenge_guide.data.models.Task
import np.com.emoji_based_interactive_challenge_guide.data.models.TaskType
import np.com.emoji_based_interactive_challenge_guide.data.models.VerificationMethod
import np.com.emoji_based_interactive_challenge_guide.data.models.VerificationStatus

class ChallengeRepository private constructor() {

    private val challenges = listOf(
        Challenge(
            id = "happy_1",
            title = "Happy Mix Challenge",
            description = "Solve puzzles, create happy things, and spread joy!",
            moodType = MoodType.HAPPY,
            difficulty = Difficulty.EASY,
            points = 30,
            emojiHint = "😊",
            tasks = listOf(
                Task("1", "Solve this riddle: What has a face and two hands but no arms or legs?", "🕐", false, TaskType.PUZZLE, "clock", "", VerificationMethod.NONE, "", VerificationStatus.PENDING, Difficulty.EASY, 5),
                Task("2", "Creative: Draw something that makes you happy and describe it", "🎨", false, TaskType.CREATIVE, "", "", VerificationMethod.TEXT_INPUT, "", VerificationStatus.PENDING, Difficulty.MEDIUM, 10),
                Task("3", "Social: Send a compliment to someone right now", "💬", false, TaskType.SOCIAL, "", "", VerificationMethod.TEXT_INPUT, "", VerificationStatus.PENDING, Difficulty.EASY, 5),
                Task("4", "Puzzle: Complete the pattern: 😊😂😊😂😊?", "🔢", false, TaskType.PUZZLE, "😂", "", VerificationMethod.NONE, "", VerificationStatus.PENDING, Difficulty.MEDIUM, 10)
            )
        ),
        Challenge(
            id = "happy_2",
            title = "Joy & Expression Challenge",
            description = "Mix of brain teasers, creativity, and social connection!",
            moodType = MoodType.HAPPY,
            difficulty = Difficulty.MEDIUM,
            points = 30,
            emojiHint = "🌟",
            tasks = listOf(
                Task("1", "Creative: Write a 4-line happy poem", "✍️", false, TaskType.CREATIVE, "", "", VerificationMethod.TEXT_INPUT, "", VerificationStatus.PENDING, Difficulty.MEDIUM, 10),
                Task("2", "Puzzle: If laughter is medicine, and medicine = health, then laughter = ?", "🧠", false, TaskType.PUZZLE, "health", "", VerificationMethod.NONE, "", VerificationStatus.PENDING, Difficulty.MEDIUM, 10),
                Task("3", "Social: Share a happy memory with a friend", "📝", false, TaskType.SOCIAL, "", "", VerificationMethod.TEXT_INPUT, "", VerificationStatus.PENDING, Difficulty.EASY, 5),
                Task("4", "Puzzle: Monday=1, Tuesday=2, Friday=?", "📅", false, TaskType.PUZZLE, "5", "", VerificationMethod.NONE, "", VerificationStatus.PENDING, Difficulty.EASY, 5)
            )
        ),
        Challenge(
            id = "sad_1",
            title = "Comfort & Healing Mix",
            description = "Gentle puzzles, creative expression, and supportive activities",
            moodType = MoodType.SAD,
            difficulty = Difficulty.EASY,
            points = 25,
            emojiHint = "💙",
            tasks = listOf(
                Task("1", "Puzzle: What gets wet while drying?", "🏖️", false, TaskType.PUZZLE, "towel", "", VerificationMethod.NONE, "", VerificationStatus.PENDING, Difficulty.EASY, 5),
                Task("2", "Creative: Draw your current feelings using colors", "🎨", false, TaskType.CREATIVE, "", "", VerificationMethod.TEXT_INPUT, "", VerificationStatus.PENDING, Difficulty.MEDIUM, 10),
                Task("3", "Social: Text someone you trust for support", "💬", false, TaskType.SOCIAL, "", "", VerificationMethod.TEXT_INPUT, "", VerificationStatus.PENDING, Difficulty.EASY, 5),
                Task("4", "Puzzle: Complete: 💧🌧️💧🌧️💧?", "🔢", false, TaskType.PUZZLE, "🌧️", "", VerificationMethod.NONE, "", VerificationStatus.PENDING, Difficulty.EASY, 5)
            )
        ),
        Challenge(
            id = "sad_2",
            title = "Gentle Recovery Mix",
            description = "Soothing puzzles, creative comfort, and social connection",
            moodType = MoodType.SAD,
            difficulty = Difficulty.MEDIUM,
            points = 30,
            emojiHint = "🌙",
            tasks = listOf(
                Task("1", "Creative: Create a comfort box design", "📦", false, TaskType.CREATIVE, "", "", VerificationMethod.TEXT_INPUT, "", VerificationStatus.PENDING, Difficulty.MEDIUM, 10),
                Task("2", "Puzzle: What can you catch but not throw?", "🤧", false, TaskType.PUZZLE, "cold", "", VerificationMethod.NONE, "", VerificationStatus.PENDING, Difficulty.MEDIUM, 10),
                Task("3", "Social: Write a supportive message to yourself", "💌", false, TaskType.SOCIAL, "", "", VerificationMethod.TEXT_INPUT, "", VerificationStatus.PENDING, Difficulty.EASY, 5),
                Task("4", "Puzzle: Healing takes 7 days, you're on day 3, days left?", "📆", false, TaskType.PUZZLE, "4", "", VerificationMethod.NONE, "", VerificationStatus.PENDING, Difficulty.EASY, 5)
            )
        ),
        Challenge(
            id = "cool_1",
            title = "Cool Confidence Mix",
            description = "Smart puzzles, creative branding, and social confidence!",
            moodType = MoodType.COOL,
            difficulty = Difficulty.MEDIUM,
            points = 40,
            emojiHint = "😎",
            tasks = listOf(
                Task("1", "Puzzle: What has an eye but cannot see?", "🪡", false, TaskType.PUZZLE, "needle", "", VerificationMethod.NONE, "", VerificationStatus.PENDING, Difficulty.MEDIUM, 10),
                Task("2", "Creative: Design your personal logo", "🎭", false, TaskType.CREATIVE, "", "", VerificationMethod.TEXT_INPUT, "", VerificationStatus.PENDING, Difficulty.HARD, 15),
                Task("3", "Social: Start a conversation with someone new", "🗣️", false, TaskType.SOCIAL, "", "", VerificationMethod.TEXT_INPUT, "", VerificationStatus.PENDING, Difficulty.HARD, 15),
                Task("4", "Puzzle: If you're rank #3 out of 10, how many are cooler?", "🏆", false, TaskType.PUZZLE, "2", "", VerificationMethod.NONE, "", VerificationStatus.PENDING, Difficulty.EASY, 5)
            )
        ),
        Challenge(
            id = "cool_2",
            title = "Innovation & Style Mix",
            description = "Creative challenges, smart puzzles, and social influence!",
            moodType = MoodType.COOL,
            difficulty = Difficulty.HARD,
            points = 50,
            emojiHint = "🔥",
            tasks = listOf(
                Task("1", "Creative: Invent a solution to a daily problem", "💡", false, TaskType.CREATIVE, "", "", VerificationMethod.TEXT_INPUT, "", VerificationStatus.PENDING, Difficulty.HARD, 15),
                Task("2", "Puzzle: What can you break without touching it?", "🤝", false, TaskType.PUZZLE, "promise", "", VerificationMethod.NONE, "", VerificationStatus.PENDING, Difficulty.HARD, 15),
                Task("3", "Social: Share your talent with others", "🎯", false, TaskType.SOCIAL, "", "", VerificationMethod.TEXT_INPUT, "", VerificationStatus.PENDING, Difficulty.MEDIUM, 10),
                Task("4", "Puzzle: Find 'COOL' hidden in 'SCHOOL'", "🔍", false, TaskType.PUZZLE, "school", "", VerificationMethod.NONE, "", VerificationStatus.PENDING, Difficulty.MEDIUM, 10)
            )
        ),
        Challenge(
            id = "thinking_1",
            title = "Brain Power Mix",
            description = "Logic puzzles, creative problem-solving, and intellectual sharing!",
            moodType = MoodType.THINKING,
            difficulty = Difficulty.MEDIUM,
            points = 40,
            emojiHint = "🧩",
            tasks = listOf(
                Task("1", "Puzzle: What has keys but no locks?", "🎹", false, TaskType.PUZZLE, "piano", "", VerificationMethod.NONE, "", VerificationStatus.PENDING, Difficulty.MEDIUM, 10),
                Task("2", "Creative: Design an app that solves a real problem", "📱", false, TaskType.CREATIVE, "", "", VerificationMethod.TEXT_INPUT, "", VerificationStatus.PENDING, Difficulty.HARD, 15),
                Task("3", "Social: Share an interesting fact with someone", "📚", false, TaskType.SOCIAL, "", "", VerificationMethod.TEXT_INPUT, "", VerificationStatus.PENDING, Difficulty.EASY, 5),
                Task("4", "Puzzle: Complete sequence: 2, 4, 8, 16, ?", "🔢", false, TaskType.PUZZLE, "32", "", VerificationMethod.NONE, "", VerificationStatus.PENDING, Difficulty.MEDIUM, 10)
            )
        ),
        Challenge(
            id = "thinking_2",
            title = "Advanced Thinking Mix",
            description = "Complex puzzles, creative innovation, and intellectual discussion!",
            moodType = MoodType.THINKING,
            difficulty = Difficulty.HARD,
            points = 60,
            emojiHint = "💡",
            tasks = listOf(
                Task("1", "Creative: Write a story with an unexpected twist", "📖", false, TaskType.CREATIVE, "", "", VerificationMethod.TEXT_INPUT, "", VerificationStatus.PENDING, Difficulty.HARD, 15),
                Task("2", "Puzzle: What has a neck without a head?", "🍾", false, TaskType.PUZZLE, "bottle", "", VerificationMethod.NONE, "", VerificationStatus.PENDING, Difficulty.HARD, 15),
                Task("3", "Social: Discuss a complex topic with someone", "💬", false, TaskType.SOCIAL, "", "", VerificationMethod.TEXT_INPUT, "", VerificationStatus.PENDING, Difficulty.HARD, 15),
                Task("4", "Puzzle: What word becomes shorter when you add 2 letters?", "📝", false, TaskType.PUZZLE, "short", "", VerificationMethod.NONE, "", VerificationStatus.PENDING, Difficulty.HARD, 15)
            )
        ),
        Challenge(
            id = "tired_1",
            title = "Gentle Mind Mix",
            description = "Easy puzzles, relaxing creativity, and mindful social activities!",
            moodType = MoodType.TIRED,
            difficulty = Difficulty.EASY,
            points = 20,
            emojiHint = "😴",
            tasks = listOf(
                Task("1", "Puzzle: What runs but never walks?", "🌊", false, TaskType.PUZZLE, "river", "", VerificationMethod.NONE, "", VerificationStatus.PENDING, Difficulty.EASY, 5),
                Task("2", "Creative: Color a mandala or draw peaceful patterns", "🎨", false, TaskType.CREATIVE, "", "", VerificationMethod.TEXT_INPUT, "", VerificationStatus.PENDING, Difficulty.EASY, 5),
                Task("3", "Social: Send a calming message to someone", "🕊️", false, TaskType.SOCIAL, "", "", VerificationMethod.TEXT_INPUT, "", VerificationStatus.PENDING, Difficulty.EASY, 5),
                Task("4", "Puzzle: Need 8 hours sleep, got 6, how many more?", "⏰", false, TaskType.PUZZLE, "2", "", VerificationMethod.NONE, "", VerificationStatus.PENDING, Difficulty.EASY, 5)
            )
        ),
        Challenge(
            id = "tired_2",
            title = "Relaxation Mix",
            description = "Calming puzzles, mindful creativity, and gentle social connection!",
            moodType = MoodType.TIRED,
            difficulty = Difficulty.EASY,
            points = 15,
            emojiHint = "🌸",
            tasks = listOf(
                Task("1", "Creative: Create a vision board of peaceful places", "🖼️", false, TaskType.CREATIVE, "", "", VerificationMethod.TEXT_INPUT, "", VerificationStatus.PENDING, Difficulty.EASY, 5),
                Task("2", "Puzzle: What must be broken before use?", "🥚", false, TaskType.PUZZLE, "egg", "", VerificationMethod.NONE, "", VerificationStatus.PENDING, Difficulty.EASY, 5),
                Task("3", "Social: Share a calming quote with someone", "📜", false, TaskType.SOCIAL, "", "", VerificationMethod.TEXT_INPUT, "", VerificationStatus.PENDING, Difficulty.EASY, 5),
                Task("4", "Puzzle: Complete: 1, 3, 5, 7, ?", "🔢", false, TaskType.PUZZLE, "9", "", VerificationMethod.NONE, "", VerificationStatus.PENDING, Difficulty.EASY, 5)
            )
        )
    )

    private val _currentChallenge = MutableStateFlow<Challenge?>(null)
    val currentChallenge: Flow<Challenge?> = _currentChallenge.asStateFlow()

    companion object {
        @Volatile
        private var INSTANCE: ChallengeRepository? = null

        fun getInstance(): ChallengeRepository {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: ChallengeRepository().also { INSTANCE = it }
            }
        }
    }

    fun getChallengeByMood(moodType: MoodType): Challenge? {
        val moodChallenges = challenges.filter { it.moodType == moodType }

        // Sort by difficulty: EASY -> MEDIUM -> HARD
        val sortedChallenges = moodChallenges.sortedBy { challenge ->
            when (challenge.difficulty) {
                Difficulty.EASY -> 0
                Difficulty.MEDIUM -> 1
                Difficulty.HARD -> 2
            }
        }

        // Return the first available challenge (progressive difficulty)
        return sortedChallenges.firstOrNull()
    }

    fun setCurrentChallenge(challenge: Challenge) {
        _currentChallenge.value = challenge
    }

    fun getAllChallenges(): List<Challenge> = challenges

    fun getChallengeById(id: String): Challenge? {
        return challenges.find { it.id == id }
    }
}
