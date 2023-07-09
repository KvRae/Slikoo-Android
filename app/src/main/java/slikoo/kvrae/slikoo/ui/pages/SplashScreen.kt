package slikoo.kvrae.slikoo.ui.pages

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kotlinx.coroutines.delay
import slikoo.kvrae.slikoo.R
import slikoo.kvrae.slikoo.ui.theme.ButtonsAndIcons
import slikoo.kvrae.slikoo.ui.theme.SecondaryWhiteText


@Composable
fun AnimatedSplashScreen(navController: NavController) {
    var startAnimation by remember { mutableStateOf(false) }
    val alphaAnim = animateFloatAsState(targetValue = if (startAnimation) 1f else 0f,
        animationSpec = tween(
            durationMillis = 3000
        )
    )
    LaunchedEffect(key1 = true ){
        startAnimation = true
        delay(3000)
        navController.navigate("sign_in_screen"){
            popUpTo("splash_screen"){
                inclusive = true
            }
        }

    }
    Splash(alpha = alphaAnim.value)
}


@Composable
fun Splash(alpha: Float) {
    val logo = if (isSystemInDarkTheme()) R.drawable.slikoo_white else R.drawable.logo2
    Box(
        modifier = Modifier
            .background(
                if (isSystemInDarkTheme()) ButtonsAndIcons else SecondaryWhiteText
            )
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        Image(
            painter = painterResource(id = logo), // Load the drawable resource
            contentDescription = "Logo",
            modifier = Modifier.size(200.dp).alpha(alpha)
        )
    }
}