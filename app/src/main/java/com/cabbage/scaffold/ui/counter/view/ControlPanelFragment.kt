package com.cabbage.scaffold.ui.counter.view

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.cabbage.scaffold.R
import com.cabbage.scaffold.ui.counter.viewmodel.CounterViewModel
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_control_panel.*
import timber.log.Timber
import javax.inject.Inject

class ControlPanelFragment : Fragment() {

    @Inject lateinit var viewModel: CounterViewModel

    override fun onAttach(context: Context?) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_control_panel, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Timber.v("onViewCreated")
        super.onViewCreated(view, savedInstanceState)

        btn_inc_global.setOnClickListener { viewModel.increaseGlobal() }
        btn_dec_global.setOnClickListener { viewModel.decreaseGlobal() }
        btn_inc_local.setOnClickListener { viewModel.increaseLocal() }
        btn_dec_local.setOnClickListener { viewModel.decreaseLocal() }
    }
}