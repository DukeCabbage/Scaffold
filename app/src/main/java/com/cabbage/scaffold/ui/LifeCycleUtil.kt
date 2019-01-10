@file:Suppress("unused")

package com.cabbage.scaffold.ui

import android.arch.lifecycle.*
import android.support.v4.app.FragmentActivity
import timber.log.Timber

/**
 * Here are utility functions related to arch live data and arch view model
 */

typealias daggerLazy<T> = dagger.Lazy<T>

typealias VMFactory = ViewModelProvider.Factory

/**
 * Takes a lazy [ViewModelProvider.Factory], only unwraps it if view model wasn't created before.
 * This is only relevant for ViewModels whose constructors require arguments, thus making it
 * necessary for us to implement custom [ViewModelProvider.Factory]
 */
inline fun <reified T : ViewModel>
        FragmentActivity.getViewModel(lazyFactory: daggerLazy<out VMFactory>): T =
        try {
            Timber.d("Provide cached VM")
            ViewModelProviders.of(this)[T::class.java]
        } catch (e: Throwable) {
            Timber.d("Provide VM with factory")
            ViewModelProviders.of(this, lazyFactory.get())[T::class.java]
        }


//// We need the view model associated with host activity, not fragment itself
//// Calling ViewModelProviders.of(this: Fragment) will result in a different instance
///**
// * Fragment version of getViewModel with lazy factory. Returns null if activity is not attached yet.
// */
//inline fun <reified T : ViewModel>
//        Fragment.getViewModel(lazyFactory: daggerLazy<out VMFactory>): T? =
//        activity?.run { getViewModel(lazyFactory) }

/**
 * Shortcut for [Transformations.map]
 */
fun <X, Y> LiveData<X>.map(func: (X) -> Y): LiveData<Y> =
        Transformations.map(this, func)