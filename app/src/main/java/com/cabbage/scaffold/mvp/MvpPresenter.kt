package com.cabbage.scaffold.mvp

interface MvpPresenter<V : MvpView> {

    var mvpView: V?
}
