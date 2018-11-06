package com.cabbage.scaffold.ui.container.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MediatorLiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.cabbage.scaffold.ui.container.domain.AANetworkManager
import timber.log.Timber

class ContainerViewModel
constructor(private val network: AANetworkManager) : ViewModel() {

    val data: MutableLiveData<String> = MutableLiveData()

    val globalCount = network.count

    private val localCount = MutableLiveData<Int>().apply { value = 0 }

    val count: LiveData<Int> = MediatorLiveData<Int>().apply {
        addSource(globalCount) { global ->
            if (global == null) return@addSource
            localCount.value?.also { local -> value = local + global }
        }

        addSource(localCount) { local ->
            if (local == null) return@addSource
            globalCount.value?.also { global -> value = local + global }
        }
    }

    init {
        Timber.i("init")
        fetch()
    }

    fun fetch() {
        data.value = network.address
    }

    fun increaseLocal() {
        localCount.value = (localCount.value ?: 0) + 1
    }

    fun decreaseLocal() {
        localCount.value = (localCount.value ?: 0) - 1
    }

    fun increaseGlobal() {
        globalCount.value = (globalCount.value ?: 0) + 1
    }

    fun decreaseGlobal() {
        globalCount.value = (globalCount.value ?: 0) - 1
    }
}