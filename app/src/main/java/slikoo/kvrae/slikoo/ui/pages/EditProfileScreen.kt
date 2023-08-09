package slikoo.kvrae.slikoo.ui.pages

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController

@Composable
fun EditProfileScreen(navController : NavController) {
    Box(Modifier
        .fillMaxSize(),
    ) {
        Text(text = "Edit Profile Screen")
    }


}


//    // on back pressed button on android devices
//    BackHandler {
//        navController.popBackStack()
//
//    }

