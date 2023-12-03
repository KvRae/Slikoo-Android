package slikoo.kvrae.slikoo.ui.pages

import android.content.Context
import android.widget.Toast
import androidx.activity.compose.BackHandler
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
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import slikoo.kvrae.slikoo.R
import slikoo.kvrae.slikoo.ui.components.CustomAlertDialogWithContent
import slikoo.kvrae.slikoo.ui.components.CustomButton
import slikoo.kvrae.slikoo.ui.components.CustomTextField
import slikoo.kvrae.slikoo.ui.components.ImagePickerField
import slikoo.kvrae.slikoo.ui.components.ProfileImagePicker
import slikoo.kvrae.slikoo.ui.theme.LightSecondary
import slikoo.kvrae.slikoo.ui.theme.LightSurface
import slikoo.kvrae.slikoo.utils.AppScreenNavigator
import slikoo.kvrae.slikoo.viewmodels.MainScreenViewModel

@Composable
fun EditProfileScreen(navController: NavController) {
    val viewModel: MainScreenViewModel = viewModel()

    DisposableEffect(Unit){
        viewModel.getUser()
        onDispose { }
    }

    Box(
        Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .navigationBarsPadding(),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(LightSecondary)
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            EditProfileTopBar(navController = navController)
            ProfileImagePicker(
                imageUri = (viewModel.user.value.avatarUrl+viewModel.user.value.avatar).toUri(),
                onImageSelected = {})
            CustomTextField(
                onChange = {viewModel.user.value.copy( nom = it)},
                value = viewModel.user.value.nom,
                label = stringResource(id = R.string.name),
                leadingIcon = Icons.Filled.Person
            )
            CustomTextField(
                onChange = { viewModel.user.value.copy(prenom = it?: "",) },
                value = viewModel.user.value.prenom?: "",
                label = stringResource(id = R.string.familyName),
                leadingIcon = Icons.Filled.Person
            )
            CustomTextField(
                onChange = { viewModel.user.value.copy(numtel = it)},
                value = viewModel.user.value.numtel,
                label = stringResource(id = R.string.phone),
                leadingIcon = Icons.Filled.Phone
            )
            CustomTextField(
                onChange = { viewModel.user.value.copy(adressepostal = it) },
                value =  viewModel.user.value.adressepostal,
                label = stringResource(id = R.string.address),
                leadingIcon = Icons.Filled.LocationOn
            )
            CustomTextField(
                onChange = { viewModel.user.value.copy(codepostal = it) },
                value = viewModel.user.value.codepostal,
                label = stringResource(id = R.string.postal_code),
                leadingIcon = Icons.Filled.AccountCircle
            )
            CustomTextField(
                onChange = { viewModel.user.value.copy(description = it)},
                value = viewModel.user.value.description,
                label = stringResource(id = R.string.description),
                leadingIcon = Icons.Filled.Info
            )
            ImagePickerField(
                imageUrl = viewModel.user.value.avatarbannerUrl.plus(viewModel.user.value.avatarbanner).toUri(),
                onImageSelected = { viewModel.user.value.avatarbanner = it.toString() }
            )
            CustomButton(text = stringResource(id = R.string.update),
                onClick = { navController.navigate(AppScreenNavigator.MainAppScreen.route) })
            Row {
                TextButton(onClick = { viewModel.showDialog = true}) {
                    Text(text = stringResource(R.string.add_rib), color = LightSurface)
                }
                Spacer(modifier = Modifier.weight(1f))
                TextButton(onClick = { navController.navigate(AppScreenNavigator.AdvancedEditProfilesAppScreen.route) }) {
                    Text(text = stringResource(R.string.advanced_profile), color = LightSurface)
                }
            }
            if (viewModel.showDialog)
                CustomAlertDialogWithContent(
                    title = stringResource(id = R.string.add_rib),
                    content = {
                        CustomTextField(
                            onChange = {  viewModel.user.value.RIB = it},
                            value = viewModel.user.value.RIB?:"",
                            label = stringResource(id = R.string.add_rib),
                            leadingIcon = Icons.Filled.AccountCircle
                        )
                    },
                    confirmText = stringResource(id = R.string.add),
                    dismissText = stringResource(id = R.string.dismiss),
                    onDismiss = { viewModel.showDialog = false },
                    onConfirm = {
                        viewModel.user.value.RIB = viewModel.user.value.RIB
                        viewModel.addRib()
                        viewModel.showDialog = false
                        makeToast(viewModel.ribMessage.value, navController.context)
                    })

        }
    }
    if (viewModel.isLoading) LoadingScreen()

    if (viewModel.isError)
        TextWithButtonScreen(text = stringResource(id = R.string.session_expired),
            buttonText = stringResource(id = R.string.reconnect),
            onClick = { navController.navigate(AppScreenNavigator.SignInAppScreen.route) }
        )
    BackHandler {
        navController.popBackStack()
    }
}

fun makeToast(message: String, context: Context) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}

@Composable
fun EditProfileTopBar(navController: NavController) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = {
            navController.popBackStack()
            navController.navigate(AppScreenNavigator.MainAppScreen.route) }) {
            Icon(
                imageVector = Icons.Filled.ArrowBack,
                contentDescription = null
            )
        }
        Spacer(modifier = Modifier.width(32.dp))
        Text(
            text = stringResource(R.string.edit_profile),
            fontWeight = FontWeight.Medium,
            fontSize = TextUnit(20f, TextUnitType.Sp)
        )
    }
}




