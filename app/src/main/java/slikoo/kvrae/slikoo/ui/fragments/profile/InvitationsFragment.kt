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
import slikoo.kvrae.slikoo.viewmodels.InvitationsViewModel


@SuppressLint("SuspiciousIndentation")
@Composable
fun InvitationsFragment() {
    val viewModel: InvitationsViewModel = viewModel()

    if (viewModel.invitations.isNullOrEmpty())
    DisposableEffect(Unit) {
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
        if (!viewModel.invitations.isNullOrEmpty()) {
            LazyColumn(
                content = {
                    items(viewModel.invitations.size) { index ->
                        ReservationCard(
                            title = "Invité",
                            subtitle = viewModel.invitations[index].userDemander?.nom
                                ?: "utilisateur inconnu",
                            description = viewModel.invitations[index].motif ?: "pas de motif",
                            dissmissText = stringResource(R.string.delete),
                            onDismiss = { /* TODO */ },
                            confirmText = stringResource(R.string.accept),
                            onConfirm = { /* TODO */ }
                        )
                    }
                }
            )
        } else TextElementScreen(text = stringResource(R.string.no_invits_text))

    }
    if (viewModel.isLoading)  LoadingScreen()
}
