package slikoo.kvrae.slikoo.ui.pages

import android.util.Log
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import slikoo.kvrae.slikoo.R
import slikoo.kvrae.slikoo.data.datasources.entities.Feedback
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


    val viewModel: FeedbackViewModel = viewModel()
    if (idMeal != 0 && idUser != 0) {
        DisposableEffect(Unit) {
            Log.d("FeedbackForm idMeal: ", "$idMeal")
            Log.d("FeedbackForm idUser: ", "$idUser")
            Log.d("FeedbackForm readOnly: ", "${viewModel.verifyFeedbackSubmitted(
                idUser,
                idMeal
            )}")
            viewModel.getFeedbackByUserId(idUser)?: Feedback()
            onDispose {}
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
                AsyncImage(
                    model = (viewModel.feedback.recipient.avatarUrl.plus(viewModel.feedback.recipient.avatar)),
                    contentDescription = "Image",
                    contentScale = ContentScale.Crop,
                )
            }
            Spacer(modifier = Modifier.size(16.dp))
            Text(
                text = viewModel.feedback.recipient.nom + " " + viewModel.feedback.recipient.prenom,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.size(32.dp))

            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = stringResource(R.string.your_feedback),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.size(8.dp))
                FeedbackRatingBar(
                    iconsSize = 24,
                    currentRating = viewModel.feedback.rate,
                    onRatingChanged = {
                        if (!viewModel.verifyFeedbackSubmitted(idUser, idMeal))
                            viewModel.feedback.rate = it
                    }
                )
            }
            Spacer(modifier = Modifier.size(16.dp))
            CustomTextField(
                onChange = {
                    if (!viewModel.verifyFeedbackSubmitted(idUser, idMeal))
                        viewModel.feedback = viewModel.feedback.copy(comment = it)
                },
                leadingIcon = ImageVector.vectorResource(id = R.drawable.feedback),
                value = viewModel.feedback.comment,
                label = stringResource(R.string.comment),
                readOnly = viewModel.verifyFeedbackSubmitted(idUser, idMeal)
            )
            Spacer(modifier = Modifier.size(24.dp))
            if (idMeal != 0 && idUser != 0 &&
                !viewModel.verifyFeedbackSubmitted(idUser, idMeal))
                CustomButton(
                    text = stringResource(id = R.string.submit),
                    onClick = {
                        viewModel.addFeedback(
                            idReciver = idUser,
                            idMeal = idMeal,
                            comment = viewModel.feedback.comment,
                            rate = viewModel.feedback.rate
                        )
                    }
                )
        }
    }

}