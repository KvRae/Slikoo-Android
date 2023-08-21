package slikoo.kvrae.slikoo.ui.fragments.event

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import slikoo.kvrae.slikoo.R
import slikoo.kvrae.slikoo.ui.components.CustomButton
import slikoo.kvrae.slikoo.ui.components.CustomSlider
import slikoo.kvrae.slikoo.ui.components.ExpandableCard
import slikoo.kvrae.slikoo.ui.components.ImagePickerField
import slikoo.kvrae.slikoo.ui.theme.LightSurface
import slikoo.kvrae.slikoo.utils.AppScreenNavigator


@Composable
fun EventSecondFragment(onFragmentChange: (String) -> Unit, navController: NavController) {
    val items = listOf("item1", "item2", "item3", "item4", "item5")
    val item = "item"
    val themes = listOf("theme1", "theme2", "theme3", "theme4", "theme5")
    val theme = "theme"
    val types = listOf("type1", "type2", "type3", "type4", "type5")
    val type = "type"
    val places = listOf("place1", "place2", "place3", "place4", "place5")
    val place = "place"
    val dates = listOf("date1", "date2", "date3", "date4", "date5")
    val date = "date"
    Column(
        modifier = androidx.compose.ui.Modifier.fillMaxWidth(),
        horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally
    ) {
        CustomSlider(maxSlide = 2, currentSlide = 2)
        ExpandableCard(items = items, title = item)
        ExpandableCard(items = themes, title = theme)
        ExpandableCard(items = types, title = type)
        ExpandableCard(items = places, title = place)
        ExpandableCard(items = dates, title = date)
        ImagePickerField()
        CustomButton(text = stringResource(id = R.string.finish)) {
            navController.popBackStack()
            navController.navigate(AppScreenNavigator.MainAppScreen.route)
        }
        TextButton(onClick = { onFragmentChange("first") }) {
            Text(stringResource(id = R.string.previous), color = LightSurface)
        }

    }

}