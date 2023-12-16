package slikoo.kvrae.slikoo.ui.fragments.profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import slikoo.kvrae.slikoo.ui.components.FeedbackAlertForm
import slikoo.kvrae.slikoo.ui.components.MealCardWrapper
import slikoo.kvrae.slikoo.ui.theme.LightBackground

@Composable
fun FeedbackFragment() {
    var showDialog by remember { mutableStateOf(false) }
    Column(
        modifier = Modifier
            .fillMaxSize(1f),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = androidx.compose.ui.Alignment.Start
    ) {
        MealsSection()
        InivitationsSection()

    }

    if (showDialog)
        FeedbackAlertForm(
            showDialog = showDialog,
            onDismiss = {
                showDialog = false
            },
            onConfirm = {
                showDialog = false
            }
        )

}

@Composable
fun MealsSection() {
    var showDialog by remember { mutableStateOf(false) }
    Column {
        SectionHeader(text = stringResource(id = slikoo.kvrae.slikoo.R.string.meals))
        Spacer(modifier = Modifier.height(16.dp))
        LazyRow(
            content = {
                items(10) {
                    MealCardWrapper(
                        onClick = {
                            showDialog = true
                        }
                    )
                }

            }
        )
    }

}

@Composable
fun InivitationsSection() {

    Column {
        SectionHeader(text = stringResource(id = slikoo.kvrae.slikoo.R.string.invitations))
        Spacer(modifier = Modifier.height(8.dp))
        LazyRow(
            content = {
                items(10) {
                    MealCardWrapper()
                }

            }

        )
    }
}

@Composable
fun SectionHeader(
    text: String
) {
    Text(
        text = text,
        modifier = Modifier.fillMaxWidth(),
        fontWeight = FontWeight.Bold,
        style = androidx.compose.ui.text.TextStyle(
            color = LightBackground,
            fontSize = 16.sp
        )
    )

}