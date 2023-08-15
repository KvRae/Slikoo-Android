package slikoo.kvrae.slikoo.ui.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import slikoo.kvrae.slikoo.ui.theme.LightPrimary
import slikoo.kvrae.slikoo.ui.theme.LightSecondary


@Composable
fun LoadingScreen() {
    Box(modifier = Modifier
        .fillMaxSize()
        .background(color = LightSecondary),
        contentAlignment = androidx.compose.ui.Alignment.Center
    ) {
        CircularProgressIndicator(
            color = LightPrimary,
            strokeWidth = 4.dp,
            modifier = Modifier.size(40.dp)
        )
    }

}

@Composable
fun EmptyElementsScreen() {
    Box(modifier = Modifier
        .fillMaxSize()
        .background(color = LightSecondary),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "No elements to show")
    }
}

@Composable
fun ErrorScreen() {
    Box(modifier = Modifier
        .fillMaxSize()
        .background(color = LightSecondary),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Error")
    }
}

@Composable
fun NoInternetScreen() {
    Box(modifier = Modifier
        .fillMaxSize()
        .background(color = LightSecondary),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "No Internet")
    }
}

@Composable
fun NoSearchResultsScreen() {
    Box(modifier = Modifier
        .fillMaxSize()
        .background(color = LightSecondary),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "No results found")
    }
}

@Composable
fun NoNotificationsScreen() {
    Box(modifier = Modifier
        .fillMaxSize()
        .background(color = LightSecondary),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "No notifications")
    }
}


