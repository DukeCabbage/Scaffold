package com.cabbage.scaffold.ui.main.di

import android.app.Activity
import com.cabbage.scaffold.dagger.ActivityScope
import com.cabbage.scaffold.dagger.RxPermissionModule
import com.cabbage.scaffold.ui.main.NextActivity
import dagger.Binds
import dagger.Module

@Module(includes = [RxPermissionModule::class])
abstract class NextActivityModule {

    @Binds @ActivityScope
    abstract fun bindsActivity(act: NextActivity): Activity
}