package slikoo.kvrae.slikoo.ui.fragments.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Done
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import slikoo.kvrae.slikoo.R
import slikoo.kvrae.slikoo.data.datasources.entities.Feedback
import slikoo.kvrae.slikoo.ui.components.CustomButton
import slikoo.kvrae.slikoo.ui.components.CustomTextField
import slikoo.kvrae.slikoo.ui.components.FeedbackRatingBar
import slikoo.kvrae.slikoo.ui.components.MealCardWrapper
import slikoo.kvrae.slikoo.ui.pages.LoadingScreen
import slikoo.kvrae.slikoo.ui.pages.TextWithImageScreen
import slikoo.kvrae.slikoo.ui.theme.LightBackground
import slikoo.kvrae.slikoo.ui.theme.LightError
import slikoo.kvrae.slikoo.ui.theme.LightGreen
import slikoo.kvrae.slikoo.ui.theme.LightPrimary
import slikoo.kvrae.slikoo.ui.theme.LightSecondary
import slikoo.kvrae.slikoo.viewmodels.FeedbackViewModel

@Composable
fun FeedbackFragment(navController: NavController) {
    val viewModel: FeedbackViewModel = viewModel()

    DisposableEffect(key1 = viewModel.invitations, key2 = viewModel.reservations) {
        viewModel.getFeedbacks()
        onDispose {
            viewModel.isLoading = false
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
            if (!(viewModel.reservations.size <= 0 && viewModel.reservations.isEmpty()))
                MealsSection(
                    vm = viewModel
                )
            if (!(viewModel.invitations.size <= 0 && viewModel.invitations.isEmpty()))
                InvitationsSection(
                    vm = viewModel
                )



        }
        if (viewModel.reservations.isEmpty() && viewModel.invitations.isEmpty())
            TextWithImageScreen(
                imageVector = ImageVector.vectorResource(id = R.drawable.no_speaker),
                text = stringResource(id = R.string.no_feedback),
                background = LightError
            )
        if (viewModel.isLoading)
            LoadingScreen(
                background = LightError
            )
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MealsSection(
    vm: FeedbackViewModel
) {
    val isExpanded = remember {
        mutableStateOf(false)
    }
    val idMeal = remember {
        mutableStateOf(0)
    }
    val idUser = remember {
        mutableStateOf(0)
    }
    val image = remember {
        mutableStateOf("")
    }
    val title = remember {
        mutableStateOf("")
    }

    Column {
        SectionHeader(text = stringResource(id = slikoo.kvrae.slikoo.R.string.meals))
        Spacer(modifier = Modifier.height(16.dp))
        LazyRow(
            content = {
                items(vm.reservations.size) {
                    MealCardWrapper(
                        title =
                        stringResource(id = R.string.by) +
                                (vm.reservations[it].user?.nom
                                    ?: "") + " " +
                                (vm.reservations[it].user?.prenom
                            ?: ""),
                        image = (vm.reservations[it].meal?.avatarUrl.plus(vm.reservations[it].meal?.avatar)),
                        color = if (vm.verifyFeedbackSubmitted(
                                vm.reservations[it].user?.id ?: 0,
                                vm.reservations[it].meal?.id ?: 0
                            )
                        )
                            LightGreen
                        else
                            LightPrimary,
                        icon = if (vm.verifyFeedbackSubmitted(
                                vm.reservations[it].user?.id ?: 0,
                                vm.reservations[it].meal?.id ?: 0
                            )
                        )
                            Icons.Rounded.Done
                        else
                            Icons.Rounded.Add,
                        onClick = {
                            isExpanded.value = true
                            idMeal.value = vm.reservations[it].meal?.id ?: 0
                            idUser.value = vm.reservations[it].user?.id ?: 0
                            image.value = (vm.reservations[it].meal?.avatarUrl.plus(vm.reservations[it].meal?.avatar))
                            title.value = (vm.reservations[it].user?.nom ?: "") + " " + (vm.reservations[it].user?.prenom ?: "")

                        },
                    )
                }
            }
        )
    }
    if (isExpanded.value){
        ModalBottomSheet(
            modifier = Modifier
                .fillMaxSize(),
            containerColor = LightSecondary,
            sheetState = rememberModalBottomSheetState(),
            onDismissRequest = { isExpanded.value = false },
            content = {
                FeedbackContent(
                    viewModel = vm,
                    idMeal = idMeal.value,
                    idUser = idUser.value,
                    image = image.value,
                    title = title.value,
                    onClick = {
                        isExpanded.value = false
                    }

                )
            }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InvitationsSection(
    vm: FeedbackViewModel
) {

    val isExpanded = remember {
        mutableStateOf(false)
    }
    val idMeal = remember {
        mutableStateOf(0)
    }
    val idUser = remember {
        mutableStateOf(0)
    }
    val image = remember {
        mutableStateOf("")
    }
    val title = remember {
        mutableStateOf("")
    }

    Column {
        SectionHeader(text = stringResource(id = slikoo.kvrae.slikoo.R.string.invitations))
        Spacer(modifier = Modifier.height(8.dp))
        LazyRow(
            content = {
                items(vm.invitations.size) {
                    MealCardWrapper(
                        title = (vm.invitations[it].userDemander?.nom
                            ?: "") + " " + (vm.invitations[it].userDemander?.prenom
                            ?: ""),
                        image = (vm.invitations[it].userDemander?.avatarUrl.plus(vm.invitations[it].userDemander?.avatar)),
                        color = if (vm.verifyFeedbackSubmitted(
                                vm.invitations[it].userDemander?.id ?: 0,
                                vm.invitations[it].meal?.id ?: 0
                            )
                        )
                            LightGreen
                        else
                            LightPrimary,
                        icon = if (vm.verifyFeedbackSubmitted(
                                vm.invitations[it].userDemander?.id ?: 0,
                                vm.invitations[it].meal?.id ?: 0)
                            )
                            Icons.Rounded.Done
                        else
                            Icons.Rounded.Add,
                        onClick = {
                            isExpanded.value = true
                            idMeal.value = vm.invitations[it].meal?.id ?: 0
                            idUser.value = vm.invitations[it].userDemander?.id ?: 0
                            image.value = (vm.invitations[it].userDemander?.avatarUrl.plus(vm.invitations[it].userDemander?.avatar))
                            title.value = (vm.invitations[it].userDemander?.nom ?: "") + " " + (vm.invitations[it].userDemander?.prenom ?: "")
                           }
                    )
                }

            }

        )
    }
    if (isExpanded.value){
        ModalBottomSheet(
            modifier = Modifier
                .fillMaxSize(),
            sheetState = rememberModalBottomSheetState(),
            onDismissRequest = { isExpanded.value = false },
            content = {
                FeedbackContent(
                    viewModel = vm,
                    idMeal = idMeal.value,
                    idUser = idUser.value,
                    image = image.value,
                    title = title.value,
                    onClick = {
                        isExpanded.value = false
                    }
                )
            }
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

@Composable
fun FeedbackContent(
    viewModel: FeedbackViewModel,
    idMeal: Int,
    idUser: Int,
    image: String,
    title: String,
    onClick: () -> Unit = {}
) {
    val feedback = remember {
        mutableStateOf(Feedback())
    }

    DisposableEffect(Unit){
        feedback.value = viewModel.getMyFeedbackByUserMeal(
            idUser = idUser,
            idMeal = idMeal
        )
        onDispose { }
    }
    Surface(
        modifier = Modifier
            .background(color = LightSecondary)
                .fillMaxSize(),
        shape = RoundedCornerShape(16.dp),
        elevation = 4.dp,
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(1f)
                .background(color = LightSecondary)
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Surface(
                modifier = Modifier
                    .size(100.dp)
                    .background(color = LightSecondary),
                shape = CircleShape,
                elevation = 8.dp

            ) {
                AsyncImage(
                    model = image,
                    contentDescription = "Image",
                    contentScale = ContentScale.Crop,
                )
            }
            Spacer(modifier = Modifier.size(8.dp))
            androidx.compose.material.Text(
                text = title,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.size(16.dp))

            Column(
                modifier = Modifier.fillMaxWidth().padding(8.dp),
                horizontalAlignment = Alignment.Start,

            ) {
                androidx.compose.material.Text(
                    text = stringResource(R.string.your_feedback),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.size(4.dp))
                FeedbackRatingBar(
                    iconsSize = 24,
                    currentRating = feedback.value.rate,
                    onRatingChanged = {
                        if (!viewModel.verifyFeedbackSubmitted(
                                idUser= idUser, idMeal =  idMeal))
                            feedback.value = feedback.value.copy(rate = it)
                    }
                )
            }
            Spacer(modifier = Modifier.size(8.dp))
            CustomTextField(
                onChange = {
                    if (!viewModel.verifyFeedbackSubmitted(idUser, idMeal))
                        feedback.value = feedback.value.copy(comment = it)
                },
                leadingIcon = ImageVector.vectorResource(id = R.drawable.feedback),
                value = feedback.value.comment,
                label = stringResource(R.string.comment),
                readOnly = viewModel.verifyFeedbackSubmitted(idUser, idMeal)
            )
            Spacer(modifier = Modifier.size(12.dp))
            if (idMeal != 0 && idUser != 0 &&
                !viewModel.verifyFeedbackSubmitted(idUser, idMeal)
            )
                CustomButton(
                    text = stringResource(id = R.string.submit),
                    onClick = {
                        viewModel.addFeedback(
                            idReciver = idUser,
                            idMeal = idMeal,
                            comment = feedback.value.comment,
                            rate = feedback.value.rate
                        )
                        onClick()
                    }
                )
        }
    }

}