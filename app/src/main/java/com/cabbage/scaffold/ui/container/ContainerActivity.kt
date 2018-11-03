package com.cabbage.scaffold.ui.container

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v7.widget.Toolbar
import butterknife.BindView
import butterknife.ButterKnife
import com.cabbage.scaffold.R
import com.cabbage.scaffold.dagger.ActivityScope
import com.cabbage.scaffold.ui.base.BaseActivity
import com.cabbage.scaffold.ui.container.viewmodel.ContainerVMFactory
import com.cabbage.scaffold.ui.container.viewmodel.ContainerViewModel
import com.cabbage.scaffold.ui.daggerLazy
import com.cabbage.scaffold.ui.getViewModel
import dagger.android.AndroidInjection
import timber.log.Timber
import javax.inject.Inject

class ContainerActivity : BaseActivity() {

    @BindView(R.id.toolbar) lateinit var toolbar: Toolbar

    @Inject @ActivityScope
    lateinit var lazyVMFactory: daggerLazy<ContainerVMFactory>
    private val viewModel by lazy { getViewModel<ContainerViewModel>(lazyVMFactory) }

    override fun onCreate(savedInstanceState: Bundle?) {
        Timber.d("onCreate")
        AndroidInjection.inject(this)

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_container)
        ButterKnife.bind(this)

        setSupportActionBar(toolbar)
    }

    override fun onResume() {
        super.onResume()
        viewModel.data.observe(this, Observer { Timber.i(it) })
    }
}