package slikoo.kvrae.slikoo.ui.fragments.signup

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Email
import androidx.compose.material.icons.rounded.Person
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
import slikoo.kvrae.slikoo.ui.components.CustomButton
import slikoo.kvrae.slikoo.ui.components.CustomSliderPointers
import slikoo.kvrae.slikoo.ui.components.CustomTextField
import slikoo.kvrae.slikoo.ui.components.PasswordTextField
import slikoo.kvrae.slikoo.utils.SignUpNavigator
import slikoo.kvrae.slikoo.viewmodels.SignUpViewModel


@Composable
fun SignUpForm(onChange: (String) -> Unit) {
    val userViewModel: SignUpViewModel = viewModel()
    var isErrors by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier.run {
            fillMaxSize().padding(8.dp).navigationBarsPadding()
        }
    ) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(id = R.string.signUp),
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.size(8.dp))

            CustomSliderPointers(
                maxSlide = 4,
                currentSlide = 1
            )

            CustomTextField(
                onChange = { userViewModel.user.value = userViewModel.user.value.copy(nom = it) },
                value = userViewModel.user.value.nom,
                label = stringResource(id = R.string.name),
                keyboardType = KeyboardType.Text,
                leadingIcon = Icons.Rounded.Person,
                errorMessage = userViewModel.onValidateFirstName(),
                isError = userViewModel.onValidateFirstName().isNotEmpty() && isErrors
            )

            CustomTextField(
                onChange = { userViewModel.user.value = userViewModel.user.value.copy(prenom = it) },
                value = userViewModel.user.value.prenom,
                label = stringResource(id = R.string.familyName),
                keyboardType = KeyboardType.Text,
                leadingIcon = Icons.Rounded.Person,
                errorMessage = userViewModel.onValidateLastName(),
                isError = userViewModel.onValidateLastName().isNotEmpty() && isErrors
            )

            CustomTextField(
                onChange = { userViewModel.user.value = userViewModel.user.value.copy(email = it) },
                value = userViewModel.user.value.email,
                label = stringResource(id = R.string.email),
                keyboardType = KeyboardType.Email,
                leadingIcon = Icons.Rounded.Email,
                errorMessage = userViewModel.onValidateEmail(),
                isError = userViewModel.onValidateEmail().isNotEmpty() && isErrors
            )

            PasswordTextField(label = stringResource(id = R.string.password),
                value = userViewModel.user.value.password,
                onChange = { userViewModel.user.value = userViewModel.user.value.copy(password = it) },
                isError = userViewModel.onValidatePassword().isNotEmpty() && isErrors,
                errorMessage = userViewModel.onValidatePassword()
            )

            PasswordTextField(label = stringResource(id = R.string.confirmPassword),
                value = userViewModel.confirmPassword.value,
                onChange = { userViewModel.confirmPassword.value = it},
                isError = userViewModel.onConfirmPassword().isNotEmpty() && isErrors,
                errorMessage = userViewModel.onConfirmPassword()
            )

            Spacer(modifier = Modifier.padding(8.dp))

            CustomButton(text = stringResource(id = R.string.next),
                onClick = {
                    isErrors = if (userViewModel.onValidateFirstForm()) {
                        onChange(SignUpNavigator.SignUpSecondFormFragment.route)
                        false } else true
                }
            )
        }
    }
}