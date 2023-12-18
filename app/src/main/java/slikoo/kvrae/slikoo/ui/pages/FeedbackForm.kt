package slikoo.kvrae.slikoo.ui.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import slikoo.kvrae.slikoo.R
import slikoo.kvrae.slikoo.ui.components.CustomButton
import slikoo.kvrae.slikoo.ui.components.CustomTextField
import slikoo.kvrae.slikoo.ui.components.FeedbackRatingBar
import slikoo.kvrae.slikoo.ui.theme.LightSecondary
import slikoo.kvrae.slikoo.viewmodels.FeedbackViewModel


@Composable
fun FeedbackForm(
        navController: NavController,
        idMeal: Int,
        idUser: Int
) {
    val viewModel : FeedbackViewModel = viewModel()

    if (idMeal != 0 && idUser != 0){
        DisposableEffect(Unit){
            viewModel.getFeedbacks()
            onDispose {

            }
        }
    }




    Column(
        modifier = Modifier
            .fillMaxSize(1f)
            .statusBarsPadding()
            .navigationBarsPadding()
            .background(color = LightSecondary)

    ) {
        EditProfileTopBar(
            navController = navController,
            title = stringResource(id = R.string.your_feeback)
        )
        Column(
            modifier = Modifier
                .fillMaxSize(1f)
                .background(color = LightSecondary)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Surface(
                modifier = Modifier
                    .size(120.dp)
                    .background(color = LightSecondary),
                shape = CircleShape,
                elevation = 8.dp

            ) {
                AsyncImage(model =  R.drawable.musique,
                    contentDescription = "Image",
                    contentScale = ContentScale.Crop,
                )
            }
            Spacer(modifier = Modifier.size(16.dp))
            Text(
                text = "Karam Mannai",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.size(32.dp))

            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = "Votre avis",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.size(8.dp))
                FeedbackRatingBar(
                    iconsSize = 24,
                    currentRating = 2,
                    onRatingChanged = {}
                )
            }
            Spacer(modifier = Modifier.size(16.dp))
            CustomTextField(
                onChange = {},
                value = "",
                label = "Feedback",
                readOnly = false
            )
            Spacer(modifier = Modifier.size(24.dp))
            if (idMeal!=0 && idUser!=0)
                CustomButton(
                    text = stringResource(id = R.string.submit),
                    onClick = {
                        viewModel
                    }
                )


        }
    }

}