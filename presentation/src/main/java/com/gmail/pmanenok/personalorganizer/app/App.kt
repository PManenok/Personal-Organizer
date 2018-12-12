package com.gmail.pmanenok.personalorganizer.app

import android.app.Activity
import android.app.Application
import com.crashlytics.android.Crashlytics;
import com.gmail.pmanenok.personalorganizer.inject.AppComponent
import com.gmail.pmanenok.personalorganizer.inject.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import io.fabric.sdk.android.Fabric;
import javax.inject.Inject

class App : Application(), HasActivityInjector {
    companion object {
        lateinit var instance: App
        @JvmStatic
        lateinit var appComponent: AppComponent
    }

    init {
        instance = this
    }

    @Inject
    lateinit var activityInjector: DispatchingAndroidInjector<Activity>

    override fun activityInjector(): AndroidInjector<Activity> {
        return activityInjector
    }

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent
            .builder()
            .application(this)
            .build()
        Fabric.with(this, Crashlytics())
    }
}