package com.cabbage.scaffold.dagger

import android.support.v7.app.AppCompatActivity
import com.cabbage.scaffold.demo.MainPresenter
import com.tbruyelle.rxpermissions2.RxPermissions
import dagger.Module
import dagger.Provides

@Module
class ActivityModule(val activity: AppCompatActivity) {

    @ActivityScope @Provides
    fun providesRxPermission(): RxPermissions {
        return RxPermissions(activity)
    }

    @ActivityScope @Provides
    fun providesMainPresenter(rxPermissions: RxPermissions): MainPresenter {
        return MainPresenter(rxPermissions)
    }

}