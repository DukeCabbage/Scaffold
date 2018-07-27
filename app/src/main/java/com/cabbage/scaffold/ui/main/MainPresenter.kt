package com.cabbage.scaffold.ui.main

import android.Manifest
import com.cabbage.scaffold.dagger.ConfigPersistent
import com.cabbage.scaffold.ui.base.BasePresenter
import com.tbruyelle.rxpermissions2.RxPermissions
import io.reactivex.rxkotlin.subscribeBy
import timber.log.Timber
import javax.inject.Inject

@ConfigPersistent
class MainPresenter
@Inject constructor()
    : BasePresenter<MainContract.View>(),
      MainContract.Presenter {

    init {
        Timber.v("Initializing")
    }

    override fun ensureLocationPermission(rxPermissions: RxPermissions) {

        val locationPermission = Manifest.permission.ACCESS_FINE_LOCATION
        val disposable = rxPermissions.request(locationPermission)
                .subscribeBy(
                        onNext = { mvpView?.showLocationPermissionResult(it) },
                        onError = { e -> Timber.e(e) }
                )
        addSubscription(disposable)
    }
}