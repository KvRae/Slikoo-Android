package slikoo.kvrae.slikoo.ui.pages

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import slikoo.kvrae.slikoo.R
import slikoo.kvrae.slikoo.ui.components.CustomButton
import slikoo.kvrae.slikoo.ui.components.PasswordTextField
import slikoo.kvrae.slikoo.viewmodels.MainScreenViewModel

@Composable
fun UpdatePasswordScreen(
    navController: NavController
) {
    val viewModel : MainScreenViewModel = viewModel()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        EditProfileTopBar(
            navController = navController,
            title = stringResource(id = R.string.update_password)
        )
        PasswordTextField(
            value = viewModel.oldPassword,
            label = stringResource(id = R.string.old_password),
            onChange = {viewModel.oldPassword = it}
        )
        PasswordTextField(
            label = stringResource(id = R.string.new_password),
            value = viewModel.newPassword,
            onChange = {viewModel.newPassword = it}
        )
        PasswordTextField(
            label = stringResource(id = R.string.confirm_password),
            value = viewModel.confirmPassword ,
            onChange = {viewModel.confirmPassword = it}
        )
        Spacer(modifier = Modifier.height(16.dp))
        CustomButton(
            text = stringResource(id = R.string.update_password),
            onClick = {
                viewModel.updatePassword(
                    oldPassword = viewModel.oldPassword,
                    newPassword = viewModel.newPassword,
                    confirmPassword = viewModel.confirmPassword
                )
            }
        )
    }
}