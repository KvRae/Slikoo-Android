package slikoo.kvrae.slikoo.utils


import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import slikoo.kvrae.slikoo.ui.pages.EventScreen
import slikoo.kvrae.slikoo.ui.pages.AdvancedEditProfileScreen
import slikoo.kvrae.slikoo.ui.pages.AnimatedSplashScreen
import slikoo.kvrae.slikoo.ui.pages.EditProfileScreen
import slikoo.kvrae.slikoo.ui.pages.EmailInput
import slikoo.kvrae.slikoo.ui.pages.EventDetailScreen
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
    object EventScreen: MainScreenNavigator("Organiser")
    object SplashAppScreen: AppScreenNavigator("splash_screen")
    object SignInAppScreen: AppScreenNavigator("sign_in_screen")
    object SignUpAppScreen: AppScreenNavigator("sign_up_screen")
    object MainAppScreen: AppScreenNavigator("main_screen")
    object ForgotPasswordAppScreen: AppScreenNavigator("forgot_password_screen")
    object VerifyEmailAppScreen: AppScreenNavigator("verify_email_screen")
    object ResetPasswordAppScreen: AppScreenNavigator("reset_password_screen")
    object EditProfileAppScreen: AppScreenNavigator("edit_profile_screen")
    object AdvancedEditProfilesAppScreen: AppScreenNavigator("advanced_edit_profile_screen")
    object EventDetailsAppScreen: AppScreenNavigator("event_details_screen")
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

}

@Composable
fun Navigation() {
    val navController = rememberNavController()
    val mainScreenIndex = remember {
        mutableStateOf("Home")
    }
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
            MainScreen(navController = navController, currentScreen = mainScreenIndex.value)
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
        composable(route = AppScreenNavigator.EditProfileAppScreen.route) {
            EditProfileScreen(navController = navController)
            mainScreenIndex.value = MainScreenNavigator.SettingsScreen.route
        }
        composable(route = AppScreenNavigator.AdvancedEditProfilesAppScreen.route) {
            AdvancedEditProfileScreen(navController = navController)
            mainScreenIndex.value = MainScreenNavigator.SettingsScreen.route
        }
        composable(route = AppScreenNavigator.EventScreen.route){
            EventScreen(onBackPress = {mainScreenIndex.value = it}, navController = navController )
            mainScreenIndex.value = MainScreenNavigator.HomeScreen.route
        }
        composable(route = AppScreenNavigator.EventDetailsAppScreen.route){
            EventDetailScreen(navController = navController)
        }
    }
}





