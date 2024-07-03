package com.example.mybusiness.presentation.uicomponents.pickers

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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import com.example.mybusiness.domain.model.Importance
import com.example.mybusiness.domain.model.getLocalizedName
import com.example.mybusiness.theme.AppResources

@Composable
fun CustomDropDownMenu(
    items: List<Importance>,
    onItemSelected: (Importance) -> Unit,
    selectedItem: Importance,
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current

    var expanded by remember {
        mutableStateOf(false)
    }
    var mTextFieldSize by remember {
        mutableStateOf(Size.Zero)
    }
    Box(
        modifier = modifier
            .onGloballyPositioned { coordinates ->
                mTextFieldSize = coordinates.size.toSize()
            }
    ) {

        Text(text = selectedItem.getLocalizedName(context),
            modifier = Modifier
                .clickable { expanded = !expanded },
            color = AppResources.colors.LabelTertiary
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
        )
        {
            items.forEach {
                DropdownMenuItem(
                    text = {
                        Text(
                            text = it.getLocalizedName(context),
                            color = AppResources.colors.LabelPrimary
                        )
                    },
                    onClick = {
                        expanded = false
                        onItemSelected(it)
                    },
                    modifier = Modifier
                        .background(AppResources.colors.BackElevated)
                        .padding(16.dp)
                )
            }

        }
    }
}
