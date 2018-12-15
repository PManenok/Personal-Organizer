package com.gmail.pmanenok.personalorganizer.inject

import com.gmail.pmanenok.data.repositories.DateRepositoryImpl
import com.gmail.pmanenok.data.repositories.NoteRepositoryImpl
import com.gmail.pmanenok.domain.repositories.DateRepository
import com.gmail.pmanenok.domain.repositories.NoteRepository
import dagger.Binds
import dagger.Module

@Module
abstract class RepositoryModule {

    @Binds
    abstract fun provideNoteRepository(repository: NoteRepositoryImpl): NoteRepository

    @Binds
    abstract fun provideDateRepository(repository: DateRepositoryImpl): DateRepository

}