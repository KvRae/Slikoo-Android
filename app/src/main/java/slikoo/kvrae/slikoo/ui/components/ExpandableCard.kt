package slikoo.kvrae.slikoo.ui.components

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardArrowDown
import androidx.compose.material.icons.rounded.KeyboardArrowRight
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Shapes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import slikoo.kvrae.slikoo.ui.theme.LightPrimaryVariant


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExpandableCard(
    modifier: Modifier = Modifier,
    title: String,
    content: @Composable () -> Unit
) {
    var expanded by remember {
        mutableStateOf(false)
    }
    
    Card(modifier = Modifier
        .fillMaxWidth().padding(8.dp)
        .animateContentSize(
            animationSpec = tween(
                delayMillis = 300,
                easing = LinearOutSlowInEasing
            )
        )
        ,
        shape = Shapes().medium,
        onClick = {
            expanded = !expanded
        }
    ) {
        Column(
            modifier = Modifier.background(color = LightPrimaryVariant)
                .fillMaxWidth().padding(8.dp)) {
            Row(
                modifier = Modifier
                    .fillMaxWidth().padding(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = title,
                    style = TextStyle(fontSize = 20.sp),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    )
                Spacer(modifier = Modifier.weight(1f))
                Icon(
                    imageVector =if (expanded) Icons.Rounded.KeyboardArrowDown else Icons.Rounded.KeyboardArrowRight,
                    contentDescription = "Expandable Card arrow",
                    modifier = Modifier)
            }
            if (expanded) {
                content()
            }
        }
    }
}