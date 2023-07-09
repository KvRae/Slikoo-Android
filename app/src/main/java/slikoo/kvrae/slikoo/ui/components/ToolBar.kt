package slikoo.kvrae.slikoo.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import slikoo.kvrae.slikoo.R
import slikoo.kvrae.slikoo.ui.theme.ButtonsAndIcons
import slikoo.kvrae.slikoo.ui.theme.PrimaryBlackText


@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun CustomTopBar(title: String = "Slikoo") {
    TopAppBar(
        modifier = Modifier.fillMaxWidth()
        ,
        title = {
                if (title == "Home") {
                    Image(
                        painter = painterResource(id = R.drawable.logo2),
                        contentDescription = "logo",
                        modifier = Modifier.size(100.dp)

                    )
                }
                else {
                    Text(title, style = TextStyle(fontSize = 24.sp,
                        color = PrimaryBlackText,
                        fontWeight = FontWeight.Bold))
                }

        },
        actions = {
            if (title != "Settings") {
                IconButton(onClick = { /*TODO*/ }) {
                    Image(
                        painter = painterResource(id = R.drawable.avatar),
                        contentDescription = "avatar",
                        modifier = Modifier.clip(CircleShape)                       // clip to the circle shape
                            .border(2.dp, ButtonsAndIcons, CircleShape)
                    )
                }
            }
        }
    )
}