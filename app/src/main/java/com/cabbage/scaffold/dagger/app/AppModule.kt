package com.cabbage.scaffold.dagger.app

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import com.cabbage.scaffold.dagger.ApplicationScope
import com.f2prateek.rx.preferences2.RxSharedPreferences
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
class AppModule(private val context: Context) {

    @ApplicationScope @Provides
    @Named("appContext")
    fun providesContext(): Context {
        return context
    }

    @ApplicationScope @Provides
    fun providesPreference(): SharedPreferences {
        return context.getSharedPreferences("default", MODE_PRIVATE)
    }

    @ApplicationScope @Provides
    fun providesRxPermission(sharedPreferences: SharedPreferences): RxSharedPreferences {
        return RxSharedPreferences.create(sharedPreferences)
    }
}