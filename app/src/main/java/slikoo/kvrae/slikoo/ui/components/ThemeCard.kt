package slikoo.kvrae.slikoo.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import slikoo.kvrae.slikoo.ui.theme.LightBackground
import slikoo.kvrae.slikoo.ui.theme.LightError

@Composable
fun ThemeCard(
    image: Int,
    name: String,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .padding(16.dp)
            .size(150.dp)

    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .clickable(onClick = {onClick() })
        ) {
            AsyncImage(model = image,
                contentScale = ContentScale.Crop,
                contentDescription = "Theme Image"
            )
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = LightBackground.copy(alpha = 0.2f))
                    .padding(8.dp)
            ){
                Text(
                    text = name,
                    style = androidx.compose.ui.text.TextStyle(
                        color = LightError,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    ),

                )
            }

        }
    }
}