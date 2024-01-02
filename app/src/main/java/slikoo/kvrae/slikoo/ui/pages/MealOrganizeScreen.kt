package slikoo.kvrae.slikoo.ui.pages

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import slikoo.kvrae.slikoo.R
import slikoo.kvrae.slikoo.ui.components.CustomAlertDialog
import slikoo.kvrae.slikoo.ui.fragments.meal.EventFinalFragment
import slikoo.kvrae.slikoo.ui.fragments.meal.EventFirstFragment
import slikoo.kvrae.slikoo.ui.fragments.meal.EventSecondFragment
import slikoo.kvrae.slikoo.ui.theme.LightBackground
import slikoo.kvrae.slikoo.ui.theme.LightSecondary
import slikoo.kvrae.slikoo.viewmodels.MealsViewModel


@Composable
fun MealOrganizeScreen(
    idMeal : Int = 0,
    navController: NavController
) {
    var fragment by remember {
        mutableStateOf("first")
    }
    var show by remember {
        mutableStateOf(false)
    }

    val mealVM: MealsViewModel = viewModel()

    if (idMeal > 0)
        DisposableEffect(Unit) {
            mealVM.getMealById(idMeal)
            onDispose { }
        }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .navigationBarsPadding()
            .background(LightSecondary),
        contentAlignment = Alignment.TopCenter
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Top
        )
        {
            TopAppBar(
                modifier = Modifier.fillMaxWidth(),
                backgroundColor = Color.Transparent,
                elevation = 0.dp,
                title = { Text(
                    text = stringResource(id = R.string.organize),
                    color = LightBackground,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold
                )
                        },
                actions = {
                    IconButton(onClick = { show = true }) {
                        Icon(
                            imageVector = Icons.Rounded.Close,
                            contentDescription = "",
                            tint = LightBackground
                        )
                    }
                    if (show) CustomAlertDialog(
                        onDismiss= { show = false },
                        dismissText = stringResource(id = R.string.no),
                        onConfirm = {
                            navController.popBackStack() },
                        title = stringResource(id = R.string.cancel),
                        message = stringResource(id = R.string.cancel_message),
                    )
                }
            )
            when (fragment) {
                stringResource(R.string.first) -> EventFirstFragment(
                    mealsViewModel = mealVM

                ) { fragment = it }
                stringResource(R.string.second) -> {
                    EventSecondFragment(
                        { fragment = it },
                        navController = navController,
                        mealVM
                    )
                }
                stringResource(R.string.third) -> {
                    EventFinalFragment(
                        onFragmentChange = { fragment = it },
                        navController = navController,
                    )
                }
                else -> EventFirstFragment(
                    mealVM) { fragment = it }
            }
        }
        BackHandler {
            show = true
        }
    }
}
