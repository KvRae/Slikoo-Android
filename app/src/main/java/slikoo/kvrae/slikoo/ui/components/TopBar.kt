package slikoo.kvrae.slikoo.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import slikoo.kvrae.slikoo.R
import slikoo.kvrae.slikoo.data.datasources.entities.User
import slikoo.kvrae.slikoo.ui.theme.LightBackground
import slikoo.kvrae.slikoo.ui.theme.LightPrimary
import slikoo.kvrae.slikoo.ui.theme.LightSecondary
import slikoo.kvrae.slikoo.utils.AppScreenNavigator
import slikoo.kvrae.slikoo.utils.MainScreenNavigator




@Composable
fun CustomMainMenuTopBar(title: String = stringResource(id = R.string.app_name),
                         user: User = User(),
                         onTitleChange: (String) -> Unit = {}) {

    val avatar = if (user.avatar.isNotEmpty()) user.avatarUrl + user.avatar  else stringResource(id = R.string.avatar)

    var isExpanded by remember {
        mutableStateOf(false)
    }

    val appBarTitle = when (title) {
        MainScreenNavigator.RecipeScreen.route -> stringResource(id = R.string.meal)
        MainScreenNavigator.SettingsScreen.route -> stringResource(id = R.string.settings)
        MainScreenNavigator.NotificationScreen.route -> stringResource(id = R.string.notifications)
        AppScreenNavigator.EventScreen.route -> stringResource(id = R.string.organize)
        else -> stringResource(id = R.string.app_name)
    }
    TopAppBar(
        modifier = Modifier
            .fillMaxWidth()
            .background(LightSecondary),
        backgroundColor = LightSecondary,
        elevation = 0.dp,
        title = {
                if (title == MainScreenNavigator.HomeScreen.route) {
                    Image(
                        painter = painterResource(id = R.drawable.logo2),
                        contentDescription = "logo",
                        modifier = Modifier.size(100.dp)
                    )
                }
                else {
                    Text(appBarTitle, style = TextStyle(fontSize = 24.sp,
                        color = LightBackground,
                        fontWeight = FontWeight.Bold))
                    }
                },
        actions = {
            if (title != MainScreenNavigator.SettingsScreen.route) {
                IconButton(onClick = {
                        onTitleChange("Parametres")
                }) {
                    AsyncImage(
                        model = avatar,
                        contentDescription = stringResource(R.string.avatar),
                        modifier = Modifier
                            .clip(CircleShape)
                            .border(2.dp, LightPrimary, CircleShape)
                            .size(40.dp),
                        contentScale = ContentScale.Crop
                    )
                }
            }
        }
    )

}


