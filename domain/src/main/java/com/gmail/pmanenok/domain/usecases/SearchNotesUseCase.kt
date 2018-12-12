package com.gmail.pmanenok.domain.usecases

import com.gmail.pmanenok.domain.entity.Note
import com.gmail.pmanenok.domain.entity.NoteSearch
import com.gmail.pmanenok.domain.executor.PostExecutorThread
import com.gmail.pmanenok.domain.repositories.NoteRepository
import io.reactivex.Flowable
import io.reactivex.Observable
import javax.inject.Inject

class SearchNotesUseCase @Inject constructor(
    postExecutorThread: PostExecutorThread,
    private val repository: NoteRepository
) : BaseUseCase(postExecutorThread) {

    fun search(noteSearch: NoteSearch): Flowable<List<Note>> {
        return repository.search(noteSearch)
            .observeOn(postExecutorThread)
            .subscribeOn(workExecutorThread)
    }
}
