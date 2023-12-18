package slikoo.kvrae.slikoo.ui.pages

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import slikoo.kvrae.slikoo.data.datasources.entities.Meal
import slikoo.kvrae.slikoo.ui.components.CustomButton
import slikoo.kvrae.slikoo.ui.components.DescriptionTextField
import slikoo.kvrae.slikoo.ui.components.ExpandableCard
import slikoo.kvrae.slikoo.viewmodels.EditMealViewModel


@Composable
fun EditMealScreen(
    navController: NavController,
    idMeal : Int = 0,
) {
    val viewModel : EditMealViewModel = viewModel()
    var meal by remember {
        mutableStateOf(Meal())
    }

    if (idMeal != 0) {
        DisposableEffect(Unit){
            viewModel.getMealById(idMeal)
            onDispose {  }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize(1f),
        horizontalAlignment = Alignment.Start,
    ) {
        EditProfileTopBar(
            navController = navController,
        )
        Column(
            modifier = Modifier
                .fillMaxSize(1f)
                .verticalScroll(
                    enabled = true,
                    state = rememberScrollState()
                )
                .padding(8.dp),
            horizontalAlignment = Alignment.Start,
        ) {

            ExpandableCard(
                items = viewModel.genres,
                onTitleChange = { meal = meal.copy(genre = it) },
                label = stringResource(id = R.string.genre_holder),
                placeholder = viewModel.meal.genre,
                value = meal.genre,
            )
            ExpandableCard(
                items = viewModel.invitationTypes,
                onTitleChange = { meal = meal.copy(type = it) },
                value = meal.type,
                placeholder = viewModel.meal.type,
            )
            ExpandableCard(
                items =  viewModel.themes,
                onTitleChange = { meal = meal.copy(theme = it) },
                placeholder = viewModel.meal.theme,
                label = stringResource(id = R.string.theme_holder),
                value = meal.theme,
            )
            ExpandableCard(
                items =  viewModel.preferences
            )
            DescriptionTextField(
                onChange = {} ,
                value = meal.description,
                label = stringResource(id = R.string.description),
            )
            CustomButton(
                text = stringResource(id = R.string.update),
                onClick = {},
            )
        }
    }

}