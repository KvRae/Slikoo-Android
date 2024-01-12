package slikoo.kvrae.slikoo.ui.pages

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import kotlinx.coroutines.launch
import slikoo.kvrae.slikoo.R
import slikoo.kvrae.slikoo.ui.components.CustomButton
import slikoo.kvrae.slikoo.ui.components.DescriptionTextField
import slikoo.kvrae.slikoo.ui.components.ExpandableCard
import slikoo.kvrae.slikoo.ui.components.ImagePickerField
import slikoo.kvrae.slikoo.ui.fragments.meal.getRealPathFromURI
import slikoo.kvrae.slikoo.utils.compressFile
import slikoo.kvrae.slikoo.viewmodels.EditMealViewModel
import java.io.File


@Composable
fun EditMealScreen(
    navController: NavController,
    idMeal : Int = 0,
) {
    val viewModel : EditMealViewModel = viewModel()
    val coroutineScope = rememberCoroutineScope()
    val meal by remember {
        mutableStateOf(viewModel.meal)
    }

    if (idMeal != 0) {
        DisposableEffect(Unit){
            viewModel.getMealById(idMeal)

            onDispose {
                viewModel.isLoading = false
                viewModel.isError = false

            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize(1f)
            .statusBarsPadding()
            .navigationBarsPadding()
    ) {
        if (viewModel.meal.value.id > 0)
            Column(
                modifier = Modifier.fillMaxSize(1f),
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
                        onTitleChange = { meal.value = meal.value.copy(genre = it) },
                        label = stringResource(id = R.string.genre_holder),
                        placeholder = viewModel.meal.value.genre,
                        value = meal.value.genre,
                    )
                    ExpandableCard(
                        items = viewModel.invitationTypes,
                        onTitleChange = { meal.value = meal.value.copy(type = it) },
                        value = meal.value.type,
                        placeholder = viewModel.meal.value.type,
                    )
                    ExpandableCard(
                        items = viewModel.themes,
                        onTitleChange = { meal.value = meal.value.copy(theme = it) },
                        placeholder = viewModel.meal.value.theme,
                        label = stringResource(id = R.string.theme_holder),
                        value = meal.value.theme,
                    )

                    DescriptionTextField(
                        onChange = {},
                        value = meal.value.description,
                        label = stringResource(id = R.string.description),
                    )

                    ImagePickerField(
                        imageUrl = viewModel.banner.value,
                        onImageSelected = { viewModel.banner.value = it!! },
                        modifier = Modifier.padding(8.dp),
                    )

                    CustomButton(
                        text = stringResource(id = R.string.meal_update),
                        onClick = {
                            coroutineScope.launch {
                                val mealBanner =
                                    if (viewModel.banner.toString()
                                            .contains("https") || viewModel.banner.toString()
                                            .isNullOrEmpty()
                                    )
                                        null
                                    else
                                        compressFile(
                                            navController.context,
                                            File(
                                                getRealPathFromURI(
                                                    context = navController.context,
                                                    uri = viewModel.banner.value
                                                ) ?: ""
                                            )
                                        )
                                viewModel.onUpdateMeal(
                                    mealBanner = mealBanner,
                                    meal = meal.value,
                                    id = idMeal
                                )
                            }
                        },
                    )
                }
            }
        if (viewModel.isLoading) {
            LoadingScreen()
        }
    }

}