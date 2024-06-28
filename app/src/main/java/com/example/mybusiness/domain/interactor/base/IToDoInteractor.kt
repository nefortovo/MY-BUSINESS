package com.example.mybusiness.domain.interactor.base

import com.example.mybusiness.domain.model.ToDoModel
import kotlinx.coroutines.flow.Flow

interface IToDoInteractor {
    fun getToDoList(): Flow<List<ToDoModel>>

    fun addToDoItem(item: ToDoModel)

    fun editToDoItem(item: ToDoModel)

    fun deleteToDoItem(item: ToDoModel)

    fun getToDoItem(toDoId: Int): ToDoModel?
}