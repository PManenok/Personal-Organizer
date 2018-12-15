package com.gmail.pmanenok.personalorganizer.presentation.screen.main.week.binding

import android.databinding.ObservableField
import android.text.format.DateFormat
import com.gmail.pmanenok.domain.entity.Note
import com.gmail.pmanenok.personalorganizer.presentation.base.recycler.BaseItemViewModel
import java.lang.StringBuilder

class DayItemViewModel : BaseItemViewModel<Pair<Long, List<String>>>() {
    val weekDayTitle = ObservableField<String>("weekDayTitle")
    var id: Long = 0L
    val notesTitles = ObservableField<String>("title")

    override fun bindItem(item: Pair<Long, List<String>>, position: Int) {
        weekDayTitle.set(DateFormat.format("E\nd", item.first).toString().toUpperCase())
        id = item.first
        val stringBuilder = StringBuilder()
        val count = item.second.count()
        var itemsCount = 0
        for (entity in item.second) {
            itemsCount++
            stringBuilder.append(entity)
            if (itemsCount != count)
                stringBuilder.append(", ")
        }
        if (count != 0) notesTitles.set(stringBuilder.toString())
        else notesTitles.set("")
    }
}