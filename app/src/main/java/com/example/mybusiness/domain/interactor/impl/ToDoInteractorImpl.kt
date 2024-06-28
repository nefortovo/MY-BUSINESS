package com.example.mybusiness.domain.interactor.impl

import com.example.mybusiness.domain.interactor.base.IToDoInteractor
import com.example.mybusiness.domain.model.ToDoModel
import com.example.mybusiness.domain.repository.IToDoRepository
import kotlinx.coroutines.flow.Flow

class ToDoInteractorImpl(
    private val repository: IToDoRepository
) : IToDoInteractor {
    override fun getToDoList(): Flow<List<ToDoModel>> {
        return repository.getToDoList()
    }

    override fun addToDoItem(item: ToDoModel) {
        return repository.addToDoItem(item)
    }

    override fun editToDoItem(item: ToDoModel) {
        return repository.editToDoItem(item)
    }

    override fun deleteToDoItem(item: ToDoModel) {
        return repository.deleteToDoItem(item)
    }

    override fun getToDoItem(toDoId: Int): ToDoModel? {
        return repository.getToDoItem(toDoId)
    }
}