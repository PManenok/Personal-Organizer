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
import com.gmail.pmanenok.personalorganizer.app.App
import com.gmail.pmanenok.personalorganizer.presentation.base.BaseViewModel
import com.gmail.pmanenok.personalorganizer.presentation.screen.note.NoteRouter
import com.gmail.pmanenok.personalorganizer.presentation.utils.todayFromLong
import io.reactivex.rxkotlin.subscribeBy
import java.util.*
import javax.inject.Inject

class NoteRecordViewModel : BaseViewModel<NoteRouter>() {
    val title = ObservableField<String>()
    val textNote = ObservableField<String>()
    val notify = ObservableBoolean(false)
    var note: Note? = null
    private var noteId: String = ""

    fun setRecordId(id: String) {
        if (noteId != "") return
        noteId = id
        bindNote()
    }

    val backOnClick = View.OnClickListener {
        Log.e("NoteRecordViewModel","backOnClick")
        router?.goBack()
    }
    val notifyOnClick = View.OnClickListener {
        //router?.showNotify()
        Log.e("NoteRecordViewModel","notifyOnClick")
        val oldNote = note
        if (oldNote != null) {
            addToDisposable(updateRecordUseCase.update(updateNote(oldNote)).subscribeBy(
                onError = {
                    router?.showError(it)
                }
            ))
        } else if(!title.get().isNullOrBlank() && !textNote.get().isNullOrBlank()) {
            addToDisposable(addRecordUseCase.add(createNote()).subscribeBy(
                onError = {
                    router?.showError(it)
                }
            ))
        }
        router?.goBack()
    }

    private fun createNote(): Note {
        val calendar = Calendar.getInstance()
        val id = calendar.timeInMillis.toString()
        val day = todayFromLong(calendar.timeInMillis)
        val title = title.get() ?: ""
        val textNote = textNote.get() ?: ""
        val splitList = textNote.split("\n")
        val lineCount = if (splitList.size >= 2) 1 else if (splitList.size > 0) 0 else -1
        val comment = if (lineCount < 0) "" else splitList.subList(0, splitList.size).joinToString("\n")
        return Note(id, day, NoteType.TYPE_NOTE, title, comment, textNote)
    }

    private fun updateNote(oldNote: Note): Note {
        val title = title.get() ?: oldNote.title
        val textNote = textNote.get() ?: ""
        val splitList = textNote.split("\n")
        val lineCount = if (splitList.size >= 2) 1 else if (splitList.size > 0) 0 else -1
        val comment = if (lineCount < 0) "" else splitList.subList(0, splitList.size).joinToString("\n")
        return Note(oldNote.id, oldNote.date, NoteType.TYPE_NOTE, title, comment, textNote)
    }

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

    private fun bindNote() {

        addToDisposable(getRecordByIdUseCase.getNoteRecordById(noteId).subscribeBy(
            onNext = {
                note = it
                title.set(it.title)
                //comment = it.comment
                textNote.set(it.textNote)
            },
            onError = {
                router?.showError(it)
            }
        ))

    }

}