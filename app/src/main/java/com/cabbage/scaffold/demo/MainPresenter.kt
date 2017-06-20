package com.cabbage.scaffold.demo

import android.Manifest
import com.cabbage.scaffold.mvp.BasePresenter
import com.tbruyelle.rxpermissions2.RxPermissions
import io.reactivex.rxkotlin.subscribeBy
import timber.log.Timber

class MainPresenter(val rxPermissions: RxPermissions) : BasePresenter<MainActivity>() {

    fun ensureLocationPermission() {

        val locationPermission = Manifest.permission.ACCESS_FINE_LOCATION
        addSubscription(rxPermissions.request(locationPermission)
                                .subscribeBy(
                                        onNext = { mvpView?.showLocationPermissionResult(it) },
                                        onError = { Timber.e(it) }
                                ))
    }
}