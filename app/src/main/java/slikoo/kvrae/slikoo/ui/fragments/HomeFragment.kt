package slikoo.kvrae.slikoo.ui.fragments

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardArrowDown
import androidx.compose.material.icons.rounded.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import slikoo.kvrae.slikoo.ui.components.Content
import slikoo.kvrae.slikoo.ui.components.RatingCard
import slikoo.kvrae.slikoo.ui.components.RecipeCard
import slikoo.kvrae.slikoo.ui.theme.ScreenBackground


@Preview
@Composable
fun HomeScreenPreview() {
    val context = androidx.compose.ui.platform.LocalContext.current
    HomeScreen(navController = NavController(context))
}


@Composable
fun HomeScreen(navController: NavController) {
    Column(modifier = Modifier.background(ScreenBackground)
        .fillMaxSize()) {
        OnlineRecipes()
        RecipesCategorySection()
        RatingListSection()
    }
}


@Composable
fun OnlineRecipes() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            SectionHeader(title ="Online Recipes")
            LazyRow(modifier = Modifier.fillMaxWidth()) {
                items(3) {
                    RecipeCard {
                        RatingCard()
                    }
                }
            }
        }
    }
}

@Composable
fun RecipesCategorySection() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .fillMaxHeight(0.6f)
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            SectionHeader(title = "Recipes Category")
            Spacer(modifier = Modifier.padding(8.dp))
            LazyVerticalGrid(columns = GridCells.Fixed(2), content = {
                    items(6) {
                        RecipeCard {
                            Content()
                        }
                    }
                }
            )
        }
    }
}

@Composable
fun RatingListSection() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            SectionHeader(title = "Rating List")
            LazyRow(modifier = Modifier.fillMaxWidth()) {
                items(3) {
                    RecipeCard {
                        RatingCard()
                    }
                }
            }
        }
    }
}

@Composable
fun SectionHeader(title: String) {
    Row(modifier = Modifier
        .padding(start = 8.dp, end = 8.dp)
        .fillMaxWidth()) {
        Text(text = title)
        Spacer(modifier = Modifier.weight(1f))
        if (title != "Recipes Category")
            Icon(imageVector = Icons.Rounded.KeyboardArrowRight, contentDescription ="")
        else
            Icon(imageVector = Icons.Rounded.KeyboardArrowDown, contentDescription ="")
    }
}
