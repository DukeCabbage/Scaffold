package com.cabbage.scaffold.ui.counter.di

import android.content.Context
import com.cabbage.scaffold.dagger.ActivityScope
import com.cabbage.scaffold.dagger.ApplicationScope
import com.cabbage.scaffold.ui.counter.view.CounterActivity
import com.cabbage.scaffold.ui.counter.domain.AANetworkManager
import com.cabbage.scaffold.ui.counter.domain.Counter
import com.cabbage.scaffold.ui.counter.viewmodel.ContainerVMFactory
import com.cabbage.scaffold.ui.counter.viewmodel.ContainerViewModel
import com.cabbage.scaffold.ui.daggerLazy
import com.cabbage.scaffold.ui.getViewModel
import dagger.Module
import dagger.Provides
import javax.inject.Named

/**
 * All providers here are supposed to be used to construct a view model.
 *
 * Despite being annotated as [ActivityScope], if there's already a view model created, due to
 * configuration change, these providers are not supposed to be invoked. To achieve this, the
 * ViewModel Factory is wrapped with [dagger.Lazy].
 */
@Module
object ContainerVMModule {

    @Provides @ActivityScope @JvmStatic
    fun provideViewModel(@ActivityScope lazyVMFac: daggerLazy<ContainerVMFactory>,
                         @ActivityScope activity: CounterActivity): ContainerViewModel {
        return activity.getViewModel(lazyVMFac)
    }

    @Provides @ActivityScope @JvmStatic
    fun provideVMFactory(@ActivityScope manager: AANetworkManager,
                         @Named("Global") globalCounter: Counter,
                         @Named("Local") localCounter: Counter) =
            ContainerVMFactory(manager, globalCounter, localCounter)

    @Provides @ActivityScope @JvmStatic
    fun provideNetworkManager(@ApplicationScope @Named("appContext") context: Context) =
            AANetworkManager(context)

    @Provides @ActivityScope @JvmStatic
    @Named("Local")
    fun providesGlobalCounter(@ApplicationScope @Named("appContext") context: Context) =
            Counter(context, "local")
}