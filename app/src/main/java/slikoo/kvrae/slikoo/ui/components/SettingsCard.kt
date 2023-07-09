package slikoo.kvrae.slikoo.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import slikoo.kvrae.slikoo.R
import slikoo.kvrae.slikoo.ui.theme.ButtonsAndIcons
import slikoo.kvrae.slikoo.ui.theme.SecondaryWhiteText


@OptIn(ExperimentalMaterialApi::class, ExperimentalMaterial3Api::class)
@Composable
fun SettingCard(icon: ImageVector, title: String, subtitle: String = "Null", actionIcon: ImageVector) {
    Card(
         modifier = Modifier.padding(8.dp),
        shape = RoundedCornerShape(16.dp),
        onClick = { /*TODO*/ },
        elevation = 4.dp){
        TopAppBar(
            modifier = Modifier.fillMaxWidth().padding(4.dp)
            ,
            title = {
                Column() {
                    Text(text = title)
                    if (subtitle != "Null") Text(text = subtitle)

                }
            },
            actions = {
                if (subtitle != "Null")
                    Button(onClick = { /*TODO*/ },
                        modifier = Modifier.background(color = ButtonsAndIcons, shape = RoundedCornerShape(32.dp)
                        )
                    ) {
                        Text(text = "Voir profile", style = TextStyle(color = SecondaryWhiteText))}
                else
                    Icon(imageVector = actionIcon, contentDescription = "")
                },
            navigationIcon = {
                if (subtitle != "Null")
                    IconButton(onClick = { /*TODO*/ }) {
                    Image(
                        painter = painterResource(id = R.drawable.avatar),
                        contentDescription = "avatar"
                    )
                }
                else
                    Icon(imageVector = icon, contentDescription = "" )

            }
        )
    }
}