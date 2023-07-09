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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import slikoo.kvrae.slikoo.R
import slikoo.kvrae.slikoo.ui.components.BottomNavItem
import slikoo.kvrae.slikoo.ui.components.BottomNavigationBar
import slikoo.kvrae.slikoo.ui.components.CustomTopBar
import slikoo.kvrae.slikoo.utils.MainScreenNavigation

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(navController: NavController) {
    val menuNavController = rememberNavController()
    val title = remember {
        mutableStateOf("Home")
    }
    val bottomNavigationItems = listOf(
        BottomNavItem("Home", "Home", Icons.Rounded.Home, 0),
        BottomNavItem("Recipe", "Recipes", ImageVector.vectorResource(id = R.drawable.plat_icon), 0),
        BottomNavItem("Event", "Events", Icons.Rounded.Add, 0),
        BottomNavItem("Notification", "Notifications", Icons.Rounded.Notifications, 5),
        BottomNavItem("Settings", "Settings", Icons.Rounded.Settings, 0),
    )
 Scaffold(
        topBar = {
            CustomTopBar(title = title.value)
        },
     bottomBar = {
         BottomNavigationBar(items = bottomNavigationItems ,
             navController= menuNavController,
             onItemClick ={menuNavController.navigate(it)
                 title.value = it})
     },
     content = {
         Box(modifier = Modifier.padding(top = 70.dp, bottom = 50.dp)) {
             MainScreenNavigation(navController = menuNavController)
         }
     }
  )
}

@Preview
@Composable
fun MainScreenPreview() {
    val context = androidx.compose.ui.platform.LocalContext.current
 MainScreen(navController = NavController(context))
}

