package com.cabbage.scaffold.ui.demo

import android.Manifest
import com.cabbage.scaffold.dagger.ConfigPersistent
import com.cabbage.scaffold.ui.base.BasePresenter
import com.tbruyelle.rxpermissions2.RxPermissions
import io.reactivex.rxkotlin.subscribeBy
import timber.log.Timber
import javax.inject.Inject

@ConfigPersistent
class MainPresenter @Inject constructor() : BasePresenter<MainActivity>() {
    init {
        Timber.v("Initializing")
    }

    fun ensureLocationPermission(rxPermissions: RxPermissions) {

        val locationPermission = Manifest.permission.ACCESS_FINE_LOCATION
        addSubscription(rxPermissions.request(locationPermission)
                                .subscribeBy(
                                        onNext = { mvpView?.showLocationPermissionResult(it) },
                                        onError = { e -> Timber.e(e) }
                                ))
    }
}