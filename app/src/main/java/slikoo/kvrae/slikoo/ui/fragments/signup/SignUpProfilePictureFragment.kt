package slikoo.kvrae.slikoo.ui.fragments.signup

import android.util.Log
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.core.net.toFile
import androidx.core.net.toUri
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import slikoo.kvrae.slikoo.R
import slikoo.kvrae.slikoo.ui.components.CustomButton
import slikoo.kvrae.slikoo.ui.components.CustomSliderPointers
import slikoo.kvrae.slikoo.ui.components.ProfileImagePicker
import slikoo.kvrae.slikoo.ui.theme.LightSurface
import slikoo.kvrae.slikoo.utils.SignUpNavigator
import slikoo.kvrae.slikoo.viewmodels.SignUpViewModel


@Composable
fun ProfilePictureSection(
    onChange: (String) -> Unit,
    navController: NavController,
) {
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
            imageUri = "${viewModel.profilePicture}".toUri(),
            onImageSelected = { viewModel.profilePicture.value = it.toFile() }
        )
        CustomButton(text = stringResource(R.string.finish),
            onClick = {
                viewModel.onRegister()
                Log.d("Terminer", "clicked")
                /*navController.popBackStack()
                navController.navigate(AppScreenNavigator.SignInAppScreen.route)*/
            }
        )
        TextButton(onClick = { onChange(SignUpNavigator.SignUpIDCFragment.route) }) {
            Text(text = stringResource(id = R.string.previous), color = LightSurface)
        }
    }
}



