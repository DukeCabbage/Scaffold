package com.cabbage.scaffold.ui.counter.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MediatorLiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.cabbage.scaffold.ui.counter.domain.Counter
import timber.log.Timber

class CounterViewModel
constructor(private val globalCounter: Counter,
            private val localCounter: Counter) : ViewModel() {

    val data: MutableLiveData<String> = MutableLiveData()

    val globalCount: LiveData<Int>
        get() = globalCounter.count

    private val localCount: LiveData<Int>
        get() = localCounter.count

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
    }

    fun increaseLocal() {
        localCounter.count.value = (localCount.value ?: 0) + 1
    }

    fun decreaseLocal() {
        localCounter.count.value = (localCount.value ?: 0) - 1
    }

    fun increaseGlobal() {
        globalCounter.count.value = (globalCount.value ?: 0) + 1
    }

    fun decreaseGlobal() {
        globalCounter.count.value = (globalCount.value ?: 0) - 1
    }
}