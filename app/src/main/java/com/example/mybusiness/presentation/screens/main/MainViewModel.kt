package com.example.mybusiness.presentation.screens.main

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mybusiness.domain.interactor.base.IToDoInteractor
import com.example.mybusiness.domain.model.ToDoModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val toDoInteractor: IToDoInteractor
): ViewModel() {

    var isVisible: Boolean by mutableStateOf(true)

    val _toDoList: Flow<List<ToDoModel>> = toDoInteractor.getToDoList()

    init {
        fetchToDoList()
    }

    fun updateVisible(){
        isVisible = isVisible.not()
    }

    private fun fetchToDoList(){
        viewModelScope.launch {
            _toDoList.collectLatest{ list ->
                Log.d("JOPA", "$list")
            }
        }
    }

    fun deleteToDoItem(item: ToDoModel){
        toDoInteractor.deleteToDoItem(item)
    }

    fun changeEnabledState(item: ToDoModel){
        toDoInteractor.editToDoItem(item.copy(enabled = item.enabled?.not()))
    }
}