package com.gmail.pmanenok.domain.usecases

import com.gmail.pmanenok.domain.entity.Note
import com.gmail.pmanenok.domain.executor.PostExecutorThread
import com.gmail.pmanenok.domain.repositories.NoteRepository
import io.reactivex.Completable
import io.reactivex.Observable
import javax.inject.Inject

class DeleteNoteUseCase @Inject constructor(
    postExecutorThread: PostExecutorThread,
    private val repository: NoteRepository
) : BaseUseCase(postExecutorThread) {

    fun delete(id: String): Completable {
        return repository.delete(id)
            .observeOn(postExecutorThread)
            .subscribeOn(workExecutorThread)
    }
}
