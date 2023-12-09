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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import slikoo.kvrae.slikoo.R
import slikoo.kvrae.slikoo.ui.components.CustomAlertDialog
import slikoo.kvrae.slikoo.ui.components.UserEventCard
import slikoo.kvrae.slikoo.ui.pages.LoadingScreen
import slikoo.kvrae.slikoo.ui.pages.TextElementScreen
import slikoo.kvrae.slikoo.ui.theme.LightError
import slikoo.kvrae.slikoo.viewmodels.MealsViewModel

@SuppressLint("SuspiciousIndentation")
@Composable
fun UserOffersList(navController: NavController) {
    val viewModel : MealsViewModel = viewModel()
    val scrollState = rememberLazyGridState()
    var isOpen by remember { mutableStateOf(false) }
    var mealId by remember { mutableStateOf(0) }

    if (viewModel.myMeals.isNullOrEmpty())
    DisposableEffect(Unit) {
        viewModel.getMyMeals()
        onDispose {}
    }

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
            if (viewModel.myMeals.isNotEmpty())
                LazyVerticalGrid(columns = GridCells.Fixed(2),
                userScrollEnabled = true,
                state = scrollState,
                content = {
                    items(viewModel.myMeals.size) {
                        UserEventCard(meal = viewModel.myMeals[it],
                            navController = navController,
                            onDelete = {
                                isOpen = true
                                mealId = viewModel.myMeals[it].id
                            }
                        )
                    }
                })
            if (isOpen) CustomAlertDialog(
                title = stringResource(id = R.string.delete),
                message = stringResource(R.string.delete_meal_description),
                confirmText = stringResource(id = R.string.yes),
                dismissText = stringResource(id = R.string.no),
                onDismiss = { isOpen = false },
                onConfirm = {
                    viewModel.deleteMeal(mealId)
                    makeToast(navController.context, "" )
                    mealId = 0
                    isOpen = false
                }
            )
            if (viewModel.myMeals.isEmpty()) TextElementScreen(backgound = LightError, text = "Aucune offre disponible")

        }
        if (viewModel.isLoading.value) LoadingScreen()
    }
}

fun makeToast(context: android.content.Context, message : String) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}