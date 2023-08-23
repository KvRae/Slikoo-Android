package slikoo.kvrae.slikoo.ui.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import slikoo.kvrae.slikoo.R
import slikoo.kvrae.slikoo.ui.theme.LightBackground
import slikoo.kvrae.slikoo.ui.theme.LightError
import slikoo.kvrae.slikoo.ui.theme.LightPrimary
import slikoo.kvrae.slikoo.ui.theme.LightSecondary
import slikoo.kvrae.slikoo.ui.theme.LightSurface


@Composable
fun CustomAlertDialog(
    title: String = stringResource(R.string.title),
    message: String = stringResource(R.string.message),
    dismissText : String = stringResource(R.string.no),
    confirmText : String = stringResource(R.string.yes),
    showDialog: Boolean = true,
    onDismiss: () -> Unit = {},
    onConfirm: () -> Unit = {}
) {
    if (showDialog)
    AlertDialog(
        onDismissRequest = { onDismiss() },
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
            TextButton(onClick = { onDismiss() }) {
                Text(
                    text = dismissText,
                    color = LightSurface,

                )
            }
        },
        backgroundColor = LightSecondary,
        contentColor = LightBackground,
        shape = RoundedCornerShape(16.dp)
    )
    else
        return

}

