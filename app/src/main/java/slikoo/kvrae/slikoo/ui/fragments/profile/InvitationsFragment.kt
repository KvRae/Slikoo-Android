package slikoo.kvrae.slikoo.ui.fragments.profile

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import slikoo.kvrae.slikoo.R


@Composable
fun InvitationsFragment() {
    LazyColumn(
        content = {
            items(1) {
                ReservationCard(
                    title = "Invité",
                    subtitle = "Hamza Ben Mamoud",
                    description = "Je vous Invite à mon repas",
                    dissmissText = stringResource(R.string.delete),
                    onDismiss = { /*TODO*/ },
                    confirmText = stringResource(R.string.accept),
                    onConfirm = { /*TODO*/ }
                )
            }
        }
    )
}