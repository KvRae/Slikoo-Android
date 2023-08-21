package slikoo.kvrae.slikoo.ui.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import slikoo.kvrae.slikoo.R
import slikoo.kvrae.slikoo.ui.components.CustomButton
import slikoo.kvrae.slikoo.ui.components.CustomTextField
import slikoo.kvrae.slikoo.ui.components.ImagePickerField
import slikoo.kvrae.slikoo.ui.fragments.signup.ProfileImagePicker
import slikoo.kvrae.slikoo.ui.theme.LightSecondary
import slikoo.kvrae.slikoo.ui.theme.LightSurface
import slikoo.kvrae.slikoo.utils.AppScreenNavigator

@Composable
fun EditProfileScreen(navController : NavController) {
    Box(
        Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .navigationBarsPadding(),
    ) {
        Column(modifier = Modifier
            .fillMaxSize()
            .background(LightSecondary)
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            EditProfileTopBar(navController = navController)
            ProfileImagePicker( onImageSelected = {})
            CustomTextField(onChange = {}, value = "", label = stringResource(id = R.string.name), leadingIcon = Icons.Filled.Person )
            CustomTextField(onChange = {}, value = "", label = stringResource(id = R.string.familyName), leadingIcon = Icons.Filled.Person )
            CustomTextField(onChange = {}, value = "", label = stringResource(id = R.string.phone), leadingIcon = Icons.Filled.Phone )
            CustomTextField(onChange = {}, value = "", label = stringResource(id = R.string.address), leadingIcon = Icons.Filled.LocationOn )
            CustomTextField(onChange = {}, value = "", label = stringResource(id = R.string.postal_code), leadingIcon = Icons.Filled.AccountCircle )
            CustomTextField(onChange = {}, value = "", label = stringResource(id = R.string.description) , leadingIcon = Icons.Filled.Info )
            ImagePickerField()
            CustomButton(text = stringResource(id = R.string.update),
                onClick = {navController.navigate(AppScreenNavigator.MainAppScreen.route) })
            Row {
                TextButton( onClick = {}){
                    Text(text = stringResource(R.string.add_rib), color = LightSurface)
                }
                Spacer(modifier = Modifier.weight(1f))
                TextButton( onClick = {navController.navigate(AppScreenNavigator.AdvancedEditProfilesAppScreen.route)}){
                    Text(text = stringResource(R.string.advanced_profile), color = LightSurface)
                }
            }
        }
    }
}

@Composable
fun EditProfileTopBar(navController: NavController) {
    Row(modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = { navController.navigate(AppScreenNavigator.MainAppScreen.route) }) {
            Icon(
                imageVector = Icons.Filled.ArrowBack,
                contentDescription = null
            )
        }
        Spacer(modifier = Modifier.width(32.dp))
        Text(text = stringResource(R.string.edit_profile),
            fontWeight = FontWeight.Medium,
            fontSize = TextUnit(20f, TextUnitType.Sp)
        )
    }
}


//    // on back pressed button on android devices
//    BackHandler {
//        navController.popBackStack()
//
//    }

