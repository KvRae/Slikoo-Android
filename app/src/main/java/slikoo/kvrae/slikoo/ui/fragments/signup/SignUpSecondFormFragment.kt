package slikoo.kvrae.slikoo.ui.fragments.signup

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import slikoo.kvrae.slikoo.R
import slikoo.kvrae.slikoo.ui.components.CustomButton
import slikoo.kvrae.slikoo.ui.components.CustomSliderPointers
import slikoo.kvrae.slikoo.ui.components.CustomTextField
import slikoo.kvrae.slikoo.ui.components.DescriptionTextField
import slikoo.kvrae.slikoo.ui.theme.LightSurface
import slikoo.kvrae.slikoo.utils.SignUpNavigator
import slikoo.kvrae.slikoo.viewmodels.SignUpViewModel


@Composable
fun SignUpSecondForm(onChange: (String) -> Unit) {
    val context = LocalContext.current
    val userViewModel: SignUpViewModel = viewModel( initializer = { SignUpViewModel(context) })
    var isErrors by remember{ mutableStateOf(false)}
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
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

            CustomSliderPointers(maxSlide = 4, currentSlide = 2)

            CustomTextField(
                onChange = { userViewModel.user.value = userViewModel.user.value.copy(numtel = it) },
                value = userViewModel.user.value.numtel,
                label = stringResource(R.string.phone_number),
                keyboardType = KeyboardType.Phone,
                leadingIcon = Icons.Rounded.Phone,
                errorMessage = userViewModel.onValidatePhone(),
                isError = userViewModel.onValidatePhone().isNotEmpty() && isErrors
            )

            CustomTextField(
                onChange = { userViewModel.user.value= userViewModel.user.value.copy(codepostal = it) },
                value = userViewModel.user.value.codepostal,
                label = stringResource(id = R.string.postal_code),
                keyboardType = KeyboardType.Number,
                leadingIcon = Icons.Rounded.LocationOn,
                errorMessage = userViewModel.onValidatePostalCode(),
                isError = userViewModel.onValidatePostalCode().isNotEmpty() && isErrors
            )

            DescriptionTextField(
                onChange = { userViewModel.user.value = userViewModel.user.value.copy(description = it)},
                value = userViewModel.user.value.description,
                label = stringResource(R.string.profile_description),
                leadingIcon = Icons.Rounded.Info
            )
            Spacer(modifier = Modifier.height(16.dp))

            CustomButton(text = stringResource(id = R.string.next),
                onClick = {
                    isErrors = if (userViewModel.onValidateSecondPart()){
                        onChange(SignUpNavigator.SignUpIDCFragment.route)
                        false } else true
                })
            /*CustomAlertDialog(
                title = stringResource(id = R.string.form_error_message),
                message = userViewModel.onValidateSecondPart().joinToString(separator = "\n"),
                confirmText = stringResource(id = R.string.ok),
                showDialog = isError,
                onConfirm = { isError = false }
            )*/
            TextButton(onClick = { onChange(SignUpNavigator.SignUpFormFragment.route) }) {
                Text(text = stringResource(id = R.string.previous), color = LightSurface)
            }

        }
    }
}