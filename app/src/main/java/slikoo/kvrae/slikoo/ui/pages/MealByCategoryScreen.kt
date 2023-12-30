package slikoo.kvrae.slikoo.ui.pages

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import slikoo.kvrae.slikoo.R
import slikoo.kvrae.slikoo.data.datasources.entities.Meal
import slikoo.kvrae.slikoo.ui.components.LoadingDialog
import slikoo.kvrae.slikoo.ui.components.RecipeCardContent
import slikoo.kvrae.slikoo.ui.theme.LightSecondary
import slikoo.kvrae.slikoo.viewmodels.MealsViewModel


@SuppressLint("SuspiciousIndentation")
@Composable
fun MealsByCategory(
    navController: NavController,
    filter: String = ""
) {
    val viewModel: MealsViewModel = viewModel()
    val filteredMeals = remember { mutableStateListOf<Meal>() }

    LaunchedEffect(Unit) {
        viewModel.getMealsByLocalisation(
            filter = filter,
            mealsFiltered = filteredMeals
        )
    }

    Column(
        modifier = Modifier
            .statusBarsPadding()
            .navigationBarsPadding()
            .fillMaxSize()
    ) {
        EditProfileTopBar(
            navController = navController,
            title = stringResource(id = R.string.meal_for) + " " + filter
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            if (filteredMeals.isNotEmpty())
                SwipeRefresh(
                    state = rememberSwipeRefreshState(
                        isRefreshing = viewModel.isLoading.value
                    ),
                    onRefresh = {
                        viewModel.getAllMeals()
                    }
                ) {
                    LazyVerticalGrid(columns = GridCells.Fixed(2),
                        userScrollEnabled = true,
                        content = {
                            items(filteredMeals.size) { index ->
                                RecipeCardContent(
                                    meal = filteredMeals[index],
                                    navController = navController
                                )
                            }
                        })
                }
            if (filteredMeals.isEmpty() && !viewModel.isLoading.value)
                TextWithImageScreen(
                    imageVector = ImageVector.vectorResource(id = R.drawable.no_food),
                    text = stringResource(id = R.string.no_meals),
                    background = LightSecondary,
                )
            if (viewModel.isLoading.value)
                LoadingDialog()
        }
    }
}
