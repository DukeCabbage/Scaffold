package com.cabbage.scaffold.ui.counter.view

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.cabbage.scaffold.R
import com.cabbage.scaffold.ui.counter.viewmodel.ContainerViewModel
import com.cabbage.scaffold.ui.getViewModel
import kotlinx.android.synthetic.main.fragment_counter.*
import timber.log.Timber
import java.lang.Exception

class CounterFragment : Fragment() {

    private val viewModel: ContainerViewModel?
        get() = try {
            getViewModel()
        } catch (e: Exception) {
            null
        }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_counter, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Timber.d("onViewCreated")
        super.onViewCreated(view, savedInstanceState)

        viewModel?.count?.observe(viewLifecycleOwner, Observer {
            tv_local_counter.text = (it ?: 0).toString()
        })

        viewModel?.globalCount?.observe(viewLifecycleOwner, Observer {
            tv_global_counter.text = (it ?: 0).toString()
        })
    }

    companion object {
        fun newInstance() = CounterFragment()
    }
}