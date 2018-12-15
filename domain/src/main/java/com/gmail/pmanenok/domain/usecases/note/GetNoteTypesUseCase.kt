package com.gmail.pmanenok.domain.usecases.note

import com.gmail.pmanenok.domain.executor.PostExecutorThread
import com.gmail.pmanenok.domain.repositories.NoteRepository
import io.reactivex.Flowable
import javax.inject.Inject

class GetNoteTypesUseCase @Inject constructor(
    postExecutorThread: PostExecutorThread,
    private val repository: NoteRepository
) : BaseUseCase(postExecutorThread) {

    fun getAllNoteTypes(): Flowable<Map<Long,List<Int>>> {
        return repository.getAllTypedNote()
            .observeOn(postExecutorThread)
            .subscribeOn(workExecutorThread)
    }
}
