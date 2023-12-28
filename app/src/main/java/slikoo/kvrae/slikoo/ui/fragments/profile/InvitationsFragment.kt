package slikoo.kvrae.slikoo.ui.fragments.profile

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material.icons.rounded.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import slikoo.kvrae.slikoo.R
import slikoo.kvrae.slikoo.ui.pages.LoadingScreen
import slikoo.kvrae.slikoo.ui.pages.TextWithImageScreen
import slikoo.kvrae.slikoo.ui.theme.LightBackground
import slikoo.kvrae.slikoo.ui.theme.LightError
import slikoo.kvrae.slikoo.ui.theme.LightGreen
import slikoo.kvrae.slikoo.ui.theme.LightGrey
import slikoo.kvrae.slikoo.ui.theme.LightRed
import slikoo.kvrae.slikoo.ui.theme.LightYellow
import slikoo.kvrae.slikoo.viewmodels.InvitationsViewModel



@Composable
fun InvitationsFragment(
    navController: NavController
) {
    val viewModel: InvitationsViewModel = viewModel()
    DisposableEffect(viewModel.invitations) {
        viewModel.getInvitations()
        onDispose {
            viewModel.isLoading = false
        }
    }

    Box(
        modifier = Modifier
            .fillMaxWidth(1f)
            .fillMaxHeight(1f),
        contentAlignment = Alignment.TopCenter
    ) {
        if (viewModel.invitations.size > 0 && viewModel.invitations.isNotEmpty()) {
            SwipeRefresh(
                state =  rememberSwipeRefreshState(isRefreshing = viewModel.isLoading),
                onRefresh = { viewModel.getInvitations() }
            ) {
                LazyColumn(
                    content = {
                        items(viewModel.invitations.size) { index ->
                            if (viewModel.invitations[index].meal != null){
                                InvitationCard(
                                    title = "Invité",
                                    status = viewModel.invitations[index].status ?: "inconnu",
                                    subtitle = (viewModel.invitations[index].userDemander?.nom +
                                            " " + viewModel.invitations[index].userDemander?.prenom),
                                    mealAvatar = viewModel.invitations[index].meal?.avatarUrl
                                        .plus(viewModel.invitations[index].meal?.avatar),
                                    description = viewModel.invitations[index].motif ?: "pas de motif",
                                    dissmissText = stringResource(R.string.delete),
                                    onDismiss = {
                                        viewModel.declineInvitation(
                                            idMeal = viewModel.invitations[index].meal?.id ?: 0,
                                            idDemander = viewModel.invitations[index].userDemander?.id
                                                ?: 0,
                                        )
                                    },
                                    confirmText = stringResource(R.string.accept),
                                    navController = navController,
                                    onConfirm = {
                                        viewModel.acceptInvitation(
                                            idMeal = viewModel.invitations[index].meal?.id.toString()
                                                ?: "",
                                            informationComp = "",
                                            idDemander = viewModel.invitations[index].userDemander?.id.toString()
                                                ?: ""
                                        )
                                    },
                                    idUser = viewModel.invitations[index].userDemander?.id ?: 0,
                                )
                            }
                        }
                    }
                )
            }
        } else
            TextWithImageScreen(
                imageVector = ImageVector.vectorResource(id = R.drawable.no_meals),
                background = LightError,
                text = stringResource(R.string.no_invits_text))

    }
    if (viewModel.isLoading)  LoadingScreen(background = LightError)
}

@Composable
fun InvitationCard(
    title: String = "",
    subtitle: String = "",
    offreText: String = "",
    status: String = "",
    description: String = "",
    dissmissText: String,
    idUser: Int = 0,
    mealAvatar: String = "",
    onDismiss: () -> Unit,
    confirmText: String,
    navController: NavController,
    onConfirm: () -> Unit
) {
    val offerColor = when (offreText) {
        "Pending" -> LightYellow
        "Accepted" -> LightGreen
        "Rejected" -> LightRed
        else -> LightBackground
    }
    val offerValue = when (offreText) {
        "Pending" -> stringResource(R.string.pending)
        "Accepted" -> stringResource(R.string.accepted)
        "Rejected" -> stringResource(R.string.rejected)
        else -> ""
    }
    Card(
        modifier = Modifier
            .padding(8.dp)
            .height(210.dp)
            .fillMaxWidth(),
        elevation = 8.dp,
        shape = RoundedCornerShape(16.dp)
    ) {
        Row {
            AsyncImage(
                model = mealAvatar,
                contentDescription = "image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth(0.3f)
                    .fillMaxHeight(1f)
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth(1f)
                    .padding(8.dp)
                    .fillMaxHeight(1f),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(1f),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = title,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.weight(1f))

                    Text(text = stringResource(id = R.string.see_profile),
                        fontSize = 12.sp,
                        color = LightYellow,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .clickable { navController.navigate("user_profile_screen/${idUser}") }
                            .height(30.dp)
                            .fillMaxWidth(0.3f)

                    )

                }

                Text(
                    text = subtitle,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium
                )

                Text(
                    text = "Motif",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium
                )
                Text(
                    text = description,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium
                )
                Divider(Modifier.padding(8.dp), LightGrey, thickness = 0.5.dp)
                when (status) {
                    "Pending" -> Row {
                        OutlinedButton(
                            onClick = { onDismiss() },
                            shape = RoundedCornerShape(16.dp),
                            border = BorderStroke(1.dp, LightRed),
                            colors = ButtonDefaults.outlinedButtonColors(
                                contentColor = LightRed,
                            )
                        ) {
                            Icon(imageVector = Icons.Rounded.Close, contentDescription = "")
                            Text(
                                text = dissmissText,
                                style = TextStyle(
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 12.sp
                                )
                            )
                        }
                        Spacer(modifier = Modifier.weight(1f))
                        OutlinedButton(
                            onClick = { onConfirm() },
                            shape = RoundedCornerShape(16.dp),
                            border = BorderStroke(1.dp, LightGreen),
                            colors = ButtonDefaults.outlinedButtonColors(
                                contentColor = LightGreen,
                            )
                        ) {
                            Icon(imageVector = Icons.Rounded.Check, contentDescription = "")
                            Text(
                                text = confirmText,
                                style = TextStyle(
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 12.sp
                                )
                            )
                        }
                    }

                    "Accepted" -> Row(
                        modifier = Modifier
                            .fillMaxWidth(1f),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = stringResource(R.string.invitation_accepted),
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Medium,
                            color = LightGreen
                        )
                    }

                    "Rejected" -> Row(
                        modifier = Modifier
                            .fillMaxWidth(1f),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = stringResource(R.string.invitation_rejected),
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Medium,
                            color = LightRed
                        )
                    }
                    else -> Row {}
                }

            }

        }
    }
}
