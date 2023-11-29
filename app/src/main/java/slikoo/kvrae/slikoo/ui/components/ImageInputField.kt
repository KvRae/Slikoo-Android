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
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import slikoo.kvrae.slikoo.R
import slikoo.kvrae.slikoo.ui.theme.LightError
import slikoo.kvrae.slikoo.ui.theme.LightPrimary


@Preview
@Composable
fun ImagePickerField( imageUrl : Uri? = null ,
                      image : String = "",
                      onImageSelected : (Uri?) -> Unit = {}){

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = { onImageSelected(it) },)
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
                contentDescription = stringResource(R.string.add_image),
                tint = Color.Gray,
                modifier = Modifier.height(50.dp)
            )
            else
                BoxWithConstraints {
                    AsyncImage(model = image,
                        contentDescription = stringResource(R.string.picture),
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
                        , contentDescription = stringResource(R.string.add_image),
                        tint = LightPrimary.copy(alpha = 0.8f),
                        modifier = Modifier
                            .align(Alignment.BottomEnd)
                            .padding(8.dp)
                            .size(30.dp)
                    )
                }

        }
    }
}

@Composable
fun ProfileImagePicker1(
    imageUri: Uri? = null,
    image: String = "",
    onImageSelected: (Uri) -> Unit = {}
) {
    // Use rememberSaveable for persistence across process death
    var imageUrl by rememberSaveable {
        mutableStateOf(imageUri)
    }
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = { uri ->
            imageUrl = uri
            onImageSelected(uri!!)
        }
    )

    Box(
        modifier = Modifier
            .padding(8.dp)
            .size(200.dp),
        contentAlignment = Alignment.Center,
    ) {
        IconButton(
            onClick = {
                launcher.launch(
                    PickVisualMediaRequest(
                        ActivityResultContracts.PickVisualMedia.ImageOnly
                    )
                )
            },
            modifier = Modifier
                .padding(8.dp)
                .fillMaxSize()
                .clip(shape = RoundedCornerShape(50))
                .background(Color.White),
            colors = IconButtonDefaults.filledIconButtonColors(
                containerColor = Color.Transparent,
                contentColor = LightPrimary,
            )
        ) {
            if (imageUrl == null) {
                // Placeholder content when no image is selected
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.camera),
                    contentDescription = "",
                    tint = Color.Gray,
                    modifier = Modifier.size(50.dp)
                )
            } else {
                // AsyncImage implementation (replace with your actual AsyncImage code)
                AsyncImage(
                    model = image,
                    contentDescription = "picture",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(shape = RoundedCornerShape(50))
                        .clickable {
                            launcher.launch(
                                PickVisualMediaRequest(
                                    ActivityResultContracts.PickVisualMedia.ImageOnly
                                )
                            )
                        },
                )
                // Icon for adding a photo (replace with your actual Icon code)
                Icon(
                    painter = painterResource(id = R.drawable.camera),
                    contentDescription = "Ajouter une photo",
                    tint = LightPrimary.copy(alpha = 0.8f),
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(32.dp)
                        .size(25.dp)
                )
            }
        }
    }
}


@Composable
fun ProfileImagePicker(
    imageUri: Uri? = null,
    image: String = "",
    onImageSelected: (Uri) -> Unit = {}
) {
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = { uri ->
            onImageSelected(uri ?: Uri.EMPTY)
        })

    Box(
        modifier = Modifier
            .padding(8.dp)
            .size(200.dp),
        contentAlignment = Alignment.Center,
    ) {
        IconButton(
            onClick = {
                launcher.launch(
                    PickVisualMediaRequest(
                        ActivityResultContracts.PickVisualMedia.ImageOnly
                    )
                )
            },
            modifier = Modifier
                .padding(8.dp)
                .fillMaxSize()
                .clip(shape = RoundedCornerShape(50))
                .background(Color.White),
            colors = IconButtonDefaults.filledIconButtonColors(
                containerColor = Color.Transparent,
                contentColor = LightPrimary
            )
        ) {
            if (imageUri == null) {
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.camera),
                    contentDescription = null,
                    tint = Color.Gray,
                    modifier = Modifier.size(50.dp)
                )
            } else {
                BoxWithConstraints {
                    AsyncImage(
                        model = image,
                        contentDescription = "picture",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxSize()
                            .clip(shape = RoundedCornerShape(50)),
                    )
                    Icon(
                        painter = painterResource(id = R.drawable.camera),
                        contentDescription = "Ajouter une photo",
                        tint = LightPrimary.copy(alpha = 0.8f),
                        modifier = Modifier
                            .align(Alignment.BottomEnd)
                            .padding(32.dp)
                            .size(25.dp)
                    )
                }
            }
        }
    }
}