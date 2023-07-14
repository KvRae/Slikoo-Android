package slikoo.kvrae.slikoo.ui.fragments.signup

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import slikoo.kvrae.slikoo.R
import slikoo.kvrae.slikoo.ui.components.CustomButton
import slikoo.kvrae.slikoo.ui.components.CustomSlider
import slikoo.kvrae.slikoo.ui.theme.ButtonsAndIcons



@Composable
fun ProfilePictureSection(navController : NavController) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Photo de profil", style = MaterialTheme.typography.h4)
            Spacer(modifier = Modifier.size(8.dp))
            CustomSlider(maxSlide = 3, currentSlide = 3)
            ProfileImagePicker()
            CustomButton(text = "Terminer", onClick = {  })

        }
}


//@Preview(showBackground = true)
@Composable
fun ProfileImagePicker() {
    val imageUrl = ""
    Box(modifier = Modifier
        .padding(8.dp)
        .size(200.dp),
        contentAlignment = Alignment.Center,
        ) {
        IconButton(onClick = { /*TODO*/ },
            modifier = Modifier
                .padding(8.dp)
                .fillMaxSize()
                .clip(shape = RoundedCornerShape(50))
                .border(1.dp, Color.Transparent, RoundedCornerShape(50)),
            enabled = imageUrl.isNotEmpty(),
            colors = IconButtonDefaults.filledIconButtonColors()
        ) {
            Icon(imageVector = ImageVector.vectorResource(id = R.drawable.camera),
                contentDescription = "",
                tint = ButtonsAndIcons,
                modifier = Modifier.size(50.dp)

                )
        }

    }
}