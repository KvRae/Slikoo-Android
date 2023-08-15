package slikoo.kvrae.slikoo.ui.components

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import slikoo.kvrae.slikoo.R
import slikoo.kvrae.slikoo.ui.theme.LightError
import slikoo.kvrae.slikoo.ui.theme.LightPrimary


@Preview
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
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(LightError)
            .height(150.dp) // Adjust the height value as needed
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .clickable {
                    launcher.launch(
                        PickVisualMediaRequest(
                            ActivityResultContracts.PickVisualMedia.ImageOnly
                        )
                    )
                },
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            if(imageUrl == null)
            Icon(
                painter = painterResource(id = R.drawable.camera),
                contentDescription = "Ajouter une photo",
                tint = Color.Gray,
                modifier = Modifier
                    .height(50.dp)
            )
            else
                BoxWithConstraints {
                    AsyncImage(model = imageUrl,
                        contentDescription = "picture",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxSize()
                            .clickable {
                                launcher.launch(
                                    PickVisualMediaRequest(
                                        ActivityResultContracts.PickVisualMedia.ImageOnly
                                    )
                                )
                            }
                    )
                    Icon(painter = painterResource(id = R.drawable.camera)
                        , contentDescription = "Ajouter une photo",
                        tint = LightPrimary.copy(alpha = 0.8f),
                        modifier = Modifier.align(Alignment.BottomEnd)
                            .padding(8.dp).size(30.dp)
                    )
                }

        }
    }
}