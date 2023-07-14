package slikoo.kvrae.slikoo.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp



@Composable
fun CustomTextField(onChange : (String) -> Unit, value : String, label : String,
                    modifier: Modifier = Modifier, placeHolder: String = "") {
    OutlinedTextField(value = value,
        onValueChange ={
            onChange(it)
        },
        shape = RoundedCornerShape(16.dp),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color.Red,
            unfocusedBorderColor = Color.Blue,
            disabledBorderColor = Color.Transparent,
        ),
        placeholder = { Text(text = placeHolder) },
        label = { Text(text = label) },
        modifier = Modifier.padding(16.dp).fillMaxWidth(),
        singleLine = true,
        isError = false,
        trailingIcon = { /*TODO*/ },
        leadingIcon = { /*TODO*/ },
        keyboardOptions = KeyboardOptions( /*TODO*/),
        keyboardActions = KeyboardActions( /*TODO*/),

        )
}