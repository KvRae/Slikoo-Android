package slikoo.kvrae.slikoo.ui.pages


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.navigation.NavController
import slikoo.kvrae.slikoo.R
import slikoo.kvrae.slikoo.ui.components.BottomNavItem
import slikoo.kvrae.slikoo.ui.components.BottomNavigationBar
import slikoo.kvrae.slikoo.ui.components.CustomTopBar
import slikoo.kvrae.slikoo.ui.fragments.main_screen.EventScreen
import slikoo.kvrae.slikoo.ui.fragments.main_screen.HomeScreen
import slikoo.kvrae.slikoo.ui.fragments.main_screen.NotificationScreen
import slikoo.kvrae.slikoo.ui.fragments.main_screen.RecipeScreen
import slikoo.kvrae.slikoo.ui.fragments.main_screen.SettingsScreen
import slikoo.kvrae.slikoo.ui.theme.ScreenBackground
import slikoo.kvrae.slikoo.utils.MainScreenNavigator



@Composable
fun MainScreen(navController: NavController, currentScreen : String = "Home") {
    val title = remember {
        mutableStateOf(currentScreen)
    }

    
    val bottomNavigationItems = listOf(
        BottomNavItem("Home", MainScreenNavigator.HomeScreen.route, ImageVector.vectorResource(id = R.drawable.home_icon), 0),
        BottomNavItem("Repas",MainScreenNavigator.RecipeScreen.route , ImageVector.vectorResource(id = R.drawable.plat_icon), 0),
        BottomNavItem("Organiser", MainScreenNavigator.EventScreen.route, Icons.Rounded.Add, 0),
        BottomNavItem("Notifications", MainScreenNavigator.NotificationScreen.route, ImageVector.vectorResource(id = R.drawable.notification),5),
        BottomNavItem("Parametres", MainScreenNavigator.SettingsScreen.route, ImageVector.vectorResource(id = R.drawable.settings_icon), 0),
    )
 Scaffold(
        topBar = {
            CustomTopBar(title = title.value)
        },
     bottomBar = {

         BottomNavigationBar(items = bottomNavigationItems ,
             route = title.value,
             onItemClick ={ title.value = it})
     },
     content = {padding ->
         Box(modifier = Modifier
             .padding(padding)
             .background(ScreenBackground)) {
             when(title.value)
             {
                 "Home" -> HomeScreen()

                 "Repas" -> RecipeScreen ()

                 "Organiser" -> EventScreen()

                 "Notifications" -> NotificationScreen()

                 "Parametres" -> SettingsScreen(navController = navController)
             }
         }
     }
 )

}


//@Preview(showBackground = true)
//@Composable
//fun PreviewMAIN() {
//    val context = androidx.compose.ui.platform.LocalContext.current
//    MainScreen(navController = NavController(context))
//}
//

