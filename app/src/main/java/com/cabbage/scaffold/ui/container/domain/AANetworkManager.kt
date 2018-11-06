package com.cabbage.scaffold.ui.container.domain

import android.app.Application
import android.arch.lifecycle.MutableLiveData
import android.content.Context
import timber.log.Timber

/**
 * A mock class that has at least one app scoped dependency
 */
class AANetworkManager
constructor(private val context: Context) {

    val count = MutableLiveData<Int>().apply { value = 0 }

    init {
        Timber.i("init")
        print()

        if (context !is Application) {
            throw IllegalArgumentException("Expects application context")
        }
    }

    fun print() {
        Timber.i("App context? ${context is Application}")
        Timber.i("Address: $address")
    }

    val address: String
        get() = System.identityHashCode(this).toString()
}