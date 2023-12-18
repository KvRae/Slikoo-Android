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
import slikoo.kvrae.slikoo.ui.pages.AdvancedProfileScreen
import slikoo.kvrae.slikoo.ui.pages.AnimatedSplashScreen
import slikoo.kvrae.slikoo.ui.pages.EditMealScreen
import slikoo.kvrae.slikoo.ui.pages.EditProfileScreen
import slikoo.kvrae.slikoo.ui.pages.FeedbackForm
import slikoo.kvrae.slikoo.ui.pages.ForgetPasswordScreen
import slikoo.kvrae.slikoo.ui.pages.LoginForm
import slikoo.kvrae.slikoo.ui.pages.MainScreen
import slikoo.kvrae.slikoo.ui.pages.MealOrganizeScreen
import slikoo.kvrae.slikoo.ui.pages.MealsByCategory
import slikoo.kvrae.slikoo.ui.pages.MealsDetailScreen
import slikoo.kvrae.slikoo.ui.pages.ProfileScreen
import slikoo.kvrae.slikoo.ui.pages.SignUp
import slikoo.kvrae.slikoo.ui.pages.UpdatePasswordScreen
import slikoo.kvrae.slikoo.ui.pages.UserProfileScreen


@Composable
fun App() {
    Navigation()
}

sealed class AppScreenNavigator(val route: String) {
    object EventScreen : MainScreenNavigator("organiser")
    object SplashAppScreen : AppScreenNavigator("splash_screen")
    object SignInAppScreen : AppScreenNavigator("sign_in_screen")
    object SignUpAppScreen : AppScreenNavigator("sign_up_screen")
    object MainAppScreen : AppScreenNavigator("main_screen")
    object ForgotPasswordAppScreen : AppScreenNavigator("forgot_password_screen")
    object EditProfileAppScreen : AppScreenNavigator("edit_profile_screen")
    object ProfileAppScreen : AppScreenNavigator("profile_screen")
    object AdvancedEditProfilesAppScreen : AppScreenNavigator("advanced_edit_profile_screen/{id}")
    object EventDetailsAppScreen : AppScreenNavigator("event_details_screen/{id}")
    object CategoryAppScreen : AppScreenNavigator("category_screen/{filter}")
    object UpdatePasswordAppScreen : AppScreenNavigator("update_password_screen")
    object UserProfileAppScreen : AppScreenNavigator("user_profile_screen/{id}")
    object FeedbackAppScreen : AppScreenNavigator("feedback_screen/{idMeal}/{idUser}")
    object EditMealAppScreen : AppScreenNavigator("edit_meal_screen/{idMeal}")

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
        }
        composable(route = AppScreenNavigator.ProfileAppScreen.route) {
            ProfileScreen(navController = navController)
        }
        composable(route = AppScreenNavigator.EventScreen.route) {
            MealOrganizeScreen(navController = navController,)
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
        }

        composable(
            route = AppScreenNavigator.AdvancedEditProfilesAppScreen.route,
            arguments = listOf(navArgument("id") {
                type = NavType.IntType
            })
        )
        {
            AdvancedProfileScreen(
                navController = navController,
                id = it.arguments?.getInt("id") ?: 0
            )
        }
        composable(
            route = AppScreenNavigator.CategoryAppScreen.route,
            arguments = listOf(navArgument("filter") {
                type = NavType.StringType
            })
        )
        {
            MealsByCategory(
                navController = navController,
                filter = it.arguments?.getString("filter") ?: ""
            )
        }
        composable(route = AppScreenNavigator.UpdatePasswordAppScreen.route) {
           UpdatePasswordScreen(navController = navController)
        }

        composable(
            route = AppScreenNavigator.UserProfileAppScreen.route,
            arguments = listOf(navArgument("id") {
                type = NavType.IntType
            })
        )
        {
            UserProfileScreen(
                navController = navController,
                id = it.arguments?.getInt("id") ?: 0
            )
        }
        composable(
            route = AppScreenNavigator.FeedbackAppScreen.route,
            arguments = listOf(
                navArgument("idMeal") {
                    type = NavType.IntType
                },
                navArgument("idUser") {
                    type = NavType.IntType
                }
            )
        )
        {
            FeedbackForm(
                navController = navController,
                idMeal = it.arguments?.getInt("idMeal") ?: 0,
                idUser = it.arguments?.getInt("idUser") ?: 0
            )
        }
        composable(route = AppScreenNavigator.EditMealAppScreen.route,
            arguments = listOf(navArgument("idMeal") {
                type = NavType.IntType
            })
        )
        {
            EditMealScreen(
                navController = navController,
                idMeal = it.arguments?.getInt("idMeal") ?: 0
            )
        }
    }
}





