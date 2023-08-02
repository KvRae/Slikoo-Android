package slikoo.kvrae.slikoo.ui.pages

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
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
import slikoo.kvrae.slikoo.utils.MainScreenNavigator

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(navController: NavController, currentScreen : String = "Home") {

    val title = remember {
        mutableStateOf(currentScreen)
    }
    val bottomNavigationItems = listOf(
        BottomNavItem("Home", MainScreenNavigator.HomeScreen.route, Icons.Rounded.Home, 0),
        BottomNavItem("Repas",MainScreenNavigator.RecipeScreen.route , ImageVector.vectorResource(id = R.drawable.plat_icon), 0),
        BottomNavItem("Organiser", MainScreenNavigator.EventScreen.route, Icons.Rounded.Add, 0),
        BottomNavItem("Notifications", MainScreenNavigator.NotificationScreen.route, Icons.Rounded.Notifications, 5),
        BottomNavItem("Parametres", MainScreenNavigator.SettingsScreen.route, Icons.Rounded.Settings, 0),
    )
 Scaffold(
        topBar = {
            CustomTopBar(title = title.value)
        },
     bottomBar = {
         BottomNavigationBar(items = bottomNavigationItems ,
             route = title.value,
             onItemClick ={
                 title.value = it})
     },
     content = {
         Box(modifier = Modifier.padding(top = 70.dp, bottom = 50.dp)) {
             when(title.value)
                {
                    "Home" -> HomeScreen()

                    "Repas" -> RecipeScreen()

                    "Organiser" -> EventScreen()

                    "Notifications" -> NotificationScreen()

                    "Parametres" -> SettingsScreen(navController = navController)

                }
         }
     }
  )
}


