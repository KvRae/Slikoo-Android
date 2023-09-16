package slikoo.kvrae.slikoo.ui.fragments.main_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import slikoo.kvrae.slikoo.R
import slikoo.kvrae.slikoo.ui.components.CustomAlertDialog
import slikoo.kvrae.slikoo.ui.components.SettingCard
import slikoo.kvrae.slikoo.ui.theme.LightSecondary
import slikoo.kvrae.slikoo.utils.AppScreenNavigator

@Composable
fun SettingsScreen(navController: NavController) {
    var dialogState by remember {
        mutableStateOf(false)
    }
    Box(
        modifier = Modifier.fillMaxSize().background(LightSecondary),
        contentAlignment = Alignment.Center
    ) {
        CustomAlertDialog(showDialog = dialogState,
            title = stringResource(R.string.disconnect),
            message = stringResource(R.string.logout_message),
            onDismiss = { dialogState = false },
            onConfirm = {
                dialogState = false
                navController.popBackStack()
                navController.navigate(AppScreenNavigator.SignInAppScreen.route)
            }
        )
        LazyColumn(
            modifier = Modifier
                .fillMaxSize(),
            content = {
                item {
                    SettingCard(title = "Karam Mannai",
                        subtitle = "Manage your account",
                        // modifier = Modifier.height(100.dp),
                        actionIcon = Icons.Filled.Person, icon = Icons.Filled.Person,
                        onClick = {
                            navController.navigate(AppScreenNavigator.AdvancedEditProfilesAppScreen.route)
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
                    SettingCard(title = stringResource(R.string.update_advance_profile),
                        actionIcon = Icons.Filled.KeyboardArrowRight,
                        icon = Icons.Filled.Info,
                        onClick = {
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
    }
}