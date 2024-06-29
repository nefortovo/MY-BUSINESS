package com.example.mybusiness.presentation.screens.card.model

import com.example.mybusiness.domain.model.Importance
import com.example.mybusiness.domain.model.ToDoModel
import java.time.LocalDate

data class ToDoCardUIState(
    val toDoModel: ToDoModel? = null,
    val description: String = "",
    val importance: Importance = Importance.NORMAL,
    val enabled: Boolean = false,
    val deadLine: LocalDate? = null
)
