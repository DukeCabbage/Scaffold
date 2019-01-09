package com.cabbage.scaffold.ui.counter.view

import android.arch.lifecycle.Observer
import android.os.Bundle
import com.cabbage.scaffold.R
import com.cabbage.scaffold.dagger.ActivityScope
import com.cabbage.scaffold.ui.base.BaseActivity
import com.cabbage.scaffold.ui.counter.viewmodel.ContainerViewModel
import com.cabbage.scaffold.ui.daggerLazy
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.include_app_bar.*
import timber.log.Timber
import javax.inject.Inject

class CounterActivity : BaseActivity() {

    // This `lazy` is probably an overkill...
    @Inject @ActivityScope lateinit var viewModel: daggerLazy<ContainerViewModel>

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
        viewModel.get().data.observe(this, Observer { })

        val existing = supportFragmentManager.findFragmentById(R.id.frame) as? CounterFragment
        if (existing == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.frame, CounterFragment.newInstance(), "counter")
                .commit()
        }
    }
}