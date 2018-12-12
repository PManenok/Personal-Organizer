package com.gmail.pmanenok.personalorganizer.inject

import android.content.Context
import com.gmail.pmanenok.domain.executor.PostExecutorThread
import com.gmail.pmanenok.personalorganizer.app.App
import com.gmail.pmanenok.personalorganizer.executor.UIThread
import dagger.Module
import dagger.Provides

@Module
class AppModule() {
    @Provides
    fun provideContext(app: App): Context = app.applicationContext

    @Provides
    fun providePostExecutorThread(): PostExecutorThread = UIThread()
}