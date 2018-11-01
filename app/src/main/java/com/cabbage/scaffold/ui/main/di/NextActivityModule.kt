package com.cabbage.scaffold.ui.main.di

import com.cabbage.scaffold.dagger.ActivityScope
import com.cabbage.scaffold.ui.main.NextActivity
import com.tbruyelle.rxpermissions2.RxPermissions
import dagger.Module
import dagger.Provides

@Module
object NextActivityModule {

    @Provides @ActivityScope @JvmStatic
    fun provideRxPermission(activity: NextActivity): RxPermissions {
        return RxPermissions(activity)
    }
}