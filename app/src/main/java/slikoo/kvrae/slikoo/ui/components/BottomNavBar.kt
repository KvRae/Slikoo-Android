package slikoo.kvrae.slikoo.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BadgedBox
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import slikoo.kvrae.slikoo.ui.theme.ButtonsAndIcons
import slikoo.kvrae.slikoo.ui.theme.InactiveIcons


//The class that will be used to create the bottom navigation bar
data class BottomNavItem(
    val name :String,
    val route :String,
    val icon :ImageVector,
    var badgeCount :Int = 0
)


@Composable
fun BottomNavigationBar(items : List<BottomNavItem>,
                        navController: NavController,
                        modifier : Modifier = Modifier,
                        onItemClick : (route :String) -> Unit) {
        val backStackEntry = navController.currentBackStackEntryAsState()
        val bottomNavBarColor = remember { mutableStateOf(Color.White) }
        BottomNavigation(modifier = modifier,
        backgroundColor = bottomNavBarColor.value, elevation = 10.dp) {
        items.forEach { item ->
            val selected = item.route == backStackEntry.value?.destination?.route
            BottomNavigationItem(
                icon = {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        if (item.badgeCount > 0 && !selected){
                            BadgedBox(
                                badge = {
                                    Box(
                                        contentAlignment = Alignment.Center,
                                        modifier = Modifier
                                            .background(ButtonsAndIcons,
                                                shape = RoundedCornerShape(12.dp)
                                            )
                                            .padding(4.dp)
                                    ) {
                                }
                            }) {    
                                    Icon(imageVector = item.icon, contentDescription = item.name)
                            }
                        }
                        else{
                            item.badgeCount = 0
                            if (item.name == "Event"){
                                Box(
                                    contentAlignment = Alignment.Center,
                                    modifier = Modifier
                                        .background(if (selected) Color.White else ButtonsAndIcons,
                                            shape = RoundedCornerShape(12.dp))
                                        .padding(8.dp)

                                ) {
                                    Icon(imageVector = item.icon, contentDescription = item.name)
                                    bottomNavBarColor.value = if (!selected) Color.White else ButtonsAndIcons
                                }

                            }
                            else Icon(imageVector = item.icon, contentDescription = item.name)


                        }
                    }
                },
                selected = selected,
                selectedContentColor = ButtonsAndIcons,
                unselectedContentColor = InactiveIcons,
                onClick = {
                    onItemClick(item.route)
                }
         )
     }

 }
}