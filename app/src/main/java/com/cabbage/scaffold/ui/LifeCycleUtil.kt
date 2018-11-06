@file:Suppress("unused")

package com.cabbage.scaffold.ui

import android.arch.lifecycle.*
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import timber.log.Timber

/**
 * Here are utility functions related to arch livedata and arch viewmodel
 */

typealias daggerLazy<T> = dagger.Lazy<T>

typealias VMFac = ViewModelProvider.Factory

/**
 * Takes a lazy [ViewModelProvider.Factory], only unwraps it if view model wasn't created before.
 * This is only relevant for ViewModels whose constructors require arguments, thus making it necessary
 * for us to implement custom [ViewModelProvider.Factory]
 */
inline fun <reified T : ViewModel>
        FragmentActivity.getViewModel(lazyFactory: daggerLazy<out VMFac>): T {
    return try {
        Timber.v("Provide VM cached")
        getViewModel<T>(null)
    } catch (e: Throwable) {
        Timber.v("Provide VM with factory")
        getViewModel(lazyFactory.get())
    }
}

inline fun <reified T : ViewModel> FragmentActivity.getViewModel(factory: VMFac? = null): T =
        ViewModelProviders.of(this, factory)[T::class.java]


// We need the one associated with host activity, not fragment itself
// Calling ViewModelProviders.of(this: Fragment) will result in a distinct instance
inline fun <reified T : ViewModel> Fragment.getViewModel(factory: VMFac? = null): T? =
        activity?.run { getViewModel(factory) }

inline fun <reified T : ViewModel> Fragment.getViewModel(lazyFactory: daggerLazy<out VMFac>): T? =
        activity?.run { getViewModel(lazyFactory) }


fun <X, Y> LiveData<X>.map(func: (X) -> Y): LiveData<Y> = Transformations.map(this, func)