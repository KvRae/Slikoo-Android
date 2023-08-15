package slikoo.kvrae.slikoo.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
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
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import slikoo.kvrae.slikoo.ui.theme.LightPrimary
import slikoo.kvrae.slikoo.ui.theme.LightSurface


@Composable
fun SearchBarWithFilter(onSearch: (String) -> Unit,
                        modifier: Modifier = Modifier,
                        onFilter: (Boolean) -> Unit) {
    var searchText by remember { mutableStateOf("") }
    var isToggled by remember { mutableStateOf(false) }
    val focusManager = LocalFocusManager.current
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Search Bar
        OutlinedTextField(
            value = searchText,
            onValueChange = { text ->
                searchText = text
            },
            modifier = Modifier.weight(1f)
                .clip(RoundedCornerShape(16.dp))
                .background(color = Color.White).border(
                    width = 2.dp,
                    color = Color.White,
                    shape = RoundedCornerShape(16.dp)
                )
        ,
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search Icon",
                    tint = Color.Gray
                )
            },
            placeholder = { Text(text = "Rechercher")},
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.Transparent,
                focusedIndicatorColor = LightPrimary,
                unfocusedIndicatorColor = Color.Gray
            ),
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
            keyboardActions = KeyboardActions(
                onSearch = {
                    onSearch(searchText)
                    focusManager.clearFocus()
                }
            ),
            shape = RoundedCornerShape(16.dp),
            trailingIcon = {
                if (searchText.isNotEmpty()) {
                    IconButton(onClick = {
                        searchText = ""
                    }) {
                        Icon(
                            imageVector = Icons.Default.Clear,
                            contentDescription = "Clear Search Icon"
                        )
                    }
                }
            }
        )

        // Filter Button
        IconButton(
            onClick = {
                isToggled = !isToggled
                onFilter(isToggled)
            },
            modifier = Modifier.padding(start = 16.dp)
            .background(
                color = Color.White,
                shape = RoundedCornerShape(16.dp))

        ) {
            Icon(
                imageVector = Icons.Default.List,
                contentDescription = "Filter",
                tint = LightSurface
            )
        }
    }
}

@Composable
fun FilterDialog() {

}