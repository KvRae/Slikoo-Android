package slikoo.kvrae.slikoo.utils


import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import slikoo.kvrae.slikoo.ui.fragments.EventScreen
import slikoo.kvrae.slikoo.ui.fragments.HomeScreen
import slikoo.kvrae.slikoo.ui.fragments.NotificationScreen
import slikoo.kvrae.slikoo.ui.fragments.RecipeScreen
import slikoo.kvrae.slikoo.ui.fragments.SettingsScreen
import slikoo.kvrae.slikoo.ui.pages.AnimatedSplashScreen
import slikoo.kvrae.slikoo.ui.pages.EmailInput
import slikoo.kvrae.slikoo.ui.pages.LoginForm
import slikoo.kvrae.slikoo.ui.pages.MainScreen
import slikoo.kvrae.slikoo.ui.pages.OtpInput
import slikoo.kvrae.slikoo.ui.pages.PasswordReset
import slikoo.kvrae.slikoo.ui.pages.SignUp


@Composable
fun App() {
    Navigation()
}

sealed class ScreenNavigator(val route: String){
    object SplashScreen: ScreenNavigator("splash_screen")
    object SignInScreen: ScreenNavigator("sign_in_screen")
    object SignUpScreen: ScreenNavigator("sign_up_screen")
    object MainScreen: ScreenNavigator("main_screen")
    object ForgotPasswordScreen: ScreenNavigator("forgot_password_screen")
    object VerifyEmailScreen: ScreenNavigator("verify_email_screen")
    object ResetPasswordScreen: ScreenNavigator("reset_password_screen")
}

sealed class MainScreenNavigator(val route: String){
    object HomeScreen: MainScreenNavigator("home_screen")
    object RecipeScreen: MainScreenNavigator("recipe_screen")
    object SettingsScreen: MainScreenNavigator("settings_screen")
    object NotificationScreen: MainScreenNavigator("notification_screen")
    object EventScreen: MainScreenNavigator("event_screen")
}

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        // Set start destination
        startDestination = ScreenNavigator.SplashScreen.route) {
        composable(route = ScreenNavigator.SignInScreen.route) {
           LoginForm(navController = navController)

        }
        composable(route = ScreenNavigator.SignUpScreen.route) {
            SignUp(navController = navController)
        }
        composable(route = ScreenNavigator.MainScreen.route) {
            MainScreen(navController = navController)
        }
        composable(route = ScreenNavigator.SplashScreen.route) {
            AnimatedSplashScreen(navController = navController)
        }
        composable(route = ScreenNavigator.ForgotPasswordScreen.route) {
            EmailInput(navController = navController)
        }
        composable(route = ScreenNavigator.VerifyEmailScreen.route) {
            OtpInput(navController = navController)
        }
        composable(route = ScreenNavigator.ResetPasswordScreen.route) {
            PasswordReset(navController = navController)
        }
        // Main Screen Fragments Navigation
        composable(route = MainScreenNavigator.HomeScreen.route) {
            HomeScreen(navController = navController)
        }
        composable(route = MainScreenNavigator.RecipeScreen.route) {
            RecipeScreen(navController = navController)
        }
        composable(route = MainScreenNavigator.SettingsScreen.route) {
            SettingsScreen(navController = navController)
        }
        composable(route = MainScreenNavigator.NotificationScreen.route) {
            NotificationScreen(navController = navController)
        }
        composable(route = MainScreenNavigator.EventScreen.route) {
            EventScreen(navController = navController)
        }
    }
}

@Composable
fun MainScreenNavigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination =  "Home"){
        composable("Home") {
            HomeScreen(navController = navController)
        }
        composable("Recipes") {
            RecipeScreen(navController = navController)
        }
        composable("Settings") {
            SettingsScreen(navController = navController)
        }
        composable("Notifications") {
            NotificationScreen(navController = navController)
        }
        composable("Events") {
            EventScreen(navController = navController)
        }

    }
}



