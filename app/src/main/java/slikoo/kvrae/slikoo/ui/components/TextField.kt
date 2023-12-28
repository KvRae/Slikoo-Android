package slikoo.kvrae.slikoo.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.material.icons.rounded.Lock
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import slikoo.kvrae.slikoo.R
import slikoo.kvrae.slikoo.ui.theme.LightBackground
import slikoo.kvrae.slikoo.ui.theme.LightError
import slikoo.kvrae.slikoo.ui.theme.LightPrimary

/*data class TextField(
    val label: String,
    val value: String,
    val placeHolder: String,
    val leadingIcon: Icon? = null,
    val trailingIcon: Icon? = null,
    val modifier: Modifier = Modifier,
    val onChange: (String) -> Unit,
    val keyboardType: KeyboardType = KeyboardType.Text,
)*/




//******************************* Custom Text Field *******************************************************//
@Composable
fun CustomTextField(
    onChange: (String) -> Unit,
    value: String?,
    label: String,
    modifier: Modifier = Modifier,
    placeHolder: String = "",
    keyboardType: KeyboardType = KeyboardType.Text,
    keyboardActions: KeyboardActions = KeyboardActions(),
    leadingIcon: ImageVector? = null,
    readOnly: Boolean = false,
    isError: Boolean = false,
    trailingIcon: ImageVector? = null,
    errorMessage: String = "",
) {
    val focusManager = LocalFocusManager.current
    var isFocused by remember { mutableStateOf(false) }

    Column {
        OutlinedTextField(
            value = value.orEmpty(), // Use the safe call operator to handle null value
            onValueChange = { onChange(it) },
            readOnly = readOnly,
            shape = RoundedCornerShape(8.dp),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = LightPrimary,
                textColor = LightBackground,
                errorBorderColor = LightPrimary,
                errorCursorColor = LightPrimary,
                cursorColor = LightPrimary,
                unfocusedBorderColor = if (value?.isEmpty() == true) Color.Transparent else Color.Gray.copy(
                    alpha = 0.3f
                ),
                backgroundColor = LightError,
                disabledBorderColor = Color.Transparent,
            ),
            placeholder = { Text(text = placeHolder) },
            label = {
                Text(
                    text = label,
                    overflow = TextOverflow.Ellipsis,
                    style = TextStyle(color = if (!isFocused) Color.Gray else LightPrimary),
                    maxLines = 1
                )
            },
            modifier = modifier
                .padding(8.dp)
                .fillMaxWidth()
                .onFocusChanged { focusState -> isFocused = focusState.isFocused },
            singleLine = true,
            visualTransformation = VisualTransformation.None,
            isError = if (errorMessage.isNotEmpty() && isError) true else isError,
            trailingIcon = {
                if (value?.isNotEmpty() == true && trailingIcon == null && !readOnly) {
                    IconButton(onClick = {
                        onChange("")
                    }) {
                        Icon(
                            imageVector = Icons.Rounded.Clear,
                            contentDescription = "Clear Icon",
                            tint = if (!isFocused) Color.Gray else LightPrimary
                        )
                    }
                } else if (trailingIcon != null) {
                    Icon(
                        imageVector = trailingIcon,
                        contentDescription = "",
                        tint = if (!isFocused) Color.Gray else LightPrimary
                    )
                }
            },

            leadingIcon = {
                if (leadingIcon != null) Icon(
                    imageVector = leadingIcon,
                    contentDescription = "",
                    modifier = Modifier
                        .padding(end = 8.dp)
                        .size(24.dp),
                    tint = if (!isFocused) Color.Gray else LightPrimary
                )
            },
            keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
            keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
        )
        if (isError) {
            Text(
                text = errorMessage,
                color = LightPrimary,
                fontSize = 12.sp,
                modifier = Modifier.padding(start = 8.dp)
            )
        }
    }
}


//******************************* Password Text Field *******************************************************//

