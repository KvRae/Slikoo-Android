package slikoo.kvrae.slikoo.ui.fragments.main_screen

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
import slikoo.kvrae.slikoo.ui.components.RecipeCardContent
import slikoo.kvrae.slikoo.ui.components.SearchBarWithFilter
import slikoo.kvrae.slikoo.viewmodel.AreaViewModel


@Composable
fun RecipeScreen(navController: NavController) {
    val areas = AreaViewModel().getAreas()
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
            SearchBarWithFilter({})
            LazyVerticalGrid(columns = GridCells.Fixed(2), content ={
                items(areas.size){
                    RecipeCardContent( area = areas[it])
                }
            })
        }
    }
}