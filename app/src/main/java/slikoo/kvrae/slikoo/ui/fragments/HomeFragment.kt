package slikoo.kvrae.slikoo.ui.fragments

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import slikoo.kvrae.slikoo.ui.components.Content
import slikoo.kvrae.slikoo.ui.components.RatingCard
import slikoo.kvrae.slikoo.ui.components.RecipeCard



@Preview
@Composable
fun HomeScreenPreview() {
    val context = androidx.compose.ui.platform.LocalContext.current
    HomeScreen(navController = NavController(context))
}


@Composable
fun HomeScreen(navController: NavController) {
    Column(modifier = Modifier
        .fillMaxSize().padding(top = 16.dp)) {
        // Top Row
        Row (modifier = Modifier.fillMaxWidth().padding(8.dp)){
            Text(text = "Hello,")
            Spacer(modifier = Modifier.weight(1f))
            Text(text = "See All")
        }
        Box(modifier = Modifier
            .fillMaxWidth()) {
            RatingList()
        }
        // Middle Row
        Row (modifier = Modifier.fillMaxWidth().padding(8.dp)){
            Text(text = "Hello,")
            Spacer(modifier = Modifier.weight(1f))
            Text(text = "See All") }
        Box(modifier = Modifier
            .fillMaxWidth().fillMaxHeight(0.4f)) {
            OnlineRecipeList()
        }
        // Bottom Row
        Row (modifier = Modifier.fillMaxWidth().padding(8.dp)){
            Text(text = "Hello,")
            Spacer(modifier = Modifier.weight(1f))
            Text(text = "See All") }
        Box(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.5f)) {
            OnlineRecipeList()
        }


    }
}

@Composable
fun OnlineRecipeList() {
        LazyRow(modifier = Modifier.fillMaxWidth()) {
            items(3) {
                RecipeCard {
                    Content()
                }
            }
        }
}

@Composable
fun RatingList() {
    Box(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(modifier = Modifier.fillMaxWidth()) {
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