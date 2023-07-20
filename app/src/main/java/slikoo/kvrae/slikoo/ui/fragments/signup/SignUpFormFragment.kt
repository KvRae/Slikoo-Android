package slikoo.kvrae.slikoo.ui.fragments.signup

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import slikoo.kvrae.slikoo.ui.components.CustomButton
import slikoo.kvrae.slikoo.ui.components.CustomSlider
import slikoo.kvrae.slikoo.ui.components.CustomTextField


@Composable
fun SignUpForm(navController: NavController) {
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
            CustomSlider(currentSlide =  1, maxSlide = 3)

            CustomTextField(onChange = {}, value = "", label = "Nom")

            CustomTextField(onChange = {}, value = "", label = "Prenom")

            CustomTextField(onChange = {}, value = "", label = "Adresse Electronique")

            CustomTextField(onChange = {}, value = "", label = "Mot de passe")

            CustomTextField(onChange = {}, value = "", label = "Confirmer le mot de passe")

//            ExpandableCard(title = "Aditionnel") {
//                CustomTextField(onChange = {}, value = "", label = "Numero de telephone")
//
//                CustomTextField(onChange = {}, value = "", label = "Code postal")
//
//                CustomTextField(
//                    onChange = {},
//                    value = "",
//                    label = "Description profil",
//                    modifier = Modifier.fillMaxSize()
//                )
//            }

//            Spacer(modifier = Modifier.padding(8.dp))

            CustomButton(text = "Suivant", onClick = { navController.navigate("sign_up_idc") })

        }
    }
}