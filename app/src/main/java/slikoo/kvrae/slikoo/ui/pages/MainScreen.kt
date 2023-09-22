package slikoo.kvrae.slikoo.ui.pages


import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import slikoo.kvrae.slikoo.R
import slikoo.kvrae.slikoo.ui.components.BottomNavItem
import slikoo.kvrae.slikoo.ui.components.BottomNavigationBar
import slikoo.kvrae.slikoo.ui.components.CustomMainMenuTopBar
import slikoo.kvrae.slikoo.ui.fragments.main_screen.HomeScreen
import slikoo.kvrae.slikoo.ui.fragments.main_screen.NotificationScreen
import slikoo.kvrae.slikoo.ui.fragments.main_screen.RecipeScreen
import slikoo.kvrae.slikoo.ui.fragments.main_screen.SettingsScreen
import slikoo.kvrae.slikoo.ui.theme.LightPrimary
import slikoo.kvrae.slikoo.ui.theme.LightSecondary
import slikoo.kvrae.slikoo.utils.AppScreenNavigator
import slikoo.kvrae.slikoo.utils.MainScreenNavigator
import slikoo.kvrae.slikoo.viewmodels.MainScreenViewModel


@SuppressLint("SuspiciousIndentation")
@Composable
fun MainScreen(navController: NavController, currentScreen: String = "Home") {
    val title = remember {
        mutableStateOf(currentScreen)
    }

    val viewModel: MainScreenViewModel = viewModel()


    val bottomNavigationItems = listOf(
        BottomNavItem(
            "Home",
            MainScreenNavigator.HomeScreen.route,
            ImageVector.vectorResource(id = R.drawable.home_icon),
            0
        ),
        BottomNavItem(
            "Repas",
            MainScreenNavigator.RecipeScreen.route,
            ImageVector.vectorResource(id = R.drawable.plat_icon),
            0
        ),
        BottomNavItem("Organiser", AppScreenNavigator.EventScreen.route, Icons.Rounded.Add, 0),
        BottomNavItem(
            "Notifications",
            MainScreenNavigator.NotificationScreen.route,
            ImageVector.vectorResource(id = R.drawable.notification),
            4
        ),
        BottomNavItem(
            "Parametres",
            MainScreenNavigator.SettingsScreen.route,
            ImageVector.vectorResource(id = R.drawable.settings_icon),
            0
        ),
    )
    if (viewModel.user.value.nom.isNotEmpty())
    Scaffold(
        modifier = Modifier
            .navigationBarsPadding()
            .statusBarsPadding(),
        topBar = {
            if (title.value != AppScreenNavigator.EventScreen.route)
                CustomMainMenuTopBar(
                    title = title.value,
                    onTitleChange = { title.value = it },
                    user = viewModel.user.value
                )
            else return@Scaffold
        },
        bottomBar = {
            if (title.value != AppScreenNavigator.EventScreen.route)
                BottomNavigationBar(items = bottomNavigationItems,
                    route = title.value,
                    onItemClick = { title.value = it }
                )
            else return@Scaffold
        },
        floatingActionButton = {
            if (title.value == MainScreenNavigator.RecipeScreen.route)
                FloatingActionButton(
                    onClick = { navController.navigate(AppScreenNavigator.EventScreen.route) },
                    backgroundColor = LightPrimary
                ) {
                    Icon(
                        imageVector = Icons.Rounded.Add,
                        contentDescription = "",
                        tint = LightSecondary
                    )
                }
            else return@Scaffold
        },
        content = { padding ->
            Box(
                modifier = Modifier
                    .padding(padding)
                    .background(LightSecondary)
            ) {
                when (title.value) {
                    "Home" -> HomeScreen(navController = navController)

                    "Repas" -> RecipeScreen(navController = navController)

                    "Organiser" -> EventScreen(
                        onBackPress = { title.value = "Repas" },
                        navController = navController
                    )

                    "Notifications" -> NotificationScreen(navController = navController)

                    "Parametres" -> SettingsScreen(navController = navController, user = viewModel.user.value)
                }
            }
        }
    )
    if(viewModel.isLoading && !viewModel.isError) LoadingScreen()
    if (viewModel.user.value.email.isEmpty() && !viewModel.isLoading)
        TextWithButtonScreen(text = stringResource(id = R.string.session_expired),
            buttonText = stringResource(id = R.string.reconnect),
            onClick = {
                navController.navigate(AppScreenNavigator.SignInAppScreen.route)
            }
        )

}
