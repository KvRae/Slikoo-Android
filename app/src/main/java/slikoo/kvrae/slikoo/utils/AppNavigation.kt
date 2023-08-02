package slikoo.kvrae.slikoo.utils


import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
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

sealed class AppScreenNavigator(val route: String){
    object SplashAppScreen: AppScreenNavigator("splash_screen")
    object SignInAppScreen: AppScreenNavigator("sign_in_screen")
    object SignUpAppScreen: AppScreenNavigator("sign_up_screen")
    object MainAppScreen: AppScreenNavigator("main_screen")
    object ForgotPasswordAppScreen: AppScreenNavigator("forgot_password_screen")
    object VerifyEmailAppScreen: AppScreenNavigator("verify_email_screen")
    object ResetPasswordAppScreen: AppScreenNavigator("reset_password_screen")
}

sealed class SignUpNavigator(val route: String){
    object SignUpFormFragment: SignUpNavigator("sign_up_form")
    object SignUpSecondFormFragment: SignUpNavigator("sign_up_sec_form")
    object SignUpIDCFragment: SignUpNavigator("sign_up_idc")
    object SignUpProfilePictureFragment: SignUpNavigator("sign_up_profile_picture")

}


sealed class MainScreenNavigator(val route: String){
    object HomeScreen: MainScreenNavigator("Home")
    object RecipeScreen: MainScreenNavigator("Repas")
    object SettingsScreen: MainScreenNavigator("Parametres")
    object NotificationScreen: MainScreenNavigator("Notifications")
    object EventScreen: MainScreenNavigator("Organiser")
}


@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        // Set start destination
        startDestination = AppScreenNavigator.SplashAppScreen.route) {

        composable(route = AppScreenNavigator.SignInAppScreen.route) {
           LoginForm(navController = navController)
        }
        composable(route = AppScreenNavigator.SignUpAppScreen.route) {
            SignUp(navController = navController)
        }
        composable(route = AppScreenNavigator.MainAppScreen.route) {
            MainScreen(navController = navController)
        }
        composable(route = AppScreenNavigator.SplashAppScreen.route) {
            AnimatedSplashScreen(navController = navController)
        }
        composable(route = AppScreenNavigator.ForgotPasswordAppScreen.route) {
            EmailInput(navController = navController)
        }
        composable(route = AppScreenNavigator.VerifyEmailAppScreen.route) {
            OtpInput(navController = navController)
        }
        composable(route = AppScreenNavigator.ResetPasswordAppScreen.route) {
            PasswordReset(navController = navController)
        }
    }
}





