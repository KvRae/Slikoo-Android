package slikoo.kvrae.slikoo.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import slikoo.kvrae.slikoo.ui.theme.LightSurface


@Composable
fun CustomAlertDialog(state : Boolean = false,
                      title: String = "title",
                      text: String = "Body",
                      confirmText: String = "Confirm",
                      dismissText: String = "Dismiss",
                      onConfirm: () -> Unit = {}) {

    val dialogState = remember { mutableStateOf(state) }

    if (dialogState.value){
    AlertDialog(
        onDismissRequest = { dialogState.value = false },
        title = {
                Row(Modifier.fillMaxWidth(),
                    verticalAlignment = androidx.compose.ui.Alignment.CenterVertically,
                ) {
                    Text(text = title,
                        style = TextStyle(
                            color = androidx.compose.ui.graphics.Color.Black,
                            fontSize = 20.sp,
                            fontWeight = androidx.compose.ui.text.font.FontWeight.Bold))
                }
                 },
        text = {
            Text(text = text)
        },
        shape = RoundedCornerShape(16.dp),
        confirmButton = {
                        Button(onClick = {
                            onConfirm();
                            dialogState.value = false
                        }, shape = RoundedCornerShape(16.dp),
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = MaterialTheme.colors.primary,
                                contentColor = MaterialTheme.colors.primaryVariant
                            )

                        ) {
                            Text(text = confirmText, style = TextStyle(
                                color = MaterialTheme.colors.primaryVariant,
                                fontSize = 16.sp,
                                fontWeight = androidx.compose.ui.text.font.FontWeight.Bold))
                        }
        },
        dismissButton = {
            TextButton(onClick = {
                dialogState.value = false
                                 },
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = androidx.compose.ui.graphics.Color.Transparent,
                    contentColor = LightSurface)
            ) {
                Text(text = dismissText)
            }

        }
    )}
}


@Preview(showBackground = true)
@Composable
fun AlertDialogPreview() {
    CustomAlertDialog(state = true)
}