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

        // Randomly select from available challenges for variety
        val randomChallenge = moodChallenges.randomOrNull()

        // Create dynamic variation of the selected challenge
        return randomChallenge?.let { createDynamicChallenge(it) }
    }

    private fun createDynamicChallenge(baseChallenge: Challenge): Challenge {
        // Randomize puzzle answers for variety
        val dynamicTasks = baseChallenge.tasks.map { task ->
            when (task.taskType) {
                TaskType.PUZZLE -> {
                    // Generate random puzzle variants
                    val randomPuzzle = getRandomPuzzle(task.difficulty)
                    task.copy(
                        description = randomPuzzle.first,
                        solution = randomPuzzle.second
                    )
                }
                TaskType.CREATIVE -> {
                    // Use mood-based creative tasks (open-ended)
                    val creativeTask = getRandomCreativeTask(baseChallenge.moodType)
                    task.copy(description = creativeTask)
                }
                TaskType.SOCIAL -> {
                    // Use mood-based social tasks
                    val socialTask = getRandomSocialTask(baseChallenge.moodType)
                    task.copy(description = socialTask)
                }
            }
        }

        return baseChallenge.copy(tasks = dynamicTasks)
    }

    private fun getRandomPuzzle(difficulty: Difficulty): Pair<String, String> {
        // Randomly choose puzzle type: 20% each (5 types)
        val puzzleType = kotlin.random.Random.nextInt(5)
        return when (puzzleType) {
            0 -> getRandomWordScramble(difficulty)
            1 -> getRandomNumberPuzzle(difficulty)
            2 -> getRandomPatternPuzzle(difficulty)
            3 -> getRandomWordGame(difficulty)
            4 -> getRandomMemoryPuzzle(difficulty)
            else -> when (difficulty) {
                Difficulty.EASY -> easyPuzzles.random()
                Difficulty.MEDIUM -> mediumPuzzles.random()
                Difficulty.HARD -> hardPuzzles.random()
            }
        }
    }

    private fun getRandomWordScramble(difficulty: Difficulty): Pair<String, String> {
        return when (difficulty) {
            Difficulty.EASY -> easyWordScrambles.random()
            Difficulty.MEDIUM -> mediumWordScrambles.random()
            Difficulty.HARD -> hardWordScrambles.random()
        }
    }

    private fun getRandomNumberPuzzle(difficulty: Difficulty): Pair<String, String> {
        return when (difficulty) {
            Difficulty.EASY -> easyNumberPuzzles.random()
            Difficulty.MEDIUM -> mediumNumberPuzzles.random()
            Difficulty.HARD -> hardNumberPuzzles.random()
        }
    }

    private fun getRandomPatternPuzzle(difficulty: Difficulty): Pair<String, String> {
        return when (difficulty) {
            Difficulty.EASY -> easyPatternPuzzles.random()
            Difficulty.MEDIUM -> mediumPatternPuzzles.random()
            Difficulty.HARD -> hardPatternPuzzles.random()
        }
    }

    private fun getRandomWordGame(difficulty: Difficulty): Pair<String, String> {
        return when (difficulty) {
            Difficulty.EASY -> easyWordGames.random()
            Difficulty.MEDIUM -> mediumWordGames.random()
            Difficulty.HARD -> hardWordGames.random()
        }
    }

    private fun getRandomMemoryPuzzle(difficulty: Difficulty): Pair<String, String> {
        return when (difficulty) {
            Difficulty.EASY -> easyMemoryPuzzles.random()
            Difficulty.MEDIUM -> mediumMemoryPuzzles.random()
            Difficulty.HARD -> hardMemoryPuzzles.random()
        }
    }

    private fun getRandomCreativeTask(moodType: MoodType): String {
        return when (moodType) {
            MoodType.HAPPY -> happyCreativeTasks.random()
            MoodType.SAD -> sadCreativeTasks.random()
            MoodType.COOL -> coolCreativeTasks.random()
            MoodType.THINKING -> thinkingCreativeTasks.random()
            MoodType.TIRED -> tiredCreativeTasks.random()
        }
    }

    private fun getRandomSocialTask(moodType: MoodType): String {
        return when (moodType) {
            MoodType.HAPPY -> happySocialTasks.random()
            MoodType.SAD -> sadSocialTasks.random()
            MoodType.COOL -> coolSocialTasks.random()
            MoodType.THINKING -> thinkingSocialTasks.random()
            MoodType.TIRED -> tiredSocialTasks.random()
        }
    }



    fun setCurrentChallenge(challenge: Challenge) {
        _currentChallenge.value = challenge
    }

    fun getAllChallenges(): List<Challenge> = challenges

    fun getChallengeById(id: String): Challenge? {
        return challenges.find { it.id == id }
    }

    // Dynamic puzzle collections for variety
    private val easyPuzzles = listOf(
        "What has a face and two hands but no arms or legs?" to "clock",
        "What gets wet while drying?" to "towel",
        "What runs but never walks?" to "river",
        "What has keys but no locks?" to "piano",
        "What must be broken before use?" to "egg"
    )

    private val mediumPuzzles = listOf(
        "What has an eye but cannot see?" to "needle",
        "What can you catch but not throw?" to "cold",
        "What has a neck without a head?" to "bottle",
        "What can you break without touching it?" to "promise",
        "Complete sequence: 2, 4, 8, 16, ?" to "32"
    )

    private val hardPuzzles = listOf(
        "What word becomes shorter when you add 2 letters?" to "short",
        "I speak without a mouth and hear without ears. What am I?" to "echo",
        "The more you take, the more you leave behind. What am I?" to "footsteps",
        "What has cities, but no houses; forests, but no trees; and water, but no fish?" to "map"
    )


    // Word scramble
    private val easyWordScrambles = listOf(
        "Unscramble: iertg" to "tiger",
        "Unscramble: act" to "cat",
        "Unscramble: ogd" to "dog",
        "Unscramble: tac" to "cat",
        "Unscramble: pzi" to "zip"
    )

    private val mediumWordScrambles = listOf(
        "Unscramble: elppah" to "happy",
        "Unscramble: elims" to "smile",
        "Unscramble: trahe" to "heart",
        "Unscramble: caep" to "peace",
        "Unscramble: mared" to "dream",
    )

    private val hardWordScrambles = listOf(
        "Unscramble: llahcgene" to "challenge",
        "Unscramble: gnihktni" to "thinking",
        "Unscramble: ativerce" to "creative",
        "Unscramble: gnizama" to "amazing",
        "Unscramble: liantlirb" to "brilliant"
    )

    // Number puzzles
    private val easyNumberPuzzles = listOf(
        "Complete: 2, 4, 6, ?" to "8",
        "Solve: 5 + ? = 12" to "7",
        "Complete: 1, 3, 5, ?" to "7",
        "Solve: 10 - ? = 3" to "7",
        "Complete: 10, 20, 30, ?" to "40"
    )

    private val mediumNumberPuzzles = listOf(
        "Complete: 3, 6, 9, 12, ?" to "15",
        "Solve: 8 × ? = 48" to "6",
        "Complete: 5, 10, 15, ?" to "20",
        "Solve: ? + 15 = 25" to "10",
        "Complete: 2, 4, 8, 16, ?" to "32"
    )

    private val hardNumberPuzzles = listOf(
        "Complete: 1, 4, 9, 16, ?" to "25",
        "Solve: 7 × 8 - ? = 40" to "16",
        "Complete: 2, 3, 5, 8, 13, ?" to "21",
        "Solve: 100 ÷ 5 + ? = 30" to "10",
        "Complete: 1, 1, 2, 3, 5, 8, ?" to "13"
    )

    // Pattern recognition
    private val easyPatternPuzzles = listOf(
        "Complete: 😊😂😊😂?" to "😊",
        "Complete: Triangle, Circle, Triangle, Circle, ?" to "Triangle",
        "Complete: Red, Blue, Red, ?" to "Blue",
        "Complete: 🌟🌟🌟?" to "🌟",
        "Complete: A, B, A, B, ?" to "A"
    )

    private val mediumPatternPuzzles = listOf(
        "Complete: 😊😂🤣😊😂?" to "🤣",
        "Complete: Triangle, Circle, Square, Triangle, Circle, ?" to "Square",
        "Complete: Red, Blue, Green, Red, Blue, ?" to "Green",
        "Complete: 🌟⭐✨🌟⭐?" to "✨",
        "Complete: A, C, E, G, ?" to "I"
    )

    private val hardPatternPuzzles = listOf(
        "Complete: 😊😂🤣😂😊😂?" to "🤣",
        "Complete: Triangle, Circle, Square, Diamond, Triangle, Circle, ?" to "Square",
        "Complete: Monday, Wednesday, Friday, ?" to "Sunday",
        "Complete: 🌟⭐✨💫🌟⭐?" to "✨",
        "Complete: A, D, G, J, ?" to "M"
    )

    // Simple word
    private val easyWordGames = listOf(
        "Fill blanks: H_P_Y" to "HAPPY",
        "Fill blanks: C_T" to "CAT",
        "Fill blanks: D_G" to "DOG",
        "Fill blanks: S_N" to "SUN",
        "Fill blanks: M__N" to "MOON",
    )

    private val mediumWordGames = listOf(
        "Fill blanks: SM_LE" to "SMILE",
        "Fill blanks: HE_RT" to "HEART",
        "Fill blanks: PE_CE" to "PEACE",
        "Fill blanks: DR_AM" to "DREAM",
        "Fill blanks: L_GHT" to "LIGHT",
    )

    private val hardWordGames = listOf(
        "Fill blanks: CH_LLEN_E" to "CHALLENGE",
        "Fill blanks: TH_NKIN_" to "THINKING",
        "Fill blanks: CR__TIV_" to "CREATIVE",
        "Fill blanks: AM_ZIN_" to "AMAZING",
        "Fill blanks: BR_LLIAN_" to "BRILLIANT"
    )

    // Memory sequence puzzles
    private val easyMemoryPuzzles = listOf(
        "Memory: Remember these emojis: 😊🌟" to "😊🌟",
        "Memory: Remember these emojis: 🎯🚀" to "🎯🚀",
        "Memory: Remember these emojis: 💙🎨" to "💙🎨",
        "Memory: Remember these emojis: 🧩📝" to "🧩📝",
        "Memory: Remember these emojis: 🌙⭐" to "🌙⭐"
    )

    private val mediumMemoryPuzzles = listOf(
        "Memory: Remember these emojis: 😊🌟🎯" to "😊🌟🎯",
        "Memory: Remember these emojis: 🚀💙🎨" to "🚀💙🎨",
        "Memory: Remember these emojis: 🧩📝🌙" to "🧩📝🌙",
        "Memory: Remember these emojis: ⭐😊🌟" to "⭐😊🌟",
        "Memory: Remember these emojis: 🎯🚀💙" to "🎯🚀💙"
    )

    private val hardMemoryPuzzles = listOf(
        "Memory: Remember these emojis: 😊🌟🎯🚀" to "😊🌟🎯🚀",
        "Memory: Remember these emojis: 💙🎨🧩📝" to "💙🎨🧩📝",
        "Memory: Remember these emojis: 🌙⭐😊🌟" to "🌙⭐😊🌟",
        "Memory: Remember these emojis: 🎯🚀💙🎨" to "🎯🚀💙🎨",
        "Memory: Remember these emojis: 🧩📝🌙⭐" to "🧩📝🌙⭐"
    )

    // Fun creative tasks
    private val happyCreativeTasks = listOf(
        "Write a short poem about happiness: ?",
        "Create a story with 4 emojis: 😊→🌟→🎯→🚀 = ?",
        "Complete poem: Happy as a ☀️, bright as a ⭐ = ?",
        "Make up a word: Happy + Sunshine = ?"
    )

    private val sadCreativeTasks = listOf(
        "Write a short poem about comfort: ?",
        "Create a story with 4 emojis: 🌙→⭐→💙→💭 = ?",
        "Complete poem: Peaceful as 🌙, gentle as ⭐ = ?",
        "Make up a word: Peace + Calm = ?"
    )

    private val coolCreativeTasks = listOf(
        "Write a short poem about confidence: ?",
        "Create a story with 4 emojis: 🎯→🚀→⚡→🔥 = ?",
        "Complete poem: Cool as ⚡, strong as 🎯 = ?",
        "Make up a word: Cool + Fire = ?"
    )

    private val thinkingCreativeTasks = listOf(
        "Write a short poem about wisdom: ?",
        "Create a story with 4 emojis: 🧠→💡→🎯→📚 = ?",
        "Complete poem: Wise as 🧠, bright as 💡 = ?",
        "Make up a word: Smart + Idea = ?"
    )

    private val tiredCreativeTasks = listOf(
        "Write a short poem about rest: ?",
        "Create a story with 4 emojis: 🌙→😴→💭→🌸 = ?",
        "Complete poem: Calm as 🌙, peaceful as 😴 = ?",
        "Make up a word: Sleep + Peace = ?"
    )

    // Mood-based social tasks
    private val happySocialTasks = listOf(
        "Flirting line: Are you a ⭐? (A) Because you shine bright (B) Because you're hot",
        "Pickup line: Did it hurt when you ? (A) Fell from heaven (B) Stole my heart",
        "Write a happy compliment: Your smile is like ?",
        "Share good news: I'm so happy because ?"
    )

    private val sadSocialTasks = listOf(
        "Write a comforting message: It's okay to feel ?",
        "Share support: I'm here for you because ?",
        "Write a caring compliment: Your strength is like ?",
        "Offer help: Let me help you with ?"
    )

    private val coolSocialTasks = listOf(
        "Flirting line: Are you a camera? (A) Because you make me smile (B) Every time I see you",
        "Pickup line: Are you a parking ticket? (A) Because you've got fine written (B) All over you",
        "Write a cool compliment: Your style is like ?",
        "Share confidence: You're amazing because ?"
    )

    private val thinkingSocialTasks = listOf(
        "Write a thoughtful compliment: Your mind is like ?",
        "Share wisdom: I've learned that ?",
        "Write an intelligent message: Your perspective is ?",
        "Share insight: The most important thing is ?"
    )

    private val tiredSocialTasks = listOf(
        "Write a gentle message: Rest is important because ?",
        "Share comfort: Take your time with ?",
        "Write a caring message: You deserve to ?",
        "Offer rest: Let me help you ?"
    )
}
