package com.cabbage.scaffold.ui.container.di

import android.app.Activity
import com.cabbage.scaffold.dagger.ActivityScope
import com.cabbage.scaffold.ui.container.view.ContainerActivity
import dagger.Binds
import dagger.Module

@Module
abstract class ContainerActivityModule {

    @Binds @ActivityScope
    abstract fun bindsActivity(act: ContainerActivity): Activity
}