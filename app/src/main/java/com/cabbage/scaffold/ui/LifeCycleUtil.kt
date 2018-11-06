@file:Suppress("unused")

package com.cabbage.scaffold.ui

import android.arch.lifecycle.*
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import timber.log.Timber

/**
 * Here are utility functions related to arch live data and arch view model
 */

typealias daggerLazy<T> = dagger.Lazy<T>

typealias VMFac = ViewModelProvider.Factory

/**
 * Takes a lazy [ViewModelProvider.Factory], only unwraps it if view model wasn't created before.
 * This is only relevant for ViewModels whose constructors require arguments, thus making it
 * necessary for us to implement custom [ViewModelProvider.Factory]
 */
inline fun <reified T : ViewModel>
        FragmentActivity.getViewModel(lazyFactory: daggerLazy<out VMFac>): T =
        try {
            Timber.v("Provide VM cached")
            getViewModel<T>(null)
        } catch (e: Throwable) {
            Timber.v("Provide VM with factory")
            getViewModel(lazyFactory.get())
        }

/**
 * Get [ViewModel] of type [T], with optional [factory]
 */
inline fun <reified T : ViewModel>
        FragmentActivity.getViewModel(factory: VMFac? = null): T =
        ViewModelProviders.of(this, factory)[T::class.java]


// We need the view model associated with host activity, not fragment itself
// Calling ViewModelProviders.of(this: Fragment) will result in a different instance
/**
 * Get [ViewModel] of type [T] from the view model pool of the activity this fragment is attached to.
 * Returns null if activity is not attached yet.
 */
inline fun <reified T : ViewModel>
        Fragment.getViewModel(factory: VMFac? = null): T? =
        activity?.run { getViewModel(factory) }

/**
 * Fragment version of [getViewModel] with lazy factory. Returns null if activity is not attached yet.
 */
inline fun <reified T : ViewModel>
        Fragment.getViewModel(lazyFactory: daggerLazy<out VMFac>): T? =
        activity?.run { getViewModel(lazyFactory) }

/**
 * Shortcut for [Transformations.map]
 */
fun <X, Y> LiveData<X>.map(func: (X) -> Y): LiveData<Y> =
        Transformations.map(this, func)