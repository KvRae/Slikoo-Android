package slikoo.kvrae.slikoo.ui.fragments.main_screen

import android.annotation.SuppressLint
import androidx.compose.foundation.background
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import slikoo.kvrae.slikoo.R
import slikoo.kvrae.slikoo.data.datasources.entities.Theme
import slikoo.kvrae.slikoo.ui.components.AreaFilterCard
import slikoo.kvrae.slikoo.ui.components.RecipeCardContent
import slikoo.kvrae.slikoo.ui.components.ThemeCard
import slikoo.kvrae.slikoo.ui.pages.LoadingScreen
import slikoo.kvrae.slikoo.ui.theme.LightBackground
import slikoo.kvrae.slikoo.ui.theme.LightSecondary
import slikoo.kvrae.slikoo.viewmodels.CategoryViewModel
import slikoo.kvrae.slikoo.viewmodels.MealsViewModel


@Composable
fun HomeScreen(navController: NavController) {
    val mealsViewModel : MealsViewModel  = viewModel()
    SwipeRefresh(
        state = rememberSwipeRefreshState(isRefreshing = mealsViewModel.isLoading.value),
        onRefresh = {
            mealsViewModel.getAllMeals()
        }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = LightSecondary)
                .verticalScroll(rememberScrollState())
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp)
            ) {
                Spacer(modifier = Modifier.padding(8.dp))
                if (mealsViewModel.meals.value.isNotEmpty()) OnlineRecipes(
                    navController,
                    mealsViewModel
                )
                Spacer(modifier = Modifier.padding(8.dp))
                ThemeListSection(navController)
                Spacer(modifier = Modifier.padding(8.dp))
                RecipesCategorySection(navController)
            }
        }
    }

    if (mealsViewModel.meals.value.isEmpty() && mealsViewModel.isLoading.value) LoadingScreen()
}


@Composable
fun OnlineRecipes(navController: NavController,mealsViewModel : MealsViewModel ) {
    val meals = mealsViewModel.meals
    val size = if (meals.value.size > 6) 6 else meals.value.size
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            SectionHeader(title = stringResource(R.string.online_recipes))
            Spacer(modifier = Modifier.padding(8.dp))
            LazyRow(modifier = Modifier.fillMaxWidth()) {
                items(size) {
                    RecipeCardContent(meals.value[it], navController = navController)
                }
            }
        }
    }
}

@Composable
fun RecipesCategorySection(navController: NavController) {
    val categories = CategoryViewModel().getCategories()
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .height((160 * categories.size).dp)
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            SectionHeader(title = stringResource(R.string.recipes_category))
            Spacer(modifier = Modifier.padding(8.dp))
            LazyVerticalGrid(columns = GridCells.Fixed(2),
                userScrollEnabled = false,
                content = {
                    items(categories.size) {
                        AreaFilterCard(
                            onClick =  {
                                       navController.navigate("category_screen/${categories[it].name}")
                            },
                            name = categories[it].name,
                            image = categories[it].image
                        )

                    }
                }
            )
        }
    }
}

@SuppressLint("SuspiciousIndentation")
@Composable
fun ThemeListSection(
    navController: NavController
) {
    val themes = listOf<Theme>(
        Theme(0, "Lecture", R.drawable.lecture),
        Theme(1, "L'ecriture", R.drawable.lecriture),
        Theme(2, "Philosophies", R.drawable.philosophie),
        Theme(3, "La dance", R.drawable.danse),
        Theme(4, "La musique", R.drawable.musique),
    )

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            SectionHeader(title = stringResource(R.string.theme_header))
            Spacer(modifier = Modifier.padding(8.dp))
            LazyRow(modifier = Modifier.fillMaxWidth()) {
                items(themes.size) {
                    ThemeCard(
                        name = themes[it].name,
                        image =  themes[it].image,
                        onClick = {
                            navController.navigate("category_screen/${themes[it].name}")
                        }
                    )
                }
            }
        }
    }

}

@Composable
fun SectionHeader(title: String) {
    Row(
        modifier = Modifier
            .padding(start = 10.dp, end = 8.dp)
            .fillMaxWidth()
    ) {
        Text(
            text = title, style = TextStyle(
                color = LightBackground,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
        )
    }
}
