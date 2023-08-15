package slikoo.kvrae.slikoo.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import slikoo.kvrae.slikoo.R
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
fun CustomTextField(onChange : (String) -> Unit,
                    value : String, label : String,
                    modifier: Modifier = Modifier,
                    placeHolder: String = "",
                    leadingIcon: ImageVector? = null,
                    trailingIcon: ImageVector? = null,
) {
    var searchText by remember { mutableStateOf(value) }
    var isFocused by remember {
        mutableStateOf(false)
    }
    OutlinedTextField(
        value = searchText,
        onValueChange = { text ->
            searchText = text
        },
        shape = RoundedCornerShape(8.dp),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = LightPrimary,
            cursorColor = LightPrimary,
            unfocusedBorderColor = Color.Transparent,
            backgroundColor = LightError,
            disabledBorderColor = Color.Transparent,
        ),
        placeholder = { Text(text = placeHolder) },
        label = { if(searchText.isEmpty()) Text(text = label,
            overflow = TextOverflow.Ellipsis,
            style = TextStyle(color = if (!isFocused) Color.Gray else LightPrimary),
            maxLines = 1) },
        modifier = modifier
            .padding(8.dp)
            .fillMaxWidth()
            .onFocusChanged { focusState ->
                isFocused = focusState.isFocused }
        ,
        singleLine = true,
        visualTransformation = VisualTransformation.None,
        isError = false,
        trailingIcon = {
            if (searchText.isNotEmpty()) {
                IconButton(onClick = { searchText = "" }) {
                    Icon(
                        imageVector = Icons.Rounded.Clear,
                        contentDescription = "Clear Icon",
                        tint = if (!isFocused) Color.Gray else LightPrimary
                    )
                }
            }
        },

        leadingIcon = { if (leadingIcon != null) Icon(imageVector = leadingIcon
            , contentDescription = "",
            tint = if (!isFocused) Color.Gray else LightPrimary
            ) },
        keyboardOptions = KeyboardOptions( /*TODO*/),
        keyboardActions = KeyboardActions( /*TODO*/),


    )
}


//******************************* Password Text Field *******************************************************//

@Composable
fun PasswordTextField(
    label: String = "Password",
    value: String,
    placeHolder: String = "",
    onChange: (String) -> Unit,
    isError: Boolean = false,
) {
    var passwordVisibility by remember {
        mutableStateOf(true)
    }
    var isFocused by remember {
        mutableStateOf(false)
    }
    OutlinedTextField(
        value = value,
        onValueChange = { onChange(it) },
        label = { Text(text = label, style = TextStyle(color = if (!isFocused) Color.Gray else LightPrimary)) },
        isError = isError,
        //placeholder = { Text(text = placeHolder) },
        leadingIcon = { Icon(imageVector = Icons.Rounded.Lock,
            contentDescription = "lock icon" ,
            tint = if (!isFocused) Color.Gray else LightPrimary
        ) },
        trailingIcon = {
                IconButton(onClick = {
                        passwordVisibility = !passwordVisibility
                }) {
                    Icon(
                        painter = if (passwordVisibility)painterResource(id = R.drawable.visibility_icon) else painterResource(id = R.drawable.visibility_off_icon),
                        contentDescription = "Clear Icon",
                        tint = if (!isFocused) Color.Gray else LightPrimary
                    )

                }
        },
        modifier = Modifier
            .fillMaxWidth().padding(8.dp).onFocusChanged { focusState ->
                isFocused = focusState.isFocused },
        singleLine = true,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = LightPrimary,
            backgroundColor = LightError,
            cursorColor = LightPrimary,
            unfocusedBorderColor = Color.Transparent,
            disabledBorderColor = Color.Transparent,
        ),
        shape = RoundedCornerShape(8.dp),
        keyboardOptions = KeyboardOptions( keyboardType = KeyboardType.Password),
        keyboardActions = KeyboardActions( /*TODO*/),
        visualTransformation = if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),

    )
}

// ********************************* OtpView ********************************* //


const val PIN_VIEW_TYPE_UNDERLINE = 0
const val PIN_VIEW_TYPE_BORDER = 1

@Composable
fun PinView(
    pinText: String,
    onPinTextChange: (String) -> Unit,
    digitColor: Color = MaterialTheme.colors.onBackground,
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
    digitColor: Color = MaterialTheme.colors.onBackground,
    digitSize: TextUnit,
    containerSize: Dp,
    type: Int = PIN_VIEW_TYPE_UNDERLINE,
) {
    val modifier = if (type == PIN_VIEW_TYPE_BORDER) {
        Modifier
            .width(containerSize)
            .border(
                width = 1.dp,
                color = digitColor,
                shape = MaterialTheme.shapes.medium
            )
            .padding(bottom = 3.dp)
    } else Modifier.width(containerSize)

    Column(horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center) {
        Text(
            text = if (index >= pinText.length) "" else pinText[index].toString(),
            color = digitColor,
            modifier = modifier,
            style = MaterialTheme.typography.body1,
            fontSize = digitSize,
            textAlign = TextAlign.Center)
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
}

