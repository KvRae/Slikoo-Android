package slikoo.kvrae.slikoo.ui.fragments.profile

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
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
import slikoo.kvrae.slikoo.ui.pages.TextElementScreen
import slikoo.kvrae.slikoo.ui.pages.TextWithImageScreen
import slikoo.kvrae.slikoo.ui.theme.LightBackground
import slikoo.kvrae.slikoo.ui.theme.LightError
import slikoo.kvrae.slikoo.ui.theme.LightGreen
import slikoo.kvrae.slikoo.ui.theme.LightGrey
import slikoo.kvrae.slikoo.ui.theme.LightRed
import slikoo.kvrae.slikoo.ui.theme.LightYellow
import slikoo.kvrae.slikoo.viewmodels.ReservationViewModel

@SuppressLint("SuspiciousIndentation")
@Composable
fun ReservationFragment(
    navController: NavController
) {
    val viewModel: ReservationViewModel = viewModel()

    DisposableEffect(key1 = viewModel.reservations) {
        viewModel.getAllReservations()
        onDispose {}
    }


    Box(
        modifier = Modifier
            .fillMaxSize(1f)
    ) {
        if (viewModel.reservations.isNotEmpty())
            SwipeRefresh(
                state = rememberSwipeRefreshState(
                    isRefreshing = viewModel.isLoading
                ),
                onRefresh = {
                    viewModel.getAllReservations()
                }
            ) {
                LazyColumn() {
                    items(viewModel.reservations.size) { index ->

                        ReservationCard(
                            navController = navController,
                            title = stringResource(R.string.organiser),
                            subtitle = "${viewModel.reservations[index].user?.nom ?: ""} " +
                                    (viewModel.reservations[index].user?.prenom ?: ""),
                            idUser = viewModel.reservations[index].user?.id ?: 0,
                            mealAvatar =
                            viewModel.reservations[index].meal?.avatarUrl
                                .plus(viewModel.reservations[index].meal?.avatar),
                            offreText = viewModel.reservations[index].status ?: "",
                            description = viewModel.reservations[index].motif ?: "",
                            status = viewModel.reservations[index].status ?: "",
                            dissmissText = stringResource(R.string.cancel_pay),
                            onDismiss = {
                                viewModel.declineReservation(
                                    idMeal = viewModel.reservations[index].meal?.id ?: 0,
                                )
                            },
                            confirmText = stringResource(R.string.pay),
                            onConfirm = { /*TODO*/ },
                        )

                    }
                }
            }
        if (viewModel.reservations.isEmpty())
            TextWithImageScreen(
                text = stringResource(id = R.string.no_reservations),
                imageVector = ImageVector.vectorResource(id = R.drawable.no_drinks),
                background = LightError
            )
        if (viewModel.isLoading) LoadingScreen(
            background = LightError
        )
        if (viewModel.isError) {
            TextElementScreen(text = "Something went wrong")
        }
    }
}


@Composable
fun ReservationCard(
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
        "Canceled" -> LightRed
        else -> LightBackground
    }
    val offerValue = when (offreText) {
        "Pending" -> stringResource(R.string.pending)
        "Accepted" -> stringResource(R.string.accepted)
        "Rejected" -> stringResource(R.string.rejected)
        "Canceled" -> stringResource(R.string.canceled)
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
                    verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
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
                    text = offerValue,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    color = offerColor
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
                    "Accepted" -> Row {
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

                    "Pending" -> Row(
                        modifier = Modifier
                            .fillMaxWidth(1f),
                        horizontalArrangement = Arrangement.Center
                    ) {
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
                    }

                    "Rejected" -> Row(
                        modifier = Modifier
                            .fillMaxWidth(1f),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = stringResource(R.string.reservation_rejected),
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