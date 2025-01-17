package com.example.mybusiness.presentation.uicomponents.dragble

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mybusiness.theme.AppResources

@Composable
fun ChangeEnabledAction(modifier: Modifier) {
    Box(
        modifier = modifier.background(AppResources.colors.Green),
        contentAlignment = Alignment.Center
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                modifier = Modifier
                    .size(48.dp)
                    .padding(10.dp),
                imageVector = Icons.Filled.Check,
                contentDescription = null,
                tint = Color.White
            )
        }
    }
}

@Composable
fun DeleteAction(modifier: Modifier) {
    Box(
        modifier = modifier.background(AppResources.colors.Red),
        contentAlignment = Alignment.Center
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                modifier = Modifier
                    .size(48.dp)
                    .padding(10.dp),
                imageVector = Icons.Filled.Delete,
                contentDescription = null,
                tint = Color.White
            )
        }
    }
}
