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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Email
import androidx.compose.material.icons.rounded.Info
import androidx.compose.material.icons.rounded.Lock
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import slikoo.kvrae.slikoo.R
import slikoo.kvrae.slikoo.ui.components.CustomButton
import slikoo.kvrae.slikoo.ui.components.CustomTextField
import slikoo.kvrae.slikoo.ui.components.PasswordTextField
import slikoo.kvrae.slikoo.ui.fragments.profile.makeToast
import slikoo.kvrae.slikoo.ui.theme.LightGrey
import slikoo.kvrae.slikoo.ui.theme.LightSecondary
import slikoo.kvrae.slikoo.utils.AppScreenNavigator
import slikoo.kvrae.slikoo.viewmodels.ForgetPasswordViewModel


@Composable
fun ForgetPasswordScreen(navController: NavController) {
    val viewModel: ForgetPasswordViewModel = viewModel()
    var screenTitle by rememberSaveable {
        mutableStateOf("EMAIL")
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(LightSecondary)
    ) {
        ForgetPasswordTopBar(navController = navController)
        when (screenTitle) {
            "EMAIL" -> EmailInput( viewModel = viewModel, onScreenChange = { screenTitle = it })
            "OTP" -> OtpInput( viewModel = viewModel, onScreenChange = { screenTitle = it })
            "PASSWORD" -> PasswordReset(navController = navController , viewModel = viewModel, onScreenChange = { screenTitle = it })
        }
    }

}

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
            }) {
                Icon(imageVector = Icons.Rounded.ArrowBack, contentDescription = null)
            }

            Text(
                text = stringResource(id = R.string.forgotPassword),
                fontWeight = FontWeight.Medium,
                fontSize = 16.sp,
                modifier = Modifier.padding(start = 18.dp)

            )


        }
    }
}


@Composable
fun EmailInput(
    viewModel: ForgetPasswordViewModel,
    onScreenChange: (String) -> Unit
) {
    val context = LocalContext.current

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(LightSecondary)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Icon(
                imageVector =  Icons.Rounded.Email,
                contentDescription =  stringResource(id = R.string.email),
                tint = LightGrey,
                modifier = Modifier
                    .size(200.dp)
                    .align(Alignment.CenterHorizontally)
                    .padding(16.dp)
            )
            Text(
                text = stringResource(R.string.enter_the_email_address_associated_with_your_account),
                style = typography.titleLarge
            )
            Spacer(modifier = Modifier.padding(16.dp))
            CustomTextField(onChange = { viewModel.email.value = it },
                value = viewModel.email.value,
                label = stringResource(id = R.string.email),
                keyboardType = KeyboardType.Email,
                isError = viewModel.isEmailValid.value,
                errorMessage =
                if (viewModel.email.value.isEmpty()) stringResource(id = R.string.email_is_empty)
                else stringResource(id = R.string.emailInvalid),
                leadingIcon = Icons.Rounded.Email)
            Spacer(modifier = Modifier.padding(16.dp))
            CustomButton(
                onClick = {
                    onMakeToast(context, viewModel.emailError)
                    viewModel.onEmailVerify()
                          },
                text = stringResource(id = R.string.send_email)
            )
        }

        if (viewModel.onNavigateToCode.value){
            val toastMsg = stringResource(id = R.string.email_sent)
            DisposableEffect(Unit){
                onScreenChange("OTP")
                makeToast(context, toastMsg)
                onDispose {
                    viewModel.onNavigate.value = false
                }
            }
        }

        if (viewModel.isLoading.value) LoadingScreen()
    }
}


@Composable
fun OtpInput(
    viewModel: ForgetPasswordViewModel,
    onScreenChange: (String) -> Unit
) {
    val context = LocalContext.current
    Box(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .background(LightSecondary)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = Icons.Rounded.Info,
                contentDescription = stringResource(id = R.string.code),
                tint = LightGrey,
                modifier = Modifier
                    .size(200.dp)
                    .align(Alignment.CenterHorizontally)
                    .padding(16.dp)
            )
            Text(
                text = stringResource(R.string.enter_the_code_sent_to_your_email_address),
                style = typography.titleLarge
            )
            Spacer(modifier = Modifier.padding(16.dp))
            CustomTextField(
                onChange = { viewModel.code.value = it },
                value = viewModel.code.value ,
                label = stringResource(id = R.string.code),
                keyboardType = KeyboardType.Text,
                isError = viewModel.isEmailValid.value,
                errorMessage =
                if (viewModel.code.value.isEmpty()) stringResource(id = R.string.code_is_empty)
                else stringResource(id = R.string.codeInvalid),
                leadingIcon = Icons.Rounded.Info
            )
            Spacer(modifier = Modifier.padding(16.dp))
            CustomButton(
                text = stringResource(id = R.string.verify),
                onClick = {
                    viewModel.onCodeVerify()
                })
        }
        if (viewModel.onNavigateToPassword.value){
            val toastMsg = stringResource(id = R.string.code_verified)
            DisposableEffect(Unit){
                onScreenChange("PASSWORD")
                makeToast(context, toastMsg)
                onDispose {
                    viewModel.onNavigate.value = false
                }
            }
        }
        if (viewModel.isLoading.value) LoadingScreen()
    }
}


@Composable
fun PasswordReset(
    navController: NavController,
    viewModel: ForgetPasswordViewModel,
    onScreenChange: (String) -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(LightSecondary)
            .padding(16.dp)
            .statusBarsPadding(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon (
            imageVector = Icons.Rounded.Lock,
            contentDescription = stringResource(id = R.string.reset_password),
            tint = LightGrey,
            modifier = Modifier
                .size(200.dp)
                .align(Alignment.CenterHorizontally)
                .padding(16.dp)
        )
        Text(text = stringResource(id = R.string.reset_password)   ,
            style = typography.titleLarge)
        PasswordTextField(
            value = viewModel.password.value,
            label = stringResource(id = R.string.password),
            placeHolder = viewModel.password.value,
            onChange = { viewModel.password.value = it },
            isError = viewModel.isPasswordValid.value,
            errorMessage = if (viewModel.password.value.isEmpty()) stringResource(id = R.string.password_is_empty)
            else stringResource(id = R.string.password_short)
        )
        PasswordTextField(
            value = viewModel.confirmPassword.value,
            label = stringResource(id = R.string.confirm_password),
            placeHolder = viewModel.password.value,
            onChange = { viewModel.confirmPassword.value = it },
            isError = viewModel.isPasswordValid.value,
            errorMessage = if (viewModel.confirmPassword.value.isEmpty()) stringResource(id = R.string.password_is_empty)
            else stringResource(id = R.string.password_short)
        )
        Spacer(modifier = Modifier.padding(16.dp))
        CustomButton(
            onClick = {
                viewModel.onPasswordVerify()
            },
            text = stringResource(id = R.string.verify)
        )
        if (viewModel.onNavigate.value){
            val toastMsg = stringResource(id = R.string.password_changed)
            DisposableEffect(Unit){
                onNavigateTo(navController, AppScreenNavigator
                    .SignInAppScreen
                    .route)
                makeToast(navController.context, toastMsg)
                onDispose {
                    viewModel.onNavigate.value = false
                    onScreenChange("EMAIL")
                }
            }
        }
    }
    if (viewModel.isLoading.value) LoadingScreen()

}

fun onNavigateTo(navController: NavController, route: String) {
    navController.popBackStack()
    navController.navigate(route)
}

