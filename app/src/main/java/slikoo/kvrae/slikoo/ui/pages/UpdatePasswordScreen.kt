package slikoo.kvrae.slikoo.ui.pages

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import slikoo.kvrae.slikoo.R
import slikoo.kvrae.slikoo.ui.components.CustomAlertDialog
import slikoo.kvrae.slikoo.ui.components.CustomButton
import slikoo.kvrae.slikoo.ui.components.LoadingDialog
import slikoo.kvrae.slikoo.ui.components.PasswordTextField
import slikoo.kvrae.slikoo.viewmodels.EditPasswordViewModel


@Composable
fun UpdatePasswordScreen(
    navController: NavController
) {
    val viewModel : EditPasswordViewModel = viewModel()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .navigationBarsPadding()
            .statusBarsPadding(),

        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        EditProfileTopBar(
            navController = navController,
            title = stringResource(id = R.string.update_password)
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Spacer(modifier = Modifier.height(16.dp))
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
                    )
                }
            )
        }

        if (viewModel.isLoading) {
            LoadingDialog()
        }

        if (viewModel.showDialog) {
            CustomAlertDialog(
                showDialog = viewModel.showDialog,
                title = stringResource(id = R.string.update_password),
                message = if (viewModel.resCode == 200) stringResource(id = R.string.update_password_success)
                else stringResource(id = R.string.update_password_failed),
                confirmText = stringResource(id = R.string.ok),
                onDismiss = {
                    viewModel.showDialog = false
                    if (viewModel.resCode == 200) {
                        navController.popBackStack()
                    }
                },
                onConfirm = {
                    viewModel.showDialog = false
                    if (viewModel.resCode == 200) {
                        navController.popBackStack()
                    }
                }
            )
        }

    }
}