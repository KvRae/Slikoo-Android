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
import slikoo.kvrae.slikoo.ui.components.DescriptionTextField
import slikoo.kvrae.slikoo.ui.components.ExpandableCard
import slikoo.kvrae.slikoo.ui.theme.LightSurface
import slikoo.kvrae.slikoo.viewmodels.MealsViewModel


@Composable
fun EventFirstFragment(mealsViewModel: MealsViewModel,
                       onFragmentChange: (String) -> Unit,
) {



    val pplLabel = stringResource(R.string.ppl_nbr)
    val invitationTypes = listOf("Anniversaire", "Mariage", "Soirée", "Fête", "Réunion", "Autre")
    val themes = listOf("Theme 1", "THeme 2", "Theme 3")
    val preferences = listOf("Halal", "Casher", "Végétarien", "Végétalien", "Autre")
    val genres = listOf("Famille", "Amis", "Collègues", "Entre fille", "Entre mecs", "Autre")


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


        CustomTextField(onChange = {mealsViewModel.meal.value = mealsViewModel.meal.value.copy(nbr = it)}, value= mealsViewModel.meal.value.nbr,
            label = pplLabel, leadingIcon = Icons.Rounded.Person,
            keyboardType = KeyboardType.Number
        )

        ExpandableCard(items = invitationTypes, title = mealsViewModel.meal.value.lettre.toString(),
            onTitleChange = { mealsViewModel.meal.value.lettre = it},

            leadingIcon = ImageVector.vectorResource(id = R.drawable.person_add)
        )

        ExpandableCard(items = themes , title = mealsViewModel.meal.value.theme ,
            onTitleChange = { mealsViewModel.meal.value = mealsViewModel.meal.value.copy(theme = it)}, leadingIcon = ImageVector.vectorResource(id = R.drawable.category)
        )

        ExpandableCard(items = preferences , title = mealsViewModel.meal.value.genrenourriture ,
            onTitleChange = { mealsViewModel.meal.value= mealsViewModel.meal.value.copy(genrenourriture = it)}, leadingIcon = ImageVector.vectorResource(id = R.drawable.fastfood)
        )

        ExpandableCard(items = genres , title = mealsViewModel.meal.value.genre ,
            onTitleChange = { mealsViewModel.meal.value = mealsViewModel.meal.value.copy(genre = it)},
            leadingIcon = Icons.Rounded.Face
        )

        DescriptionTextField(
            onChange = {mealsViewModel.meal.value = mealsViewModel.meal.value.copy(description = it)},
            value = mealsViewModel.meal.value.description ,
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