package com.gmail.pmanenok.personalorganizer.inject

import com.gmail.pmanenok.data.repositories.NoteRepositoryImpl
import com.gmail.pmanenok.domain.repositories.NoteRepository
import dagger.Binds
import dagger.Module

@Module
abstract class RepositoryModule {

    @Binds
    abstract fun provideStudentRepository(repository: NoteRepositoryImpl): NoteRepository

}