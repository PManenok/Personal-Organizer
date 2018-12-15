package com.gmail.pmanenok.domain.usecases.note

import com.gmail.pmanenok.domain.entity.Birthday
import com.gmail.pmanenok.domain.entity.List
import com.gmail.pmanenok.domain.entity.Note
import com.gmail.pmanenok.domain.executor.PostExecutorThread
import com.gmail.pmanenok.domain.repositories.NoteRepository
import com.gmail.pmanenok.domain.usecases.note.BaseUseCase
import io.reactivex.Flowable
import javax.inject.Inject

class GetRecordByIdUseCase @Inject constructor(
    postExecutorThread: PostExecutorThread,
    private val repository: NoteRepository
) : BaseUseCase(postExecutorThread) {

    fun getNoteRecordById(id: String): Flowable<Note> {
        return repository.getNoteRecordById(id)
            .observeOn(postExecutorThread)
            .subscribeOn(workExecutorThread)
    }

    fun getBirthdayRecordById(id: String): Flowable<Birthday> {
        return repository.getBirthdayRecordById(id)
            .observeOn(postExecutorThread)
            .subscribeOn(workExecutorThread)
    }

    fun getListRecordById(id: String): Flowable<List> {
        return repository.getListRecordById(id)
            .observeOn(postExecutorThread)
            .subscribeOn(workExecutorThread)
    }
}
