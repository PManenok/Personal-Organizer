package com.gmail.pmanenok.personalorganizer.presentation.screen.main.day

import android.databinding.ObservableField
import android.text.format.DateFormat
import android.util.Log
import com.gmail.pmanenok.domain.entity.NoteType
import com.gmail.pmanenok.domain.usecases.date.GetSelectedDayUseCase
import com.gmail.pmanenok.domain.usecases.date.UpdateSelectedDayUseCase
import com.gmail.pmanenok.domain.usecases.note.DeleteRecordUseCase
import com.gmail.pmanenok.domain.usecases.note.GetRecordsByDayUseCase
import com.gmail.pmanenok.personalorganizer.app.App
import com.gmail.pmanenok.personalorganizer.presentation.screen.main.MainRouter
import com.gmail.pmanenok.personalorganizer.presentation.base.BaseViewModel
import com.gmail.pmanenok.personalorganizer.presentation.screen.main.day.binding.RecordItemAdapter
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.subjects.PublishSubject
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class DayViewModel : BaseViewModel<MainRouter>() {
    val adapter = RecordItemAdapter({ note ->
        router?.goToNoteActivity(note.type, note.id, note.date)
    }, { note ->
        Log.e("RecordItemAdapter", "OnLongClickListener")
        when (note.type) {
            NoteType.TYPE_NOTE -> deleteRecordUseCase.deleteNoteRecord(note.id)
            NoteType.TYPE_BIRTHDAY -> deleteRecordUseCase.deleteBirthdayRecord(note.id)
            NoteType.TYPE_LIST -> deleteRecordUseCase.deleteListRecord(note.id)
            else -> null
        }/*?.concatWith {
            updateSelectedDayUseCase.update(note.date)
        }*/?.subscribeBy(
            onComplete = {
                updateSelectedDayUseCase.update(note.date)
            },
            onError = { error ->
                router?.showError(error)
            }
        )?.dispose()
        //updateSelectedDayUseCase.update(note.date)
    })

    val clickSubject: PublishSubject<Boolean> = PublishSubject.create()
    val dayTitle = ObservableField<String>("Day title")
    var day: Long = 0L
        set(value) {
            field = value
            Log.e("DayViewModel", "day set")
            addToDisposable(recordsUseCase.getByDay(day)
                .subscribeBy(
                    onNext = {
                        Log.e("DayViewModel day set", "recordsUseCase $it")
                        adapter.cleanItems()
                        adapter.addItems(it)
                    },
                    onError = {
                        router?.showError(it)
                    }
                ))
        }
    @Inject
    public lateinit var updateSelectedDayUseCase: UpdateSelectedDayUseCase
    @Inject
    public lateinit var recordsUseCase: GetRecordsByDayUseCase

    @Inject
    public lateinit var getSelectedDayUseCase: GetSelectedDayUseCase

    @Inject
    public lateinit var deleteRecordUseCase: DeleteRecordUseCase

    init {
        Log.e("DayViewModel", "init")
        clickSubject
            .throttleFirst(500L, TimeUnit.MILLISECONDS)
            .subscribeBy {
                Log.e("DayViewModel", "clickSubject")
            }
        App.appComponent.inject(this)

        addToDisposable(getSelectedDayUseCase.get().subscribeBy(
            onNext = {
                Log.e("DayViewModel", "init day and dayTitle $it")
                dayTitle.set(DateFormat.format("d MMMM yyyy", it).toString())
                day = it
            },
            onError = {
                router?.showError(it)
            }
        ))
    }

    fun refresh() {
        Log.e("DayViewModel", "refresh")
        /*if (day != 0L) {
            addToDisposable(recordsUseCase.getByDay(day)
                .subscribeBy(
                    onNext = {
                        Log.e("DayViewModel", "refresh recordsUseCase $it")
                        adapter.cleanItems()
                        adapter.addItems(it)
                    },
                    onError = {
                        router?.showError(it)
                    }
                ))
        }*/
    }
}