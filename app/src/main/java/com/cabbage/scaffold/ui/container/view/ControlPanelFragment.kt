package com.cabbage.scaffold.ui.container.view

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import butterknife.ButterKnife
import butterknife.OnClick
import butterknife.Unbinder
import com.cabbage.scaffold.R
import com.cabbage.scaffold.ui.container.viewmodel.ContainerViewModel
import com.cabbage.scaffold.ui.getViewModel
import timber.log.Timber
import java.lang.Exception

class ControlPanelFragment : Fragment() {

    @OnClick(R.id.btn_inc_global) fun incGlobalOnClick() {
        viewModel?.increaseGlobal()
    }

    @OnClick(R.id.btn_dec_global) fun decGlobalOnClick() {
        viewModel?.decreaseGlobal()
    }

    @OnClick(R.id.btn_inc_local) fun incLocalOnClick() {
        viewModel?.increaseLocal()
    }

    @OnClick(R.id.btn_dec_local) fun decLocalOnClick() {
        viewModel?.decreaseLocal()
    }

    private val viewModel: ContainerViewModel?
        get() = try {
            getViewModel()
        } catch (e: Exception) {
            null
        }

    private var unbinder: Unbinder? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_control_panel, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Timber.d("onViewCreated")
        super.onViewCreated(view, savedInstanceState)
        unbinder = ButterKnife.bind(this, view)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        unbinder?.unbind()
    }
}