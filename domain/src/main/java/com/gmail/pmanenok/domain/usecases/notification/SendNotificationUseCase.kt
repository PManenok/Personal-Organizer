package com.gmail.pmanenok.domain.usecases.notification

import com.gmail.pmanenok.domain.executor.PostExecutorThread
import com.gmail.pmanenok.domain.repositories.NotificationRepository
import com.gmail.pmanenok.domain.usecases.note.BaseUseCase
import javax.inject.Inject

class SendNotificationUseCase @Inject constructor(
    postExecutorThread: PostExecutorThread,
    private val repository: NotificationRepository
) : BaseUseCase(postExecutorThread) {

    fun sendNotification(notification: String) {
        return repository.sendNotification(notification)
    }

    fun sendNotificationDatabaseChanged() {
        return repository.sendNotificationDatabaseChanged()
    }
}
