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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import slikoo.kvrae.slikoo.R
import slikoo.kvrae.slikoo.ui.theme.ButtonsAndIcons
import slikoo.kvrae.slikoo.ui.theme.ScreenBackground
import slikoo.kvrae.slikoo.utils.SignUpNavigation

@Preview
@Composable
fun SignUpPreview() {
    val context = androidx.compose.ui.platform.LocalContext.current
    SignUp(navController = NavController(context))
}


@Composable
fun SignUp(navController: NavController) {
    val signUpNavController = rememberNavController()
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
                .background(color = ButtonsAndIcons.copy(alpha = 0.8f))) {
        }
        Row(modifier = Modifier.fillMaxWidth()) {
            IconButton(onClick = { navController.popBackStack()

            }) {
                Icon(imageVector = Icons.Rounded.ArrowBack,
                    contentDescription = "Back",
                    tint = ScreenBackground)
            }
        }

        Column {
            SignUpHeader()

            Box(
                modifier = Modifier
                    .clip(shape = RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp))
                    .background(color = ScreenBackground)
                    .border(
                        width = 1.dp,
                        color = ScreenBackground,
                        shape = RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp)
                    )

            ) {
                SignUpNavigation(navController = signUpNavController)
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
