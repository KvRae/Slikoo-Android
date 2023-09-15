package slikoo.kvrae.slikoo.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Card
import androidx.compose.material.Checkbox
import androidx.compose.material.Divider
import androidx.compose.material.DropdownMenu
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.rounded.Clear
import androidx.compose.material.icons.rounded.Search
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
import slikoo.kvrae.slikoo.ui.theme.LightSecondaryVariant
import slikoo.kvrae.slikoo.ui.theme.LightSurface

@Composable
fun SearchBar(onSearch: (String) -> Unit, modifier: Modifier = Modifier) {
    var searchText by remember { mutableStateOf("") }
    val focusManager = LocalFocusManager.current

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(65.dp)
            .padding(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 8.dp),
        elevation = 4.dp,
        shape = RoundedCornerShape(16.dp)


    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextField(
                value = searchText,
                onValueChange = { text ->
                    searchText = text
                },
                modifier = Modifier
                    .fillMaxWidth(),
                leadingIcon = { Icon(
                    imageVector = Icons.Rounded.Search,
                    contentDescription = "Search Icon",
                    tint = LightSecondaryVariant
                ) },
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
                keyboardActions = KeyboardActions(
                    onSearch = {
                        onSearch(searchText)
                        focusManager.clearFocus()
                    }
                ),
                trailingIcon = {
                    if (searchText.isNotEmpty()) {
                        IconButton(onClick = {
                            searchText = ""
                        }) {
                            Icon(
                                imageVector = Icons.Rounded.Clear,
                                contentDescription = "Clear Search Icon"
                            )
                        }
                    }

                }
            )
        }
    }
}

//************************************ Search Bar with Filter ************************************


@Composable
fun SearchBarWithFilter(
    searchText: String,
    onValueChange: (String) -> Unit,
    onSearch: (String) -> Unit,
    onFilter: (Boolean) -> Unit
) {
    var isToggled by remember { mutableStateOf(false) }
    val focusManager = LocalFocusManager.current

    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Search Bar
            OutlinedTextField(
                value = searchText,
                onValueChange = {
                    onValueChange(it)
                },
                modifier = Modifier
                    .weight(1f)
                    .clip(RoundedCornerShape(16.dp))
                    .background(color = Color.White)
                    .border(
                        width = 2.dp,
                        color = Color.White,
                        shape = RoundedCornerShape(16.dp)
                    ),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search Icon",
                        tint = Color.Gray
                    )
                },
                placeholder = { Text(text = "Rechercher") },
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
                            onValueChange("")
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
                modifier = Modifier
                    .padding(start = 16.dp)
                    .background(
                        color = Color.White,
                        shape = RoundedCornerShape(16.dp)
                    )

            ) {
                Icon(
                    imageVector = Icons.Default.List,
                    contentDescription = "Filter",
                    tint = LightSurface
                )
            }
        }
        // filter list
        DropdownMenu(expanded = isToggled,
            onDismissRequest = { isToggled = false },
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()

        ) {
            FilterRow(header = "Filter 1", onFilterChange = { /*TODO*/ }, filterList = listOf("Item1", "Item2", "Item3"))
            Divider(modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp))
            FilterRow(header = "Date", onFilterChange = { /*TODO*/ }, filterList = listOf("Item1", "Item2", "Item3"))
            Divider()
            Row(modifier = Modifier.fillMaxWidth()) {
                TextButton(onClick = { /*TODO*/ }) {
                    Text(text = "Clear Filters")
                }
                Spacer(modifier = Modifier.weight(1f))
                TextButton(onClick = { /*TODO*/ }) {
                    Text(text = "Cancel")
                }
                Spacer(modifier = Modifier.padding(8.dp))
                TextButton(onClick = { /*TODO*/ }) {
                    Text(text = "Apply")
                }
            }


        }
    }
}

@Composable
fun FilterRow(
    header: String,
    onFilterChange: (String) -> Unit,
    filterList : List<String>
) {
    var selectedFilter by remember { mutableStateOf("") }
    Row(modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically) {
        Text(text = header)
        filterList.forEach {
            filter ->
            Row(modifier = Modifier.padding(8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                Checkbox(checked = selectedFilter == filter, onCheckedChange = {
                    selectedFilter = filter
                    onFilterChange(filter)
                })
                Text(text = filter)
            }
        }



    }
}
