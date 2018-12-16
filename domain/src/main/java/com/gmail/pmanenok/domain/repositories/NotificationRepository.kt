package com.gmail.pmanenok.domain.repositories

import com.gmail.pmanenok.domain.entity.Notification
import io.reactivex.Flowable

interface NotificationRepository : BaseRepository {
    fun getNotification(): Flowable<Notification>
    fun sendNotification(notification: String)
    fun sendNotificationDatabaseChanged()
}