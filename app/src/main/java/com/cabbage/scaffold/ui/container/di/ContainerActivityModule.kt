package com.cabbage.scaffold.ui.container.di

import android.content.Context
import com.cabbage.scaffold.dagger.ActivityScope
import com.cabbage.scaffold.dagger.ApplicationScope
import com.cabbage.scaffold.ui.container.domain.AANetworkManager
import com.cabbage.scaffold.ui.container.viewmodel.ContainerVMFactory
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
object ContainerActivityModule {

    @Provides @ActivityScope @JvmStatic
    fun provideVMFactory(@ActivityScope manager: AANetworkManager) =
            ContainerVMFactory(manager)

    @Provides @ActivityScope @JvmStatic
    fun provideNetworkManager(@ApplicationScope @Named("appContext")
                              context: Context) =
            AANetworkManager(context)
}
