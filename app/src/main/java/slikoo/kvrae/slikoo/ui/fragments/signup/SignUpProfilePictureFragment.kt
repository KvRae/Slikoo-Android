package slikoo.kvrae.slikoo.ui.fragments.signup

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import slikoo.kvrae.slikoo.R
import slikoo.kvrae.slikoo.ui.components.CustomButton
import slikoo.kvrae.slikoo.ui.components.CustomSliderPointers
import slikoo.kvrae.slikoo.ui.components.LoadingDialog
import slikoo.kvrae.slikoo.ui.components.ProfileImagePicker
import slikoo.kvrae.slikoo.ui.fragments.event.getRealPathFromURI
import slikoo.kvrae.slikoo.ui.theme.LightSurface
import slikoo.kvrae.slikoo.utils.SignUpNavigator
import slikoo.kvrae.slikoo.viewmodels.SignUpViewModel
import java.io.File


@Composable
fun ProfilePictureSection(
    onChange: (String) -> Unit,
    navController: NavController,
) {
    val context = LocalContext.current
    val viewModel: SignUpViewModel = viewModel()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = stringResource(R.string.profile_pic), style = MaterialTheme.typography.h4)
        Spacer(modifier = Modifier.size(8.dp))
        CustomSliderPointers(maxSlide = 4, currentSlide = 4)
        ProfileImagePicker(
            imageUri = viewModel.profilePictureUri,
            onImageSelected = { viewModel.profilePictureUri = it!!
                             },
        )
        CustomButton(text = stringResource(R.string.finish),
            onClick = {
                val cidFile = File(getRealPathFromURI(viewModel.profilePictureUri, context))
                val profilePictureFile = File(getRealPathFromURI(viewModel.profilePictureUri, context))
                viewModel.onRegister(
                    avatar = profilePictureFile,
                    cidAvatar = cidFile,
                )
                /*navController.popBackStack()
                navController.navigate(AppScreenNavigator.SignInAppScreen.route)*/
            }
        )
        TextButton(onClick = { onChange(SignUpNavigator.SignUpIDCFragment.route) }) {
            Text(text = stringResource(id = R.string.previous), color = LightSurface)
        }
    }

    if (viewModel.isLoading) LoadingDialog()
}









