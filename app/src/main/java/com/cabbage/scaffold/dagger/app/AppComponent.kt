package com.cabbage.scaffold.dagger.app

import android.app.Application
import android.content.Context
import com.cabbage.scaffold.ScaffoldApplication
import com.cabbage.scaffold.dagger.ApplicationScope
import com.f2prateek.rx.preferences2.RxSharedPreferences
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Named

@ApplicationScope
@Component(modules = [
    AppModule::class,
    AndroidSupportInjectionModule::class,
    ActivityBuilderModule::class
])
interface AppComponent {

    //injection
    fun inject(application: ScaffoldApplication)

    @Named("appContext")
    fun appContext(): Context

    fun rxPreference(): RxSharedPreferences

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}