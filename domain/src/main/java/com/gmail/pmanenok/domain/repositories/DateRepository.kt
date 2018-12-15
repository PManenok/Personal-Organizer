package com.gmail.pmanenok.domain.repositories

import com.gmail.pmanenok.domain.entity.Note
import com.gmail.pmanenok.domain.entity.NoteSearch
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.subjects.BehaviorSubject

interface DateRepository : BaseRepository {
    fun getToday(): Flowable<Long>
    fun getSelectedDay(): Flowable<Long>
    fun updateSelectedDay(dateInMillis: Long)
    fun refreshSelectedDay()
}