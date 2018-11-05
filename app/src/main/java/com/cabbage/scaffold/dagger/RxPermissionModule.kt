package com.cabbage.scaffold.dagger

import android.app.Activity
import com.tbruyelle.rxpermissions2.RxPermissions
import dagger.Module
import dagger.Provides

/**
 * Provides a [ActivityScope] dependency, this module should be used with other activity modules,
 * which provides the binding to the [Activity].
 */
@Module
object RxPermissionModule {

    @Provides @ActivityScope @JvmStatic
    fun provideRxPermission(activity: Activity): RxPermissions {
        return RxPermissions(activity)
    }
}