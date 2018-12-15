package com.gmail.pmanenok.domain.usecases.note

import com.gmail.pmanenok.domain.executor.PostExecutorThread
import com.gmail.pmanenok.domain.repositories.NoteRepository
import io.reactivex.Completable
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
