package com.gmail.pmanenok.domain.usecases

import com.gmail.pmanenok.domain.entity.TypedNote
import com.gmail.pmanenok.domain.executor.PostExecutorThread
import com.gmail.pmanenok.domain.repositories.NoteRepository
import io.reactivex.Flowable
import javax.inject.Inject

class GetNoteTypesByRangeUseCase @Inject constructor(
    postExecutorThread: PostExecutorThread,
    private val repository: NoteRepository
) : BaseUseCase(postExecutorThread) {

    fun getAllNoteTypesByRange(dayFirst: Long, dayLast: Long): Flowable<List<TypedNote>> {
        return repository.getAllTypedNoteByRange(dayFirst, dayLast)
            .observeOn(postExecutorThread)
            .subscribeOn(workExecutorThread)
    }
}
