package slikoo.kvrae.slikoo.ui.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EmailInput(navController: NavController){
    var email by rememberSaveable { mutableStateOf("") }
    Box(modifier = Modifier
        .fillMaxWidth()
        .background(MaterialTheme.colorScheme.background)) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Text(text ="Enter the email address associated with your account",
                style = typography.titleLarge)
            Spacer(modifier = Modifier.padding(16.dp))
            TextField(

                value = email,
                onValueChange = { email = it },
                label = { Text("Email") }

            )
            Spacer(modifier = Modifier.padding(16.dp))
            Button(onClick = { navigateTo(navController,"verify_email_screen") }) {
                Text(text = "Get Code")
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OtpInput(navController: NavController) {
    var code by rememberSaveable { mutableStateOf("") }
    Box(modifier = Modifier
        .fillMaxWidth()
        .background(MaterialTheme.colorScheme.background)) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Text(text ="Enter the code sent to your email address",
                style = typography.titleLarge)
            Spacer(modifier = Modifier.padding(16.dp))
            TextField(
                value = code,
                onValueChange = { code = it },
                label = { Text("Email") }
            )
            Spacer(modifier = Modifier.padding(16.dp))
            Button(onClick = { navigateTo(navController,"reset_password_screen") }) {
                Text(text = "Get Code")
            }
        }
    }
}


@Composable
fun PasswordReset(navController: NavController) {
  Column(modifier = Modifier.fillMaxSize()) {
      Text(text = "Reset Password")
        Spacer(modifier = Modifier.padding(16.dp))
      Button(onClick = { navigateTo(navController,"sign_in_screen")}) {
            Text(text = "Reset Password")
      }

  }

}

fun navigateTo(navController: NavController, route: String) {
    navController.navigate(route)
}

