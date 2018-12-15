package com.gmail.pmanenok.domain.usecases.date

import com.gmail.pmanenok.domain.executor.PostExecutorThread
import com.gmail.pmanenok.domain.repositories.DateRepository
import com.gmail.pmanenok.domain.usecases.note.BaseUseCase
import io.reactivex.Flowable
import io.reactivex.subjects.BehaviorSubject
import javax.inject.Inject

class GetTodayUseCase @Inject constructor(
    postExecutorThread: PostExecutorThread,
    private val repository: DateRepository
) : BaseUseCase(postExecutorThread) {

    fun get(): Flowable<Long> {
        return repository.getToday()
            .observeOn(postExecutorThread)
            .subscribeOn(workExecutorThread)
    }
}
