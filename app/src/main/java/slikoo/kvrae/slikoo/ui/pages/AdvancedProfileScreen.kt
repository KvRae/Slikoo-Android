package slikoo.kvrae.slikoo.ui.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import slikoo.kvrae.slikoo.R
import slikoo.kvrae.slikoo.ui.components.CustomButton
import slikoo.kvrae.slikoo.ui.components.CustomTextField
import slikoo.kvrae.slikoo.ui.components.ExpandableCard
import slikoo.kvrae.slikoo.ui.components.LoadingDialog
import slikoo.kvrae.slikoo.ui.components.MultiChoiceExpendableCard
import slikoo.kvrae.slikoo.ui.fragments.profile.makeToast
import slikoo.kvrae.slikoo.ui.theme.LightSecondary
import slikoo.kvrae.slikoo.viewmodels.UserDetailsViewModel


@Composable
fun AdvancedProfileScreen(
    navController: NavController,
    id: Int = 0
) {
    val viewModel: UserDetailsViewModel = viewModel()

    if (id != 0) {
        DisposableEffect(key1 = id) {
            viewModel.getUserDetails(id)
            onDispose { }
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .navigationBarsPadding()
            .background(color = LightSecondary)
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        EditProfileTopBar(
            navController = navController,
            title = stringResource(id = R.string.advanced_profile)
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .navigationBarsPadding()
                .verticalScroll(rememberScrollState())
                .padding(top = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            ExpandableCard(
                items = viewModel.choices,
                placeholder = "Fumeur",
                value = viewModel.userDetails.fumeur ?: "",
                label = stringResource(R.string.fumer),
                onTitleChange = { viewModel.userDetails = viewModel.userDetails.copy(fumeur = it) },
                leadingIcon = ImageVector.vectorResource(id = R.drawable.cigarette_icon)
            )
            ExpandableCard(
                items = viewModel.choices,
                label = stringResource(R.string.alcoholic),
                value = viewModel.userDetails.alcohol ?: "",
                placeholder = stringResource(R.string.alcoholic),
                onTitleChange = {
                    viewModel.userDetails = viewModel.userDetails.copy(alcohol = it)
                },
                leadingIcon = ImageVector.vectorResource(id = R.drawable.cup_icon)
            )
            MultiChoiceExpendableCard(
                items = viewModel.foodAlergies,
                onTitleChange = {
                    viewModel.onFoodAlergiesChange(
                        it
                    )
                },
                value = viewModel.userDetails.algalimentaire.joinToString(),
                label = "Allergie alimentaire",
                leadingIcon = ImageVector.vectorResource(id = R.drawable.person_search)
            )
            MultiChoiceExpendableCard(
                items = viewModel.lookingFor,
                onTitleChange = { viewModel.onLookingForChange(it) },
                value = viewModel.userDetails.cherche.joinToString(),
                label = "Genre Recherché",
                leadingIcon = ImageVector.vectorResource(id = R.drawable.fork_knife_icon)
            )
            MultiChoiceExpendableCard(
                items = viewModel.languages,
                onTitleChange = { viewModel.onLanguagesChange(it)},
                value = viewModel.userDetails.langues.joinToString(),
                label = "Langues",
                leadingIcon = ImageVector.vectorResource(id = R.drawable.language_icon)
            )
            MultiChoiceExpendableCard(
                onTitleChange = { it -> viewModel.onAreaOfInterestChange(it) },
                items = viewModel.areaOfInterest,
                value = viewModel.userDetails.centreinteret.joinToString(),
                label = "Centres d'interet",
                leadingIcon = ImageVector.vectorResource(id = R.drawable.heart_icon)
            )
            MultiChoiceExpendableCard(
                items = viewModel.lookingPlus,
                onTitleChange = { viewModel.onLookingPlusChange(it) },
                value = viewModel.userDetails.chercherplus.joinToString(),
                label = "Vous cherchez plus",
                leadingIcon = ImageVector.vectorResource(id = R.drawable.person_add)
            )
            CustomTextField(
                onChange = {
                    viewModel.userDetails = viewModel.userDetails.copy(Facebooklink = it)
                },
                value = viewModel.userDetails.Facebooklink ?: "",
                label = "Facebook",
                placeHolder = "www.facebook.com/johnDOE",
                isError = !viewModel.onValidateFacebookLink(viewModel.userDetails.Facebooklink ?: ""),
                errorMessage = stringResource(id = R.string.invalid_link),
                leadingIcon = ImageVector.vectorResource(id = R.drawable.facebook_out_icon)
            )
            CustomTextField(
                onChange = { viewModel.userDetails = viewModel.userDetails.copy(TwitterLink = it) },
                value = viewModel.userDetails.TwitterLink ?: "",
                label = "X(Twitter)",
                isError = !viewModel.onValidateTwitterLink(viewModel.userDetails.TwitterLink ?: ""),
                errorMessage = stringResource(id = R.string.invalid_link),
                placeHolder = "www.twitter.com/johnDOE",
                leadingIcon = ImageVector.vectorResource(id = R.drawable.x_out_icon)
            )
            CustomTextField(
                onChange = {
                    viewModel.userDetails = viewModel.userDetails.copy(InstagramLink = it)
                },
                value = viewModel.userDetails.InstagramLink ?: "",
                isError = !viewModel.onValidateInstagramLink(viewModel.userDetails.InstagramLink ?: ""),
                errorMessage = stringResource(id = R.string.invalid_link),
                label = "Instagram",
                placeHolder = "www.instagram.com/johnDOE",
                leadingIcon = ImageVector.vectorResource(id = R.drawable.instagram_out_icon)
            )
            CustomTextField(
                onChange = {
                    viewModel.userDetails = viewModel.userDetails.copy(LinkedinLink = it)
                },
                value = viewModel.userDetails.LinkedinLink ?: "",
                label = "LinkedIn",
                isError = !viewModel.onValidateLinkedinLink(viewModel.userDetails.LinkedinLink ?: ""),
                errorMessage = stringResource(id = R.string.invalid_link),
                placeHolder = "www.linkedin.com/johnDOE",
                leadingIcon = ImageVector.vectorResource(id = R.drawable.linkedin_out_icon)
            )
            Spacer(modifier = Modifier.padding(16.dp))
            CustomButton(
                text = stringResource(id = R.string.save),
                onClick = {
                    viewModel.isError = false
                    if (viewModel.userDetails.idusermain!!.isNotEmpty()) {
                        viewModel.updateUserDetails(viewModel.userDetails)
                    } else {
                        viewModel.addUserDetails(viewModel.userDetails)
                    }
                },
            )

        }
    }
    if (viewModel.isError) {
        val toastMsg = stringResource(id = R.string.form_error_message)
        DisposableEffect(Unit) {
            makeToast(navController.context, toastMsg)
            onDispose {
                viewModel.isError = false
            }
        }
    }
    if (viewModel.navigate) {
        val toastMsg = stringResource(id = R.string.profile_updated)
        DisposableEffect(Unit) {
            navController.popBackStack()
            makeToast(navController.context, toastMsg)
            onDispose {
                viewModel.navigate = false
            }
        }
    }
    if (viewModel.isLoading) {
        LoadingDialog()
    }

}