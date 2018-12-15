package com.gmail.pmanenok.domain.usecases.note

import com.gmail.pmanenok.domain.entity.Note
import com.gmail.pmanenok.domain.executor.PostExecutorThread
import com.gmail.pmanenok.domain.repositories.NoteRepository
import io.reactivex.Flowable
import javax.inject.Inject

class GetNotesByDayRangeUseCase @Inject constructor(
    postExecutorThread: PostExecutorThread,
    private val repository: NoteRepository
) : BaseUseCase(postExecutorThread) {

    fun getByDay(idBegin: Long, idEnd: Long): Flowable<List<Pair<Long, List<Note>>>> {
        return repository.getByDayRange(idBegin, idEnd)
            .observeOn(postExecutorThread)
            .subscribeOn(workExecutorThread)
    }
}
