package com.example.mybusiness.presentation.screens.main

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mybusiness.domain.interactor.base.IToDoInteractor
import com.example.mybusiness.domain.model.ToDoModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val toDoInteractor: IToDoInteractor
): ViewModel() {

    var isVisible: Boolean by mutableStateOf(false)
        private set

    private val _toDoList: MutableStateFlow<List<ToDoModel>> = MutableStateFlow(emptyList())
    val toDoList: StateFlow<List<ToDoModel>> = _toDoList.asStateFlow()

    init {
        fetchToDoList()
    }

    fun updateVisible(){
        isVisible = isVisible.not()
    }

    private fun fetchToDoList() {
        viewModelScope.launch(Dispatchers.IO) {
            toDoInteractor.getToDoList()
                .collect {
                    _toDoList.value = it
                }
        }
    }

    fun deleteToDoItem(item: ToDoModel){
        viewModelScope.launch(Dispatchers.IO) {
            toDoInteractor.deleteToDoItem(item)
        }
    }

    fun changeEnabledState(item: ToDoModel){
        viewModelScope.launch(Dispatchers.IO){
            toDoInteractor.editToDoItem(item.copy(enabled = item.enabled.not()))

        }
    }
}