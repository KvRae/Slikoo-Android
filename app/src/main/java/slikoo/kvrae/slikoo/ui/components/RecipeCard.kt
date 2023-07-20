package slikoo.kvrae.slikoo.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.LocationOn
import androidx.compose.material.icons.rounded.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import slikoo.kvrae.slikoo.R
import slikoo.kvrae.slikoo.ui.theme.SecondaryWhiteText




@OptIn(ExperimentalMaterialApi::class)
@Composable
fun RecipeCardContent() {
    Card(
        modifier = Modifier
            .padding(16.dp)
            .size(170.dp),
        shape = RoundedCornerShape(16.dp),
        elevation = 4.dp,
        onClick = { /*TODO*/ }
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Image(painter = painterResource(id = R.drawable.loginback),
                contentDescription = "",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
            Column(modifier = Modifier
                .fillMaxSize()
                .background(Color.Gray.copy(0.5f))) {

            }

            Column(modifier = Modifier.padding(4.dp)) {
                Row(horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically) {
                    Icon(imageVector = Icons.Rounded.LocationOn,
                        contentDescription = "",
                        modifier = Modifier
                            .size(20.dp)
                            .padding(start = 8.dp),
                        tint = SecondaryWhiteText
                    )
                    Text(text = "Location", modifier = Modifier.padding(start = 4.dp),
                        style = TextStyle(fontSize = 14.sp,
                            fontWeight = FontWeight.Bold
                        ),
                        color = SecondaryWhiteText
                    )

                    Spacer(modifier = Modifier.weight(1f))

                    Text(text = "4", modifier = Modifier.padding(start = 4.dp),
                        style = TextStyle(fontSize = 12.sp,
                            fontWeight = FontWeight.Bold),
                        color = SecondaryWhiteText
                    )
                    Icon(imageVector = Icons.Rounded.Person, contentDescription = ""
                        , modifier = Modifier
                            .padding(start = 2.dp)
                            .size(16.dp),
                        tint = SecondaryWhiteText
                            )
                }
                Spacer(modifier = Modifier.weight(1f))
                Row {
                    Text(text = "Lun 20 juin 14:30",
                        modifier = Modifier.padding(start = 8.dp),
                        style = TextStyle(fontSize = 10.sp,
                            fontWeight = FontWeight.Bold),
                        color = SecondaryWhiteText
                        )
                }
                Row {
                    Text(text = "Degustation de vin",
                        modifier = Modifier.padding(start = 8.dp),
                        style = TextStyle(fontSize = 16.sp,
                            fontWeight = FontWeight.Bold),
                        color = SecondaryWhiteText
                        )
                }
                Row {
                    Text(text = "Le cinema", modifier = Modifier.padding(start = 8.dp),
                        style = TextStyle(fontSize = 10.sp,
                            fontWeight = FontWeight.Bold),
                        color = SecondaryWhiteText
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Text(text = "20$", modifier = Modifier.padding(end = 8.dp),
                        style = TextStyle(fontSize = 10.sp,
                            fontWeight = FontWeight.Medium),
                        color = SecondaryWhiteText)
                }
            }
        }
        }
    }

@Preview
@Composable
fun RecipeCardPreview() {
    RecipeCardContent()
}

