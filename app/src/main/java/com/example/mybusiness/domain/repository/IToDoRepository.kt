package com.example.mybusiness.domain.repository

import com.example.mybusiness.domain.model.ToDoModel
import kotlinx.coroutines.flow.Flow

interface IToDoRepository {
    suspend fun getToDoList(): Flow<List<ToDoModel>>

    suspend fun addToDoItem(item: ToDoModel)

    suspend fun editToDoItem(item: ToDoModel)

    suspend fun deleteToDoItem(item: ToDoModel)

    suspend fun getToDoItem(toDoId: String): ToDoModel?
}