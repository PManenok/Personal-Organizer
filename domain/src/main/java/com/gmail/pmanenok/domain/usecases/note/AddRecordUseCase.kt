package com.gmail.pmanenok.domain.usecases.note

import com.gmail.pmanenok.domain.entity.Birthday
import com.gmail.pmanenok.domain.entity.List
import com.gmail.pmanenok.domain.entity.Note
import com.gmail.pmanenok.domain.executor.PostExecutorThread
import com.gmail.pmanenok.domain.repositories.NoteRepository
import com.gmail.pmanenok.domain.usecases.note.BaseUseCase
import io.reactivex.Completable
import javax.inject.Inject

class AddRecordUseCase @Inject constructor(
    postExecutorThread: PostExecutorThread,
    private val repository: NoteRepository
) : BaseUseCase(postExecutorThread) {

    fun add(note: Note): Completable {
        return repository.addNoteRecord(note)
            .observeOn(postExecutorThread)
            .subscribeOn(workExecutorThread)
    }

    fun add(birthday: Birthday): Completable {
        return repository.addBirthdayRecord(birthday)
            .observeOn(postExecutorThread)
            .subscribeOn(workExecutorThread)
    }

    fun add(list: List): Completable {
        return repository.addListRecord(list)
            .observeOn(postExecutorThread)
            .subscribeOn(workExecutorThread)
    }
}
