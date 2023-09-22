package slikoo.kvrae.slikoo.ui.fragments.main_screen

import androidx.compose.foundation.background
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import slikoo.kvrae.slikoo.R
import slikoo.kvrae.slikoo.ui.components.RecipeCardContent
import slikoo.kvrae.slikoo.ui.components.SearchBarWithFilter
import slikoo.kvrae.slikoo.ui.components.ShimmerRecipeCard
import slikoo.kvrae.slikoo.ui.pages.TextElementScreen
import slikoo.kvrae.slikoo.ui.theme.LightSecondary
import slikoo.kvrae.slikoo.viewmodels.MealsViewModel


@Composable
fun RecipeScreen(navController: NavController) {
    val mealsViewModel: MealsViewModel = viewModel()
    val scrollState = rememberLazyGridState()

    Box(
        modifier = Modifier.fillMaxSize().background(LightSecondary),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(4.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            if (remember { derivedStateOf { scrollState.firstVisibleItemIndex } }.value == 0)
                SearchBarWithFilter(
                    searchText = mealsViewModel.searchText.value,
                    onSearch = {
                        mealsViewModel.searchText.value = it
                        mealsViewModel.filterMealsList(it) },
                    onValueChange = {
                        mealsViewModel.searchText.value = it
                        mealsViewModel.filterMealsList(it)
                                    },
                    onFilter = { }
                    )
            if (mealsViewModel.filteredMeals.isEmpty() && mealsViewModel.searchText.value.isNotEmpty())
                TextElementScreen(text = stringResource(id = R.string.no_element_found))
            LazyVerticalGrid(columns = GridCells.Fixed(2),
                userScrollEnabled = true,
                state = scrollState,
                content = {
                    if (mealsViewModel.filteredMeals.isEmpty() && mealsViewModel.isLoading.value) items(6){ ShimmerRecipeCard() }
                    items(mealsViewModel.filteredMeals.size) {
                        RecipeCardContent(meal = mealsViewModel.filteredMeals[it],
                            navController = navController)
                    }
                }
            )
            if (mealsViewModel.filteredMeals.isEmpty() && !mealsViewModel.isLoading.value)
                TextElementScreen(text = stringResource(id = R.string.no_element_found))

        }
    }
}

