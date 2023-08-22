package slikoo.kvrae.slikoo.ui.fragments.event

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import slikoo.kvrae.slikoo.R
import slikoo.kvrae.slikoo.ui.components.CustomButton
import slikoo.kvrae.slikoo.ui.components.CustomSlidingBar
import slikoo.kvrae.slikoo.ui.components.ImagePickerField
import slikoo.kvrae.slikoo.ui.theme.LightSurface
import slikoo.kvrae.slikoo.utils.AppScreenNavigator


@Composable
fun EventFinalFragment(onFragmentChange: (String) -> Unit, navController: NavController) {
    val context = LocalContext.current
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        CustomSlidingBar(sliderPosition = 2f)
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            Text(text = stringResource(R.string.step_three_event), color = LightSurface)
        }

        Spacer(modifier = Modifier.height(32.dp))

        ImagePickerField()

        CustomButton(text = stringResource(id = R.string.finish)) {
            navController.popBackStack()
            navController.navigate(AppScreenNavigator.MainAppScreen.route)
        }
        TextButton(onClick = { onFragmentChange(context.getString(R.string.second)) }) {
            Text(
                text = stringResource(id = R.string.previous),
                color = LightSurface
            )
        }

    }
}