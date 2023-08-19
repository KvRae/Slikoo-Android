package slikoo.kvrae.slikoo.ui.fragments.signup

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Email
import androidx.compose.material.icons.rounded.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import slikoo.kvrae.slikoo.R
import slikoo.kvrae.slikoo.ui.components.CustomButton
import slikoo.kvrae.slikoo.ui.components.CustomSlider
import slikoo.kvrae.slikoo.ui.components.CustomTextField
import slikoo.kvrae.slikoo.ui.components.PasswordTextField
import slikoo.kvrae.slikoo.utils.SignUpNavigator


@Composable
fun SignUpForm(onChange: (String) -> Unit) {
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(id = R.string.signUp),
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.size(8.dp))
            CustomSlider(maxSlide = 4, currentSlide = 1)


            CustomTextField(onChange = {}, value = "", label = stringResource(id = R.string.name), leadingIcon = Icons.Rounded.Person)

            CustomTextField(onChange = {}, value = "", label = stringResource(id = R.string.familyName), leadingIcon = Icons.Rounded.Person)

            CustomTextField(onChange = {}, value = "", label = stringResource(id = R.string.email), leadingIcon = Icons.Rounded.Email)

            PasswordTextField(label = "Mot de passe", value = password, onChange = { password = it})

            PasswordTextField(label = "Repeter mot de passe", value = confirmPassword, onChange = { confirmPassword = it})


            Spacer(modifier = Modifier.padding(4.dp))

            CustomButton(text = "Suivant", onClick = { onChange(SignUpNavigator.SignUpSecondFormFragment.route)})

        }
    }
}