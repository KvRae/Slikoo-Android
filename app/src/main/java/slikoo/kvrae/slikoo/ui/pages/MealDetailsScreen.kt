package slikoo.kvrae.slikoo.ui.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import slikoo.kvrae.slikoo.R
import slikoo.kvrae.slikoo.ui.components.CustomSlidingBar
import slikoo.kvrae.slikoo.ui.theme.LightError
import slikoo.kvrae.slikoo.ui.theme.LightPrimary
import slikoo.kvrae.slikoo.utils.AppScreenNavigator


@Composable
fun MealsDetailScreen(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())

    ) {
        // Heading Image
        Image(
            painter = painterResource(id = R.drawable.petit_dejeuner_sain_frais_fond_bois),
            contentDescription = "",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(400.dp)
        )
//        AsyncImage(
//            model = "",
//            contentDescription = ""
//        )

        //Content
        Column(modifier = Modifier.fillMaxWidth()) {
            // TopAppBar
            TopAppBar(
                title = {},
                navigationIcon = {
                    IconButton(
                        onClick = { navController.navigate(AppScreenNavigator.MainAppScreen.route) },
                        modifier = Modifier
                            .clip(shape = CircleShape)
                            .background(LightError)
                    ) {
                        Icon(
                            imageVector = Icons.Rounded.ArrowBack,
                            contentDescription = "ArrowBack",
                            tint = LightPrimary
                        )

                    }
                },
                backgroundColor = Color.Transparent,
                elevation = 0.dp,
                actions = {
                    IconButton(
                        onClick = { },
                        modifier = Modifier
                            .clip(shape = CircleShape)
                            .background(LightError)
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.FavoriteBorder,
                            contentDescription = "Share",
                            tint = LightPrimary
                        )

                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .statusBarsPadding()
            )

            // Event Details
            Spacer(modifier = Modifier.height(200.dp))
            Surface(
                modifier = Modifier
                    .fillMaxHeight(2f)
                    .fillMaxWidth(),
                shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
                elevation = 8.dp,
                color = Color.White
            ) {
                Column(
                    Modifier
                        .fillMaxSize()
                        .fillMaxHeight(1f)
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center

                ) {
                    // Event Name
                    Text(
                        text = "Baladé au Champ d'elysee",
                        style = androidx.compose.ui.text.TextStyle(
                            color = Color.Black,
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp
                        )
                    )
                    Row {
                        Icon(
                            imageVector = Icons.Outlined.FavoriteBorder,
                            contentDescription = "Share",
                            tint = LightPrimary
                        )
                        Text(text = "${4.0} ")
                        Text(text = "(${100})")
                        Text(text = "Reviews")
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    LazyRow(
                        modifier= Modifier.fillMaxWidth().padding(end = 8.dp, start=8.dp),
                        horizontalArrangement = Arrangement.SpaceAround
                    ){
                        items(3){
                            Text(text = "Info")
                        }
                    }

                    CustomSlidingBar(
                        sliderPosition = 0.7f,
                        maxSlide = 2f,
                        mintSlide = 0f,
                    )

                }

            }

        }

    }
}

@Preview
@Composable
fun MealsDetailScreenPreview() {
    MealsDetailScreen(navController = NavController(LocalContext.current))
}