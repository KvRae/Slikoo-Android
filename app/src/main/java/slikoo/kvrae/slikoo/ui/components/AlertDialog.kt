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
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
    title: String = "",
    message: String = "",
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
        modifier = Modifier
            .border(1.dp, LightBackground.copy(alpha = 0.1f),
            RoundedCornerShape(16.dp))
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
                .background(color = LightError, shape = RoundedCornerShape(8.dp))
                .border(1.dp, LightBackground.copy(alpha = 0.1f), RoundedCornerShape(8.dp))
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







