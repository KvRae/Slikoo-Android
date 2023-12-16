package slikoo.kvrae.slikoo.ui.fragments.profile

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import slikoo.kvrae.slikoo.R
import slikoo.kvrae.slikoo.ui.pages.LoadingScreen
import slikoo.kvrae.slikoo.ui.pages.TextElementScreen
import slikoo.kvrae.slikoo.ui.theme.LightError
import slikoo.kvrae.slikoo.viewmodels.InvitationsViewModel


@SuppressLint("SuspiciousIndentation")
@Composable
fun InvitationsFragment(
    navController: androidx.navigation.NavController
) {
    val viewModel: InvitationsViewModel = viewModel()

    if (viewModel.invitations.isEmpty())
    DisposableEffect(viewModel.invitations) {
        viewModel.getInvitations()

        onDispose {
            // Clean up resources if needed
        }
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(1f),
        verticalArrangement = Arrangement.Center
    ) {
        if (viewModel.invitations.isNotEmpty()) {
            LazyColumn(
                content = {
                    items(viewModel.invitations.size) { index ->
                        ReservationCard(
                            title = "Invité",
                            status = viewModel.invitations[index].status?: "inconnu",
                            subtitle = (viewModel.invitations[index].userDemander?.nom +
                                    " " + viewModel.invitations[index].userDemander?.prenom)
                            ,
                            mealAvatar = viewModel.invitations[index].meal?.avatarUrl
                                .plus(viewModel.invitations[index].meal?.avatar),
                            description = viewModel.invitations[index].motif ?: "pas de motif",
                            dissmissText = stringResource(R.string.delete),
                            onDismiss = {
                                viewModel.declineInvitation(
                                    idMeal = viewModel.invitations[index].meal?.id ?: 0,
                                    idDemander = viewModel.invitations[index].userDemander?.id ?: 0,
                                    )
                            },
                            confirmText = stringResource(R.string.accept),
                            navController = navController,
                            onConfirm = {
                                viewModel.acceptInvitation(
                                    idMeal = viewModel.invitations[index].meal?.id.toString() ?: "",
                                    informationComp = "",
                                    idDemander = viewModel.invitations[index].userDemander?.id.toString()
                                        ?: ""
                                )
                            },
                            idUser = viewModel.invitations[index].userDemander?.id ?: 0,
                        )
                    }
                }
            )
        } else TextElementScreen(backgound = LightError, text = stringResource(R.string.no_invits_text))

    }
    if (viewModel.isLoading)  LoadingScreen()
}
