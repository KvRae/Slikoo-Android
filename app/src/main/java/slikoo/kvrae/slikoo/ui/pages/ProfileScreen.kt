package slikoo.kvrae.slikoo.ui.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.IconButton
import androidx.compose.material.Surface
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import slikoo.kvrae.slikoo.R
import slikoo.kvrae.slikoo.data.datasources.entities.User
import slikoo.kvrae.slikoo.ui.components.CustomAlertDialog
import slikoo.kvrae.slikoo.ui.fragments.profile.BioFragment
import slikoo.kvrae.slikoo.ui.fragments.profile.InvitationsFragment
import slikoo.kvrae.slikoo.ui.fragments.profile.ReservationFragment
import slikoo.kvrae.slikoo.ui.fragments.profile.UserOffersList
import slikoo.kvrae.slikoo.ui.theme.LightBackground
import slikoo.kvrae.slikoo.ui.theme.LightError
import slikoo.kvrae.slikoo.ui.theme.LightPrimary
import slikoo.kvrae.slikoo.ui.theme.LightSecondary
import slikoo.kvrae.slikoo.utils.AppScreenNavigator
import slikoo.kvrae.slikoo.viewmodels.MainScreenViewModel


@Composable
fun ProfileScreen(navController: NavController) {
    val viewModel: MainScreenViewModel = viewModel()
    val bio = stringResource(id = R.string.biographie)
    var selectedMenuIndex by remember { mutableStateOf(bio) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .navigationBarsPadding()
            .background(LightSecondary)
    ) {
        // Profile Header
        ProfileHeader(navController = navController, user = viewModel.user.value)

        Spacer(modifier = Modifier.height(8.dp))

        // Profile Row Menu List
        ProfileRowMenuList(
            selectedMenuIndex = selectedMenuIndex,
            onMenuSelected = { selectedMenuIndex = it })

        Spacer(modifier = Modifier.height(8.dp))

        // Profile Content
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(shape = RoundedCornerShape(32.dp))
                .background(LightError)
                .padding(16.dp),
        ) {
            ProfileContent(selectedMenuIndex = selectedMenuIndex, navController = navController)
        }
    }
}

@Composable
fun ProfileAppBar(navController: NavController) {
    TopAppBar(
        modifier = Modifier.statusBarsPadding(),
        elevation = 0.dp,
        actions = {
            var showMenu by remember { mutableStateOf(false) }
            var showAlertDialog by remember { mutableStateOf(false) }
            IconButton(onClick = { showMenu = !showMenu }) {
                Icon(
                    imageVector = Icons.Default.MoreVert,
                    contentDescription = stringResource(R.string.settings),
                    tint = LightError
                )
                DropdownMenu(expanded = showMenu, onDismissRequest = { showMenu = false }) {
                    DropdownMenuItem(onClick = { showAlertDialog = true }) {
                        ItemMenu(
                            text = stringResource(R.string.report),
                            icon = Icons.Default.Warning
                        )
                    }
                    Divider()
                    DropdownMenuItem(onClick = { /* Handle settings! */ }) {
                        ItemMenu(
                            text = stringResource(R.string.settings),
                            icon = Icons.Default.Settings
                        )
                    }
                    Divider()
                    DropdownMenuItem(onClick = { /* Handle send feedback! */ }) {
                        ItemMenu(
                            text = stringResource(R.string.send_feedback),
                            icon = Icons.Default.Send
                        )
                    }
                }
                CustomAlertDialog(
                    showDialog = showAlertDialog,
                    onDismiss = { showAlertDialog = false },
                    onConfirm = { showAlertDialog = false })
            }
        },
        title = {},
        backgroundColor = Color.Transparent,
        navigationIcon = {
            IconButton(onClick = {
                navController.popBackStack()
                navController.navigate(AppScreenNavigator.MainAppScreen.route)

            }) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = stringResource(R.string.previous),
                    tint = LightError
                )
            }
        },
    )
}

