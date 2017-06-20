package com.cabbage.scaffold.ui.mvp

interface MvpPresenter<V : MvpView> {

    var mvpView: V?
}
