package slikoo.kvrae.slikoo.ui.fragments.signup

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import slikoo.kvrae.slikoo.ui.components.CustomButton
import slikoo.kvrae.slikoo.ui.components.CustomSlider
import slikoo.kvrae.slikoo.ui.components.ImageInputField

@Composable
fun SignUpCidForm(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Carte D'identité", style = MaterialTheme.typography.h4)
        Spacer(modifier = Modifier.size(8.dp))
        CustomSlider(maxSlide = 3, currentSlide = 2)
        Row(
            modifier = Modifier
                .padding(24.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            ImageInputField()
        }
        CustomButton(text = "Suivant", onClick = { navController.navigate("sign_up_profile_picture") })

    }
}