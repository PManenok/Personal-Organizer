package com.gmail.pmanenok.personalorganizer.inject

import com.gmail.pmanenok.personalorganizer.app.App
import com.gmail.pmanenok.personalorganizer.presentation.screen.main.MainViewModel
import com.gmail.pmanenok.personalorganizer.presentation.screen.main.day.DayViewModel
import com.gmail.pmanenok.personalorganizer.presentation.screen.main.month.MonthViewModel
import com.gmail.pmanenok.personalorganizer.presentation.screen.main.week.WeekViewModel
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule

@Component(
    modules = [AndroidSupportInjectionModule::class, AppModule::class,
        RepositoryModule::class, PresentationModule::class]
)//DataModule::class
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
}