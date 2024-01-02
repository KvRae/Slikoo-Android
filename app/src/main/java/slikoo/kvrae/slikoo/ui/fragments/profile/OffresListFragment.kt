package slikoo.kvrae.slikoo.ui.fragments.profile

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import slikoo.kvrae.slikoo.R
import slikoo.kvrae.slikoo.ui.components.CustomAlertDialog
import slikoo.kvrae.slikoo.ui.components.UserEventCard
import slikoo.kvrae.slikoo.ui.pages.LoadingScreen
import slikoo.kvrae.slikoo.ui.pages.TextWithImageScreen
import slikoo.kvrae.slikoo.ui.theme.LightError
import slikoo.kvrae.slikoo.viewmodels.MyMealsViewModel

@SuppressLint("SuspiciousIndentation")
@Composable
fun UserOffersList(navController: NavController) {

    val viewModel : MyMealsViewModel = viewModel()

    val myMeals = remember {
        viewModel.myMeals
    }

    val scrollState = rememberLazyGridState()

    var isOpen by remember { mutableStateOf(false) }
    var mealId by remember { mutableStateOf(0) }


    DisposableEffect(key1 = viewModel.myMeals.size) {
        viewModel.getMyMeals()
        onDispose {}
    }

    Box(
        modifier = Modifier
            .fillMaxSize(1f),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(1f)
                .padding(4.dp),
            horizontalAlignment = Alignment.Start,
        ) {
            if (viewModel.myMeals.isNotEmpty())
                SwipeRefresh(
                    state = rememberSwipeRefreshState(
                        isRefreshing = viewModel.isLoading.value
                    ),
                    onRefresh = {
                        viewModel.getMyMeals()
                    }
                ) {
                    LazyVerticalGrid(columns = GridCells.Fixed(2),
                        userScrollEnabled = true,
                        state = scrollState,
                        content = {
                            items(myMeals.size) {
                                UserEventCard(meal = myMeals[it],
                                    navController = navController,
                                    onDelete = {
                                        isOpen = true
                                        mealId = myMeals[it].id
                                    },
                                    onEdit = {
                                        navController
                                            .navigate("edit_meal_screen/${myMeals[it].id}")
                                    }
                                )
                            }
                        }
                    )
                }
            if (isOpen) CustomAlertDialog(
                title = stringResource(id = R.string.delete),
                message = stringResource(R.string.delete_meal_description),
                confirmText = stringResource(id = R.string.yes),
                dismissText = stringResource(id = R.string.no),
                onDismiss = { isOpen = false },
                onConfirm = {
                    viewModel.deleteMeal(mealId)
                    isOpen = false


                }
            )
        }
        if (myMeals.isEmpty() && !viewModel.isLoading.value) TextWithImageScreen(
            imageVector = ImageVector.vectorResource(id =R.drawable.no_food),
            text = stringResource(id = R.string.no_meals),
            background = LightError)
        if (viewModel.isLoading.value) LoadingScreen(
            background = LightError
        )
    }
}

fun makeToast(context: android.content.Context, message : String) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}