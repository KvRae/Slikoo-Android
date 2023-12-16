package slikoo.kvrae.slikoo.ui.pages

import android.annotation.SuppressLint
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
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import kotlinx.coroutines.launch
import slikoo.kvrae.slikoo.R
import slikoo.kvrae.slikoo.data.datasources.entities.User
import slikoo.kvrae.slikoo.ui.components.CustomAlertDialogWithContent
import slikoo.kvrae.slikoo.ui.components.CustomButton
import slikoo.kvrae.slikoo.ui.components.CustomTextField
import slikoo.kvrae.slikoo.ui.components.ImagePickerField
import slikoo.kvrae.slikoo.ui.components.ProfileImagePicker
import slikoo.kvrae.slikoo.ui.fragments.event.getRealPathFromURI
import slikoo.kvrae.slikoo.ui.fragments.profile.makeToast
import slikoo.kvrae.slikoo.ui.theme.LightBackground
import slikoo.kvrae.slikoo.ui.theme.LightSecondary
import slikoo.kvrae.slikoo.ui.theme.LightSurface
import slikoo.kvrae.slikoo.utils.AppScreenNavigator
import slikoo.kvrae.slikoo.utils.compressFile
import slikoo.kvrae.slikoo.viewmodels.EditProfileViewModel
import java.io.File

