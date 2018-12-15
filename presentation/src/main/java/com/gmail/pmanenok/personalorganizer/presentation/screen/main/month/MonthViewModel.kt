package com.gmail.pmanenok.personalorganizer.presentation.screen.main.month

import android.view.View
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
        updateSelectedDayUseCase.update(view.dayId)
        router?.goToDayPage()
    }

    @Inject
    public lateinit var noteTypesUseCase: GetNoteTypesUseCase
    @Inject
    public lateinit var updateSelectedDayUseCase: UpdateSelectedDayUseCase
    @Inject
    public lateinit var refreshSelectedDayUseCase: RefreshSelectedDayUseCase

    init {
        adapter.setCalendar()
        App.appComponent.inject(this)
        val disposable = noteTypesUseCase.getAllNoteTypes()
            .subscribeBy(
                onNext = {
                    adapter.setItems(it.toMutableMap())
                },
                onError = {
                    router?.showError(it)
                }
            )
        addToDisposable(disposable)
    }

    fun refresh() {
        refreshSelectedDayUseCase.refresh()
    }
}