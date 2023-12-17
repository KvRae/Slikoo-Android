package slikoo.kvrae.slikoo.ui.fragments.profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material.icons.rounded.Done
import androidx.compose.material.icons.rounded.Place
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import slikoo.kvrae.slikoo.R
import slikoo.kvrae.slikoo.ui.components.MealCardWrapper
import slikoo.kvrae.slikoo.ui.pages.LoadingScreen
import slikoo.kvrae.slikoo.ui.pages.TextWithImageScreen
import slikoo.kvrae.slikoo.ui.theme.LightBackground
import slikoo.kvrae.slikoo.ui.theme.LightError
import slikoo.kvrae.slikoo.ui.theme.LightGreen
import slikoo.kvrae.slikoo.viewmodels.FeedbackViewModel

@Composable
fun FeedbackFragment(navController: NavController) {

    val viewModel : FeedbackViewModel = viewModel()

    DisposableEffect(key1 = viewModel.invitations, key2 = viewModel.reservations) {
        viewModel.getFeedbacks()
        onDispose {

        }
    }

    Box(
        Modifier
            .fillMaxSize(1f)
    ) {
        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {
            MealsSection(
                vm = viewModel,
                navController = navController
            )
            InvitationsSection(
                vm = viewModel,
                navController = navController
            )

        }
        if (viewModel.isLoading)
            LoadingScreen()
    }

}

@Composable
fun MealsSection(
    vm: FeedbackViewModel,
    navController: NavController
) {

    Column {
        SectionHeader(text = stringResource(id = slikoo.kvrae.slikoo.R.string.meals))
        Spacer(modifier = Modifier.height(16.dp))
        LazyRow(
            content = {
                items(vm.reservations.size) {
                    for (feedback in vm.feedbacks) {
                        if (feedback.providerId == vm.reservations[it].user?.id.toString()) {
                            MealCardWrapper(
                                title =
                                stringResource(id = R.string.by) +
                                        (vm.reservations[it].user?.nom
                                            ?: "") + " " + (vm.reservations[it].user?.prenom
                                    ?: ""),
                                image = (vm.reservations[it].meal?.avatarUrl.plus(vm.reservations[it].meal?.avatar)),
                                color = LightGreen,
                                icon = Icons.Rounded.Check,
                                onClick = {
                                    navController.navigate("feedback/${vm.reservations[it].meal?.id}")
                                }
                            )
                        } else {
                            MealCardWrapper(
                                title =
                                stringResource(id = R.string.by) +
                                        (vm.reservations[it].user?.nom
                                            ?: "") + " " + (vm.reservations[it].user?.prenom
                                    ?: ""),
                                image = (vm.reservations[it].meal?.avatarUrl.plus(vm.reservations[it].meal?.avatar)),
                                color = LightGreen,
                                icon = Icons.Rounded.Place,
                                onClick = {
                                    navController.navigate("feedback_screen/${vm.reservations[it].meal?.id}/${0}")
                                }
                            )
                        }
                    }

                }

            }
        )
    }

}

@Composable
fun InvitationsSection(
    vm: FeedbackViewModel,
    navController : NavController
) {

    Column {
        if (vm.invitations.isNotEmpty()) {
            SectionHeader(text = stringResource(id = slikoo.kvrae.slikoo.R.string.invitations))
            Spacer(modifier = Modifier.height(8.dp))
            LazyRow(
                content = {
                    items(vm.invitations.size) {
                        MealCardWrapper(
                            title = (vm.invitations[it].userDemander?.nom ?: "") + " " + (vm.invitations[it].userDemander?.prenom
                                ?: ""),
                            image = (vm.invitations[it].userDemander?.avatarUrl.plus(vm.invitations[it].userDemander?.avatar)),
                            color = LightGreen,
                            icon = Icons.Rounded.Done,
                            onClick = {

                            }
                        )
                    }

                }

            )
        }
        else
            TextWithImageScreen(
                backgound = LightError,
                imageVector = ImageVector.vectorResource(id = R.drawable.email),
                text =  stringResource(id = R.string.no_element_found)
            )
    }
}

@Composable
fun SectionHeader(
    text: String
) {
    Text(
        text = text,
        modifier = Modifier.fillMaxWidth(),
        fontWeight = FontWeight.Bold,
        style = androidx.compose.ui.text.TextStyle(
            color = LightBackground,
            fontSize = 16.sp
        )
    )

}