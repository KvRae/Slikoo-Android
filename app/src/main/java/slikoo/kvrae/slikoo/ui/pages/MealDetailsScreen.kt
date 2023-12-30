package slikoo.kvrae.slikoo.ui.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.LocationOn
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material.icons.rounded.Warning
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import slikoo.kvrae.slikoo.R
import slikoo.kvrae.slikoo.ui.components.CustomAlertDialog
import slikoo.kvrae.slikoo.ui.components.CustomButton
import slikoo.kvrae.slikoo.ui.components.DescriptionTextField
import slikoo.kvrae.slikoo.ui.fragments.profile.makeToast
import slikoo.kvrae.slikoo.ui.theme.LightBackground
import slikoo.kvrae.slikoo.ui.theme.LightError
import slikoo.kvrae.slikoo.ui.theme.LightPrimary
import slikoo.kvrae.slikoo.ui.theme.LightPrimaryVariant
import slikoo.kvrae.slikoo.ui.theme.LightSecondary
import slikoo.kvrae.slikoo.ui.theme.LightSurface
import slikoo.kvrae.slikoo.utils.AppScreenNavigator
import slikoo.kvrae.slikoo.utils.TempSession
import slikoo.kvrae.slikoo.viewmodels.MealsViewModel


@Composable
fun MealsDetailScreen(navController: NavController, id: Int) {
    val mealsViewModel: MealsViewModel = viewModel()
    var toastMsg by remember {
        mutableStateOf("")
    }
    if (id <= 0 || id.toString().isEmpty())
        DisposableEffect(Unit ){
            navController.navigateUp()
            onDispose {}
        }
    if (id != 0)
        DisposableEffect(Unit) {
            mealsViewModel.getMealById(id)
            mealsViewModel.checkIfParticipating(
                idrepas = id,
                iduser = TempSession.user.id
            )
            onDispose {
                mealsViewModel.isLoading.value = false
            }
        }

    if (mealsViewModel.meal.value.id != 0) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(LightSecondary)
        ) {
            // Heading Image
            AsyncImage(
                model = "https://slikoo.com/repasImgs/${mealsViewModel.meal.value.avatar}",
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(500.dp)
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(400.dp)
                    .background(Color.Black.copy(alpha = 0.3f))
            )

            //Content
            Column(modifier = Modifier.fillMaxWidth()) {
                // TopAppBar
                MealDetailHeader(navController = navController)
                // Event Details
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .statusBarsPadding()
                        .navigationBarsPadding()
                        .verticalScroll(rememberScrollState()),
                    //.padding(top = 100.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Top
                ) {
                    MealDetailHeading(
                        eventName = mealsViewModel.meal.value.type,
                        eventPlace = mealsViewModel.meal.value.localisation,
                        eventParticipants = mealsViewModel.meal.value.nbr
                    )

                    MealDetailContent(
                        mealsViewModel = mealsViewModel,
                        navController = navController
                    )
                }
            }
        }
    }
    if (mealsViewModel.isLoading.value) LoadingScreen()

    if (mealsViewModel.isDialogOpen && mealsViewModel.dialogContext == "delete") CustomAlertDialog(
        title = stringResource(id = R.string.delete),
        message = stringResource(id = R.string.delete_meal_description),
        confirmText = stringResource(id = R.string.yes),
        dismissText = stringResource(id = R.string.no),
        onDismiss = { mealsViewModel.isDialogOpen = false },
        onConfirm = {
            mealsViewModel.deleteMeal(mealsViewModel.meal.value.id)
            mealsViewModel.isDialogOpen = false
            toastMsg = "Repas supprimé"
        }
    )
    if (mealsViewModel.isDialogOpen && mealsViewModel.dialogContext == "book") CustomAlertDialog(
        title = stringResource(id = R.string.participate),
        message = stringResource(id = R.string.book_meal_description),
        confirmText = stringResource(id = R.string.yes),
        dismissText = stringResource(id = R.string.no),
        onDismiss = { mealsViewModel.isDialogOpen = false },
        onConfirm = {
            if (mealsViewModel.motif.isNotEmpty() && mealsViewModel.motif.length > 5) {
                mealsViewModel.participateMeal(
                    mealsViewModel.meal.value.id,
                    mealsViewModel.meal.value.iduser.toInt()
                )
                toastMsg = "Vous participez a cet evenement"
                mealsViewModel.isDialogOpen = false
            }
        }
    )
    if (mealsViewModel.navigate) {
        toastMsg = stringResource(id = R.string.meal_deleted)
        DisposableEffect(Unit) {
            makeToast(navController.context, toastMsg)
            navController.navigate(AppScreenNavigator.MainAppScreen.route) {
                popUpTo(AppScreenNavigator.MainAppScreen.route) {
                    inclusive = true
                }
            }
            onDispose {}
        }
    }
}

