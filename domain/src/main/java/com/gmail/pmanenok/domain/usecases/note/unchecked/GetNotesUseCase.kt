package com.gmail.pmanenok.domain.usecases.note.unchecked

import com.gmail.pmanenok.domain.executor.PostExecutorThread
import com.gmail.pmanenok.domain.repositories.NoteRepository
import com.gmail.pmanenok.domain.usecases.note.BaseUseCase
import javax.inject.Inject

class GetNotesUseCase @Inject constructor(
    postExecutorThread: PostExecutorThread,
    private val repository: NoteRepository
) : BaseUseCase(postExecutorThread) {

    /*fun getAll(): Flowable<List<Note>> {
        return repository.getAll()
            .observeOn(postExecutorThread)
            .subscribeOn(workExecutorThread)
    }*/
}
