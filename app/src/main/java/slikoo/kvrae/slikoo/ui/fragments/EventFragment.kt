package slikoo.kvrae.slikoo.ui.fragments

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController


@Composable
fun EventScreen(navController: NavController) {
    Box(modifier = Modifier
        .fillMaxSize()
        .padding(8.dp)
        .verticalScroll(rememberScrollState()),
        contentAlignment = Alignment.Center
    ){
                Column(modifier = Modifier
                    .fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally)
                {
                    Row(modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)) {
                        OutlinedTextField(value = "Rechercher", onValueChange = { /* handle value change */ },modifier = Modifier.weight(1f))
                        Spacer(modifier = Modifier.width(8.dp))
                        OutlinedTextField(value = "", onValueChange = { /* handle value change */ }, modifier = Modifier.weight(1f))
                    }
                    Row(modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)) {
                        OutlinedTextField(value = "Rechercher", onValueChange = { /* handle value change */ },modifier = Modifier.weight(1f))
                        Spacer(modifier = Modifier.width(8.dp))
                        OutlinedTextField(value = "", onValueChange = { /* handle value change */ }, modifier = Modifier.weight(1f))
                    }
                    Row(modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)) {
                        OutlinedTextField(value = "Rechercher", onValueChange = { /* handle value change */ },modifier = Modifier.weight(1f))
                        Spacer(modifier = Modifier.width(8.dp))
                        OutlinedTextField(value = "", onValueChange = { /* handle value change */ }, modifier = Modifier.weight(1f))
                    }

                    Row(modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)) {
                        OutlinedTextField(value = "Rechercher", onValueChange = { /* handle value change */ },modifier = Modifier.weight(1f))
                        Spacer(modifier = Modifier.width(8.dp))
                        OutlinedTextField(value = "", onValueChange = { /* handle value change */ }, modifier = Modifier.weight(1f))
                    }

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                    ) {
                        OutlinedTextField(value = "Date", onValueChange = {})
                    }

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                    ) {
                        OutlinedTextField(
                            value = "Description",
                            onValueChange = { /* handle value change */ },
                            modifier = Modifier
                                .weight(1f)
                                .height(120.dp) // Adjust the height value as needed
                        )
                    }

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                    ) {
                        OutlinedTextField(
                            value = "Description",
                            onValueChange = { /* handle value change */ },
                            modifier = Modifier
                                .weight(1f)
                                .height(120.dp) // Adjust the height value as needed
                        )
                    }

                    Spacer(modifier = Modifier.weight(1f))

                    Button(onClick = { /*TODO*/ }, modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth()) {
                        Text(text = "Button" )
                    }
                }
            }
}