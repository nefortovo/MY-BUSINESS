package com.example.mybusiness.presentation.uicomponents.listItems.pickers

import android.app.DatePickerDialog
import android.widget.DatePicker
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.mybusiness.theme.AppResources
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.ZoneId
import java.util.Calendar
import java.util.Date
import java.util.Locale

@Composable
fun DatePicker(
    date: LocalDate,
    onPickDate: (Date) -> Unit,
    modifier: Modifier = Modifier
) {
    var selectedDate by remember { mutableStateOf(date) }
    val context = LocalContext.current

    val calendar = Calendar.getInstance()
    val year = calendar.get(Calendar.YEAR)
    val month = calendar.get(Calendar.MONTH)
    val day = calendar.get(Calendar.DAY_OF_MONTH)

    val dateFormatter = remember {
        SimpleDateFormat("d MMMM yyyy", Locale("ru"))
    }

    val datePickerDialog = DatePickerDialog(
        context,
        { _: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
            val selectedCalendar = Calendar.getInstance().apply {
                set(year, month, dayOfMonth)
            }
            selectedDate = selectedCalendar.toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
            onPickDate(selectedCalendar.time)
        }, year, month, day
    )

    Column(
        modifier = modifier
            .clickable{datePickerDialog.show()}
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(text = "Сделать до")
        Text(
            text = dateFormatter.format(localDateToDate(selectedDate)),
            style = MaterialTheme.typography.bodyLarge,
            color = AppResources.colors.Blue
        )
    }
}

fun localDateToDate(localDate: LocalDate): Date {
    return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant())
}