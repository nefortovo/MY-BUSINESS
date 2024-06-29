package com.example.mybusiness.presentation.screens.card

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mybusiness.domain.interactor.base.IToDoInteractor
import com.example.mybusiness.domain.model.Importance
import com.example.mybusiness.domain.model.ToDoModel
import com.example.mybusiness.presentation.screens.card.model.ToDoCardUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.LocalDateTime
import javax.inject.Inject

@HiltViewModel
class ToDoCardViewModel @Inject constructor(
    private val toDoInteractor: IToDoInteractor
) : ViewModel() {

    private val _uiState = MutableStateFlow(ToDoCardUIState())
    val uiState = _uiState.asStateFlow()

    fun updateDescription(newDescription: String) {
        _uiState.update { currentState ->
            currentState.copy(
                description = newDescription
            )
        }
    }

    fun updateEnabled() {
        _uiState.update { currentState ->
            currentState.copy(
                enabled = !currentState.enabled.not()
            )
        }
    }

    fun getItem(id: String) {
        viewModelScope.launch {
            val model = toDoInteractor.getToDoItem(id)
            _uiState.value = _uiState.value.copy(toDoModel = model)
            _uiState.update{ it.copy(description = model?.text ?: "")}
            _uiState.update{ it.copy(importance = model?.importance ?: Importance.NORMAL)}
            _uiState.update{ it.copy(deadLine = model?.deadLine)}
            _uiState.update{ it.copy(enabled = model?.enabled ?: false)}
        }
    }

    fun editElement(){
        viewModelScope.launch {
            with(uiState.value){
                toDoInteractor.editToDoItem(ToDoModel(
                    id = toDoModel?.id ?: ToDoModel.UNDEFINED_ID,
                    text = description,
                    importance = importance,
                    deadLine = deadLine,
                    enabled = enabled,
                    dateCreate = toDoModel?.dateCreate ?: LocalDate.now(),
                    dateChanged = LocalDateTime.now(),
                )
                )
            }
        }
    }

    fun changeDate(newDeadline: LocalDate){
        _uiState.update {
            it.copy(
                deadLine = newDeadline
            )
        }
    }

    fun deleteToDoItem(item: ToDoModel){
        viewModelScope.launch {
            toDoInteractor.deleteToDoItem(item)
        }
    }

    fun changeImportance(newImportance: Importance){
        _uiState.update {
            it.copy(
                importance = newImportance
            )
        }
    }

}