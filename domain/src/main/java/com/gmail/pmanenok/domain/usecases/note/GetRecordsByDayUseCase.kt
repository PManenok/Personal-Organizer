package com.gmail.pmanenok.domain.usecases.note

import com.gmail.pmanenok.domain.entity.Note
import com.gmail.pmanenok.domain.entity.Record
import com.gmail.pmanenok.domain.executor.PostExecutorThread
import com.gmail.pmanenok.domain.repositories.NoteRepository
import io.reactivex.Flowable
import javax.inject.Inject

class GetRecordsByDayUseCase @Inject constructor(
    postExecutorThread: PostExecutorThread,
    private val repository: NoteRepository
) : BaseUseCase(postExecutorThread) {

    fun getByDay(day: Long): Flowable<List<Record>> {
        return repository.getByDay(day)
            .observeOn(postExecutorThread)
            .subscribeOn(workExecutorThread)
    }
}
