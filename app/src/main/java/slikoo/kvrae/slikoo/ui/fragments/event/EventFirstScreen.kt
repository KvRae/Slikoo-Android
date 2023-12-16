package slikoo.kvrae.slikoo.ui.fragments.event

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.unit.sp
import slikoo.kvrae.slikoo.R
import slikoo.kvrae.slikoo.ui.components.CustomButton
import slikoo.kvrae.slikoo.ui.components.CustomSlidingBar
import slikoo.kvrae.slikoo.ui.components.CustomTextField
import slikoo.kvrae.slikoo.ui.components.DescriptionTextField
import slikoo.kvrae.slikoo.ui.components.ExpandableCard
import slikoo.kvrae.slikoo.ui.theme.LightBackground
import slikoo.kvrae.slikoo.ui.theme.LightSurface
import slikoo.kvrae.slikoo.viewmodels.MealsViewModel


@Composable
fun EventFirstFragment(
    mealsViewModel: MealsViewModel,
    onFragmentChange: (String) -> Unit,
) {
    val invitationTypes = listOf(
        "Anniversaire",
        "Mariage",
        "Soirée", "Fête",
        "Réunion",
        "Autre"
    )
    val themes = listOf("Cocktail", "Dîner", "Déjeuner", "Brunch", "Goûter", "Autre")
    val preferences = listOf("Halal", "Casher", "Végétarien", "Végétalien", "Autre")
    val genres = listOf("Famille", "Amis", "Collègues", "Entre fille", "Entre mecs", "Autre")


    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState()),
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
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start
        ) {
            Text(
                modifier = Modifier.padding(top = 8.dp),
                text = stringResource(R.string.create_event_description),
                fontSize = 12.sp,
                color = LightBackground
            )
        }

        Spacer(modifier = Modifier.height(32.dp))


        CustomTextField(
            onChange = { mealsViewModel.meal.value = mealsViewModel.meal.value.copy(nbr = it) },
            value = mealsViewModel.meal.value.nbr,
            placeHolder = stringResource(id = R.string.ppl_nbr),
            label = stringResource(R.string.ppl_nbr),
            leadingIcon = Icons.Rounded.Person,
            keyboardType = KeyboardType.Number
        )



        ExpandableCard(
            items = themes,
            label = stringResource(id = R.string.theme_holder),
            value = mealsViewModel.meal.value.theme,
            placeholder = stringResource(id = R.string.theme_holder),
            onTitleChange = {
                mealsViewModel.meal.value = mealsViewModel.meal.value.copy(theme = it)
            },
            leadingIcon = ImageVector.vectorResource(id = R.drawable.category)
        )

        ExpandableCard(
            items = preferences, value = mealsViewModel.meal.value.genrenourriture,
            label = stringResource(id = R.string.food_pref_holder),
            placeholder = stringResource(id = R.string.food_pref_holder),
            onTitleChange = {
                mealsViewModel.meal.value = mealsViewModel.meal.value.copy(genrenourriture = it)
            }, leadingIcon = ImageVector.vectorResource(id = R.drawable.fastfood)
        )

        ExpandableCard(
            items = genres,
            label = stringResource(id = R.string.genre_holder),
            value = mealsViewModel.meal.value.genre,
            placeholder = stringResource(id = R.string.genre_holder),
            onTitleChange = {
                mealsViewModel.meal.value = mealsViewModel.meal.value.copy(genre = it)
            },
            leadingIcon = Icons.Rounded.Face
        )
        ExpandableCard(
            items = invitationTypes,
            value = mealsViewModel.meal.value.lettre,
            label = stringResource(id = R.string.invitation_type_holder),
            placeholder = stringResource(id = R.string.invitation_type_holder),
            onTitleChange = {
                mealsViewModel.meal.value = mealsViewModel.meal.value.copy(lettre = it)
            },
            leadingIcon = ImageVector.vectorResource(id = R.drawable.person_add)
        )

        DescriptionTextField(
            onChange = {
                mealsViewModel.meal.value = mealsViewModel.meal.value.copy(description = it)
            },
            value = mealsViewModel.meal.value.description,
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