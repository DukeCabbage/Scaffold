package com.cabbage.scaffold.ui.main.di

import com.cabbage.scaffold.dagger.ActivityScope
import com.cabbage.scaffold.ui.main.MainActivity
import com.tbruyelle.rxpermissions2.RxPermissions
import dagger.Module
import dagger.Provides

@Module
object MainActivityModule {

    @Provides @ActivityScope @JvmStatic
    fun provideRxPermission(activity: MainActivity): RxPermissions {
        return RxPermissions(activity)
    }
}