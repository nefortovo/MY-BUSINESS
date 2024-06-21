package com.example.mybusiness.presentation.screens.card

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.mybusiness.R
import com.example.mybusiness.domain.model.Importance
import com.example.mybusiness.presentation.uicomponents.listItems.pickers.CustomDropDownMenu
import com.example.mybusiness.presentation.uicomponents.listItems.pickers.DatePicker
import com.example.mybusiness.theme.AppResources

@Composable
fun ToDoCardScreen(
    id: Int?,
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: ToDoCardViewModel = hiltViewModel()
) {

    val uiState by viewModel.uiState.collectAsState()

    val description by viewModel.description

    val importance by viewModel.importance

    val date by viewModel.deadLine

    var isFocused by remember {
        mutableStateOf(false)
    }

    LaunchedEffect(Unit){
        id?.let { viewModel.getItem(it) }
    }

    Column(
        modifier = modifier
            .background(AppResources.colors.BackPrimary)
            .padding(horizontal = 16.dp)
            .scrollable(rememberScrollState(), orientation = Orientation.Vertical),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = { onBack() }) {
                Icon(imageVector = Icons.Default.Close, contentDescription = null)
            }
            Text(
                text = stringResource(id = R.string.save).uppercase(),
                style = AppResources.typography.button.button0,
                color = AppResources.colors.Blue,
                modifier = Modifier
                    .clickable {
                        viewModel.editElement()
                        onBack()
                    }
            )
        }
        BasicTextField(
            value = description,
            onValueChange = { viewModel.updateDescription(it) },
            modifier = Modifier
                .fillMaxWidth()
                .defaultMinSize(minHeight = 100.dp)
                .background(AppResources.colors.White, shape = RoundedCornerShape(8.dp))
                .onFocusChanged { focusState ->
                    isFocused = focusState.isFocused
                },
            decorationBox = { innerTextField ->
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                ) {
                    if (!isFocused && description.isEmpty()) {
                        Text(text = stringResource(id = R.string.smth_do), color = AppResources.colors.LabelTertiary)
                    }
                    else{
                        innerTextField()
                    }
                }
            }
        )

        Spacer(modifier = Modifier
            .padding(top = 16.dp))

        CustomDropDownMenu(
            items = listOf(Importance.LOW.importanceName, Importance.NORMAL.importanceName, Importance.HIGH.importanceName),
            onItemSelected = { viewModel.changeImportance(it) },
            selectedItem = importance,
            modifier = Modifier.fillMaxWidth()
                .defaultMinSize(minHeight = 72.dp)
        )

        Spacer(modifier = Modifier
            .padding(top = 16.dp))

        DatePicker(
            date = date,
            onPickDate = { viewModel.changeDate(date) },
            modifier = Modifier
                .defaultMinSize(minHeight = 72.dp)
        )

        Spacer(modifier = Modifier
            .padding(top = 32.dp))

        Row(
            modifier = Modifier
                .defaultMinSize(minHeight = 72.dp)
        ) {
            Icon(imageVector = Icons.Default.Delete, contentDescription = null, tint = AppResources.colors.Red)
            Text(text = stringResource(R.string.delete), color = AppResources.colors.Red)
        }
    }
}