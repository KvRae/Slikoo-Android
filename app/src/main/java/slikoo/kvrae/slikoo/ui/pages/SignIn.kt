

package slikoo.kvrae.slikoo.ui.pages

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Email
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import slikoo.kvrae.slikoo.R
import slikoo.kvrae.slikoo.ui.components.CustomButton
import slikoo.kvrae.slikoo.ui.components.CustomTextField
import slikoo.kvrae.slikoo.ui.theme.DividerColor
import slikoo.kvrae.slikoo.ui.theme.ScreenBackground
import slikoo.kvrae.slikoo.ui.theme.SecondaryWhiteText


@Composable
fun LoginForm(navController: NavController) {
    val logo = if(isSystemInDarkTheme()) R.drawable.slikoo_white else R.drawable.slikoo_white
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val largeText = """Bienvenue dans notre temple des saveurs
        |et des affinités.
        |Connectez-vous dès maintenant 
        |sur Slikoo
    """.trimMargin()
    val subDescription = """
                Vivez une expérience culinaire 
                unique en rencontrant
                d'autres amateur de cuisinepassionés sur Slikoo
    """.trimIndent()

    Box(modifier = Modifier
        .fillMaxSize()
    ){
        Image(painter = painterResource(id = R.drawable.loginback),
            contentDescription ="",
            modifier = Modifier.fillMaxSize(),
        )
        Column(
            modifier = Modifier
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .height(300.dp),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.login), // Load the drawable resource
                    contentDescription = "Background",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.FillBounds
                )
                Column(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(id = logo), // Load the drawable resource
                        contentDescription = "Logo",
                        modifier = Modifier.size(100.dp)
                    )
                    Text(text = largeText, style = TextStyle(color = SecondaryWhiteText,
                        fontWeight = FontWeight.Bold))
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = subDescription, style = TextStyle(color = SecondaryWhiteText))

                }
            }
            Surface(
                color = Color.White,
                border = BorderStroke(1.dp, ScreenBackground),
                shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxSize()
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .clip(shape = RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp)),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Text(text = "Se connecter", style = MaterialTheme.typography.h5)
                    Spacer(modifier = Modifier.height(16.dp))
                    CustomTextField(onChange ={} , value = "" , label = "Email", leadingIcon = Icons.Rounded.Email,  )

                Spacer(modifier = Modifier.height(16.dp))

                CustomTextField(onChange = {}, value = "", label = "Mot de passe")

                Spacer(modifier = Modifier.height(16.dp))

                // Submit button
                CustomButton(text = "Se connecter", onClick = { onSubmit(navController) })
                Divider(
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth().clip(shape =MaterialTheme.shapes.medium),
                    color = DividerColor,
                    thickness = 1.dp
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    TextButton(onClick = { onNavigate(navController,"sign_up_screen") }) {
                        Text("S'inscrire")
                    }
                    TextButton(onClick = { onNavigate(navController,"forgot_password_screen") }) { Text("Mot de passe oublié ?") }
                }

            }
        }
        }

    }

}



fun onNavigate(navController: NavController, route: String) {
    navController.navigate(route)

}

fun onSubmit(navController: NavController) {
    navController.navigate("main_screen")

}

fun onValidate(username: String, password: String, navController: NavController) {
    println("username: $username")
    println("password: $password")
    onSubmit(navController)

}
