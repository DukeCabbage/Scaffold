package com.cabbage.scaffold.ui.base

interface MvpPresenter<V : MvpView> {

    var mvpView: V?

    fun attachView(v: V)

    fun detachView()
}
