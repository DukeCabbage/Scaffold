package com.cabbage.scaffold.ui.counter.view

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.cabbage.scaffold.R
import com.cabbage.scaffold.ui.counter.viewmodel.ContainerViewModel
import com.cabbage.scaffold.ui.getViewModel
import kotlinx.android.synthetic.main.fragment_control_panel.*
import timber.log.Timber
import java.lang.Exception

class ControlPanelFragment : Fragment() {

    private val viewModel: ContainerViewModel?
        get() = try {
            getViewModel()
        } catch (e: Exception) {
            null
        }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_control_panel, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Timber.d("onViewCreated")
        super.onViewCreated(view, savedInstanceState)

        btn_inc_global.setOnClickListener { viewModel?.increaseGlobal() }
        btn_dec_global.setOnClickListener { viewModel?.decreaseGlobal() }
        btn_inc_local.setOnClickListener { viewModel?.increaseLocal() }
        btn_dec_local.setOnClickListener { viewModel?.decreaseLocal() }
    }
}