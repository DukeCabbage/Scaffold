package com.cabbage.scaffold.ui.counter.view

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v4.app.Fragment
import com.cabbage.scaffold.R
import com.cabbage.scaffold.ui.base.BaseActivity
import com.cabbage.scaffold.ui.counter.viewmodel.CounterViewModel
import dagger.android.AndroidInjection
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import kotlinx.android.synthetic.main.include_app_bar.*
import timber.log.Timber
import javax.inject.Inject

class CounterActivity : BaseActivity(),
                        HasSupportFragmentInjector {

    @Inject lateinit var viewModel: CounterViewModel

    @Inject lateinit var fragmentInjector: DispatchingAndroidInjector<Fragment>

    override fun supportFragmentInjector() = fragmentInjector

    override fun onCreate(savedInstanceState: Bundle?) {
        Timber.v("onCreate")
        AndroidInjection.inject(this)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_container)

        setSupportActionBar(toolbar)
    }

    override fun onResume() {
        Timber.v("onResume")
        super.onResume()
        viewModel.data.observe(this, Observer { })

        val existing = supportFragmentManager.findFragmentById(R.id.frame) as? CounterFragment
        if (existing == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.frame, CounterFragment.newInstance(), "counter")
                .commit()
        }
    }
}