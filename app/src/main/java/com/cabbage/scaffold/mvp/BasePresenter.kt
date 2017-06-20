package com.cabbage.scaffold.mvp

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.plusAssign
import timber.log.Timber

open class BasePresenter<V : MvpView> : MvpPresenter<V> {

    override var mvpView: V? = null
        get() {
            if (field == null) Timber.w("No view attached yet")
            return field
        }
        set(value) {
            field = value
            if (field == null) unSubscribeAll()
        }

    private var mDisposables: CompositeDisposable = CompositeDisposable()

    /**
     * Add a subscription, initialize the subscription container if needed
     */
    protected fun addSubscription(d: Disposable) {
        if (mDisposables.isDisposed) mDisposables = CompositeDisposable()
        mDisposables += d
    }

    /**
     * Un-subscribe a subscription, if it's not in the container, dispose anyway
     */
    protected fun unSubscribe(d: Disposable) {
        if (!(mDisposables.remove(d))) d.dispose()
    }

    /**
     * Un-subscribe everything in the container
     */
    protected fun unSubscribeAll() {
        mDisposables.dispose()
        mDisposables = CompositeDisposable()
    }
}