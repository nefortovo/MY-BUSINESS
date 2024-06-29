package com.example.mybusiness.presentation.screens.main

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.mybusiness.R
import com.example.mybusiness.data.repository.ToDoRepositoryImpl
import com.example.mybusiness.domain.interactor.base.IToDoInteractor
import com.example.mybusiness.domain.interactor.impl.ToDoInteractorImpl
import com.example.mybusiness.presentation.uicomponents.dragble.ToDoItemDecorator
import com.example.mybusiness.theme.AppResources
import com.example.mybusiness.theme.CustomAppTheme

@Composable
fun MainScreen(
    onNavigateToCard: (String) -> Unit,

    modifier: Modifier = Modifier,

    viewModel: MainViewModel = hiltViewModel()
) {
    val toDoList by viewModel.toDoList.collectAsState(emptyList())

    val isVisible = viewModel.isVisible

    Scaffold(
        modifier = Modifier,
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    onNavigateToCard("-1")
                },
                shape = CircleShape,
                containerColor = AppResources.colors.Blue
            ) {
                Icon(
                    imageVector = Icons.Default.Add, contentDescription = null,
                    tint = AppResources.colors.White
                )
            }
        }
    ) { padding ->
        Column(
            modifier = modifier
                .background(AppResources.colors.BackPrimary)
                .padding(top = 50.dp)
        ) {
            Column(
                modifier = Modifier
                    .padding(start = 60.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    text = stringResource(R.string.my_business),
                    style = AppResources.typography.titles.largeTitle
                )
                Spacer(modifier = Modifier.padding(4.dp))
                Row(
                    modifier = Modifier
                        .padding(end = 25.dp)
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = stringResource(
                            R.string.complete,
                            toDoList.filter { it.enabled }.size
                        ),
                        style = AppResources.typography.body.body0
                    )
                    IconButton(onClick = { viewModel.updateVisible() }) {
                        Icon(
                            imageVector = ImageVector.vectorResource(
                                id = if (isVisible) R.drawable.visibility else R.drawable.visibility_off
                            ),
                            tint = AppResources.colors.Blue,
                            contentDescription = null,
                        )
                    }
                }
            }

            LazyColumn(
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp)
            ) {
                val filteredList = if (isVisible) {
                    toDoList.filter { it.enabled }
                } else {
                    toDoList
                }

                items(filteredList) { element ->
                    ToDoItemDecorator(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { onNavigateToCard(element.id) },
                        element = element,
                        onDeleteElement = { viewModel.deleteToDoItem(element) },
                        changeEnabled = { viewModel.changeEnabledState(element) },
                    )
                }
            }
        }
    }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun MainScreenPreview() {
    val viewModel = MainViewModel(
        toDoInteractor =  ToDoInteractorImpl(
            repository = ToDoRepositoryImpl()
        )
    )
    CustomAppTheme {
        MainScreen(
            onNavigateToCard = {},
            modifier = Modifier,
            viewModel = viewModel
        )
    }
}