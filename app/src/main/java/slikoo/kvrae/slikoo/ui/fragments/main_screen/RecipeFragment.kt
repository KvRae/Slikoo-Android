package slikoo.kvrae.slikoo.ui.fragments.main_screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import slikoo.kvrae.slikoo.ui.components.RecipeCardContent
import slikoo.kvrae.slikoo.ui.components.SearchBarWithFilter
import slikoo.kvrae.slikoo.viewmodel.MealsViewModel


@Composable
fun RecipeScreen(navController: NavController) {
    val mealsViewModel: MealsViewModel = viewModel()
    val scrollState = rememberLazyGridState()

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(4.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            if (remember { derivedStateOf { scrollState.firstVisibleItemIndex } }.value == 0) {
                SearchBarWithFilter(onSearch = { }, onFilter = { }, onValueChange = {}, searchText = "")
            }
            LazyVerticalGrid(columns = GridCells.Fixed(2),
                userScrollEnabled = true,
                state = scrollState,
                content = {
                    items(mealsViewModel.meals.size) {
                        RecipeCardContent(meal = mealsViewModel.meals[it],
                            navController = navController)
                    }
                }
            )
        }
    }
}

