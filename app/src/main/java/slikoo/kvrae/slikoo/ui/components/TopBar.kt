package slikoo.kvrae.slikoo.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Lock
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.PopupProperties
import slikoo.kvrae.slikoo.R
import slikoo.kvrae.slikoo.ui.theme.LightBackground
import slikoo.kvrae.slikoo.ui.theme.LightPrimary
import slikoo.kvrae.slikoo.ui.theme.LightSecondary
import slikoo.kvrae.slikoo.utils.AppScreenNavigator
import slikoo.kvrae.slikoo.utils.MainScreenNavigator



@Preview
@Composable
fun CustomMainMenuTopBar(title: String = stringResource(id = R.string.app_name)) {
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
                    isExpanded = !isExpanded
                }) {
                    Image(
                        painter = painterResource(id = R.drawable.avatar),
                        contentDescription = stringResource(R.string.avatar),
                        modifier = Modifier
                            .clip(CircleShape)                       // clip to the circle shape
                            .border(2.dp, LightPrimary, CircleShape)
                            .size(40.dp)
                    )
                }
            }
            DropdownMenu(
                expanded = isExpanded,
                offset = DpOffset(4.dp, 0.dp),
                properties = PopupProperties(focusable = true),
                onDismissRequest = { isExpanded = false },
                modifier = Modifier.background(LightSecondary)
            ) {
                DropdownMenuItem(onClick = { /*TODO*/ }) { DropDownItemContent(stringResource(id = R.string.see_profile), Icons.Rounded.Person) }
                DropdownMenuItem(onClick = { /*TODO*/ }) { DropDownItemContent(stringResource(id = R.string.settings), Icons.Rounded.Settings) }
                DropdownMenuItem(onClick = { /*TODO*/ }) { DropDownItemContent(stringResource(id =R.string.disconnect), Icons.Rounded.Lock) }
            }
        }
    )

}

@Composable
fun DropDownItemContent(label : String, icon : ImageVector) {
    Row {
        Icon(imageVector = icon, contentDescription = stringResource(R.string.menu_item_icon))
        Spacer(modifier = Modifier.size(4.dp))
        Text(label)
    }
}


