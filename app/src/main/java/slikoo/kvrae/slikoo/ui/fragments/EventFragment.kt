package slikoo.kvrae.slikoo.ui.fragments

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController


@Composable
fun EventScreen(navController: NavController) {
    Box(modifier = Modifier.fillMaxSize()
        .padding(top = 50.dp, bottom = 50.dp),
        contentAlignment = Alignment.Center
    ){
        Text(text = "Event Screen")
    }
}