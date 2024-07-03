package com.example.mybusiness.presentation.uicomponents.pickers

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
    onPickDate: (LocalDate) -> Unit,
    modifier: Modifier = Modifier
) {
    val dateFormatter = remember {
        SimpleDateFormat("d MMMM yyyy", Locale("ru"))
    }

    val context = LocalContext.current
    val calendar = Calendar.getInstance()
    calendar.time = Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant())

    val datePickerDialog = remember {
        DatePickerDialog(
            context,
            { _, year, month, dayOfMonth ->
                val selectedDate = LocalDate.of(year, month + 1, dayOfMonth)
                onPickDate(selectedDate)
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
    }

    Column(
        modifier = modifier
            .clickable {
                datePickerDialog.show()
            }
            .fillMaxWidth()
    ) {
        Text(
            text = dateFormatter.format(Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant())),
            style = MaterialTheme.typography.bodyLarge,
            color = AppResources.colors.Blue
        )
    }
}