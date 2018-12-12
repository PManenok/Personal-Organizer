package com.gmail.pmanenok.personalorganizer.presentation.screen.main.month

import android.util.Log
import com.gmail.pmanenok.domain.usecases.GetNoteTypesUseCase
import com.gmail.pmanenok.personalorganizer.app.App
import com.gmail.pmanenok.personalorganizer.presentation.base.BaseViewModel
import com.gmail.pmanenok.personalorganizer.presentation.screen.main.MainRouter
import io.reactivex.rxkotlin.subscribeBy
import javax.inject.Inject

class MonthViewModel : BaseViewModel<MainRouter>() {
    val adapter: MonthCalendarAdapter = MonthCalendarAdapter()

    @Inject
    public lateinit var notesUseCase: GetNoteTypesUseCase

    init {
        Log.e("MonthViewModel", "init")

        App.appComponent.inject(this)
        val disposable = notesUseCase.getAllNoteTypes()
            .subscribeBy(
                onNext = {
                    adapter.setItems(it.toMutableMap())
                    Log.e("MonthViewModel", "adapter.setItems")
                },
                onError = {
                    router?.showError(it)
                }
            )
        addToDisposable(disposable)
        adapter.setCalendar()
    }
}