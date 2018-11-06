package com.cabbage.scaffold.ui.container.view

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.Unbinder
import com.cabbage.scaffold.R
import com.cabbage.scaffold.ui.container.viewmodel.ContainerViewModel
import com.cabbage.scaffold.ui.getViewModel
import timber.log.Timber
import java.lang.Exception

class CounterFragment : Fragment() {

    @BindView(R.id.tv_global_counter) lateinit var tvGlobalCounter: TextView

    @BindView(R.id.tv_local_counter) lateinit var tvLocalCounter: TextView

    private val viewModel: ContainerViewModel?
        get() = try {
            getViewModel()
        } catch (e: Exception) {
            null
        }

    private var unbinder: Unbinder? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_counter, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Timber.d("onViewCreated")
        super.onViewCreated(view, savedInstanceState)
        unbinder = ButterKnife.bind(this, view)

        viewModel?.count?.observe(viewLifecycleOwner, Observer {
            tvLocalCounter.text = (it ?: 0).toString()
        })

        viewModel?.globalCount?.observe(viewLifecycleOwner, Observer {
            tvGlobalCounter.text = (it ?: 0).toString()
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        unbinder?.unbind()
    }

    companion object {
        fun newInstance() = CounterFragment()
    }
}