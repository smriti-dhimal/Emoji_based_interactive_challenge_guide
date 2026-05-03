package np.com.emoji_based_interactive_challenge_guide.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.graphics.Color

// Composition locals for custom theme values
data class EmojiColors(
    val happy: Color,
    val sad: Color,
    val cool: Color,
    val thinking: Color,
    val tired: Color,
    val success: Color,
    val warning: Color,
    val info: Color,
    val error: Color
)

val LocalEmojiColors = compositionLocalOf {
    EmojiColors(
        happy = HappyColor,
        sad = SadColor,
        cool = CoolColor,
        thinking = ThinkingColor,
        tired = TiredColor,
        success = SuccessColor,
        warning = WarningColor,
        info = InfoColor,
        error = ErrorColor
    )
}

@Composable
fun EmojiTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) {
        darkColorScheme(
            primary = DarkPrimary,
            onPrimary = DarkOnPrimary,
            secondary = DarkSecondary,
            onSecondary = DarkOnSecondary,
            background = DarkBackground,
            onBackground = DarkOnBackground,
            surface = DarkSurface,
            onSurface = DarkOnSurface,
            error = DarkError,
            onError = DarkOnError
        )
    } else {
        lightColorScheme(
            primary = LightPrimary,
            onPrimary = LightOnPrimary,
            secondary = LightSecondary,
            onSecondary = LightOnSecondary,
            background = LightBackground,
            onBackground = LightOnBackground,
            surface = LightSurface,
            onSurface = LightOnSurface,
            error = LightError,
            onError = LightOnError
        )
    }

    val emojiColors = EmojiColors(
        happy = HappyColor,
        sad = SadColor,
        cool = CoolColor,
        thinking = ThinkingColor,
        tired = TiredColor,
        success = SuccessColor,
        warning = WarningColor,
        info = InfoColor,
        error = ErrorColor
    )

    CompositionLocalProvider(
        LocalEmojiColors provides emojiColors
    ) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = Typography,
            shapes = Shapes,
            content = content
        )
    }
}

// Extension functions for easy access to emoji colors
object EmojiTheme {
    val colors: EmojiColors
        @Composable get() = LocalEmojiColors.current
}