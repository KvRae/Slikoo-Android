package slikoo.kvrae.slikoo.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import slikoo.kvrae.slikoo.R


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun RecipeCard(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
    ) {
        Card(
            modifier = modifier.padding(8.dp),
            shape = RoundedCornerShape(16.dp),
            elevation = 4.dp,
            onClick = { /*TODO*/ }
        ) {

            Column(modifier = Modifier.padding(1.dp)) {
                content()
            }
        }

    }

@Composable
fun Content() {
    Image(
        painter = painterResource(id = R.drawable.packgound),
        contentDescription = "Background"
    )
    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "Recipe Name")
        Text(text = "Description")


    }
}

@Preview
@Composable
fun RecipeCardPreview() {
       RecipeCard() {
        Content() }
}
