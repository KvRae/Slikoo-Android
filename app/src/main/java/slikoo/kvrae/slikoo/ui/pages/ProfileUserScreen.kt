package slikoo.kvrae.slikoo.ui.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import slikoo.kvrae.slikoo.R
import slikoo.kvrae.slikoo.ui.fragments.profile.BioFragment
import slikoo.kvrae.slikoo.ui.theme.LightError
import slikoo.kvrae.slikoo.ui.theme.LightSecondary
import slikoo.kvrae.slikoo.viewmodels.UserProfileViewModel

@Composable
fun UserProfileScreen(
    navController: NavController,
    id : Int = 0
) {
    val viewModel: UserProfileViewModel = viewModel()

    if (id != 0) {
        DisposableEffect(Unit) {
           viewModel.getUserById(id)
            onDispose { }
        }
    }
    if (viewModel.user.id > 0)
        Column(
            modifier = Modifier
                .fillMaxSize()
                .navigationBarsPadding()
                .background(LightSecondary)
        ) {
            // Profile Header
            ProfileHeader(navController = navController, user = viewModel.user)
            Spacer(modifier = Modifier.height(8.dp))
            // Profile Content
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(shape = RoundedCornerShape(32.dp))
                    .background(LightError)
                    .padding(16.dp),
            ) {

                BioFragment(user = viewModel.user)
                Spacer(modifier = Modifier.weight(1f))
            }
        }
    if (viewModel.user.id == 0)
        TextElementScreen(
            backgound = LightSecondary,
            text = stringResource(R.string.no_user_text)
        )
    if (viewModel.isLoading) LoadingScreen()
}