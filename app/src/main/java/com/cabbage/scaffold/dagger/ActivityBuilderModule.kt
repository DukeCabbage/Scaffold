package com.cabbage.scaffold.dagger

import dagger.android.ContributesAndroidInjector
import com.cabbage.scaffold.ui.counter.view.CounterActivity
import com.cabbage.scaffold.ui.counter.di.ContainerActivityModule
import com.cabbage.scaffold.ui.main.MainActivity
import com.cabbage.scaffold.ui.main.NextActivity
import com.cabbage.scaffold.ui.main.di.MainActivityModule
import com.cabbage.scaffold.ui.main.di.NextActivityModule
import dagger.Module

@Module
abstract class ActivityBuilderModule {

    @ActivityScope
    @ContributesAndroidInjector(modules = [MainActivityModule::class])
    abstract fun contributeMainActivity(): MainActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = [NextActivityModule::class])
    abstract fun contributeNextActivity(): NextActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = [ContainerActivityModule::class])
    abstract fun contributeContainerActivity(): CounterActivity
}