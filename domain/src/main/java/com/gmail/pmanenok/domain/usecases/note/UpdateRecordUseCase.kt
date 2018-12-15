package com.gmail.pmanenok.domain.usecases.note

import com.gmail.pmanenok.domain.entity.Birthday
import com.gmail.pmanenok.domain.entity.List
import com.gmail.pmanenok.domain.entity.Note
import com.gmail.pmanenok.domain.executor.PostExecutorThread
import com.gmail.pmanenok.domain.repositories.NoteRepository
import com.gmail.pmanenok.domain.usecases.note.BaseUseCase
import io.reactivex.Completable
import javax.inject.Inject

class UpdateRecordUseCase @Inject constructor(
    postExecutorThread: PostExecutorThread,
    private val repository: NoteRepository
) : BaseUseCase(postExecutorThread) {

    fun update(note: Note): Completable {
        return repository.updateNoteRecordById(note)
            .observeOn(postExecutorThread)
            .subscribeOn(workExecutorThread)
    }

    fun update(birthday: Birthday): Completable {
        return repository.updateBirthdayRecordById(birthday)
            .observeOn(postExecutorThread)
            .subscribeOn(workExecutorThread)
    }

    fun update(list: List): Completable {
        return repository.updateListRecordById(list)
            .observeOn(postExecutorThread)
            .subscribeOn(workExecutorThread)
    }
}
