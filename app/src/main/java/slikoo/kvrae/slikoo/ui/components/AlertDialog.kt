package slikoo.kvrae.slikoo.ui.components

import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue


@Composable
fun CustomAlertDialog() {
    var dialogState by remember { mutableStateOf(false) }
    if (dialogState)
    AlertDialog(
        onDismissRequest = { dialogState = false },
        title = {
                 Text(text = "This is a title")},
        text = {
            Text(text = "This is a text")
        },
        confirmButton = {
                        Button(onClick = { dialogState = false }) {
                Text(text = "Confirm")
                        }
        },
        dismissButton = {
                         Button(onClick = { dialogState = false }) {
                Text(text = "Dismiss") }
                        })
}