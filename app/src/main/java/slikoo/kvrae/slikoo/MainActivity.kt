package slikoo.kvrae.slikoo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import slikoo.kvrae.slikoo.ui.theme.SlikooTheme
import slikoo.kvrae.slikoo.utils.App

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SlikooTheme {
                App()
            }
        }
    }
}
