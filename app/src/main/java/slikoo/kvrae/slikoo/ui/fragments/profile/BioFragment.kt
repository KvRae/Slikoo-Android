package slikoo.kvrae.slikoo.ui.fragments.profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import slikoo.kvrae.slikoo.R
import slikoo.kvrae.slikoo.data.datasources.entities.User
import slikoo.kvrae.slikoo.data.datasources.entities.UserDetails
import slikoo.kvrae.slikoo.ui.components.UserRatingBar
import slikoo.kvrae.slikoo.ui.pages.TextElementScreen
import slikoo.kvrae.slikoo.ui.theme.LightBackground
import slikoo.kvrae.slikoo.ui.theme.LightError
import slikoo.kvrae.slikoo.ui.theme.LightGrey
import slikoo.kvrae.slikoo.ui.theme.LightPrimary
import slikoo.kvrae.slikoo.viewmodels.UserDetailsViewModel


@Composable
fun BioFragment(user: User) {

    val viewModel: UserDetailsViewModel = viewModel()
    if (user.Hasdetails) {
        DisposableEffect(Unit) {
            viewModel.getUserDetails(id = user.id)
            viewModel.getFeedbacks(id = user.id)
            onDispose {}
        }
    }

    if (user.Hasdetails && viewModel.userDetails.value.id != 0)
        Column(
            modifier = Modifier
                .fillMaxSize(1f)
                .padding(4.dp)
                .verticalScroll(
                    enabled = true,
                    state = rememberScrollState()
                )
        ) {
            SocialMediaSection(
                userDetail = viewModel.userDetails.value
            )
            Spacer(modifier = Modifier.padding(16.dp))
            Divider(
                modifier = Modifier
                    .padding(4.dp)
                    .fillMaxWidth()
            )
            BioHeaderSection(
                icon = R.drawable.fork_knife_icon,
                title = stringResource(R.string.allergies_alimentaires),
                description = viewModel.userDetails.value.algalimentaire.joinToString { it }
            )
            Divider(
                modifier = Modifier
                    .padding(4.dp)
                    .fillMaxWidth()
            )
            BioHeaderSection(
                icon = R.drawable.heart_icon,
                title = stringResource(R.string.centres_d_interet),
                description = viewModel.userDetails.value.centreinteret.joinToString { it }
            )
            Divider(
                modifier = Modifier
                    .padding(4.dp)
                    .fillMaxWidth()
            )
            BioHeaderSection(
                icon = R.drawable.language_icon,
                title = stringResource(R.string.languages),
                description = viewModel.userDetails.value.langues.joinToString { it }
            )
            Divider(
                modifier = Modifier
                    .padding(4.dp)
                    .fillMaxWidth()
            )
            BioDescriptionSection(userDetail = viewModel.userDetails.value)
            BioHeaderSection(
                icon = R.drawable.feedback,
                title = stringResource(R.string.comments),
            )




        }
    else TextElementScreen(
        backgound = LightError,
        text = stringResource(id = R.string.no_description)
    )
}

@Composable
fun BioHeaderSection(
    icon: Int,
                     title: String,
                     description: String = "",
    vm : UserDetailsViewModel = viewModel()
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                painter = painterResource(id = icon),
                contentDescription = "Favorite",
                tint = LightPrimary,
                modifier = Modifier.size(16.dp)
            )
            Spacer(modifier = Modifier.padding(4.dp))
            Text(
                text = title,
                color = LightBackground,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold
            )
        }
        if (description.isNotEmpty()) {
            Spacer(modifier = Modifier.padding(4.dp))
            Text(
                text = description,
                color = Color.Gray,
                fontSize = 12.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                fontWeight = FontWeight.Medium
            )
        }

        if (title == stringResource(id = R.string.comments)) {
            Spacer(modifier = Modifier.padding(4.dp))
            Column {
                if (vm.feedbacks.isNotEmpty()) {
                    vm.feedbacks.forEach {
                        UserRatingBar(feedBack = it)
                        Spacer(modifier = Modifier.padding(4.dp))
                    }
                } else TextElementScreen(
                    backgound = LightError,
                    text = stringResource(id = R.string.no_feedback)
                )
            }
        }
    }
}

