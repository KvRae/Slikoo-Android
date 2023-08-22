package slikoo.kvrae.slikoo.ui.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import slikoo.kvrae.slikoo.R
import slikoo.kvrae.slikoo.ui.fragments.event.EventFinalFragment
import slikoo.kvrae.slikoo.ui.fragments.event.EventFirstFragment
import slikoo.kvrae.slikoo.ui.fragments.event.EventSecondFragment
import slikoo.kvrae.slikoo.ui.theme.LightSecondary


@Composable
fun EventScreen(
    title: String = stringResource(R.string.home),
    onBackPress: (String) -> Unit,
    navController: NavController
) {
    var fragment by remember {
        mutableStateOf("first")
    }

    var date by remember {
        mutableStateOf("")
    }

    val themes = listOf("Theme 1", "THeme 2", "Theme 3")

    var time by remember {
        mutableStateOf("Heure")
    }


    val localistaion by remember {
        mutableStateOf("localisation")
    }
    var typeInvitation by remember {
        mutableStateOf("type invitation")
    }
    var theme by remember {
        mutableStateOf("theme")
    }
    var preferenceCulinaire by remember {
        mutableStateOf("preference culinaire")
    }
    val norriture by remember {
        mutableStateOf(" nourriture")
    }
    var nombrePersonnes by remember {
        mutableStateOf("nombre personnes")
    }
    var prix by remember {
        mutableStateOf("prix")
    }


    var description by remember {
        mutableStateOf("description")
    }
    var image by remember {
        mutableStateOf("")
    }


    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
            .background(color = LightSecondary),
        contentAlignment = Alignment.TopCenter
    ) {

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Top
        )
        {
            when (fragment) {
                stringResource(R.string.first) -> EventFirstFragment { fragment = it }
                stringResource(R.string.second) -> {
                    EventSecondFragment({ fragment = it }, navController = navController)

                }
                stringResource(R.string.third) -> {
                    EventFinalFragment({ fragment = it }, navController = navController)

                }
                else -> EventFirstFragment { fragment = it }
            }

        }
    }
}
