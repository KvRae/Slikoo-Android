package slikoo.kvrae.slikoo.ui.theme

import android.app.Activity
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

//private val DarkColorScheme = darkColorScheme(
//     primary = DarkPrimary,
//     secondary = DarkPrimaryVariant,
//     tertiary = DarkSurface,
//     background = DarkSecondary,
//     surface = DarkSecondary,
//     onPrimary = Color.White,
//     onSecondary = Color.White,
//     onTertiary = DarkSecondaryVariant,
//     onBackground = DarkBackground,
//     onSurface = DarkBackground
//)

private val lightColorScheme = lightColorScheme(
    primary = LightPrimary,
    secondary = LightPrimaryVariant,
    tertiary = LightSurface,
    background = LightSecondary,
    surface = LightSecondary,
    onPrimary = LightError,
    onSecondary = LightError,
    onTertiary = LightSecondaryVariant,
    onBackground = LightBackground,
    onSurface = LightBackground,

    )


@Composable
fun SlikooTheme(
//    darkTheme: Boolean = isSystemInDarkTheme(),
//    dynamicColor: Boolean = false, // Dynamic color is available on Android 12+
    content: @Composable () -> Unit
) {
//    val colorScheme = when {
////        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
////            val context = LocalContext.current
////            if (darkTheme) dynamicLightColorScheme(context) else dynamicLightColorScheme(context)
////        }
//
//        darkTheme -> LightColorScheme
//        else -> LightColorScheme
//    }
    // Set the status bar to be transparent
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = Color.Transparent.toArgb()
            window.navigationBarColor = Color.Transparent.toArgb()
            WindowCompat.getInsetsController(window, view)
                .isAppearanceLightStatusBars = false //dynamicColor || darkTheme

        }
    }

    // Set the navigation bar to be transparent and light
    SideEffect {
        val window = (view.context as Activity).window
        window.navigationBarColor = Color.Transparent.toArgb()
        WindowCompat.getInsetsController(window, view)
            .isAppearanceLightNavigationBars =  false //dynamicColor || darkTheme
    }

    MaterialTheme(
        colorScheme = lightColorScheme,
        typography = Typography,
        content = content
    )
}