package slikoo.kvrae.slikoo.ui.pages


import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun AnimatedSplashScreen() {
    val startAnimation by remember {
        mutableStateOf(false)
    }
    val alphaAnim = animateFloatAsState(targetValue = if (startAnimation) 1f else 0f,
        animationSpec = tween(
            durationMillis = 1000
        )
    )
    
}

@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun Splash() {
    Box(
        modifier = Modifier
            .background(
                if (isSystemInDarkTheme()) Color.Black else Color.Blue
            )
            .fillMaxSize(),
    contentAlignment = Alignment.Center
    ){
        Icon(
            modifier = Modifier.size(120.dp),
            imageVector = Icons.Default.Email,
            contentDescription = "Splash Screen Logo",
            tint = Color.White
        )
    }

}