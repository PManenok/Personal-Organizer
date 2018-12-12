package com.gmail.pmanenok.personalorganizer.presentation.screen.main.day

import android.databinding.ObservableBoolean
import android.util.Log
import com.gmail.pmanenok.domain.usecases.GetNotesByDayUseCase
import com.gmail.pmanenok.domain.usecases.GetNotesUseCase
import com.gmail.pmanenok.domain.usecases.SearchNotesUseCase
import com.gmail.pmanenok.personalorganizer.app.App
import com.gmail.pmanenok.personalorganizer.presentation.screen.main.MainRouter
import com.gmail.pmanenok.personalorganizer.presentation.base.BaseViewModel
import com.gmail.pmanenok.personalorganizer.presentation.screen.main.day.binding.NoteItemAdapter
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.subjects.PublishSubject
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class DayViewModel : BaseViewModel<MainRouter>() {
    val adapter = NoteItemAdapter() //{ Log.e("DayViewModel", "Item clicked") }
    val clickSubject: PublishSubject<Boolean> = PublishSubject.create()

    @Inject
    public lateinit var notesUseCase: GetNotesByDayUseCase

    var dayId: Long = 0L

    init {
        clickSubject
            .throttleFirst(500L, TimeUnit.MILLISECONDS)
            .subscribeBy {
            }
        App.appComponent.inject(this)
    }

    fun get() {
        val disposable = notesUseCase.getByDay(dayId)
            .subscribeBy(
                onNext = {
                    adapter.cleanItems()
                    adapter.addItems(it)
                    Log.e("aaa", "${adapter.itemList}")
                },
                onError = {
                    router?.showError(it)
                }
            )
        addToDisposable(disposable)
    }
}