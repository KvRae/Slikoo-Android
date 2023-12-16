package slikoo.kvrae.slikoo.ui.fragments.event

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.LocationOn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import slikoo.kvrae.slikoo.R
import slikoo.kvrae.slikoo.ui.components.CustomButton
import slikoo.kvrae.slikoo.ui.components.CustomSlidingBar
import slikoo.kvrae.slikoo.ui.components.CustomTextField
import slikoo.kvrae.slikoo.ui.components.DatePicker
import slikoo.kvrae.slikoo.ui.components.TimePicker
import slikoo.kvrae.slikoo.ui.theme.LightSurface
import slikoo.kvrae.slikoo.viewmodels.MealsViewModel


@Composable
fun EventSecondFragment(onFragmentChange: (String) -> Unit,
                        navController: NavController,
                        mealsViewModel: MealsViewModel,
) {

    val context = LocalContext.current

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        CustomSlidingBar(sliderPosition = 1f)
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(text = stringResource(R.string.step_two_event), color = LightSurface)
        }

        Spacer(modifier = Modifier.height(32.dp))

        CustomTextField(
            value =     mealsViewModel.meal.value.localisation,
            onChange = { mealsViewModel.meal.value = mealsViewModel.meal.value.copy(localisation = it)},
            label = stringResource(id = R.string.location),
            leadingIcon = Icons.Rounded.LocationOn)

        CustomTextField(
            onChange = {mealsViewModel.meal.value = mealsViewModel.meal.value.copy(prix = it) } ,
            value = mealsViewModel.meal.value.prix,
            label = stringResource(id = R.string.price),
            leadingIcon = ImageVector.vectorResource(id = R.drawable.round_euro),
            keyboardType = KeyboardType.Number
        )

        CustomTextField(
            onChange = {mealsViewModel.meal.value = mealsViewModel.meal.value.copy(type = it) } ,
            value = mealsViewModel.meal.value.type,
            label = stringResource(id = R.string.type),
            leadingIcon = ImageVector.vectorResource(id = R.drawable.cup_icon),
            keyboardType = KeyboardType.Text
        )

        TimePicker(
            time = mealsViewModel.meal.value.heure ,
            onTimeChange ={mealsViewModel.meal.value = mealsViewModel.meal.value.copy(heure = it)}
        )

        DatePicker(date = mealsViewModel.meal.value.date,
            onDateChange = {mealsViewModel.meal.value = mealsViewModel.meal.value.copy(date = it)}
        )

        CustomButton(text = stringResource(id = R.string.next)) {
            onFragmentChange(context.getString(R.string.third))
        }
        TextButton(onClick = { onFragmentChange(context.getString(R.string.first)) }) {
            Text(
                text = stringResource(id = R.string.previous),
                color = LightSurface
            )
        }

    }

}