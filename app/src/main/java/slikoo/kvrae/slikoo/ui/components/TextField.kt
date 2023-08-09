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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import slikoo.kvrae.slikoo.R
import slikoo.kvrae.slikoo.ui.theme.ButtonsAndIcons

data class TextField(
    val label: String,
    val value: String,
    val placeHolder: String,
    val leadingIcon: Icon? = null,
    val trailingIcon: Icon? = null,
    val modifier: Modifier = Modifier,
    val onChange: (String) -> Unit,
    val keyboardType: KeyboardType = KeyboardType.Text,
)


@Composable
fun CustomTextField(onChange : (String) -> Unit,
                    value : String, label : String,
                    modifier: Modifier = Modifier,
                    placeHolder: String = "",
                    leadingIcon: ImageVector? = null,
                    trailingIcon: ImageVector? = null,
) {
    var searchText by remember { mutableStateOf("") }
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
            focusedBorderColor = ButtonsAndIcons,
            unfocusedBorderColor = Color.Gray,
            disabledBorderColor = Color.Transparent,
        ),
        placeholder = { Text(text = placeHolder) },
        label = { Text(text = label,
            overflow = TextOverflow.Ellipsis,
            style = TextStyle(color = if (!isFocused) Color.Gray else ButtonsAndIcons),
            maxLines = 1) },
        modifier = Modifier
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
                        tint = if (!isFocused) Color.Gray else ButtonsAndIcons
                    )
                }
            }
        },

        leadingIcon = { if (leadingIcon != null) Icon(imageVector = leadingIcon
            , contentDescription = "",
            tint = if (!isFocused) Color.Gray else ButtonsAndIcons

            ) },
        keyboardOptions = KeyboardOptions( /*TODO*/),
        keyboardActions = KeyboardActions(

        ),


    )
}


@Composable
fun PasswordTextField(
    label: String = "Password",
    value: String,
    placeHolder: String,
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
        label = { Text(text = label, style = TextStyle(color = if (!isFocused) Color.Gray else ButtonsAndIcons)) },
        isError = isError,
        placeholder = { Text(text = placeHolder) },
        leadingIcon = { Icon(imageVector = Icons.Rounded.Lock,
            contentDescription = "lock icon" ,
            tint = if (!isFocused) Color.Gray else ButtonsAndIcons
        ) },
        trailingIcon = {
                IconButton(onClick = {
                        passwordVisibility = !passwordVisibility
                }) {
                    Icon(
                        painter = if (passwordVisibility)painterResource(id = R.drawable.visibility_icon) else painterResource(id = R.drawable.visibility_off_icon),
                        contentDescription = "Clear Icon",
                        tint = if (!isFocused) Color.Gray else ButtonsAndIcons
                    )

                }
        },
        modifier = Modifier
            .fillMaxWidth().padding(8.dp).onFocusChanged { focusState ->
                isFocused = focusState.isFocused },
        singleLine = true,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = ButtonsAndIcons,
            unfocusedBorderColor = Color.Gray,
            disabledBorderColor = Color.Transparent,
        ),
        shape = RoundedCornerShape(8.dp),
        keyboardOptions = KeyboardOptions( keyboardType = KeyboardType.Password),
        keyboardActions = KeyboardActions( /*TODO*/),
        visualTransformation = if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),

    )
}

