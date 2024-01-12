package slikoo.kvrae.slikoo.ui.fragments.main_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kotlinx.coroutines.launch
import slikoo.kvrae.slikoo.R
import slikoo.kvrae.slikoo.data.datasources.entities.User
import slikoo.kvrae.slikoo.ui.components.CustomAlertDialog
import slikoo.kvrae.slikoo.ui.components.SettingCard
import slikoo.kvrae.slikoo.ui.pages.TextWithButtonScreen
import slikoo.kvrae.slikoo.ui.theme.LightSecondary
import slikoo.kvrae.slikoo.utils.AppScreenNavigator
import slikoo.kvrae.slikoo.utils.SessionDataStore
import slikoo.kvrae.slikoo.utils.TempSession

@Composable
fun SettingsScreen(navController: NavController, user: User) {
    var dialogState by remember {
        mutableStateOf(false)
    }
    val coroutineScompe = rememberCoroutineScope()
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(LightSecondary),
        contentAlignment = Alignment.Center
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            content = {
                item {
                    SettingCard(title = user.nom + " " + user.prenom,
                        subtitle = if (user.verified) stringResource(R.string.verified)
                        else stringResource(R.string.not_verified),
                        actionIcon = Icons.Filled.Person,
                        avatar = if (user.avatar != "") user.avatarUrl + user.avatar else "",
                        icon = Icons.Filled.Person,
                        onClick = {
                            navController.navigate(AppScreenNavigator.ProfileAppScreen.route)
                        })
                }
                item {
                    SettingCard(title = stringResource(R.string.modifier_le_profil),
                        actionIcon = Icons.Filled.KeyboardArrowRight,
                        icon = Icons.Filled.Edit,
                        onClick = {
                            navController.navigate(AppScreenNavigator.EditProfileAppScreen.route)
                        })
                }
                item {
                    SettingCard(title = stringResource(R.string.update_password),
                        actionIcon = Icons.Filled.KeyboardArrowRight,
                        icon = Icons.Filled.Lock,
                        onClick = {
                            navController.navigate(AppScreenNavigator.UpdatePasswordAppScreen.route)
                        })
                }
                item {
                    SettingCard(title = stringResource(R.string.update_advance_profile),
                        actionIcon = Icons.Filled.KeyboardArrowRight,
                        icon = Icons.Filled.Info,
                        onClick = {
                            navController.navigate("advanced_edit_profile_screen/${user.id}")
                        })
                }
                item {
                    SettingCard(title = stringResource(R.string.disconnect),
                        actionIcon = Icons.Filled.KeyboardArrowRight,
                        icon = Icons.Filled.ExitToApp,
                        onClick = {
                            dialogState = true
                        }
                    )
                }

            }
        )


        if (user.nom.isEmpty())
            TextWithButtonScreen(
                text = stringResource(id = R.string.session_expired),
                buttonText = stringResource(id = R.string.reconnect),
                onClick = {
                    navController.popBackStack()
                    navController.navigate(AppScreenNavigator.SignInAppScreen.route)
                }
            )
    }
    CustomAlertDialog(showDialog = dialogState,
        title = stringResource(R.string.disconnect),
        message = stringResource(R.string.logout_message),
        dismissText = stringResource(id = R.string.no),
        onDismiss = { dialogState = false },
        onConfirm = {
            coroutineScompe.launch {
                SessionDataStore(navController.context).clearUser()
                TempSession.clear()
                dialogState = false
                navController.popBackStack()
                navController.navigate(AppScreenNavigator.SignInAppScreen.route)
            }
        }
    )
}