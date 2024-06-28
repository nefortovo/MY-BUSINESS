package com.example.mybusiness.presentation.uicomponents.listItems

import android.graphics.drawable.Icon
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.ExperimentalWearMaterialApi
import androidx.wear.compose.material.FractionalThreshold
import androidx.wear.compose.material.rememberSwipeableState
import androidx.wear.compose.material.swipeable
import com.example.mybusiness.R
import com.example.mybusiness.domain.model.ToDoModel
import com.example.mybusiness.theme.AppResources

@OptIn(ExperimentalWearMaterialApi::class)
@Composable
fun ToDoItem(
    element: ToDoModel,
    modifier: Modifier = Modifier,
) {
    val sizePx = with(LocalDensity.current) { 75.dp.toPx() }

    Row(
        modifier = modifier
            .background(AppResources.colors.White)
            .padding(vertical = 12.dp, horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            modifier = Modifier,
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Icon(imageVector = ImageVector.vectorResource(
                id= if (element.enabled!!) R.drawable.checked else R.drawable.unchecked,
            ),
                tint = if (element.enabled) AppResources.colors.Green else AppResources.colors.SupportSeparator,
                contentDescription = null)
            Spacer(modifier = Modifier.padding(15.dp))
            Text(text = element.text, style = AppResources.typography.body.body0)
        }
        IconButton(onClick = { /*TODO*/ }) {
            Icon(imageVector = ImageVector.vectorResource(R.drawable.info_outline), contentDescription = null)
        }
    }
}