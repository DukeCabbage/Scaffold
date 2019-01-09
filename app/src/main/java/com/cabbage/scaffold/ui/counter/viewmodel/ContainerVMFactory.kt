package com.cabbage.scaffold.ui.counter.viewmodel

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.cabbage.scaffold.ui.counter.domain.AANetworkManager
import com.cabbage.scaffold.ui.counter.domain.Counter
import timber.log.Timber

class ContainerVMFactory
constructor(private val network: AANetworkManager,
            private val globalCounter: Counter,
            private val localCounter: Counter) : ViewModelProvider.Factory {

    init {
        Timber.i("init")
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(ContainerViewModel::class.java)) {
            return ContainerViewModel(network, globalCounter, localCounter) as T
        } else {
            throw UnsupportedOperationException()
        }
    }
}