package com.gmail.pmanenok.data.entity

import java.util.*

object DateSingleton {
    val today = todayFromCalendar()
    var selectedDay = today

    private fun todayFromCalendar(): Long {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.HOUR_OF_DAY, 0)
        calendar.set(Calendar.MINUTE, 0)
        calendar.set(Calendar.SECOND, 0)
        calendar.set(Calendar.MILLISECOND, 0)
        return calendar.timeInMillis
    }
}