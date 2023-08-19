package slikoo.kvrae.slikoo.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import slikoo.kvrae.slikoo.R
import slikoo.kvrae.slikoo.data.models.Area
import slikoo.kvrae.slikoo.ui.theme.LightPrimaryVariant
import slikoo.kvrae.slikoo.utils.AppScreenNavigator
import java.text.SimpleDateFormat



@OptIn(ExperimentalMaterialApi::class)
@Composable
fun RecipeCardContent( area : Area, navController: NavController) {
    val dateFormat = SimpleDateFormat("dd/MM/yyyy HH:mm",
        java.util.Locale.getDefault())
    val date = dateFormat.format(area.date)

    Card(
        modifier = Modifier
            .padding(8.dp)
            .size(170.dp),
        shape = RoundedCornerShape(16.dp),
        elevation = 4.dp,
        onClick = { navController.navigate(AppScreenNavigator.EventDetailsAppScreen.route) }
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Image(painter = painterResource(id = R.drawable.login),
                contentDescription = "",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
            Column(modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(0.4f))) {

            }

            Column(modifier = Modifier.padding(8.dp)) {
                Row(horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically) {
                    Icon(imageVector = Icons.Rounded.LocationOn,
                        contentDescription = "",
                        modifier = Modifier
                            .size(20.dp)
                            .padding(start = 8.dp),
                        tint = LightPrimaryVariant
                    )
                    Text(text = area.name, modifier = Modifier.padding(start = 4.dp),
                        style = TextStyle(fontSize = 14.sp,
                            fontWeight = FontWeight.Bold
                        ),
                        color = LightPrimaryVariant
                    )

                    Spacer(modifier = Modifier.weight(1f))

                    Text(text = area.nbPerson.toString(), modifier = Modifier.padding(start = 4.dp),
                        style = TextStyle(fontSize = 12.sp,
                            fontWeight = FontWeight.Bold),
                        color = LightPrimaryVariant
                    )
                    Icon(imageVector = Icons.Rounded.Person, contentDescription = ""
                        , modifier = Modifier
                            .padding(start = 2.dp)
                            .size(16.dp),
                        tint = LightPrimaryVariant
                            )
                }
                Spacer(modifier = Modifier.weight(1f))
                Row {
                    Text(text = date,
                        modifier = Modifier.padding(start = 8.dp),
                        style = TextStyle(fontSize = 10.sp,
                            fontWeight = FontWeight.Bold),
                        color = LightPrimaryVariant
                        )
                }
                Row {
                    Text(text = area.description,
                        modifier = Modifier.padding(start = 8.dp),
                        style = TextStyle(fontSize = 16.sp,
                            fontWeight = FontWeight.Bold),
                        color = LightPrimaryVariant,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                        )
                }
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp)) {
                    Text(text = area.place, modifier = Modifier.padding(start = 8.dp),
                        style = TextStyle(fontSize = 10.sp,
                            fontWeight = FontWeight.Bold),
                        color = LightPrimaryVariant
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Text(text = "${area.price} $", modifier = Modifier.padding(end = 8.dp),
                        style = TextStyle(fontSize = 12.sp,
                            fontWeight = FontWeight.Medium),
                        color = LightPrimaryVariant)
                }
            }
        }
    }
}



