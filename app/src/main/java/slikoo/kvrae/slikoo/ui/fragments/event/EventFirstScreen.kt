package slikoo.kvrae.slikoo.ui.fragments.event

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Face
import androidx.compose.material.icons.rounded.Info
import androidx.compose.material.icons.rounded.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import slikoo.kvrae.slikoo.R
import slikoo.kvrae.slikoo.ui.components.CustomButton
import slikoo.kvrae.slikoo.ui.components.CustomSlidingBar
import slikoo.kvrae.slikoo.ui.components.CustomTextField
import slikoo.kvrae.slikoo.ui.components.ExpandableCard
import slikoo.kvrae.slikoo.ui.theme.LightSurface


@Composable
fun EventFirstFragment(onFragmentChange: (String) -> Unit) {

    var description by remember { mutableStateOf("") }

    val pplLabel = stringResource(R.string.ppl_nbr)
    var peopleNbr by rememberSaveable { mutableStateOf("") }

    val invitationTypes = listOf("Anniversaire", "Mariage", "Soirée", "Fête", "Réunion", "Autre")
    var invitationType by rememberSaveable { mutableStateOf("Type d'invitation") }

    val themes = listOf("Theme 1", "THeme 2", "Theme 3")
    var theme by rememberSaveable { mutableStateOf("Thème ou sujet ") }

    val preferences = listOf("Halal", "Casher", "Végétarien", "Végétalien", "Autre")
    var preference by rememberSaveable { mutableStateOf("Préférence Culinaire") }

    val genres = listOf("Famille", "Amis", "Collègues", "Entre fille", "Entre mecs", "Autre")
    var genre by rememberSaveable { mutableStateOf("Genre") }


    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        CustomSlidingBar(sliderPosition = 0f)

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start
        ) {
            Text(text = stringResource(R.string.step_one_event), color = LightSurface)
        }

        Spacer(modifier = Modifier.height(32.dp))


        CustomTextField(onChange = {peopleNbr = it}, value= peopleNbr,
            label = pplLabel, leadingIcon = Icons.Rounded.Person,
            keyboardType = KeyboardType.Number
        )

        ExpandableCard(items = invitationTypes, title = invitationType,
            onTitleChange = { invitationType = it}, leadingIcon = ImageVector.vectorResource(id = R.drawable.person_add)
        )

        ExpandableCard(items = themes , title = theme ,
            onTitleChange = { theme = it}, leadingIcon = ImageVector.vectorResource(id = R.drawable.category)
        )

        ExpandableCard(items = preferences , title = preference ,
            onTitleChange = { preference = it}, leadingIcon = ImageVector.vectorResource(id = R.drawable.fastfood)
        )

        ExpandableCard(items = genres , title = genre ,
            onTitleChange = { genre = it}, leadingIcon = Icons.Rounded.Face
        )

        CustomTextField(
            onChange = {description = it} ,
            value = description ,
            label = stringResource(id = R.string.description_event),
            leadingIcon = Icons.Rounded.Info,
            keyboardType = KeyboardType.Text
        )

        Spacer(modifier = Modifier.height(16.dp))

        CustomButton(text = stringResource(id = R.string.next)) {
            onFragmentChange("second")
        }




    }

}