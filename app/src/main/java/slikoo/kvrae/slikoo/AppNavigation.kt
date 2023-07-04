package slikoo.kvrae.slikoo


import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import slikoo.kvrae.slikoo.ui.pages.AnimatedSplashScreen
import slikoo.kvrae.slikoo.ui.pages.LoginForm
import slikoo.kvrae.slikoo.ui.pages.MainScreen
import slikoo.kvrae.slikoo.ui.pages.SignUp
import slikoo.kvrae.slikoo.utils.ScreenNavigator


@Composable
fun App() {
    Navigation()
    }

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = ScreenNavigator.SplashScreen.route) {
        composable(route = ScreenNavigator.SignInScreen.route) {
           LoginForm(navController = navController)
        }
        composable(route = ScreenNavigator.SignUpScreen.route) {
            SignUp(navController = navController)
        }
        composable(route = ScreenNavigator.HomeScreen.route) {
            MainScreen(navController = navController)
        }
        composable(route = ScreenNavigator.SplashScreen.route) {
            AnimatedSplashScreen(navController = navController)
        }


    }
}



