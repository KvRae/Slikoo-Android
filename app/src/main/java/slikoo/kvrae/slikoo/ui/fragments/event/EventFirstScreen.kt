package slikoo.kvrae.slikoo.ui.fragments.event

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.Info
import androidx.compose.material.icons.rounded.List
import androidx.compose.material.icons.rounded.LocationOn
import androidx.compose.material.icons.rounded.ThumbUp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import slikoo.kvrae.slikoo.R
import slikoo.kvrae.slikoo.ui.components.CustomButton
import slikoo.kvrae.slikoo.ui.components.CustomSlider
import slikoo.kvrae.slikoo.ui.components.ExpandableCard


@Composable
fun EventFirstFragment(onFragmentChange: (String) -> Unit) {
    val items = listOf("item1", "item2", "item3", "item4", "item5")
    var item by remember {
        mutableStateOf("item")
    }
    val themes = listOf("theme1", "theme2", "theme3", "theme4", "theme5")
    var theme by remember {
        mutableStateOf("theme")
    }
    val types = listOf("type1", "type2", "type3", "type4", "type5")
    var type by remember {
        mutableStateOf("type")
    }
    val places = listOf("place1", "place2", "place3", "place4", "place5")
    var place by remember {
        mutableStateOf("place")
    }
    val dates = listOf("date1", "date2", "date3", "date4", "date5")
    var date by remember {
        mutableStateOf("date")
    }
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CustomSlider(maxSlide = 2, currentSlide = 1)
        ExpandableCard(
            items = items,
            title = item,
            onTitleChange = { item = it },
            leadingIcon = Icons.Rounded.Info
        )
        ExpandableCard(
            items = themes,
            title = theme,
            onTitleChange = { theme = it },
            leadingIcon = Icons.Rounded.ThumbUp
        )
        ExpandableCard(
            items = types,
            title = type,
            onTitleChange = { type = it },
            leadingIcon = Icons.Rounded.List
        )
        ExpandableCard(
            items = places,
            title = place,
            onTitleChange = { place = it },
            leadingIcon = Icons.Rounded.LocationOn
        )
        ExpandableCard(
            items = dates,
            title = date,
            onTitleChange = { date = it },
            leadingIcon = Icons.Rounded.Close
        )
        CustomButton(text = stringResource(id = R.string.next)) {
            onFragmentChange("second")
        }
    }

}