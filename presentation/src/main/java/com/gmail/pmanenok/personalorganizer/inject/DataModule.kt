package com.gmail.pmanenok.personalorganizer.inject

import android.content.Context
import com.gmail.pmanenok.data.db.AppDataBase
import com.gmail.pmanenok.data.db.dao.NoteDao
import com.gmail.pmanenok.data.db.dao.NoteDaoTransactions
import dagger.Module
import dagger.Provides

@Module
class DataModule {

    @Provides
    fun provideNoteDao(appDataBase: AppDataBase): NoteDao = appDataBase.getNoteDao()

    @Provides
    fun provideNoteDaoTransactions(appDataBase: AppDataBase): NoteDaoTransactions = appDataBase.getNoteDaoTransactions()

    @Provides
    fun provideAppDataBase(context: Context): AppDataBase = AppDataBase.getInstance(context)

    /*@Provides
    fun provideRestService(@Named(URL_INJECT_NAME_DEBUG) url: String): RestService =
        RestService("https://api.backendless.com/3C38FF89-D6CA-F09A-FF2D-375419F6C600/6D5A1710-032A-8000-FF13-60CA35177F00/data/")

    @Provides
    @Named(URL_INJECT_NAME_DEBUG)
    fun provideServerUrlDebug(): String =
        "https://api.backendless.com/3C38FF89-D6CA-F09A-FF2D-375419F6C600/6D5A1710-032A-8000-FF13-60CA35177F00/data/"

    @Provides
    @Named(URL_INJECT_NAME_RELEASE)
    fun provideServerUrlRelease(): String =
        "https://api.backendless.com/3C38FF89-D6CA-F09A-FF2D-375419F6C600/6D5A1710-032A-8000-FF13-60CA35177F00/data/"*/

}
