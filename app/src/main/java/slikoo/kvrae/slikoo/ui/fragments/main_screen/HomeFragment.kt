package slikoo.kvrae.slikoo.ui.fragments.main_screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import slikoo.kvrae.slikoo.ui.components.AreaFilterCard
import slikoo.kvrae.slikoo.ui.components.RatingCard
import slikoo.kvrae.slikoo.ui.components.RecipeCardContent
import slikoo.kvrae.slikoo.ui.components.SearchBar
import slikoo.kvrae.slikoo.ui.theme.PrimaryBlackText
import slikoo.kvrae.slikoo.viewmodel.AreaViewModel
import slikoo.kvrae.slikoo.viewmodel.CategoryViewModel


@Preview
@Composable
fun HomeScreenPreview() {
    val context = androidx.compose.ui.platform.LocalContext.current
    HomeScreen(navController = NavController(context))
}


@Composable
fun HomeScreen(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            SearchBar(onSearch = {})
            OnlineRecipes()
            RecipesCategorySection()
            RatingListSection()
        }
    }
}


@Composable
fun OnlineRecipes() {
    var areas = AreaViewModel().getAreas()
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            SectionHeader(title ="Online Recipes")
            LazyRow(modifier = Modifier.fillMaxWidth()) {
                items(areas.size) {
                        RecipeCardContent(areas[it])
                }
            }
        }
    }
}

@Composable
fun RecipesCategorySection() {
    val categories = CategoryViewModel().getCategories()
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .height((160*categories.size ).dp)
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            SectionHeader(title = "Recipes Category")
            Spacer(modifier = Modifier.padding(8.dp))
            LazyVerticalGrid(columns = GridCells.Fixed(2),
                userScrollEnabled = false,
                content = {
                    items(categories.size) {
                        AreaFilterCard(name = categories[it].name, image = categories[it].image, imageUrl = "") {

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
                        RatingCard()
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
        Text(text = title,style = TextStyle(
            color = PrimaryBlackText,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp)
        )
    }
}
