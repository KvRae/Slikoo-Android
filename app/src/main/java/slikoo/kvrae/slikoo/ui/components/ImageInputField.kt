package slikoo.kvrae.slikoo.ui.components

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.OutlinedCard
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import slikoo.kvrae.slikoo.R


@Composable
fun ImageInputField() {
    var imageUrl by remember {
        mutableStateOf<Uri?>(null)
    }
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = { uri ->
            imageUrl = uri
        }
    )
    OutlinedCard {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp) // Adjust the height value as needed
                .clickable {
                    launcher.launch(PickVisualMediaRequest(
                        ActivityResultContracts.PickVisualMedia.ImageOnly
                    ))
                },
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            if(imageUrl == null)
            Image(
                painter = painterResource(id = R.drawable.camera),
                contentDescription = "Ajouter une photo",
                modifier = Modifier
                    .height(50.dp)
            )
            else
                AsyncImage(model = imageUrl,
                    contentDescription ="image",
                    contentScale = androidx.compose.ui.layout.ContentScale.FillBounds,
                )

        }
    }
}