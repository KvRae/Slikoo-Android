package slikoo.kvrae.slikoo.ui.fragments.profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import slikoo.kvrae.slikoo.R
import slikoo.kvrae.slikoo.ui.theme.LightBackground
import slikoo.kvrae.slikoo.ui.theme.LightPrimary


@Composable
fun BioFragment() {
    Column(modifier = Modifier.padding(4.dp)) {
        BioHeaderSection(
            icon = R.drawable.fork_knife_icon,
            title = stringResource(R.string.allergies_alimentaires),
            description = stringResource(id = R.string.welcome_sub_description)
        )
        BioHeaderSection(
            icon = R.drawable.heart_icon,
            title = stringResource(R.string.centres_d_interet),
            description = stringResource(id = R.string.welcome_sub_description)
        )
        BioHeaderSection(
            icon = R.drawable.language_icon,
            title = stringResource(R.string.languages),
            description = stringResource(id = R.string.welcome_sub_description)
        )
        BioDescriptionSection()
        BioHeaderSection(
            icon = R.drawable.ellipse_22,
            title = stringResource(R.string.plus_sur_moi),
            description = stringResource(id = R.string.welcome_sub_description)
        )
        SocialMediaSection()


    }
}

@Composable
fun BioHeaderSection(icon: Int, title: String, description: String = "null") {
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
        Spacer(modifier = Modifier.padding(4.dp))
        Text(
            text = description,
            color = Color.Gray,
            fontSize = 12.sp,
            fontWeight = FontWeight.Medium,
            overflow = TextOverflow.Ellipsis,
            maxLines = 2,


            )
        Divider(
            modifier = Modifier
                .padding(4.dp)
                .fillMaxWidth()
        )
    }
}

data class UserDescriptionItem(
    val icon: Int,
    val title: String,
    val description: String = "null"
)

@Composable
fun BioDescriptionSection() {
    val userDescriptionList = arrayListOf<UserDescriptionItem>(
        UserDescriptionItem(
            icon = R.drawable.cigarette_icon,
            title = stringResource(R.string.fumeur),
            description = "oui"
        ),
        UserDescriptionItem(
            icon = R.drawable.cup_icon,
            title = stringResource(R.string.alcohol),
            description = "oui"
        ),
        UserDescriptionItem(
            icon = R.drawable.person_search,
            title = stringResource(R.string.cherche_plus),
            description = "oui"
        ),
        UserDescriptionItem(
            icon = R.drawable.two_ppl_icon,
            title = stringResource(R.string.genre_recherche),
            description = "Femmes"
        ),
    )
    Column(modifier = Modifier.fillMaxWidth()) {
        LazyVerticalGrid(
            modifier = Modifier
                .fillMaxWidth()
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
                        fontWeight = FontWeight.Bold
                    )

                    Text(
                        text = userDescriptionList[it].description,
                        color = Color.Gray,
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
fun SocialMediaSection() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(
            onClick = { /*TODO*/ })
        {
            Icon(
                painter = painterResource(id = R.drawable.facebook_icon),
                contentDescription = "Facebook",
                tint = LightPrimary
            )

        }
        IconButton(onClick = { /*TODO*/ })
        {
            Icon(
                painter = painterResource(id = R.drawable.instagram_icon),
                contentDescription = "Instagram",
            )
            //tint = LightPrimary)
        }
        IconButton(onClick = { /*TODO*/ })
        {
            Icon(
                painter = painterResource(id = R.drawable.linkedin_icon),
                contentDescription = "LinkedIn",
                tint = LightPrimary
            )
        }
        IconButton(onClick = { /*TODO*/ })
        {
            Icon(
                painter = painterResource(id = R.drawable.twitter_icon),
                contentDescription = "Twitter",
                tint = LightPrimary
            )
        }
    }
}

