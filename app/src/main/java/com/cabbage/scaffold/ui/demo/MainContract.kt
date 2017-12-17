package com.cabbage.scaffold.ui.demo

import com.cabbage.scaffold.ui.base.BasePresenter
import com.cabbage.scaffold.ui.mvp.MvpView
import com.tbruyelle.rxpermissions2.RxPermissions

interface MainContract {
    interface View : MvpView {
        fun showLocationPermissionResult(granted: Boolean)
    }

    abstract class Presenter : BasePresenter<View>() {
        abstract fun ensureLocationPermission(rxPermissions: RxPermissions)
    }
}
