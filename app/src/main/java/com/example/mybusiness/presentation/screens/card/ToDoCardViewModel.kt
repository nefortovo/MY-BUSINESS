package com.example.mybusiness.presentation.screens.card

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mybusiness.domain.model.Importance
import com.example.mybusiness.domain.model.ToDoModel
import com.example.mybusiness.domain.repository.IToDoRepository
import com.example.mybusiness.presentation.screens.card.model.ToDoCardUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.LocalDateTime
import javax.inject.Inject

@HiltViewModel
class ToDoCardViewModel @Inject constructor(
    private val toDoRepository: IToDoRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(ToDoCardUIState())
    val uiState = _uiState.asStateFlow()

    private val _description: MutableState<String> = mutableStateOf("")
    val description = _description

    private val _importance: MutableState<String> = mutableStateOf(Importance.NORMAL.importanceName)
    val importance = _importance

    fun updateDescription(newDescription: String) {
        _description.value = newDescription
    }

    var deadLine = mutableStateOf(LocalDate.now())


    fun getItem(id: Int) {
        viewModelScope.launch {
            val model = toDoRepository.getToDoItem(id)
            _uiState.value = _uiState.value.copy(toDoModel = model)
            _description.value = model?.text ?: ""
            _importance.value = model?.importance?.importanceName ?: ""
        }
    }

    fun editElement(){
        with(uiState.value.toDoModel){
            toDoRepository.editToDoItem(ToDoModel(
                id = this?.id ?: ToDoModel.UNDEFINED_ID,
                text = _description.value,
                importance =  Importance.getByName(_importance.value),
                deadLine = deadLine.value,
                enabled = this?.enabled,
                dateCreate = this?.dateCreate,
                dateChanged = LocalDate.now()
                )
            )
        }
    }

    fun changeDate(dateTime: LocalDate){
        deadLine.value = dateTime
    }

    fun changeImportance(newImportance: String){
        _importance.value = newImportance
    }

}