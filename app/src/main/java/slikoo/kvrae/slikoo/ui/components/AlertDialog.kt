package slikoo.kvrae.slikoo.ui.components

import androidx.compose.foundation.background
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import slikoo.kvrae.slikoo.R
import slikoo.kvrae.slikoo.ui.theme.LightBackground
import slikoo.kvrae.slikoo.ui.theme.LightError
import slikoo.kvrae.slikoo.ui.theme.LightPrimary
import slikoo.kvrae.slikoo.ui.theme.LightPrimaryVariant
import slikoo.kvrae.slikoo.ui.theme.LightSecondary
import slikoo.kvrae.slikoo.ui.theme.LightSurface


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
            shape = RoundedCornerShape(16.dp)
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


@Preview
@Composable
fun MaterialAlertBox() {
    Column(
        modifier = Modifier
            .background(LightSecondary)
            .padding(16.dp),
    ){
        Box(modifier = Modifier
            .fillMaxWidth()
            .background(LightPrimaryVariant)
            .clip(shape = RoundedCornerShape(32.dp))
            .padding(8.dp),
            contentAlignment = Alignment.Center
        ) {
            IconButton(
                onClick = { /*TODO*/ },
                Modifier
                    .clip(shape = CircleShape)
                    .background(LightPrimary)
                    .padding(8.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.home_icon),
                    contentDescription = "",
                    tint = LightSecondary,
                    modifier = Modifier.size(30.dp)
                )
            }
        }
        Text(text= "Delete this item ?")
        Text(text = "This action cannot be undone")

        Row(modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
            ) {
            Button(
                modifier = Modifier.fillMaxWidth(0.5f),
                onClick = { /*TODO*/ },
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = LightPrimaryVariant,
                    contentColor = LightBackground
                ),
                content = { Text(text = "Cancel") }
            )
            Spacer(modifier = Modifier.size(16.dp))
            Button(
                modifier = Modifier.fillMaxWidth(1f),
                onClick = { /*TODO*/ },
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = LightPrimary,
                    contentColor = LightError
                ),
                content = { Text(text = "Delete") }
            )
        }
    }
}


@Composable
fun FeedbackAlertForm(
    showDialog: Boolean = true,
    onDismiss: () -> Unit = {},
    onConfirm: () -> Unit = {}
) {
    var rating: Int = 0
    AlertDialog(

        buttons = {
        },
        onDismissRequest = {
            onDismiss(
            )
        },
         text = {FeedbackContent()},
    )

}

@Composable
fun FeedbackContent(
    rating :Int = 0,
    onChange: (Int)->Unit = {}
) {
    Column(
        modifier = Modifier
            .background(LightSecondary)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = stringResource(id = R.string.feedback_form_title))
        Spacer(modifier = Modifier.size(16.dp))
        Text(text = stringResource(id = R.string.feedback_form_message))
        RatingBar(
            onRatingChanged =
            { onChange(it)},
            currentRating = rating
        )
    }

}



