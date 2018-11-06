package com.cabbage.scaffold.dagger

import android.app.Application
import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import com.cabbage.scaffold.ui.container.domain.Counter
import com.f2prateek.rx.preferences2.RxSharedPreferences
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
class AppModule {

    @ApplicationScope @Provides
    @Named("appContext")
    fun provideContext(application: Application): Context {
        return application
    }

    @ApplicationScope @Provides
    fun providesPreference(@Named("appContext") context: Context): SharedPreferences {
        return context.getSharedPreferences("default", MODE_PRIVATE)
    }

    @ApplicationScope @Provides
    fun providesRxPermission(sharedPreferences: SharedPreferences): RxSharedPreferences {
        return RxSharedPreferences.create(sharedPreferences)
    }

    @ApplicationScope @Provides
    @Named("Global")
    fun providesGlobalCounter(@Named("appContext") context: Context): Counter {
        return Counter(context, "global")
    }
}