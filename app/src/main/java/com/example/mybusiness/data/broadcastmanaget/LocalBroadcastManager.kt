package com.example.mybusiness.data.broadcastmanaget

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

//Обработку ошибок реализовал пока не подключал т.к не добавлял работу с беком
object LocalBroadcastManager {
    internal val _error: MutableStateFlow<ErrorStatus> = MutableStateFlow(ErrorStatus.Default)
    val error: StateFlow<ErrorStatus> = _error.asStateFlow()
    fun clear(){
        _error.value = ErrorStatus.Default
    }
}

sealed class ErrorStatus(
    open val message: String,
){

    data class Network(
        override val message: String,
    ): ErrorStatus(message)

    object Default: ErrorStatus("")
}