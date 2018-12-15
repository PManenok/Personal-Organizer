package com.gmail.pmanenok.domain.usecases.note

import com.gmail.pmanenok.domain.entity.Note
import com.gmail.pmanenok.domain.executor.PostExecutorThread
import com.gmail.pmanenok.domain.repositories.NoteRepository
import io.reactivex.Flowable
import javax.inject.Inject

class GetNoteUseCase @Inject constructor(
    postExecutorThread: PostExecutorThread,
    private val repository: NoteRepository
) : BaseUseCase(postExecutorThread) {

    fun getById(id: String): Flowable<Note> {
        return repository.getById(id)
            .observeOn(postExecutorThread)
            .subscribeOn(workExecutorThread)
    }
}
