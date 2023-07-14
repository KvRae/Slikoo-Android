package slikoo.kvrae.slikoo.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import slikoo.kvrae.slikoo.R
import slikoo.kvrae.slikoo.ui.theme.DefautBlueElement


@Preview
@Composable
fun CustomSliderPreview() {
    CustomSlider(
        maxSlide = 3,
        currentSlide = 1,
    )
}

@Composable
fun CustomSlider(
    maxSlide: Int = 3,
    currentSlide: Int = 0,
) {
    Row(Modifier.padding(4.dp)) {
        val icon= ImageVector.vectorResource(id = R.drawable.ellipse_22)

        repeat(maxSlide) { index ->
            Box(Modifier.padding(2.dp)) {
                Icon(
                    imageVector =  icon,   //if (index < currentSlide) icon else icon,
                    contentDescription = null,
                    tint = if (index < currentSlide) DefautBlueElement else Color.Gray,
                    modifier = Modifier
                        .size(8.dp)
                )
            }
        }


    }
}