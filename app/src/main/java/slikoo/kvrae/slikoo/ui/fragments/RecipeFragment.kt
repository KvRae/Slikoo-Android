package slikoo.kvrae.slikoo.ui.fragments

import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import slikoo.kvrae.slikoo.ui.components.Content
import slikoo.kvrae.slikoo.ui.components.RecipeCard


@Composable
fun RecipeScreen(navController: NavController) {
    val scrollState = rememberScrollState()
    Box(modifier = Modifier
        .fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        Column(modifier = Modifier
            .fillMaxSize()
            .scrollable(scrollState, orientation = Orientation.Vertical, enabled = true),
            horizontalAlignment = Alignment.CenterHorizontally,

        ){
            LazyVerticalGrid(columns = GridCells.Fixed(2), content ={
                items(10){
                    RecipeCard(){ Content() }
                }
            })
        }
    }
}