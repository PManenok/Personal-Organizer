package com.gmail.pmanenok.personalorganizer.inject

import com.gmail.pmanenok.personalorganizer.app.App
import com.gmail.pmanenok.personalorganizer.presentation.screen.main.MainViewModel
import com.gmail.pmanenok.personalorganizer.presentation.screen.main.day.DayViewModel
import com.gmail.pmanenok.personalorganizer.presentation.screen.main.month.MonthViewModel
import com.gmail.pmanenok.personalorganizer.presentation.screen.main.week.WeekViewModel
import com.gmail.pmanenok.personalorganizer.presentation.screen.note.birthdayrec.BirthdayRecordViewModel
import com.gmail.pmanenok.personalorganizer.presentation.screen.note.listrec.ListRecordViewModel
import com.gmail.pmanenok.personalorganizer.presentation.screen.note.noterec.NoteRecordViewModel
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule

@Component(
    modules = [AndroidSupportInjectionModule::class, AppModule::class,
        RepositoryModule::class, PresentationModule::class, DataModule::class]
)
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: App): Builder

        fun build(): AppComponent
    }

    fun inject(app: App)

    fun inject(view: DayViewModel)

    fun inject(view: MonthViewModel)

    fun inject(view: WeekViewModel)

    fun inject(view: NoteRecordViewModel)

    fun inject(view: ListRecordViewModel)

    fun inject(view: BirthdayRecordViewModel)

    fun inject(view: MainViewModel)
}