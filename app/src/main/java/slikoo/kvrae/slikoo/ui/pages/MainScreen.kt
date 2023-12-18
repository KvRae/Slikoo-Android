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
import androidx.compose.material.Snackbar
import androidx.compose.material.SnackbarDuration
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import kotlinx.coroutines.launch
import slikoo.kvrae.slikoo.R
import slikoo.kvrae.slikoo.ui.components.BottomNavItem
import slikoo.kvrae.slikoo.ui.components.BottomNavigationBar
import slikoo.kvrae.slikoo.ui.components.CustomMainMenuTopBar
import slikoo.kvrae.slikoo.ui.fragments.main_screen.HomeScreen
import slikoo.kvrae.slikoo.ui.fragments.main_screen.NotificationScreen
import slikoo.kvrae.slikoo.ui.fragments.main_screen.RecipeScreen
import slikoo.kvrae.slikoo.ui.fragments.main_screen.SettingsScreen
import slikoo.kvrae.slikoo.ui.theme.LightBackground
import slikoo.kvrae.slikoo.ui.theme.LightError
import slikoo.kvrae.slikoo.ui.theme.LightPrimary
import slikoo.kvrae.slikoo.ui.theme.LightSecondary
import slikoo.kvrae.slikoo.utils.AppScreenNavigator
import slikoo.kvrae.slikoo.utils.MainScreenNavigator
import slikoo.kvrae.slikoo.utils.SessionDataStore
import slikoo.kvrae.slikoo.utils.TempSession
import slikoo.kvrae.slikoo.viewmodels.MainScreenViewModel




@SuppressLint("SuspiciousIndentation")
@Composable
fun MainScreen(navController: NavController,
               currentScreen: String = "Home",
) {

    var title by rememberSaveable { mutableStateOf(currentScreen) }
    val viewModel: MainScreenViewModel = viewModel()
    val coroutineScope = rememberCoroutineScope()

    DisposableEffect(Unit) {
        viewModel.getUser()
        onDispose {
            if (TempSession.token != "" && TempSession.email != "")
            coroutineScope.launch {
                SessionDataStore(navController.context).setUserLoggedIn(
                    token = TempSession.token,
                    email = TempSession.email,
                    isLogged = true
                )
            }
        }
    }


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
        BottomNavItem("Organiser",
            title,
            Icons.Rounded.Add, 0),
        BottomNavItem(
            "Notifications",
            MainScreenNavigator.NotificationScreen.route,
            ImageVector.vectorResource(id = R.drawable.notification),
            0
        ),
        BottomNavItem(
            "Parametres",
            MainScreenNavigator.SettingsScreen.route,
            ImageVector.vectorResource(id = R.drawable.settings_icon),
            0
        ),
    )

    val scaffoldState = rememberScaffoldState()
    val couroutineScope = rememberCoroutineScope()

    if (viewModel.user.nom.isNotBlank())
    Scaffold(
        scaffoldState = scaffoldState,
        modifier = Modifier
            .navigationBarsPadding()
            .statusBarsPadding(),
        topBar = {
                CustomMainMenuTopBar(
                    title = title,
                    onTitleChange = { title = it },
                    user = viewModel.user
                )

        },
        snackbarHost = {
            scaffoldState.snackbarHostState.currentSnackbarData?.let { data ->
                Snackbar(
                    snackbarData = data,
                    modifier = Modifier.padding(8.dp),
                    contentColor = LightBackground,
                    actionColor = LightPrimary,
                    backgroundColor = LightSecondary,

                )
            }
        },
        bottomBar = {
                BottomNavigationBar(items = bottomNavigationItems,
                    route = title,
                    navController = navController,
                    onItemClick = { title = it },
                    hasDetails = viewModel.user.Hasdetails
                )
        },
        floatingActionButton = {
            if (title == MainScreenNavigator.RecipeScreen.route && viewModel.user.verified && viewModel.user.Hasdetails)
                FloatingActionButton(
                    onClick = { navController.navigate("Organiser" ) },
                    backgroundColor = LightPrimary,
                    contentColor = LightError
                ) {
                    Icon(
                        imageVector = Icons.Rounded.Add,
                        contentDescription = "",
                        tint = LightError
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
                when (title) {
                    "Home" -> HomeScreen(navController = navController)
                    "Repas" -> RecipeScreen(navController = navController)
                    "Notifications" -> NotificationScreen(navController = navController)
                    "Parametres" -> SettingsScreen(navController = navController, user = viewModel.user)
                }


                if (!viewModel.user.verified) {
                    val msg = stringResource(id = R.string.complete_profile)
                    val msgAction = stringResource(id = R.string.ok)
                    DisposableEffect(scaffoldState.snackbarHostState) {
                        couroutineScope.launch {
                            scaffoldState.snackbarHostState.showSnackbar(
                                message = msg,
                                actionLabel = msgAction,
                                duration = SnackbarDuration.Indefinite,
                            )

                        }
                        onDispose {
                        }
                    }
                }
            }
        }
    )
    if(viewModel.isLoading) LoadingScreen()
    if (viewModel.isError)
        TextWithButtonScreen(text = stringResource(id = R.string.session_expired),
            buttonText = stringResource(id = R.string.reconnect),
            onClick = {
                navController.navigate(AppScreenNavigator.SignInAppScreen.route)
            }
        )
//    if (!isInternetAvailable)
//        TextWithButtonScreen(text = stringResource(id = R.string.no_internet),
//            buttonText = stringResource(id = R.string.reconnect),
//            onClick = {
//                navController.navigate(AppScreenNavigator.SignInAppScreen.route)
//            }
//        )
}

