package com.cabbage.scaffold.ui.demo

import com.cabbage.scaffold.ui.base.MvpPresenter
import com.cabbage.scaffold.ui.base.MvpView
import com.tbruyelle.rxpermissions2.RxPermissions

interface MainContract {
    interface View : MvpView {
        fun showLocationPermissionResult(granted: Boolean)
    }

    interface Presenter : MvpPresenter<View> {
        fun ensureLocationPermission(rxPermissions: RxPermissions)
    }
}
