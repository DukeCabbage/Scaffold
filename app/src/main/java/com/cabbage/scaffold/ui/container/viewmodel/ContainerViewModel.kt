package com.cabbage.scaffold.ui.container.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.cabbage.scaffold.ui.container.domain.AANetworkManager
import timber.log.Timber

class ContainerViewModel
constructor(private val network: AANetworkManager) : ViewModel() {

    val data: MutableLiveData<String> = MutableLiveData()

    init {
        Timber.i("init")
        fetch()
    }

    fun fetch() {
        data.value = network.address
    }
}