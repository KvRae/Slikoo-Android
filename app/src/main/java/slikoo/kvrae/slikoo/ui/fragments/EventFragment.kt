package slikoo.kvrae.slikoo.ui.fragments

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import slikoo.kvrae.slikoo.ui.theme.ButtonsAndIcons
import slikoo.kvrae.slikoo.ui.theme.ScreenBackground


@Composable
fun EventScreen(navController: NavController) {
    Box(modifier = Modifier
        .fillMaxSize()
        .background(ButtonsAndIcons),
        contentAlignment = Alignment.Center
    ){
        Box(
            modifier = Modifier
                .fillMaxSize().background(ScreenBackground),
            contentAlignment = Alignment.Center
        ) {
            Text(text = "Event Screen")
        }
    }
}