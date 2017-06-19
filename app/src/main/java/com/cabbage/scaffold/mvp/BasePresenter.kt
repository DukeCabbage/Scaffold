package com.cabbage.scaffold.mvp

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

open class BasePresenter<V : MvpView> : MvpPresenter<V> {

    protected var mvpView: V? = null
    private var mDisposables: CompositeDisposable? = null

    /**
     * Set or override view attached to this presenter
     * @param view
     */
    override fun attachView(view: V) {
        mvpView = view
    }

    /**
     * Remove reference to the currently attached view, and un-subscribe all on going operations
     */
    override fun detachView() {
        mvpView = null
        unSubscribeAll()
    }

    /**
     * Add a subscription, initialize the subscription container if needed
     */
    protected fun addSubscription(d: Disposable) {
        if (mDisposables?.isDisposed ?: true) mDisposables = CompositeDisposable()
        mDisposables!!.add(d)
    }

    /**
     * Un-subscribe a subscription, if it's not in the container, dispose anyway
     */
    protected fun unSubscribe(d: Disposable) {
        if (!(mDisposables?.remove(d) ?: false)) d.dispose()
    }

    /**
     * Un-subscribe everything in the container
     */
    protected fun unSubscribeAll() {
        mDisposables?.dispose()
        mDisposables = null
    }
}