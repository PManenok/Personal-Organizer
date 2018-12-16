package com.gmail.pmanenok.personalorganizer.presentation.screen.main.month

import android.util.Log
import android.view.View
import com.gmail.pmanenok.domain.entity.Notification
import com.gmail.pmanenok.domain.usecases.date.GetSelectedDayUseCase
import com.gmail.pmanenok.domain.usecases.date.RefreshSelectedDayUseCase
import com.gmail.pmanenok.domain.usecases.date.UpdateSelectedDayUseCase
import com.gmail.pmanenok.domain.usecases.note.GetNoteTypesUseCase
import com.gmail.pmanenok.domain.usecases.notification.GetNotificationUseCase
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

    @Inject
    public lateinit var getSelectedDayUseCase: GetSelectedDayUseCase
    @Inject
    public lateinit var noteTypesUseCase: GetNoteTypesUseCase
    @Inject
    public lateinit var updateSelectedDayUseCase: UpdateSelectedDayUseCase
    @Inject
    public lateinit var refreshSelectedDayUseCase: RefreshSelectedDayUseCase
    @Inject
    public lateinit var getNotificationUseCase: GetNotificationUseCase

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
        addToDisposable(noteTypesUseCase.getAllNoteTypes()
            .subscribeBy(
                onNext = {
                    Log.e("MonthViewModel", "adapter set ${it.size}")
                    adapter.setItems(it.toMutableMap())
                },
                onError = {
                    router?.showError(it)
                }
            ))
        addToDisposable(getNotificationUseCase.get().subscribeBy(
            onNext = {
                Log.e("MonthViewModel", "init getNotificationUseCase $it")
                if (it == Notification.DATABASE_CHANGED) getRecords()
            },
            onError = {
                router?.showError(it)
            }
        ))
    }

    fun getRecords() {
        val disposable = noteTypesUseCase.getAllNoteTypes()
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
    }

    fun refresh() {
        Log.e("MonthViewModel", "refresh empty")
        /*val disposable = noteTypesUseCase.getAllNoteTypes().take(1)
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
}