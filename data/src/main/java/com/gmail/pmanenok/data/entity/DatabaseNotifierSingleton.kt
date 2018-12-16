package com.gmail.pmanenok.data.entity

import com.gmail.pmanenok.domain.entity.Notification
import io.reactivex.processors.BehaviorProcessor

object DatabaseNotifierSingleton {
    val notify = BehaviorProcessor.createDefault(Notification.DEFAULT_NOTIFY)
}