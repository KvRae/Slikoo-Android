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
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import slikoo.kvrae.slikoo.R
import slikoo.kvrae.slikoo.ui.components.CustomTextField
import slikoo.kvrae.slikoo.ui.components.PasswordTextField
import slikoo.kvrae.slikoo.ui.components.PinView
import slikoo.kvrae.slikoo.ui.theme.LightSecondary
import slikoo.kvrae.slikoo.utils.AppScreenNavigator


@Preview(showBackground = true)
@Composable
fun ForgetPasswordTopBar() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .statusBarsPadding()
            .height(56.dp)
            .background(LightSecondary)
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            //horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically

        ) {
            IconButton(onClick = { /*TODO*/ }) {
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
    var email by rememberSaveable { mutableStateOf("") }
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
            ForgetPasswordTopBar()
            Text(
                text = stringResource(R.string.enter_the_email_address_associated_with_your_account),
                style = typography.titleLarge
            )
            Spacer(modifier = Modifier.padding(16.dp))
            CustomTextField(onChange = { email = it },
                value = email,
                label = stringResource(id = R.string.email),
                leadingIcon = Icons.Rounded.Email)
            Spacer(modifier = Modifier.padding(16.dp))
            Button(onClick = {
                onNavigateTo(
                    navController,
                    AppScreenNavigator.VerifyEmailAppScreen.route
                )
            }) {
                Text(text = stringResource(R.string.get_code))
            }
        }
    }
}


@Composable
fun OtpInput(navController: NavController) {
    var code by rememberSaveable { mutableStateOf("") }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .background(LightSecondary)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Text(
                text = stringResource(R.string.enter_the_code_sent_to_your_email_address),
                style = typography.titleLarge
            )
            Spacer(modifier = Modifier.padding(16.dp))
            PinView(pinText = "", onPinTextChange = { code = it })
            Spacer(modifier = Modifier.padding(16.dp))
            Button(onClick = {
                onNavigateTo(
                    navController,
                    AppScreenNavigator.ResetPasswordAppScreen.route
                )
            }) {
                Text(text = stringResource(R.string.postal_code))
            }
        }
    }
}


@Composable
fun PasswordReset(navController: NavController) {
    var password by rememberSaveable { mutableStateOf("") }
    var confirmPassword by rememberSaveable { mutableStateOf("") }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
    ) {
        Text(text = "Reset Password")
        PasswordTextField(value = password, placeHolder = password, onChange = { password = it })
        PasswordTextField(
            value = confirmPassword,
            placeHolder = password,
            onChange = { confirmPassword = it })
        Spacer(modifier = Modifier.padding(16.dp))
        Button(onClick = {
            onNavigateTo(
                navController,
                AppScreenNavigator.SignInAppScreen.route
            )
        }) {
            Text(text = "Reset Password")
        }

    }

}

fun onNavigateTo(navController: NavController, route: String) {
    navController.popBackStack()
    navController.navigate(route)
}

