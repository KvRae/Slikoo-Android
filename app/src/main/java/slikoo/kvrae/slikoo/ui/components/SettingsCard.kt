package slikoo.kvrae.slikoo.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import slikoo.kvrae.slikoo.ui.theme.ButtonsAndIcons
import slikoo.kvrae.slikoo.ui.theme.SecondaryWhiteText


@OptIn(ExperimentalMaterialApi::class, ExperimentalMaterial3Api::class)
@Composable
fun SettingCard(icon: ImageVector,
                title: String, subtitle: String = "Null",
                actionIcon: ImageVector,
                onClick : () -> Unit) {
    Card(
        modifier = Modifier.padding(8.dp),
        shape = RoundedCornerShape(16.dp),
        onClick = { onClick() },
        elevation = 4.dp){
        TopAppBar(
            modifier = Modifier
                .fillMaxSize(),
            title = {
                Column( modifier = Modifier.padding(8.dp)) {
                    Text(text = title,
                        style = TextStyle(color =if (subtitle != "Null") Color.White else Color.Black))
                    if (subtitle != "Null") Text(text = subtitle,
                        style = TextStyle(color = Color.White))

                }
            },
            colors = TopAppBarDefaults.largeTopAppBarColors(
                containerColor = if (subtitle != "Null") ButtonsAndIcons else Color.White,
            ),
            actions = {
                if (subtitle != "Null")
                    Button(onClick = { /*TODO*/ },
                        modifier = Modifier
                            .padding(8.dp)
                            .background(
                                color = ButtonsAndIcons,
                                shape = RoundedCornerShape(32.dp),
                            ),
                        colors = ButtonDefaults.buttonColors(
                            contentColor = SecondaryWhiteText,
                            containerColor = ButtonsAndIcons,

                        ),
                        elevation = ButtonDefaults.elevatedButtonElevation(
                            defaultElevation = 8.dp,
                            pressedElevation = 0.dp,
                            disabledElevation = 0.dp
                        )
                    ) {
                        Text(text = "Voir profile",
                            style = TextStyle(
                                color = SecondaryWhiteText)
                            )
                    }
                else
                    Icon(imageVector = actionIcon,
                        contentDescription = "",
                        modifier = Modifier.padding(8.dp))
                },
            navigationIcon = {
                if (subtitle != "Null")
                    /*Image(
                        painter = painterResource(id = R.drawable.avatar),
                        contentDescription = "avatar",
                        modifier = Modifier.size(50.dp))*/
                AsyncImage(model = "https://raw.githubusercontent.com/KvRae/Slikoo-JsonCollection/main/Assets/portrait-6054910_1280.jpg",
                    contentDescription = "profile pic",
                    onLoading = { /*TODO*/ },

                    modifier = Modifier
                        .size(50.dp)
                        .clip(RoundedCornerShape(25.dp))
                )
                else
                    Icon(imageVector = icon, contentDescription = "" ,
                        tint = ButtonsAndIcons, modifier = Modifier.padding(8.dp))

            }
        )
    }
}