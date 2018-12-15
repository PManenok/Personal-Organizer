package com.gmail.pmanenok.domain.usecases.note

import com.gmail.pmanenok.domain.executor.PostExecutorThread
import com.gmail.pmanenok.domain.repositories.NoteRepository
import com.gmail.pmanenok.domain.usecases.note.BaseUseCase
import io.reactivex.Completable
import javax.inject.Inject

class DeleteRecordUseCase @Inject constructor(
    postExecutorThread: PostExecutorThread,
    private val repository: NoteRepository
) : BaseUseCase(postExecutorThread) {

    fun deleteNoteRecord(id: String): Completable {
        return repository.deleteNoteRecordById(id)
            .observeOn(postExecutorThread)
            .subscribeOn(workExecutorThread)
    }

    fun deleteListRecord(id: String): Completable {
        return repository.deleteListRecordById(id)
            .observeOn(postExecutorThread)
            .subscribeOn(workExecutorThread)
    }

    fun deleteBirthdayRecord(id: String): Completable {
        return repository.deleteBirthdayRecordById(id)
            .observeOn(postExecutorThread)
            .subscribeOn(workExecutorThread)
    }
}
