package slikoo.kvrae.slikoo.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

//private val DarkColorScheme = darkColorScheme(
//    /* primary = DarkPrimary,
//     secondary = DarkPrimaryVariant,
//     tertiary = DarkSurface,
//     background = DarkSecondary,
//     surface = DarkSecondary,
//     onPrimary = Color.White,
//     onSecondary = Color.White,
//     onTertiary = DarkSecondaryVariant,
//     onBackground = DarkBackground,
//     onSurface = DarkBackground*/
//)

private val LightColorScheme = lightColorScheme(
    primary = LightPrimary,
    secondary = LightPrimaryVariant,
    tertiary = LightSurface,
    background = LightSecondary,
    surface = LightSecondary,
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = LightSecondaryVariant,
    onBackground = LightBackground,
    onSurface = LightBackground,

    )

@Composable
fun SlikooTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = LightColorScheme,
        typography = Typography,
        content = content
    )
}

//@Composable
//fun SlikooTheme(
//    darkTheme: Boolean = isSystemInDarkTheme(),
//    dynamicColor: Boolean = true, // Dynamic color is available on Android 12+
//    content: @Composable () -> Unit
//) {
//    val colorScheme = when {
//        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
//            val context = LocalContext.current
//            if (darkTheme) dynamicLightColorScheme(context) else dynamicLightColorScheme(context)
//        }
//
//        darkTheme -> LightColorScheme
//        else -> LightColorScheme
//    }
//    // Set the status bar to be transparent
//    val view = LocalView.current
//    if (!view.isInEditMode) {
//        SideEffect {
//            val window = (view.context as Activity).window
//            window.statusBarColor = Color.Transparent.toArgb()
//            WindowCompat.getInsetsController(window, view)
//                .isAppearanceLightStatusBars = dynamicColor || darkTheme
//
//        }
//    }
//
//    // Set the navigation bar to be transparent and light
//    SideEffect {
//        val window = (view.context as Activity).window
//        window.navigationBarColor = Color.Transparent.toArgb()
//        WindowCompat.getInsetsController(window, view)
//            .isAppearanceLightNavigationBars = true
//    }
//
//    MaterialTheme(
//        colorScheme = colorScheme,
//        typography = Typography,
//        content = content
//    )
//}