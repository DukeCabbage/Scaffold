package com.cabbage.scaffold.dagger

import android.content.Context
import com.f2prateek.rx.preferences2.RxSharedPreferences
import dagger.Component
import javax.inject.Named

@ApplicationScope
@Component(modules = [(AppModule::class)])
interface AppComponent {

    @Named("appContext")
    fun appContext(): Context

    fun rxPreference(): RxSharedPreferences
}