package slikoo.kvrae.slikoo.utils


import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import slikoo.kvrae.slikoo.ui.fragments.main_screen.EventScreen
import slikoo.kvrae.slikoo.ui.fragments.main_screen.HomeScreen
import slikoo.kvrae.slikoo.ui.fragments.main_screen.NotificationScreen
import slikoo.kvrae.slikoo.ui.fragments.main_screen.RecipeScreen
import slikoo.kvrae.slikoo.ui.fragments.main_screen.SettingsScreen
import slikoo.kvrae.slikoo.ui.fragments.signup.ProfilePictureSection
import slikoo.kvrae.slikoo.ui.fragments.signup.SignUpCidForm
import slikoo.kvrae.slikoo.ui.fragments.signup.SignUpForm
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
       /* navigation(
            route = MainScreenNavigator.HomeScreen.route,
            startDestination = MainScreenNavigator.HomeScreen.route
        ) {
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

        // Sign Up Fragments Navigation
        navigation(
            route = SignUpNavigator.SignUpFormFragment.route,
            startDestination = SignUpNavigator.SignUpFormFragment.route
        ) {
            composable(route = SignUpNavigator.SignUpFormFragment.route) {
                SignUpForm(navController = navController)
            }
            composable(route = SignUpNavigator.SignUpIDCFragment.route) {
                SignUpCidForm(navController = navController)
            }
            composable(route = SignUpNavigator.SignUpProfilePictureFragment.route) {
                ProfilePictureSection(navController = navController)
            }
        }*/

    }
}


sealed class MainScreenNavigator(val route: String){
    object HomeScreen: MainScreenNavigator("home_screen")
    object RecipeScreen: MainScreenNavigator("recipe_screen")
    object SettingsScreen: MainScreenNavigator("settings_screen")
    object NotificationScreen: MainScreenNavigator("notification_screen")
    object EventScreen: MainScreenNavigator("event_screen")
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




sealed class SignUpNavigator(val route: String){
    object SignUpFormFragment: SignUpNavigator("sign_up_form")
    object SignUpIDCFragment: SignUpNavigator("sign_up_idc")
    object SignUpProfilePictureFragment: SignUpNavigator("sign_up_profile_picture")

}

@Composable
fun SignUpNavigation(navController: NavHostController) {
    NavHost(navController = navController,
        startDestination =  SignUpNavigator.SignUpFormFragment.route
        ){
        composable(SignUpNavigator.SignUpFormFragment.route) {
            SignUpForm(navController = navController)
        }
        composable(SignUpNavigator.SignUpIDCFragment.route) {
            SignUpCidForm(navController = navController)
        }
        composable(SignUpNavigator.SignUpProfilePictureFragment.route) {
            ProfilePictureSection(navController = navController)
        }

    }

}



