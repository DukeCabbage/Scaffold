package com.cabbage.scaffold.ui.counter.view

import androidx.lifecycle.Observer
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.cabbage.scaffold.R
import com.cabbage.scaffold.ui.counter.viewmodel.CounterViewModel
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_counter.*
import timber.log.Timber
import javax.inject.Inject

class CounterFragment : androidx.fragment.app.Fragment() {

    @Inject lateinit var viewModel: CounterViewModel

    override fun onAttach(context: Context?) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_counter, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Timber.v("onViewCreated")
        super.onViewCreated(view, savedInstanceState)

        viewModel.count.observe(viewLifecycleOwner, Observer {
            tv_local_counter.text = (it ?: 0).toString()
        })

        viewModel.globalCount.observe(viewLifecycleOwner, Observer {
            tv_global_counter.text = (it ?: 0).toString()
        })
    }

    companion object {
        fun newInstance() = CounterFragment()
    }
}