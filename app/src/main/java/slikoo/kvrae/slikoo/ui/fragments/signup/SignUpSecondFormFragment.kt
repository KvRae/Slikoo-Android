package slikoo.kvrae.slikoo.ui.fragments.signup

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Info
import androidx.compose.material.icons.rounded.LocationOn
import androidx.compose.material.icons.rounded.Phone
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import slikoo.kvrae.slikoo.R
import slikoo.kvrae.slikoo.ui.components.CustomButton
import slikoo.kvrae.slikoo.ui.components.CustomSlider
import slikoo.kvrae.slikoo.ui.components.CustomTextField
import slikoo.kvrae.slikoo.ui.theme.LightSurface
import slikoo.kvrae.slikoo.utils.SignUpNavigator
import slikoo.kvrae.slikoo.viewmodel.UserViewModel


@Composable
fun SignUpSecondForm(onChange: (String) -> Unit, userViewModel: UserViewModel) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.size(16.dp))
            Text(
                text = stringResource(id = R.string.signUp),
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.size(8.dp))
            CustomSlider(maxSlide = 4, currentSlide = 2)

            CustomTextField(
                onChange = {},
                value = "",
                label = stringResource(R.string.phone_number),
                keyboardType = KeyboardType.Phone,
                leadingIcon = Icons.Rounded.Phone
            )

            CustomTextField(
                onChange = {},
                value = "",
                label = stringResource(id = R.string.postal_code),
                keyboardType = KeyboardType.Number,
                leadingIcon = Icons.Rounded.LocationOn
            )

            CustomTextField(
                onChange = {},
                value = "",
                label = stringResource(R.string.profile_description),
                leadingIcon = Icons.Rounded.Info
            )
            Spacer(modifier = Modifier.padding(32.dp))

            CustomButton(text = stringResource(id = R.string.next),
                onClick = {
                    onChange(SignUpNavigator.SignUpIDCFragment.route)
                })

            TextButton(onClick = { onChange(SignUpNavigator.SignUpFormFragment.route) }) {
                Text(text = stringResource(id = R.string.previous), color = LightSurface)
            }

        }
    }
}