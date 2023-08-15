package slikoo.kvrae.slikoo.ui.fragments.main_screen

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import slikoo.kvrae.slikoo.R
import slikoo.kvrae.slikoo.ui.components.CustomAlertDialog
import slikoo.kvrae.slikoo.ui.components.SettingCard
import slikoo.kvrae.slikoo.utils.AppScreenNavigator

@Composable
fun SettingsScreen(navController: NavController) {
    val dialogState = remember {
        mutableStateOf(false)
    }
    Box(modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        CustomAlertDialog(state = dialogState.value)
        LazyColumn(
            modifier = Modifier
                .fillMaxSize(),
            content = {
                item {
                    SettingCard(title = "Karam Mannai",
                        subtitle = "Manage your account",
                        modifier = Modifier.height(100.dp),
                        actionIcon = Icons.Filled.Person, icon = Icons.Filled.Person,
                        onClick = {
                        //navController.navigate("profile")
                    })
                }
                item {
                    SettingCard(title = stringResource(R.string.modifier_le_profil),
                        actionIcon = Icons.Filled.KeyboardArrowRight, icon = Icons.Filled.Edit, onClick = {
                            navController.navigate(AppScreenNavigator.EditProfileAppScreen.route)
                        })
                }
                item {
                    SettingCard(title = stringResource(R.string.mettre_a_jour_le_profile_avanc),
                        actionIcon = Icons.Filled.KeyboardArrowRight, icon = Icons.Filled.Info, onClick = {
                            navController.navigate(AppScreenNavigator.AdvancedEditProfilesAppScreen.route)
                        })
                }
                item {
                    SettingCard(title = stringResource(R.string.se_deconnecter),
                        actionIcon = Icons.Filled.KeyboardArrowRight,
                        icon = Icons.Filled.ExitToApp,
                        onClick = {
                            dialogState.value = true
                            Log.d("clcik", dialogState.value.toString())
                            navController.popBackStack()
                            navController.navigate(AppScreenNavigator.SignInAppScreen.route)

                        }
                    )
                }

            }
        )
    }
}