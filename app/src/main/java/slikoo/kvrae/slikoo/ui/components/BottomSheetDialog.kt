package slikoo.kvrae.slikoo.ui.components

import androidx.compose.foundation.border
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Checkbox
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Clear
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Preview(showBackground = true)
@Composable
fun ModalBottomSheet() {
    val isShow by remember { mutableStateOf(false) }
    Box(modifier = Modifier
        .fillMaxWidth()
        .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
        .onFocusChanged {}
        .focusable(isShow)
        .height(350.dp)
        .border(
            1.dp,
            MaterialTheme.colors.onSurface,
            RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
        )
        .padding(16.dp)
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            Row(
                Modifier
                    .fillMaxWidth()) {
                Spacer(modifier = Modifier.weight(1f))
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(imageVector = Icons.Rounded.Clear, contentDescription = null)
                }
            }
            FilterItem()
            FilterItem()
            FilterItem()
            FilterItem()
        }
    }
}

@Composable
fun FilterItem() {
    val isCheck = remember { mutableStateOf(false) }
    Row(Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically)
    {

        Checkbox(checked = isCheck.value,
            onCheckedChange = {
                isCheck.value = !it
            })
        Spacer(modifier = Modifier.padding(4.dp))
        Text(text = "Valeur", style = MaterialTheme.typography.h6)
    }
}