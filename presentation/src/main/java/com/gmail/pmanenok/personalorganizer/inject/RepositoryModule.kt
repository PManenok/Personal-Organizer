package com.gmail.pmanenok.personalorganizer.inject

import com.gmail.pmanenok.data.repositories.DateRepositoryImpl
import com.gmail.pmanenok.data.repositories.NoteRepositoryImpl
import com.gmail.pmanenok.data.repositories.NotificationRepositoryImpl
import com.gmail.pmanenok.domain.repositories.DateRepository
import com.gmail.pmanenok.domain.repositories.NoteRepository
import com.gmail.pmanenok.domain.repositories.NotificationRepository
import dagger.Binds
import dagger.Module

@Module
abstract class RepositoryModule {

    @Binds
    abstract fun provideNoteRepository(repository: NoteRepositoryImpl): NoteRepository

    @Binds
    abstract fun provideDateRepository(repository: DateRepositoryImpl): DateRepository

    @Binds
    abstract fun provideNotificationRepository(repository: NotificationRepositoryImpl): NotificationRepository

}