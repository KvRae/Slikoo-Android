package slikoo.kvrae.slikoo.ui.fragments.signup

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.TextButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import slikoo.kvrae.slikoo.ui.components.CustomButton
import slikoo.kvrae.slikoo.ui.components.CustomSlider
import slikoo.kvrae.slikoo.ui.components.ImagePickerField
import slikoo.kvrae.slikoo.ui.theme.LightSurface
import slikoo.kvrae.slikoo.utils.SignUpNavigator

@Composable
fun SignUpCidForm(onChange: (String) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Carte D'identité", style = MaterialTheme.typography.h4)
        Spacer(modifier = Modifier.size(8.dp))
        CustomSlider(maxSlide = 4, currentSlide = 3)
        Row(
            modifier = Modifier
                .padding(24.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            ImagePickerField()
        }
        CustomButton(text = "Suivant",
            onClick = { onChange(SignUpNavigator.SignUpProfilePictureFragment.route) })
        TextButton(onClick = { onChange(SignUpNavigator.SignUpSecondFormFragment.route) }) {
            Text(text = "Precedent", color = LightSurface)
        }

    }
}