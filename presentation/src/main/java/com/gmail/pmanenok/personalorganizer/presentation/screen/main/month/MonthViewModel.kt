package com.gmail.pmanenok.personalorganizer.presentation.screen.main.month

import android.text.format.DateFormat
import android.util.Log
import android.view.View
import com.gmail.pmanenok.domain.usecases.date.GetSelectedDayUseCase
import com.gmail.pmanenok.domain.usecases.date.RefreshSelectedDayUseCase
import com.gmail.pmanenok.domain.usecases.date.UpdateSelectedDayUseCase
import com.gmail.pmanenok.domain.usecases.note.GetNoteTypesUseCase
import com.gmail.pmanenok.personalorganizer.app.App
import com.gmail.pmanenok.personalorganizer.presentation.base.BaseViewModel
import com.gmail.pmanenok.personalorganizer.presentation.screen.main.MainRouter
import com.gmail.pmanenok.personalorganizer.presentation.views.CellView
import io.reactivex.rxkotlin.subscribeBy
import javax.inject.Inject

class MonthViewModel : BaseViewModel<MainRouter>() {
    val adapter: MonthCalendarAdapter = MonthCalendarAdapter()
    val cellOnClickListener = View.OnClickListener { view ->
        view as CellView
        Log.e("cellOnClickListener", "updateSelectedDayUseCase ${view.dayId}")
        updateSelectedDayUseCase.update(view.dayId)
        router?.goToDayPage()
    }
    val calendarLongClickListener = View.OnLongClickListener {
        refreshSelectedDayUseCase.refresh()
        return@OnLongClickListener true
    }
    /*var selectedDay : Long = 0L
    set(value) {
        field = value
        adapter.setSelectedDay(value)
    }*/

    @Inject
    public lateinit var getSelectedDayUseCase: GetSelectedDayUseCase
    @Inject
    public lateinit var noteTypesUseCase: GetNoteTypesUseCase
    @Inject
    public lateinit var updateSelectedDayUseCase: UpdateSelectedDayUseCase
    @Inject
    public lateinit var refreshSelectedDayUseCase: RefreshSelectedDayUseCase

    init {
        Log.e("MonthViewModel", "init")
        App.appComponent.inject(this)
        adapter.setCalendarParams()
        addToDisposable(getSelectedDayUseCase.get().subscribeBy(
            onNext = {
                Log.e("MonthViewModel", "init selectedDay $it")
                adapter.setSelectedDay(it)
            },
            onError = {
                router?.showError(it)
            }
        ))
        /*val disposable = noteTypesUseCase.getAllNoteTypes()
            .subscribeBy(
                onNext = {
                    Log.e("MonthViewModel", "adapter set ${it.size}")
                    adapter.setItems(it.toMutableMap())
                },
                onError = {
                    router?.showError(it)
                }
            )
        addToDisposable(disposable)*/
    }

    fun refresh() {
        Log.e("MonthViewModel", "refresh")
        val disposable = noteTypesUseCase.getAllNoteTypes().take(1)
            .subscribeBy(
                onNext = {
                    Log.e("MonthViewModel", "adapter set ${it.size}")
                    adapter.setItems(it.toMutableMap())
                },
                onError = {
                    router?.showError(it)
                }
            )
        addToDisposable(disposable)
        Log.e("MonthViewModel", "refreshSelectedDayUseCase")
        refreshSelectedDayUseCase.refresh()
    }
}