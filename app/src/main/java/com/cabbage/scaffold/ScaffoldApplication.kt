package com.cabbage.scaffold

import android.app.Application
import com.cabbage.scaffold.dagger.AppComponent
import com.cabbage.scaffold.dagger.AppModule
import com.cabbage.scaffold.dagger.DaggerAppComponent

import timber.log.Timber

class ScaffoldApplication : Application() {

    companion object {
        lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        Timber.plant(ForestFire())

        appComponent = DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .build()
    }
}
