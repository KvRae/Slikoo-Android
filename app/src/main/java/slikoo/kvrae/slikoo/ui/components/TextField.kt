package slikoo.kvrae.slikoo.ui.components

import android.graphics.drawable.Icon
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Clear
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp

data class TextField(
    val label: String,
    val value: String,
    val placeHolder: String,
    val leadingIcon: Icon? = null,
    val trailingIcon: Icon? = null,
    val modifier: Modifier = Modifier,
    val onChange: (String) -> Unit
)


@Composable
fun CustomTextField(onChange : (String) -> Unit,
                    value : String, label : String,
                    modifier: Modifier = Modifier,
                    placeHolder: String = "",
                    leadingIcon: Icon? = null,
                    trailingIcon: Icon? = null,
) {
    var searchText by remember { mutableStateOf("") }
    OutlinedTextField(value = searchText,
        onValueChange = {
            text -> searchText = text
        },
        shape = RoundedCornerShape(8.dp),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color.Blue,
            unfocusedBorderColor = Color.Gray,
            disabledBorderColor = Color.Transparent,
        ),
        placeholder = { Text(text = placeHolder) },
        label = { Text(text = label, overflow = TextOverflow.Ellipsis, maxLines = 1) },
        modifier = Modifier.padding(8.dp).fillMaxWidth(),
        singleLine = true,
        visualTransformation = VisualTransformation.None,
        isError = false,
        trailingIcon = {
            if (searchText.isNotEmpty()) {
                IconButton(onClick = { searchText = "" }) {
                    Icon(
                        imageVector = Icons.Rounded.Clear,
                        contentDescription = "Clear Icon"
                    )
                }
            }
        },
        //leadingIcon = { leadingIcon},
        keyboardOptions = KeyboardOptions( /*TODO*/),
        keyboardActions = KeyboardActions( /*TODO*/),
        )
}
/*
@Composable
fun PasswordTextField(
    text: String,
    modifier: Modifier = Modifier,
    semanticContentDescription: String = "",
    labelText: String = "",
    validateStrengthPassword: Boolean = false,
    hasError: Boolean = false,
    onHasStrongPassword: (isStrong: Boolean) -> Unit = {},
    onTextChanged: (text: String) -> Unit,
) {
    val focusManager = LocalFocusManager.current
    val showPassword = remember { mutableStateOf(false) }

    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        OutlinedTextField(
            modifier = Modifier
                .background(color = colorResource(id = R.color.colorVeryDarkDesaturatedBlue))
                .fillMaxWidth()
                .semantics { contentDescription = semanticContentDescription },
            value = text,
            onValueChange = onTextChanged,
            placeholder = {
                Text(
                    text = labelText,
                    color = Color.White,
                    fontSize = 16.sp,
//                    fontFamily = muliFontFamily
                )
            },
            keyboardOptions = KeyboardOptions.Default.copy(
                autoCorrect = true,
                keyboardType = KeyboardType.Text,
                //imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    focusManager.clearFocus()
                }
            ),
            singleLine = true,
            isError = hasError,
            visualTransformation = if (showPassword.value) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                val (icon, iconColor) = if (showPassword.value) {
                    Pair(
                        Icons.Filled.Visibility,
                        colorResource(id = R.color.colorBrightViolet200)
                    )
                } else {
                    Pair(Icons.Filled.VisibilityOff, colorResource(id = R.color.colorWhite))
                }

                IconButton(onClick = { showPassword.value = !showPassword.value }) {
                    Icon(
                        icon,
                        contentDescription = "Visibility",
                        tint = iconColor
                    )
                }
            },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color.White,
                unfocusedBorderColor = Color.White,
                textColor = Color.White,
                cursorColor = Color.White,
            )
        )
        Spacer(modifier = Modifier.height(8.dp))
        if (validateStrengthPassword && text != String.empty()) {
            val strengthPasswordType = strengthChecker(text)
            if (strengthPasswordType == StrengthPasswordTypes.STRONG) {
                onHasStrongPassword(true)
            } else {
                onHasStrongPassword(false)
            }
            Text(
                modifier = Modifier.semantics { contentDescription = "StrengthPasswordMessage" },
                text = buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            color = Color.White,
                            fontSize = 10.sp,
                            fontFamily = muliFontFamily
                        )
                    ) {
                        append(stringResource(id = R.string.warning_password_level))
                        withStyle(style = SpanStyle(color = colorResource(id = R.color.colorOrange100))) {
                            when (strengthPasswordType) {
                                StrengthPasswordTypes.STRONG ->
                                    append(" ${stringResource(id = R.string.warning_password_level_strong)}")
                                StrengthPasswordTypes.WEAK ->
                                    append(" ${stringResource(id = R.string.warning_password_level_weak)}")
                            }
                        }
                    }
                }
            )
        }
    }
}


*/