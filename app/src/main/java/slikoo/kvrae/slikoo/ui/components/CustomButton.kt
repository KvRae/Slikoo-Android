package slikoo.kvrae.slikoo.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import slikoo.kvrae.slikoo.ui.theme.ButtonsAndIcons




@Composable
fun CustomButton(text : String, onClick: () -> Unit) {
    Button(
        onClick = { onClick()},
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .clip(shape = RoundedCornerShape(1))
            .padding(8.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = ButtonsAndIcons,
            contentColor = androidx.compose.ui.graphics.Color.White
        )
    ) {
        Text(text = text)
    }
}