@Composable
fun MealDetailHeader(navController : NavController) {
    TopAppBar(
        title = {},
        navigationIcon = {
            IconButton(
                onClick = {
                    navController.popBackStack()
                },
                modifier = Modifier
                    .clip(shape = CircleShape)
                    .background(LightError)
            ) {
                Icon(
                    imageVector = Icons.Rounded.ArrowBack,
                    contentDescription = "ArrowBack",
                    tint = LightPrimary
                )
            }
        },
        backgroundColor = Color.Transparent,
        elevation = 0.dp,
        actions = {
            IconButton(
                onClick = {
                    makeToast(navController.context, "Report")
                },
                modifier = Modifier
                    .clip(shape = CircleShape)
                    .background(LightError)
            ) {
                Icon(
                    imageVector = Icons.Rounded.Warning,
                    contentDescription = "Report",
                    tint = LightPrimary
                )
            }
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .statusBarsPadding()
    )
}

@Composable
fun MealDetailHeading(
    eventName : String = "Event Name",
    eventPlace : String = "Event place",
    eventParticipants : String = "0"

) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = eventName,
            style = TextStyle(
                color = LightPrimaryVariant,
                fontWeight = FontWeight.Bold,
                fontSize = 32.sp,
            )
        )
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Rounded.LocationOn,
                contentDescription = "" ,
                modifier = Modifier.padding(end = 8.dp),
                tint = LightPrimaryVariant
            )
            Text(text = eventPlace,
                style = TextStyle(
                    color = LightPrimaryVariant,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                )
            )
            Spacer(modifier = Modifier.weight(1f))
            Text(text = eventParticipants,
                style = TextStyle(
                    color = LightPrimaryVariant,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                )
            )
            Icon(
                imageVector = Icons.Rounded.Person,
                contentDescription = "",
                tint = LightPrimaryVariant
            )
        }
    }
}

@Composable
fun MealDetailContent(mealsViewModel: MealsViewModel, navController: NavController) {
    Surface(
        modifier = Modifier
            .fillMaxSize(),
        shape = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp),
        color = LightSecondary
    ) {
        Column(
            Modifier
                .fillMaxSize()
                .fillMaxHeight()
                .padding(16.dp),

            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top

        ) {
            //Content Header
            ContentHeader(mealsViewModel = mealsViewModel)
            Divider(color = LightBackground, thickness = 0.5.dp)
            // Content Sub Header
            ContentSubHeader(mealsViewModel = mealsViewModel)
            ContentBody(mealsViewModel = mealsViewModel, navController = navController)
        }
    }
}

@Composable
fun ContentHeader(mealsViewModel: MealsViewModel) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(model = mealsViewModel.user.value.avatarUrl+mealsViewModel.user.value.avatar,
            contentDescription = "",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .width(50.dp)
                .height(50.dp)
                .clip(CircleShape)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column(
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = mealsViewModel.user.value.nom + " " + mealsViewModel.user.value.prenom,
                style = TextStyle(
                    color = LightBackground,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                )
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = mealsViewModel.dateConverter(mealsViewModel.meal.value.date,mealsViewModel.meal.value.heure),
                style = TextStyle(
                    color = LightBackground,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                )
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        Text(text = mealsViewModel.meal.value.prix + " £",
            style = TextStyle(
                color = LightBackground,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
            )
        )

    }

}

@Composable
fun ContentSubHeader(mealsViewModel: MealsViewModel) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp, top = 16.dp),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp, top = 16.dp),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            SubHeaderItemCard(
                title = "Genre Recherché",
                description = mealsViewModel.meal.value.genre
            )
            SubHeaderItemCard(
                title = "Type de nourriture",
                description = mealsViewModel.meal.value.genrenourriture
            )
            SubHeaderItemCard(
                title = "Type d'evenement",
                description = mealsViewModel.meal.value.theme
            )
        }

    }
}

