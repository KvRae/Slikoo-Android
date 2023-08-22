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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
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
import slikoo.kvrae.slikoo.ui.components.ExpandableCard
import slikoo.kvrae.slikoo.ui.components.TimePicker
import slikoo.kvrae.slikoo.ui.theme.LightSurface


@Composable
fun EventSecondFragment(onFragmentChange: (String) -> Unit,
                        navController: NavController,
) {

    val locations = listOf("Lyon", "Paris", "Marseille", "Toulouse", "Bordeaux", "Dijon", "Lille", "Nantes", "Nice", "Strasbourg")
    var location by rememberSaveable { mutableStateOf("Lieu") }

    val context = LocalContext.current
    var price by remember {
        mutableStateOf("")
    }
    var time by remember {
        mutableStateOf("")
    }
    var date by remember {
        mutableStateOf("")
    }



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

        ExpandableCard(items = locations  ,
            title = location,
            onTitleChange = { location = it},
            leadingIcon = Icons.Rounded.LocationOn )

        CustomTextField(
            onChange = {price = "$it.00" } ,
            value = price,
            label = stringResource(id = R.string.price),
            leadingIcon = ImageVector.vectorResource(id = R.drawable.round_euro),
            keyboardType = KeyboardType.Number
        )
        TimePicker(time = time , onTimeChange ={time = it} )

        DatePicker(date = date, onDateChange = {date = it})




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