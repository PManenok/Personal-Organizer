package com.gmail.pmanenok.domain.usecases.note.unchecked

import com.gmail.pmanenok.domain.executor.PostExecutorThread
import com.gmail.pmanenok.domain.repositories.NoteRepository
import com.gmail.pmanenok.domain.usecases.note.BaseUseCase
import javax.inject.Inject

class SearchNotesUseCase @Inject constructor(
    postExecutorThread: PostExecutorThread,
    private val repository: NoteRepository
) : BaseUseCase(postExecutorThread) {

    /*fun search(noteSearch: NoteSearch): Flowable<List<Note>> {
        return repository.search(noteSearch)
            .observeOn(postExecutorThread)
            .subscribeOn(workExecutorThread)
    }*/
}
