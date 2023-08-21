package slikoo.kvrae.slikoo.ui.components


//import androidx.compose.material3.DropdownMenuItem
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun DropDownPreview() {
    val dropDownList = listOf("Item 1", "Item 2", "Item 3")
    CustomDropDownMenu(dropDownList = dropDownList, onChange = { })
}


@Composable
fun CustomDropDownMenu(dropDownList : List<String> ,
                       value : String = "" ,
                       onChange : (String) -> Unit) {
    var isExpanded by remember { mutableStateOf(false) }
    Box() {
        OutlinedButton(onClick = { isExpanded = !isExpanded },
            shape = RectangleShape,
            modifier = Modifier.fillMaxWidth()){
            Text (value.ifEmpty { "Select an item" })
            Icon(
                imageVector = if(isExpanded) Icons.Filled.KeyboardArrowDown else Icons.Filled.KeyboardArrowRight,
                contentDescription = null,
            )
        }
        DropdownMenu(
            modifier = Modifier.fillMaxWidth(),
            expanded = isExpanded,
            onDismissRequest = { isExpanded = false },
        ) {
            dropDownList.forEach { label ->
                DropdownMenuItem(onClick = {
                    isExpanded = false
                    onChange(label)
                }) {
                    Text(text = label)
                }
            }
        }
    }
}