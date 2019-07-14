package com.cabbage.scaffold.ui.main

import android.os.Bundle
import com.cabbage.scaffold.R
import com.cabbage.scaffold.ui.base.BaseActivity
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_next.*
import timber.log.Timber

class NextActivity : BaseActivity() {

    private lateinit var data: String

    override fun onCreate(savedInstanceState: Bundle?) {
        Timber.d("onCreate")
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_next)

        data = intent?.getStringExtra("MEOW") ?: "default"

        tvBundleData.text = data
    }
}