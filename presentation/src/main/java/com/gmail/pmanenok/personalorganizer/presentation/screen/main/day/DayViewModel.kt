package com.gmail.pmanenok.personalorganizer.presentation.screen.main.day

import android.databinding.ObservableField
import android.text.format.DateFormat
import com.gmail.pmanenok.domain.usecases.date.GetSelectedDayUseCase
import com.gmail.pmanenok.domain.usecases.note.GetNotesByDayUseCase
import com.gmail.pmanenok.personalorganizer.app.App
import com.gmail.pmanenok.personalorganizer.presentation.screen.main.MainRouter
import com.gmail.pmanenok.personalorganizer.presentation.base.BaseViewModel
import com.gmail.pmanenok.personalorganizer.presentation.screen.main.day.binding.NoteItemAdapter
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.subjects.PublishSubject
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class DayViewModel : BaseViewModel<MainRouter>() {
    val adapter = NoteItemAdapter {
        router?.goToNoteActivity(it.type, it.id)
    }
    val clickSubject: PublishSubject<Boolean> = PublishSubject.create()
    val dayTitle = ObservableField<String>("Day title")
    var today: Long = 0L
    @Inject
    public lateinit var notesUseCase: GetNotesByDayUseCase

    @Inject
    public lateinit var getSelectedDayUseCase: GetSelectedDayUseCase

    init {
        clickSubject
            .throttleFirst(500L, TimeUnit.MILLISECONDS)
            .subscribeBy {
            }
        App.appComponent.inject(this)
    }

    fun refresh() {
        addToDisposable(getSelectedDayUseCase.get().subscribeBy(
            onNext = {
                dayTitle.set(DateFormat.format("d MMMM yyyy", it).toString())
                today = it
            },
            onError = {
                router?.showError(it)
            }
        ))
        addToDisposable(notesUseCase.getByDay(today)
            .subscribeBy(
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