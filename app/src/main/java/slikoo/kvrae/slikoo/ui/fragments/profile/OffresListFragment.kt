package slikoo.kvrae.slikoo.ui.fragments.profile

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import slikoo.kvrae.slikoo.ui.components.UserEventCard
import slikoo.kvrae.slikoo.ui.pages.TextElementScreen
import slikoo.kvrae.slikoo.ui.theme.LightError
import slikoo.kvrae.slikoo.viewmodels.MealsViewModel

@Composable
fun UserOffersList(navController: NavController) {
    val viewModel : MealsViewModel = viewModel()
    val scrollState = rememberLazyGridState()

    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(4.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            if (viewModel.myMeals.isNotEmpty())LazyVerticalGrid(columns = GridCells.Fixed(2),
                userScrollEnabled = true,
                state = scrollState,
                content = {
                    items(viewModel.myMeals.size) {
                        UserEventCard(meal = viewModel.myMeals[it], navController = navController)
                    }
                })
            if (viewModel.myMeals.isEmpty()) TextElementScreen(backgound = LightError, text = "Aucune offre disponible")
        }
    }
}