package com.cabbage.scaffold.dagger

import dagger.android.ContributesAndroidInjector
import com.cabbage.scaffold.ui.container.ContainerActivity
import com.cabbage.scaffold.ui.container.di.ContainerActivityModule
import com.cabbage.scaffold.ui.container.di.ContainerVMModule
import com.cabbage.scaffold.ui.main.MainActivity
import com.cabbage.scaffold.ui.main.NextActivity
import com.cabbage.scaffold.ui.main.di.MainActivityModule
import com.cabbage.scaffold.ui.main.di.NextActivityModule
import dagger.Module

@Suppress("unused")
@Module
abstract class ActivityBuilderModule {

    @ActivityScope
    @ContributesAndroidInjector(modules = [
        MainActivityModule::class,
        RxPermissionModule::class])
    abstract fun contributeMainActivity(): MainActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = [
        NextActivityModule::class,
        RxPermissionModule::class
    ])
    abstract fun contributeNextActivity(): NextActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = [
        ContainerActivityModule::class,
        ContainerVMModule::class,
        RxPermissionModule::class
    ])
    abstract fun contributeContainerActivity(): ContainerActivity
}