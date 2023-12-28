package slikoo.kvrae.slikoo.ui.components

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.rounded.Email
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import slikoo.kvrae.slikoo.R


@Composable
fun ExpandableCard(
    modifier: Modifier = Modifier,
    items: List<String>,
    placeholder: String? = stringResource(R.string.select),
    value: String? = "",
    label : String? = "",
    leadingIcon: ImageVector = Icons.Rounded.Email,
    onTitleChange: (String) -> Unit = {},
) {
    var expanded by remember { mutableStateOf(false) }

    var textfieldSize by remember { mutableStateOf(androidx.compose.ui.geometry.Size(0f,0f)) }

    val icon = if (expanded)
        Icons.Filled.KeyboardArrowUp //it requires androidx.compose.material:material-icons-extended
    else
        Icons.Filled.KeyboardArrowDown

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(90.dp)

            .clip(shape = RoundedCornerShape(8.dp))
            .animateContentSize(
                animationSpec = tween(
                    delayMillis = 300,
                    easing = LinearOutSlowInEasing
                )
            )
    ) {

        CustomTextField(
            value = value?:"",
            label = label?:"",
            onChange = { onTitleChange(it) },
            readOnly = true,
            placeHolder = placeholder ?: stringResource(R.string.select),
            leadingIcon = leadingIcon,
            modifier = Modifier
                .fillMaxWidth()
                .clickable { expanded = !expanded }
                .onGloballyPositioned { coordinates ->
                    textfieldSize = coordinates.size.toSize()
                },
            trailingIcon = icon
        )
        Box(
            modifier = Modifier
                .background(color = Color.Transparent)
                .clickable(onClick = { expanded = !expanded })
                .size(
                    with(LocalDensity.current) { textfieldSize.width.toDp() },
                    with(LocalDensity.current) { textfieldSize.height.toDp() }),
            content = {}
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .width(with(LocalDensity.current){textfieldSize.width.toDp()})
        ) {
            items.forEach { label ->
                DropdownMenuItem(onClick = {
                    onTitleChange(label)
                    expanded = false
                }) {
                    Text(text = label)
                }
            }
        }
    }
}


@Composable
fun MultiChoiceExpendableCard(
    modifier: Modifier = Modifier,
    items: List<String>,
    placeholder: String? = stringResource(R.string.select),
    value: String? = "",
    label: String? = "",
    leadingIcon: ImageVector = Icons.Rounded.Email,
    onTitleChange: (String) -> Unit = {},
) {
    var expanded by remember { mutableStateOf(false) }

    var textfieldSize by remember { mutableStateOf(androidx.compose.ui.geometry.Size(0f, 0f)) }

    val icon = if (expanded)
        Icons.Filled.KeyboardArrowUp //it requires androidx.compose.material:material-icons-extended
    else
        Icons.Filled.KeyboardArrowDown
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(90.dp)

            .clip(shape = RoundedCornerShape(8.dp))
            .animateContentSize(
                animationSpec = tween(
                    delayMillis = 300,
                    easing = LinearOutSlowInEasing
                )
            )
    ) {
        CustomTextField(
            value = value ?: "",
            label = label ?: "",
            onChange = { onTitleChange(it) },
            readOnly = true,
            placeHolder = placeholder ?: stringResource(R.string.select),
            leadingIcon = leadingIcon,
            modifier = Modifier
                .fillMaxWidth()
                .clickable { expanded = !expanded }
                .onGloballyPositioned { coordinates ->
                    textfieldSize = coordinates.size.toSize()
                },
            trailingIcon = icon
        )
        Box(
            modifier = Modifier
                .background(color = Color.Transparent)
                .clickable(onClick = { expanded = !expanded })
                .size(
                    with(LocalDensity.current) { textfieldSize.width.toDp() },
                    with(LocalDensity.current) { textfieldSize.height.toDp() }),
            content = {}
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .width(with(LocalDensity.current) { textfieldSize.width.toDp() })
        ) {
            items.forEach { label ->
                DropdownMenuItem(onClick = {
                    onTitleChange(label)
                    expanded = false
                }) {
                    Text(text = label)
                }
            }
        }
    }
}

