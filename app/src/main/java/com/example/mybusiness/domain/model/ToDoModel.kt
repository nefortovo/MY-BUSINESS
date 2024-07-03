package com.example.mybusiness.domain.model

import java.time.LocalDate
import java.time.LocalDateTime

data class ToDoModel(
    var id: String = UNDEFINED_ID,
    val text: String,
    val importance: Importance? = Importance.NORMAL,
    val deadLine: LocalDate? = null,
    val enabled: Boolean,
    val dateCreate: LocalDate,
    val dateChanged: LocalDateTime,
){
    companion object{
       const val UNDEFINED_ID = "-1"
    }
}
