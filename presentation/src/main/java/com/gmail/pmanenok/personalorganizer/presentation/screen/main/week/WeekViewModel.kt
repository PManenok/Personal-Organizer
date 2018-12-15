package com.gmail.pmanenok.personalorganizer.presentation.screen.main.week

import android.databinding.ObservableField
import com.gmail.pmanenok.domain.usecases.date.GetSelectedDayUseCase
import com.gmail.pmanenok.domain.usecases.date.UpdateSelectedDayUseCase
import com.gmail.pmanenok.domain.usecases.note.GetNotesByDayRangeUseCase
import com.gmail.pmanenok.personalorganizer.app.App
import com.gmail.pmanenok.personalorganizer.presentation.base.BaseViewModel
import com.gmail.pmanenok.personalorganizer.presentation.screen.main.MainRouter
import com.gmail.pmanenok.personalorganizer.presentation.screen.main.week.binding.DayItemAdapter
import io.reactivex.rxkotlin.subscribeBy
import java.util.*
import javax.inject.Inject

class WeekViewModel : BaseViewModel<MainRouter>() {
    val adapter = DayItemAdapter {
        updateSelectedDayUseCase.update(it.first)
        router?.goToDayPage()
    }
    val weekTitle = ObservableField<String>("Day title")
    var selectedDay: Long = 0L
    var weekBeginDay: Long = 0L
    var weekEndDay: Long = 0L

    @Inject
    public lateinit var updateSelectedDayUseCase: UpdateSelectedDayUseCase
    @Inject
    public lateinit var getNotesByDayRangeUseCase: GetNotesByDayRangeUseCase
    @Inject
    public lateinit var getSelectedDayUseCase: GetSelectedDayUseCase

    init {
        App.appComponent.inject(this)
    }

    private fun countWeekRange(it: Long) {
        val calendar = Calendar.getInstance()
        calendar.firstDayOfWeek = Calendar.MONDAY
        calendar.timeInMillis = it
        while (calendar.get(Calendar.DAY_OF_WEEK) != calendar.firstDayOfWeek) {
            calendar.roll(Calendar.DAY_OF_WEEK, false)
        }
        weekBeginDay = calendar.timeInMillis
        calendar.roll(Calendar.DAY_OF_WEEK, false)
        weekEndDay = calendar.timeInMillis
    }

    fun refresh() {
        addToDisposable(getSelectedDayUseCase.get().subscribeBy(
            onNext = {
                val calendar = Calendar.getInstance()
                calendar.firstDayOfWeek = Calendar.MONDAY
                calendar.timeInMillis = it
                val weekNum = calendar[Calendar.WEEK_OF_YEAR]
                weekTitle.set("Week " + weekNum.toString())
                selectedDay = it
                countWeekRange(it)
            },
            onError = {
                router?.showError(it)
            }
        ))
        addToDisposable(getNotesByDayRangeUseCase.getByDay(weekBeginDay, weekEndDay).subscribeBy(
            onNext = {
                adapter.cleanItems()
                adapter.addItems(it)
            },
            onError = {
                router?.showError(it)
            }
        ))
    }
}