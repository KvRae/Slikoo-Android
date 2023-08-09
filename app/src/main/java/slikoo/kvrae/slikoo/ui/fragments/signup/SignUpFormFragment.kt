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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import slikoo.kvrae.slikoo.ui.components.CustomButton
import slikoo.kvrae.slikoo.ui.components.CustomSlider
import slikoo.kvrae.slikoo.ui.components.CustomTextField
import slikoo.kvrae.slikoo.ui.components.PasswordTextField
import slikoo.kvrae.slikoo.utils.SignUpNavigator


@Composable
fun SignUpForm(onChange: (String) -> Unit) {
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
                text = "Sign Up",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.size(8.dp))
            CustomSlider(maxSlide = 4, currentSlide = 1)


            CustomTextField(onChange = {}, value = "", label = "Nom", leadingIcon = Icons.Rounded.Person)

            CustomTextField(onChange = {}, value = "", label = "Prenom", leadingIcon = Icons.Rounded.Person)

            CustomTextField(onChange = {}, value = "", label = "Adresse Electronique", leadingIcon = Icons.Rounded.Email)

            PasswordTextField(label = "Mot de passe", value = "", placeHolder = "entrer votre mot de passe", onChange = {})

            PasswordTextField(label = "Repeter mot de passe", value = "", placeHolder = "repeter votre mot de passe", onChange = {})


            Spacer(modifier = Modifier.padding(4.dp))

            CustomButton(text = "Suivant", onClick = { onChange(SignUpNavigator.SignUpSecondFormFragment.route)})

        }
    }
}