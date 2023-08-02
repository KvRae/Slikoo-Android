package slikoo.kvrae.slikoo.ui.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import slikoo.kvrae.slikoo.R
import slikoo.kvrae.slikoo.ui.theme.ScreenBackground


@Preview
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun AdvancedEditProfileScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(ScreenBackground)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(ScreenBackground)
        ) {
            Image(
                painter = painterResource(id = R.drawable.loginback),
                contentDescription = "Profile cover picture",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp),
                contentScale = ContentScale.Crop
            )
            Image(
                painter = painterResource(id = R.drawable.avatar),
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .clip(shape = RoundedCornerShape(50))
                    .padding(start = 16.dp, top = 50.dp)
                    .size(150.dp)
            )
            IconButton(onClick = { /*TODO*/ }) {
                Icon(imageVector = Icons.Rounded.ArrowBack,
                    contentDescription = "",
                    tint = ScreenBackground,
                    modifier = Modifier
                        .padding(16.dp)
                        .size(24.dp)
                )

            }
        }

        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)) {
            Column(
                modifier = Modifier
                    .fillMaxWidth(0.5f)

            ) {
                Text(text = "Name")
                Text(text = "Email")
                Text(text = "Adresse")
                Text(text = "Phone")
            }

            Divider(
                color = Color.Gray,
                modifier = Modifier
                    .height(100.dp)
                    .width(1.dp)

            )

            Column(
                modifier = Modifier
                    .fillMaxWidth(0.5f).padding(start = 16.dp)
            ) {
                Text(text = "A propos")
                Text(text = "Description")
            }



        }

        Spacer(modifier = Modifier.height(16.dp))
        LazyRow(content = {
            items(5)
            {
                Card(modifier = Modifier
                    .padding(8.dp)
                    .clip(shape = RoundedCornerShape(16.dp),)
                    .background(ScreenBackground),
                    ) {
                    Text(text = "My Offres", modifier = Modifier.padding(8.dp))
                }
            }
        })


    }

}