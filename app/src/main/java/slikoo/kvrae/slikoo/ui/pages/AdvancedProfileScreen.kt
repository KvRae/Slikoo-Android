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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import slikoo.kvrae.slikoo.R
import slikoo.kvrae.slikoo.ui.components.CustomButton
import slikoo.kvrae.slikoo.ui.components.CustomTextField
import slikoo.kvrae.slikoo.ui.components.ExpandableCard
import slikoo.kvrae.slikoo.ui.theme.LightSecondary
import slikoo.kvrae.slikoo.viewmodels.UserDetailsViewModel

@Preview
@Composable
fun AdvancedProfileScreen() {
    val navController: NavController = NavController(context = androidx.compose.ui.platform.LocalContext.current)
    val viewModel: UserDetailsViewModel = viewModel()
    val choices = listOf("oui","non")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .verticalScroll(rememberScrollState())
            .navigationBarsPadding()
            .background(color = LightSecondary)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        EditProfileTopBar(navController = navController)
        ExpandableCard(items = choices,
            placeholder ="Fumeur" ,
            title = "",
            leadingIcon = ImageVector.vectorResource(id = R.drawable.cigarette_icon)
        )
        ExpandableCard(
            items = choices,
            title = "",
            placeholder = "Alcool",
            leadingIcon = ImageVector.vectorResource(id = R.drawable.cup_icon)
        )
        CustomTextField(
            onChange = {},
            value = "",
            label = "Allergie alimentaire",
            leadingIcon = ImageVector.vectorResource(id = R.drawable.fork_knife_icon)
        )
        CustomTextField(
            onChange = {},
            value = "",
            label = "Langues",
            leadingIcon = ImageVector.vectorResource(id = R.drawable.language_icon)
        )
        CustomTextField(
            onChange = {},
            value = "",
            label = "Centres d'interet",
            leadingIcon = ImageVector.vectorResource(id = R.drawable.heart_icon)
        )
        CustomTextField(onChange = {}, value = "", label = "Preférence culinaire" )
        CustomTextField(onChange = {},
            value = "",
            label = "Vous cherchez plus",
            leadingIcon = ImageVector.vectorResource(id = R.drawable.person_search)
        )
        CustomTextField(
            onChange = {},
            value = "",
            label ="Facebook",
            placeHolder = "www.facebook.com/johnDOE",
            leadingIcon =  ImageVector.vectorResource(id = R.drawable.facebook_out_icon)
        )
        CustomTextField(
            onChange = {},
            value = "",
            label ="X(Twitter)",
            placeHolder = "www.twitter.com/johnDOE",
            leadingIcon =  ImageVector.vectorResource(id = R.drawable.twitter_out_icon)
        )
        CustomTextField(
            onChange = {},
            value = "",
            label ="Instagram",
            placeHolder = "www.instagram.com/johnDOE",
            leadingIcon =  ImageVector.vectorResource(id = R.drawable.instagram_out_icon)
        )
        CustomTextField(
            onChange = {},
            value = "",
            label ="LinkedIn"
        )
        Spacer(modifier = Modifier.padding(16.dp))
        CustomButton(
            text = stringResource(id = R.string.save),
            onClick = {  },
        )



        
    }

}