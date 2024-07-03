package com.example.mybusiness.data.repository

import com.example.mybusiness.domain.model.Importance
import com.example.mybusiness.domain.model.ToDoModel
import com.example.mybusiness.domain.repository.IToDoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import java.time.LocalDate
import java.time.LocalDateTime

class ToDoRepositoryImpl: IToDoRepository, BaseRepository() {

    private val currentDate: LocalDate = LocalDate.now()
    private val pastDate: LocalDate = currentDate.minusDays(1)
    private val futureDate: LocalDate = currentDate.plusDays(1)
    private val farFutureDate: LocalDate = currentDate.plusMonths(1)
    private val farPastDate: LocalDate = currentDate.minusMonths(1)

    private val _toDoList = mutableListOf(
        ToDoModel("1", "FJKSDGNJKSFNSADJKFDSGJKFNDFJKSANDFJKDSGSNFJKDNFSJKDSLGNJLKDNFSJKLDSNDSJKLGNFJKGNFJKGNFDJKGNFDJKGNFDGJKNFDGJKFNDGJKFDNGJKDFNGJKDFGNJKFDGNJKFDGNFJKDGNDFJKGNDFJKGNDFJKLGNFDJKGNDFKJGNFDJKGNJKFDGNDFJKGNDFJKGNFJKDGNFJKDGNFJKDGNDFJKGNFDKJGN", Importance.LOW, futureDate, true, currentDate, LocalDateTime.now()),
        ToDoModel("2", "Task 2", Importance.LOW, futureDate, false, pastDate, LocalDateTime.now().plusMinutes(10)),
        ToDoModel("3", "Task 3", Importance.LOW, farFutureDate, true, currentDate, LocalDateTime.now().plusHours(1)),
        ToDoModel("4", "Task 4", Importance.LOW, pastDate, false, pastDate, LocalDateTime.now().plusDays(1)),

        // Normal Importance
        ToDoModel("5", "Task 5", Importance.NORMAL, currentDate, true, pastDate, LocalDateTime.now().minusMinutes(30)),
        ToDoModel("6", "Task 6", Importance.NORMAL, farFutureDate, false, currentDate, LocalDateTime.now().minusHours(2)),
        ToDoModel("7", "Task 7", Importance.NORMAL, futureDate, true, farPastDate, LocalDateTime.now().minusDays(1)),
        ToDoModel("8", "Task 8", Importance.NORMAL, pastDate, false, currentDate, LocalDateTime.now().minusMonths(1)),

        // High Importance
        ToDoModel("9", "Task 9", Importance.HIGH, pastDate, true, currentDate, LocalDateTime.now().plusMinutes(20)),
        ToDoModel("10", "Task 10", Importance.HIGH, futureDate, false, farPastDate, LocalDateTime.now().plusDays(2)),
        ToDoModel("11", "Task 11", Importance.HIGH, currentDate, true, pastDate, LocalDateTime.now().plusHours(3)),
        ToDoModel("12", "Task 12", Importance.HIGH, farFutureDate, false, currentDate, LocalDateTime.now().plusMonths(1)),

        // Mixed Importance
        ToDoModel("13", "Task 13", Importance.LOW, currentDate, true, farPastDate, LocalDateTime.now().minusMinutes(15)),
        ToDoModel("14", "Task 14", Importance.NORMAL, futureDate, false, pastDate, LocalDateTime.now().minusHours(1)),
        ToDoModel("15", "Task 15", Importance.HIGH, farFutureDate, true, farPastDate, LocalDateTime.now().minusDays(2)),
        ToDoModel("16", "Task 16", Importance.LOW, pastDate, false, currentDate, LocalDateTime.now().minusSeconds(30)),
        ToDoModel("17", "Task 17", Importance.NORMAL, farPastDate, true, futureDate, LocalDateTime.now().plusSeconds(15)),
        ToDoModel("18", "Task 18", Importance.HIGH, futureDate, false, pastDate, LocalDateTime.now().plusMinutes(45)),
        ToDoModel("19", "Task 19", Importance.LOW, farFutureDate, true, currentDate, LocalDateTime.now().plusDays(3)),
        ToDoModel("20", "Task 20", Importance.NORMAL, currentDate, false, pastDate, LocalDateTime.now().plusHours(2))
    )

    private val toDoList = MutableStateFlow(_toDoList.toList())

    private var autoIncId = 20

    override suspend fun getToDoList(): Flow<List<ToDoModel>> {
        return toDoList.map { list ->
            list.sortedBy { it.dateChanged } }
    }

    override suspend fun addToDoItem(item: ToDoModel) {
        if (item.id == ToDoModel.UNDEFINED_ID) {
            item.id = autoIncId++.toString()
        }
        _toDoList.add(item)
        updateList()
    }

    override suspend fun editToDoItem(item: ToDoModel) {
        val oldItem = getToDoItem(item.id)
        _toDoList.remove(oldItem)
        addToDoItem(item)
    }

    override suspend fun deleteToDoItem(item: ToDoModel) {
        _toDoList.remove(item)
        updateList()
    }

    override suspend fun getToDoItem(toDoId: String): ToDoModel? {
        return _toDoList.find { toDoId == it.id }
    }

    private fun updateList(){
        toDoList.value = _toDoList.toList()
    }
}