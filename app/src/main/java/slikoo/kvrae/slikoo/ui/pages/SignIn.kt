package slikoo.kvrae.slikoo.ui.pages

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Email
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import slikoo.kvrae.slikoo.R
import slikoo.kvrae.slikoo.ui.components.CustomButton
import slikoo.kvrae.slikoo.ui.components.CustomTextField
import slikoo.kvrae.slikoo.ui.components.PasswordTextField
import slikoo.kvrae.slikoo.ui.theme.LightBackground
import slikoo.kvrae.slikoo.ui.theme.LightPrimary
import slikoo.kvrae.slikoo.ui.theme.LightPrimaryVariant
import slikoo.kvrae.slikoo.ui.theme.LightSecondary
import slikoo.kvrae.slikoo.ui.theme.LightSurface
import slikoo.kvrae.slikoo.utils.AppScreenNavigator


@Composable
fun LoginForm(navController: NavController) {
    val logo = R.drawable.slikoo_white
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val context = LocalContext.current

    Box(
        modifier = Modifier
            .fillMaxSize()
            .navigationBarsPadding()
            .background(LightPrimary.copy(alpha = 1f))
    ) {
        SignInHeader(logo = logo)

        Surface(
            color = LightSecondary,
            border = BorderStroke(1.dp, LightSecondary),
            shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp),
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .clip(shape = RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp))
                    .background(LightSecondary),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = stringResource(id = R.string.connect),
                    fontWeight = FontWeight.Bold,
                    style = TextStyle(
                        color = LightBackground,
                        fontSize = MaterialTheme.typography.h5.fontSize,
                    )
                )
                Spacer(modifier = Modifier.height(16.dp))
                CustomTextField(
                    onChange = { username = it },
                    value = username,
                    label = stringResource(id = R.string.email),
                    keyboardType = KeyboardType.Email,
                    leadingIcon = Icons.Rounded.Email
                )

                Spacer(modifier = Modifier.height(16.dp))

                PasswordTextField(
                    value = password,
                    label = stringResource(id = R.string.password),
                    placeHolder = stringResource(id = R.string.password_placeholder),
                    onChange = { password = it }
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Submit button
                CustomButton(text = stringResource(id = R.string.connect),
                    onClick = {
                        onSubmit(navController)
                        onMakeToast(
                            context = context,
                            message = context.getString(R.string.welcome)
                        )
                    })

                Spacer(modifier = Modifier.weight(1f))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {
                    TextButton(onClick = {
                        onNavigate(
                            navController,
                            AppScreenNavigator.SignUpAppScreen.route
                        )
                    }) {
                        Text(
                            stringResource(id = R.string.signUp),
                            color = LightSurface,
                            fontSize = MaterialTheme.typography.body2.fontSize,
                        )
                    }
                    Spacer(modifier = Modifier.weight(1f))
                    TextButton(onClick = {
                        onNavigate(
                            navController,
                            AppScreenNavigator.ForgotPasswordAppScreen.route
                        )
                    })
                    {
                        Text(
                            stringResource(id = R.string.forgotPassword),
                            color = LightSurface,
                            fontSize = MaterialTheme.typography.body2.fontSize
                        )
                    }

                }
            }
        }
    }
}


@Composable
fun SignInHeader(logo: Int = R.drawable.slikoo_white) {
    Column(
        modifier = Modifier
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(LightSecondary)
                .height(400.dp),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.login), // Load the drawable resource
                contentDescription = stringResource(R.string.background),
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(LightPrimary.copy(alpha = 0.9f))
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(32.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = logo), // Load the drawable resource
                    contentDescription = "Logo",
                    modifier = Modifier.size(150.dp)
                )
                Text(
                    text = stringResource(id = R.string.welcome_description),
                    style = TextStyle(
                        color = LightPrimaryVariant,
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                    )
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = stringResource(id = R.string.welcome_sub_description),
                    style = TextStyle(
                        color = LightPrimaryVariant,
                        fontSize = MaterialTheme.typography.body2.fontSize,
                        fontWeight = FontWeight.Normal,
                    )
                )

            }
        }
    }
}


fun onNavigate(navController: NavController, route: String) {
    navController.navigate(route)

}

fun onSubmit(navController: NavController) {
    navController.popBackStack()
    navController.navigate(AppScreenNavigator.MainAppScreen.route)

}

fun onMakeToast(context: Context, message: String) {
    Toast.makeText(
        context,
        message,
        Toast.LENGTH_LONG
    )
        .show()
}

//fun getGoogleSignInClient(context: Context ): GoogleSignInClient {
//    val signInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
////         Request id token if you intend to verify google user from your backend server
////        .requestIdToken(context.getString(R.string.backend_client_id))
//        .requestEmail()
//        .build()
//
//    return GoogleSignIn.getClient(context, signInOptions)
//}
