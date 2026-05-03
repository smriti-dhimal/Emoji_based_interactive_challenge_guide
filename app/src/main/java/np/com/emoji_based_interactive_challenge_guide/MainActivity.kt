package np.com.emoji_based_interactive_challenge_guide

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import np.com.emoji_based_interactive_challenge_guide.navigation.AppNavigation
import np.com.emoji_based_interactive_challenge_guide.ui.theme.EmojiTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            EmojiTheme {
                AppNavigation()
            }
        }
    }
}