package com.gmail.pmanenok.data.repositories

import android.util.Log
import com.gmail.pmanenok.data.entity.DatabaseNotifierSingleton
import com.gmail.pmanenok.domain.entity.Notification
import com.gmail.pmanenok.domain.repositories.NotificationRepository
import io.reactivex.Flowable
import javax.inject.Inject

class NotificationRepositoryImpl @Inject constructor() : NotificationRepository {
    private var notifier = DatabaseNotifierSingleton

    override fun getNotification(): Flowable<Notification> {
        Log.e("NotificationRepository", "getNotification")
        return notifier.notify
    }

    override fun sendNotification(notification: String) {
        Log.e("NotificationRepository", "sendNotification $notification")
        notifier.notify.offer(Notification.valueOf(notification))
    }

    override fun sendNotificationDatabaseChanged() {
        Log.e("NotificationRepository", "sendNotificationDatabaseChanged")
        notifier.notify.offer(Notification.DATABASE_CHANGED)
    }
}