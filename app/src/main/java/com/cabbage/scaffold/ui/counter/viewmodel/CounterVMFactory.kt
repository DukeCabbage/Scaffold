package com.cabbage.scaffold.ui.counter.viewmodel

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.cabbage.scaffold.ui.counter.domain.Counter
import timber.log.Timber

class CounterVMFactory
constructor(private val globalCounter: Counter,
            private val localCounter: Counter) : ViewModelProvider.Factory {

    init {
        Timber.i("init")
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(CounterViewModel::class.java)) {
            return CounterViewModel(globalCounter, localCounter) as T
        } else {
            throw UnsupportedOperationException()
        }
    }
}