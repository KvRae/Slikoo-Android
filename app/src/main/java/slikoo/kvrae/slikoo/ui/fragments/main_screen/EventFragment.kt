package slikoo.kvrae.slikoo.ui.fragments.main_screen

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.widget.DatePicker
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import slikoo.kvrae.slikoo.ui.components.ImageInputField
import slikoo.kvrae.slikoo.ui.theme.LightPrimary
import slikoo.kvrae.slikoo.ui.theme.LightSecondary
import slikoo.kvrae.slikoo.utils.AppScreenNavigator
import java.util.Calendar


@Composable
fun EventScreen(title: String = "Home",
                onBackPress: (String) -> Unit,
                navController: NavController) {

    val date = remember {
        mutableStateOf("")
    }
    
    val themes = listOf<String>("Theme 1", "THeme 2", "Theme 3")

    val time = remember {
        mutableStateOf("Heure")
    }
    val datePickerDialog = DatePickerDialog(
        LocalContext.current,
        { _: DatePicker, mYear: Int, mMonth: Int, mDayOfMonth: Int ->
            date.value = "$mDayOfMonth/${mMonth+1}/$mYear"
        },
        Calendar.getInstance().get(Calendar.YEAR),
        Calendar.getInstance().get(Calendar.MONTH),
        Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
    )

    val timePickerDialog = TimePickerDialog(
        LocalContext.current,
//        android.R.style.Theme_DeviceDefault_Light,
        {_, mHour : Int , mMinute: Int ->
            time.value = "$mHour:$mMinute"
        }, Calendar.getInstance().get(Calendar.HOUR),
        Calendar.getInstance().get(Calendar.MINUTE),
        false,
    )



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


    val description = remember {
        mutableStateOf("")
    }
    var image = remember {
        mutableStateOf("")
    }


    Box(modifier = Modifier
        .fillMaxSize()
        .padding(8.dp)
        .background(color = LightSecondary)
        .verticalScroll(rememberScrollState()),
        contentAlignment = Alignment.Center
    ){
                Column(modifier = Modifier
                    .fillMaxSize(),
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.Top)
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

                        OutlinedTextField(label = { Text(text = "Heure") }
                            ,value = time.value,
                            onValueChange = { it -> time.value = it },
                            enabled = false,

                            modifier = Modifier
                                .weight(1f)
                                .clickable { timePickerDialog.show() }
                        )
                    }

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                    ) {
                        OutlinedTextField(label = { Text(text = "Date") }
                            ,value = date.value,
                            onValueChange = { it -> date.value = it },
                            enabled = false,

                            modifier = Modifier
                                .weight(1f)
                                .clickable { datePickerDialog.show() }
                        )
                    }
//                   ExpandableCard(title = "Information detailé") {
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
//                   }

                    Spacer(modifier = Modifier.weight(1f))

                    Button(onClick = { navController.navigate(AppScreenNavigator.AdvancedEditProfilesAppScreen.route) }, modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth(),
                        colors = androidx.compose.material.ButtonDefaults.buttonColors(
                            backgroundColor = LightPrimary,
                            contentColor = androidx.compose.ui.graphics.Color.White
                        ),
                        shape = RoundedCornerShape(16.dp),
                        elevation = ButtonDefaults.elevation(8.dp)
                        ) {
                        Text(text = "Terminer" )
                    }
                }
            }

    BackHandler() {
        onBackPress(title)
    }
}