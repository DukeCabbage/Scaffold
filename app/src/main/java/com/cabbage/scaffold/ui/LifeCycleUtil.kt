package com.cabbage.scaffold.ui

import android.arch.lifecycle.*
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import timber.log.Timber

typealias daggerLazy<T> = dagger.Lazy<T>
typealias VMFac = ViewModelProvider.Factory

inline fun <reified T : ViewModel>
        FragmentActivity.getViewModel(lazyFactory: daggerLazy<out VMFac>): T =
        try {
            Timber.v("Provide VM cached")
            ViewModelProviders.of(this)[T::class.java]
        } catch (e: Throwable) {
            Timber.v("Provide VM with factory")
            ViewModelProviders.of(this, lazyFactory.get())[T::class.java]
        }


inline fun <reified T : ViewModel> FragmentActivity.getViewModel(factory: VMFac? = null): T =
        ViewModelProviders.of(this, factory)[T::class.java]


// We need the one associated with host activity, not fragment itself
// Calling ViewModelProviders.of(this: Fragment) will result in a distinct instance
inline fun <reified T : ViewModel> Fragment.getViewModel(factory: VMFac? = null): T? =
        activity?.run { getViewModel(factory) }


fun <X, Y> LiveData<X>.map(func: (X) -> Y): LiveData<Y> = Transformations.map(this, func)