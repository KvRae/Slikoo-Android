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
import androidx.compose.material.icons.rounded.LocationOn
import androidx.compose.material.icons.rounded.Phone
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import slikoo.kvrae.slikoo.ui.components.CustomButton
import slikoo.kvrae.slikoo.ui.components.CustomSlider
import slikoo.kvrae.slikoo.ui.components.CustomTextField
import slikoo.kvrae.slikoo.utils.SignUpNavigator


@Composable
fun SignUpSecondForm( onChange: (String) -> Unit) {

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
            Spacer(modifier = Modifier.size(16.dp))
            Text(
                text = "Sign Up",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.size(8.dp))
                CustomSlider(maxSlide = 4, currentSlide = 2)

                CustomTextField(onChange = {}, value = "", label = "Numero de telephone", leadingIcon = Icons.Rounded.Phone)

                CustomTextField(onChange = {}, value = "", label = "Code postal", leadingIcon = Icons.Rounded.LocationOn)

                CustomTextField(
                    onChange = {},
                    value = "",
                    label = "Description profil",
                    modifier = Modifier.fillMaxSize())


            Spacer(modifier = Modifier.padding(32.dp))

            CustomButton(text = "Suivant", onClick = { onChange(SignUpNavigator.SignUpIDCFragment.route)})

            TextButton(onClick = { onChange(SignUpNavigator.SignUpFormFragment.route)}) {
                androidx.compose.material3.Text(text = "Precedent")
            }

        }
    }
}