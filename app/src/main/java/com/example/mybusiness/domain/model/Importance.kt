package com.example.mybusiness.domain.model

enum class Importance(val importanceName: String) {
    LOW("Низкий"),
    NORMAL("Обычный"),
    HIGH("Высокий");


    companion object {
        fun getByName(name: String): Importance =
            when (name) {
                LOW.importanceName -> LOW
                NORMAL.importanceName -> NORMAL
                HIGH.importanceName -> HIGH
                else -> NORMAL
            }

    }
}