@SuppressLint("SuspiciousIndentation")
@Composable
fun EditProfileScreen(navController: NavController) {
    val viewModel: EditProfileViewModel = viewModel()
    val coroutineScope = rememberCoroutineScope()
    var user by remember {
        mutableStateOf(User())
    }
    if (viewModel.user.id == 0)
        DisposableEffect(Unit) {
            viewModel.getUser()
            onDispose { }
        }

    Box(
        Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .navigationBarsPadding(),
    ) {
        if (viewModel.user.id != 0)
        Column(
            Modifier
                .fillMaxSize()
                .background(LightSecondary)
        ) {
            EditProfileTopBar(navController = navController, enableShadow = true)
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(LightSecondary)
                    .padding(16.dp)
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                ProfileImagePicker(
                    imageUri = viewModel.userAvatar,
                    onImageSelected = {
                        viewModel.userAvatar = it!!
                    })
                CustomTextField(
                    onChange = {
                        user = user.copy(nom = it)
                    },
                    value = user.nom,
                    placeHolder = stringResource(id = R.string.familyName),
                    label = if(user.nom.isNotEmpty()) stringResource(id = R.string.familyName) else viewModel.user.nom,
                    leadingIcon = Icons.Filled.Person
                )
                CustomTextField(
                    onChange = { user = user.copy(prenom = it) },
                    value = user.prenom,
                    placeHolder = stringResource(id = R.string.givenName),
                    label = if (user.prenom.isNotEmpty()) stringResource(id = R.string.givenName) else viewModel.user.prenom,
                    leadingIcon = Icons.Filled.Person
                )
                CustomTextField(
                    onChange = { user = user.copy(numtel = it) },
                    value = user.numtel,
                    placeHolder = stringResource(id = R.string.phone),
                    label = if (user.numtel.isNotEmpty()) stringResource(id = R.string.phone) else viewModel.user.numtel,
                    leadingIcon = Icons.Filled.Phone
                )
                CustomTextField(
                    onChange = { user = user.copy(adressepostal = it) },
                    value = user.adressepostal,
                    placeHolder = stringResource(id = R.string.address),
                    label = if (user.adressepostal.isNotEmpty()) stringResource(id = R.string.address) else viewModel.user.adressepostal,
                    leadingIcon = Icons.Filled.LocationOn
                )
                CustomTextField(
                    onChange = {user = user.copy(codepostal = it)},
                    value = user.codepostal,
                    placeHolder = stringResource(id = R.string.postal_code),
                    label = if (user.codepostal.isNotEmpty()) stringResource(id = R.string.postal_code) else viewModel.user.codepostal,
                    leadingIcon = Icons.Filled.AccountCircle
                )
                CustomTextField(
                    onChange = { user = user.copy(description = it)},
                    value = user.description,
                    placeHolder = stringResource(id = R.string.description),
                    label = if (user.description.isNotEmpty()) stringResource(id = R.string.description) else viewModel.user.description,
                    leadingIcon = Icons.Filled.Info
                )
                ImagePickerField(
                    imageUrl = viewModel.userBanner,
                    onImageSelected = { viewModel.userBanner = it!! }
                )
                CustomButton(text = stringResource(id = R.string.update),
                    onClick = {
                            coroutineScope.launch {

                                val userAvatar =
                                    if (viewModel.userAvatar.toString().contains("https") || viewModel.userAvatar.toString().isNullOrEmpty())
                                        null
                                    else
                                        compressFile(
                                            navController.context,
                                            File(
                                                getRealPathFromURI(
                                                    context = navController.context,
                                                    uri = viewModel.userAvatar
                                                ) ?: ""
                                            ))
                                val userBanner =
                                    if (viewModel.userBanner.toString().contains("https") || viewModel.userBanner.toString().isNullOrEmpty())
                                        null
                                    else
                                        compressFile(
                                            navController.context,
                                            File(
                                                getRealPathFromURI(
                                                    context = navController.context,
                                                    uri = viewModel.userBanner
                                                ) ?: ""
                                            )
                                        )
                                val cin =
                                    if (viewModel.userCin.toString().contains("https") || viewModel.userCin.toString().isNullOrEmpty())
                                        null
                                    else
                                        compressFile(
                                            navController.context,
                                            File(
                                                getRealPathFromURI(
                                                    context = navController.context,
                                                    uri = viewModel.userCin
                                                ) ?: ""
                                            )
                                        )
                                viewModel.updateUser(
                                    user = user,
                                    userAvatar = userAvatar,
                                    userBanner = userBanner,
                                    cin = cin
                                )
                            }
                    })
                Row {
                    TextButton(onClick = { viewModel.showDialog = true }) {
                        Text(text = stringResource(R.string.add_rib), color = LightSurface)
                    }
                    Spacer(modifier = Modifier.weight(1f))
                    TextButton(onClick = { navController.navigate("advanced_edit_profile_screen/${viewModel.user.id}") }) {
                        Text(text = stringResource(R.string.advanced_profile), color = LightSurface)
                    }
                }
                if (viewModel.showDialog)
                    CustomAlertDialogWithContent(
                        title = stringResource(id = R.string.add_rib),
                        content = {
                            CustomTextField(
                                onChange = { user = user.copy(RIB = it) },
                                value = user.RIB ?: "",
                                placeHolder = stringResource(id = R.string.add_rib),
                                label = if (!user.RIB.isNullOrEmpty()) stringResource(id = R.string.add_rib) else viewModel.user.RIB
                                    ?: "",
                                leadingIcon = Icons.Filled.AccountCircle
                            )
                        },
                        confirmText = stringResource(id = R.string.add),
                        dismissText = stringResource(id = R.string.dismiss),
                        onDismiss = { viewModel.showDialog = false },
                        onConfirm = {
                            viewModel.addRib(
                                rib = user.RIB ?: ""
                            )
                            viewModel.showDialog = false
                        }
                    )

            }
        }
    }


    if (viewModel.isLoading) LoadingScreen()

    if (viewModel.isError)
        TextWithButtonScreen(text = stringResource(id = R.string.session_expired),
            buttonText = stringResource(id = R.string.reconnect),
            onClick = { navController.navigate(AppScreenNavigator.SignInAppScreen.route) }
        )
    if (viewModel.navigate){
    val toastMsg = stringResource(id = R.string.profile_updated)
        DisposableEffect(Unit ){
            navController.popBackStack()
            makeToast(navController.context,toastMsg )
            onDispose {
                viewModel.navigate = false
            }
        }
    }

    BackHandler {
        navController.popBackStack()
    }
}



@Composable
fun EditProfileTopBar(
    navController: NavController,
    title : String = stringResource(R.string.edit_profile),
    enableShadow : Boolean = false // this is to enable shadow inside the top bar
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(
            modifier = Modifier
                .padding(16.dp)
                .background(LightSecondary, shape = CircleShape),
            onClick = {
            navController.popBackStack()
        }) {
            Icon(
                imageVector = Icons.Filled.ArrowBack,
                contentDescription = "",
                tint = LightBackground,
                modifier = Modifier

            )
        }
        Spacer(modifier = Modifier.width(32.dp))
        Text(
            text = title,
            fontWeight = FontWeight.Medium,
            fontSize = TextUnit(20f, TextUnitType.Sp)
        )
    }
}




