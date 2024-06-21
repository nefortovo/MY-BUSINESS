package com.example.mybusiness.domain.model

import java.time.LocalDate

data class ToDoModel(
    var id: Int = UNDEFINED_ID,
    val text: String,
    val importance: Importance,
    val deadLine: LocalDate?,
    val enabled: Boolean?,
    val dateCreate: LocalDate?,
    val dateChanged: LocalDate,
){
    companion object{
       const val UNDEFINED_ID = -1
    }
}
