package com.cabbage.scaffold

import android.app.Activity
import android.app.Application
import com.cabbage.scaffold.dagger.app.DaggerAppComponent
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector

import timber.log.Timber
import javax.inject.Inject

class ScaffoldApplication : Application(),
        HasActivityInjector {

    @Inject
    lateinit var activityInjector: DispatchingAndroidInjector<Activity>

    override fun activityInjector() = activityInjector

    override fun onCreate() {
        super.onCreate()
        Timber.plant(ForestFire())

        val component = DaggerAppComponent.builder()
                .application(this)
                .build()

        component.inject(this)
    }
}