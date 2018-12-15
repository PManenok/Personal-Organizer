package com.gmail.pmanenok.data.repositories

import com.gmail.pmanenok.data.entity.DateSingleton
import com.gmail.pmanenok.domain.repositories.DateRepository
import io.reactivex.Flowable
import javax.inject.Inject

class DateRepositoryImpl @Inject constructor() : DateRepository {
    private var date = DateSingleton

    override fun getToday(): Flowable<Long> {
        return Flowable.just(date.today)
    }

    override fun getSelectedDay(): Flowable<Long> {
        return Flowable.just((date.selectedDay))
    }

    override fun updateSelectedDay(dateInMillis: Long) {
        date.selectedDay = dateInMillis
    }

    override fun refreshSelectedDay() {
        date.selectedDay = date.today
    }
}