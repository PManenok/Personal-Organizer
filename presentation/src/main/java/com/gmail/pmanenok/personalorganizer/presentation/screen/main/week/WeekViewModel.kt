package com.gmail.pmanenok.personalorganizer.presentation.screen.main.week

import android.databinding.ObservableField
import android.util.Log
import com.gmail.pmanenok.domain.entity.Notification
import com.gmail.pmanenok.domain.usecases.date.GetSelectedDayUseCase
import com.gmail.pmanenok.domain.usecases.date.UpdateSelectedDayUseCase
import com.gmail.pmanenok.domain.usecases.note.GetNotesTitlesByDayRangeUseCase
import com.gmail.pmanenok.domain.usecases.notification.GetNotificationUseCase
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
        set(value) {
            field = value
            Log.e("WeekViewModel", "weekEndDay set")
            Log.e("WeekViewModel", "before getNotesTitlesByDayRangeUseCase $weekBeginDay $weekEndDay")
            addToDisposable(getNotesTitlesByDayRangeUseCase.getByDay(weekBeginDay, weekEndDay).subscribeBy(
                onNext = {
                    Log.e(
                        "WeekViewModel",
                        "weekEndDay set getNotesTitlesByDayRangeUseCase $weekBeginDay $weekEndDay"
                    )
                    adapter.cleanItems()
                    adapter.addItems(it)
                    Log.e("WeekViewModel", "weekEndDay set getNotesTitlesByDayRangeUseCase $it")
                },
                onError = {
                    router?.showError(it)
                }
            ))
        }
    @Inject
    public lateinit var getNotificationUseCase: GetNotificationUseCase
    @Inject
    public lateinit var updateSelectedDayUseCase: UpdateSelectedDayUseCase
    @Inject
    public lateinit var getNotesTitlesByDayRangeUseCase: GetNotesTitlesByDayRangeUseCase
    @Inject
    public lateinit var getSelectedDayUseCase: GetSelectedDayUseCase

    init {
        Log.e("WeekViewModel", "init")
        App.appComponent.inject(this)
        addToDisposable(getSelectedDayUseCase.get().subscribeBy(
            onNext = {
                Log.e("WeekViewModel", "getSelectedDayUseCase weekTitle selectedDay countWeekRange")
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
        addToDisposable(getNotificationUseCase.get().subscribeBy(
            onNext = {
                Log.e("WeekViewModel", "init getNotificationUseCase $it")
                if (it == Notification.DATABASE_CHANGED) getRecords()
            },
            onError = {
                router?.showError(it)
            }
        ))
    }

    private fun countWeekRange(it: Long) {
        Log.e("WeekViewModel", "countWeekRange $weekBeginDay $weekEndDay")
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

    fun getRecords() {
        addToDisposable(getNotesTitlesByDayRangeUseCase.getByDay(weekBeginDay, weekEndDay).subscribeBy(
            onNext = {
                adapter.cleanItems()
                adapter.addItems(it)
                Log.e("WeekViewModel", "getRecords getNotesTitlesByDayRangeUseCase")
            },
            onError = {
                router?.showError(it)
            }
        ))
    }

    fun refresh() {
        Log.e("WeekViewModel", "refresh empty")
        /*if (weekBeginDay != 0L && weekEndDay != 0L)
            addToDisposable(getNotesTitlesByDayRangeUseCase.getByDay(weekBeginDay, weekEndDay).subscribeBy(
                onNext = {
                    adapter.cleanItems()
                    adapter.addItems(it)
                    Log.e("WeekViewModel", "refresh getNotesTitlesByDayRangeUseCase")
                },
                onError = {
                    router?.showError(it)
                }
            ))*/
    }
}