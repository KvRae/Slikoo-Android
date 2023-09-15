package slikoo.kvrae.slikoo.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.unit.sp
import slikoo.kvrae.slikoo.ui.theme.LightPrimary
import slikoo.kvrae.slikoo.ui.theme.LightPrimaryVariant


@Composable
fun CustomButton(text : String, onClick: () -> Unit) {
    Button(
        onClick = { onClick()},
        modifier = Modifier.fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(
            containerColor = LightPrimary,
            contentColor = LightPrimaryVariant)
    ) {
        Text(text = text,
            color = LightPrimaryVariant,
            fontWeight = Bold,
            fontSize = 16.sp,
        )
    }
}