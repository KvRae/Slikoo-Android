package slikoo.kvrae.slikoo.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import slikoo.kvrae.slikoo.ui.theme.ButtonsAndIcons
import slikoo.kvrae.slikoo.ui.theme.SecondaryWhiteText


@Composable
fun CustomButton(text : String, onClick: () -> Unit) {
    Button(
        onClick = { onClick()},
        modifier = Modifier.fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(
            containerColor = ButtonsAndIcons,
            contentColor = SecondaryWhiteText)
    ) {
        Text(text = text)
    }
}