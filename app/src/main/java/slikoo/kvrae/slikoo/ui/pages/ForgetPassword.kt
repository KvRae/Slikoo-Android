package slikoo.kvrae.slikoo.ui.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Email
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import slikoo.kvrae.slikoo.R
import slikoo.kvrae.slikoo.ui.components.CustomAlertDialog
import slikoo.kvrae.slikoo.ui.components.CustomButton
import slikoo.kvrae.slikoo.ui.components.CustomTextField
import slikoo.kvrae.slikoo.ui.theme.LightSecondary
import slikoo.kvrae.slikoo.utils.AppScreenNavigator
import slikoo.kvrae.slikoo.viewmodels.ForgetPasswordViewModel



@Composable
fun ForgetPasswordTopBar( navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .statusBarsPadding()
            .height(56.dp)
            .background(LightSecondary)
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically

        ) {
            IconButton(onClick = {
                navController.popBackStack()
                navController.navigate(AppScreenNavigator.SignInAppScreen.route)
            }) {
                Icon(imageVector = Icons.Rounded.ArrowBack, contentDescription = null)
            }

            Text(
                text = stringResource(id = R.string.forgotPassword),
                fontWeight = FontWeight.Medium,
                fontSize = 16.sp,
                modifier = Modifier.padding(start = 16.dp)

            )


        }
    }
}


@Composable
fun EmailInput(navController: NavController) {
    val emailViewModel: ForgetPasswordViewModel = viewModel()
    var isValid by remember {
        mutableStateOf(false)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            ForgetPasswordTopBar(navController = navController)
            Text(
                text = stringResource(R.string.enter_the_email_address_associated_with_your_account),
                style = typography.titleLarge
            )
            Spacer(modifier = Modifier.padding(16.dp))
            CustomTextField(onChange = { emailViewModel.email.value = it },
                value = emailViewModel.email.value,
                label = stringResource(id = R.string.email),
                keyboardType = KeyboardType.Email,
                isError = !emailViewModel.isEmailValid.value,
                leadingIcon = Icons.Rounded.Email)
            Spacer(modifier = Modifier.padding(16.dp))
            CustomButton(onClick = {
                isValid = true
                emailViewModel.isEmailValid.value = emailViewModel.onValidateEmail() && emailViewModel.onValidateEmailNotEmpty()
                if (emailViewModel.isEmailValid.value) emailViewModel.onSendEmail()

            }, text = stringResource(id = R.string.send_email))
        }

        if (isValid) CustomAlertDialog(
            title = stringResource(id = emailViewModel.dialogTitle()),
            message = stringResource(id = emailViewModel.dialogMessage()),
            confirmText = stringResource(id = R.string.ok),
            onConfirm = {
                isValid = false
                if (emailViewModel.resCode.value == 200) {
                    navController.popBackStack()
                    navController.navigate(AppScreenNavigator.SignInAppScreen.route)
                }
            }
        )


    }
}


















//@Composable
//fun OtpInput(navController: NavController) {
//    var code by rememberSaveable { mutableStateOf("") }
//    Box(
//        modifier = Modifier
//            .fillMaxSize()
//            .statusBarsPadding()
//            .background(LightSecondary)
//    ) {
//        Column(
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(16.dp)
//        ) {
//            Text(
//                text = stringResource(R.string.enter_the_code_sent_to_your_email_address),
//                style = typography.titleLarge
//            )
//            Spacer(modifier = Modifier.padding(16.dp))
//            PinView(pinText = "", onPinTextChange = { code = it })
//            Spacer(modifier = Modifier.padding(16.dp))
//            Button(onClick = {
//                onNavigateTo(
//                    navController,
//                    AppScreenNavigator.ResetPasswordAppScreen.route
//                )
//            }) {
//                Text(text = stringResource(R.string.postal_code))
//            }
//        }
//    }
//}


//@Composable
//fun PasswordReset(navController: NavController) {
//    var password by rememberSaveable { mutableStateOf("") }
//    var confirmPassword by rememberSaveable { mutableStateOf("") }
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .statusBarsPadding()
//    ) {
//        Text(text = "Reset Password")
//        PasswordTextField(value = password, placeHolder = password, onChange = { password = it })
//        PasswordTextField(
//            value = confirmPassword,
//            placeHolder = password,
//            onChange = { confirmPassword = it })
//        Spacer(modifier = Modifier.padding(16.dp))
//        Button(onClick = {
//            onNavigateTo(
//                navController,
//                AppScreenNavigator.SignInAppScreen.route
//            )
//        }) {
//            Text(text = "Reset Password")
//        }
//
//    }
//
//}

fun onNavigateTo(navController: NavController, route: String) {
    navController.popBackStack()
    navController.navigate(route)
}

