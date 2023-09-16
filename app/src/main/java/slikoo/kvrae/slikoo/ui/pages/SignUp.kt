package slikoo.kvrae.slikoo.ui.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import slikoo.kvrae.slikoo.R
import slikoo.kvrae.slikoo.ui.fragments.signup.ProfilePictureSection
import slikoo.kvrae.slikoo.ui.fragments.signup.SignUpCidForm
import slikoo.kvrae.slikoo.ui.fragments.signup.SignUpForm
import slikoo.kvrae.slikoo.ui.fragments.signup.SignUpSecondForm
import slikoo.kvrae.slikoo.ui.theme.LightPrimary
import slikoo.kvrae.slikoo.ui.theme.LightSecondary
import slikoo.kvrae.slikoo.utils.SignUpNavigator
import slikoo.kvrae.slikoo.viewmodels.UserViewModel


@Composable
fun SignUp(navController: NavController) {
    val userViewModel : UserViewModel = viewModel()
    val route = remember {
        mutableStateOf(SignUpNavigator.SignUpFormFragment.route)
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.loginback),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
        Row(
            modifier = Modifier
                .fillMaxSize()
                .background(color = LightPrimary.copy(alpha = 0.8f))
        ) {
        }
        Row(modifier = Modifier
            .fillMaxWidth()
            .statusBarsPadding()) {
            IconButton(onClick = {
                navController.popBackStack()

            }) {
                Icon(
                    imageVector = Icons.Rounded.ArrowBack,
                    contentDescription = "Back",
                    tint = LightSecondary
                )
            }
        }

        Column {
            SignUpHeader()

            Box(
                modifier = Modifier
                    .clip(shape = RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp))
                    .background(color = LightSecondary)
                    .border(
                        width = 1.dp,
                        color = LightSecondary,
                        shape = RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp)
                    )

            ) {
                when (route.value) {
                    SignUpNavigator.SignUpFormFragment.route -> SignUpForm(onChange = {
                        route.value = it
                    } )

                    SignUpNavigator.SignUpSecondFormFragment.route -> SignUpSecondForm(onChange = {
                        route.value = it
                    })

                    SignUpNavigator.SignUpIDCFragment.route -> SignUpCidForm(onChange = {
                        route.value = it
                    })

                    SignUpNavigator.SignUpProfilePictureFragment.route -> ProfilePictureSection(
                        onChange = { route.value = it },
                        navController = navController,
                    )

                }
            }
        }
    }
}


@Composable
fun SignUpHeader() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp),
        contentAlignment = androidx.compose.ui.Alignment.Center

    ) {
        Image(
            painter = painterResource(id = R.drawable.slikoo_white),
            contentDescription = null,
            modifier = Modifier.size(150.dp)
        )

    }
}
