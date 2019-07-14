package com.cabbage.scaffold.ui.counter.domain

import android.app.Application
import androidx.lifecycle.MutableLiveData
import android.content.Context
import timber.log.Timber

/**
 * A mock class that has at least one app scoped dependency
 */
class Counter
constructor(private val context: Context,
            private val tag: String) {

    val count = MutableLiveData<Int>().apply { value = 0 }

    init {
        Timber.i("init")
        print()

        if (context !is Application) {
            throw IllegalArgumentException("Expects application context")
        }
    }

    fun print() {
//        Timber.i("App context? ${context is Application}")
        Timber.i("Address: $address")
        Timber.i("Tag: $tag")
//        Timber.i("Count: ${count.value}")
    }

    val address: String
        get() = System.identityHashCode(this).toString()
}