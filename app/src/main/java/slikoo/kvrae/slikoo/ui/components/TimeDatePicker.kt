package slikoo.kvrae.slikoo.ui.components

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import slikoo.kvrae.slikoo.R
import slikoo.kvrae.slikoo.ui.theme.LightError
import java.util.Calendar


@Composable
fun TimePicker(
    time: String,
    modifier: Modifier = Modifier,
    onTimeChange: (String) -> Unit
) {
    var timePicked by remember {
        mutableStateOf(time)
    }
    val timePickerDialog = TimePickerDialog(
        LocalContext.current,
        R.style.DialogTheme,
        { _, mHour: Int, mMinute: Int ->
            timePicked = "$mHour:$mMinute"
            onTimeChange(timePicked) // Update the time using the callback
        },
        Calendar.getInstance().get(Calendar.HOUR_OF_DAY),
        Calendar.getInstance().get(Calendar.MINUTE),
        true
    )
    OutlinedTextField(
        value = timePicked,
        label = { Text(text = stringResource(id = R.string.hour), ) },
        shape = RoundedCornerShape(8.dp),
        placeholder = { Text(text = time) },
        onValueChange = { onTimeChange(it) },
        leadingIcon = {
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.time_filled),
                contentDescription = null,
                tint = Color.Gray
            )
        },
        enabled = false,
        readOnly = true,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color.Gray,
            unfocusedBorderColor = Color.Gray,
            disabledBorderColor = if (timePicked != time) Color.Gray.copy(0.4f) else LightError,
            backgroundColor = LightError
        ),
        modifier = modifier
            .padding(8.dp)
            .fillMaxWidth()
            .clickable { timePickerDialog.show() }
    )
}

@Composable
fun DatePicker(
    date: String,
    modifier: Modifier = Modifier,
    onDateChange: (String) -> Unit
) {
    var selectedDate by remember {
        mutableStateOf(date)
    }
    val datePickerDialog = DatePickerDialog(
        LocalContext.current,
        R.style.DialogTheme,
        { _, mYear: Int, mMonth: Int, mDay: Int ->
            selectedDate = "$mDay/${mMonth+1}/$mYear"
            onDateChange(selectedDate) // Update the date using the callback
        },
        Calendar.getInstance().get(Calendar.YEAR),
        Calendar.getInstance().get(Calendar.MONTH),
        Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
    )

    OutlinedTextField(
        value = selectedDate,
        label = { Text(text = stringResource(id = R.string.date)) },
        shape = RoundedCornerShape(8.dp),
        placeholder = { Text(text = date) },
        onValueChange = { onDateChange(it) },
        enabled = false,
        leadingIcon = {
            Icon(
                imageVector = Icons.Rounded.DateRange,
                contentDescription = null,
                tint = Color.Gray
            )
        },
        readOnly = true,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color.Gray,
            unfocusedBorderColor = Color.Gray,
            disabledBorderColor = if (date.isNotEmpty()) Color.Gray.copy(0.4f) else LightError,
            backgroundColor = LightError
        ),
        modifier = modifier
            .padding(8.dp)
            .fillMaxWidth()
            .clickable { datePickerDialog.show() }
    )
}
