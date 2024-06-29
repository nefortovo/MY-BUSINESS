package com.example.mybusiness.presentation.screens.card

import android.content.res.Configuration
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.mybusiness.R
import com.example.mybusiness.data.repository.ToDoRepositoryImpl
import com.example.mybusiness.domain.interactor.impl.ToDoInteractorImpl
import com.example.mybusiness.domain.model.Importance
import com.example.mybusiness.presentation.screens.main.MainScreen
import com.example.mybusiness.presentation.screens.main.MainViewModel
import com.example.mybusiness.presentation.uicomponents.pickers.CustomDropDownMenu
import com.example.mybusiness.presentation.uicomponents.pickers.DatePicker
import com.example.mybusiness.theme.AppResources
import com.example.mybusiness.theme.CustomAppTheme
import java.time.LocalDate

@Composable
fun ToDoCardScreen(
    id: String?,
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: ToDoCardViewModel = hiltViewModel()
) {

    val uiState by viewModel.uiState.collectAsState()

    var isFocused by remember {
        mutableStateOf(false)
    }

    LaunchedEffect(Unit) {
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
                .padding(vertical = 21.dp)
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
            value = uiState.description,
            onValueChange = { viewModel.updateDescription(it) },
            modifier = Modifier
                .fillMaxWidth()
                .defaultMinSize(minHeight = 100.dp)
                .background(AppResources.colors.BackSecondary, shape = RoundedCornerShape(8.dp))
                .onFocusChanged { focusState ->
                    isFocused = focusState.isFocused
                },
            textStyle = TextStyle(color = AppResources.colors.LabelPrimary),
            decorationBox = { innerTextField ->
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                ) {
                    if (uiState.description.isEmpty()) {
                        Text(
                            text = stringResource(id = R.string.smth_do),
                            color = AppResources.colors.LabelTertiary
                        )
                    } else {
                        innerTextField()
                    }
                }
            }
        )

        Spacer(
            modifier = Modifier
                .padding(top = 32.dp)
        )

        Text(text = stringResource(R.string.importance))

        CustomDropDownMenu(
            items = listOf(Importance.LOW, Importance.NORMAL, Importance.HIGH),
            onItemSelected = { viewModel.changeImportance(it) },
            selectedItem = uiState.importance,
            modifier = Modifier
                .fillMaxWidth()
                .defaultMinSize(minHeight = 72.dp)
        )

        Spacer(
            modifier = Modifier
                .padding(top = 16.dp)
        )

        DatePicker(
            date = uiState.toDoModel?.deadLine ?: LocalDate.now(),
            onPickDate = { viewModel.changeDate(uiState.deadLine!!) },
            modifier = Modifier
                .defaultMinSize(minHeight = 72.dp)
        )

        Spacer(
            modifier = Modifier
                .padding(top = 32.dp)
        )

        Row(
            modifier = Modifier
                .defaultMinSize(minHeight = 72.dp)
                .fillMaxWidth()
                .clickable {
                    uiState.toDoModel?.let {
                        viewModel.deleteToDoItem(it)
                        onBack()
                    }
                }
        ) {
            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = null,
                tint = AppResources.colors.Red
            )
            Spacer(modifier = Modifier.padding(12.dp))
            Text(text = stringResource(R.string.delete), color = AppResources.colors.Red)
        }
    }
}

//черная тема по непонятной мне причине отображается не корректно, но в МП все норм
@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun ToDoCardPreview() {
    val viewModel = ToDoCardViewModel(
        toDoInteractor = ToDoInteractorImpl(
            repository = ToDoRepositoryImpl()
        )
    )
    CustomAppTheme {
        ToDoCardScreen(
            id = "1",
            modifier = Modifier,
            viewModel = viewModel,
            onBack = {}
        )
    }
}