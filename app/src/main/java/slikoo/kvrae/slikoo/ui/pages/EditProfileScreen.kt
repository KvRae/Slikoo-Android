package slikoo.kvrae.slikoo.ui.pages

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun EditProfileScreen() {
    Box(Modifier
        .fillMaxSize(),
    ) {
        Text(text = "Edit Profile Screen")
    }
    // on back pressed button on android devices
    BackHandler {

    }
}




