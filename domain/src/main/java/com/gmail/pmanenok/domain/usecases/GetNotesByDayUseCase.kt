package com.gmail.pmanenok.domain.usecases

import com.gmail.pmanenok.domain.entity.Note
import com.gmail.pmanenok.domain.executor.PostExecutorThread
import com.gmail.pmanenok.domain.repositories.NoteRepository
import io.reactivex.Flowable
import io.reactivex.Observable
import javax.inject.Inject

class GetNotesByDayUseCase @Inject constructor(
    postExecutorThread: PostExecutorThread,
    private val repository: NoteRepository
) : BaseUseCase(postExecutorThread) {

    fun getByDay(day: Long): Flowable<List<Note>> {
        return repository.getByDay(day)
            .observeOn(postExecutorThread)
            .subscribeOn(workExecutorThread)
    }
}
