package slikoo.kvrae.slikoo.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BadgedBox
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import slikoo.kvrae.slikoo.R
import slikoo.kvrae.slikoo.ui.pages.onMakeToast
import slikoo.kvrae.slikoo.ui.theme.LightError
import slikoo.kvrae.slikoo.ui.theme.LightPrimary
import slikoo.kvrae.slikoo.ui.theme.LightSecondary
import slikoo.kvrae.slikoo.ui.theme.LightSecondaryVariant
import slikoo.kvrae.slikoo.utils.MainScreenNavigator


// The class that will be used
// to create the bottom navigation bar
data class BottomNavItem(
    val name :String,
    val route :String,
    val icon :ImageVector,
    var badgeCount :Int = 0,
)


@Composable
fun BottomNavigationBar(items : List<BottomNavItem>,
                        modifier : Modifier = Modifier,
                        navController: NavController,
                        route: String = MainScreenNavigator.HomeScreen.route,
                        onItemClick : (route :String) -> Unit,
                        hasDetails :Boolean = false,
) {
    val msg = stringResource(R.string.advanced_prof_msg)
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp)
            .background(LightSecondary),

    ) {
        BottomNavigation(
            elevation = 16.dp,
            backgroundColor = LightError,

            modifier = modifier
                .clip(
                    shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
                )
                .fillMaxSize(),


        ) {
            items.forEach { item ->
                val selected = item.route == route
                BottomNavigationItem(
                    icon = {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            if (item.badgeCount > 0 && !selected) {
                                BadgedBox(
                                    badge = {
                                        Box(
                                            contentAlignment = Alignment.Center,
                                            modifier = Modifier
                                                .background(
                                                    LightPrimary,
                                                    shape = RoundedCornerShape(12.dp)
                                                )
                                                .padding(5.dp)
                                        ) {
                                        }
                                    }) {
                                    Icon(
                                        imageVector = item.icon,
                                        contentDescription = item.name,
                                        modifier = Modifier.size(24.dp)

                                    )
                                }
                            } else {
                                item.badgeCount = 0
                                if (item.name == "Organiser") {
                                    Box(
                                        contentAlignment = Alignment.Center,
                                        modifier = Modifier
                                            .background(
                                                LightPrimary,
                                                shape = RoundedCornerShape(12.dp)
                                            )
                                            .padding(8.dp)

                                    ) {
                                        Icon(
                                            imageVector = item.icon,
                                            tint = LightError,
                                            contentDescription = item.name,
                                            modifier = Modifier.size(26.dp)
                                        ) // Event Icon
                                    }

                                } else Icon(
                                    imageVector = item.icon,
                                    contentDescription = item.name,
                                    modifier = Modifier.size(26.dp)
                                )
                            }
                        }
                    },
                    selected = selected,
                    selectedContentColor = LightPrimary,
                    unselectedContentColor = LightSecondaryVariant,
                    onClick = {
                        if (item.name == "Organiser" && hasDetails){
                            navController.navigate("organiser")
                        }
                        if (!hasDetails && item.name == "Organiser"){
                            onMakeToast(navController.context, msg)
                        }
                        onItemClick(item.route)
                    }
                )
            }

        }
    }
}