@Composable
fun ProfileHeader(navController: NavController ,user: User) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(LightSecondary)
    ) {
        AsyncImage(
            model = user.avatarbannerUrl+user.avatarbanner,
            contentDescription = stringResource(R.string.profile_cover_picture),
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp),
            contentScale = ContentScale.Crop
        )
        UserProfileInfo( user = user)
        // Profile Picture
        AsyncImage(
            model = user.avatarUrl+user.avatar,
            contentDescription = stringResource(R.string.profile_pic),
            onLoading = { },
            modifier = Modifier
                .padding(start = 26.dp, top = 100.dp)
                .size(100.dp)
                .clip(shape = CircleShape),
            contentScale = ContentScale.Crop
        )
        // TopAppBar
        ProfileAppBar(navController = navController)

    }
}

@Composable
fun UserProfileInfo(user : User = User()) {
    Surface(
        Modifier
            .fillMaxWidth()
            .padding(top = 150.dp),
        shape = RoundedCornerShape(bottomStart = 16.dp, bottomEnd = 24.dp),
        elevation = 8.dp,
    ) {

        Column {
            Spacer(modifier = Modifier.height(50.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth(0.4f)

                ) {
                    Text(
                        text = user.nom + " " + user.prenom,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                    )
                    Text(
                        text = user.email,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        fontSize = 11.sp,

                        fontWeight = FontWeight.Normal,
                        modifier = Modifier.padding(top = 4.dp)

                    )
                    Text(
                        text = user.adressepostal,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                        lineHeight = 15.sp,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Normal,
                        modifier = Modifier.padding(top = 4.dp)

                    )
                    Text(
                        text = user.numtel,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Normal,
                        modifier = Modifier.padding(top = 4.dp)
                    )
                }

                Divider(
                    color = Color.Gray.copy(alpha = 0.4f),
                    modifier = Modifier
                        .height(100.dp)
                        .width(1.dp)

                )
                Spacer(modifier = Modifier.width(8.dp))

                Column(
                    modifier = Modifier
                        .fillMaxWidth(0.9f)
                ) {
                    Text(
                        text = stringResource(R.string.about),
                        maxLines = 1,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                    )
                    Text(
                        text = user.description,
                        maxLines = 5,
                        letterSpacing = 0.5.sp,
                        lineHeight = 15.sp,
                        overflow = TextOverflow.Ellipsis,
                        fontSize = 11.sp,
                        modifier = Modifier.padding(top = 4.dp)

                    )
                }
            }
        }
    }
}

@Composable
fun ProfileRowMenuList(
    selectedMenuIndex: String = stringResource(R.string.biographie),
    onMenuSelected: (String) -> Unit = {}
) {
    val menuList = listOf(
        stringResource(R.string.biographie),
        stringResource(R.string.mes_offres),
        stringResource(R.string.invitations),
        stringResource(R.string.reservations)
    )
    LazyRow(content = {
        items(menuList.size) {
            Surface(
                modifier = Modifier
                    .padding(8.dp)
                    .clip(shape = RoundedCornerShape(16.dp))
                    .clickable(onClick = { onMenuSelected(menuList[it]) }),
                elevation = 8.dp,
                color = if (selectedMenuIndex == menuList[it]) LightPrimary else LightError
            ) {
                Text(
                    text = menuList[it],
                    modifier = Modifier.padding(10.dp),
                    overflow = TextOverflow.Ellipsis,
                    color = if (selectedMenuIndex == menuList[it]) LightError else LightBackground,
                    maxLines = 1,
                    fontWeight = FontWeight.Medium,
                    fontSize = 14.sp,
                )
            }
        }

    })

}

@Composable
fun ProfileContent(selectedMenuIndex: String, navController: NavController) {
    when (selectedMenuIndex) {
        stringResource(id = R.string.biographie) -> {
            BioFragment()
        }

        stringResource(id = R.string.mes_offres) -> {
            UserOffersList(navController = navController)
        }

        stringResource(id = R.string.invitations) -> {
            InvitationsFragment()
        }

        stringResource(id = R.string.reservations) -> {
            ReservationFragment()
        }
    }
}

@Composable
fun ItemMenu(text: String, icon: ImageVector) {
    Row {
        Icon(
            imageVector = icon,
            contentDescription = stringResource(R.string.refresh),
            tint = LightBackground,
            modifier = Modifier.size(18.dp)
        )
        Text(
            text = text,
            modifier = Modifier.padding(start = 4.dp),
            fontSize = 14.sp,
            color = LightBackground,
            fontWeight = FontWeight.Bold,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1
        )
    }
}

