package slikoo.kvrae.slikoo.ui.pages

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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AccountBox
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.LocationOn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import slikoo.kvrae.slikoo.R
import slikoo.kvrae.slikoo.ui.components.CustomButton
import slikoo.kvrae.slikoo.ui.theme.LightBackground
import slikoo.kvrae.slikoo.ui.theme.LightError
import slikoo.kvrae.slikoo.ui.theme.LightPrimary
import slikoo.kvrae.slikoo.ui.theme.LightPrimaryVariant
import slikoo.kvrae.slikoo.ui.theme.LightSecondary
import slikoo.kvrae.slikoo.utils.AppScreenNavigator
import slikoo.kvrae.slikoo.viewmodel.MealsViewModel


@Composable
fun MealsDetailScreen(navController: NavController,id : Int) {
    val mealsViewModel : MealsViewModel = viewModel()

    mealsViewModel.getMealById(id = id)

    if (mealsViewModel.meal.value.id != 0){
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(LightSecondary)
        ) {
            // Heading Image
            AsyncImage(
                model = "https://slikoo.com/repasImgs/${mealsViewModel.meal.value.avatar}",
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(400.dp)
            )
            Box(modifier = Modifier
                .fillMaxWidth()
                .height(400.dp)
                .background(Color.Black.copy(alpha = 0.3f)))
            //Content
            Column(modifier = Modifier.fillMaxWidth()) {
                // TopAppBar
                MealDetailHeader(navController = navController)
                // Event Details
                Spacer(modifier = Modifier.height(100.dp))
                MealDetailHeading(eventName = mealsViewModel.meal.value.theme, eventDate = mealsViewModel.meal.value.genrenourriture)
                MealDetailContent(mealsViewModel = mealsViewModel)
            }

        }
    } else {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
        ) {
           LoadingScreen()
        }
    }

}

@Composable
fun MealDetailHeader(navController : NavController) {
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
        actions = {},
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .statusBarsPadding()
    )
}

@Composable
fun MealDetailHeading(
    eventName : String = "Event Name",
    eventDate : String = "Event Date"
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = eventName,
            style = TextStyle(
                color = LightPrimaryVariant,
                fontWeight = FontWeight.Bold,
                fontSize = 32.sp,
            )
        )
        Text(text = eventDate,
            style = TextStyle(
                color = LightPrimaryVariant,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
            )
        )
    }
}

@Composable
fun MealDetailContent(mealsViewModel: MealsViewModel) {
    Surface(
        modifier = Modifier
            .fillMaxSize(),
        shape = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp),
        color = LightSecondary
    ) {
        Column(
            Modifier
                .fillMaxSize()
                .fillMaxHeight()
                .padding(16.dp),

            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center

        ) {
            MealDetailContentItem(
                icon = ImageVector.vectorResource(id = R.drawable.time_filled)
                , title = mealsViewModel.dateConverter(mealsViewModel.meal.value.date.slice(0..9)) ,
                detail = mealsViewModel.meal.value.heure.slice(11..15)
            )
            MealDetailContentItem(
                icon = Icons.Rounded.LocationOn,
                detail = mealsViewModel.meal.value.localisation,
                title = stringResource(id = R.string.location)
            )
            MealDetailContentItem(
                icon = ImageVector.vectorResource(id = R.drawable.round_euro),
                title = stringResource(id = R.string.price),
                detail = mealsViewModel . meal . value . prix,
            )

            MealDetailContentItem(
                icon = Icons.Rounded.AccountBox,
                title = stringResource(id = R.string.detail),
                detail = mealsViewModel.meal. value . description,
            )

            Spacer(modifier = Modifier.height(16.dp))
            CustomButton(text = stringResource(id = R.string.book), onClick = {  })
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
fun MealDetailContentItem(
    icon: ImageVector,
    title: String = "",
    detail: String = ""
) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically) {
        Icon(
            imageVector = icon,
            tint = Color.Gray,
            contentDescription ="")
        Spacer(modifier = Modifier.width(8.dp))
        Column(Modifier.fillMaxWidth()) {
            Text(text = title,
                style = TextStyle(
                    color = LightBackground,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                )
            )
            Text(text = detail,
                style = TextStyle(
                    color = Color.Gray,
                    fontWeight = FontWeight.Medium,
                    fontSize = 12.sp,
                )
            )
        }
    }
}

@Preview
@Composable
fun MealsDetailScreenPreview() {
    MealsDetailScreen(navController = NavController(LocalContext.current),id = 1)
}