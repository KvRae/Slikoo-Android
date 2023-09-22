package slikoo.kvrae.slikoo.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import slikoo.kvrae.slikoo.R
import slikoo.kvrae.slikoo.ui.theme.LightPrimary
import slikoo.kvrae.slikoo.ui.theme.LightPrimaryVariant


@OptIn(ExperimentalMaterialApi::class, ExperimentalMaterial3Api::class)
@Composable
fun SettingCard(icon: ImageVector,
                modifier: Modifier = Modifier,
                title: String, subtitle: String = "Null",
                avatar: String = "",
                actionIcon: ImageVector,
                onClick : () -> Unit) {
    Card(
        modifier = modifier.padding(8.dp),
        shape = RoundedCornerShape(16.dp),
        onClick = { onClick() },
        elevation = 4.dp){
        TopAppBar(
            title = {
                Column( modifier = Modifier
                    .padding(16.dp)
                    .fillMaxHeight()) {
                    Text(text = title,
                        style = TextStyle(color =if (subtitle != "Null") Color.White else Color.Black,
                            fontSize = TextUnit(14f, TextUnitType.Sp),
                            fontWeight = FontWeight.Medium))
                    if (subtitle != "Null") Text(text = subtitle,
                        style = TextStyle(color = Color.White))

                }
            },
            colors = TopAppBarDefaults.largeTopAppBarColors(
                containerColor = if (subtitle != "Null") LightPrimary else Color.White,
            ),
            actions = {
                if (subtitle != "Null")
                    Button(onClick = { onClick()},
                        modifier = Modifier
                            .padding(8.dp)
                            .background(
                                color = LightPrimary,
                                shape = RoundedCornerShape(32.dp),
                            ),
                        colors = ButtonDefaults.buttonColors(
                            contentColor = LightPrimaryVariant,
                            containerColor = LightPrimary,

                        ),
                        elevation = ButtonDefaults.elevatedButtonElevation(
                            defaultElevation = 8.dp,
                            pressedElevation = 0.dp,
                            disabledElevation = 0.dp
                        )
                    ) {
                        Text(text = stringResource(R.string.see_profile),
                            style = TextStyle(
                                color = LightPrimaryVariant)
                            )
                    }
                else
                    Icon(imageVector = actionIcon,
                        contentDescription = "",
                        modifier = Modifier.padding(8.dp))
                },
            navigationIcon = {
                if (subtitle != "Null")
                AsyncImage(model = avatar.ifEmpty { R.drawable.avatar },
                    contentDescription = stringResource(R.string.profile_pic),
                    onLoading = { },
                    modifier = Modifier
                        .size(50.dp)
                        .clip(shape = CircleShape)
                )
                else
                    Icon(imageVector = icon, contentDescription = "" ,
                        tint = LightPrimary, modifier = Modifier.padding(8.dp))

            }
        )
    }
}