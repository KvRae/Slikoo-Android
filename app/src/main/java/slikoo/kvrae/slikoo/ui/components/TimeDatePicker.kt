package slikoo.kvrae.slikoo.ui.components

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.DateRange
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import slikoo.kvrae.slikoo.ui.theme.LightError
import java.util.Calendar


@Composable
fun TimePicker(time: String, modifier: Modifier = Modifier, onTimeChange: (String) -> Unit) {
    var timePicked by remember {
        mutableStateOf(time)
    }
    val timePickerDialog = TimePickerDialog(
        LocalContext.current,
//        android.R.style.Theme_DeviceDefault_Light,
        {_, mHour : Int , mMinute: Int ->
            timePicked = "$mHour:$mMinute"
        }, Calendar.getInstance().get(Calendar.HOUR),
        Calendar.getInstance().get(Calendar.MINUTE),
        false,
    )
    OutlinedTextField(label = { Text(text = time) },
        value = time,
        placeholder = { Text(text = time) },
        onValueChange = { onTimeChange(timePicked)},
        enabled = false,
        readOnly = true,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color.Gray,
            unfocusedBorderColor = Color.Gray,
            disabledBorderColor = Color.Gray,
            backgroundColor = LightError
        ),
        modifier = modifier.padding(8.dp).fillMaxWidth().clickable { timePickerDialog.show() }
    )

}

@Composable
fun DatePicker( date : String, modifier: Modifier = Modifier, onDateChange: (String) -> Unit) {
    var datePicked by remember {
        mutableStateOf(date)
    }
    val datePickerDialog = DatePickerDialog(
        LocalContext.current,
        { _, mYear: Int, mMonth: Int, mDayOfMonth: Int ->
            datePicked = "$mDayOfMonth/${mMonth + 1}/$mYear"
        },
        Calendar.getInstance().get(Calendar.YEAR),
        Calendar.getInstance().get(Calendar.MONTH),
        Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
    )
    OutlinedTextField(label = { Text(text = date) },
        value = date,
        placeholder = { Text(text = date) },
        onValueChange = { onDateChange(datePicked)  },
        enabled = false,
        leadingIcon = {Icon(imageVector = Icons.Rounded.DateRange,
            contentDescription = null, tint = Color.Gray )},
        readOnly = true,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color.Gray,
            unfocusedBorderColor = Color.Gray,
            disabledBorderColor = Color.Transparent,
            backgroundColor = LightError
        ),
        modifier = modifier.padding(8.dp).fillMaxWidth().clickable { datePickerDialog.show() }
    )

    
}