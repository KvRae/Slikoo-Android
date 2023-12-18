package slikoo.kvrae.slikoo.ui.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import slikoo.kvrae.slikoo.ui.components.CustomButton
import slikoo.kvrae.slikoo.ui.theme.LightGrey
import slikoo.kvrae.slikoo.ui.theme.LightPrimary
import slikoo.kvrae.slikoo.ui.theme.LightSecondary


@Composable
fun LoadingScreen(
    background: Color = LightSecondary
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = background),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            color = LightPrimary,
            strokeWidth = 4.dp,
            modifier = Modifier.size(40.dp)
        )
    }
}

@Composable
fun TextElementScreen(
    text : String,
    backgound : Color = LightSecondary
) {
    Box(
        modifier = Modifier
            .fillMaxSize(1f)
            .background(color = backgound),
        contentAlignment = Alignment.Center
    ) {
        Text(text = text)
    }
}

@Composable
fun TextWithImageScreen(
    imageVector: ImageVector,
    text: String,
    backgound: Color = LightSecondary
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = backgound),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            imageVector = imageVector,
            contentDescription = "",
            tint = LightGrey,
            modifier = Modifier.size(128.dp)
        )

        Spacer(modifier = Modifier.size(16.dp))
        Text(text = text,
            color = LightGrey,
            fontWeight = FontWeight.Medium,
        )
    }

}


@Composable
fun TextWithButtonScreen(
    text: String,
    buttonText: String,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
            .background(color = LightSecondary),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = text)
        CustomButton(text = buttonText, onClick = onClick)
    }

}