data class UserDescriptionItem(
    val icon: Int,
    val title: String,
    val description: String = "null"
)

@Composable
fun BioDescriptionSection(userDetail: UserDetails) {
    val userDescriptionList = arrayListOf(
        UserDescriptionItem(
            icon = R.drawable.cigarette_icon,
            title = stringResource(R.string.fumeur),
            description = userDetail.fumeur?:""
        ),
        UserDescriptionItem(
            icon = R.drawable.cup_icon,
            title = stringResource(R.string.alcohol),
            description = userDetail.alcohol?:""
        ),
        UserDescriptionItem(
            icon = R.drawable.person_search,
            title = stringResource(R.string.cherche_plus),
            description = userDetail.chercherplus.joinToString { it }
        ),
        UserDescriptionItem(
            icon = R.drawable.two_ppl_icon,
            title = stringResource(R.string.genre_recherche),
            description = userDetail.cherche.joinToString { it }
        ),
    )
    Column(modifier = Modifier.fillMaxWidth()) {
        LazyVerticalGrid(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .padding(4.dp),
            userScrollEnabled = false,
            columns = GridCells.Adaptive(minSize = 150.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            items(userDescriptionList.size) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                ) {
                    Icon(
                        painterResource(id = userDescriptionList[it].icon),
                        contentDescription = "Favorite",
                        tint = LightPrimary,
                        modifier = Modifier.size(16.dp)
                    )
                    Text(
                        text = userDescriptionList[it].title,
                        color = LightBackground,
                        fontSize = 12.sp,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        fontWeight = FontWeight.Bold
                    )

                    Text(
                        text = userDescriptionList[it].description,
                        color = LightGrey,
                        fontSize = 10.sp,
                        maxLines = 1,
                        fontWeight = FontWeight.Medium
                    )
                }
            }

        }
        Divider(
            modifier = Modifier
                .padding(4.dp)
                .fillMaxWidth()
        )
    }
}

@Composable
fun SocialMediaSection(
    userDetail: UserDetails
) {
    val uriHandler = LocalUriHandler.current

    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(
            onClick = {
                try {
                    uriHandler.openUri(userDetail.facebooklink?:"https://www.facebook.com/")
                }
                catch (e : Exception) {
                    uriHandler.openUri("https://www.facebook.com/")
                }

            })
        {
            Icon(
                painter = painterResource(id = R.drawable.facebook_icon),
                contentDescription = "Facebook",
                tint = LightPrimary,
                modifier = Modifier.size(32.dp)

            )

        }
        IconButton(onClick = {
            try {
                uriHandler.openUri(userDetail.InstagramLink?:"https://www.instagram.com/")
            }
            catch (e : Exception) {
                e.printStackTrace()
                uriHandler.openUri("https://www.instagram.com/")
            }
        })
        {
            Icon(
                painter = painterResource(id = R.drawable.instagram_icon),
                contentDescription = "Instagram",
                tint = LightPrimary,
                modifier = Modifier.size(32.dp)
            )

        }
        IconButton(onClick = {
            try {
                uriHandler.openUri(userDetail.LinkedinLink?:"https://www.linkedin.com/")
            }
            catch (e : Exception) {
                e.printStackTrace()
                uriHandler.openUri("https://www.linkedin.com/")
            }
        } )
        {
            Icon(
                painter = painterResource(id = R.drawable.linkedin_icon),
                contentDescription = "LinkedIn",
                tint = LightPrimary,
                modifier = Modifier.size(32.dp)
            )
        }
        IconButton(onClick = {
            try {
                uriHandler.openUri(userDetail.TwitterLink?:"https://www.twitter.com/")
            }
            catch (e : Exception) {
                e.printStackTrace()
                uriHandler.openUri("https://www.twitter.com/")
            }
        })
        {
            Icon(
                painter = painterResource(id = R.drawable.twitter_icon),
                contentDescription = "Twitter",
                tint = LightPrimary,
                modifier = Modifier.size(32.dp)
            )
        }
    }
}

