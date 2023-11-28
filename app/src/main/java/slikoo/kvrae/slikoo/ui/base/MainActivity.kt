package slikoo.kvrae.slikoo.ui.base

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.view.WindowCompat
import slikoo.kvrae.slikoo.ui.theme.SlikooTheme
import slikoo.kvrae.slikoo.utils.App

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window,
            false)
        setContent {
            SlikooTheme {
                App()
            }
        }
    }
}
