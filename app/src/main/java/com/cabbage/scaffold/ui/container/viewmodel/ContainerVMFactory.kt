package com.cabbage.scaffold.ui.container.viewmodel

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.cabbage.scaffold.ui.container.domain.AANetworkManager
import timber.log.Timber

class ContainerVMFactory
constructor(private val network: AANetworkManager) : ViewModelProvider.Factory {

    init {
        Timber.i("init")
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(ContainerViewModel::class.java)) {
            return ContainerViewModel(network) as T
        } else {
            throw UnsupportedOperationException()
        }
    }
}