package com.gmail.pmanenok.domain.usecases.note

import com.gmail.pmanenok.domain.entity.Note
import com.gmail.pmanenok.domain.executor.PostExecutorThread
import com.gmail.pmanenok.domain.repositories.NoteRepository
import io.reactivex.Flowable
import javax.inject.Inject

class GetNotesUseCase @Inject constructor(
    postExecutorThread: PostExecutorThread,
    private val repository: NoteRepository
) : BaseUseCase(postExecutorThread) {

    fun getAll(): Flowable<List<Note>> {
        return repository.getAll()
            .observeOn(postExecutorThread)
            .subscribeOn(workExecutorThread)
    }
}
