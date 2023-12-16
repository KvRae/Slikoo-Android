package slikoo.kvrae.slikoo.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import slikoo.kvrae.slikoo.R
import slikoo.kvrae.slikoo.ui.theme.LightBackground
import slikoo.kvrae.slikoo.ui.theme.LightError
import slikoo.kvrae.slikoo.ui.theme.LightPrimary
import slikoo.kvrae.slikoo.ui.theme.LightPrimaryVariant


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingCard(
    icon: ImageVector,
    modifier: Modifier = Modifier,
    title: String,
    subtitle: String = "",
    avatar: String = "",
    actionIcon: ImageVector,
    onClick: () -> Unit
) {
    Card(
        modifier = modifier.padding(8.dp),
        shape = RoundedCornerShape(16.dp),
        elevation = 4.dp
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .clickable { onClick() }
        ) {
            TopAppBar(
                title = {
                    Column(
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxHeight()
                    ) {
                        Text(
                            text = title,
                            overflow = TextOverflow.Ellipsis,
                            maxLines = 1,
                            style = TextStyle(
                                color = if (subtitle.isNotEmpty()) LightError else LightBackground,
                                fontSize = TextUnit(14f, TextUnitType.Sp),
                                fontWeight = FontWeight.Medium,

                            )
                        )
                        if (subtitle.isNotEmpty()) Text(
                            text = subtitle,
                            style = TextStyle(color = LightError)
                        )

                    }
                },
                colors = TopAppBarDefaults.largeTopAppBarColors(
                    containerColor = if (subtitle.isNotEmpty()) LightPrimary else Color.White,
                ),
                actions = {
                    if (subtitle.isNotEmpty())
                        Button(
                            onClick = { onClick() },
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
                            Text(
                                text = stringResource(R.string.see_profile),
                                overflow = TextOverflow.Ellipsis,
                                maxLines = 1,
                                style = TextStyle(
                                    color = LightPrimaryVariant
                                )
                            )
                        }
                    else
                        Icon(
                            imageVector = actionIcon,
                            contentDescription = "",
                            modifier = Modifier.padding(8.dp)
                        )
                },
                navigationIcon = {
                    if (subtitle.isNotEmpty())
                        AsyncImage(
                            model = avatar.ifEmpty { R.drawable.avatar },
                            contentDescription = stringResource(R.string.profile_pic),
                            modifier = Modifier
                                .size(50.dp)
                                .clip(shape = CircleShape),
                            contentScale = ContentScale.Crop
                        )
                    else
                        Icon(
                            imageVector = icon, contentDescription = "",
                            tint = LightPrimary, modifier = Modifier.padding(8.dp)
                        )

                }
            )
        }
    }
}
