package com.example.mybusiness.presentation.uicomponents.listItems.pickers

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import com.example.mybusiness.theme.AppResources

@Composable
fun CustomDropDownMenu(
    items: List<String>,
    onItemSelected: (String) -> Unit,
    selectedItem: String,
    modifier: Modifier = Modifier,
) {
    var expanded by remember {
        mutableStateOf(false)
    }
    var mTextFieldSize by remember {
        mutableStateOf(Size.Zero)
    }
    Box(
        modifier = modifier
            .onGloballyPositioned { coordinates ->
                // This value is used to assign to
                // the DropDown the same width
                mTextFieldSize = coordinates.size.toSize()
            }
    ) {
        Text(text = selectedItem,
            modifier = Modifier
                .clickable { expanded = !expanded }
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .background(color = AppResources.colors.White)
                .padding(top = 8.dp)
        )
        {
            items.forEach {
                DropdownMenuItem(
                    text = {
                        Text(
                            text = it,
                        )
                    },
                    onClick = {
                        expanded = false
                        onItemSelected(it)
                    },
                    modifier = Modifier
                        .padding(8.dp)
                )
            }

        }
    }
}
