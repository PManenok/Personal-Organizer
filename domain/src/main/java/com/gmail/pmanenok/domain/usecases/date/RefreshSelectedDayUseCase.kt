package com.gmail.pmanenok.domain.usecases.date

import com.gmail.pmanenok.domain.executor.PostExecutorThread
import com.gmail.pmanenok.domain.repositories.DateRepository
import com.gmail.pmanenok.domain.usecases.note.BaseUseCase
import io.reactivex.Completable
import io.reactivex.Flowable
import javax.inject.Inject

class RefreshSelectedDayUseCase @Inject constructor(
    postExecutorThread: PostExecutorThread,
    private val repository: DateRepository
) : BaseUseCase(postExecutorThread) {

    fun refresh() {
        return repository.refreshSelectedDay()
    }
}
