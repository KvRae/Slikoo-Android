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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import slikoo.kvrae.slikoo.R
import slikoo.kvrae.slikoo.ui.components.CustomAlertDialog
import slikoo.kvrae.slikoo.ui.components.CustomButton
import slikoo.kvrae.slikoo.ui.components.CustomSlider
import slikoo.kvrae.slikoo.ui.components.CustomTextField
import slikoo.kvrae.slikoo.ui.components.DescriptionTextField
import slikoo.kvrae.slikoo.ui.theme.LightSurface
import slikoo.kvrae.slikoo.utils.SignUpNavigator
import slikoo.kvrae.slikoo.viewmodel.UserViewModel


@Composable
fun SignUpSecondForm(onChange: (String) -> Unit) {
    val userViewModel: UserViewModel = viewModel()
    var isError by remember{ mutableStateOf(false)}
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
                onChange = { userViewModel.user.value = userViewModel.user.value.copy(phone = it) },
                value = userViewModel.user.value.phone,
                label = stringResource(R.string.phone_number),
                keyboardType = KeyboardType.Phone,
                leadingIcon = Icons.Rounded.Phone
            )

            CustomTextField(
                onChange = { userViewModel.user.value= userViewModel.user.value.copy(postalCode = it) },
                value = userViewModel.user.value.postalCode,
                label = stringResource(id = R.string.postal_code),
                keyboardType = KeyboardType.Number,
                leadingIcon = Icons.Rounded.LocationOn
            )

            DescriptionTextField(
                onChange = { userViewModel.user.value = userViewModel.user.value.copy(about = it)},
                value = userViewModel.user.value.about,
                label = stringResource(R.string.profile_description),
                leadingIcon = Icons.Rounded.Info
            )
            Spacer(modifier = Modifier.padding(32.dp))

            CustomButton(text = stringResource(id = R.string.next),
                onClick = {
                    if (userViewModel.onValidateSecondPart().isEmpty())
                        onChange(SignUpNavigator.SignUpIDCFragment.route)
                    else
                        isError = true
                })
            CustomAlertDialog(
                title = stringResource(id = R.string.form_error_message),
                message = userViewModel.onValidateSecondPart().joinToString(separator = "\n"),
                confirmText = stringResource(id = R.string.ok),
                showDialog = isError,
                onConfirm = { isError = false }
            )
            TextButton(onClick = { onChange(SignUpNavigator.SignUpFormFragment.route) }) {
                Text(text = stringResource(id = R.string.previous), color = LightSurface)
            }

        }
    }
}