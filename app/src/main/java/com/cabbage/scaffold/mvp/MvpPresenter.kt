package com.cabbage.scaffold.mvp

interface MvpPresenter<in V : MvpView> {

    fun attachView(view: V)

    fun detachView()
}
