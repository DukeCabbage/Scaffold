package com.cabbage.scaffold.ui.container.di

import android.app.Activity
import com.cabbage.scaffold.dagger.ActivityScope
import com.cabbage.scaffold.dagger.RxPermissionModule
import com.cabbage.scaffold.ui.container.view.ContainerActivity
import dagger.Binds
import dagger.Module

@Module(includes = [
    ContainerVMModule::class,
    RxPermissionModule::class
])
abstract class ContainerActivityModule {

    @Binds @ActivityScope
    abstract fun bindsActivity(act: ContainerActivity): Activity
}