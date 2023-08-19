package slikoo.kvrae.slikoo.ui.fragments.signup

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
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
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import slikoo.kvrae.slikoo.R
import slikoo.kvrae.slikoo.ui.components.CustomButton
import slikoo.kvrae.slikoo.ui.components.CustomSlider
import slikoo.kvrae.slikoo.ui.theme.LightPrimary
import slikoo.kvrae.slikoo.ui.theme.LightSurface
import slikoo.kvrae.slikoo.utils.AppScreenNavigator
import slikoo.kvrae.slikoo.utils.SignUpNavigator


@Composable
fun ProfilePictureSection(onChange : (String) -> Unit, navController: NavController) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = stringResource(R.string.profile_pic), style = MaterialTheme.typography.h4)
            Spacer(modifier = Modifier.size(8.dp))
            CustomSlider(maxSlide = 4, currentSlide = 4)
            ProfileImagePicker()
            CustomButton(text = stringResource(R.string.finish),
                onClick = {
                    navController.navigate(AppScreenNavigator.SignInAppScreen.route)
                }
            )
            TextButton(onClick = { onChange(SignUpNavigator.SignUpIDCFragment.route) }) {
                Text(text = "Precedent", color = LightSurface)
            }
        }
    }


@Composable
fun ProfileImagePicker(imageUri: Uri? = null,
                       onImageSelected: (Uri) -> Unit = {}) {
    var imageUrl by remember {
        mutableStateOf<Uri?>(imageUri)
    }
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = { uri ->
            imageUrl = uri
        })

    Box(modifier = Modifier
        .padding(8.dp)
        .size(200.dp),
        contentAlignment = Alignment.Center,
        ) {
        IconButton(onClick = {
            launcher.launch(
                PickVisualMediaRequest(
                    ActivityResultContracts.PickVisualMedia.ImageOnly
                ))
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
            if (imageUrl == null)
            Icon(imageVector = ImageVector.vectorResource(id = R.drawable.camera),
                contentDescription = "",
                tint = Color.Gray,
                modifier = Modifier.size(50.dp)
            )
            else
                BoxWithConstraints {
                    AsyncImage(model = imageUrl,
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
                    Icon(painter = painterResource(id = R.drawable.camera)
                        , contentDescription = "Ajouter une photo",
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