@Composable
fun PasswordTextField(
    label: String = "Password",
    value: String,
    placeHolder: String = "",
    keyboardType: KeyboardType = KeyboardType.Password,
    onChange: (String) -> Unit,
    isError: Boolean = false,
    errorMessage: String = "",
) {
    var passwordVisibility by remember {
        mutableStateOf(true)
    }
    var isFocused by remember {
        mutableStateOf(false)
    }
    Column {
        OutlinedTextField(
            value = value,
            onValueChange = { onChange(it) },
            label = {
                Text(
                    text = label,
                    style = TextStyle(color = if (!isFocused) Color.Gray else LightPrimary)
                )
            },
            isError = isError,
            //placeholder = { Text(text = placeHolder) },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Rounded.Lock,
                    contentDescription = "lock icon",
                    tint = if (!isFocused) Color.Gray else LightPrimary
                )
            },
            trailingIcon = {
                IconButton(onClick = {
                    passwordVisibility = !passwordVisibility
                }) {
                    Icon(
                        painter = if (passwordVisibility) painterResource(id = R.drawable.visibility_icon) else painterResource(
                            id = R.drawable.visibility_off_icon
                        ),
                        contentDescription = "Clear Icon",
                        tint = if (!isFocused) Color.Gray else LightPrimary
                    )

                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .onFocusChanged { focusState ->
                    isFocused = focusState.isFocused
                },
            singleLine = true,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = LightPrimary,
                backgroundColor = LightError,
                textColor = LightBackground,
                cursorColor = LightPrimary,
                unfocusedBorderColor = if (value.isEmpty()) Color.Transparent else Color.Gray.copy(alpha = 0.3f),
                disabledBorderColor = Color.Transparent,
            ),
            shape = RoundedCornerShape(8.dp),
            keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
            keyboardActions = KeyboardActions( /*TODO*/),
            visualTransformation = if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
            )
            if (errorMessage.isNotEmpty() && isError) {
                Text(
                    text = errorMessage,
                    color = LightPrimary,
                    fontSize = 12.sp,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
        }
    }

// ********************************* OtpView ********************************* //


    /*const val PIN_VIEW_TYPE_UNDERLINE = 0
    const val PIN_VIEW_TYPE_BORDER = 1

    @Composable
    fun PinView(
        pinText: String,
        onPinTextChange: (String) -> Unit,
        digitColor: Color = LightBackground,
        digitSize: TextUnit = 16.sp,
        containerSize: Dp = digitSize.value.dp * 2,
        digitCount: Int = 4,
        type: Int = PIN_VIEW_TYPE_UNDERLINE,
    ) {
        BasicTextField(value = pinText,
            onValueChange = onPinTextChange,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            decorationBox = {
                Row(horizontalArrangement = Arrangement.SpaceBetween) {
                    repeat(digitCount) { index ->
                        DigitView(index, pinText, digitColor, digitSize, containerSize, type = type)
                        Spacer(modifier = Modifier.width(5.dp))
                    }
                }
            })
    }


    @Composable
    private fun DigitView(
        index: Int,
        pinText: String,
        digitColor: Color = LightBackground,
        digitSize: TextUnit,
        containerSize: Dp,
        type: Int = PIN_VIEW_TYPE_UNDERLINE,
    ) {
        val modifier = if (type == PIN_VIEW_TYPE_BORDER) {
            Modifier
                .width(containerSize)
                .border(
                    width = 1.dp, color = digitColor, shape = MaterialTheme.shapes.medium
                )
                .padding(bottom = 3.dp)
        } else Modifier.width(containerSize)

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = if (index >= pinText.length) "" else pinText[index].toString(),
                color = digitColor,
                modifier = modifier,
                style = MaterialTheme.typography.body1,
                fontSize = digitSize,
                textAlign = TextAlign.Center
            )
            if (type == PIN_VIEW_TYPE_UNDERLINE) {
                Spacer(modifier = Modifier.height(2.dp))
                Box(
                    modifier = Modifier
                        .background(digitColor)
                        .height(1.dp)
                        .width(containerSize)
                )
            }
        }
    }*/


    /************************* Description Text Field *************************************/

    @Composable
    fun DescriptionTextField(
        onChange: (String) -> Unit,
        value: String, label: String,
        modifier: Modifier = Modifier,
        placeHolder: String = "",
        keyboardType: KeyboardType = KeyboardType.Text,
        keyboardActions: KeyboardActions = KeyboardActions(),
        leadingIcon: ImageVector? = null,
        trailingIcon: ImageVector? = null,
        errorMessage: String = "",
    ) {
        val focusManager = LocalFocusManager.current
        var isFocused by remember {
            mutableStateOf(false)
        }
        Column(modifier = Modifier.fillMaxWidth()) {
            OutlinedTextField(
                value = value,
                onValueChange = {
                    onChange(it)
                },
                shape = RoundedCornerShape(8.dp),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = LightPrimary,
                    textColor = LightBackground,
                    cursorColor = LightPrimary,
                    unfocusedBorderColor = if (value.isEmpty()) Color.Transparent else Color.Gray.copy(
                        alpha = 0.3f
                    ),
                    backgroundColor = LightError,
                    disabledBorderColor = Color.Transparent,
                ),
                placeholder = { Text(text = placeHolder) },
                label = {
                    Text(
                        text = label,
                        overflow = TextOverflow.Ellipsis,
                        style = TextStyle(color = if (!isFocused) Color.Gray else LightPrimary),
                        maxLines = 4
                    )
                },
                modifier = modifier
                    .padding(8.dp)
                    .fillMaxWidth()
                    .onFocusChanged { focusState -> isFocused = focusState.isFocused },
                singleLine = false,
                visualTransformation = VisualTransformation.None,
                isError = errorMessage.isNotEmpty(),
                trailingIcon = {
                    if (value.isNotEmpty()) {
                        IconButton(onClick = {
                            onChange("")
                        }) {
                            Icon(
                                imageVector = Icons.Rounded.Clear,
                                contentDescription = "Clear Icon",
                                tint = if (!isFocused) Color.Gray else LightPrimary
                            )
                        }
                    }
                },

                leadingIcon = {
                    if (leadingIcon != null) Icon(
                        imageVector = leadingIcon,
                        contentDescription = "",
                        tint = if (!isFocused) Color.Gray else LightPrimary
                    )
                },
                keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
                keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
            )
            if (errorMessage.isNotEmpty()) {
                Text(
                    text = errorMessage,
                    color = LightPrimary,
                    fontSize = 12.sp,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
        }
    }

