package com.example.mybusiness.domain.model

import android.content.Context
import com.example.mybusiness.R

enum class Importance {
    LOW,
    NORMAL,
    HIGH;
}

fun Importance.getLocalizedName(context: Context): String{
    return when (this) {
        Importance.LOW -> context.getString(R.string.importance_low)
        Importance.NORMAL -> context.getString(R.string.importance_normal)
        Importance.HIGH -> context.getString(R.string.importance_high)
        else -> context.getString(R.string.importance_normal)
    }
}





