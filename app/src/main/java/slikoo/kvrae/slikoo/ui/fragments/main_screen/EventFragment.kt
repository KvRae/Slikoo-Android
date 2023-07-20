package slikoo.kvrae.slikoo.ui.fragments.main_screen

import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import slikoo.kvrae.slikoo.ui.components.ExpandableCard
import slikoo.kvrae.slikoo.ui.components.ImageInputField
import slikoo.kvrae.slikoo.ui.theme.ButtonsAndIcons


@Composable
fun EventScreen(navController: NavController) {
    val localistaion = remember {
       mutableStateOf("")
    }
    val typeInvitation = remember {
        mutableStateOf("")
    }
    val theme = remember {
        mutableStateOf("")
    }
    val preferenceCulinaire = remember {
        mutableStateOf("")
    }
    val norriture = remember {
        mutableStateOf("")
    }
    val nombrePersonnes = remember {
        mutableStateOf("")
    }
    val prix = remember {
        mutableStateOf("")
    }
    val date = remember {
        mutableStateOf("")
    }
    val heure = remember {
        mutableStateOf("")
    }
    val description = remember {
        mutableStateOf("")
    }
    var image = remember {
        mutableStateOf("")
    }


    Box(modifier = Modifier
        .fillMaxSize()
        .padding(8.dp)
        .verticalScroll(rememberScrollState()),
        contentAlignment = Alignment.Center
    ){
                Column(modifier = Modifier
                    .fillMaxSize(),
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.Center)
                {
                    Row(modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)) {
                        OutlinedTextField(label = { Text(text = "Localistation") },value = localistaion.value , onValueChange = { it -> localistaion.value = it  },modifier = Modifier.weight(1f))
                        Spacer(modifier = Modifier.width(8.dp))
                        OutlinedTextField(label = { Text(text = "Type D'invitation") },value = typeInvitation.value, onValueChange = {it -> typeInvitation.value = it }, modifier = Modifier.weight(1f))
                    }
                    Row(modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)) {
                        OutlinedTextField(label = { Text(text = "Theme") },value = theme.value , onValueChange = { it -> theme.value = it },modifier = Modifier.weight(1f))
                        Spacer(modifier = Modifier.width(8.dp))
                        OutlinedTextField(label = { Text(text = "Preference Culinaire") },value = preferenceCulinaire.value, onValueChange = { it -> preferenceCulinaire.value = it }, modifier = Modifier.weight(1f))
                    }
                    Row(modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)) {
                        OutlinedTextField(
                            label = { Text(text = "Norriture") },
                            value = norriture.value, onValueChange = { it -> norriture.value =it },modifier = Modifier.weight(1f))
                        Spacer(modifier = Modifier.width(8.dp))
                        OutlinedTextField(label = { Text(text = "Nombres Personnes") },value = nombrePersonnes.value, onValueChange = { it-> nombrePersonnes.value = it }, modifier = Modifier.weight(1f))
                    }

                    Row(modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)) {
                        OutlinedTextField(label = { Text(text = "Prix") }, value = prix.value, onValueChange = { it -> prix.value = it },modifier = Modifier.weight(1f))
                        Spacer(modifier = Modifier.width(8.dp))
                        OutlinedTextField(label = { Text(text = "Heure") },value = heure.value, onValueChange = { it -> heure.value = it }, modifier = Modifier.weight(1f))
                    }

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                    ) {
                        OutlinedTextField(label = { Text(text = "Date") },value = date.value, onValueChange = {
                            it -> date.value = it
                        },
                            modifier = Modifier.fillMaxWidth()
                        )

                    }
                   ExpandableCard(title = "Information detailé") {
                       Row(
                           modifier = Modifier
                               .fillMaxWidth()
                               .padding(8.dp)
                       ) {
                           OutlinedTextField(
                               label = { Text(text = "Description") },
                               value = description.value,
                               onValueChange = { it -> description.value = it},
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
                           ImageInputField()
                       }
                   }

                    Spacer(modifier = Modifier.weight(1f))

                    Button(onClick = {  }, modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth(),
                        colors = androidx.compose.material.ButtonDefaults.buttonColors(
                            backgroundColor = ButtonsAndIcons,
                            contentColor = androidx.compose.ui.graphics.Color.White
                        ),
                        shape = RoundedCornerShape(16.dp),
                        elevation = ButtonDefaults.elevation(8.dp)
                        ) {
                        Text(text = "Terminer" )
                    }
                }
            }
}

fun onSubmit() {

}