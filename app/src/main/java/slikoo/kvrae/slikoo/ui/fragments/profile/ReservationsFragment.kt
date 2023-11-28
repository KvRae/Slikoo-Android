package slikoo.kvrae.slikoo.ui.fragments.profile

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material.icons.rounded.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import slikoo.kvrae.slikoo.R

@Composable
fun ReservationFragment() {
    LazyColumn() {
        items(1) {
            ReservationCard(
                title = "L'organisateur",
                subtitle = "Hamza Ben Mamoudd",
                offreText = "Demande Acceptée!",
                description = "Je suis Interesé par votre offre",
                dissmissText = stringResource(R.string.cancel_pay),
                onDismiss = { /*TODO*/ },
                confirmText = stringResource(R.string.pay),
                onConfirm = { /*TODO*/ }
            )
        }
    }
}


@Composable
fun ReservationCard(
    title: String = "",
    subtitle: String = "",
    offreText : String = "",
    description : String = "",
    dissmissText : String,
    onDismiss : () -> Unit,
    confirmText : String,
    onConfirm : () -> Unit
) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .height(210.dp)
            .fillMaxWidth(),
        elevation = 8.dp,
        shape = RoundedCornerShape(16.dp)
    ) {
        Row {
            AsyncImage(
                model = R.drawable.lyon,
                contentDescription =  "image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth(0.3f)
                    .fillMaxHeight(1f)
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth(1f)
                    .padding(8.dp)
                    .fillMaxHeight(1f),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(1f),
                    verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
                ) {
                    Text(text = title,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
//                    Spacer(modifier = Modifier.weight(1f))
//
//                    Text(text = stringResource(id = R.string.see_profile),
//                        fontSize = 12.sp,
//                        color = Color(0xFFFFC107),
//                        fontWeight = FontWeight.Bold)

                }

                Text(text = subtitle,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium
                )
                Text(text = offreText,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color(0xFF00FF00)
                )

                Text(text = "Motif",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium
                )
                Text(text = description,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium
                )
                Divider(Modifier.padding(8.dp), Color(0xFF818181), thickness = 0.5.dp)
                Row {
                    OutlinedButton(
                        onClick = { /*TODO*/ },
                        shape = RoundedCornerShape(16.dp),
                        border = BorderStroke(1.dp, Color(0xFFFF0000)  ),
                        colors = ButtonDefaults.outlinedButtonColors(
                            contentColor = Color(0xFFFF0000),
                        )
                    ) {
                        Icon(imageVector = Icons.Rounded.Close, contentDescription = "")
                        Text(
                            text = dissmissText,
                            style = TextStyle(
                                fontWeight = FontWeight.Bold,
                                fontSize = 12.sp
                            )
                        )
                    }
                    Spacer(modifier = Modifier.weight(1f))
                    OutlinedButton(
                        onClick = { /*TODO*/ },
                        shape = RoundedCornerShape(16.dp),
                        border = BorderStroke(1.dp, Color(0xFF00FF00)  ),
                        colors = ButtonDefaults.outlinedButtonColors(
                            contentColor = Color(0xFF00FF00),
                        )
                    ) {
                        Icon(imageVector = Icons.Rounded.Check, contentDescription = "" )
                        Text(
                            text = confirmText,
                            style = TextStyle(
                                fontWeight = FontWeight.Bold,
                                fontSize = 12.sp
                            )
                        )
                    }
                }

            }

        }
    }

}