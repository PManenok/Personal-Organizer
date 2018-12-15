package com.gmail.pmanenok.domain.usecases.note

import com.gmail.pmanenok.domain.entity.Note
import com.gmail.pmanenok.domain.executor.PostExecutorThread
import com.gmail.pmanenok.domain.repositories.NoteRepository
import io.reactivex.Completable
import javax.inject.Inject

class UpdateNoteUseCase @Inject constructor(
    postExecutorThread: PostExecutorThread,
    private val repository: NoteRepository
) : BaseUseCase(postExecutorThread) {

    fun update(note: Note): Completable {
        return repository.update(note)
            .observeOn(postExecutorThread)
            .subscribeOn(workExecutorThread)
    }
}
