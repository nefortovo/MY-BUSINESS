package com.example.mybusiness.presentation.uicomponents.listItems

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mybusiness.R
import com.example.mybusiness.domain.model.Importance
import com.example.mybusiness.domain.model.ToDoModel
import com.example.mybusiness.theme.AppResources
import com.example.mybusiness.theme.CustomAppTheme
import java.time.LocalDate
import java.time.LocalDateTime

@Composable
fun ToDoItem(
    element: ToDoModel,
    modifier: Modifier = Modifier,
) {
    val isImportance by remember {
        mutableStateOf(element.importance == Importance.HIGH)
    }

    Row(
        modifier = modifier
            .background(AppResources.colors.BackSecondary)
            .padding(vertical = 12.dp, horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            modifier = Modifier,
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(
                    id = (if (element.enabled) {
                        R.drawable.checked
                    } else if (isImportance) {
                        R.drawable.selection_controls
                    }else{
                        R.drawable.unchecked
                    }),
                ),
                tint = (if (element.enabled) {
                    AppResources.colors.Green
                } else if (isImportance) {
                    AppResources.colors.Red
                } else{
                    AppResources.colors.SupportSeparator
                }) ,
                contentDescription = null
            )
            Spacer(modifier = Modifier.padding(15.dp))
            Text(
                text = buildAnnotatedString {
                    if (isImportance && element.enabled.not()){
                        withStyle(style = SpanStyle(color = AppResources.colors.Red)) {
                            append("!! ")
                        }
                    }
                    append(element.text)
                },
                style = AppResources.typography.body.body0,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis,
                color = AppResources.colors.LabelPrimary
            )
        }
        Icon(
            imageVector = ImageVector.vectorResource(R.drawable.info_outline),
            contentDescription = null
        )
    }
}

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun ToDoItemHighPreview() {

    CustomAppTheme {
        ToDoItem(
            element = ToDoModel("1", "Task 1", Importance.HIGH, LocalDate.now(), false, LocalDate.now(), LocalDateTime.now()),
            modifier = Modifier
                .fillMaxWidth(),
        )
    }
}

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun ToDoItemLowPreview() {

    CustomAppTheme {
        ToDoItem(
            element = ToDoModel("1", "Task 1", Importance.LOW, LocalDate.now(), false, LocalDate.now(), LocalDateTime.now()),
            modifier = Modifier
                .fillMaxWidth(),
        )
    }
}

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun ToDoItemPreview() {

    CustomAppTheme {
        ToDoItem(
            element = ToDoModel("1", "Task 1", Importance.LOW, LocalDate.now(), true, LocalDate.now(), LocalDateTime.now()),
            modifier = Modifier
                .fillMaxWidth(),
        )
    }
}