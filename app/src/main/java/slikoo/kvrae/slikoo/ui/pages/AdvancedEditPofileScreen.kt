package slikoo.kvrae.slikoo.ui.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.IconButton
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import slikoo.kvrae.slikoo.R
import slikoo.kvrae.slikoo.ui.fragments.main_screen.RecipeScreen
import slikoo.kvrae.slikoo.ui.theme.LightError
import slikoo.kvrae.slikoo.ui.theme.LightSecondary
import slikoo.kvrae.slikoo.utils.AppScreenNavigator


@Composable
fun AdvancedEditProfileScreen(navController: NavController) {
    val menuList = listOf(
        "Recipes",
        "Saved",
        "Liked",
        "Following",
        "Followers",
        "Settings",
        "Help",
        "About"
    )
    Column(
        modifier = Modifier
            .fillMaxSize()
            .navigationBarsPadding()
            .background(LightSecondary)
            //.verticalScroll(rememberScrollState())
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(LightSecondary)
        ) {
            Image(
                painter = painterResource(id = R.drawable.banner),
                contentDescription = "Profile cover picture",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(160.dp),
                contentScale = ContentScale.Crop
            )
            Surface(
                Modifier
                    .fillMaxWidth()
                    .padding(top = 160.dp),
                shape = RoundedCornerShape(bottomStart = 16.dp, bottomEnd = 24.dp),
                elevation = 8.dp,
            ) {

                Column {
                    Spacer(modifier = Modifier.height(70.dp))
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth(0.4f)

                        ) {
                            Text(text = "Mannai Sara",
                                maxLines = 1,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold,
                            )
                            Text(text = "SaraMannai@exemple.tn",
                                maxLines = 1,
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Normal,
                                modifier = Modifier.padding(top = 4.dp)

                            )
                            Text(text = "08 Rue Marseille, Jendouba 8100",
                                maxLines = 1,
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Normal,
                                modifier = Modifier.padding(top = 4.dp)

                            )
                            Text(text = "+21654879654",
                                maxLines = 1,
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Normal,
                                modifier = Modifier.padding(top = 4.dp)
                            )
                        }

                        Divider(
                            color = Color.Gray.copy(alpha = 0.4f),
                            modifier = Modifier
                                .height(100.dp)
                                .width(1.dp)

                        )
                        Spacer(modifier = Modifier.width(8.dp))

                        Column(
                            modifier = Modifier
                                .fillMaxWidth(0.9f)
                        ) {
                            Text(text = stringResource(R.string.about),
                                maxLines = 1,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold,
                            )
                            Text(text = stringResource(id = R.string.welcome_sub_description),
                                maxLines = 4,
                                letterSpacing = 0.5.sp,
                                lineHeight = 15.sp,
                                overflow = TextOverflow.Ellipsis,
                                fontSize = 11.sp,
                                modifier = Modifier.padding(top = 4.dp)

                            )
                        }
                    }
                }
            }
            AsyncImage(model = "https://raw.githubusercontent.com/KvRae/Slikoo-JsonCollection/main/Assets/portrait-6054910_1280.jpg",
                contentDescription = stringResource(R.string.profile_pic),
                onLoading = { },
                modifier = Modifier
                    .padding(start = 26.dp, top = 110.dp,)
                    .size(120.dp)
                    .clip(shape = CircleShape)
            )
            IconButton(modifier = Modifier.statusBarsPadding(),
                onClick = {
                navController.popBackStack()
                navController.navigate(AppScreenNavigator.MainAppScreen.route)
            }) {
                Icon(imageVector = Icons.Rounded.ArrowBack,
                    contentDescription = "",
                    tint = LightSecondary,
                    modifier = Modifier
                        .padding(16.dp)
                        .size(24.dp)
                )

            }

        }

        Spacer(modifier = Modifier.height(8.dp))

        LazyRow(content = {
            items(menuList.size) {
                Surface(modifier = Modifier
                    .padding(8.dp)
                    .clip(shape = RoundedCornerShape(16.dp))
                    .background(LightError)
                    .clickable(onClick = { /*TODO*/ }),
                    elevation = 8.dp
                ) {
                    Text(text = menuList[it],
                        modifier = Modifier.padding(10.dp),
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1,
                        fontWeight = FontWeight.Medium,
                        fontSize = 14.sp,
                    )
                }
            }

        })
        Spacer(modifier = Modifier.height(8.dp))
        Box(modifier = Modifier
            .fillMaxWidth()
            .clip(shape = RoundedCornerShape(16.dp))
            .background(LightError)
            .padding(16.dp)
        ) {
            RecipeScreen(navController = navController)
        }
    }

}