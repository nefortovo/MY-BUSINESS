package com.example.mybusiness.data.repository

import com.example.mybusiness.domain.model.Importance
import com.example.mybusiness.domain.model.ToDoModel
import com.example.mybusiness.domain.repository.IToDoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import java.time.LocalDate
import java.time.LocalDateTime

class ToDoRepositoryImpl: IToDoRepository {

    private val currentDate: LocalDate = LocalDate.now()
    private val pastDate: LocalDate = currentDate.minusDays(1)
    private val futureDate: LocalDate = currentDate.plusDays(1)
    private val farFutureDate: LocalDate = currentDate.plusMonths(1)
    private val farPastDate: LocalDate = currentDate.minusMonths(1)

    private val _toDoList = mutableListOf(
        ToDoModel(1, "Task 1", Importance.LOW, futureDate, true, currentDate, currentDate),
        ToDoModel(2, "Task 2", Importance.LOW, futureDate, false, pastDate, currentDate),
        ToDoModel(3, "Task 3", Importance.LOW, farFutureDate, true, currentDate, pastDate),
        ToDoModel(4, "Task 4", Importance.LOW, pastDate, false, pastDate, futureDate),

        // Normal Importance
        ToDoModel(5, "Task 5", Importance.NORMAL, currentDate, true, pastDate, currentDate),
        ToDoModel(6, "Task 6", Importance.NORMAL, farFutureDate, false, currentDate, futureDate),
        ToDoModel(7, "Task 7", Importance.NORMAL, futureDate, true, farPastDate, currentDate),
        ToDoModel(8, "Task 8", Importance.NORMAL, pastDate, false, currentDate, farPastDate),

        // High Importance
        ToDoModel(9, "Task 9", Importance.HIGH, pastDate, true, currentDate, currentDate),
        ToDoModel(10, "Task 10", Importance.HIGH, futureDate, false, farPastDate, futureDate),
        ToDoModel(11, "Task 11", Importance.HIGH, currentDate, true, pastDate, farFutureDate),
        ToDoModel(12, "Task 12", Importance.HIGH, farFutureDate, false, currentDate, pastDate),

        // Mixed Importance
        ToDoModel(13, "Task 13", Importance.LOW, currentDate, true, farPastDate, farFutureDate),
        ToDoModel(14, "Task 14", Importance.NORMAL, futureDate, false, pastDate, futureDate),
        ToDoModel(15, "Task 15", Importance.HIGH, farFutureDate, true, farPastDate, currentDate),
        ToDoModel(16, "Task 16", Importance.LOW, pastDate, false, currentDate, futureDate),
        ToDoModel(17, "Task 17", Importance.NORMAL, farPastDate, true, futureDate, currentDate),
        ToDoModel(18, "Task 18", Importance.HIGH, futureDate, false, pastDate, farPastDate),
        ToDoModel(19, "Task 19", Importance.LOW, farFutureDate, true, currentDate, futureDate),
        ToDoModel(20, "Task 20", Importance.NORMAL, currentDate, false, pastDate, farFutureDate)
    )

    private val toDoList = MutableStateFlow<List<ToDoModel>>(_toDoList.toList())

    private var autoIncId = 20

    override fun getToDoList(): Flow<List<ToDoModel>> {
        return toDoList.map { list ->
            list.sortedBy { it.id } }
    }

    override fun addToDoItem(item: ToDoModel) {
        if (item.id == ToDoModel.UNDEFINED_ID) {
            item.id = autoIncId++
        }
        _toDoList.add(item)
        updateList()
    }

    override fun editToDoItem(item: ToDoModel) {
        val oldItem = getToDoItem(item.id)
        _toDoList.remove(oldItem)
        addToDoItem(item)
    }

    override fun deleteToDoItem(item: ToDoModel) {
        _toDoList.remove(item)
        updateList()
    }

    override fun getToDoItem(toDoId: Int): ToDoModel? {
        return _toDoList.find { toDoId == it.id }
    }

    private fun updateList(){
        toDoList.value = _toDoList.toList()
    }
}