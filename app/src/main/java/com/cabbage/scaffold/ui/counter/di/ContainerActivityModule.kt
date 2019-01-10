package com.cabbage.scaffold.ui.counter.di

import android.app.Activity
import com.cabbage.scaffold.dagger.ActivityScope
import com.cabbage.scaffold.dagger.FragmentScope
import com.cabbage.scaffold.dagger.RxPermissionModule
import com.cabbage.scaffold.ui.counter.view.ControlPanelFragment
import com.cabbage.scaffold.ui.counter.view.CounterActivity
import com.cabbage.scaffold.ui.counter.view.CounterFragment
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module(includes = [
    ContainerVMModule::class,
    RxPermissionModule::class
])
abstract class ContainerActivityModule {

    @Binds @ActivityScope
    abstract fun bindsActivity(act: CounterActivity): Activity

    @FragmentScope
    @ContributesAndroidInjector()
    abstract fun contributeFragmentInjector1(): CounterFragment

    @FragmentScope
    @ContributesAndroidInjector()
    abstract fun contributeFragmentInjector2(): ControlPanelFragment
}