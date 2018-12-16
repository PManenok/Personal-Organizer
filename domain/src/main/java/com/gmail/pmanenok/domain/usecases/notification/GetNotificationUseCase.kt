package com.gmail.pmanenok.domain.usecases.notification

import com.gmail.pmanenok.domain.entity.Notification
import com.gmail.pmanenok.domain.executor.PostExecutorThread
import com.gmail.pmanenok.domain.repositories.DateRepository
import com.gmail.pmanenok.domain.repositories.NotificationRepository
import com.gmail.pmanenok.domain.usecases.note.BaseUseCase
import io.reactivex.Flowable
import io.reactivex.subjects.BehaviorSubject
import javax.inject.Inject

class GetNotificationUseCase @Inject constructor(
    postExecutorThread: PostExecutorThread,
    private val repository: NotificationRepository
) : BaseUseCase(postExecutorThread) {

    fun get(): Flowable<Notification> {
        return repository.getNotification()
            .observeOn(postExecutorThread)
            .subscribeOn(workExecutorThread)
    }
}
