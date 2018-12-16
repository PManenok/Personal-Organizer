package com.gmail.pmanenok.personalorganizer.presentation.screen.note.noterec

import android.databinding.ObservableBoolean
import android.databinding.ObservableField
import android.util.Log
import android.view.View
import com.gmail.pmanenok.domain.entity.Note
import com.gmail.pmanenok.domain.entity.NoteType
import com.gmail.pmanenok.domain.usecases.note.AddRecordUseCase
import com.gmail.pmanenok.domain.usecases.note.DeleteRecordUseCase
import com.gmail.pmanenok.domain.usecases.note.GetRecordByIdUseCase
import com.gmail.pmanenok.domain.usecases.note.UpdateRecordUseCase
import com.gmail.pmanenok.domain.usecases.notification.SendNotificationUseCase
import com.gmail.pmanenok.personalorganizer.app.App
import com.gmail.pmanenok.personalorganizer.presentation.base.BaseViewModel
import com.gmail.pmanenok.personalorganizer.presentation.screen.note.NoteRouter
import com.gmail.pmanenok.personalorganizer.presentation.utils.todayFromLong
import io.reactivex.Completable
import io.reactivex.rxkotlin.subscribeBy
import java.util.*
import javax.inject.Inject

class NoteRecordViewModel : BaseViewModel<NoteRouter>() {
    @Inject
    public lateinit var sendNotificationUseCase: SendNotificationUseCase
    @Inject
    public lateinit var getRecordByIdUseCase: GetRecordByIdUseCase
    @Inject
    public lateinit var addRecordUseCase: AddRecordUseCase
    @Inject
    public lateinit var updateRecordUseCase: UpdateRecordUseCase
    @Inject
    public lateinit var deleteRecordUseCase: DeleteRecordUseCase

    init {
        App.appComponent.inject(this)
    }

    val title = ObservableField<String>()
    val textNote = ObservableField<String>()
    val notify = ObservableBoolean(false)
    var note: Note? = null
    var day: Long = 0L
        set(value) {
            field = value
            Log.e("NoteRecordViewModel", "set day $value")
        }
    private var noteId: String = ""

    fun setRecordId(id: String) {
        if (noteId != "") return
        noteId = id
        bindNote()
    }

    val backOnClick = View.OnClickListener {
        Log.e("NoteRecordViewModel", "backOnClick")
        router?.goBack()
    }
    val notifyOnClick = View.OnClickListener {
        //router?.showNotify()
        Log.e("NoteRecordViewModel", "notifyOnClick")
        val oldNote = note
        if (oldNote != null) {
            addToDisposable(updateRecordUseCase.update(updateNote(oldNote)).andThen { observer ->
                sendNotificationUseCase.sendNotificationDatabaseChanged()
                observer.onComplete()
            }.subscribeBy(
                onError = { error ->
                    router?.showError(error)
                }
            ))
        } else if (!title.get().isNullOrBlank() && !textNote.get().isNullOrBlank()) {
            addToDisposable(addRecordUseCase.add(createNote()).andThen { observer ->
                sendNotificationUseCase.sendNotificationDatabaseChanged()
                observer.onComplete()
            }.subscribeBy(
                onError = { error ->
                    router?.showError(error)
                }
            ))
        }
        router?.goBack()
    }

    private fun bindNote() {
        addToDisposable(getRecordByIdUseCase.getNoteRecordById(noteId).subscribeBy(
            onNext = {
                note = it
                title.set(it.title)
                textNote.set(it.textNote)
            },
            onError = {
                router?.showError(it)
            }
        ))

    }

    /*val notifyOnLongClick = View.OnLongClickListener {
        //router?.showNotify()
        Log.e("NoteRecordViewModel", "notifyOnLongClick")
        val oldNote = note
        if (oldNote != null) {
            addToDisposable(updateRecordUseCase.update(updateNote(oldNote)).subscribeBy(
                onError = {
                    router?.showError(it)
                }
            ))
        } else if (!title.get().isNullOrBlank() && !textNote.get().isNullOrBlank()) {
            addToDisposable(addRecordUseCase.add(createNote()).subscribeBy(
                onError = {
                    router?.showError(it)
                }
            ))
        }
        true
    }*/

    private fun createNote(): Note {
        Log.e("NoteRecordViewModel", "createNote")
        val calendar = Calendar.getInstance()
        val id = calendar.timeInMillis.toString()
        val createDay = if (day != 0L) day else todayFromLong(calendar.timeInMillis)
        val createTitle = title.get() ?: ""
        val createTextNote = textNote.get() ?: ""
        val splitList = createTextNote.split("\n")
        val lineCount = if (splitList.size >= 2) 1 else if (splitList.size > 0) 0 else -1
        val comment = if (lineCount < 0) "" else splitList.subList(0, splitList.size).joinToString("\n")
        return Note(id, createDay, NoteType.TYPE_NOTE, createTitle, comment, createTextNote)
    }

    private fun updateNote(oldNote: Note): Note {
        val title = title.get() ?: oldNote.title
        val textNote = textNote.get() ?: ""
        val splitList = textNote.split("\n")
        val lineCount = if (splitList.size >= 2) 1 else if (splitList.size > 0) 0 else -1
        val comment = if (lineCount < 0) "" else splitList.subList(0, splitList.size).joinToString("\n")
        return Note(oldNote.id, oldNote.date, NoteType.TYPE_NOTE, title, comment, textNote)
    }


}