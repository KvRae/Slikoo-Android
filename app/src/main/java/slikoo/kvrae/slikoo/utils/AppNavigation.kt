package slikoo.kvrae.slikoo.utils


//import slikoo.kvrae.slikoo.ui.pages.OtpInput
//import slikoo.kvrae.slikoo.ui.pages.PasswordReset
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import slikoo.kvrae.slikoo.ui.pages.AnimatedSplashScreen
import slikoo.kvrae.slikoo.ui.pages.EditProfileScreen
import slikoo.kvrae.slikoo.ui.pages.ForgetPasswordScreen
import slikoo.kvrae.slikoo.ui.pages.LoginForm
import slikoo.kvrae.slikoo.ui.pages.MainScreen
import slikoo.kvrae.slikoo.ui.pages.MealOrganizeScreen
import slikoo.kvrae.slikoo.ui.pages.MealsDetailScreen
import slikoo.kvrae.slikoo.ui.pages.ProfileScreen
import slikoo.kvrae.slikoo.ui.pages.SignUp


@Composable
fun App() {
    Navigation()
}

sealed class AppScreenNavigator(val route: String) {
    object EventScreen : MainScreenNavigator("Organiser/{id}")
    object SplashAppScreen : AppScreenNavigator("splash_screen")
    object SignInAppScreen : AppScreenNavigator("sign_in_screen")
    object SignUpAppScreen : AppScreenNavigator("sign_up_screen")
    object MainAppScreen : AppScreenNavigator("main_screen")
    object ForgotPasswordAppScreen : AppScreenNavigator("forgot_password_screen")
    object EditProfileAppScreen : AppScreenNavigator("edit_profile_screen")
    object AdvancedEditProfilesAppScreen : AppScreenNavigator("advanced_edit_profile_screen")
    object EventDetailsAppScreen : AppScreenNavigator("event_details_screen/{id}")
}

sealed class SignUpNavigator(val route: String) {
    object SignUpFormFragment : SignUpNavigator("sign_up_form")
    object SignUpSecondFormFragment : SignUpNavigator("sign_up_sec_form")
    object SignUpIDCFragment : SignUpNavigator("sign_up_idc")
    object SignUpProfilePictureFragment : SignUpNavigator("sign_up_profile_picture")

}

sealed class MainScreenNavigator(val route: String) {
    object HomeScreen : MainScreenNavigator("Home")
    object RecipeScreen : MainScreenNavigator("Repas")
    object SettingsScreen : MainScreenNavigator("Parametres")
    object NotificationScreen : MainScreenNavigator("Notifications")

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
        startDestination = AppScreenNavigator.SplashAppScreen.route
    ) {

        composable(route = AppScreenNavigator.SignInAppScreen.route) {
            LoginForm(navController = navController)
            mainScreenIndex.value = MainScreenNavigator.HomeScreen.route

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
            ForgetPasswordScreen(navController = navController)
        }

        composable(route = AppScreenNavigator.EditProfileAppScreen.route) {
            EditProfileScreen(navController = navController)
            mainScreenIndex.value = MainScreenNavigator.SettingsScreen.route
        }
        composable(route = AppScreenNavigator.AdvancedEditProfilesAppScreen.route) {
            ProfileScreen(navController = navController)
            mainScreenIndex.value = MainScreenNavigator.SettingsScreen.route
        }
        composable(
            route = AppScreenNavigator.EventScreen.route,
            arguments = listOf(navArgument("id") {
                type = NavType.IntType
            })
        ) {
            MealOrganizeScreen(
                onBackPress = { mainScreenIndex.value = it },
                navController = navController,
                idMeal = it.arguments?.getInt("id") ?: 0
            )

            mainScreenIndex.value = MainScreenNavigator.RecipeScreen.route
        }
        composable(
            route = AppScreenNavigator.EventDetailsAppScreen.route,
            arguments = listOf(navArgument("id") {
            type = NavType.IntType
        })) {
            MealsDetailScreen(
                navController = navController,
                id = it.arguments?.getInt("id") ?: 0
            )
            mainScreenIndex.value = MainScreenNavigator.RecipeScreen.route
        }
    }
}





