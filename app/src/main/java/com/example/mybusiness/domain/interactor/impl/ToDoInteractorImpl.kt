package com.example.mybusiness.domain.interactor.impl

import com.example.mybusiness.domain.interactor.base.IToDoInteractor
import com.example.mybusiness.domain.model.ToDoModel
import com.example.mybusiness.domain.repository.IToDoRepository
import kotlinx.coroutines.flow.Flow

class ToDoInteractorImpl(
    private val repository: IToDoRepository
) : IToDoInteractor {
    override suspend fun getToDoList(): Flow<List<ToDoModel>> {
        return repository.getToDoList()
    }

    override suspend fun addToDoItem(item: ToDoModel) {
        return repository.addToDoItem(item)
    }

    override suspend fun editToDoItem(item: ToDoModel) {
        return repository.editToDoItem(item)
    }

    override suspend fun deleteToDoItem(item: ToDoModel) {
        return repository.deleteToDoItem(item)
    }

    override suspend fun getToDoItem(toDoId: String): ToDoModel? {
        return repository.getToDoItem(toDoId)
    }
}