package slikoo.kvrae.slikoo.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import coil.compose.AsyncImage
import slikoo.kvrae.slikoo.R
import slikoo.kvrae.slikoo.ui.theme.LightBackground
import slikoo.kvrae.slikoo.ui.theme.LightError
import slikoo.kvrae.slikoo.ui.theme.LightPrimary
import slikoo.kvrae.slikoo.ui.theme.LightPrimaryVariant
import slikoo.kvrae.slikoo.ui.theme.LightSecondary
import slikoo.kvrae.slikoo.ui.theme.LightSurface
import slikoo.kvrae.slikoo.viewmodels.FeedbackViewModel


@Composable
fun CustomAlertDialog(
    title: String = stringResource(R.string.title),
    message: String = stringResource(R.string.message),
    dismissText: String = "",
    confirmText: String = stringResource(R.string.yes),
    showDialog: Boolean = true,
    onDismiss: () -> Unit = {},
    onConfirm: () -> Unit = {}
) {
    if (showDialog)

        AlertDialog(
            onDismissRequest = { onDismiss() },
            properties = DialogProperties(
                dismissOnBackPress = false,
                dismissOnClickOutside = false,


                ),
            title = {
                Text(
                    text = title,
                    color = LightBackground,
                    fontWeight = FontWeight.Bold,

                    )
            },

            text = { Text(text = message) },
            confirmButton = {
                Button(
                    onClick = { onConfirm() },
                    shape = RoundedCornerShape(16.dp),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = LightPrimary,
                        contentColor = LightError
                    )
                ) {
                    Text(text = confirmText)
                }
            },
            dismissButton = {
                if (dismissText.isNotEmpty()) TextButton(onClick = { onDismiss() }) {
                    Text(
                        text = dismissText,
                        color = LightSurface,
                    )
                }
            },
            backgroundColor = LightError,
            contentColor = LightBackground,
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier.border(1.dp, LightBackground.copy(alpha = 0.1f),
             RoundedCornerShape(16.dp))
        )


}

@Composable
fun CustomAlertDialogWithContent(
    title: String = stringResource(R.string.title),
    content : @Composable () -> Unit,
    confirmText: String = stringResource(R.string.yes),
    dismissText: String = "",
    onDismiss: () -> Unit = {},
    onConfirm: () -> Unit = {}

){
    AlertDialog(
        properties = DialogProperties(
            dismissOnBackPress = false,
            dismissOnClickOutside = false,

        ),

        backgroundColor = LightSecondary,
        onDismissRequest = {},

        text = {
            content()
        },
        title = {  },
        shape = RoundedCornerShape(16.dp),
        confirmButton = {
            Button(
                onClick = { onConfirm() },
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = LightPrimary,
                    contentColor = LightError
                )
            ) {
                Text(text = confirmText)
            }
        },
        dismissButton = {
            if (dismissText.isNotEmpty())
                TextButton(onClick = { onDismiss() }) {
                Text(
                    text = dismissText,
                    color = LightSurface,
                )
            }
        },
    )
}


@Composable
fun LoadingDialog() {
    Dialog(
        onDismissRequest = { },
        DialogProperties(
            dismissOnBackPress = false,
            dismissOnClickOutside = true
        )
    ) {
        Box(
            contentAlignment= Alignment.Center,
            modifier = Modifier
                .size(100.dp)
                .background(LightError, shape = RoundedCornerShape(8.dp))
        ) {
            CircularProgressIndicator(
                color = LightPrimary,
                backgroundColor = LightSecondary,
            )
        }
    }
}



@Composable
fun MaterialAlertBox(
    icon : Int = R.drawable.celebration,
    title: String = "",
    message: String = "",
    onClick : () -> Unit = {}
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(LightBackground.copy(alpha = 0.5f))
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .background(LightSecondary, shape = RoundedCornerShape(16.dp))
                .padding(16.dp),
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(LightPrimaryVariant)
                    .clip(shape = RoundedCornerShape(32.dp))
                    .padding(8.dp),
                contentAlignment = Alignment.Center
            ) {
                IconButton(
                    onClick = { },
                    Modifier
                        .clip(shape = CircleShape)
                        .background(LightPrimary)
                        .padding(8.dp)
                ) {
                    Icon(
                        painter = painterResource(id = icon),
                        contentDescription = "",
                        tint = LightSecondary,
                        modifier = Modifier.size(40.dp)
                    )
                }
            }
            Spacer(modifier = Modifier.size(16.dp))
            Text(
                text = title,
                color = LightBackground,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                modifier = Modifier.padding(8.dp)
            )
            Text(
                text = message,
                color = LightBackground,
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal,
                modifier = Modifier.padding(8.dp)
            )
            Spacer(modifier = Modifier.size(16.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(
                    modifier = Modifier.fillMaxWidth(1f),
                    onClick = { onClick() },
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = LightPrimary,
                        contentColor = LightError
                    ),
                    content = { Text(text = stringResource(R.string.proceed)) }
                )
            }
        }
    }
}


@Composable
fun FeedbackAlertForm(
    viewModel: FeedbackViewModel,
    idMeal: Int,
    idUser: Int,
    onDismiss: () -> Unit = {}
) {
    Dialog(
        properties = DialogProperties(
            dismissOnBackPress = true,
            dismissOnClickOutside = true,
        ),
        onDismissRequest = {
            onDismiss()
        },
        content = {
            FeedbackContent(
                viewModel = viewModel,
                idMeal = idMeal,
                idUser = idUser
            )
        },
    )
}

@Composable
fun FeedbackContent(
    viewModel: FeedbackViewModel,
    idMeal: Int,
    idUser: Int
) {
    Surface(
        modifier = Modifier
            .background(color = LightSecondary),
        shape = RoundedCornerShape(16.dp),
        elevation = 4.dp,
    ) {
        Column(
            modifier = Modifier
                .background(color = LightSecondary)
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Surface(
                modifier = Modifier
                    .size(60.dp)
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
            Spacer(modifier = Modifier.size(8.dp))
            Text(
                text = viewModel.feedback.recipient.nom + " " + viewModel.feedback.recipient.prenom,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.size(16.dp))

            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = stringResource(R.string.your_feedback),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.size(4.dp))
                FeedbackRatingBar(
                    iconsSize = 24,
                    currentRating = viewModel.feedback.rate,
                    onRatingChanged = {
                        if (!viewModel.verifyFeedbackSubmitted(idUser, idMeal))
                            viewModel.feedback.rate = it
                    }
                )
            }
            Spacer(modifier = Modifier.size(8.dp))
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
            Spacer(modifier = Modifier.size(12.dp))
            if (idMeal != 0 && idUser != 0 &&
                !viewModel.verifyFeedbackSubmitted(idUser, idMeal)
            )
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