@Composable
fun ContentBody(mealsViewModel: MealsViewModel, navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp, bottom = 16.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Top
    ) {
        Text(
            text = mealsViewModel.meal.value.description,
            style = TextStyle(
                color = LightBackground,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
            )
        )
        Spacer(modifier = Modifier.height(8.dp))
        when{
            mealsViewModel.isParticipating && mealsViewModel.meal.value.iduser != TempSession.user.id.toString() ->
                DetailsContentParticipation(mealsViewModel = mealsViewModel)

            mealsViewModel.meal.value.iduser != TempSession.user.id.toString() ->
                DetailsContentBodyWithTextField(viewModel = mealsViewModel, navController = navController)

            mealsViewModel.meal.value.iduser == TempSession.user.id.toString() ->
                DetailsContentBodyWithButtons(viewModel = mealsViewModel, navController = navController)
        }

    }
}

@Composable
fun DetailsContentBodyWithTextField(
    viewModel: MealsViewModel,
    navController: NavController
) {
    val msg = stringResource(id = R.string.advanced_prof_msg)
    val msg2 = stringResource(id = R.string.motif_user)
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp, bottom = 16.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Top
    ) {
        Text(
            text = stringResource(R.string.message_organisateur),
            style = TextStyle(
                color = LightBackground,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
            )
        )
        Spacer(modifier = Modifier.height(8.dp))
        DescriptionTextField(
            onChange = {
                viewModel.motif = it
            },
            value = viewModel.motif,
            placeHolder = stringResource(id = R.string.motif_user),
            errorMessage = if (viewModel.motif.length>5) stringResource(id = R.string.text_is_empty) else "",
            label = stringResource(R.string.motif_user)
        )
        Spacer(modifier = Modifier.height(16.dp))
        CustomButton(text = stringResource(id = R.string.book),
            onClick = {
                when{
                    !TempSession.user.Hasdetails -> {
                        makeToast(navController.context, msg)
                    }
                    viewModel.motif.length<5 -> {
                        makeToast(navController.context,msg2 )
                    }
                    else ->{
                        viewModel.dialogContext = "book"
                        viewModel.isDialogOpen = true
                    }
                }
            })
        Spacer(modifier = Modifier.navigationBarsPadding())
    }
}


@Composable
fun DetailsContentBodyWithButtons(
    viewModel: MealsViewModel,
    navController: NavController
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp, top = 16.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Button(
            colors = ButtonDefaults.buttonColors(
                backgroundColor = LightSurface,
                contentColor = LightPrimaryVariant
            ),
            onClick = {
                navController.navigate("edit_meal_screen/${viewModel.meal.value.id}")
            }
        ) {
            Text(
                text = stringResource(id = R.string.updateRecipe),
                style = TextStyle(
                    color = LightPrimaryVariant,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                ),
                modifier = Modifier.padding(top = 4.dp, bottom = 4.dp, start = 16.dp, end = 16.dp)
            )
        }

        Button(
            colors = ButtonDefaults.buttonColors(
                backgroundColor = LightPrimary,
                contentColor = LightPrimaryVariant
            ),
            onClick = {
                viewModel.dialogContext = "delete"
                viewModel.isDialogOpen = true
            }
        ) {
            Text(
                text = stringResource(id = R.string.delete),
                style = TextStyle(
                    color = LightPrimaryVariant,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                ),
                modifier = Modifier.padding(top = 4.dp, bottom = 4.dp, start = 16.dp, end = 16.dp)
            )
        }
    }

}

@Composable
fun DetailsContentParticipation(
    mealsViewModel: MealsViewModel
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp, bottom = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(R.string.participate_msg),
            style = TextStyle(
                color = LightBackground,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
            )
        )
    }

}

@Composable
fun SubHeaderItemCard(
    title: String = "",
    description: String = ""
) {
    Card(
        modifier = Modifier,
        shape = RoundedCornerShape(16.dp),
        backgroundColor = LightSecondary,
        elevation = 4.dp
    ) {
        Column(
            modifier = Modifier.padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = title,
                style = TextStyle(
                    color = LightPrimary,
                    fontWeight = FontWeight.Bold,
                    fontSize = 11.sp,
                )
            )
            Text(
                text = description,
                style = TextStyle(
                    color = LightBackground,
                    fontWeight = FontWeight.Bold,
                    fontSize = 12.sp,
                )
            )
        }
    }
}


