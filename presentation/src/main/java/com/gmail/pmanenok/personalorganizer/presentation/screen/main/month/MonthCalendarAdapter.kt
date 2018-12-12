package com.gmail.pmanenok.personalorganizer.presentation.screen.main.month

import android.util.Log
import com.gmail.pmanenok.personalorganizer.presentation.views.CalendarView

class MonthCalendarAdapter : CalendarView.NoteAdapter() {
    init {
        Log.e("MonthCalendarAdapter", "init")
    }

    override fun getMoreDays(dayId: Long, longTime: Long) {

    }
}